
package newoptime;

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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class FrontPage implements ActionListener{
    
private static JLabel UserLabel;
   private static JLabel currentLabel;
   private static JButton timeBtn;
   private static JLabel timeStats;
   private static JLabel lblTxt = new JLabel();
   
   
   private static JButton startBtn;
   private static JButton stopBtn;
   
   public int hourInt;
   public int minInt;
   
   StopWatch stopWatch = new StopWatch();
   

    
    FrontPage() throws IOException{
        
        
    JPanel panel = new JPanel();
     JFrame frame = new JFrame();
     frame.setSize(1920, 1080);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
     frame.add(panel);
     panel.setLayout(null);
     
     
     //Title at top
     UserLabel = new JLabel("OpTime");
     UserLabel.setBounds(750, 10, 500, 150);
     panel.add(UserLabel);
     UserLabel.setFont(new Font("Futura", Font.BOLD, 100));
     
     //Creates the button for user to set time
     timeBtn = new JButton("Set Reminder");
     timeBtn.setBounds(100, 300, 200, 100);
     panel.add(timeBtn);
     
     startBtn = new JButton("Start Tracking");
     startBtn.setBounds(100, 500, 200, 100);
     panel.add(startBtn);
     
     stopBtn = new JButton("Stop Tracking");
     stopBtn.setBounds(100, 700, 200, 100);
     panel.add(stopBtn);
     
     timeBtn.addActionListener(this);
     startBtn.addActionListener(this);
     stopBtn.addActionListener(this);

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
