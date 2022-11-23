package clases;
//java
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
//extension larga
import java.util.logging.Level;

/**
 * Clase encargada de descargar los recursos de internet en caso de que el programa tenga actualizaciones pendientes.
 * 
 * @author erick
 */
public class ResourceHandler{
    protected String methodName;
    
    protected File f;
    protected InputStream is;
    protected FileOutputStream fos;
    
    protected URL u;
    protected URLConnection uc;
    
    /**
     * Método encargado de descargar de internet los recursos del programa.
     * 
     * @param archivo que se validará y guardará.
     * @param ext Extensión del archivo.
     * @param link del recurso a decargar.
     */
    public void downloadResources(String archivo,String ext,String link){
        methodName="downloadLibs";
        f=new File("data/generic/temp/"+archivo+"."+ext);
        for(int i=0;f.exists();i++){
            f=new File("data/generic/temp/"+archivo+"-"+i+"."+ext);
        }
        try{
            while(checkConnection(link)!=null){
                if(!f.exists()&&f.length()==0){
                    u=new URL(link);
                    uc=u.openConnection();
                    
                    is=uc.getInputStream();
                    fos=new FileOutputStream(f.getPath());
                    
                    new Thread1(is,fos).run();
                }
                break;
            }
            
            is.close();
            fos.flush();
            fos.close();
        }catch(MalformedURLException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,ResourceHandler.class.getName(),methodName,"1I");
        }catch(FileNotFoundException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,ResourceHandler.class.getName(),methodName,"1IO");
        }catch(IOException k){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,k,ResourceHandler.class.getName(),methodName,"2IO");
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
    public String checkConnection(String url) throws MalformedURLException,IOException{
        u=new URL(url);
        uc=u.openConnection();
        
        return u.getPath();
    }
}