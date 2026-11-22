package com.chat.common.security;

import com.chat.auth.service.IamUserProvisioner;
import com.chat.users.domain.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Maps a validated Explore IAM JWT ({@link JwtAuthenticationToken}) to a Chat user id principal
 * after Spring authorization has checked module scopes.
 */
@Component
@ConditionalOnBean(JwtDecoder.class)
public class IamUserProvisioningFilter extends OncePerRequestFilter {

  private final IamUserProvisioner iamUserProvisioner;

  public IamUserProvisioningFilter(IamUserProvisioner iamUserProvisioner) {
    this.iamUserProvisioner = iamUserProvisioner;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication instanceof JwtAuthenticationToken jwtAuth) {
      try {
        User user = iamUserProvisioner.resolve(jwtAuth.getToken());
        if (user.isDeleted() || user.isDisabled()) {
          response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
          return;
        }
        var mapped =
            new UsernamePasswordAuthenticationToken(user.getId(), null, jwtAuth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(mapped);
      } catch (RuntimeException ex) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return;
      }
    }
    filterChain.doFilter(request, response);
  }
}
