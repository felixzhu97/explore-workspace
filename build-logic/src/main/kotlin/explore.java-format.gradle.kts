plugins {
    id("com.diffplug.spotless")
}

val libs = the<VersionCatalogsExtension>().named("libs")

spotless {
    java {
        target("src/*/java/**/*.java")
        // Javadoc stays as written so class comments can keep their multi-line form.
        googleJavaFormat(libs.findVersion("google-java-format").get().requiredVersion)
            .formatJavadoc(false)
        removeUnusedImports()
        trimTrailingWhitespace()
        endWithNewline()
    }
}
