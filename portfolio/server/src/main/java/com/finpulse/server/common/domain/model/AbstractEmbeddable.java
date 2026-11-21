package com.finpulse.server.common.domain.model;

/**
 * Portfolio-local alias of {@link com.explore.kernel.AbstractEmbeddable}.
 *
 * <p>Remaining domain types still import this package; migrate call sites to {@code
 * com.explore.kernel} incrementally (see portfolio/server/README migration note).
 */
public abstract class AbstractEmbeddable extends com.explore.kernel.AbstractEmbeddable {}
