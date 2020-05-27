package com.pluginTests;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.table.JBTable;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TestDialog extends DialogWrapper {
    static private TestDialog instance;

    static public TestDialog getInstance() {
        if (TestDialog.instance == null) {
            TestDialog.instance = new TestDialog();
        }
        return TestDialog.instance;
    }

    private Window window;
    private JPanel mainPanel;

    private JPanel titlePanel;
    private ComboBox<String> findBox;
    private JPanel findInFolderPanel;
    private JBTable foundFilesList;
    private JLabel foundFilePath;
    private JTextArea foundFileBody;

    private TestDialog() {
        super(true);

        // Window Config
        this.setTitle("Find In Json");
        this.setModal(false);

        window = this.getWindow();
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                window.setVisible(false);
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                window.setVisible(false);
            }
        });

        // mainPanel
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(610, 640));
        mainPanel.setMinimumSize(new Dimension(425, 325));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // titlePanel
        titlePanel = new JPanel();
        setComponentSize(titlePanel, 40, 40, 40);

        // findBox
        findBox = new ComboBox<String>();
        setComponentSize(findBox, 30, 30, 30);

        // findInFolderPanel
        findInFolderPanel = new JPanel();
        setComponentSize(findInFolderPanel, 0, 40, 0);

        // foundFilesList
        foundFilesList = new JBTable();
        setComponentSize(foundFilesList, 0, 200, 0);

        // foundFilePath
        foundFilePath = new JLabel();
        setComponentSize(titlePanel, 40, 40, 40);

        // foundFileBody
        foundFileBody = new JTextArea();

        // Load Panel
        mainPanel.add(titlePanel);
        mainPanel.add(findBox);
        mainPanel.add(findInFolderPanel);
        mainPanel.add(foundFilesList);
        mainPanel.add(foundFilePath);
        mainPanel.add(foundFileBody);

        this.getContentPane().add(mainPanel);
    }

    private void setComponentSize(Component component, int min, int height, int max) {
        if (min != 0) {
            component.setMinimumSize(new Dimension(1, min));
        }
        if (height != 0) {
            component.setPreferredSize(new Dimension(1, height));
        }
        if (max != 0) {
            component.setMaximumSize(new Dimension(Integer.MAX_VALUE, max));
        }
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return mainPanel;
    }

    @Override
    public void show() {
        window.setVisible(true);
    }
}
