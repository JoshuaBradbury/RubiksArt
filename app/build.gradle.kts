import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.30"
}

group = "uk.co.newagedev"
version = "1.0"

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":model"))

    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.amshove.kluent:kluent:1.65")

    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
}