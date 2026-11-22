package com.chat.common.security;

import com.chat.auth.service.JwtTokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Chat HS access tokens ({@code ROLE_USER}). Explore IAM Bearer tokens are validated by the
 * OAuth2 resource server ({@code JwtDecoder}) and mapped to Chat users via
 * {@link IamUserProvisioningFilter}.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenService jwtTokenService;

  public JwtAuthenticationFilter(JwtTokenService jwtTokenService) {
    this.jwtTokenService = jwtTokenService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    Authentication existing = SecurityContextHolder.getContext().getAuthentication();
    if (existing != null && existing.isAuthenticated()) {
      filterChain.doFilter(request, response);
      return;
    }
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header != null && header.startsWith("Bearer ")) {
      String token = header.substring(7);
      authenticateChatHs(token);
    }
    filterChain.doFilter(request, response);
  }

  private void authenticateChatHs(String token) {
    try {
      Claims claims = jwtTokenService.parseAccess(token);
      var auth =
          new UsernamePasswordAuthenticationToken(
              claims.getSubject(), null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
      SecurityContextHolder.getContext().setAuthentication(auth);
    } catch (RuntimeException ignored) {
      // Not a Chat HS token; BearerTokenAuthenticationFilter may validate IAM JWT next.
    }
  }

  /** Parses space-delimited {@code scope} or list {@code scp} claims from an IAM JWT. */
  public static List<String> scopesFrom(Jwt jwt) {
    Object scopeClaim = jwt.getClaims().get("scope");
    if (scopeClaim instanceof String scopeString && !scopeString.isBlank()) {
      return Arrays.stream(scopeString.split("\\s+")).filter(s -> !s.isBlank()).toList();
    }
    Object scp = jwt.getClaims().get("scp");
    if (scp instanceof List<?> list) {
      return list.stream().map(Object::toString).filter(s -> !s.isBlank()).toList();
    }
    return List.of();
  }
}
