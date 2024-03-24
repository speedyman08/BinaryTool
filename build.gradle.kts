plugins {
    kotlin("jvm") version "1.9.23"
    id("io.ktor.plugin") version "2.3.9"
    application
}
application {
    mainClass.set("com.speedyman77.MainKt")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.23")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.9.23")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}

ktor {
    fatJar {
        archiveFileName.set("BinaryTool.jar")
    }
}