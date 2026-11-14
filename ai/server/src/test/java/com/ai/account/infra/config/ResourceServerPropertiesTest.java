package com.ai.account.infra.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.security.oauth2.server.resource.autoconfigure.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

/**
 * Pins the Spring Boot resource-server behavior that ai relies on for Explore IAM tokens.
 */
@DisplayName("Resource server properties")
class ResourceServerPropertiesTest {

  private final WebApplicationContextRunner runner =
      new WebApplicationContextRunner()
          .withConfiguration(AutoConfigurations.of(OAuth2ResourceServerAutoConfiguration.class))
          .withPropertyValues(
              "spring.security.oauth2.resourceserver.jwt.authorities-claim-name=scope",
              "spring.security.oauth2.resourceserver.jwt.authority-prefix=SCOPE_");

  @Test
  @DisplayName("should start without reaching the issuer when an issuer is set")
  void shouldStartWithoutReachingTheIssuerWhenAnIssuerIsSet() {
    runner
        .withPropertyValues(
            "spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:1")
        .run(context -> assertThat(context).hasNotFailed().hasSingleBean(JwtDecoder.class));
  }

  @Test
  @DisplayName("should not create a decoder when the issuer is blank")
  void shouldNotCreateADecoderWhenTheIssuerIsBlank() {
    runner
        .withPropertyValues("spring.security.oauth2.resourceserver.jwt.issuer-uri=")
        .run(context -> assertThat(context).doesNotHaveBean(JwtDecoder.class));
  }

  @Test
  @DisplayName("should map each token scope to a SCOPE_ authority")
  void shouldMapEachTokenScopeToAScopeAuthority() {
    runner.run(
        context -> {
          Jwt jwt =
              Jwt.withTokenValue("token")
                  .header("alg", "none")
                  .subject("user-1")
                  .claim("scope", "write:ai_chat write:ai_scim")
                  .issuedAt(Instant.now())
                  .build();

          var authorities =
              context.getBean(JwtAuthenticationConverter.class).convert(jwt).getAuthorities();

          assertThat(authorities)
              .extracting(GrantedAuthority::getAuthority)
              .contains("SCOPE_write:ai_chat", "SCOPE_write:ai_scim");
        });
  }
}
