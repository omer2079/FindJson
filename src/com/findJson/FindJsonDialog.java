package com.findJson;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.SearchTextField;
import com.intellij.ui.components.JBCheckBox;
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
    private JLabel titleLabel;
    private JCheckBox matchCaseBox;
    private JCheckBox fromHeadBox;
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
                window.setVisible(false);
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                window.setVisible(false);
            }
        });

        // mainPanel
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(610, 660));
        mainPanel.setMinimumSize(new Dimension(425, 325));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));
        JPanel topLinePanel = new JPanel();
        setComponentSize(topLinePanel, 20, 20, 20);

        // titlePanel
        titlePanel = new JPanel();
        setComponentSize(titlePanel, 40, 40, 40);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));

        // titleLabel
        titleLabel = new JLabel();
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0,0));
        titleLabel.setText(this.getTitle());

        // matchCaseBox
        matchCaseBox = new JBCheckBox("Match Case", true);
        matchCaseBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,20));

        // fromHeadBox
        fromHeadBox = new JBCheckBox("From Head", true);
        fromHeadBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,10));

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(matchCaseBox);
        titlePanel.add(fromHeadBox);

        // findBox
        findBox = new SearchTextField();
        setComponentSize(findBox, 30, 30, 30);

        // findInFolderPanel
        findInFolderPanel = new JPanel();
        setComponentSize(findInFolderPanel, 40, 40, 40);
        findInFolderPanel.setLayout(new BoxLayout(findInFolderPanel, BoxLayout.X_AXIS));

        //

        // foundFilesList
        foundFilesList = new JBTable();
        setComponentSize(foundFilesList, 170, 170, 0);
        // line height 20px

        // foundFilePath
        foundFilePath = new JLabel();
        setComponentSize(titlePanel, 30, 30, 30);

        // foundFileBody
        foundFileBody = new JTextArea();

        mainPanel.add(topLinePanel);
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
