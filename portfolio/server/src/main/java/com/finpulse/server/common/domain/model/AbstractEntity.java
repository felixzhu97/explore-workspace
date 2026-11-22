package com.finpulse.server.common.domain.model;

/**
 * Portfolio-local alias of {@link com.explore.kernel.AbstractEntity}.
 */
public abstract class AbstractEntity<IdT extends AbstractEmbeddable>
    extends com.explore.kernel.AbstractEntity<IdT> {

  protected AbstractEntity() {
    super();
  }

  protected AbstractEntity(IdT id) {
    super(id);
  }
}
