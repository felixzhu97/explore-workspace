plugins {
    id("explore.java-library-conventions")
}

dependencies {
    api("jakarta.persistence:jakarta.persistence-api")
    api("org.springframework.data:spring-data-commons")
    implementation("org.hibernate.orm:hibernate-core")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}
