package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 * Clase encargada de descargar los recursos necesarios para el correcto funcionamiento del programa.
 * Descarga las librerías e idiomas.
 */
public class resourceDownload{
    protected InputStream is;
    protected FileOutputStream fos;
    protected File f;
    
    protected URL u;
    protected URLConnection uc;
    
    /**
     * Método encargado de descargar de internet las librerías(.jar).
     * 
     * @param validar Archivo que se validará y guardará
     * @param link Página web del recurso a decargar
     */
    public void downloadLibs(String validar,String link){
        f=new File("src/data/libs/"+validar);
        if(!f.exists()){
            try{
                f.createNewFile();
                u=new URL(link);
                uc=u.openConnection();
                
                is=uc.getInputStream();
                fos=new FileOutputStream("src/data/libs/"+validar);
                
                new thread(is,fos).run();
                
                is.close();
                fos.flush();
                fos.close();
            }catch(MalformedURLException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1I",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 1I: "+e.getMessage()+"\nOcurrió en la clase '"+resourceDownload.class.getName()+"', en el método 'downloadLibs()'",Level.WARNING);
                new logger().exceptionLogger(resourceDownload.class.getName(),Level.WARNING,"downloadLibs-1I",e.fillInStackTrace());
            }catch(FileNotFoundException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 1IO: "+x.getMessage()+"\nOcurrió en la clase '"+resourceDownload.class.getName()+"', en el método 'downloadLibs()'",Level.WARNING);
                new logger().exceptionLogger(resourceDownload.class.getName(),Level.WARNING,"downloadLibs-1IO",x.fillInStackTrace());
            }catch(IOException k){
                JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 2IO: "+k.getMessage()+"\nOcurrió en la clase '"+resourceDownload.class.getName()+"', en el método 'downloadLibs()'",Level.WARNING);
                new logger().exceptionLogger(resourceDownload.class.getName(),Level.WARNING,"downloadLibs-2IO",k.fillInStackTrace());
            }
        }
    }
}