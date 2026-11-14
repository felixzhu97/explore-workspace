package com.iam.common;

import com.explore.architecture.LayerRules;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Architecture rules")
class ArchitectureRulesTest {

  private static final String ROOT = "com.iam";

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
    LayerRules.featureModulesFreeOfCycles(ROOT).check(PRODUCTION_CLASSES);
  }

  @Test
  @DisplayName("should reach audit only through domain events")
  void shouldReachAuditOnlyThroughDomainEvents() {
    ArchRuleDefinition.noClasses()
        .that()
        .resideOutsideOfPackage("com.iam.audit..")
        .should()
        .dependOnClassesThat()
        .resideInAPackage("com.iam.audit..")
        .check(PRODUCTION_CLASSES);
  }
}
