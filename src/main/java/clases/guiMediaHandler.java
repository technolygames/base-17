package clases;
//java
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
//extension larga
import java.util.logging.Level;

/**
 * Clase encargada de manejar los diseños de las ventanas o interfaces gráficas de usuario.<br>
 * <ul>
 * <li>Carga y valida las imágenes seleccionadas en la ventana de configuración.</li>
 * <li>Carga el diseño, o color de fondo del programa, de las ventanas.</li>
 * <li>Carga el ícono de la ventana.</li>
 * </ul>
 * 
 * @author erick
 */
public class guiMediaHandler{
    protected String clase;
    
    /**
     * Inicializa la instancia para que registre en qué clase se ustá usando las funciones de esta clase.<br>
     * Esto para ver si hay problemas en la clase que usa las funciones de esta clase.
     * 
     * @param clase que está usando las funciones.
     */
    public guiMediaHandler(String clase){
        this.clase=clase;
    }
    
    protected String icon;
    protected String image;
    
    protected Properties p;
    
    /**
     * Muestra en la ventana la imagen que fue seleccionada en la ventana de configuración.
     * 
     * @return la imagen que se mostrará en el formulario o ventanas.
     */
    public Image getFormImage(){
        p=new Properties();
        try{
            p.load(new FileInputStream("data/config/config.properties"));
            image=p.getProperty("imagenes");
            
            if(!new File(image).exists()){
                image=p.getProperty("imagen_respaldo");
                if(!new File(image).exists()){
                    p.load(new FileInputStream("data/config/preconfig.properties"));
                    image=p.getProperty("imagenes");
                }
            }
            
            return Toolkit.getDefaultToolkit().getImage(image);
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'FormImage()'");
            new logger(Level.SEVERE).exceptionLogger(clase,"FormImage-1IO",e.fillInStackTrace());
            return null;
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'FormImage()'");
            new logger(Level.SEVERE).exceptionLogger(clase,"FormImage-2IO",x.fillInStackTrace());
            return null;
        }
    }
    
    /**
     * Obtiene el ícono que está destinado a usarse en las ventanas.<br>
     * Aparece en la esquina superior izquierda de la ventana.
     * 
     * @return la imagen a usar.
     */
    public Image getIconImage(){
        p=new Properties();
        try{
            p.load(new FileInputStream("data/config/config.properties"));
            icon=p.getProperty("icono");
            
            if(!new File(icon).exists()){
                icon=p.getProperty("icono_respaldo");
                if(!new File(icon).exists()){
                    p.load(new FileInputStream("data/config/preconfig.properties"));
                    icon=p.getProperty("icono");
                }
            }
            
            return Toolkit.getDefaultToolkit().getImage(icon);
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'getIconImage()'");
            new logger(Level.SEVERE).exceptionLogger(clase,"getIconImage-1IO",e.fillInStackTrace());
            return null;
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'getIconImage()'");
            new logger(Level.SEVERE).exceptionLogger(clase,"getIconImage-2IO",x.fillInStackTrace());
            return null;
        }
    }
    
    /**
     * Clase para usar el diseño seleccionado en la ventana de configuración.
     * 
     * @param componente en el que mostrará el diseño o color de fondo del programa.
     */
    public void LookAndFeel(Component componente){
        try{
            p=new Properties();
            p.load(new FileInputStream("data/config/config.properties"));
            UIManager.setLookAndFeel(p.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(componente);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error CNFE: "+e.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'LookAndFeel()'");
            new logger(Level.SEVERE).exceptionLogger(clase,"LookAndFeel-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error IE: "+x.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'LookAndFeel()'");
            new logger(Level.SEVERE).exceptionLogger(clase,"LookAndFeel-IE",x.fillInStackTrace());
        }catch(IllegalAccessException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IAE",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error IAE: "+n.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'LookAndFeel()'");
            new logger(Level.SEVERE).exceptionLogger(clase,"LookAndFeel-IAE",n.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 28",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 28: "+y.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'LookAndFeel()'");
            new logger(Level.SEVERE).exceptionLogger(clase,"LookAndFeel-28",y.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'LookAndFeel()'");
            new logger(Level.SEVERE).exceptionLogger(clase,"LookAndFeel-0",k.fillInStackTrace());
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+s.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'LookAndFeel()'");
            new logger(Level.SEVERE).exceptionLogger(clase,"LookAndFeel-1IO",s.fillInStackTrace());
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+d.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'LookAndFeel()'");
            new logger(Level.SEVERE).exceptionLogger(clase,"LookAndFeel-2IO",d.fillInStackTrace());
        }
    }
}