package com.pluginTests;

import com.github.hypfvieh.classloader.ComponentRegistry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestForm {
    static private TestForm instance;

    private JFrame frame;
    private JPanel titlePanel;
    private JTextField findBox;
    private JPanel findInFolderPanel;
    private JTable foundFilesList;
    private JLabel foundFilePath;
    private JTextArea foundFileBody;

    static public TestForm getInstance() {
        if (TestForm.instance == null) {
            TestForm.instance = new TestForm();
        }
        return  TestForm.instance;
    }

    private TestForm() {
        // Frame
        frame = new JFrame();
        frame.setTitle("Find In Json");
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        frame.pack();
        frame.setSize(850, 650);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                frame.setVisible(false);
            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }
}
