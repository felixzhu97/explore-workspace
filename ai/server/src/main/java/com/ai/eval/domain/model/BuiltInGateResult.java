package com.ai.eval.domain.model;

import java.util.List;
import lombok.Value;

/** Pass/fail gate from Spring AI RelevancyEvaluator and FactCheckingEvaluator. */
@Value
public class BuiltInGateResult {
  boolean relevancyPassed;
  Boolean factualityPassed;
  boolean factualityEvaluated;
  double relevanceScore;
  Double factualityScore;
  List<String> feedback;
  boolean passed;

  public BuiltInGateResult(
      boolean relevancyPassed,
      Boolean factualityPassed,
      boolean factualityEvaluated,
      double relevanceScore,
      Double factualityScore,
      List<String> feedback,
      boolean passed) {
    feedback = feedback == null ? List.of() : List.copyOf(feedback);
    this.relevancyPassed = relevancyPassed;
    this.factualityPassed = factualityPassed;
    this.factualityEvaluated = factualityEvaluated;
    this.relevanceScore = relevanceScore;
    this.factualityScore = factualityScore;
    this.feedback = feedback;
    this.passed = passed;
  }
}
