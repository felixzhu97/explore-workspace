plugins {
    id("explore.java-library-conventions")
}

dependencies {
    api("org.springframework.security:spring-security-web")
    api("org.springframework.security:spring-security-oauth2-resource-server")
    implementation("org.springframework.security:spring-security-oauth2-jose")
    testImplementation("jakarta.servlet:jakarta.servlet-api")
}
