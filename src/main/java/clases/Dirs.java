package clases;
//java
import java.io.File;
//extension larga
import org.apache.commons.io.FilenameUtils;

/**
 * Clase encargada de verificar las carpetas que utiliza el programa.
 * 
 * @author erick
 */
public class Dirs{
    protected Dirs(){}
    
    public static final String USERDIR=System.getProperty("user.dir");
    
    /**
     * Verifica si existen las carpetas. Si no existen, las crea automáticamente.
     * 
     * @param dir Dirección a verificar y crear.
     */
    public static void makeDir(String dir){
        File direccion=new File(dir);
        if(!direccion.exists()){
            direccion.mkdir();
        }
    }
    
    public static String exists(File f){
        String filename0=f.getName();
        String parent=f.getParent();
        
        String filename=FilenameUtils.getBaseName(filename0);
        String ext=FilenameUtils.getExtension(filename0);
        
        f=new File(parent,filename+"."+ext);
        for(int i=0;f.exists();i++){
            f=new File(parent,filename+"-"+i+"."+ext);
        }
        return f.getPath();
    }
}