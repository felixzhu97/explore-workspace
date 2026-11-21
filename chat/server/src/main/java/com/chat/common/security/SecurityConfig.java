package com.chat.common.security;

import com.explore.identity.BearerTokenScopeAuthorization;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Chat API security: public auth endpoints; Explore IAM Bearer tokens via OAuth2
 * resource server with GitHub-style module scopes; Chat HS JWT login via {@link
 * JwtAuthenticationFilter}.
 *
 * <p>Verify IAM: set {@code CHAT_IAM_ISSUER_URI} to a running Explore IAM issuer, obtain an access
 * token with {@code write:chat_messaging} or {@code write:chat_social}, then {@code curl -H
 * "Authorization: Bearer $TOKEN" http://localhost:9001/api/v1/chats}.
 *
 * @see <a
 *     href="https://docs.github.com/en/apps/oauth-apps/building-oauth-apps/scopes-for-oauth-apps">GitHub
 *     OAuth scopes</a>
 */
@Configuration
public class SecurityConfig {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  @Bean
  SecurityFilterChain securityFilterChain(
      HttpSecurity http,
      JwtAuthenticationFilter jwtFilter,
      ObjectProvider<IamUserProvisioningFilter> iamUserProvisioningFilter,
      ObjectProvider<JwtDecoder> jwtDecoder)
      throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .cors(Customizer.withDefaults())
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers("/actuator/health", "/actuator/info", "/api/v1/health")
                    .permitAll()
                    .requestMatchers(
                        HttpMethod.POST,
                        "/api/v1/auth/register",
                        "/api/v1/auth/login",
                        "/api/v1/auth/forgotPassword",
                        "/api/v1/auth/resetPassword")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/v1/auth/refreshToken")
                    .permitAll()
                    .requestMatchers("/api/v1/admin/**")
                    .hasAuthority("SCOPE_admin:chat")
                    .requestMatchers(
                        "/api/v1/chats/**",
                        "/api/v1/calls/**",
                        "/api/v1/media/**",
                        "/api/v1/notifications/**")
                    .access(
                        BearerTokenScopeAuthorization.requireScopeForBearerTokens(
                            "write:chat_messaging"))
                    .requestMatchers(
                        "/api/v1/posts/**",
                        "/api/v1/users/**",
                        "/api/v1/groups/**",
                        "/api/v1/status/**",
                        "/api/v1/search/**")
                    .access(
                        BearerTokenScopeAuthorization.requireScopeForBearerTokens(
                            "write:chat_social"))
                    .requestMatchers("/api/v1/**")
                    .authenticated()
                    .anyRequest()
                    .permitAll());
    if (jwtDecoder.getIfAvailable() != null) {
      http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
      http.addFilterBefore(jwtFilter, BearerTokenAuthenticationFilter.class);
      iamUserProvisioningFilter.ifAvailable(
          provisioning -> http.addFilterAfter(provisioning, AuthorizationFilter.class));
    } else {
      http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
    return http.build();
  }
}
