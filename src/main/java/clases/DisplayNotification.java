package clases;
//java
import java.awt.TrayIcon;
import java.awt.SystemTray;
import java.awt.AWTException;
import java.io.IOException;
import java.io.FileNotFoundException;
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
    /**
     * Método encargado en mostrar una notificiación usando es sistema de notificaciones que usa el sistema operativo.
     * 
     * @param notification Título de la notificacion.
     * @param message que se mostrará en la notificación.
     * @param messageType de la notificación (puede ser ERROR, INFO, NONE y WARNING).
     */
    public void trayNotify(String notification,String message,MessageType messageType){
        String methodName="trayNotify";
        SystemTray st=SystemTray.getSystemTray();
        MediaHandler mh=new MediaHandler(DisplayNotification.class.getName());
        try{
            TrayIcon ti=new TrayIcon(mh.getIconImage());
            while(SystemTray.isSupported()){
                st.add(ti);
                ti.setImageAutoSize(true);
                ti.displayMessage(notification,message,messageType);
                ti.setToolTip(mh.getProgramName());
                break;
            }
            st.remove(ti);
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