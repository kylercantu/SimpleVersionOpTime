/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newoptime;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author JohnChown
 */
public class localFiles implements ActionListener{
    
    private static JButton button = new JButton();
    
    localFiles(){
     button = new JButton("Browse Local Files");
     button.setBounds(875, 880, 150, 50);
     button.addActionListener(this);
    }
    
   
    public void actionPerformed(ActionEvent e) {
       
        
       try {
           Runtime.getRuntime().exec("explorer.exe \"C:\\Users\\John Chown\\Documents\\NetBeansProjects\\SimpleVersionOpTime-main\\NewOpTime\"");
       } catch (IOException ex) {
           Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    }
