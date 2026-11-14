package com.explore.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

/**
 * Layer rules every product shares: per-feature {@code controller}, {@code service}, {@code
 * domain}, {@code infra} and {@code mapper} packages, with a domain that depends on nothing
 * outward.
 */
public final class LayerRules {

  private LayerRules() {}

  /** No class lives in a {@code domain.port} package. */
  public static ArchRule noDomainPortPackages() {
    return noClasses()
        .should()
        .resideInAPackage("..domain.port..")
        .because(
            "domain ports are a retired layout. Fix: move the interface to domain.repository or"
                + " domain.service")
        .allowEmptyShould(true);
  }

  /** No class lives in an {@code adapter.in} or {@code adapter.out} package. */
  public static ArchRule noAdapterPackages() {
    return noClasses()
        .should()
        .resideInAnyPackage("..adapter.in..", "..adapter.out..")
        .because(
            "features use controller, service, domain, infra and mapper packages. Fix: move inbound"
                + " adapters to controller and outbound adapters to infra")
        .allowEmptyShould(true);
  }

  /** Domain classes under {@code rootPackage} never depend on its infra classes. */
  public static ArchRule domainIndependentOfInfra(String rootPackage) {
    return noClasses()
        .that()
        .resideInAPackage(rootPackage + "..domain..")
        .should()
        .dependOnClassesThat()
        .resideInAPackage(rootPackage + "..infra..")
        .because(
            "the domain has no outward dependencies. Fix: declare an interface in"
                + " domain.repository and implement it in infra")
        .allowEmptyShould(true);
  }

  /** Classes in {@code <root>.common} are exempt: they hold cross-feature infrastructure. */
  public static ArchRule noLegacyLayerPackages(String rootPackage) {
    return noClasses()
        .that()
        .resideInAPackage(rootPackage + "..")
        .and()
        .resideOutsideOfPackage(rootPackage + ".common..")
        .should()
        .resideInAnyPackage("..web..", "..application..", "..infrastructure..")
        .because(
            "web, application and infrastructure are the old top-level layers. Fix: use the"
                + " feature's controller, service or infra package")
        .allowEmptyShould(true);
  }

  /** No package under {@code rootPackage} has a usecase, adapter or port segment. */
  public static ArchRule noUseCaseAdapterOrPortPackages(String rootPackage) {
    return noClasses()
        .that()
        .resideInAPackage(rootPackage + "..")
        .should()
        .resideInAnyPackage("..usecase..", "..adapter..", "..port..")
        .because(
            "use case, adapter and port packages hide what the code does. Fix: use service for"
                + " orchestration and infra for outbound integrations")
        .allowEmptyShould(true);
  }

  /** Top-level classes do not end in UseCase, Facade, Impl, Adapter or Port. */
  public static ArchRule noVagueClassNameSuffixes(String rootPackage) {
    return noClasses()
        .that()
        .resideInAPackage(rootPackage + "..")
        .and()
        .areTopLevelClasses()
        .should()
        .haveSimpleNameEndingWith("UseCase")
        .orShould()
        .haveSimpleNameEndingWith("Facade")
        .orShould()
        .haveSimpleNameEndingWith("Impl")
        .orShould()
        .haveSimpleNameEndingWith("Adapter")
        .orShould()
        .haveSimpleNameEndingWith("Port")
        .because(
            "a class name says what it is. Fix: name it after its role, for example"
                + " ChatService, JpaChatRepository or ChatMapper")
        .allowEmptyShould(true);
  }

  /** Interfaces are not named with an {@code I} prefix such as {@code IChatRepository}. */
  public static ArchRule noInterfacePrefixI(String rootPackage) {
    return noClasses()
        .that()
        .resideInAPackage(rootPackage + "..")
        .and()
        .areInterfaces()
        .should()
        .haveNameMatching(".*[.$]I[A-Z][a-z]\\w*")
        .because(
            "Java interfaces carry no I prefix. Fix: drop the prefix, for example ChatRepository")
        .allowEmptyShould(true);
  }

  /** The top-level feature packages under {@code rootPackage} have no dependency cycles. */
  public static ArchRule featureModulesFreeOfCycles(String rootPackage) {
    return SlicesRuleDefinition.slices()
        .matching(rootPackage + ".(*)..")
        .should()
        .beFreeOfCycles()
        .because(
            "feature modules depend on each other in one direction. Fix: publish a domain event or"
                + " move the shared type to common")
        .allowEmptyShould(true);
  }
}
