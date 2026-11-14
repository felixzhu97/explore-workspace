plugins {
    id("explore.java-library-conventions")
}

dependencies {
    api(libs.scim2.sdk.common)
    api("org.springframework:spring-webmvc")
    compileOnly("org.springframework.boot:spring-boot-autoconfigure")
    testImplementation("org.springframework.boot:spring-boot-starter-web")
}
