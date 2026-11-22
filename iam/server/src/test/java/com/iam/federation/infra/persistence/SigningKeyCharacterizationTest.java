package com.iam.federation.infra.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * Locks how a signing key is saved through Spring Data and read back on the Liquibase schema.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Signing key persistence characterization")
class SigningKeyCharacterizationTest {

  @Autowired private TestEntityManager em;

  @Test
  @DisplayName("should round-trip a signing key")
  void shouldRoundTripASigningKey() {
    SigningKey saved =
        new SimpleJpaRepository<SigningKey, Object>(
                SigningKey.class, em.getEntityManager())
            .saveAndFlush(
                new SigningKey(SigningKeyId.generateId(), "public-pem", "private-pem"));
    Object id =
        em.getEntityManager()
            .getEntityManagerFactory()
            .getPersistenceUnitUtil()
            .getIdentifier(saved);
    em.clear();

    SigningKey reloaded = em.find(SigningKey.class, id);

    assertThat(reloaded.getPublicKeyPem()).isEqualTo("public-pem");
    assertThat(reloaded.getPrivateKeyPem()).isEqualTo("private-pem");
    assertThat(reloaded.getCreatedAt()).isNotNull();
  }
}
