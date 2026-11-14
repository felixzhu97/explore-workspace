package com.iam.federation.infra.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataSigningKeyRepository extends JpaRepository<SigningKeyEntity, SigningKeyId> {

  Optional<SigningKeyEntity> findFirstByOrderByCreatedAtDesc();
}
