package com.ai.account.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ai.account.controller.dto.AccountMeResponse;
import com.ai.account.controller.dto.AccountMode;
import com.ai.account.controller.dto.AccountPlan;
import com.ai.account.controller.dto.LoginProvider;
import com.ai.account.service.AccountService;
import com.ai.common.controller.GlobalExceptionHandler;
import com.ai.testsupport.AnonymousIdRequestPostProcessor;
import com.ai.testsupport.SliceWebMvcTest;
import java.time.Instant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@SliceWebMvcTest(controllers = AccountController.class)
@Import(GlobalExceptionHandler.class)
@DisplayName("AccountController")
class AccountControllerTest {

  @Autowired private MockMvcTester mvc;

  @MockitoBean private AccountService accountService;

  @AfterEach
  void clearSecurityContext() {
    SecurityContextHolder.clearContext();
  }

  @Test
  @DisplayName("should return anonymous account when anonymous id present")
  void shouldReturnAnonymousAccountWhenAnonymousIdPresent() {
    when(accountService.getCurrentAccount("cid-123"))
        .thenReturn(
            new AccountMeResponse(
                AccountMode.ANONYMOUS,
                "cid-123",
                null,
                null,
                null,
                AccountPlan.FREE,
                false,
                java.util.List.of()));

    var result =
        mvc.get()
            .uri("/api/account/me")
            .with(AnonymousIdRequestPostProcessor.withClientId("cid-123"))
            .exchange();

    assertThat(result).hasStatusOk();
    assertThat(result).bodyJson().extractingPath("$.mode").asString().isEqualTo("anonymous");
    assertThat(result).bodyJson().extractingPath("$.clientId").asString().isEqualTo("cid-123");
    assertThat(result).bodyJson().extractingPath("$.plan").asString().isEqualTo("free");
    assertThat(result).bodyJson().extractingPath("$.loginAvailable").asBoolean().isFalse();
    assertThat(result).bodyJson().extractingPath("$.userId").isNull();
    verify(accountService).getCurrentAccount("cid-123");
  }

  @Test
  @DisplayName("should return account when IAM JWT present without client cookie")
  void shouldReturnAccountWhenIamJwtPresentWithoutClientCookie() {
    when(accountService.getCurrentAccount(null))
        .thenReturn(
            new AccountMeResponse(
                AccountMode.AUTHENTICATED,
                null,
                "user-1",
                "iam@example.com",
                "iam@example.com",
                AccountPlan.FREE,
                true,
                java.util.List.of(LoginProvider.EXPLORE_IAM)));

    Jwt jwt =
        Jwt.withTokenValue("t")
            .header("alg", "none")
            .subject("iam-sub")
            .claim("email", "iam@example.com")
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusSeconds(60))
            .build();
    SecurityContextHolder.getContext()
        .setAuthentication(
            new JwtAuthenticationToken(jwt, AuthorityUtils.createAuthorityList("ROLE_USER")));

    var result = mvc.get().uri("/api/account/me").exchange();

    assertThat(result).hasStatusOk();
    assertThat(result).bodyJson().extractingPath("$.mode").asString().isEqualTo("authenticated");
    verify(accountService).getCurrentAccount(null);
  }

  @Test
  @DisplayName("should fail when anonymous id missing")
  void shouldFailWhenAnonymousIdMissing() {
    assertThat(mvc.get().uri("/api/account/me"))
        .hasStatus(HttpStatus.UNAUTHORIZED)
        .bodyJson()
        .extractingPath("$.type")
        .asString()
        .isEqualTo("https://www.felixzhu.chat/problems/anonymous-id-required");
  }
}
