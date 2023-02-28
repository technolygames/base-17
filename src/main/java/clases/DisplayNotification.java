package clases;
//java
import java.awt.TrayIcon;
import java.awt.SystemTray;
import java.awt.AWTException;
//extension larga
import java.util.logging.Level;
import java.awt.TrayIcon.MessageType;

/**
 * Clase encargada de mostrar notificaciones. 
 * Muestra notificaciones en sistemas que lo soporten.
 * 
 * @author erick
 */
public class DisplayNotification{
    protected DisplayNotification(){}
    
    protected static final String CLASSNAME=DisplayNotification.class.getName();
    
    /**
     * Método encargado en mostrar una notificiación usando es sistema de notificaciones que usa el sistema operativo.
     * 
     * @param notification Título de la notificacion.
     * @param message que se mostrará en la notificación.
     * @param messageType de la notificación (puede ser ERROR, INFO, NONE y WARNING).
     */
    public static void trayNotify(String notification,String message,MessageType messageType){
        String methodName="trayNotify";
        SystemTray st=SystemTray.getSystemTray();
        MediaHandler mh=new MediaHandler(CLASSNAME);
        try{
            TrayIcon ti=new TrayIcon(mh.getIconImage());
            if(SystemTray.isSupported()){
                st.add(ti);
                ti.setImageAutoSize(true);
                ti.displayMessage(notification,message,messageType);
                ti.setToolTip(mh.getProgramName());
            }
            st.remove(ti);
        }catch(AWTException e){
            new logger(Level.SEVERE,CLASSNAME).catchException(null,e,methodName,"24");
        }catch(UnsupportedOperationException x){
            new logger(Level.SEVERE,CLASSNAME).catchException(null,x,methodName,"25");
        }
    }
}