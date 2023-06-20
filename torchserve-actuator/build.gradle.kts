@file:Suppress("UnstableApiUsage")

/*
* This file was generated by the Gradle 'init' task.
*
* This generated file contains a sample Java library project to get you started.
* For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
* User Manual available at https://docs.gradle.org/8.1.1/userguide/building_java_projects.html
*/

version = "0.0.1"

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    id("com.diffplug.spotless") version "6.19.0"
    id("com.github.spotbugs") version "5.0.14"
    checkstyle
    pmd
    jacoco
    `jvm-test-suite`
}

tasks.jar {
    manifest {
        attributes(mapOf("Implementation-Title" to project.name,
                "Implementation-Vendor" to project.group,
                "Implementation-Version" to project.version))
    }
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    // TorchServe SDK is not yet available in Maven Central.
    @Suppress("DEPRECATION")
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

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useTestNG()
            targets {
                all {
                    testTask.configure {
                        finalizedBy(tasks.jacocoTestReport)
                    }
                }
            }
        }

        register<JvmTestSuite>("integrationTest") {
            useTestNG()
            dependencies {
                compileOnly("org.slf4j:slf4j-api:1.7.36")
                implementation("ch.qos.logback:logback-classic:1.3.5")
                implementation("io.rest-assured:rest-assured:5.3.1")
                implementation("org.testcontainers:testcontainers:1.18.3")
            }
            testType.set(TestSuiteType.INTEGRATION_TEST)
            targets {
                all {
                    testTask.configure {
                        environment("ACTUATOR_JAR", tasks.jar.get().archiveFile.get().asFile.absolutePath)
                        dependsOn(tasks.jar)
                    }
                }
            }
        }
    }
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

checkstyle {
    toolVersion = "10.12.0"
}

pmd {
    isConsoleOutput = true
    toolVersion = "6.55.0"
    ruleSetFiles = files("${rootProject.projectDir}/config/pmd.xml")
}