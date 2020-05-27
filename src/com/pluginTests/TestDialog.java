package com.pluginTests;

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
    private JTextField findBox;
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
        mainPanel.setPreferredSize(new Dimension(850, 650));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // titlePanel
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLUE);
        titlePanel.setPreferredSize(new Dimension(1, 50));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        // findBox
        findBox = new JTextField();
        findInFolderPanel.setPreferredSize(new Dimension(1, 100));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        // findInFolderPanel
        findInFolderPanel = new JPanel();
        findInFolderPanel.setBackground(Color.RED);
        findInFolderPanel.setPreferredSize(new Dimension(1, 100));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        // foundFilesList
        foundFilesList = new JBTable();

        // foundFilePath
        foundFilePath = new JLabel();

        // foundFileBody
        foundFileBody = new JTextArea();

        // Load Panel
        mainPanel.add(titlePanel, BorderLayout.PAGE_START);
        mainPanel.add(findBox, BorderLayout.AFTER_LINE_ENDS);
        mainPanel.add(findInFolderPanel, BorderLayout.CENTER);
        //mainPanel.add(foundFilesList);
        //mainPanel.add(foundFilePath);
        //mainPanel.add(foundFileBody);

        this.getContentPane().add(mainPanel);
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
