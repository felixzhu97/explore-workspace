package com.ai.common.service.llm;

/**
 * Provider/model availability checks used by shared LLM infrastructure without depending on the
 * chat feature module.
 */
public interface TextProviderGate {

  /** Reports whether the provider is configured; a blank provider means the default and passes. */
  boolean isProviderAvailable(String provider);

  /**
   * Tells whether a model may be requested explicitly. A blank model means the default and passes.
   */
  boolean isModelAllowed(String provider, String model);
}
