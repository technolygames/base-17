package clases;
//java
import java.awt.Frame;
import java.awt.Image;
import java.awt.Dialog;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileReader;
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
public class MediaHandler{
    protected String clase;
    
    /**
     * Inicializa la instancia para que registre en qué clase se ustá usando las funciones de esta clase. 
     * Esto para ver si hay problemas en la clase que usa las funciones de esta clase.
     * 
     * @param clase que está usando las funciones.
     */
    public MediaHandler(String clase){
        this.clase=clase;
    }
    
    protected String icon;
    protected String image;
    protected String configDir="data/config/config.properties";
    protected String className=this.getClass().getName();
    protected String methodName;
    
    protected Properties p;
    
    /**
     * Muestra en la ventana la imagen que fue seleccionada en la ventana de configuración.
     * 
     * @return la imagen que se mostrará en el formulario o ventanas.
     */
    public Image getFormImage(){
        return Toolkit.getDefaultToolkit().getImage(getImagePath("imagenes","imagen_respaldo"));
    }
    
    /**
     * Carga, desde un archivo .properties, el ícono destinado a usarse en las ventanas. 
     * Aparece en la esquina superior izquierda de la ventana.
     * 
     * @return la imagen a usar.
     */
    public Image getIconImage(){
        return Toolkit.getDefaultToolkit().getImage(getImagePath("icono","icono_respaldo"));
    }
    
    /**
     * Obtiene la imagen destinada a usarse en las ventanas y/o como icono de ventana. 
     * Aparece en la esquina superior izquierda de la ventana o en un recuadro interno con borde negro en las ventanas. 
     * Este método es usado para actualizar la imagen de las ventanas mientras el programa está en ejecución.
     * 
     * @param image Icono/imagen a mostrar y actualizar.
     * 
     * @return la imagen a usar.
     */
    public Image getImage(String image){
        return Toolkit.getDefaultToolkit().getImage(image);
    }
    
    /**
     * Clase para usar el diseño seleccionado en la ventana de configuración.
     * 
     * @param componente en el que mostrará el diseño o color de fondo del programa.
     */
    public void setLookAndFeel(Component componente){
        methodName="LookAndFeel";
        try{
            p=new Properties();
            p.load(new FileInputStream(configDir));
            UIManager.setLookAndFeel(p.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(componente);
        }catch(ClassNotFoundException e){
            new logger(Level.SEVERE,className).catchException(componente,e,methodName,"CNFE");
        }catch(InstantiationException x){
            new logger(Level.SEVERE,className).catchException(componente,x,methodName,"IE");
        }catch(IllegalAccessException n){
            new logger(Level.SEVERE,className).catchException(componente,n,methodName,"IAE");
        }catch(UnsupportedLookAndFeelException y){
            new logger(Level.SEVERE,className).catchException(componente,y,methodName,"28");
        }catch(NullPointerException k){
            new logger(Level.SEVERE,className).catchException(componente,k,methodName,"0");
        }catch(FileNotFoundException s){
            new logger(Level.SEVERE,className).catchException(componente,s,methodName,"1IO");
        }catch(IOException d){
            new logger(Level.SEVERE,className).catchException(componente,d,methodName,"2IO");
        }
    }
    
    /**
     * Carga la imagen solicitada desde un archivo .properties para ser usada en el programa.
     * 
     * @param propName1 nombre de la propiedad 1.
     * @param propName2 nombre de la propiedad 2.
     * 
     * @return la dirección de la imagen.
     */
    public String getImagePath(String propName1,String propName2){
        p=new Properties();
        methodName="getImagePath";
        try{
            p.load(new FileInputStream(configDir));
            String dir=p.getProperty(propName1);
            
            if(!new File(dir).exists()){
                dir=p.getProperty(propName2);
                if(!new File(dir).exists()){
                    p.load(new FileInputStream("data/config/preconfig.properties"));
                    dir=p.getProperty(propName1);
                }
            }
            return dir;
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(getFrames(),e,methodName,"1IO");
            return null;
        }catch(IOException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(getFrames(),x,methodName,"2IO");
            return null;
        }
    }
    
    /**
     * Obtiene las ventanas existentes en el programa.
     * 
     * @return ventana que se está mostrando en pantalla.
     */
    public static Frame getFrames(){
        Frame f=null;
        for(Frame f1:Frame.getFrames()){
            f=f1;
        }
        return f;
    }
    
    /**
     * Obtiene el nombre del negocio para mostrarlo en pantalla.
     * 
     * @return el nombre del negocio asignado al programa.
     */
    public String getProgramName(){
        p=new Properties();
        try{
            p.load(new FileReader(configDir));
            return p.getProperty("nombre");
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(getFrames(),e,methodName,"1IO");
            return null;
        }catch(IOException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(getFrames(),x,methodName,"2IO");
            return null;
        }
    }
    
    public static void openPanel(Dialog parent,Component child){
        EventQueue.invokeLater(()->{
            parent.setLayout(new BorderLayout());
            parent.add(child,BorderLayout.CENTER);
            parent.pack();
        });
    }
    
    public static void openPanel(Frame parent,Component child){
        EventQueue.invokeLater(()->{
            parent.setLayout(new BorderLayout());
            parent.add(child,BorderLayout.CENTER);
            parent.pack();
        });
    }
    
    public static void load(Frame parent,String title,boolean resize){
        parent.setLocationRelativeTo(null);
        parent.setTitle(title);
        parent.setResizable(resize);
        parent.pack();
    }
    
    public static void load(Dialog parent,String title,boolean resize){
        parent.setLocationRelativeTo(null);
        parent.setTitle(title);
        parent.setResizable(resize);
        parent.pack();
    }
}