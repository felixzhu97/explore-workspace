package com.iam.common.domain.base;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import com.iam.audit.domain.model.AdminActivity;
import com.iam.audit.domain.model.AuditActor;
import com.iam.audit.domain.model.AuditOutcome;
import com.iam.audit.domain.model.AuditTarget;
import com.iam.audit.domain.model.DataAccessLog;
import com.iam.common.domain.model.Effect;
import com.iam.common.domain.model.PrincipalId;
import com.iam.common.domain.model.ReasonCode;
import com.iam.common.domain.model.Resource;
import com.iam.common.domain.model.ResourceName;
import com.iam.federation.domain.model.FederatedIdentity;
import com.iam.identity.domain.model.ExternalId;
import com.iam.identity.domain.model.Group;
import com.iam.identity.domain.model.Role;
import com.iam.identity.domain.model.TotpCredential;
import com.iam.identity.domain.model.User;
import com.iam.policy.domain.model.Permission;
import com.iam.policy.domain.model.Policy;
import com.iam.policy.domain.model.PolicyAssignment;
import com.iam.policy.domain.model.Rule;
import com.iam.scim.domain.model.ProvisioningTarget;
import com.iam.sts.domain.model.TokenExchange;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * Locks how every IAM entity is saved through Spring Data and read back on the Liquibase schema,
 * so moving the entity base classes cannot change persistence behavior unnoticed.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Entity persistence characterization")
class EntityPersistenceCharacterizationTest {

  private static final Duration CLOCK_TOLERANCE = Duration.ofMillis(1);

  @Autowired private TestEntityManager em;

  @Test
  @DisplayName("should apply the Liquibase changelog that Hibernate validates against")
  void shouldApplyTheLiquibaseChangelogThatHibernateValidatesAgainst() {
    Number applied =
        (Number)
            em.getEntityManager()
                .createNativeQuery("select count(*) from databasechangelog")
                .getSingleResult();

    assertThat(applied.longValue()).isPositive();
  }

  @Test
  @DisplayName("should round-trip a user with role assignments and no external id")
  void shouldRoundTripAUserWithRoleAssignmentsAndNoExternalId() {
    Role role = saveAndReload(Role.createRole("Characterization Role", " "));
    User user = User.createUser("characterized", "characterized@example.test", "{noop}x");
    user.assignRole(idText(role));

    User reloaded = saveAndReload(user);

    assertThat(reloaded.getExternalId()).isNull();
    assertThat(reloaded.assignedRoleIds()).containsExactly(idText(role));
    assertThat(reloaded.getVersion()).isEqualTo(0L);
  }

  @Test
  @DisplayName("should round-trip a provisioned user with an external id")
  void shouldRoundTripAProvisionedUserWithAnExternalId() {
    User reloaded =
        saveAndReload(
            User.provisionUser(
                "provisioned", "provisioned@example.test", "Provisioned", new ExternalId("ext-1")));

    assertThat(reloaded.getExternalId()).isEqualTo(new ExternalId("ext-1"));
  }

  @Test
  @DisplayName("should increment the version and keep the creation time when a user changes")
  void shouldIncrementTheVersionAndKeepTheCreationTimeWhenAUserChanges() {
    User saved = saveAndReload(User.createUser("versioned", null, "{noop}x"));
    Instant createdAt = saved.getCreatedAt();

    saved.changeEmail("versioned@example.test");
    User reloaded = saveAndReload(saved);

    assertThat(reloaded.getVersion()).isEqualTo(1L);
    assertThat(reloaded.getCreatedAt()).isCloseTo(createdAt, within(CLOCK_TOLERANCE));
    assertThat(reloaded.getEmail()).isEqualTo("versioned@example.test");
  }

  @Test
  @DisplayName("should round-trip a group with members")
  void shouldRoundTripAGroupWithMembers() {
    User member = saveAndReload(User.createUser("member", null, "{noop}x"));
    Group group = Group.provisionGroup("Characterization Group", new ExternalId("grp-1"));
    group.addMember(idText(member));

    Group reloaded = saveAndReload(group);

    assertThat(reloaded.memberUserIds()).containsExactly(idText(member));
  }

  @Test
  @DisplayName("should round-trip a TOTP credential")
  void shouldRoundTripATotpCredential() {
    saveAndReload(
        TotpCredential.createTotpCredential(persistedUserId(), "ciphertext", "hash-1,hash-2"));
  }

