plugins {
    kotlin("jvm") version "1.9.22"
    id("maven-publish")
}

group = "com.isekaiofficial"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.1")
    implementation("io.ktor:ktor-client-cio:3.0.0-beta-1")
    implementation("io.ktor:ktor-client-cio-jvm:3.0.0-beta-1")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}

java {
    withJavadocJar()
    withSourcesJar()
}
