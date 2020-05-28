package com.findJson;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.SearchTextField;
import com.intellij.ui.table.JBTable;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class FindJsonDialog extends DialogWrapper {
    static private FindJsonDialog instance;

    static public FindJsonDialog getInstance() {
        if (FindJsonDialog.instance == null) {
            FindJsonDialog.instance = new FindJsonDialog();
        }
        return FindJsonDialog.instance;
    }

    private Window window;
    private JPanel mainPanel;

    private JPanel titlePanel;
    private SearchTextField findBox;
    private JPanel findInFolderPanel;
    private JBTable foundFilesList;
    private JLabel foundFilePath;
    private JTextArea foundFileBody;

    private FindJsonDialog() {
        super(true);

        // Window Config
        this.setTitle("Find In Json");
        this.setModal(false);

        window = this.getWindow();
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //window.setVisible(false);
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                //window.setVisible(false);
            }
        });

        // mainPanel
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(610, 660));
        mainPanel.setMinimumSize(new Dimension(425, 325));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // titlePanel
        titlePanel = new JPanel();
        setComponentSize(titlePanel, 40, 40, 40);

        // TODO findBox inner panel
        // findBox
        findBox = new SearchTextField();
        setComponentSize(findBox, 30, 30, 30);

        // findInFolderPanel
        findInFolderPanel = new JPanel();
        setComponentSize(findInFolderPanel, 40, 40, 40);

        // foundFilesList
        foundFilesList = new JBTable();
        setComponentSize(foundFilesList, 170, 170, 0);
        // line height 20px

        // foundFilePath
        foundFilePath = new JLabel();
        setComponentSize(titlePanel, 30, 30, 30);

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
