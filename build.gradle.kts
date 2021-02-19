allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}