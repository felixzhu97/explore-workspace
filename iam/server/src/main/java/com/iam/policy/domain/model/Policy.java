package com.iam.policy.domain.model;

import com.iam.common.domain.base.AbstractNamedEntity;
import com.iam.policy.domain.converter.RulesJsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * XACML-style policy document aggregate: named collection of rules.
 *
 * @see <a href="https://docs.oasis-open.org/xacml/3.0/xacml-3.0-core-spec-os-en.html">XACML
 *     3.0</a>
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Policy extends AbstractNamedEntity<PolicyId> {

  @Column(nullable = false, columnDefinition = "clob")
  @Convert(converter = RulesJsonConverter.class)
  private List<Rule> rules;

  private Policy(PolicyId id, String name, List<Rule> rules) {
    super(id, name);
    this.rules = List.copyOf(new ArrayList<>(requireRules(rules)));
  }

  /**
   * Creates a new policy document.
   *
   * @param name display name
   * @param rules policy rules
   * @return new aggregate
   */
  public static Policy createPolicy(String name, List<Rule> rules) {
    return new Policy(PolicyId.generateId(), name, rules);
  }

  private static List<Rule> requireRules(List<Rule> rules) {
    if (rules == null || rules.isEmpty()) {
      throw new IllegalArgumentException("policy must contain at least one rule");
    }
    return rules;
  }
}