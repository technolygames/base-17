package clases.backuphandler;
//clases
import clases.Datos;
import clases.Dirs;
import clases.MediaHandler;
import clases.logger;
import clases.Thread2;
import clases.mvc.Controlador;
//java
import java.awt.Frame;
import java.io.File;
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
public class EscritorFoto{
    protected Controlador modelo;
    
    public EscritorFoto(Controlador modelo){
        this.modelo=modelo;
    }
    
    protected Frame frame=MediaHandler.getFrames();
    protected String userdir=Dirs.USERDIR;
    
    protected File f;
    
    protected String methodName;
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    /**
     * Almacena la foto de perfil del empleado en una carpeta designada.
     * 
     * @param codigoEmpleado Código de identificación del empleado.
     * @param nombreEmpleado Nombre(s) del empleado.
     * 
     * @return dirección de la imagen de perfil almacenada.
     */
    public String storePicWorker(int codigoEmpleado,String nombreEmpleado){
        methodName="storePicWorker";
        try{
            ps=new Datos(modelo).getConnection().prepareStatement("select foto from empleados where codigo_emp='"+codigoEmpleado+"';");
            
            rs=ps.executeQuery();
            
            f=new File(userdir+"/data/databackup/Empleados/"+nombreEmpleado+"-"+codigoEmpleado,nombreEmpleado+"-"+codigoEmpleado+".jpg");
            
            new Thread2(rs,new FileOutputStream(f)).run();
            
            logger.staticLogger(Level.INFO,"Se guardó correctamente la imagen del empleado.\nOcurrió en el método 'storePicWorker()'.\nUsuario que hizo la acción: "+String.valueOf(modelo.getUserID()),this.getClass().getName());
            
            ps.close();
            
            return f.getPath();
        }catch(SQLException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,e,methodName,"14");
            return null;
        }catch(FileNotFoundException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,x,methodName,"1IO");
            return null;
        }catch(NullPointerException n){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,n,methodName,"0");
            return null;
        }
    }
    
    /**
     * Almacena la foto de perfil del proveedor en una carpeta designada.
     * 
     * @param codigoProveedor Código de identificación del proveedor.
     * @param nombreProveedor Nombre(s) del proveedor.
     * 
     * @return dirección de la imagen de perfil almacenada.
     */
    public String storePicProvider(int codigoProveedor,String nombreProveedor){
        methodName="storePicProvider";
        try{
            ps=new Datos(modelo).getConnection().prepareStatement("select foto from proveedor where codigo_prov='"+codigoProveedor+"';");
            
            f=new File(userdir+"/data/databackup/Proveedores/"+nombreProveedor+"-"+codigoProveedor,nombreProveedor+"-"+codigoProveedor+".jpg");
            
            rs=ps.executeQuery();
            new Thread2(rs,new FileOutputStream(f)).run();
            
            logger.staticLogger(Level.INFO,"Se guardó correctamente la imagen del proveedor.\nOcurrió en el método 'storePicProvider()'.\nUsuario que hizo la acción: "+String.valueOf(modelo.getUserID()),this.getClass().getName());
            
            ps.close();
            
            return f.getPath();
        }catch(SQLException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,e,methodName,"14");
            return null;
        }catch(FileNotFoundException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,x,methodName,"1IO");
            return null;
        }catch(NullPointerException n){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,n,methodName,"0");
            return null;
        }
    }
    
    /**
     * Almacena la foto de perfil del socio en una carpeta designada.
     * 
     * @param codigoSocio Código de identificación del socio.
     * @param nombreSocio Nombre(s) del socio.
     * 
     * @return dirección de la imagen de perfil almacenada.
     */
    public String storePicPartner(int codigoSocio,String nombreSocio){
        methodName="storePicPartner";
        try{
            ps=new Datos(modelo).getConnection().prepareStatement("select foto from socios where codigo_part='"+codigoSocio+"';");
            
            f=new File(userdir+"/data/databackup/Socios/"+nombreSocio+"-"+codigoSocio,nombreSocio+"-"+codigoSocio+".jpg");
            
            rs=ps.executeQuery();
            new Thread2(rs,new FileOutputStream(f)).run();
            
            logger.staticLogger(Level.INFO,"Se guardó correctamente la imagen del socio.\nOcurrió en el método 'storePicPartner()'.\nUsuario que hizo la acción: "+String.valueOf(modelo.getUserID()),this.getClass().getName());
            
            ps.close();
            
            return f.getPath();
        }catch(SQLException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,e,methodName,"14");
            return null;
        }catch(FileNotFoundException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,x,methodName,"1IO");
            return null;
        }catch(NullPointerException n){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,n,methodName,"0");
            return null;
        }
    }
}