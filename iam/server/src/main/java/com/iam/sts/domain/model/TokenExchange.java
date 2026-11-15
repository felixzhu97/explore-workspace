package com.iam.sts.domain.model;

import com.explore.kernel.AbstractImmutableEntity;
import com.iam.common.domain.base.DomainStrings;
import com.iam.common.domain.model.ResourceName;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Temporary credentials issued by AssumeRole. */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class TokenExchange extends AbstractImmutableEntity<TokenExchangeId> {

  @Embedded
  @AttributeOverride(
      name = "value",
      column = @Column(name = "role_arn", nullable = false, length = 512))
  @Valid
  private ResourceName roleArn;

  @NotBlank
  @Size(max = 128)
  @Column(nullable = false, length = 128)
  private String sessionName;

  @NotBlank
  @Size(max = 512)
  @Column(nullable = false, length = 512)
  private String callerPrincipal;

  @Column(nullable = false)
  private Instant expiresAt;

  private TokenExchange(
      TokenExchangeId id,
      ResourceName roleArn,
      String sessionName,
      String callerPrincipal,
      Instant expiresAt) {
    super(id);
    this.roleArn = roleArn;
    this.sessionName = requireSessionName(sessionName);
    this.callerPrincipal = callerPrincipal;
    this.expiresAt = expiresAt;
  }

  /** Creates a new assumed-role session. */
  public static TokenExchange createTokenExchange(
      ResourceName roleArn, String sessionName, String callerPrincipal, Instant expiresAt) {
    return new TokenExchange(
        TokenExchangeId.generateId(), roleArn, sessionName, callerPrincipal, expiresAt);
  }

  /** Returns true when the session has expired. */
  public boolean isExpired(Instant now) {
    return !expiresAt.isAfter(now);
  }

  private static String requireSessionName(String sessionName) {
    return DomainStrings.requireNonBlank(sessionName, "session name");
  }
}
