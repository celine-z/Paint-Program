/*
 * Celine Zhang
 * June 2016
 * This class contains the data attributes
 * of a shape
 */

import java.awt.Color;
import java.awt.Graphics;

abstract class MyShape
{
  private int x1;
  private int x2;
  private int y1;
  private int y2;
  private Color myColor;
  private Color myColor2;
  private int strokeWidth;
  private int dashWidth;
  private int dashSpace;
  
  // Constructor that accepts parameters for xy coordinates, color 1 and color 2, stroke width, dash width, and dash space
  public MyShape( int x1, int y1, int x2, int y2, Color myColor, Color myColor2, int strokeWidth, int dashWidth, int dashSpace){
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
    this.myColor = myColor;
    setColor2(myColor2);
    setStrokeWidth(strokeWidth);
    setDashWidth(dashWidth);
    setDashSpace(dashSpace);
  }
  
  // Constructor that accepts no parameters
  public MyShape(){
    this( 0, 0, 0, 0, Color.BLACK, Color.WHITE, 10, 20, 0); 
  }
  
  // Mutator method for x1 coordinate
  public void setCordx1( int x1 ){
    this.x1 = x1;
  }
  
  // Accessor method for x1 coordinate
  public int getCordx1(){
    return x1;
  }
  
  // Mutator method for y1 coordinate
  public void setCordy1( int y1 ){
    this.y1 = y1;
  }
  
  // Accessor method for y1 coordinate
  public int getCordy1(){
    return y1;
  }
  
  // Mutatator method for x2 coordinate
  public void setCordx2( int x2 ){
    this.x2 = x2;
  }
  
  // Accessor method for x2 coordinate
  public int getCordx2(){
    return x2;
  }
  
  // Mutator method for y2 coordinate
  public void setCordy2( int y2 ){
    this.y2 = y2;
  }
  
  // Accessor method for y2 coordinate
  public int getCordy2(){
    return y2;
  }
  
  // Mutator method for color
  public void setColor( Color myColor ){
    this.myColor = myColor;
  }
  
  // Accessor method for color
  public Color getColor(){
    return myColor;
  }
  
  public void setColor2( Color myColor2 ){
    if (myColor2 == null){
      this.myColor2 = myColor;
    }
    else{
      this.myColor2 = myColor2;
    }
  }
  
  // Accessor method for color
  public Color getColor2(){
    return myColor2;
  }
  
  // Accesor method for stroke width
  public void setStrokeWidth(int strokeWidth){
    if(strokeWidth <= 0){
      this.strokeWidth = 1;
    }
    else{
      this.strokeWidth = strokeWidth;
    }
  }
  
  // Mutator method for stroke width
  public int getStrokeWidth(){
    return strokeWidth;
  }
  
  // Mutator method for dash width
  public void setDashWidth(int dashWidth){
    if(dashWidth <= 0){
      this.dashWidth = 10;
    }
    else{
      this.dashWidth = dashWidth;
    }
  }
  
  // Accessor method for dash width
  public int getDashWidth(){
    return dashWidth;
  }
  
  // Mutator method for dash space
  public void setDashSpace(int dashSpace){
    this.dashSpace = dashSpace;
  }
  
  // Accessor method for dash space
  public int getDashSpace(){
    return dashSpace;
  }
  
  public abstract void draw( Graphics g);
}