/*
 * Celine Zhang
 * June 2016
 * This class contains methods 
 * that controls the area of where 
 * the user draws the shapes
 */

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class DrawPanel extends JPanel
{  
    private DynamicStack<MyShape> shapeObjects;
    private DynamicStack<MyShape> clearedShapes;
    private int currentShapeType;
    private MyShape currentShapeObject;
    private Color currentShapeColor;
    private Color currentShapeColor2;  
    private boolean useGradient;
    private boolean currentShapeFilled;
    private int strokeWidth;
    private boolean isDash;
    private int dashWidth;
    private JLabel statusLabel;
    private boolean isCleared = false; // keep track if shape was cleared
    
    // Constructor that accepts the label as a parameter
    public DrawPanel(JLabel label){
        statusLabel = label;
        shapeObjects = new DynamicStack<MyShape>();
        clearedShapes = new DynamicStack<MyShape>();
        MouseHandler handler = new MouseHandler(); 
        addMouseListener( handler ); 
        addMouseMotionListener( handler ); 
        setValues(Color.WHITE, Color.BLACK, Color.WHITE, false, 1, false, 1, false, 10);
    }
    
    // Set the values for the background colour, colour 1 and 2, gradient checkbox, shapetype, shape fill, stroke width, and dash width
    public void setValues(Color bgColor, Color shapeColor, Color shapeColor2, Boolean gradient, int shapeType, Boolean shapeFilled, 
                          int strokeWidth, Boolean dashSpace, int dashWidth){
      clearDrawing();
      setBackground( bgColor );
      setShapeColor(shapeColor);
      setShapeColor2(shapeColor2);
      setShapeGradient(gradient);
      setShapeType(shapeType);
      setShapeFilled(shapeFilled);
      setStrokeWidth(strokeWidth);
      setDashSpace(dashSpace);
      setDashWidth(dashWidth);
    }
    
    // Mutator that sets the background colour
    public void setBackgroundColor( Color bgColor){
      setBackground( bgColor);
    }
    
    // Accessor that gets the background colour
    public Color getBackgroundColor(){
      return getBackground();
    }
    
    // Mutator that sets the current shape to be drawn
    public void setShapeType( int type){
        currentShapeType = type;
    }
    
    // Mutator that sets the current color to use
    public void setShapeColor( Color color){
        currentShapeColor = color;
    }
    
    // Accessor that gets the current color
    public Color getShapeColor(){
        return currentShapeColor;
    }
    
    // Mutator that sets the second color for gradient use
    public void setShapeColor2(Color color){
        currentShapeColor2 = color;
    }
    
    // Accessor to gt he second gradient colour
    public Color getShapeColor2(){
        return currentShapeColor2;
    }
    
    // Set if paint is using Gradient
    public void setShapeGradient( boolean useGradient){
        this.useGradient = useGradient;
    }
    
    // Accesor method that returns the second gradient color
    // or null if paint is not using gradient
    public Color getGradientColor2(){
        if(useGradient == true)
            return currentShapeColor2;
        else
            return null;
    }
    
    // Mutator that sets the dashWidth
    public void setDashWidth(int dashWidth){
        this.dashWidth = dashWidth;
    }
    
    // Accessor that gets the dash width
    public int getDashWidth(){
        return dashWidth;
    }
    
    // Mutator that sets whether the line is solid or dashed
    public void setDashSpace(boolean isDash){
        this.isDash = isDash;
    }
    
    // Accessor that gets dash space
    public int getDashSpace(){
        if(isDash == false){
            return 0;
        }
        return 20;
    }
    
    // Mutator that sets if shape is filled or not
    public void setShapeFilled( boolean isFilled){
        currentShapeFilled = isFilled;
    }
    
    // Mutator the sets the stroke width
    public void setStrokeWidth( int strokeWidth){
        this.strokeWidth = strokeWidth;
    }
    
    // Clears the last shape drawn
    public void clearLastShape(){
        if (shapeObjects.size() > 0){
            clearedShapes.push(shapeObjects.pop());
        }
        isCleared = true;
        repaint();
    }
    
    // Redraws the last shape that was cleared
    public void redoLastShape(){
        if (clearedShapes.size() > 0){
            shapeObjects.push(clearedShapes.pop());
        }
        repaint();
    }
    
    // Clears the cleared shape array
    public void clearClearedShapes(){
        clearedShapes.makeEmpty();
    }
    
    // Clears the entire drawing panel
    public void clearDrawing(){
        shapeObjects.makeEmpty();
        clearedShapes.makeEmpty();
        repaint();
    }
    
    // Draws each individual shape
    public void paintComponent( Graphics g ){
        super.paintComponent( g );
        DynamicStack<MyShape> drawShapes = new DynamicStack<MyShape>();
        MyShape shape;
        int objectsNum = shapeObjects.size();
        
        // prepare to draw shapes
        for(int times = 0; times < objectsNum; times++){
            drawShapes.push(shapeObjects.pop());
        }
        // draw shapes
        for(int times = 0; times < objectsNum; times++){
            shape = drawShapes.pop();
            shapeObjects.push( shape );
            shape.draw( g );
        }
        if (currentShapeObject != null){
            currentShapeObject.draw(g);
        }
    }
    
    // Mouse event handling
    private class MouseHandler extends MouseAdapter{
        // Sets the new shape type to be drawn
        public void mousePressed(MouseEvent event){
            switch (currentShapeType){
                case 1:
                {
                    currentShapeObject = new MyLine(event.getX(), event.getY(), 
                                                    event.getX(), event.getY(), 
                                                    currentShapeColor, getGradientColor2(),
                                                    strokeWidth, getDashWidth(), getDashSpace());
                    break;
                }
                case 2:
                {
                    currentShapeObject = new MyRectangle(event.getX(), event.getY(), 
                                                         event.getX(), event.getY(), 
                                                         currentShapeColor, getGradientColor2(),
                                                         currentShapeFilled, strokeWidth,
                                                         getDashWidth(), getDashSpace());
                    break;
                }
                case 3:
                {
                    currentShapeObject = new MyOval(event.getX(), event.getY(), 
                                                    event.getX(), event.getY(), 
                                                    currentShapeColor, getGradientColor2(),
                                                    currentShapeFilled, strokeWidth,
                                                    getDashWidth(), getDashSpace());      
                    break;
                }
            }
            // check if need to clear the cleared shape array
            // make sure will not redo cleared shape after new shape drawn
            if(isCleared = true){
                clearClearedShapes();
                isCleared = false;
            }
        }
        // Finish drawing the shape and add to the shapeObjects
        public void mouseReleased(MouseEvent event){
            try{
                currentShapeObject.setCordx2(event.getX());
                currentShapeObject.setCordy2(event.getY());
                shapeObjects.push(currentShapeObject);
                currentShapeObject = null;
                repaint();
            }
            catch(NullPointerException e){}
        }
        // Updates the label
        public void mouseMoved(MouseEvent event){
            statusLabel.setText( String.format( " ( %d , %d )", 
                                               event.getX(), event.getY() ) );
        }
        // Updates the current shape and label
        public void mouseDragged(MouseEvent event){
            try{
                currentShapeObject.setCordx2(event.getX());
                currentShapeObject.setCordy2(event.getY());
                repaint();
                statusLabel.setText( String.format( " ( %d , %d )", 
                                                   event.getX(), event.getY() ) );
            }
            catch(NullPointerException e){}
        }
    }
}