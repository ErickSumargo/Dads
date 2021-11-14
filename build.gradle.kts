// Top-level build file where you can add configuration options common to all sub-projects/modules.

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://www.jetbrains.com/intellij-repository/releases")
            setUrl("https://jetbrains.bintray.com/intellij-third-party-dependencies")
        }
    }

    subprojects {
        configurations.all {
            resolutionStrategy.eachDependency {
                if ("org.jacoco" == requested.group) {
                    useVersion("0.8.7")
                }
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
