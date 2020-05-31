package com.findJson;

import com.findJson.utils.IDE;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class FindJsonAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        IDE.setProject(event.getProject());
        FindJsonDialog.getInstance().show();
    }
}
