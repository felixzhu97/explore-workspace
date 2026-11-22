package com.explore.identity;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

/**
 * Authorizes requests by OAuth 2.0 scope only when they carry a bearer token.
 *
 * <p>Products that also serve anonymous or cookie-session traffic use this to require a scope
 * from token callers without locking out everyone else.
 */
public final class BearerTokenScopeAuthorization {

  private static final String AUTHORITY_PREFIX = "SCOPE_";

  private BearerTokenScopeAuthorization() {}

  /**
   * Requires the scope for bearer-token requests and allows every other request.
   *
   * @param scope the OAuth 2.0 scope, such as {@code write:ai_chat}
   * @return the authorization manager for a request matcher
   */
  public static AuthorizationManager<RequestAuthorizationContext> requireScopeForBearerTokens(
      String scope) {
    String authority = AUTHORITY_PREFIX + scope;
    return (authentication, context) -> {
      Authentication current = authentication.get();
      if (!(current instanceof JwtAuthenticationToken)) {
        return new AuthorizationDecision(true);
      }
      boolean hasScope =
          current.getAuthorities().stream()
              .anyMatch(granted -> authority.equals(granted.getAuthority()));
      return new AuthorizationDecision(hasScope);
    };
  }
}
