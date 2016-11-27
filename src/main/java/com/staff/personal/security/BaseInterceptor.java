package com.staff.personal.security;

import com.staff.personal.domain.Role;
import com.staff.personal.exception.PermissionDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mtustanovskyy on 11/24/16.
 */
@Slf4j
public class BaseInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String controllerName = "";
        String actionName = "";

        if(!"OPTIONS".equals(request.getMethod())) {

            if (handler instanceof HandlerMethod) {
                // there are cases where this handler isn't an instance of HandlerMethod, so the cast fails.
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                //controllerName = handlerMethod.getBean().getClass().getSimpleName().replace("Controller", "");
                controllerName = handlerMethod.getBeanType().getSimpleName().replace("Controller", "");
                actionName = handlerMethod.getMethod().getName();
                log.info("controller: " + controllerName);
                log.info("action:" + actionName);

                // Get the resource method which matches with the requested URL
                // Extract the roles declared by it
                Method resourceMethod = handlerMethod.getMethod();
                List<Role> methodRoles = extractRoles(resourceMethod);

                try {

                    Role role = (Role) request.getAttribute("role");

                    // Check if the user is allowed to execute the method
                    // The method annotations override the class annotations
                    checkPermissions(methodRoles, role);

                } catch (Exception e) {
                    throw new PermissionDeniedException(Response.Status.FORBIDDEN.getReasonPhrase());
                }
            }
        }

        return true;

    }


    // Extract the roles from the annotated element
    private List<Role> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<Role>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<Role>();
            } else {
                Role[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }


    private void checkPermissions(List<Role> allowedRoles, Role userRole) throws Exception {
        if (allowedRoles.isEmpty()) {
            return;
        } else {
            if (userRole == null || !allowedRoles.contains(userRole)) {
                throw new PermissionDeniedException("this user have't role");
            }
        }
    }

}