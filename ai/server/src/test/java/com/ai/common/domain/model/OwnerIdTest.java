package com.ai.common.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("OwnerId")
class OwnerIdTest {

  @Test
  void shouldBuildClientKeyWhenForClient() {
    OwnerId key = OwnerId.createClientKey("  abc-123  ");

    assertThat(key.getValue()).isEqualTo("c:abc-123");
    assertThat(key.isClient()).isTrue();
    assertThat(key.isAccount()).isFalse();
  }

  @Test
  void shouldBuildAccountKeyWhenForAccount() {
    OwnerId key = OwnerId.createAccountKey("user-9");

    assertThat(key.getValue()).isEqualTo("u:user-9");
    assertThat(key.isAccount()).isTrue();
    assertThat(key.isClient()).isFalse();
  }

  @Test
  void shouldParseRawValueWhenPrefixed() {
    assertThat(OwnerId.parseKey("c:guest").getValue()).isEqualTo("c:guest");
    assertThat(OwnerId.parseKey("u:acct").getValue()).isEqualTo("u:acct");
  }

  @Test
  void shouldRejectBlankWhenCreating() {
    assertThatThrownBy(() -> OwnerId.createClientKey(" "))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> OwnerId.createAccountKey(""))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> OwnerId.parseKey("x:nope"))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("should allow merging a guest key into an account key")
  void shouldAllowMergingAGuestKeyIntoAnAccountKey() {
    OwnerId.createClientKey("guest-1").requireMergeableInto(OwnerId.createAccountKey("user-1"));
  }

  @Test
  @DisplayName("should reject merging when source is not a guest or target is not an account")
  void shouldRejectMergingWhenSourceIsNotAGuestOrTargetIsNotAnAccount() {
    OwnerId guest = OwnerId.createClientKey("guest-1");
    OwnerId account = OwnerId.createAccountKey("user-1");

    assertThatThrownBy(() -> account.requireMergeableInto(OwnerId.createAccountKey("user-2")))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> guest.requireMergeableInto(OwnerId.createClientKey("guest-2")))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> OwnerId.UNOWNED.requireMergeableInto(account))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
