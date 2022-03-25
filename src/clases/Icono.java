package clases;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 * Esta clase se encarga de cargar el ícono de las ventanas.
 * 
 * @author erick
 */
public class Icono{
    protected Image retValue;
    protected Properties p;
    
    /**
     * Obtiene el ícono que está destinado a usarse en las ventanas.
     * 
     * @return La imagen a usar
     */
    public Image getIconImage(){
        p=new Properties();
        try{
            p.load(new FileInputStream("src/data/config/config.properties"));
            retValue=Toolkit.getDefaultToolkit().getImage(p.getProperty("icono"));
            retValue.flush();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+Icono.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(Icono.class.getName(),Level.WARNING,"getIconImage-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+Icono.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(Icono.class.getName(),Level.WARNING,"getIconImage-2IO",x.fillInStackTrace());
        }
        return retValue;
    }
}