package com.ahamdah.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * CustomFilter is  Responsible to handle unauthorized response generated after keycloak enforcer policy return .
 *
 * @author AHMED FAHAD ABUHAMDAH (AHAMDAH) (Software Engineer)
 * @since 2024-09-09
 * @see com.ahamdah.config.SecurityConfig
 */
@Component
public class CustomFilter implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

    if(httpServletResponse.getStatus() == HttpStatus.SC_FORBIDDEN) {
      httpServletResponse.getWriter().write("{\n" +
          "    \"status\": 403,\n" +
          "    \"error\": \"Unauthorized error \",\n" +
          "    \"message\": \"user not authorized for this end-point\"\n" +
          "}");
      httpServletResponse.setStatus(HttpStatus.SC_FORBIDDEN);
      httpServletResponse.setContentType("application/json");
    }
  }
}
