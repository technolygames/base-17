package clases;
//java
import java.awt.Frame;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
//con extensión larga
import java.util.logging.Level;

/**
 * Clase encargada para leer errores en consola. 
 * Este hilo se encarga de leer errores producidos al leer/escribir un archivo en consola.
 * 
 * @author erick
 */
public class Thread3 implements Runnable{
    protected String className=this.getClass().getName();
    
    protected Frame frame=MediaHandler.getFrames();
    
    protected InputStream is;
    
    /**
     * Recibe el flujo de datos para leer el error producido en la consola.
     * 
     * @param is Flujo de datos del mensaje de error.
     */
    public Thread3(InputStream is){
        this.is=is;
    }
    
    /**
     * Método que puede ser usado para que pueda leer e imprimir el error en consola.
     */
    @Override
    public void run(){
        int leido;
        try{
            byte[] buffer=new byte[1024];
            while((leido=is.read(buffer))>0){
                String texto=new String(buffer,0,leido);
                JOptionPane.showMessageDialog(frame,"Error:\n"+texto,"Error 7E",JOptionPane.ERROR_MESSAGE);
                logger.staticLogger(Level.SEVERE,"Error 7E: "+texto+".\nOcurrió en el método 'run()'.",className);
            }
            
            is.close();
        }catch(IOException e){
            new logger(Level.SEVERE,className).catchException(frame,e,"run","2IO");
        }
    }
}