package com.ai.common.infra.config;

import java.time.Duration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/** Anonymous id cookie settings: name, Secure flag, SameSite policy, and max age. */
@ConfigurationProperties(prefix = "app.anonymous-id")
@Getter
@Setter
public class AnonymousIdProperties {

  /** Cookie name. Prefer {@code __Host-explore_anonymous_id} when Secure is enabled (HTTPS). */
  private String cookieName = "explore_anonymous_id";

  private boolean secure = false;

  private String sameSite = "Lax";

  private Duration maxAge = Duration.ofDays(365);
}
