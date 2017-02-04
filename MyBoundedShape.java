/*
 * Celine Zhang
 * June 2016
 * This class contains data attributes
 * of a bounded shape
 */

import java.awt.Color;
import java.awt.Graphics;

abstract class MyBoundedShape extends MyShape
{
  private boolean fill;
  
  // Constructor that accepts parameters for xy coordinate, color 1 and color 2, fill value, stroke width, dash width, and dash space
  public MyBoundedShape( int x1, int y1, int x2, int y2, Color myColor, Color myColor2, boolean fill, int strokeWidth, int dashWidth, int dashSpace){
    super( x1, y1, x2, y2, myColor, myColor2, strokeWidth, dashWidth, dashSpace);
    this.fill = fill; // set the fill status
  }
  
  // Constructor that accepts no parameters
  public MyBoundedShape(){
    this.fill = false; // set the fill status
  }
  
  // Accessor for upper left x coordinate
  public int getUpperLeftX(){
    return Math.min(getCordx1(), getCordx2());
  }
  
  // Accessor for upper left y coordinate
  public int getUpperLeftY(){
    return Math.min(getCordy1(), getCordy2());
  }
  
  // Accessor for width of shape
  public int getWidth(){
    return Math.abs(getCordx1()-getCordx2());
  }
  
  // Accessor for height of shape
  public int getHeight(){
    return Math.abs(getCordy1()-getCordy2());
  }
  
  // Mutator for fill value
  public void setFill(boolean fill){
    this.fill = fill;
  }
  
  // Accessor for fill value
  public boolean getFill(){
    return fill;
  }
}