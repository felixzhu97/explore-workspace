package com.iam.sts.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iam.sts.service.TokenExchangeService;
import com.iam.sts.service.TokenExchangeService.TokenExchangeCommand;
import com.iam.sts.service.TokenExchangeService.TokenExchangeResult;
import java.time.Duration;
import java.time.Instant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RFC 8693-style token exchange for assumed IAM roles.
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc8693#section-2">RFC 8693 §2</a>
 */
@RestController
@RequestMapping("/api/v1/tokenExchanges")
public class StsController {

  private static final String ISSUED_TOKEN_TYPE =
      "urn:ietf:params:oauth:token-type:access_token";

  private final TokenExchangeService tokenExchangeService;

  /**
   * Creates the token exchange API controller.
   *
   * @param tokenExchangeService token exchange service
   */
  public StsController(TokenExchangeService tokenExchangeService) {
    this.tokenExchangeService = tokenExchangeService;
  }

  /**
   * Exchanges the caller identity for role-scoped access token credentials.
   *
   * @param request token exchange payload
   * @return RFC 8693 token response fields
   */
  @PostMapping
  @PreAuthorize("isAuthenticated()")
  public TokenExchangeResponse createTokenExchange(@RequestBody TokenExchangeRequest request) {
    TokenExchangeResult result =
        tokenExchangeService.createTokenExchange(
            new TokenExchangeCommand(request.roleArn(), request.sessionName()));
    long expiresIn = Math.max(0, Duration.between(Instant.now(), result.expiration()).toSeconds());
    return new TokenExchangeResponse(
        result.accessToken(), "Bearer", expiresIn, ISSUED_TOKEN_TYPE, result.sessionId());
  }

  /** Request body for role token exchange. */
  public record TokenExchangeRequest(String roleArn, String sessionName) {}

  /**
   * Token exchange response aligned with RFC 8693 §2.2.1 field names.
   *
   * @param sessionId internal session id (extension)
   */
  public record TokenExchangeResponse(
      @JsonProperty("access_token") String accessToken,
      @JsonProperty("token_type") String tokenType,
      @JsonProperty("expires_in") long expiresIn,
      @JsonProperty("issued_token_type") String issuedTokenType,
      String sessionId) {}
}