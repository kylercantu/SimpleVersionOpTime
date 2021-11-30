
package newoptime;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class FrontPage implements ActionListener{
    
private static JLabel UserLabel;
   private static JLabel currentLabel;
   private static JButton timeBtn;
   private static JLabel timeStats;
   private static JLabel lblTxt = new JLabel();
   private static JButton button = new JButton();
   
   private static JButton startBtn;
   private static JButton stopBtn;
   
   public int hourInt;
   public int minInt;
   
   StopWatch stopWatch = new StopWatch();
   

    
    FrontPage() throws IOException{
        
        
    JPanel panel = new JPanel();
     JFrame frame = new JFrame();
     frame.setSize(1500, 900);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setLocationRelativeTo(null);
     
     frame.add(panel);
     panel.setLayout(null);
     
     panel.setBackground(Color.darkGray);
     
     box g = new box();
     
     JButton table = new JButton("Time Stats");
     table.setBounds(1100, 500, 300, 100);
     table.setBackground(Color.black);
     table.setForeground(Color.white);
     table.setBorder(BorderFactory.createLineBorder(Color.white, 3));
     table.addActionListener((ActionListener) g);
     panel.add(table);
     
     
     //Title at top
     UserLabel = new JLabel("OpTime", SwingConstants.CENTER);
     UserLabel.setBounds(500, 15, 500, 150);
     UserLabel.setForeground(Color.WHITE);
     UserLabel.setBorder(BorderFactory.createLineBorder(Color.white, 4));
     panel.add(UserLabel);
     UserLabel.setFont(new Font("Futura", Font.BOLD, 100));
     
     //Creates the button for user to set time
     timeBtn = new JButton("Set Reminder");
     timeBtn.setBounds(1100, 250, 300, 100);
     timeBtn.setBackground(Color.black);
     timeBtn.setForeground(Color.white);
     timeBtn.setBorder(BorderFactory.createLineBorder(Color.white, 3));
     panel.add(timeBtn);
     
     startBtn = new JButton("Start Tracking");
     startBtn.setBounds(100, 250, 300, 100);
     startBtn.setBackground(Color.black);
     startBtn.setForeground(Color.white);
     startBtn.setBorder(BorderFactory.createLineBorder(Color.white, 3));
     panel.add(startBtn);
     
     stopBtn = new JButton("Stop Tracking");
     stopBtn.setBounds(100, 500, 300, 100);
     stopBtn.setBackground(Color.black);
     stopBtn.setForeground(Color.white);
     stopBtn.setBorder(BorderFactory.createLineBorder(Color.white, 3));
     panel.add(stopBtn);
     
     timeBtn.addActionListener(this);
     startBtn.addActionListener(this);
     stopBtn.addActionListener(this);
     
     localFiles c = new localFiles();
     
     button = new JButton("Browse Local Files");
     button.setBounds(610, 700, 300, 100);
     button.setBackground(Color.black);
     button.setForeground(Color.white);
     button.setBorder(BorderFactory.createLineBorder(Color.white, 3));
     button.addActionListener((ActionListener) c);
     panel.add(button);

     frame.setVisible(true);
     
        
    }//end FrontPage constructor

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == timeBtn){
            
            String hour = JOptionPane.showInputDialog("Enter the hour (00-24)");
            hourInt = Integer.parseInt(hour);
            
            String minute = JOptionPane.showInputDialog("Enter the minute (00-59)");
            minInt = Integer.parseInt(minute);
            
            
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {

            @Override
            public void run() {
                Notification n = new Notification();
                n.sendNotification(); 
            }    
        }; //End timerTask
            
        
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, hourInt);
        date.set(Calendar.MINUTE, minInt);
        

        timer.schedule(task, date.getTime());         

        }//end timeBtn if statement
        
        
        if(e.getSource() == startBtn){
            
            String programName = JOptionPane.showInputDialog("Name of Program");
            
            try {
                FileWriter fw = new FileWriter("UserData.txt", true);
                PrintWriter pw = new PrintWriter(fw);
                pw.print(programName);
                pw.print("/");
                pw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            stopWatch.start();
            
            
        }//End startBtn if statement
        
        
        if(e.getSource() == stopBtn){
            stopWatch.stop();
            
            JOptionPane.showMessageDialog(null, "Tracking Stopped");
            
            try {
                FileWriter fw = new FileWriter("UserData.txt", true);
                PrintWriter pw = new PrintWriter(fw);
                pw.print(stopWatch.hours);
                pw.print("/");
                pw.println(stopWatch.minutes);
                pw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }//End stopBtn if statement
        
    }//End actionPerformed
    
}//End FrontPage class
