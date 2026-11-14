plugins {
    alias(libs.plugins.nx.project.graph)
    // Loads build-logic once here so Spotless's shared build service has a single class loader.
    id("explore.java-format") apply false
}

allprojects {
    apply(plugin = "dev.nx.gradle.project-graph")
}
