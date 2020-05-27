package com.pluginTests;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;

public class TestAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        TestDialog.getInstance().show();
    }
}
