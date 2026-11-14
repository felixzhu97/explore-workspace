package com.explore.provisioning;

import static org.assertj.core.api.Assertions.assertThat;

import com.unboundid.scim2.common.types.Email;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ScimEmails")
class ScimEmailsTest {

  @Test
  @DisplayName("should pick the primary email over the first one")
  void shouldPickThePrimaryEmailOverTheFirstOne() {
    List<Email> emails =
        List.of(
            new Email().setValue("home@example.test"),
            new Email().setValue("work@example.test").setPrimary(true));

    assertThat(ScimEmails.primary(emails)).isEqualTo("work@example.test");
  }

  @Test
  @DisplayName("should fall back to the first email when none is primary")
  void shouldFallBackToTheFirstEmailWhenNoneIsPrimary() {
    List<Email> emails =
        List.of(new Email().setValue("a@example.test"), new Email().setValue("b@example.test"));

    assertThat(ScimEmails.primary(emails)).isEqualTo("a@example.test");
  }

  @Test
  @DisplayName("should return null when there are no emails")
  void shouldReturnNullWhenThereAreNoEmails() {
    assertThat(ScimEmails.primary(null)).isNull();
    assertThat(ScimEmails.primary(List.of())).isNull();
  }

  @Test
  @DisplayName("should wrap an address as the primary work email")
  void shouldWrapAnAddressAsThePrimaryWorkEmail() {
    Email email = ScimEmails.work("a@example.test").getFirst();

    assertThat(email.getValue()).isEqualTo("a@example.test");
    assertThat(email.getType()).isEqualTo("work");
    assertThat(email.getPrimary()).isTrue();
  }
}
