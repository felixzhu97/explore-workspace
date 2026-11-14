package com.iam.sts.infra.persistence;

import com.iam.sts.domain.model.ShortLivedCredential;
import com.iam.sts.domain.model.ShortLivedCredentialId;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataShortLivedCredentialRepository
    extends JpaRepository<ShortLivedCredential, ShortLivedCredentialId> {}
