package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 * Clase encargada de descargar los recursos necesarios para el correcto funcionamiento del programa.
 * Descarga las librerías e idiomas.
 * 
 * @author erick
 */
public class resourceDownload{
    protected boolean sis;
    
    protected InputStream is;
    protected FileOutputStream fos;
    protected File f;
    
    protected URL u=null;
    protected Socket s=null;
    protected InetAddress ia=null;
    protected SocketAddress sa=null;
    protected URLConnection uc=null;
    
    /**
     * Esta clase se encarga de verificar si hay conectividad a internet.
     * 
     * @param url Dirección a la que se verificará la conexión.
     * @param puerto Número del puerto por el que se va a verificar la conexión.
     */
    public boolean checkConnection(String url,int puerto){
        try{
            s=new Socket();
            ia=InetAddress.getByName(url);
            sa=new InetSocketAddress(ia,puerto);
            
            s.bind(sa);
            s.connect(sa);
            
            sis=s.isConnected();
        }catch(UnknownHostException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1I",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1I: "+e.getMessage()+"\nOcurrió en la clase '"+resourceDownload.class.getName()+"', en el método 'checkConnection()'",Level.WARNING);
            new logger().exceptionLogger(resourceDownload.class.getName(),Level.WARNING,"checkConnection-1I",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+x.getMessage()+"\nOcurrió en la clase '"+resourceDownload.class.getName()+"', en el método 'checkConnection()'",Level.WARNING);
            new logger().exceptionLogger(resourceDownload.class.getName(),Level.WARNING,"checkConnection-1IO",x.fillInStackTrace());
        }
        return sis;
    }
    
    /**
     * Método encargado de descargar de internet las librerías(.jar).
     * 
     * @param validar Archivo que se validará y guardará
     * @param link Página web del recurso a decargar
     */
    public void downloadLibs(String validar,String link){
        f=new File(System.getProperty("user.dir")+"/src/data/libs/"+validar);
        try{
            if(!f.exists()){
                f.createNewFile();
            }
            
            if(!f.exists()&&f.length()==0&&checkConnection(link, 443)==true){
                u=new URL(link);
                uc=u.openConnection();
                
                is=uc.getInputStream();
                fos=new FileOutputStream(System.getProperty("user.dir")+"src/data/libs/"+validar);
                
                new thread(is,fos).run();
                
                is.close();
                fos.flush();
                fos.close();
            }
        }catch(MalformedURLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1I",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1I: "+e.getMessage()+"\nOcurrió en la clase '"+resourceDownload.class.getName()+"', en el método 'downloadLibs()'",Level.WARNING);
            new logger().exceptionLogger(resourceDownload.class.getName(),Level.WARNING,"downloadLibs-1I",e.fillInStackTrace());
        }catch(FileNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+x.getMessage()+"\nOcurrió en la clase '"+resourceDownload.class.getName()+"', en el método 'downloadLibs()'",Level.WARNING);
            new logger().exceptionLogger(resourceDownload.class.getName(),Level.WARNING,"downloadLibs-1IO",x.fillInStackTrace());
        }catch(IOException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+k.getMessage()+"\nOcurrió en la clase '"+resourceDownload.class.getName()+"', en el método 'downloadLibs()'",Level.WARNING);
            new logger().exceptionLogger(resourceDownload.class.getName(),Level.WARNING,"downloadLibs-2IO",k.fillInStackTrace());
        }
    }
}