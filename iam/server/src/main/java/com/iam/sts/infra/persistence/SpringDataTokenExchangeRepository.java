package com.iam.sts.infra.persistence;

import com.iam.sts.domain.model.TokenExchange;
import com.iam.sts.domain.model.TokenExchangeId;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataTokenExchangeRepository
    extends JpaRepository<TokenExchange, TokenExchangeId> {}
