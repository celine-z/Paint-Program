/*
 * Celine Zhang
 * June 2016
 * This class provides methods
 * for the GUIs that allows 
 * the user to control different
 * drawing aspects
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import java.awt.Dialog.ModalityType;
import java.util.Properties;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;

public class DrawFrame extends JFrame
{
    private JFrame mainWindow;
    private JLabel label = new JLabel(" ( 0 , 0 )");
    private DrawPanel paintPanel = new DrawPanel(label);
    private SaveDrawing saveDrawing;
    private PreferenceDialog prefDialog;
    private JPanel nestedJPanel;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem newMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem aboutMenuItem;
    private JMenuItem prefsMenuItem;
    private JMenuItem resetMenuItem;
    private JMenuItem exitMenuItem;
    private JMenu editMenu;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;
    private JMenuItem clearMenuItem;
    private JTabbedPane tabbedPane;
    private Actions action; // tab
    private Colour color; // tab
    private Shape shape; // tab
    private final Color colors[] = { Color.BLACK, Color.WHITE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
        Color.BLUE, Color.CYAN, Color.PINK, Color.MAGENTA, Color.LIGHT_GRAY, Color.GRAY, Color.DARK_GRAY};
    
    public DrawFrame(){
        super("Paint Panel");
        
        mainWindow = this; // this component
        saveDrawing = new SaveDrawing(paintPanel);
        
        // preference window
        prefDialog = new PreferenceDialog();        
        prefDialog.setModalityType(ModalityType.APPLICATION_MODAL);
        prefDialog.setSize( 300, 375 );
        prefDialog.setDefaultCloseOperation( JDialog.HIDE_ON_CLOSE );

        nestedJPanel = new JPanel();
        nestedJPanel.setLayout( new BorderLayout());
        
        // Create menu bar, menu, and menu items
        menuBar = new JMenuBar();
        
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        newMenuItem = new JMenuItem("New", new ImageIcon("images/newicon.png"));
        fileMenu.add(newMenuItem);
        saveMenuItem = new JMenuItem("Save", new ImageIcon("images/saveicon.png"));
        fileMenu.add(saveMenuItem);
        saveAsMenuItem = new JMenuItem("Save as...", new ImageIcon("images/saveasicon.png"));
        fileMenu.add(saveAsMenuItem);
        fileMenu.addSeparator();
        aboutMenuItem = new JMenuItem("About", new ImageIcon("images/abouticon.png"));
        fileMenu.add(aboutMenuItem);
        fileMenu.addSeparator();
        prefsMenuItem = new JMenuItem("Prefs", new ImageIcon("images/prefsicon.png"));
        fileMenu.add(prefsMenuItem);
        fileMenu.addSeparator();
        resetMenuItem = new JMenuItem("Reset", new ImageIcon("images/reseticon.png"));
        fileMenu.add(resetMenuItem);
        fileMenu.addSeparator();
        exitMenuItem = new JMenuItem("Exit", new ImageIcon("images/exiticon.png"));
        fileMenu.add(exitMenuItem);
        
        editMenu = new JMenu("Edit");
        menuBar.add(editMenu);
        undoMenuItem = new JMenuItem("Undo", new ImageIcon("images/undoicon.png"));
        editMenu.add(undoMenuItem);
        redoMenuItem = new JMenuItem("Redo", new ImageIcon("images/redoicon.png"));
        editMenu.add(redoMenuItem);
        clearMenuItem = new JMenuItem("Clear", new ImageIcon("images/clearicon.png"));
        editMenu.add(clearMenuItem);
        
        // Create the tabbedPane and the tabs
        tabbedPane = new JTabbedPane();
        action = new Actions(paintPanel, saveDrawing);
        color = new Colour(paintPanel);
        shape = new Shape(paintPanel);
        tabbedPane.addTab("Action", action);
        tabbedPane.addTab("Colour", color);
        tabbedPane.addTab("Shape", shape);
        
        // Assign the user preferences
        assignPrefs();
        
        nestedJPanel.add(tabbedPane, BorderLayout.NORTH);
        nestedJPanel.add(paintPanel, BorderLayout.CENTER);
        add(menuBar, BorderLayout.NORTH);
        add(nestedJPanel, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
        
        MenuHandler menuHandler = new MenuHandler();
        newMenuItem.addActionListener( menuHandler );
        saveMenuItem.addActionListener( menuHandler );
        saveAsMenuItem.addActionListener( menuHandler );
        aboutMenuItem.addActionListener( menuHandler );
        prefsMenuItem.addActionListener( menuHandler );
        resetMenuItem.addActionListener( menuHandler );
        exitMenuItem.addActionListener( menuHandler );
        undoMenuItem.addActionListener( menuHandler );
        redoMenuItem.addActionListener( menuHandler );
        clearMenuItem.addActionListener( menuHandler );
    }
    
    // This class assigns the preferences made previously by reading the file. 
    // If no previous preferences were made (file not found), nothing happens
    public void assignPrefs(){
        try{
            File configFile = new File("config.properties");
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);
            // set colour component values
            color.setValues(colors[Integer.valueOf(props.getProperty("bgColor"))],colors[Integer.valueOf(props.getProperty("colorJComboBox1"))],
                            colors[Integer.valueOf(props.getProperty("colorJComboBox2"))],Boolean.valueOf(props.getProperty("gradient")));
            // set shape component values
            shape.setValues(Integer.valueOf(props.getProperty("shapeJComboBox")),Boolean.valueOf(props.getProperty("filled")),
                            Integer.valueOf(props.getProperty("strokeWidth")),Boolean.valueOf(props.getProperty("dash")), 
                            Integer.valueOf(props.getProperty("dashWidth")));
            // set the preference component values
            prefDialog.setValues(Integer.valueOf(props.getProperty("bgColor")),Integer.valueOf(props.getProperty("colorJComboBox1")),
                                 Integer.valueOf(props.getProperty("colorJComboBox2")),Boolean.valueOf(props.getProperty("gradient")),
                                 Integer.valueOf(props.getProperty("shapeJComboBox")),Boolean.valueOf(props.getProperty("filled")),
                                 Integer.valueOf(props.getProperty("strokeWidth")),Boolean.valueOf(props.getProperty("dash")), 
                                 Integer.valueOf(props.getProperty("dashWidth")));
            reader.close();
        }
        catch(IOException e){}
    }
    
    // Event handling for the menu
    private class MenuHandler implements ActionListener 
    {
        public void actionPerformed( ActionEvent event )
        {
            // New painting area: all components reset, drawing area is cleared, and not a saved file
            if (event.getSource() == newMenuItem){
                // Double check they want a new canvas, if not do nothing
                if (JOptionPane.showOptionDialog(null, "All unsaved work will be lost. Do you want to continue?","New", JOptionPane.YES_NO_OPTION, 
                                                 JOptionPane.WARNING_MESSAGE,null, new String[]{"Yes", "No"},"No") == 0){
                    paintPanel.setValues(Color.WHITE, Color.BLACK, Color.WHITE, false, 1, false, 1, false, 10);
                    color.setValues(Color.WHITE, Color.BLACK, Color.WHITE, false);
                    shape.setValues(0, false, 1, false, 10);
                    assignPrefs(); // if there are prefs, the preference will be present
                    saveDrawing.setFile(null);
                }
            }
            if (event.getSource() == saveMenuItem){
                saveDrawing.save();
            }
            if (event.getSource() == saveAsMenuItem){
                saveDrawing.saveAs();
            }
            if (event.getSource() == undoMenuItem){
                paintPanel.clearLastShape();
            }
            if (event.getSource() == redoMenuItem){
                paintPanel.redoLastShape();
            }
            if (event.getSource() == clearMenuItem){
                paintPanel.clearDrawing();
            }
            // Information
            if (event.getSource() == aboutMenuItem){
                String about = "Paint Program\nCeline Zhang\n321775454\nJune 2016";
                JOptionPane.showMessageDialog(null, about, "About", JOptionPane.PLAIN_MESSAGE);
            }
            // Display the preference window
            if (event.getSource() == prefsMenuItem){
                prefDialog.setLocationRelativeTo(mainWindow);
                prefDialog.setVisible( true );
            }
            // Reset all components and all preferences. Drawing area cleared
            if (event.getSource() == resetMenuItem){
                // Double check they want to reset, if so reset, if not do nothing
                if (JOptionPane.showOptionDialog(null, "Are you sure you want to reset?","Reset", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                                                 null, new String[]{"Yes", "No"},"No") == 0){
                    paintPanel.setValues(Color.WHITE, Color.BLACK, Color.WHITE, false, 1, false, 1, false, 10);
                    color.setValues(Color.WHITE, Color.BLACK, Color.WHITE, false);
                    shape.setValues(0, false, 1, false, 10);
                    prefDialog.setValues(1, 0, 1, false, 0, false, 1, false, 10);
                    prefDialog.setPrefs(); // set the new prefs
                }
            }
            // Close the window
            if (event.getSource() == exitMenuItem){
                // Double check they want to close, if so close, if not do nothing
                if (JOptionPane.showOptionDialog(null, "Are you sure you want to exit?","Exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                                                 null, new String[]{"Yes", "No"},"No") == 0){
                    System.exit(0);
                }
            }
        }
    }
}