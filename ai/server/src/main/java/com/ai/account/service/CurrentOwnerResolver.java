package com.ai.account.service;

import com.ai.account.domain.model.Account;
import com.ai.account.domain.model.ClientId;
import com.ai.account.domain.repository.AccountRepository;
import com.ai.common.domain.model.OwnerId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Resolves data {@link OwnerId} from guest Anonymous id and/or OAuth / IAM JWT. */
@Service
@RequiredArgsConstructor
public class CurrentOwnerResolver {

  private final AccountRepository accountRepository;
  private final IamAccountService iamAccountService;

  /** Resolves owner from guest Anonymous id and/or OAuth / IAM JWT authentication. */
  @Transactional
  public OwnerId resolveOwner(String clientId, Authentication authentication) {
    Optional<Account> fromAuth = resolveSignedInUser(authentication);
    if (fromAuth.isPresent()) {
      return fromAuth.get().createOwnerId();
    }
    if (ClientId.isValid(clientId)) {
      Optional<Account> linked = accountRepository.findByLinkedClientId(ClientId.parseId(clientId));
      if (linked.isPresent()) {
        return linked.get().createOwnerId();
      }
    }
    return OwnerId.createClientKey(clientId);
  }

  /**
   * Ensures an account exists for an IAM access token and returns its owner key.
   *
   * @param jwt validated IAM JWT
   * @return account owner key
   */
  @Transactional
  public OwnerId resolveFromJwt(Jwt jwt) {
    return iamAccountService.signIn(jwt).createOwnerId();
  }

  private Optional<Account> resolveSignedInUser(Authentication authentication) {
    if (authentication instanceof JwtAuthenticationToken jwtAuth) {
      return Optional.of(iamAccountService.signIn(jwtAuth.getToken()));
    }
    Optional<Account> account =
        OAuthSignIn.createSignIn(authentication)
            .flatMap(signIn -> accountRepository.findByIdentity(signIn.identity()));
    account.ifPresent(Account::requireActive);
    return account;
  }
}
