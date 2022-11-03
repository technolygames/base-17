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
public class GuiMediaHandler{
    protected String clase;
    
    /**
     * Inicializa la instancia para que registre en qué clase se ustá usando las funciones de esta clase.<br>
     * Esto para ver si hay problemas en la clase que usa las funciones de esta clase.
     * 
     * @param clase que está usando las funciones.
     */
    public GuiMediaHandler(String clase){
        this.clase=clase;
    }
    
    protected String icon;
    protected String image;
    protected String methodName;
    
    protected Properties p;
    
    /**
     * Muestra en la ventana la imagen que fue seleccionada en la ventana de configuración.
     * 
     * @return la imagen que se mostrará en el formulario o ventanas.
     */
    public Image getFormImage(){
        methodName="getFormImage";
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
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,clase,methodName,"1IO");
            return null;
        }catch(IOException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,clase,methodName,"2IO");
            return null;
        }
    }
    
    public Image getFormImage(String image1){
        return Toolkit.getDefaultToolkit().getImage(image1);
    }
    
    /**
     * Carga, desde un archivo .properties, el ícono destinado a usarse en las ventanas.<br>
     * Aparece en la esquina superior izquierda de la ventana.
     * 
     * @return la imagen a usar.
     */
    public Image getIconImage(){
        methodName="getIconImage";
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
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,clase,methodName,"1IO");
            return null;
        }catch(IOException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,clase,methodName,"2IO");
            return null;
        }
    }
    
    /**
     * Obtiene el ícono destinado a usarse en las ventanas.<br>
     * Aparece en la esquina superior izquierda de la ventana.
     * Este método es usado para actualizar la imagen de las ventanas mientras el programa está en ejecución.
     * 
     * @param icon1 Icono a mostrar y actualizar
     * 
     * @return la imagen a usar.
     */
    public Image getIconImage(String icon1){
        return Toolkit.getDefaultToolkit().getImage(icon1);
    }
    
    /**
     * Clase para usar el diseño seleccionado en la ventana de configuración.
     * 
     * @param componente en el que mostrará el diseño o color de fondo del programa.
     */
    public void LookAndFeel(Component componente){
        methodName="LookAndFeel";
        try{
            p=new Properties();
            p.load(new FileInputStream("data/config/config.properties"));
            UIManager.setLookAndFeel(p.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(componente);
        }catch(ClassNotFoundException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(componente,e,clase,methodName,"CNFE");
        }catch(InstantiationException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(componente,x,clase,methodName,"IE");
        }catch(IllegalAccessException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(componente,n,clase,methodName,"IAE");
        }catch(UnsupportedLookAndFeelException y){
            new logger(Level.SEVERE).storeAndViewCaughtException(componente,y,clase,methodName,"28");
        }catch(NullPointerException k){
            new logger(Level.SEVERE).storeAndViewCaughtException(componente,k,clase,methodName,"0");
        }catch(FileNotFoundException s){
            new logger(Level.SEVERE).storeAndViewCaughtException(componente,s,clase,methodName,"1IO");
        }catch(IOException d){
            new logger(Level.SEVERE).storeAndViewCaughtException(componente,d,clase,methodName,"2IO");
        }
    }
}