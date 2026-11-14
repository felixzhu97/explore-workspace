package com.explore.architecture;

import static org.assertj.core.api.Assertions.assertThat;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Layer rules")
class LayerRulesTest {

  private static final String SAMPLE = "com.explore.architecture.sample";

  private static final JavaClasses CLASSES = new ClassFileImporter().importPackages(SAMPLE);

  @Test
  @DisplayName("should report a domain class that depends on infra")
  void shouldReportDomainClassThatDependsOnInfra() {
    var result = LayerRules.domainIndependentOfInfra(SAMPLE).evaluate(CLASSES);

    assertThat(result.hasViolation()).isTrue();
    assertThat(result.getFailureReport().toString()).contains("Order").contains("Fix:");
  }

  @Test
  @DisplayName("should report a class name ending in Impl")
  void shouldReportClassNameEndingInImpl() {
    var result = LayerRules.noVagueClassNameSuffixes(SAMPLE).evaluate(CLASSES);

    assertThat(result.hasViolation()).isTrue();
    assertThat(result.getFailureReport().toString()).contains("OrderServiceImpl");
  }

  @Test
  @DisplayName("should pass when no package uses a retired layout")
  void shouldPassWhenNoPackageUsesRetiredLayout() {
    assertThat(LayerRules.noDomainPortPackages().evaluate(CLASSES).hasViolation()).isFalse();
    assertThat(LayerRules.noAdapterPackages().evaluate(CLASSES).hasViolation()).isFalse();
    assertThat(LayerRules.noLegacyLayerPackages(SAMPLE).evaluate(CLASSES).hasViolation()).isFalse();
    assertThat(LayerRules.featureModulesFreeOfCycles(SAMPLE).evaluate(CLASSES).hasViolation())
        .isFalse();
  }
}