  @Test
  @DisplayName("should round-trip a policy and its assignment")
  void shouldRoundTripAPolicyAndItsAssignment() {
    Policy policy =
        saveAndReload(
            Policy.createPolicy(
                "characterization-policy",
                List.of(
                    Rule.createRule(
                        Effect.PERMIT,
                        Set.of(new com.iam.common.domain.model.Permission("iam:ListUsers")),
                        Set.of(new Resource("*"))))));

    saveAndReload(
        PolicyAssignment.createPolicyAssignment(
            idText(policy), ResourceName.createUserResourceName("characterized")));
  }

  @Test
  @DisplayName("should round-trip a permission")
  void shouldRoundTripAPermission() {
    saveAndReload(
        Permission.createPermission(
            "write:characterization",
            "write:characterization",
            "iam",
            new com.iam.common.domain.model.Permission("iam:Characterize"),
            new Resource("arn:iam:::characterization/*"),
            "characterization"));
  }

  @Test
  @DisplayName("should round-trip a short-lived credential")
  void shouldRoundTripATokenExchange() {
    saveAndReload(
        TokenExchange.createTokenExchange(
            ResourceName.createRoleResourceName("characterization-role"),
            "session",
            "user:characterized",
            Instant.now().plusSeconds(900)));
  }

  @Test
  @DisplayName("should round-trip audit events with their occurrence time")
  void shouldRoundTripAuditEventsWithTheirOccurrenceTime() {
    AdminActivity activity =
        saveAndReload(
            AdminActivity.recordAdminActivity(
                new AuditActor("characterized"),
                "user:create",
                new AuditTarget("User", "user-1"),
                AuditOutcome.SUCCESS));
    DataAccessLog log =
        saveAndReload(
            DataAccessLog.recordDataAccessLog(
                new PrincipalId("user:characterized"),
                new com.iam.common.domain.model.Permission("iam:ListUsers"),
                new Resource("arn:iam::user/*"),
                Effect.DENY,
                ReasonCode.IMPLICIT_DENY));

    assertThat(activity.getOccurredAt()).isEqualTo(activity.getCreatedAt());
    assertThat(log.getOccurredAt()).isEqualTo(log.getCreatedAt());
  }

  @Test
  @DisplayName("should round-trip a provisioning target with a provisioned account")
  void shouldRoundTripAProvisioningTargetWithAProvisionedAccount() {
    ProvisioningTarget target =
        ProvisioningTarget.createProvisioningTarget(
            "characterization-target", "https://ai.example/scim/v2", "iam-scim-outbound", null);
    String userId = persistedUserId();
    target.recordProvisionedAccount(userId, "remote-1", 3);

    ProvisioningTarget reloaded = saveAndReload(target);

    assertThat(reloaded.requiresAssignment()).isFalse();
    assertThat(reloaded.findProvisionedAccount(userId)).isPresent();
  }

  @Test
  @DisplayName("should round-trip a federated identity")
  void shouldRoundTripAFederatedIdentity() {
    saveAndReload(FederatedIdentity.createFederatedIdentity(persistedUserId(), "google", "sub-1"));
  }

  /**
   * Saves through Spring Data as the repositories do, reads the row back in a fresh persistence
   * context, and asserts every mapped field survived.
   */
  @SuppressWarnings("unchecked")
  private <T> T saveAndReload(T entity) {
    Class<T> type = (Class<T>) entity.getClass();
    T saved = new SimpleJpaRepository<T, Object>(type, em.getEntityManager()).saveAndFlush(entity);
    Object id = identifierOf(saved);
    em.clear();

    T reloaded = em.find(type, id);

    assertThat(reloaded).isNotNull();
    assertThat(reloaded).usingRecursiveComparison(fieldComparison()).isEqualTo(saved);
    return reloaded;
  }

  private static RecursiveComparisonConfiguration fieldComparison() {
    return RecursiveComparisonConfiguration.builder()
        .withIgnoredFields("persisted")
        .withComparatorForType(
            (a, b) -> Duration.between(a, b).abs().compareTo(CLOCK_TOLERANCE) <= 0 ? 0 : 1,
            Instant.class)
        .build();
  }

  private String persistedUserId() {
    return idText(saveAndReload(User.createUser("owner-" + System.nanoTime(), null, "{noop}x")));
  }

  private Object identifierOf(Object entity) {
    return em.getEntityManager()
        .getEntityManagerFactory()
        .getPersistenceUnitUtil()
        .getIdentifier(entity);
  }

  private String idText(Object entity) {
    return identifierOf(entity).toString();
  }
}
