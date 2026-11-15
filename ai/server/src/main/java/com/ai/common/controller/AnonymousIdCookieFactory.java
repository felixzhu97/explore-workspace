package com.ai.common.controller;

import com.ai.common.infra.config.AnonymousIdProperties;
import java.time.Duration;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

/** Builds Set-Cookie values for anonymous anonymous id. */
@Component
@RequiredArgsConstructor
public class AnonymousIdCookieFactory {

  private final AnonymousIdProperties properties;

  /** Returns the name of the Anonymous id cookie. */
  public String getCookieName() {
    return properties.getCookieName();
  }

  /** Creates an HttpOnly cookie carrying the client id with the configured lifetime and flags. */
  public ResponseCookie issueCookie(String clientId) {
    return ResponseCookie.from(properties.getCookieName(), clientId)
        .httpOnly(true)
        .path("/")
        .maxAge(properties.getMaxAge())
        .sameSite(properties.getSameSite())
        .secure(properties.isSecure())
        .build();
  }

  /** Creates an empty, immediately expiring cookie that removes the anonymous id. */
  public ResponseCookie clearCookie() {
    return ResponseCookie.from(properties.getCookieName(), "")
        .httpOnly(true)
        .path("/")
        .maxAge(Duration.ZERO)
        .sameSite(properties.getSameSite())
        .secure(properties.isSecure())
        .build();
  }

  /** Creates a new random client id. */
  public String generateAnonymousId() {
    return UUID.randomUUID().toString();
  }
}
