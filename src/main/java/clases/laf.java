package clases;
//java
import java.awt.Component;
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
 * Clase para cargar el diseño a utilizar en las clases.
 * 
 * @author erick
 */
public class laf{
    protected String clase;
    
    /**
     * Inicializa la instancia para que registré qué clase está usando la función de look and feel.
     * 
     * @param clase que está mostrando el diseño de ventana especificado.
     */
    public laf(String clase){
        this.clase=clase;
    }
    
    /**
     * Clase para usar el diseño seleccionado en la ventana de configuración.
     * 
     * @param componente en el que mostrará el diseño.
     */
    public void LookAndFeel(Component componente){
        try{
            Properties style=new Properties();
            style.load(new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(componente);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error CNFE: "+e.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'LookAndFeel()'",Level.WARNING);
            new logger().exceptionLogger(clase,Level.WARNING,"LookAndFeel-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error IE: "+x.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'LookAndFeel()'",Level.WARNING);
            new logger().exceptionLogger(clase,Level.WARNING,"LookAndFeel-IE",x.fillInStackTrace());
        }catch(IllegalAccessException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error IAE: "+n.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'LookAndFeel()'",Level.WARNING);
            new logger().exceptionLogger(clase,Level.WARNING,"LookAndFeel-IAE",n.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 28: "+y.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'LookAndFeel()'",Level.WARNING);
            new logger().exceptionLogger(clase,Level.WARNING,"LookAndFeel-28",y.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'LookAndFeel()'",Level.WARNING);
            new logger().exceptionLogger(clase,Level.WARNING,"LookAndFeel-0",k.fillInStackTrace());
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+s.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'LookAndFeel()'",Level.WARNING);
            new logger().exceptionLogger(clase,Level.WARNING,"LookAndFeel-1IO",s.fillInStackTrace());
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+d.getMessage()+".\nOcurrió en la clase '"+clase+"', en el método 'LookAndFeel()'",Level.WARNING);
            new logger().exceptionLogger(clase,Level.WARNING,"LookAndFeel-2IO",d.fillInStackTrace());
        }
    }
}
