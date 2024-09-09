package com.ahamdah.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * CustomFilter is  Responsible to handle unauthorized token .
 *
 * @author AHMED FAHAD ABUHAMDAH (AHAMDAH) (Software Engineer)
 * @since 2024-09-09
 * @see com.ahamdah.config.SecurityConfig
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    response.setStatus(HttpStatus.SC_UNAUTHORIZED);
    response.getWriter().write("{\n" +
        "    \"status\": " + HttpStatus.SC_UNAUTHORIZED + ",\n" +
        "    \"error\": \"" + authException.getMessage() + "\",\n" +
        "    \"message\": \"" + "provide the token or check Your token may be expired." + "\",\n" +
        "}");
    response.setHeader(HTTP.CONTENT_TYPE, "application/json");
  }
}
