
package newoptime;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kyler
 */
public class Notification {
    
    public void sendNotification(){
        
        try {
        SystemTray systemTray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("letterO.png");
        
        TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System traay icon demo");
    
            systemTray.add(trayIcon);

        
        trayIcon.displayMessage("OpTime Notification", "Time Reached, Please exit program", MessageType.INFO);
        
        }catch(Exception e){
            System.err.print(e);
        }
        
        
        
        
    }//End sendNotification
    
}//End notification class