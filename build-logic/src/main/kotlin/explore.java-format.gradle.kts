plugins {
    id("com.diffplug.spotless")
}

val libs = the<VersionCatalogsExtension>().named("libs")

spotless {
    java {
        target("src/*/java/**/*.java")
        googleJavaFormat(libs.findVersion("google-java-format").get().requiredVersion)
        removeUnusedImports()
        trimTrailingWhitespace()
        endWithNewline()
    }
}
