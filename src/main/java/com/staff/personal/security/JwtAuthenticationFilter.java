package com.staff.personal.security;


/**
 * Created by mtustanovskyy on 11/15/16.
 */
//public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//
//    public JwtAuthenticationFilter() {
//        super("/api/**");
//    }
//
//    @Override
//    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
//        return true;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//        String header = request.getHeader("Authorization");
//
//        if (header == null || !header.startsWith("OAuth ")) {
//            throw new PermissionDeniedException("No JWT token found in request headers");
//        }
//
//        String authToken = header.substring(6);
//
//        JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);
//
//        return getAuthenticationManager().authenticate(authRequest);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
//            throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
//
//        // As this authentication is in HTTP header, after success we need to continue the request normally
//        // and return the response as if the resource was not secured at all
//        chain.doFilter(request, response);
//    }
//}
