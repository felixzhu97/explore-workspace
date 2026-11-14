plugins {
    `kotlin-dsl`
    alias(libs.plugins.nx.project.graph)
}

dependencies {
    implementation(libs.spotless.gradle.plugin)
}
