/*
 * Celine Zhang
 * June 2016
 * This is a JPanel that 
 * controls the undo, redo,
 * and clear button
 */

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Actions extends JPanel
{
    private DrawPanel paintPanel;
    private SaveDrawing saveDrawing;
    private JButton undoButton;
    private JButton redoButton;
    private JButton clearButton;
    private JButton saveImage;
    
    // Constructor that accepts the main drawPanel as a parameter
    public Actions( DrawPanel paintPanel, SaveDrawing saveDrawing){
        
        setLayout( new FlowLayout(FlowLayout.LEFT, 20, 5) );
        
        this.paintPanel = paintPanel;
        this.saveDrawing = saveDrawing;
        
        // create the buttons
        undoButton = new JButton("Undo");
        add(undoButton);
        redoButton = new JButton("Redo");
        add(redoButton);
        clearButton = new JButton("Clear");
        add(clearButton);
        saveImage = new JButton("Save");
        add(saveImage);
        
        ButtonHandler buttonHandler = new ButtonHandler();
        undoButton.addActionListener( buttonHandler );
        redoButton.addActionListener( buttonHandler );
        clearButton.addActionListener( buttonHandler );
        saveImage.addActionListener( buttonHandler );
    }
    
    // Button event handling
    private class ButtonHandler implements ActionListener 
    {
        public void actionPerformed( ActionEvent event )
        {
            if (event.getSource() == undoButton){
                paintPanel.clearLastShape();
            }
            if (event.getSource() == redoButton){
                paintPanel.redoLastShape();
            }
            if (event.getSource() == clearButton){
                paintPanel.clearDrawing();
            }
            if (event.getSource() == saveImage){
                saveDrawing.save();
            }
        }
    }
}
