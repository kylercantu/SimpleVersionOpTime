
package newoptime;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;




public class StopWatch{
    
    int elapsedTime = 0;
    int minutes =0;
    int hours = 0;
    boolean started = false;
    
    Timer timer = new Timer(1000, new ActionListener() {
        
        public void actionPerformed(ActionEvent e){
            
            elapsedTime += 1000;
            hours = (elapsedTime/3600000);
            minutes = (elapsedTime/60000) % 60;

        }
        
        
    });
    
    void start(){
        timer.start();
    }
    
    void stop(){
        timer.stop();
    }
    


    

    
}//End stopWatch
