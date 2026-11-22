package com.iam.federation.infra.persistence;

import com.explore.kernel.AbstractImmutableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Persisted RSA signing key pair for the OIDC Authorization Server. */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
class SigningKey extends AbstractImmutableEntity<SigningKeyId> {

  @Getter(AccessLevel.PACKAGE)
  @Column(name = "public_key_pem", nullable = false, columnDefinition = "clob")
  private String publicKeyPem;

  @Getter(AccessLevel.PACKAGE)
  @Column(name = "private_key_pem", nullable = false, columnDefinition = "clob")
  private String privateKeyPem;

  SigningKey(SigningKeyId id, String publicKeyPem, String privateKeyPem) {
    super(id);
    this.publicKeyPem = publicKeyPem;
    this.privateKeyPem = privateKeyPem;
  }
}
