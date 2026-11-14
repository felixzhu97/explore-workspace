plugins {
    java
    checkstyle
    jacoco
}

val libs = the<VersionCatalogsExtension>().named("libs")

fun version(alias: String): String = libs.findVersion(alias).get().requiredVersion

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(version("java"))
    }
}

// Uses <root>/config/checkstyle/checkstyle.xml unless a project sets its own configFile.
checkstyle {
    toolVersion = version("checkstyle")
    isIgnoreFailures = false
    maxWarnings = 0
}

tasks.withType<Checkstyle>().configureEach {
    reports {
        xml.required = true
        html.required = true
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

jacoco {
    toolVersion = version("jacoco")
}

tasks.withType<JacocoReport>().configureEach {
    reports {
        xml.required = true
        html.required = true
    }
}
