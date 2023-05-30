/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java library project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/8.1.1/userguide/building_java_projects.html
 */

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    id("com.diffplug.spotless") version "6.19.0"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.pytorch:torchserve-plugins-sdk:0.0.4")
    // https://mvnrepository.com/artifact/org.testng/testng
    testImplementation("org.testng:testng:7.8.0")
    testImplementation("org.mockito:mockito-core:4.+")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.named<Test>("test") {
    // Use TestNG for unit tests.
    useTestNG()
}

spotless {
    java {
        importOrder()
        removeUnusedImports()
        cleanthat()
        googleJavaFormat()
        formatAnnotations()
    }
}
