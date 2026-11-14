package com.ai.common;

import com.explore.architecture.LayerRules;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.freeze.FreezingArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Architecture rules")
class ArchitectureRulesTest {

  private static final String ROOT = "com.ai";

  private static final JavaClasses CLASSES = new ClassFileImporter().importPackages(ROOT);

  private static final JavaClasses PRODUCTION_CLASSES =
      new ClassFileImporter()
          .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
          .importPackages(ROOT);

  @Test
  @DisplayName("should forbid legacy domain port packages")
  void shouldForbidLegacyDomainPortPackages() {
    LayerRules.noDomainPortPackages().check(CLASSES);
  }

  @Test
  @DisplayName("should forbid hexagonal adapter in out packages")
  void shouldForbidHexagonalAdapterPackages() {
    LayerRules.noAdapterPackages().check(CLASSES);
  }

  @Test
  @DisplayName("should keep domain free of infra dependencies")
  void shouldKeepDomainFreeOfInfraDependencies() {
    LayerRules.domainIndependentOfInfra(ROOT).check(CLASSES);
  }

  @Test
  @DisplayName("should not use legacy web application or infrastructure layer package names")
  void shouldNotUseLegacyLayerPackageNames() {
    LayerRules.noLegacyLayerPackages(ROOT).check(PRODUCTION_CLASSES);
  }

  @Test
  @DisplayName("should not use use case adapter or port package names")
  void shouldNotUseUseCaseAdapterOrPortPackageNames() {
    LayerRules.noUseCaseAdapterOrPortPackages(ROOT).check(PRODUCTION_CLASSES);
  }

  @Test
  @DisplayName("should not use vague class name suffixes")
  void shouldNotUseVagueClassNameSuffixes() {
    LayerRules.noVagueClassNameSuffixes(ROOT).check(PRODUCTION_CLASSES);
  }

  @Test
  @DisplayName("should not prefix interfaces with I")
  void shouldNotPrefixInterfacesWithI() {
    LayerRules.noInterfacePrefixI(ROOT).check(PRODUCTION_CLASSES);
  }

  @Test
  @DisplayName("should keep feature modules free of dependency cycles")
  void shouldKeepFeatureModulesFreeOfDependencyCycles() {
    FreezingArchRule.freeze(LayerRules.featureModulesFreeOfCycles(ROOT)).check(PRODUCTION_CLASSES);
  }

  @Test
  @DisplayName("should keep AI features unaware of metrics when they report invocations")
  void shouldKeepAiFeaturesUnawareOfMetricsWhenTheyReportInvocations() {
    ArchRuleDefinition.noClasses()
        .that()
        .resideInAnyPackage(
            "com.ai.chat..",
            "com.ai.rag..",
            "com.ai.pipeline..",
            "com.ai.tools..",
            "com.ai.vision..")
        .should()
        .dependOnClassesThat()
        .resideInAPackage("com.ai.metrics..")
        .check(CLASSES);
  }
}
