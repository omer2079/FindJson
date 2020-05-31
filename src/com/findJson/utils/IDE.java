package com.findJson.utils;

import com.intellij.openapi.project.Project;

public class IDE {

    static private Project project;

    static public void setProject(Project project) {
        IDE.project = project;
    }

    static public Project getProject() {
        return project;
    }
}
