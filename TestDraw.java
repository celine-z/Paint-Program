/*
 * Celine Zhang
 * June 2016
 * This is a test class 
 * for the paint program
 */

import javax.swing.JFrame;

public class TestDraw
{
  // Main method
  public static void main( String args[] ){
    DrawFrame drawFrame = new DrawFrame();
    drawFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    drawFrame.setSize( 700, 500 );
    drawFrame.setVisible( true );
  }
}