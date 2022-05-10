package org.lj92458.plugins.services

import com.intellij.openapi.project.Project
import org.lj92458.plugins.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
