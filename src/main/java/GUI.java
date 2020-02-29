import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static javax.swing.JOptionPane.YES_OPTION;

public class GUI implements ActionListener {

    private static JFrame frame;
    private static JMenuBar mainMenu;
    private static JMenu menu;
    private static JTextArea ta;
    private static JMenuItem open, newFile, save, saveAs, exit;
    private FileReader in = null;
    private BufferedReader br = null;
    private FileWriter out = null;
    private BufferedWriter bw = null;
    private String name = "";

    public GUI() {

        frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        mainMenu = new JMenuBar();
        menu = new JMenu("File");
        mainMenu.add(menu);
        open = new JMenuItem("Open");
        open.addActionListener(this);
        newFile = new JMenuItem("New");
        newFile.addActionListener(this);
        save = new JMenuItem("Save");
        save.addActionListener(this);
        saveAs = new JMenuItem("Save As");
        saveAs.addActionListener(this);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        menu.add(open);
        menu.add(newFile);
        menu.add(save);
        menu.add(saveAs);
        menu.add(exit);

        ta = new JTextArea();
        ta.setFont(new Font("Times New Roman", Font.PLAIN, 16));


        frame.getContentPane().add(BorderLayout.NORTH, mainMenu);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);

    } //Constructor

    public static void main(String[] args) { new GUI(); } //main

    private void setCurrentFile(String name) {
        this.name = name;
    } //setCurrentFile

    private String getCurrentFile() {
        return name;
    } //getCurrentFile

    private void loadFile(String fileName) {
        try {
            in = new FileReader(fileName);
            br = new BufferedReader(in);
            ta.read(br, null);
            br.close();
            ta.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        } //catch
    } //loadFile

    private void saveFile(String fileName) {
        try {
            out = new FileWriter(fileName);
            bw = new BufferedWriter(out);
            ta.write(bw);
            bw.close();
            ta.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        } //catch
    } //saveFile

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == open) {
            String openName = JOptionPane.showInputDialog(frame, "File Name: ", "Open File", JOptionPane.PLAIN_MESSAGE);
            loadFile(openName);
            setCurrentFile(openName);
        } //if
        else if (e.getSource() == newFile) {
            ta.setText("");
            setCurrentFile("n");
        } //else-if
        else if (e.getSource() == save) {
            String s = getCurrentFile();
            if (s.equalsIgnoreCase("n")) {
                JOptionPane.showMessageDialog(frame,"This is a new file. You need to use 'Save As'","Error", JOptionPane.ERROR_MESSAGE);
            } //if
            else {
                saveFile(s);
            } //else
        } //else-if
        else if (e.getSource() == saveAs) {
            String saveAsName = JOptionPane.showInputDialog(frame, "File Name: ", "Save As", JOptionPane.PLAIN_MESSAGE);
            saveFile(saveAsName);
            setCurrentFile(saveAsName);
        } //else-if
        else if (e.getSource() == exit) {
            int exitQA = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (exitQA == YES_OPTION) {
                System.exit(0);
            } //if
        } //else-if
        else {
            JOptionPane.showMessageDialog(frame,"This is not a valid option! Try something else!","Error", JOptionPane.ERROR_MESSAGE);
        } //else
    }

}
