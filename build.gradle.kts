plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "2.3.0"
    `kotlin-dsl`
    `maven-publish`
    id("com.gradle.plugin-publish") version "2.1.1"
}

group = "dev.meluhdy"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}

gradlePlugin {
    website.set("https://github.com")

    plugins {
        create("multiModule") {
            id = "dev.meluhdy.multi-module"
            implementationClass = "dev.meluhdy.MultiModulePlugin"
            displayName = "Try Multi Module Plugin"
            description = "Attempts to find a project in a multi-module setup, otherwise falls back to external artifact"
        }
    }
}

publishing {
    publications {
        matching { it.name == "pluginMaven" }.configureEach {
            val pub = this as MavenPublication
            println("Publishing Plugin Implementation: ${pub.groupId}:${pub.artifactId}:${pub.version}")
        }
    }
}