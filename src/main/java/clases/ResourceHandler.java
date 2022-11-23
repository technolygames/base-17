package clases;
//java
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URL;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URLConnection;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.net.MalformedURLException;
//extension larga
import java.util.logging.Level;

/**
 * Clase encargada de descargar los recursos necesarios para el correcto funcionamiento del programa.<br>
 * Descarga las librerías e idiomas.
 * 
 * @author erick
 */
public class ResourceHandler{
    protected boolean estado=false;
    
    protected String methodName;
    
    protected File f;
    protected InputStream is;
    protected OutputStream os;
    
    protected URL u;
    protected Socket s;
    protected SocketAddress sa;
    protected URLConnection uc;
    
    /**
     * Esta clase se encarga de verificar si hay conectividad a internet.
     * 
     * @param url Dirección a la que se verificará la conexión.
     * @param puerto Número del puerto por el que se va a verificar la conexión.
     * 
     * @return el valor booleano si está conectado o no
     */
    public boolean checkConnection(String url,int puerto){
        methodName="checkConnection";
        try{
            s=new Socket();
            sa=new InetSocketAddress(url,puerto);
            
            s.bind(sa);
            s.connect(sa);
            
            return s.isConnected();
        }catch(UnknownHostException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,ResourceHandler.class.getName(),methodName,"1I");
            return false;
        }catch(IOException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,ResourceHandler.class.getName(),methodName,"1IO");
            return false;
        }
    }
    
    /**
     * Método encargado de descargar de internet las librerías(.jar).
     * 
     * @param validar Archivo que se validará y guardará
     * @param link Página web del recurso a decargar
     */
    public void downloadLibs(String validar,String link){
        methodName="downloadLibs";
        f=new File("data/generic/temp/"+validar);
        try{
            if(!f.exists()&&f.length()==0){
                u=new URL(link);
                uc=u.openConnection();
                
                is=uc.getInputStream();
                os=new FileOutputStream(f.getPath());
                
                new Thread(new Thread01(is,os)).start();
            }
            
            is.close();
            os.flush();
            os.close();
        }catch(MalformedURLException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,ResourceHandler.class.getName(),methodName,"1I");
        }catch(FileNotFoundException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,ResourceHandler.class.getName(),methodName,"1IO");
        }catch(IOException k){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,k,ResourceHandler.class.getName(),methodName,"2IO");
        }
    }
}