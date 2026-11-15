package com.iam.policy.domain.service;

import com.iam.common.domain.model.Effect;
import com.iam.common.domain.model.ReasonCode;
import com.iam.policy.domain.model.AccessDecision;
import com.iam.policy.domain.model.AccessTuple;
import com.iam.policy.domain.model.Policy;
import com.iam.policy.domain.model.Rule;
import java.util.List;

/** Evaluates policy documents with Deny &gt; Permit &gt; implicit Deny semantics. */
public class PolicyEngine {

  /**
   * Evaluates attached policies for the given context.
   *
   * @param context evaluation input
   * @param policies policies attached to the principal
   * @return authorization decision
   */
  public AccessDecision evaluate(AccessTuple context, List<Policy> policies) {
    boolean permitMatched = false;
    for (Policy policy : policies) {
      for (Rule rule : policy.getRules()) {
        if (!rule.matchesPermissionAndResource(context.action(), context.resource())) {
          continue;
        }
        if (rule.effect() == Effect.DENY) {
          return new AccessDecision(Effect.DENY, ReasonCode.EXPLICIT_DENY);
        }
        if (rule.effect() == Effect.PERMIT) {
          permitMatched = true;
        }
      }
    }
    if (permitMatched) {
      return new AccessDecision(Effect.PERMIT, ReasonCode.EXPLICIT_PERMIT);
    }
    return new AccessDecision(Effect.DENY, ReasonCode.IMPLICIT_DENY);
  }
}
