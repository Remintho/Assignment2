/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.*;




public class test extends JFrame {

    JFrame frame;
    JButton menuButton;
    JButton loginButton;

    public test(){
      menuButton = new JButton("Menu");
      menuButton.setIcon(new ImageIcon("./icon/menu.png"));
      menuButton.setHorizontalAlignment(SwingConstants.LEFT);
      menuButton.setSize(100, 10);
      loginButton = new JButton("Login");
      menuButton.addActionListener((ActionEvent e) -> {
      });
      

     frame = new JFrame();    
     Panel centralPanel = new Panel();
     centralPanel.setPreferredSize(new Dimension(400,300));
     frame.add(centralPanel, BorderLayout.CENTER);      
            
            
    JPanel northPanel = new JPanel();
    northPanel.add(menuButton);
     northPanel.add(loginButton);
    frame.add(northPanel, BorderLayout.NORTH);
    
     frame.setSize(400, 400);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.pack();
     frame.setVisible(true);
    }
   
    public static void main(String[] args) {
        test gui = new test();
    }

    
    
}

