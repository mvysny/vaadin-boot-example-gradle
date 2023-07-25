import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    java
    application
    id("com.vaadin") version "24.1.4"
}

defaultTasks("clean", "build")

repositories {
    mavenCentral()
}

dependencies {
    // Vaadin
    implementation("com.vaadin:vaadin-core:24.1.4") {
        afterEvaluate {
            if (vaadin.productionMode) {
                exclude(module = "vaadin-dev")
            }
        }
    }

    // Vaadin-Boot
    implementation("com.github.mvysny.vaadin-boot:vaadin-boot:11.3")

    implementation("org.jetbrains:annotations:23.1.0")

    // logging
    // currently we are logging through the SLF4J API to SLF4J-Simple. See src/main/resources/simplelogger.properties file for the logger configuration
    implementation("org.slf4j:slf4j-simple:2.0.7")

    // Fast Vaadin unit-testing with Karibu-Testing: https://github.com/mvysny/karibu-testing
    testImplementation("com.github.mvysny.kaributesting:karibu-testing-v24:2.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.3")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
    }
}

application {
    mainClass.set("com.vaadin.starter.skeleton.Main")
}
