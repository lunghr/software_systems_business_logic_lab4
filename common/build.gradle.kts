plugins {
    kotlin("jvm")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-context:6.1.14")
    implementation("org.springframework.security:spring-security-web:6.3.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.github.cdimascio:dotenv-java:3.0.0")
    implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")
    implementation("io.jsonwebtoken:jjwt:0.12.6")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}