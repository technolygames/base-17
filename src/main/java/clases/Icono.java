package clases;
//java
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

/**
 * Esta clase se encarga de cargar el ícono de las ventanas.
 * 
 * @author erick
 */
public class icono{
    protected String image;
    
    protected Image retValue;
    protected Properties p;
    
    /**
     * Obtiene el ícono que está destinado a usarse en las ventanas.
     * 
     * @return la imagen a usar.
     */
    public Image getIconImage(){
        p=new Properties();
        try{
            p.load(new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/data/config/config.properties"));
            image=p.getProperty("icono");
            
            if(!new File(image).exists()){
                image=p.getProperty("icono_respaldo");
            }
            
            retValue=Toolkit.getDefaultToolkit().getImage(image);
            retValue.flush();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+icono.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(icono.class.getName(),Level.WARNING,"getIconImage-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+icono.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(icono.class.getName(),Level.WARNING,"getIconImage-2IO",x.fillInStackTrace());
        }
        return retValue;
    }
}