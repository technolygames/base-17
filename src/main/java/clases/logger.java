package clases;
//java
import java.awt.Component;
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
    protected Level level;
    
    protected String methodName;
    
    /**
     * Inicializa la instancia para mandar el estado del mensaje log al archivo estático y/o de excepción.
     * 
     * @param nivel para clasificar de mejor manera al mensaje log en el/los archivos log.
     */
    public logger(Level nivel){
        this.level=nivel;
    }
    
    protected static FileHandler fh;
    protected FileHandler fh2;
    
    static{
        try{
            fh=new FileHandler("data/logs/static/staticLog.log",0,1,true);
        }catch(SecurityException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,logger.class.getName(),"Internal","SE");
        }catch(IOException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,logger.class.getName(),"Internal","1IO");
        }
    }
    
    /**
     * Método encargado de guardar los datos de eventos ocurridos durante la ejecución del programa en un mismo archivo.
     * 
     * @param message Mensaje que se almacenará en el archivo .log.
     */
    public void staticLogger(String message){
        methodName="staticLogger";
        Logger logger=Logger.getLogger("staticLogger");
        try{
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.log(level,message);
        }catch(SecurityException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,logger.class.getName(),methodName,"SE");
        }
    }
    
    /**
     * Método que se encarga de guardar los datos de eventos ocurridos durante la ejecución del programa en varios archivos.
     * 
     * @param className2 Nombre de la clase en la que sucede el evento.
     * @param methodName2 Nombre del método en el que está ocurriendo el error.
     * @param exception Excepción (o error) al que se le manejará y guardará en el archivo log.
     */
    public void exceptionLogger(String className2,String methodName2,Throwable exception){
        methodName="exceptionLogger";
        Logger logger=Logger.getLogger("exceptionLogger");
        try{
            fh2=new FileHandler("data/logs/exceptions/"+className2+"."+methodName2+"-("+(int)(Math.random()*10000)+","+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+").log");
            fh2.setFormatter(new SimpleFormatter());
            logger.addHandler(fh2);
            logger.log(level,methodName2,exception);
            
            fh2.flush();
            fh2.close();
        }catch(SecurityException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,logger.class.getName(),methodName,"SE");
        }catch(IOException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,logger.class.getName(),methodName,"1IO");
        }
    }
    
    /**
     * Mostrará una subventana para mostrar un mensaje de error y almacenar en un archivo este mismo para su posterior análisis.
     * 
     * @param component en el que se mostrará el option pane.
     * @param exception que mostrará y almacenará en un archivo logger.
     * @param className2 procedente del error.
     * @param methodName2 que produjo la excepción o error.
     * @param level2 el tipo de nivel del error.
     */
    public void storeAndViewCaughtException(Component component,Throwable exception,String className2,String methodName2,String level2){
        JOptionPane.showMessageDialog(component,"Error:\n"+exception.getMessage(),"Error "+level2,JOptionPane.ERROR_MESSAGE);
        new logger(level).staticLogger("Error "+level2+": "+exception.getMessage()+".\nOcurrió en la clase '"+className2+"', en el método '"+methodName2+"()'");
        new logger(level).exceptionLogger(className2,methodName2+"-"+level2,exception.fillInStackTrace());
    }
    
    public void storeAndViewNumberInputWarning(Component component,String className2,String methodName2){
        JOptionPane.showMessageDialog(component,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
        new logger(level).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+className2+"', en el método '"+methodName2+"()'");
    }
    
    public void storeAndViewLetterInputWarning(Component component,String className2,String methodName2){
        JOptionPane.showMessageDialog(component,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
        new logger(level).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+className2+"', en el método '"+methodName2+"()'");
    }
    
    public void storeAndViewError14(Component component,String className2,String methodName2){
        JOptionPane.showMessageDialog(component,"Error: no existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
        new logger(level).staticLogger("Error 14: no existen o no se ingresaron los datos a buscar y/o cambiar.\nOcurrió en '"+className2+"', en el método '"+methodName2+"()'");
    }
    
    public void storeAndViewError18(Component component,String className2,String methodName2){
        JOptionPane.showMessageDialog(component,"Error:\nEscribe correctamente lo que deseas realizar en esta acción","Error 18",JOptionPane.WARNING_MESSAGE);
        new logger(level).staticLogger("Error 18: no se escribió la palabra clave para hacer la búsqueda.\nOcurrió en la clase '"+className2+"', en el método '"+methodName2+"()'");
    }
}