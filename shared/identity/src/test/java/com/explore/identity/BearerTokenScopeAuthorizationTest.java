package com.explore.identity;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

@DisplayName("BearerTokenScopeAuthorization")
class BearerTokenScopeAuthorizationTest {

  private final AuthorizationManager<RequestAuthorizationContext> manager =
      BearerTokenScopeAuthorization.requireScopeWhenPresent("write:ai_chat");

  @Test
  @DisplayName("should allow a request when it carries no bearer token")
  void shouldAllowARequestWhenItCarriesNoBearerToken() {
    Authentication anonymous =
        new AnonymousAuthenticationToken(
            "key", "anonymous", List.of(new SimpleGrantedAuthority("ROLE_ANONYMOUS")));

    assertThat(isGranted(anonymous)).isTrue();
  }

  @Test
  @DisplayName("should allow a bearer token when it has the scope")
  void shouldAllowABearerTokenWhenItHasTheScope() {
    assertThat(isGranted(token("SCOPE_write:ai_chat"))).isTrue();
  }

  @Test
  @DisplayName("should deny a bearer token when it lacks the scope")
  void shouldDenyABearerTokenWhenItLacksTheScope() {
    assertThat(isGranted(token("SCOPE_write:ai_rag"))).isFalse();
  }

  private boolean isGranted(Authentication authentication) {
    RequestAuthorizationContext context =
        new RequestAuthorizationContext(new MockHttpServletRequest());
    return manager.authorize(() -> authentication, context).isGranted();
  }

  private static JwtAuthenticationToken token(String authority) {
    Jwt jwt =
        Jwt.withTokenValue("token")
            .header("alg", "none")
            .subject("user-1")
            .issuedAt(Instant.now())
            .build();
    return new JwtAuthenticationToken(jwt, List.of(new SimpleGrantedAuthority(authority)));
  }
}
