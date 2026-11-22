package com.chat.common.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.chat.auth.service.JwtTokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;

@ExtendWith(MockitoExtension.class)
@DisplayName("JwtAuthenticationFilter")
class JwtAuthenticationFilterTest {

  @Mock private JwtTokenService jwtTokenService;
  @Mock private Claims claims;
  @Mock private HttpServletRequest request;
  @Mock private HttpServletResponse response;
  @Mock private FilterChain filterChain;

  private JwtAuthenticationFilter filter;

  @BeforeEach
  void setUp() {
    filter = new JwtAuthenticationFilter(jwtTokenService);
    SecurityContextHolder.clearContext();
  }

  @AfterEach
  void tearDown() {
    SecurityContextHolder.clearContext();
  }

  @Test
  void shouldAuthenticateWhenChatAccessTokenIsValid() throws Exception {
    when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("Bearer hs-token");
    when(jwtTokenService.parseAccess("hs-token")).thenReturn(claims);
    when(claims.getSubject()).thenReturn("user-123");

    filter.doFilter(request, response, filterChain);

    assertThat(SecurityContextHolder.getContext().getAuthentication().getName())
        .isEqualTo("user-123");
  }
}
