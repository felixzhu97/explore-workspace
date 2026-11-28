plugins {
    id("explore.java-conventions")
    id("explore.java-format-conventions")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

group = "com.ai"
version = "0.0.1-SNAPSHOT"

dependencyManagement {
    imports {
        mavenBom("org.springframework.ai:spring-ai-bom:2.1.0")
        mavenBom("me.paulschwarz:spring-dotenv-bom:5.1.0")
    }
}

// Local / CI: full Vision + MCP. Render Docker: -PcloudMinimal (smaller fat JAR).
val cloudMinimal = project.hasProperty("cloudMinimal")

dependencies {
    implementation(project(":shared-errors"))
    implementation(project(":shared-provisioning"))
    implementation(project(":shared-identity"))
    implementation(project(":shared-kernel"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("org.latencyutils:LatencyUtils:2.0.3")
    implementation("org.springframework.ai:spring-ai-starter-model-openai")
    implementation("org.springframework.ai:spring-ai-starter-model-ollama")
    implementation("org.springframework.ai:spring-ai-client-chat")
    implementation("org.springframework.retry:spring-retry:2.0.13")
    implementation("org.springframework.ai:spring-ai-starter-model-chat-memory")
    implementation("org.springframework.ai:spring-ai-starter-model-chat-memory-repository-jdbc")
    implementation("org.springframework.ai:spring-ai-vector-store")
    implementation("org.springframework.ai:spring-ai-rag")
    implementation("org.springframework.ai:spring-ai-vector-store-advisor")
    implementation("org.springframework.ai:spring-ai-starter-model-anthropic")
    implementation("org.springaicommunity:spring-ai-agent-utils:0.14.0")
    implementation("org.springframework.ai:spring-ai-tool-search-advisor")
    implementation("com.launchdarkly:launchdarkly-java-server-sdk:7.19.0")
    implementation("com.github.ben-manes.caffeine:caffeine")
    implementation(libs.scim2.sdk.common)
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // PDF Processing
    implementation("org.apache.pdfbox:pdfbox:3.0.8")

    // dotenv support
    developmentOnly("me.paulschwarz:springboot4-dotenv")

    // Database: H2 embedded + Liquibase migrations
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-liquibase")
    runtimeOnly("com.h2database:h2")

    // Automation result emails: Markdown → HTML
    implementation("org.commonmark:commonmark:0.33.0")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testImplementation("org.springframework.boot:spring-boot-webmvc-test")
    testImplementation("org.springframework.boot:spring-boot-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.springframework.ai:spring-ai-test")
    testImplementation("org.hamcrest:hamcrest:3.0")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation(libs.archunit.junit5)
    testImplementation(testFixtures(project(":shared-architecture")))
    testImplementation(testFixtures(project(":shared-provisioning")))
}

if (!cloudMinimal) {
    dependencies {
        implementation("org.springframework.ai:spring-ai-starter-mcp-server-webmvc")
        implementation("org.springframework.ai:spring-ai-starter-mcp-client")
        implementation("com.microsoft.onnxruntime:onnxruntime:1.32.0")
        implementation("net.sourceforge.tess4j:tess4j:5.21.0")
    }
} else {
    // Compile against APIs; omit from runtime fat JAR (plus bootJar class excludes).
    dependencies {
        compileOnly("org.springframework.ai:spring-ai-starter-mcp-server-webmvc")
        compileOnly("org.springframework.ai:spring-ai-starter-mcp-client")
        compileOnly("com.microsoft.onnxruntime:onnxruntime:1.32.0")
        compileOnly("net.sourceforge.tess4j:tess4j:5.21.0")
    }
}

tasks.withType<Test> {
    systemProperty("org.gradle.internal.test.binary.results-store", "false")
    val buildDir = layout.buildDirectory
    doFirst {
        buildDir.dir("jacoco").get().asFile.mkdirs()
        buildDir.dir("test-results/test/binary").get().asFile.mkdirs()
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform {
        excludeTags("integration")
    }
}

tasks.register<Test>("integrationTest") {
    description = "Runs integration-tagged tests (@Tag(\"integration\"))."
    group = "verification"
    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath
    useJUnitPlatform {
        includeTags("integration")
    }
    shouldRunAfter(tasks.named("test"))
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    classDirectories.setFrom(files(classDirectories.files.map { f ->
        fileTree(f) {
            exclude("com/ai/config/**", "com/ai/domain/repository/**")
        }
    }))
}

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.jacocoTestReport)

    violationRules {
        rule {
            element = "PACKAGE"
            excludes = listOf(
                "com.ai.config.*",
                "com.ai.domain.repository.*",
                "com.ai.*"
            )
        }
        rule {
            element = "BUNDLE"
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = BigDecimal("0.60")
            }
            limit {
                counter = "BRANCH"
                value = "COVEREDRATIO"
                minimum = BigDecimal("0.55")
            }
        }
    }
}

// Docker / Render deployment: ensure bootJar produces app.jar
tasks.bootJar {
    archiveFileName.set("app.jar")
    if (cloudMinimal) {
        // Drop classes that hard-reference ONNX / Tess4J / Spring AI MCP so
        // component scanning does not NoClassDefFoundError without those jars.
        exclude(
            "com/ai/vision/infra/adapter/**",
            "com/ai/vision/infra/config/TesseractConfig.class",
            "com/ai/vision/infra/config/TesseractConfig\$*.class",
            "com/ai/mcp/**",
        )
    }
}

// Disable plain jar to prevent conflicts
tasks.named<Jar>("jar") {
    enabled = false
}

// ai keeps its own rule set (abbreviations, Javadoc scope) until it aligns with the root one.
checkstyle {
    configFile = file("config/checkstyle/checkstyle.xml")
    configProperties = mapOf(
        "org.checkstyle.google.severity" to "error",
        "org.checkstyle.google.suppressionfilter.config" to
            file("config/checkstyle/suppressions.xml").absolutePath,
        "org.checkstyle.google.suppressionxpathfilter.config" to
            file("config/checkstyle/suppressions-xpath.xml").absolutePath,
    )
}
