package com.iam.policy.domain.model;

import com.iam.common.domain.base.AbstractNamedEntity;
import com.iam.policy.domain.converter.PolicyStatementsJsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Policy document aggregate: named collection of statements. */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class AllowPolicy extends AbstractNamedEntity<AllowPolicyId> {

  @Column(nullable = false, columnDefinition = "clob")
  @Convert(converter = PolicyStatementsJsonConverter.class)
  private List<PolicyStatement> statements;

  private AllowPolicy(
      AllowPolicyId id,
      String name,
      List<PolicyStatement> statements) {
    super(id, name);
    this.statements = List.copyOf(new ArrayList<>(requireStatements(statements)));
  }

  /**
   * Creates a new policy document.
   *
   * @param name display name
   * @param statements policy statements
   * @return new aggregate
   */
  public static AllowPolicy createAllowPolicy(String name, List<PolicyStatement> statements) {
    return new AllowPolicy(AllowPolicyId.generateId(), name, statements);
  }

  private static List<PolicyStatement> requireStatements(List<PolicyStatement> statements) {
    if (statements == null || statements.isEmpty()) {
      throw new IllegalArgumentException("policy must contain at least one statement");
    }
    return statements;
  }
}
