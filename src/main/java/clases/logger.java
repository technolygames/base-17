package clases;
//java
import java.awt.Component;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 * Clase encargada de manejar los eventos del programa.<br>
 * Guarda los eventos del programa mientras este se está ejecutando.
 * 
 * @author erick
 */
public class logger{
    protected Level level0;
    protected FileHandler fh1;
    
    protected static Logger logger0;
    protected static FileHandler fh0;
    
    protected static final Frame frames=MediaHandler.getFrames();
    
    protected static final String CLASSNAME=logger.class.getName();
    protected String className2;
    protected String methodName0;
    
    /**
     * Inicializa la instancia para mandar el estado del mensaje log al archivo estático y/o de excepción.
     * 
     * @param level para clasificar de mejor manera al mensaje log en el/los archivos log.
     * @param className1 Nombre de la clase de la que almacenará el evento.
     */
    public logger(Level level,String className1){
        this.level0=level;
        this.className2=className1;
    }
    
    static{
        try{
            fh0=new FileHandler("data/logs/static/staticLog.log",0,1,true);
            fh0.setFormatter(new SimpleFormatter());
        }catch(SecurityException e){
            new logger(Level.SEVERE,CLASSNAME).catchException(frames,e,"Internal","SE");
        }catch(IOException x){
            new logger(Level.SEVERE,CLASSNAME).catchException(frames,x,"Internal","1IO");
        }
    }
    
    /**
     * Método encargado de guardar los datos de eventos ocurridos durante la ejecución del programa en un mismo archivo.
     * 
     * @param level del evento.
     * @param message que se almacenará en el archivo .log.
     * @param className1
     */
    public static void staticLogger(Level level,String message,String className1){
        logger0=Logger.getLogger(className1);
        try{
            logger0.addHandler(fh0);
            logger0.log(level,message);
        }catch(SecurityException e){
            new logger(Level.SEVERE,CLASSNAME).catchException(frames,e,"staticLogger","SE");
        }
    }
    
    /**
     * Método que se encarga de guardar los datos de eventos ocurridos durante la ejecución del programa en varios archivos.
     * 
     * @param methodName1 Nombre del método en el que está ocurriendo el error.
     * @param ex Excepción (o error) al que se le manejará y guardará en el archivo log.
     */
    public void exceptionLogger(String methodName1,Throwable ex){
        logger0=Logger.getLogger(className2);
        methodName0="exceptionLogger";
        try{
            fh1=new FileHandler(Dirs.exists(new File("data/logs/exceptions/"+methodName1+"-("+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+").log")));
            fh1.setFormatter(new SimpleFormatter());
            logger0.addHandler(fh1);
            logger0.log(level0,methodName1,ex);
            
            fh1.flush();
            fh1.close();
        }catch(SecurityException e){
            new logger(Level.SEVERE,CLASSNAME).catchException(frames,e,methodName0,"SE");
        }catch(IOException x){
            new logger(Level.SEVERE,CLASSNAME).catchException(frames,x,methodName0,"1IO");
        }
    }
    
    /**
     * Mostrará una subventana para mostrar un mensaje de error y almacenar en un archivo este mismo para su posterior análisis.
     * 
     * @param comp en el que se mostrará el option pane.
     * @param ex que mostrará y almacenará en un archivo logger.
     * @param methodName1 que produjo la excepción o error.
     * @param level1 el tipo de nivel del error.
     */
    public void catchException(Component comp,Throwable ex,String methodName1,String level1){
        JOptionPane.showMessageDialog(comp,"Error:\n"+ex.getMessage(),"Error "+level1,JOptionPane.ERROR_MESSAGE);
        logger.staticLogger(level0,"Error "+level1+": "+ex.getMessage()+".\nOcurrió en el método '"+methodName1+"()'",className2);
        new logger(level0,className2).exceptionLogger(methodName1+"-"+level1,ex.fillInStackTrace());
    }
    
    /**
     * @param comp ventana madre para que se centre el mensaje.
     * @param methodName1 nombre del método al que almacenará el registro
     */
    public void storeNumberInputWarning(Component comp,String methodName1){
        JOptionPane.showMessageDialog(comp,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
        logger.staticLogger(level0,"Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en el método '"+methodName1+"()'",className2);
    }
    
    /**
     * @param comp ventana madre para que se centre el mensaje.
     * @param methodName1 nombre del método al que almacenará el registro
     */
    public void storeLetterInputWarning(Component comp,String methodName1){
        JOptionPane.showMessageDialog(comp,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
        logger.staticLogger(level0,"Let 7: se ingresaron números en un campo equivocado.\nOcurrió en el método '"+methodName1+"()'",className2);
    }
    
    /**
     * @param comp ventana madre para que se centre el mensaje.
     * @param methodName1 nombre del método al que almacenará el registro
     */
    public void storeError14(Component comp,String methodName1){
        JOptionPane.showMessageDialog(comp,"Error: no existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
        logger.staticLogger(level0,"Error 14: no existen o no se ingresaron los datos a buscar y/o cambiar.\nOcurrió en el método '"+methodName1+"()'",className2);
    }
    
    /**
     * @param comp ventana madre para que se centre el mensaje.
     * @param methodName1 nombre del método al que almacenará el registro
     */
    public void storeError18(Component comp,String methodName1){
        JOptionPane.showMessageDialog(comp,"Error:\nEscribe correctamente lo que deseas realizar en esta acción","Error 18",JOptionPane.WARNING_MESSAGE);
        logger.staticLogger(level0,"Error 18: no se escribió la palabra clave para hacer la búsqueda.\nOcurrió en el método '"+methodName1+"()'",className2);
    }
    
    /**
     * @param comp ventana madre para que se centre el mensaje.
     * @param methodName1 nombre del método al que almacenará el registro
     */
    public void storeMessageConfirmation(Component comp,String methodName1){
        JOptionPane.showMessageDialog(comp,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
        logger.staticLogger(level0,"Rel 1: se guardaron correctamente los datos a la base de datos.\nOcurrió en el método '"+methodName1+"'.",className2);
    }
    
    /**
     * @param comp ventana madre para que se centre el mensaje.
     * @param methodName1 nombre del método al que almacenará el registro
     */
    public void updateMessageConfirmation(Component comp,String methodName1){
        JOptionPane.showMessageDialog(comp,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
        logger.staticLogger(level0,"Rel 2: se actualizaron correctamente los datos.\nOcurrió en el método '"+methodName1+"'.",className2);
    }
    
    /**
     * @param comp ventana madre para que se centre el mensaje.
     * @param methodName1 nombre del método al que almacenará el registro
     */
    public void deleteMessageConfirmation(Component comp,String methodName1){
        JOptionPane.showMessageDialog(comp,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
        logger.staticLogger(level0,"Rel 3: se eliminaron correctamente los registros de la base de datos.\nOcurrió en el método '"+methodName1+"'.",className2);
    }
}