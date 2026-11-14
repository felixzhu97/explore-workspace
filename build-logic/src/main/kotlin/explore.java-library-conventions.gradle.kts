plugins {
    id("explore.java-conventions")
    id("explore.java-format")
    `java-library`
    `java-test-fixtures`
}

val libs = the<VersionCatalogsExtension>().named("libs")

group = "com.explore"

dependencies {
    val springBootDependencies = platform(libs.findLibrary("spring-boot-dependencies").get())
    implementation(springBootDependencies)
    testFixturesImplementation(springBootDependencies)
    testImplementation(springBootDependencies)
    compileOnly(springBootDependencies)
    annotationProcessor(springBootDependencies)

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
