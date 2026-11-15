package com.ai.account.domain.event;

import com.ai.common.domain.model.OwnerId;

/**
 * Owner erasure requested event.
 *
 * @param ownerId owner partition whose rows must be deleted
 */
public record OwnerErasureRequestedEvent(OwnerId ownerId) {}
