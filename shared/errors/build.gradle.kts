plugins {
    id("explore.java-library-conventions")
}

dependencies {
    api("org.springframework:spring-webmvc")
    compileOnly("org.springframework.boot:spring-boot-autoconfigure")
    testImplementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-webmvc-test")
}
