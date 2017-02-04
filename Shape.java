/*
 * Celine Zhang
 * June 2016
 * This is a JPanel that 
 * controls the shapes
 * and its properties
 */

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Shape extends JPanel
{
    private DrawPanel paintPanel;
    private JComboBox shapeJComboBox;
    private JCheckBox filled;
    private JPanel strokeWidthPanel;
    private JLabel strokeWidthLabel;
    private JFormattedTextField strokeWidth;
    private JCheckBox dash;
    private JPanel dashWidthPanel;
    private JLabel dashWidthLabel;
    private JFormattedTextField dashWidth;
    private final int shapes[] = { 1, 2, 3 };
    
    public Shape( DrawPanel paintPanel){
        
        setLayout( new FlowLayout(FlowLayout.LEFT, 20, 5) );
        
        strokeWidthPanel = new JPanel();
        strokeWidthPanel.setLayout( new FlowLayout(FlowLayout.LEFT, 2, 0));
        dashWidthPanel = new JPanel();
        dashWidthPanel.setLayout( new FlowLayout(FlowLayout.LEFT, 2, 0));
        
        this.paintPanel = paintPanel;
        String shape [] = {"Line", "Rectangle", "Oval"};
        
        // instantiate the components
        shapeJComboBox = new JComboBox( shape );
        filled = new JCheckBox( "filled" );
        strokeWidthLabel = new JLabel("Stroke Width:");
        strokeWidth = new JFormattedTextField();
        strokeWidth.setColumns(5);
        dash = new JCheckBox( "dashed line" );
        dashWidthLabel = new JLabel("Dash Width:");
        dashWidth = new JFormattedTextField();
        dashWidth.setColumns(5);
        
        setValues(0, false, 1, false, 10);
        
        add( shapeJComboBox);
        add(filled);
        strokeWidthPanel.add(strokeWidthLabel);
        strokeWidthPanel.add(strokeWidth);
        add(strokeWidthPanel);
        add(dash);
        dashWidthPanel.add(dashWidthLabel);
        add(dashWidthPanel);
        dashWidthPanel.add(dashWidth);
        
        ItemHandler itemHandler = new ItemHandler();
        shapeJComboBox.addItemListener( itemHandler );
        filled.addItemListener( itemHandler );
        dash.addItemListener( itemHandler );
        
        TextFieldHandler textFieldHandler = new TextFieldHandler();
        strokeWidth.addPropertyChangeListener( textFieldHandler );
        dashWidth.addPropertyChangeListener( textFieldHandler );
    }
    
    // Set the values for the shape type, fill, stroke widht, dash, and dash width
    public void setValues(int shapeType, Boolean fill, int strokeWidthValue, Boolean dashSelect, int dashWidthValue){
        shapeJComboBox.setSelectedIndex(shapeType);
        filled.setSelected(fill);
        strokeWidth.setValue(strokeWidthValue);
        dash.setSelected(dashSelect);
        dashWidth.setValue(dashWidthValue);
    }
    
    // Event handling for combobox and checkbox
    private class ItemHandler implements ItemListener
    {
        public void itemStateChanged( ItemEvent event )
        {
            if (event.getSource() == shapeJComboBox){
                paintPanel.setShapeType(shapes[shapeJComboBox.getSelectedIndex()]);
            } 
            if (event.getSource() == filled){
                paintPanel.setShapeFilled(filled.isSelected());
            }
            if (event.getSource() == dash){
                paintPanel.setDashSpace(dash.isSelected());
            }
        }
    }
    
    // Event handling for the textfield components
    private class TextFieldHandler implements PropertyChangeListener
    {
        public void propertyChange( PropertyChangeEvent event){
            if (event.getSource() == strokeWidth){
                paintPanel.setStrokeWidth((Integer)strokeWidth.getValue());
            }
            if (event.getSource() == dashWidth){
                paintPanel.setDashWidth((Integer)dashWidth.getValue());
            }
        }
    }
}

