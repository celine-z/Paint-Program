/*
 * Celine Zhang
 * June 2016
 * This is a JDialog that
 * allows the user to set 
 * their preferences
 */

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Properties;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class PreferenceDialog extends JDialog
{
    private JComboBox bgColor;
    private JComboBox colorJComboBox1;
    private JComboBox colorJComboBox2;
    private JCheckBox gradient;
    private JComboBox shapeJComboBox;
    private JCheckBox filled;
    private JLabel strokeWidthLabel;
    private JFormattedTextField strokeWidth;
    private JCheckBox dash;
    private JLabel dashWidthLabel;
    private JFormattedTextField dashWidth;
    private JButton okButton;
    private JButton cancelButton;
    private final int shapes[] = { 1, 2, 3 };
    
    public PreferenceDialog(){
        
        String shape [] = {"Line", "Rectangle", "Oval"};
        String colorNames[] = { "Black", "White", "Red", "Orange", "Yellow" , "Green", 
            "Blue", "Cyan", "Pink", "Magenta", "Light Gray", "Gray", "Dark Gray"};
        
        // Left side
        JPanel defaultLabel = new JPanel();
        defaultLabel.setLayout( new GridLayout(9, 1, 5, 5) );
        defaultLabel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 10));
        
        // Right side
        JPanel defaultValue = new JPanel();
        defaultValue.setLayout( new GridLayout(9, 1, 5, 5) );
        defaultValue.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 40));
        
        // Create background, Colour 1 and 2 labels and components
        defaultLabel.add(new JLabel("Background:"));
        bgColor = new JComboBox(colorNames);
        bgColor.setSelectedIndex(1); // white background
        defaultValue.add(bgColor);
        defaultLabel.add(new JLabel("Colour 1:"));
        colorJComboBox1 = new JComboBox(colorNames);
        defaultValue.add(colorJComboBox1);
        defaultLabel.add(new JLabel("Colour 2:"));
        colorJComboBox2 = new JComboBox(colorNames);
        colorJComboBox2.setSelectedIndex(1); // white
        defaultValue.add(colorJComboBox2);
        
        // gradient
        defaultLabel.add(new JLabel("Gradient:"));
        gradient = new JCheckBox();
        defaultValue.add(gradient);
        
        // comboboxes
        defaultLabel.add(new JLabel("Shape:"));
        shapeJComboBox = new JComboBox( shape );
        defaultValue.add(shapeJComboBox);
        
        // checkbox
        defaultLabel.add(new JLabel("Filled:"));
        filled = new JCheckBox();
        defaultValue.add(filled);
        
        // stroke width label and int only textfield
        defaultLabel.add(new JLabel("Stroke Width:"));
        strokeWidth = new JFormattedTextField();
        strokeWidth.setColumns(5);
        strokeWidth.setValue(1);
        defaultValue.add(strokeWidth);
        
        // dash width label and checkbox
        defaultLabel.add(new JLabel("Dashed Line:"));
        dash = new JCheckBox();
        defaultValue.add(dash);
        
        // dash width label and int only textfield
        defaultLabel.add(new JLabel("Dash Width:"));
        dashWidth = new JFormattedTextField();
        dashWidth.setColumns(5);
        dashWidth.setValue(10);
        defaultValue.add(dashWidth);
        
        // ok and cancel button
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout( new GridLayout(1, 2, 20, 20) );
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        okButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        actionPanel.add(okButton);
        actionPanel.add(cancelButton);
        
        add( new JLabel("Preferences", SwingConstants.CENTER), BorderLayout.NORTH);
        add(defaultLabel, BorderLayout.WEST);
        add(defaultValue, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);
        
        ButtonHandler buttonHandler = new ButtonHandler();
        okButton.addActionListener( buttonHandler );
        cancelButton.addActionListener( buttonHandler );
    }
    
    // Set the values for the components in this class
    public void setValues(int bg, int color1, int color2, Boolean isGradient, int shapeIndex, Boolean isFilled, 
                          int strokeLength, Boolean isDash, int dashLength){
        bgColor.setSelectedIndex(bg);
        colorJComboBox1.setSelectedIndex(color1);
        colorJComboBox2.setSelectedIndex(color2);
        gradient.setSelected(isGradient);
        shapeJComboBox.setSelectedIndex(shapeIndex);
        filled.setSelected(isFilled);
        strokeWidth.setValue(strokeLength);
        dash.setSelected(isDash);
        dashWidth.setValue(dashLength);
    }
    
    // Event handling for buttons
    private class ButtonHandler implements ActionListener 
    {
        public void actionPerformed( ActionEvent event )
        {
            if (event.getSource() == okButton){
                setPrefs();
                setVisible(false);
            }
            if (event.getSource() == cancelButton){
                setVisible(false);
            }
        }
    }
    
    // Writes the preferences into a configuration file
    public void setPrefs(){
        try{
            File configFile = new File("config.properties");
            FileWriter writer = new FileWriter(configFile);
            Properties props = new Properties();
            props.setProperty("bgColor", String.valueOf(bgColor.getSelectedIndex()));
            props.setProperty("colorJComboBox1", String.valueOf(colorJComboBox1.getSelectedIndex()));
            props.setProperty("colorJComboBox2", String.valueOf(colorJComboBox2.getSelectedIndex()));
            props.setProperty("gradient", String.valueOf(gradient.isSelected()));
            props.setProperty("shapeJComboBox", String.valueOf(shapeJComboBox.getSelectedIndex()));
            props.setProperty("filled", String.valueOf(filled.isSelected()));
            props.setProperty("strokeWidth", String.valueOf(strokeWidth.getValue()));
            props.setProperty("dash", String.valueOf(dash.isSelected()));
            props.setProperty("dashWidth", String.valueOf(dashWidth.getValue()));
            props.store(writer, null);
            writer.close();
        }
        catch(IOException e){}
    }
}