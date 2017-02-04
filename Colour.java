/*
 * Celine Zhang
 * June 2016
 * This is a JPanel that 
 * controls the background colour,
 * the two gradient colours, and
 * a checkbox for if gradient is
 * used
 */

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Colour extends JPanel
{
  private DrawPanel paintPanel;
  private JButton bgColor;
  private JButton colorButton1;
  private JButton colorButton2;
  private JCheckBox gradient;
  private JColorChooser colorPanel;
  
  // Constructor that accepts ONLY the main JPanel as a parameter
  public Colour(DrawPanel paintPanel){
      this(paintPanel, Color.WHITE, Color.BLACK, Color.WHITE, false);
  }
  
  // Constructor that accepts the main JPanel, background colour, the two gradient colours, and true/false for gradient use
  public Colour(DrawPanel paintPanel, Color backgroundColor, Color shapeColor, Color shapeColor2, Boolean gradientSelect){
    
    setLayout( new FlowLayout(FlowLayout.LEFT, 20, 5) );
    
    this.paintPanel = paintPanel;

    // create the buttons and checkbox
    bgColor = new JButton("Background");
    colorButton1 = new JButton("Colour 1");
    colorButton2 = new JButton("Colour 2");
    gradient = new JCheckBox( "gradient" );
    
    setValues(backgroundColor, shapeColor, shapeColor2, gradientSelect);
    
    add(bgColor);
    add(colorButton1);
    add(colorButton2);
    add(gradient);
    
    ButtonHandler buttonHandler = new ButtonHandler();
    bgColor.addActionListener( buttonHandler );
    colorButton1.addActionListener( buttonHandler );
    colorButton2.addActionListener( buttonHandler );
    
    ItemHandler itemHandler = new ItemHandler();
    gradient.addItemListener( itemHandler );
  }
  
   // Set the values and appearance for the buttons and checkbox
   public void setValues(Color backgroundColor, Color shapeColor, Color shapeColor2, Boolean gradientSelect){
    bgColor.setForeground(backgroundColor );
    paintPanel.setBackgroundColor(backgroundColor);
    colorButton1.setForeground(shapeColor);
    paintPanel.setShapeColor(shapeColor);
    colorButton2.setForeground(shapeColor2);
    paintPanel.setShapeColor2(shapeColor2);
    gradient.setSelected(gradientSelect);
   }
  
  // Event handling for buttons
  private class ButtonHandler implements ActionListener 
  {
    public void actionPerformed( ActionEvent event )
    {
      if (event.getSource() == bgColor){
        Color selectedBgColor = JColorChooser.showDialog(null, "Background Colour", paintPanel.getBackgroundColor());
        // make sure user did not close the window
        if(selectedBgColor != null){
          paintPanel.setBackgroundColor(selectedBgColor);
          bgColor.setForeground(selectedBgColor);
        }
      }
      if (event.getSource() == colorButton1){
        Color selectedColor1 = JColorChooser.showDialog(null, "Colour 1", paintPanel.getShapeColor());
        // make sure user did not close the window
        if(selectedColor1 != null){
          paintPanel.setShapeColor(selectedColor1);
          colorButton1.setForeground(selectedColor1);
        }
      }
      if (event.getSource() == colorButton2){
        Color selectedColor2 = JColorChooser.showDialog(null, "Colour 2", paintPanel.getShapeColor2());
        // make sure user did not close the window
        if(selectedColor2 != null){
          paintPanel.setShapeColor2(selectedColor2);
          colorButton2.setForeground(selectedColor2);
        }
      }
    }
  }    
  
  // Event handling for checkbox
  private class ItemHandler implements ItemListener
  {
    public void itemStateChanged( ItemEvent event )
    {     
      if (event.getSource() == gradient){
        paintPanel.setShapeGradient(gradient.isSelected());
      }
    }
  }
}
