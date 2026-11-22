pluginManagement {
    includeBuild("build-logic")
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "explore-workspace"

include(":ai-server")
project(":ai-server").projectDir = file("ai/server")

include(":iam-server")
project(":iam-server").projectDir = file("iam/server")

include(":chat-server")
project(":chat-server").projectDir = file("chat/server")

include(":portfolio-server")
project(":portfolio-server").projectDir = file("portfolio/server")

listOf("architecture", "errors", "identity", "kernel", "provisioning").forEach { library ->
    include(":shared-$library")
    project(":shared-$library").projectDir = file("shared/$library")
}
