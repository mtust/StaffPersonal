package com.staff.personal.security;

import com.staff.personal.domain.Role;
import com.staff.personal.exception.GeneralServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ResourceInfo;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mtustanovskyy on 11/1/16.
 */
@Slf4j
@Component
public class JwtFilter extends GenericFilterBean {


    org.springframework.batch.admin.web.util.ResourceInfo resourceInfo1;


    RequestContextHolder requestContextHolder;
    @Autowired
    private ApplicationContext appContext;

    private WebApplicationContext webApplicationContext;

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;

        HttpServletResponse response = (HttpServletResponse) res;

        if(!"OPTIONS".equals(request.getMethod())){
            response.addHeader("Access-Control-Allow-Origin", "*");

            final String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("OAuth ")) {
                throw new ServletException("Missing or invalid Authorization header.");
            }

            final String token = authHeader.substring(6); // The part after "Bearer "

            Claims claims = null;

            try {
                claims = Jwts.parser().setSigningKey("secretkey")
                        .parseClaimsJws(token).getBody();
                request.setAttribute("claims", claims);
            } catch (final SignatureException e) {
                log.warn(e.getMessage());
                throw new GeneralServiceException("Invalid token.");
            }



            // Get the resource class which matches with the requested URL
            // Extract the roles declared by it
//        Class<?> resourceClass = resourceInfo.getResourceClass();
//        List<Role> classRoles = extractRoles(resourceClass);
//
//        // Get the resource method which matches with the requested URL
//        // Extract the roles declared by it
//        Method resourceMethod = resourceInfo.getResourceMethod();
//        List<Role> methodRoles = extractRoles(resourceMethod);
//
//        try {
//
//            Role role = (Role) claims.get("role");
//
//            // Check if the user is allowed to execute the method
//            // The method annotations override the class annotations
//            if (methodRoles.isEmpty()) {
//                checkPermissions(classRoles, role);
//            } else {
//                checkPermissions(methodRoles, role);
//            }
//
//        } catch (Exception e) {
//            throw  new PermissionDeniedException(Response.Status.FORBIDDEN.getReasonPhrase());
//        }




            log.info("filter:");

        }
        chain.doFilter(req, res);
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
                throw new AuthenticationException();
            }
        }
    }

}
