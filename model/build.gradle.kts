plugins {
    kotlin("jvm") version "1.4.30"
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.amshove.kluent:kluent:1.65")

    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
}