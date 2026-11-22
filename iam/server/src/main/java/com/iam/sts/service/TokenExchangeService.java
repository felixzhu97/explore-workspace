package com.iam.sts.service;

import com.iam.common.domain.model.ResourceName;
import com.iam.identity.domain.model.Role;
import com.iam.identity.domain.repository.RoleRepository;
import com.iam.sts.domain.event.RoleAssumedEvent;
import com.iam.sts.domain.model.TokenExchange;
import com.iam.sts.domain.repository.TokenExchangeRepository;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Issues temporary JWT credentials for an assumed IAM role. */
@Service
public class TokenExchangeService {

  private final RoleRepository roleRepository;
  private final TokenExchangeRepository sessionRepository;
  private final JwtEncoder jwtEncoder;
  private final ApplicationEventPublisher eventPublisher;
  private final Duration sessionTtl;

  /**
   * Creates the assume-role service.
   *
   * @param roleRepository role repository
   * @param sessionRepository assumed-role session repository
   * @param jwtEncoder JWT encoder
   * @param eventPublisher domain event publisher
   * @param sessionTtl session time-to-live
   */
  public TokenExchangeService(
      RoleRepository roleRepository,
      TokenExchangeRepository sessionRepository,
      JwtEncoder jwtEncoder,
      ApplicationEventPublisher eventPublisher,
      @Value("${app.security.token.assume-role-ttl:PT1H}") Duration sessionTtl) {
    this.roleRepository = roleRepository;
    this.sessionRepository = sessionRepository;
    this.jwtEncoder = jwtEncoder;
    this.eventPublisher = eventPublisher;
    this.sessionTtl = sessionTtl;
  }

  /**
   * Assumes a role and returns temporary credentials.
   *
   * @param command assume-role input
   * @return access token and session metadata
   */
  @Transactional
  public TokenExchangeResult createTokenExchange(TokenExchangeCommand command) {
    Objects.requireNonNull(command, "command");
    ResourceName roleArn = new ResourceName(command.roleArn());
    Role role =
        roleRepository
            .findByResourceName(roleArn.value())
            .orElseThrow(() -> new IllegalArgumentException("role not found: " + roleArn));
    String caller = currentPrincipal();
    Instant expiresAt = Instant.now().plus(sessionTtl);
    TokenExchange session =
        sessionRepository.save(
            TokenExchange.createTokenExchange(
                roleArn, command.sessionName(), caller, expiresAt));
    String accessToken = encodeToken(session, role);
    eventPublisher.publishEvent(new RoleAssumedEvent(role.getId().getValue()));
    return new TokenExchangeResult(accessToken, session.getExpiresAt(), session.getId().getValue());
  }

  private String encodeToken(TokenExchange session, Role role) {
    Instant now = Instant.now();
    JwtClaimsSet claims =
        JwtClaimsSet.builder()
            .issuer("explore-iam-sts")
            .subject(session.getCallerPrincipal())
            .issuedAt(now)
            .expiresAt(session.getExpiresAt())
            .claim("role_arn", session.getRoleArn().value())
            .claim("session_name", session.getSessionName())
            .claim("session_id", session.getId().getValue())
            .claim("role_name", role.getName())
            .build();
    return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

  private static String currentPrincipal() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null || !auth.isAuthenticated()) {
      throw new IllegalStateException("caller must be authenticated");
    }
    return auth.getName();
  }

  /** Input for token exchange. */
  public record TokenExchangeCommand(String roleArn, String sessionName) {}

  /** Token exchange outcome before RFC 8693 response shaping. */
  public record TokenExchangeResult(String accessToken, Instant expiration, String sessionId) {}
}
