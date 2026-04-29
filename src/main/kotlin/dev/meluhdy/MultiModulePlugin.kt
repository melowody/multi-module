package dev.meluhdy

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class MultiModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.extensions.create("multiModule", MultiModuleExtension::class.java, project)
    }
}

open class MultiModuleExtension(private val project: Project) {
    fun tryMultiModule(projectName: String, fallback: String, function: String = "compileOnly") {
        val normalizedName = projectName.removePrefix(":")
        val depProj = project.findProject(":$normalizedName")

        project.dependencies {
            if (depProj != null) {
                add(function, depProj)
            } else {
                add(function, fallback)
            }
        }
    }
}