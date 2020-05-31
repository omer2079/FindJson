package com.findJson;

import com.intellij.json.JsonFileType;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.SearchTextField;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.table.JBTable;
import org.jetbrains.annotations.Nullable;
import com.findJson.utils.Module;
import com.findJson.utils.IDE;
import com.findJson.utils.Test;


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
    private JCheckBox includeUnloadBox;
    private SearchTextField findBox;
    private JPanel findInFolderPanel;
    private JButton findInProjectButton;
    private JButton findInModuleButton;
    private JButton findInFolderButton;
    private JComboBox<Module> findInModuleBox;
    private JComboBox<String> findInFolderBox;
    private JButton findInFolderSelectButton;
    private JBTable foundFilesList;
    private JPanel foundFilePathPanel;
    private JLabel foundFilePath;
    private JPanel foundFileBodyPanel;
    private EditorTextField foundFileBody;

    private FindJsonDialog() {
        super(true);

        // Window Config
        this.setTitle("Find In Json");
        this.setModal(false);

        window = this.getWindow();
        window.setPreferredSize(new Dimension(620, 660));
        window.addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                //window.setVisible(false);
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                //window.setVisible(false);
            }

            @Override
            public void windowOpened(WindowEvent e) {
                // TODO update findInProject and findInModule
            }
        });

        // mainPanel
        mainPanel = new JPanel();
        mainPanel.setMinimumSize(new Dimension(545, 400));
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
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));

        // matchCaseBox
        matchCaseBox = new JBCheckBox("Match Case", true);
        matchCaseBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,20));

        // fromHeadBox
        fromHeadBox = new JBCheckBox("From Head", true);
        fromHeadBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,10));

        // includeUnloadBox
        includeUnloadBox = new JBCheckBox("Include Unload");
        includeUnloadBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,10));

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(matchCaseBox);
        titlePanel.add(fromHeadBox);
        titlePanel.add(includeUnloadBox);

        // findBox
        findBox = new SearchTextField();
        setComponentSize(findBox, 30, 30, 30);

        // findInFolderPanel
        findInFolderPanel = new JPanel();
        setComponentSize(findInFolderPanel, 40, 40, 40);
        findInFolderPanel.setLayout(new BoxLayout(findInFolderPanel, BoxLayout.X_AXIS));

        // findInProjectButton
        findInProjectButton = new JButton();
        findInProjectButton.setSize(40, 20);
        findInProjectButton.setText("Project");
        findInProjectButton.setFont(findInProjectButton.getFont().deriveFont(Font.BOLD));

        // findInModuleButton
        findInModuleButton = new JButton();
        findInModuleButton.setSize(40, 20);
        findInModuleButton.setText("Module");

        // findInFolderButton
        findInFolderButton = new JButton();
        findInFolderButton.setSize(40, 20);
        findInFolderButton.setText("Folder");

        // findInModuleBox
        findInModuleBox = new ComboBox();
        findInModuleBox.addItem(Test.getModule());
        findInModuleBox.setPreferredSize(new Dimension(300, 30));
        findInModuleBox.setMaximumSize(new Dimension(300, 30));
        findInModuleBox.setVisible(false);

        // findInFolderBox
        findInFolderBox = new ComboBox();
        findInFolderBox.setMinimumSize(new Dimension(1, 30));
        findInFolderBox.setPreferredSize(new Dimension(1, 30));
        findInFolderBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        findInFolderBox.setEditable(true);
        findInFolderBox.setVisible(false);

        // findInFolderSelectButton
        findInFolderSelectButton = new JButton();
        findInFolderSelectButton.setText("Select");
        findInFolderSelectButton.setVisible(false);

        findInFolderPanel.add(findInProjectButton);
        findInFolderPanel.add(findInModuleButton);
        findInFolderPanel.add(findInFolderButton);
        findInFolderPanel.add(findInModuleBox);
        findInFolderPanel.add(findInFolderBox);
        findInFolderPanel.add(findInFolderSelectButton);

        // foundFilesList
        foundFilesList = new JBTable();
        setComponentSize(foundFilesList, 170, 170, 0);
        // line height 20px

        // foundFilePathPanel
        foundFilePathPanel = new JPanel();
        setComponentSize(foundFilePathPanel, 30, 30, 30);
        foundFilePathPanel.setLayout(new BoxLayout(foundFilePathPanel, BoxLayout.X_AXIS));

        // foundFilePath
        foundFilePath = new JLabel();
        foundFilePath.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        foundFilePath.setText(Test.getModule().getFolder());

        foundFilePathPanel.add(foundFilePath);

        // foundFileBodyPanel
        foundFileBodyPanel = new JPanel();
        setComponentSize(foundFileBodyPanel, 40, 80, 0);
        foundFileBodyPanel.setLayout(new BoxLayout(foundFileBodyPanel, BoxLayout.X_AXIS));

        // foundFileBody
        foundFileBody = new EditorTextField(EditorFactory.getInstance().createDocument(""),
                IDE.getProject(), JsonFileType.INSTANCE);
        foundFileBody.setOneLineMode(false);

        foundFileBodyPanel.add(foundFileBody);

        mainPanel.add(topLinePanel);
        mainPanel.add(titlePanel);
        mainPanel.add(findBox);
        mainPanel.add(findInFolderPanel);
        mainPanel.add(foundFilesList);
        mainPanel.add(foundFilePathPanel);
        mainPanel.add(foundFileBodyPanel);


        this.getContentPanel().add(mainPanel);
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
