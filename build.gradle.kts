plugins {
    alias(libs.plugins.nx.project.graph)
}

allprojects {
    apply(plugin = "dev.nx.gradle.project-graph")
}
