package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.kotlin.dsl.register
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.io.File

/**
 * Created by ErickSumargo on 15/04/21.
 */

class JacocoTestReportPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        applyPlugins(project)
        register(project)
    }

    private fun applyPlugins(project: Project) {
        project.plugins.apply("jacoco")
    }

    private fun register(project: Project) {
        project.tasks.register<JacocoReport>("jacocoTestReport") {
            group = "Reporting"
            description = "Generate Jacoco coverage reports."

            dependsOn(
                "testDebugUnitTest",
                "createDebugCoverageReport"
            )

            reports {
                xml.isEnabled = true
                html.isEnabled = true

                html.setDestination(File("${project.rootProject.buildDir}/coverage-report"))
            }

            val fileFilters = listOf(
                "**/R.class",
                "**/R$*.class",
                "**/BuildConfig.*",
                "**/Manifest*.*",
                "**/*Test*.*",
                "android/**/*.*"
            )

            val execFileFilters = listOf(
                "jacoco/testDebugUnitTest.exec",
                "outputs/code_coverage/debugAndroidTest/connected/**/*.ec"
            )

            val javaSrc: MutableList<String> = mutableListOf()
            val kotlinSrc: MutableList<String> = mutableListOf()

            val javaClasses: MutableList<FileTree> = mutableListOf()
            val kotlinClasses: MutableList<FileTree> = mutableListOf()
            val execFiles: MutableList<FileTree> = mutableListOf()

            project.rootProject.subprojects.forEach { subProject ->
                javaSrc.add("${subProject.projectDir}/src/main/java")
                kotlinSrc.add("${subProject.projectDir}/src/main/kotlin")

                javaClasses.add(
                    project.fileTree("${subProject.buildDir}/intermediates/javac/debug") {
                        setExcludes(fileFilters)
                    }
                )

                kotlinClasses.add(
                    project.fileTree("${subProject.buildDir}/tmp/kotlin-classes/debug") {
                        setExcludes(fileFilters)
                    }
                )

                execFiles.add(
                    project.fileTree("${subProject.buildDir}") {
                        setIncludes(execFileFilters)
                    }
                )
            }

            sourceDirectories.setFrom(
                project.files(javaSrc, kotlinSrc)
            )

            classDirectories.setFrom(
                project.files(javaClasses, kotlinClasses)
            )

            executionData.setFrom(
                project.files(execFiles)
            )
        }
    }
}
