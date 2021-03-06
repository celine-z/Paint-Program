/*
 * Celine Zhang
 * June 2016
 * Oval class that contains data attributes
 * for the oval, and inherites from
 * myBoundedShape
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.BasicStroke;

public class MyOval extends MyBoundedShape
{
  // Constructor that accepts parameters for xy coordinate, color 1 and color 2, fill value, stroke width, dash width, and dash space
  public MyOval( int x1, int y1, int x2, int y2, Color myColor, Color myColor2, boolean fill, int strokeWidth, int dashWidth, int dashSpace){
    super( x1, y1, x2, y2, myColor, myColor2, fill, strokeWidth, dashWidth, dashSpace);
  }
  
  // Constructor that accepts no parameters
  public MyOval(){
  }
  
  // Draws the oval
  public void draw( Graphics g ){
    Graphics2D g2 = (Graphics2D) g;
    // gradient
    g2.setPaint(new GradientPaint(getCordx1(), getCordy1(), getColor(), getCordx2(), getCordy2(), getColor2()));
    // dash or solid depending if dash space is 0 or 20
    g2.setStroke(new BasicStroke(getStrokeWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, new float[]{getDashWidth(), getDashSpace()}, 0));
    if (getFill()){
      g.fillOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
    }
    else{
      g.drawOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
    }
  }
}