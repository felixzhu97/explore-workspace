package com.iam.sts.domain.repository;

import com.iam.sts.domain.model.TokenExchange;
import java.util.Optional;

/** Persistence port for assumed-role sessions. */
public interface TokenExchangeRepository {

  /**
   * Persists an assumed-role session.
   *
   * @param session session to store
   * @return stored session
   */
  TokenExchange save(TokenExchange session);

  /**
   * Finds a session by id.
   *
   * @param id session id
   * @return matching session when present
   */
  Optional<TokenExchange> findById(String id);
}
