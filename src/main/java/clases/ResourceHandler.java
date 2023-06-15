package clases;
//java
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.sql.Date;
//extension larga
import java.util.logging.Level;

/**
 * Clase encargada de descargar los recursos de internet en caso de que el programa tenga actualizaciones pendientes.
 * 
 * @author erick
 */
public class ResourceHandler{
    protected ResourceHandler(){}
    
    protected static Frame frame=MediaHandler.getFrames();
    
    protected static String methodName;
    protected static String className=ResourceHandler.class.getName();
    protected static String path0="data/generic/temp/";
    
    protected static File f;
    protected static InputStream is;
    protected static FileOutputStream fos;
    
    protected static URL u;
    protected static URLConnection uc;
    
    /**
     * Método encargado de descargar de internet los recursos del programa.
     * 
     * @param link del recurso a decargar.
     */
    public static void downloadResources(String link){
        methodName="downloadLibs";
        try{
            //Checa si hay conexión a internet
            //Checks if there's internet connection
            while(checkConnection(link)!=null){
                u=new URL(link);
                uc=u.openConnection();
                
                String file=u.getFile();
                
                f=new File(path0+file);
                
                String path1=Dirs.exists(f);
                
                /**
                 * Checa si existe el archivo a modificar y si no es igual al peso del archivo local
                 * Checks if the file to change exists and if is not equal to the local file
                 */
                if(!f.exists()&&f.length()==0){
                    is=uc.getInputStream();
                    fos=new FileOutputStream(path1);
                    
                    new Thread1(is,fos).run();
                }
                break;
            }
            
            is.close();
            fos.flush();
            fos.close();
        }catch(MalformedURLException e){
            new logger(Level.SEVERE,className).catchException(frame,e,methodName,"1I");
        }catch(FileNotFoundException x){
            new logger(Level.SEVERE,className).catchException(frame,x,methodName,"1IO");
        }catch(IOException k){
            new logger(Level.SEVERE,className).catchException(frame,k,methodName,"2IO");
        }
    }
    
    /**
     * Verifica si el programa necesita ser actualizado revisando registros previos de actualización.
     * 
     * @param file que será validado si hay actualización pendiente.
     * @param link del archivo a verificar usando internet.
     * 
     * @return un valor booleano en caso de que se deba actualizar o no el programa o archivo.
     */
    public static boolean checkUpdate(String file,String link){
        methodName="checkUpdate";
        try{
            u=new URL(link);
            uc=u.openConnection();
            
            f=new File(path0+file);
            
            LocalDate fechalocal1=LocalDate.parse(new Date(f.lastModified()).toString()/*,DateTimeFormatter.ofPattern("dd/MM/yyyy")*/);
            
            LocalDate fechalocal2=LocalDate.parse(new Date(uc.getIfModifiedSince()).toString()/*,DateTimeFormatter.ofPattern("dd/MM/yyyy")*/);
            
            return fechalocal1.equals(fechalocal2);
        }catch(MalformedURLException e){
            new logger(Level.SEVERE,className).catchException(frame,e,methodName,"1I");
            return false;
        }catch(IOException x){
            new logger(Level.SEVERE,className).catchException(frame,x,methodName,"2IO");
            return false;
        }
    }
    
    /**
     * Esta clase se encarga de verificar si hay conectividad a internet.
     * 
     * @param url a la que se verificará la conexión.
     * 
     * @return un valor cadena si está conectado o no. Puede ser null si no está conectado a una red.
     * 
     * @throws MalformedURLException
     * @throws IOException
     */
    protected static String checkConnection(String url) throws MalformedURLException,IOException{
        u=new URL(url);
        uc=u.openConnection();
        uc.connect();
        
        return u.getPath();
    }
}