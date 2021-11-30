/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newoptime;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;


public class box extends JPanel implements ActionListener{

    private static String[] columnNames = {"Program Name", "Hour(s) Used", "Minute(s) Used"};
    
    private static JButton table = new JButton();
    
    box(){
     table = new JButton("Time Stats");
     
     table.addActionListener(this);
     
}

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int rows = 0;
            Scanner file = new Scanner(new File("userdata.txt"));
            
            while (file.hasNextLine()){
                rows++;
                file.nextLine();
            }
            
            file = new Scanner(new File("userdata.txt"));
            System.out.println(rows);
            Object[][] data = new Object[rows][10];
            for (int i = 0; i < rows; i++)
            {
                String str = file.nextLine();
                String[] tokens = str.split("/");
                for (int j = 0; j < 3; j++)
                {
                    data[i][j] = tokens[j];
                    System.out.print(data[i][j]);
                    System.out.print(" ");
                }
            }
        JTable day = new JTable(data, columnNames);
        
        day.setBounds(80, 80, 350, 250);
        day.setPreferredScrollableViewportSize(new Dimension(500, 50));
        day.setFillsViewportHeight(true);
        day.setBackground(Color.black);
        day.setForeground(Color.white);
        JFrame y = new JFrame();   
        y.setSize(500, 500);
        JPanel x = new JPanel();
        x.setLayout(null);
        y.add(x);
        x.add(day);
        y.setVisible(true);
        y.setAlwaysOnTop(true);
        x.setBackground(Color.darkGray);
        y.setLocationRelativeTo(null);
        JScrollPane scrollPane = new JScrollPane(day);
        scrollPane.setBounds(80, 80, 350, 250);
        x.add(scrollPane);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
    }
}
