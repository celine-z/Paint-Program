/*
 * Celine Zhang
 * June 2016
 * This is a class that saves
 * the paint panel drawing as 
 * a png file
 */

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFileChooser;

public class SaveDrawing
{
    private JPanel paintPanel;
    private File file;
    
    public SaveDrawing(JPanel paintPanel){
        this.paintPanel = paintPanel;
    }
    public void setFile(File file){
        this.file = file;
    }
    
    // This method updates the image file, if there is no image file, it calls savAs()
    public void save(){
        // Never saved before needs to save as
        if(file == null){
            saveAs();
        }
        // Update the saved file
        else{
            BufferedImage image = new BufferedImage(paintPanel.getWidth(), paintPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
            paintPanel.paint(image.getGraphics());
            try{
                ImageIO.write(image, "PNG", file);
            }
            catch(IOException e){}
        }
    }

    // This method saves paint panel drawing as a new image file
    public void saveAs(){
        BufferedImage image = new BufferedImage(paintPanel.getWidth(), paintPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        paintPanel.paint(image.getGraphics());
        JFileChooser fileChooser = new JFileChooser();
        // Make sure ok button pressed before creating a file
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = new File(fileChooser.getSelectedFile()+".png");
            try{
                ImageIO.write(image, "PNG", file);
            }
            catch(IOException e){}
        }
    }
}