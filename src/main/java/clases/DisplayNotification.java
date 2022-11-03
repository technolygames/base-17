package clases;
//java
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.SystemTray;
import java.awt.AWTException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
//extension larga
import java.util.logging.Level;
import java.awt.TrayIcon.MessageType;

/**
 * Clase encargada de mostrar notificaciones.<br>
 * Muestra notificaciones en sistemas que lo soporten.
 * 
 * @author erick
 */
public class DisplayNotification{
    protected Properties p;
    
    /**
     * Método encargado en mostrar la notificiación.
     * 
     * @param notification Título de la notificacion.
     * @param message que se mostrará en la notificación.
     * @param messageType de la notificación (puede ser ERROR, INFO, NONE y WARNING).
     */
    public void trayNotify(String notification,String message,MessageType messageType){
        String methodName="trayNotify";
        SystemTray st=SystemTray.getSystemTray();
        p=new Properties();
        try{
            if(SystemTray.isSupported()){
                p.load(new FileInputStream("data/config/config.properties"));
                TrayIcon ti=new TrayIcon(Toolkit.getDefaultToolkit().getImage(p.getProperty("icono")));
                st.add(ti);
                ti.setImageAutoSize(true);
                ti.displayMessage(notification,message,messageType);
                ti.setToolTip(p.getProperty("nombre"));
                
                st.remove(ti);
            }
        }catch(AWTException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,DisplayNotification.class.getName(),methodName,"24");
        }catch(UnsupportedOperationException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,DisplayNotification.class.getName(),methodName,"25");
        }catch(FileNotFoundException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,n,DisplayNotification.class.getName(),methodName,"1IO");
        }catch(IOException k){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,k,DisplayNotification.class.getName(),methodName,"2IO");
        }
    }
}