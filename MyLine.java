/*
 * Celine Zhang
 * June 2016
 * Line class that contains data attributes
 * for the line, and inherits from MyShape
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.BasicStroke;

public class MyLine extends MyShape
{  
  // Constructor that accepts parameters for xy cordinate, color 1 and color 2, stroke width, dash width, and dash space
  public MyLine( int x1, int y1, int x2, int y2, Color myColor, Color myColor2, int strokeWidth, int dashWidth, int dashSpace){
    super( x1, y1, x2, y2, myColor, myColor2, strokeWidth, dashWidth, dashSpace);
  }
  
  // Constructor tha accepts no parameters
  public MyLine(){
  }
  
  // Draws the line
  public void draw( Graphics g ){
    Graphics2D g2 = (Graphics2D) g;
    // gradient
    g2.setPaint(new GradientPaint(getCordx1(), getCordy1(), getColor(), getCordx2(), getCordy2(), getColor2())); 
    // dash or solid depending if dash space is 0 or 20
    g2.setStroke(new BasicStroke(getStrokeWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, new float[]{getDashWidth(), getDashSpace()}, 0));
    g.drawLine( getCordx1(), getCordy1(), getCordx2(), getCordy2() );
  }
}