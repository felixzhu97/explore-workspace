package com.iam.policy.domain.model;

import com.explore.kernel.AbstractImmutableEntity;
import com.iam.common.domain.model.ResourceName;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Links a Policy Document to a principal or resource ARN. */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class PolicyAssignment extends AbstractImmutableEntity<PolicyAssignmentId> {

  @NotBlank
  @Size(max = 36)
  @Column(nullable = false, length = 36)
  private String policyId;

  @Embedded
  @AttributeOverride(
      name = "value",
      column = @Column(name = "principal_arn", nullable = false, length = 512))
  @Valid
  private ResourceName principalArn;

  private PolicyAssignment(PolicyAssignmentId id, String policyId, ResourceName principalArn) {
    super(id);
    this.policyId = policyId;
    this.principalArn = principalArn;
  }

  /**
   * Attaches a policy to a principal ARN.
   *
   * @param policyId policy document id
   * @param principalArn target principal
   * @return new attachment
   */
  public static PolicyAssignment createPolicyAssignment(String policyId, ResourceName principalArn) {
    return new PolicyAssignment(PolicyAssignmentId.generateId(), policyId, principalArn);
  }
}
