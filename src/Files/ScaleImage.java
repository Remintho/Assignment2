/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class ScaleImage {
    
    
    //scale image to fit into the specified label
    public ImageIcon scaledImage(JLabel label, String imagePath){
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath)); 
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        
        return scaledIcon;
        
        //the end
    }
    
    
    
    //the end of the class
}
