package com.chat.common.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.chat.auth.service.IamUserProvisioner;
import com.chat.users.domain.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@ExtendWith(MockitoExtension.class)
@DisplayName("IamUserProvisioningFilter")
class IamUserProvisioningFilterTest {

  @Mock private IamUserProvisioner iamUserProvisioner;
  @Mock private HttpServletRequest request;
  @Mock private HttpServletResponse response;
  @Mock private FilterChain filterChain;

  private IamUserProvisioningFilter filter;

  @BeforeEach
  void setUp() {
    filter = new IamUserProvisioningFilter(iamUserProvisioner);
    SecurityContextHolder.clearContext();
  }

  @AfterEach
  void tearDown() {
    SecurityContextHolder.clearContext();
  }

  @Test
  void shouldMapIamJwtToChatUserId() throws Exception {
    Jwt jwt =
        Jwt.withTokenValue("iam-token")
            .header("alg", "none")
            .subject("sub")
            .claim("email", "a@b.com")
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusSeconds(60))
            .build();
    SecurityContextHolder.getContext()
        .setAuthentication(
            new JwtAuthenticationToken(
                jwt, List.of(new SimpleGrantedAuthority("SCOPE_write:chat_messaging"))));
    User user = User.createUser("alice", "a@b.com", "hash");
    when(iamUserProvisioner.resolve(jwt)).thenReturn(user);

    filter.doFilter(request, response, filterChain);

    assertThat(SecurityContextHolder.getContext().getAuthentication().getName())
        .isEqualTo(user.getId());
    verify(filterChain).doFilter(request, response);
  }
}
