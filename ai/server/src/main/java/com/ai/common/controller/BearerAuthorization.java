package com.ai.common.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

/**
 * Reads RFC 6750 bearer tokens from the {@code Authorization} header.
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc6750#section-2.1">RFC 6750 §2.1</a>
 */
public final class BearerAuthorization {

  private static final String BEARER_PREFIX = "Bearer ";

  private BearerAuthorization() {}

  /** Returns the bearer token, or {@code null} when the header is absent or not Bearer. */
  public static String extractToken(HttpServletRequest request) {
    String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authorization == null) {
      return null;
    }
    if (!authorization.regionMatches(true, 0, BEARER_PREFIX, 0, BEARER_PREFIX.length())) {
      return null;
    }
    String token = authorization.substring(BEARER_PREFIX.length()).trim();
    return token.isEmpty() ? null : token;
  }
}
