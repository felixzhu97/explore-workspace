plugins {
    id("explore.java-conventions")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

group = "com.iam"
version = "0.0.1-SNAPSHOT"

dependencyManagement {
    imports {
        mavenBom("org.springframework.modulith:spring-modulith-bom:2.1.2")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-webauthn")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-authorization-server")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-restclient")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-liquibase")
    implementation(project(":shared-errors"))
    implementation(project(":shared-kernel"))
    implementation(project(":shared-provisioning"))
    implementation(libs.scim2.sdk.common)
    implementation("org.springframework.modulith:spring-modulith-starter-jpa")
    runtimeOnly("com.h2database:h2")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation(libs.archunit.junit5)
    testImplementation(testFixtures(project(":shared-architecture")))
    testImplementation(testFixtures(project(":shared-provisioning")))
    testImplementation("org.springframework.modulith:spring-modulith-starter-test")
}

tasks.withType<Test> {
    // Local/CI tests must not rely on committed encrypt defaults (secret scanners).
    environment("APP_TOTP_ENCRYPT_PASSWORD", "ci-test-totp-encrypt-material")
    environment("APP_TOTP_ENCRYPT_SALT", "0123456789abcdef")
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

tasks.register<Exec>("npmBuild") {
    group = "build"
    description = "Build the Angular console into src/main/resources/static"
    val console = layout.projectDirectory.dir("../console").asFile
    workingDir = console
    commandLine("pnpm", "run", "build")
    // Captures only files, so the task stays compatible with the configuration cache.
    onlyIf {
        console.resolve("package.json").exists()
            && console.resolve("src/main.ts").exists()
            && try {
                ProcessBuilder("pnpm", "--version").redirectErrorStream(true).start().waitFor() == 0
            } catch (_: Exception) {
                false
            }
    }
}

tasks.bootJar {
    archiveFileName.set("app.jar")
    dependsOn("npmBuild")
}

tasks.named<Jar>("jar") {
    enabled = false
}
