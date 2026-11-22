package com.iam.policy.domain.event;

/**
 * Policy attached event.
 *
 * @param policyAssignmentId policy binding id
 */
public record PolicyAttachedEvent(String policyAssignmentId) {}
