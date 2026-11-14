package com.iam.identity.domain.model;

import com.explore.kernel.AbstractEntity;
import com.iam.common.domain.base.DomainStrings;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Authenticator-app TOTP credential (RFC 6238) for a single user. */
@Entity
@Table(name = "totp_credential")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class TotpCredential extends AbstractEntity<TotpCredentialId> {

  @Column(nullable = false, unique = true, length = 36)
  private String userId;

  @Column(nullable = false, length = 512)
  private String secretCiphertext;

  @Column(nullable = false)
  private boolean enabled;

  @Column(nullable = false, length = 2048)
  private String backupCodeHashes;

  private TotpCredential(
      TotpCredentialId id,
      String userId,
      String secretCiphertext,
      boolean enabled,
      String backupCodeHashes) {
    super(id);
    this.userId = DomainStrings.requireNonBlank(userId, "userId");
    this.secretCiphertext = DomainStrings.requireNonBlank(secretCiphertext, "secretCiphertext");
    this.enabled = enabled;
    this.backupCodeHashes = DomainStrings.requireNonBlank(backupCodeHashes, "backupCodeHashes");
  }

  /**
   * Creates a disabled TOTP credential pending confirmation.
   *
   * @param userId owning user id
   * @param secretCiphertext encrypted Base32 secret
   * @param backupCodeHashes hashed backup codes (comma-separated)
   * @return new aggregate
   */
  public static TotpCredential createTotpCredential(
      String userId, String secretCiphertext, String backupCodeHashes) {
    return new TotpCredential(
        TotpCredentialId.generateId(), userId, secretCiphertext, false, backupCodeHashes);
  }

  /** Enables TOTP after the user confirms a valid code. */
  public void enableTotp() {
    this.enabled = true;
    touch();
  }

  /** Disables TOTP for the user. */
  public void disableTotp() {
    this.enabled = false;
    touch();
  }

  /**
   * Replaces the encrypted secret and backup hashes (re-enrollment).
   *
   * @param secretCiphertext new ciphertext
   * @param backupCodeHashes new hashed backup codes
   */
  public void rotateSecret(String secretCiphertext, String backupCodeHashes) {
    this.secretCiphertext = DomainStrings.requireNonBlank(secretCiphertext, "secretCiphertext");
    this.backupCodeHashes = DomainStrings.requireNonBlank(backupCodeHashes, "backupCodeHashes");
    this.enabled = false;
    touch();
  }

  /**
   * Updates remaining backup code hashes after one is consumed.
   *
   * @param backupCodeHashes remaining hashes
   */
  public void replaceBackupCodeHashes(String backupCodeHashes) {
    this.backupCodeHashes = DomainStrings.requireNonBlank(backupCodeHashes, "backupCodeHashes");
    touch();
  }
}
