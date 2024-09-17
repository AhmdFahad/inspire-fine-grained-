package com.ahamdah.config;

import com.ahamdah.security.CustomAuthenticationEntryPoint;
import com.ahamdah.security.CustomFilter;
import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter;
import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
import org.keycloak.adapters.authorization.spi.HttpRequest;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.keycloak.util.JsonSerialization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Value("${keycloak.policy-enforcer.realm}")
  private String realm;
  @Value("${keycloak.policy-enforcer.auth-server-url}")
  private String authServerUrl;
  @Value("${keycloak.policy-enforcer.resource}")
  private String resource;
  @Value("${keycloak.policy-enforcer.credentials.secret}")
  private String secret;

  private CustomFilter customFilter;

  private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

  public SecurityConfig(CustomFilter customFilter, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
    this.customFilter = customFilter;
    this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(authorize -> authorize
            .anyRequest().authenticated()
        )
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults())
            .authenticationEntryPoint(customAuthenticationEntryPoint))
        .addFilterAfter(createPolicyEnforcerFilter(), BearerTokenAuthenticationFilter.class)
        .addFilterAfter(customFilter, BearerTokenAuthenticationFilter.class)
        .build();
  }

    private ServletPolicyEnforcerFilter createPolicyEnforcerFilter() {
    return new ServletPolicyEnforcerFilter(new ConfigurationResolver() {
      @Override
      public PolicyEnforcerConfig resolve(HttpRequest request) {
        PolicyEnforcerConfig config = new PolicyEnforcerConfig();
        config.setRealm(realm);
        config.setAuthServerUrl(authServerUrl);
        config.setResource(resource);

        Map<String, Object> credentials = new HashMap<>();
        credentials.put("secret", secret);
        config.setCredentials(credentials);
        return config;
      }
    });
  }
}
