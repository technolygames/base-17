package clases;
//java
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

/**
 * Clase encargada de cargar imágenes en las ventanas.
 * Esta imagen es cargada desde la dirección asignada en la ventana de configuración.
 * 
 * @author erick
 */
public class imageFormLoader{
    protected String clase;
    
    /**
     * Inicializa la instancia para que registre en qué clase se ustá usando.
     * 
     * @param clase que está usando las funciones.
     */
    public imageFormLoader(String clase){
        this.clase=clase;
    }
    
    protected String imagen;
    protected Properties p;
    
    /**
     * Muestra en la ventana la imagen que fue seleccionada en la ventana de configuración.
     * 
     * @param etiqueta en la que se mostrará la imagen especificada.
     */
    public void setFormImage(JLabel etiqueta){
        p=new Properties();
        try{
            p.load(new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/data/config/config.properties"));
            imagen=p.getProperty("imagenes");
            
            if(!new File(imagen).exists()){
                imagen=p.getProperty("imagen_respaldo");
            }
            
            ImageIcon im=new ImageIcon(imagen);
            Icon l=new ImageIcon(im.getImage().getScaledInstance(etiqueta.getWidth(),etiqueta.getHeight(),Image.SCALE_DEFAULT));
            etiqueta.setIcon(l);
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+s.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'setFormImage()'",Level.WARNING);
            new logger().exceptionLogger(clase,Level.WARNING,"setFormImage-1IO",s.fillInStackTrace());
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+d.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'setFormImage()'",Level.WARNING);
            new logger().exceptionLogger(clase,Level.WARNING,"setFormImage-2IO",d.fillInStackTrace());
        }
    }
}