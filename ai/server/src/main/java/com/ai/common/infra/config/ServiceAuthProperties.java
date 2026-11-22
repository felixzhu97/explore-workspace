package com.ai.common.infra.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/** Service-to-service auth settings for the shared BFF bearer secret. */
@ConfigurationProperties(prefix = "app.service-auth")
@Getter
@Setter
public class ServiceAuthProperties {

  /**
   * Shared secret for trusted BFF / service callers ({@code Authorization: Bearer}). When blank,
   * service-key anonymous id is disabled and cookie flow is used.
   */
  private String apiKey = "";

  /** Tells whether service-to-service auth is on. */
  public boolean isEnabled() {
    return apiKey != null && !apiKey.isBlank();
  }
}
