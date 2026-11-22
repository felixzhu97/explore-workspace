package com.ai.account.domain.event;

import com.ai.common.domain.model.OwnerId;

/**
 * Guest linked to account event.
 *
 * @param fromOwnerId guest owner partition of the browser
 * @param toOwnerId account owner partition that now owns the guest rows
 */
public record GuestLinkedToAccountEvent(OwnerId fromOwnerId, OwnerId toOwnerId) {}
