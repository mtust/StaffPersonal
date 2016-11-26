package com.staff.personal.security;

import com.staff.personal.domain.Role;
import com.staff.personal.exception.GeneralServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mtustanovskyy on 11/1/16.
 */
@Slf4j
@Component
public class JwtFilter extends GenericFilterBean {


    public JwtFilter() {

    }

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

            Role role = Role.valueOf((String) claims.get("role"));
            request.setAttribute("role", role);

            log.info("filter:");

        }
        chain.doFilter(req, res);
    }





}
