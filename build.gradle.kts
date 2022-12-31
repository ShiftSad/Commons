import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("plugin.serialization") version "1.7.22"
    kotlin("jvm") version "1.7.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    // Minestom
    implementation("com.github.Minestom.Minestom:Minestom:91a344aa92")

    // Kotlin dependencies
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}