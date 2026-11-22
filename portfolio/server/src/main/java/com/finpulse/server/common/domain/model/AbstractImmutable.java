package com.finpulse.server.common.domain.model;

/**
 * Portfolio-local alias of {@link com.explore.kernel.AbstractImmutableEntity}.
 *
 * <p>Delegates to shared-kernel so optimistic-lock and Persistable behaviour stays consistent
 * across Explore services.
 */
public abstract class AbstractImmutable<IdT extends AbstractEmbeddable>
    extends com.explore.kernel.AbstractImmutableEntity<IdT> {

  protected AbstractImmutable() {
    super();
  }

  protected AbstractImmutable(IdT id) {
    super(id);
  }
}
