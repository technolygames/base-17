package clases.backuphandler;
//clases
import clases.Datos1;
import clases.Dirs1;
import clases.logger;
import clases.Thread02;
import venPrimarias.start;
//java
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
//extension larga
import java.util.logging.Level;

/**
 * Clase encargada de almacenar la imagen del empleado, socio o proveedor como copia de seguridad.
 * 
 * @author erick
 */
public class EscritorFoto1{
    public static String dir1;
    public static String dir2;
    public static String dir3;
    
    protected String methodName;
    protected String userdir=Dirs1.userdir;
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    /**
     * Almacena la foto de perfil del empleado en una carpeta designada.
     * 
     * @param codigoEmpleado Código de identificación del empleado.
     * @param nombreEmpleado Nombre(s) del empleado.
     */
    public void storePicWorker(int codigoEmpleado,String nombreEmpleado){
        methodName="storePicWorker";
        try{
            ps=new Datos1().getConnection().prepareStatement("select foto from empleados where codigo_emp='"+codigoEmpleado+"';");
            
            dir1=userdir+"\\data\\databackup\\Empleados\\"+nombreEmpleado+"-"+codigoEmpleado+"\\"+nombreEmpleado+"-"+codigoEmpleado+".jpg";
            
            rs=ps.executeQuery();
            new Thread02(rs,new FileOutputStream(dir1)).run();
            
            new logger(Level.INFO).staticLogger("Se guardó correctamente la imagen del empleado.\nOcurrió en la clase '"+EscritorFoto1.class.getName()+"', en el método 'botones(storeImgButton)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
            
            ps.close();
        }catch(SQLException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,EscritorFoto1.class.getName(),methodName,"14");
        }catch(FileNotFoundException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,EscritorFoto1.class.getName(),methodName,"1IO");
        }catch(NullPointerException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,n,EscritorFoto1.class.getName(),methodName,"0");
        }
    }
    
    /**
     * Almacena la foto de perfil del proveedor en una carpeta designada.
     * 
     * @param codigoProveedor Código de identificación del proveedor.
     * @param nombreProveedor Nombre(s) del proveedor.
     */
    public void storePicProvider(int codigoProveedor,String nombreProveedor){
        methodName="storePicProvider";
        try{
            ps=new Datos1().getConnection().prepareStatement("select foto from proveedor where codigo_prov='"+codigoProveedor+"';");
            
            dir3=userdir+"\\data\\databackup\\Proveedores\\"+nombreProveedor+"-"+codigoProveedor+"\\"+nombreProveedor+"-"+codigoProveedor+".jpg";
            
            rs=ps.executeQuery();
            new Thread02(rs,new FileOutputStream(dir3)).run();
            
            new logger(Level.INFO).staticLogger("Se guardó correctamente la imagen del proveedor.\nOcurrió en la clase '"+EscritorFoto1.class.getName()+"', en el método 'botones(storeImgButton)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
            
            ps.close();
        }catch(SQLException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,EscritorFoto1.class.getName(),methodName,"14");
        }catch(FileNotFoundException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,EscritorFoto1.class.getName(),methodName,"1IO");
        }catch(NullPointerException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,n,EscritorFoto1.class.getName(),methodName,"0");
        }
    }
    
    /**
     * Almacena la foto de perfil del socio en una carpeta designada.
     * 
     * @param codigoSocio Código de identificación del socio.
     * @param nombreSocio Nombre(s) del socio.
     */
    public void storePicPartner(int codigoSocio,String nombreSocio){
        methodName="storePicPartner";
        try{
            ps=new Datos1().getConnection().prepareStatement("select foto from socios where codigo_part='"+codigoSocio+"';");
            
            dir2=userdir+"\\data\\databackup\\Socios\\"+nombreSocio+"-"+codigoSocio+"\\"+nombreSocio+"-"+codigoSocio+".jpg";
            
            rs=ps.executeQuery();
            new Thread02(rs,new FileOutputStream(dir2)).run();
            
            new logger(Level.INFO).staticLogger("Se guardó correctamente la imagen del socio.\nOcurrió en la clase '"+EscritorFoto1.class.getName()+"', en el método 'botones(storeImgButton)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
            
            ps.close();
        }catch(SQLException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,EscritorFoto1.class.getName(),methodName,"14");
        }catch(FileNotFoundException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,EscritorFoto1.class.getName(),methodName,"1IO");
        }catch(NullPointerException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,n,EscritorFoto1.class.getName(),methodName,"0");
        }
    }
}