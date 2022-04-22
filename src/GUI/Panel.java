/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Graphics;
import javax.swing.*;
/**
 *
 * @author User
 */
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

public class Panel extends JPanel {

    public Image image;

    public Panel() {
        this.image = new ImageIcon("./resources/T06_bg.jpg").getImage();
    }

    /**
     * Draw the background of this panel.
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, null);
    }
}
