package com.iam.sts.infra.persistence;

import com.iam.sts.domain.model.TokenExchange;
import com.iam.sts.domain.model.TokenExchangeId;
import com.iam.sts.domain.repository.TokenExchangeRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
class JpaTokenExchangeRepository implements TokenExchangeRepository {

  private final SpringDataTokenExchangeRepository springData;

  JpaTokenExchangeRepository(SpringDataTokenExchangeRepository springData) {
    this.springData = springData;
  }

  @Override
  public TokenExchange save(TokenExchange session) {
    return springData.save(session);
  }

  @Override
  public Optional<TokenExchange> findById(String id) {
    return springData.findById(TokenExchangeId.createId(id));
  }
}
