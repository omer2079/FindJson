package com.findJson;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;

public class FindJsonAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        FindJsonDialog.getInstance().show();
    }
}
