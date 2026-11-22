plugins {
    id("explore.java-conventions")
    id("explore.java-format-conventions")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

group = "com.finpulse"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":shared-errors"))
    implementation(project(":shared-kernel"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-liquibase")
    implementation("org.springframework.security:spring-security-crypto")

    runtimeOnly("com.h2database:h2")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-webmvc-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
}

tasks.named<Jar>("jar") {
    enabled = false
}

tasks.bootJar {
    archiveFileName.set("app.jar")
}


checkstyle {
    configFile = file("config/checkstyle/google_checks.xml")
    configDirectory = file("config/checkstyle")
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}