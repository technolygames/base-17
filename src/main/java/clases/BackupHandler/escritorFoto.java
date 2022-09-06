package clases.BackupHandler;
//clases
import clases.datos;
import clases.dirs;
import clases.logger;
import clases.thread2;
import venPrimarias.start;
//java
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

/**
 * Clase encargada de almacenar la imagen del empleado, socio o proveedor como copia de seguridad.
 * 
 * @author erick
 */
public class escritorFoto{
    public static String dir1;
    public static String dir2;
    public static String dir3;
    
    protected String userdir=dirs.userdir;
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    /**
     * Almacena la foto de perfil del empleado en una carpeta designada.
     * 
     * @param codigoEmpleado Código de identificación del empleado.
     * @param nombreEmpleado Nombre(s) del empleado.
     */
    public void storePicWorker(int codigoEmpleado,String nombreEmpleado){
        try{
            ps=new datos().getConnection().prepareStatement("select foto from empleados where codigo_emp='"+codigoEmpleado+"';");
            
            dir1=userdir+"\\data\\databackup\\Empleados\\"+nombreEmpleado+"-"+codigoEmpleado+"\\"+nombreEmpleado+"-"+codigoEmpleado+".jpg";
            
            rs=ps.executeQuery();
            new thread2(rs,new FileOutputStream(dir1)).run();
            
            new logger(Level.INFO).staticLogger("Se guardó correctamente la imagen del empleado.\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'botones(storeImgButton)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicWorker()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicWorker-14",e.fillInStackTrace());
        }catch(FileNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicWorker()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicWorker-1IO",x.fillInStackTrace());
        }catch(NullPointerException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+n.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicWorker()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicWorker-0",n.fillInStackTrace());
        }
    }
    
    /**
     * Almacena la foto de perfil del proveedor en una carpeta designada.
     * 
     * @param codigoProveedor Código de identificación del proveedor.
     * @param nombreProveedor Nombre(s) del proveedor.
     */
    public void storePicProvider(int codigoProveedor,String nombreProveedor){
        try{
            ps=new datos().getConnection().prepareStatement("select foto from proveedor where codigo_prov='"+codigoProveedor+"';");
            
            dir3=userdir+"\\data\\databackup\\Proveedores\\"+nombreProveedor+"-"+codigoProveedor+"\\"+nombreProveedor+"-"+codigoProveedor+".jpg";
            
            rs=ps.executeQuery();
            new thread2(rs,new FileOutputStream(dir3)).run();
            
            new logger(Level.INFO).staticLogger("Se guardó correctamente la imagen del proveedor.\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'botones(storeImgButton)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicProvider()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicProvider-14",e.fillInStackTrace());
        }catch(FileNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicProvider()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicProvider-1IO",x.fillInStackTrace());
        }catch(NullPointerException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+n.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicProvider()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicProvider-0",n.fillInStackTrace());
        }
    }
    
    /**
     * Almacena la foto de perfil del socio en una carpeta designada.
     * 
     * @param codigoSocio Código de identificación del socio.
     * @param nombreSocio Nombre(s) del socio.
     */
    public void storePicPartner(int codigoSocio,String nombreSocio){
        try{
            ps=new datos().getConnection().prepareStatement("select foto from socios where codigo_part='"+codigoSocio+"';");
            
            dir2=userdir+"\\data\\databackup\\Socios\\"+nombreSocio+"-"+codigoSocio+"\\"+nombreSocio+"-"+codigoSocio+".jpg";
            
            rs=ps.executeQuery();
            new thread2(rs,new FileOutputStream(dir2)).run();
            
            new logger(Level.INFO).staticLogger("Se guardó correctamente la imagen del socio.\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'botones(storeImgButton)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicPartner()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicPartner-14",e.fillInStackTrace());
        }catch(FileNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicPartner()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicPartner-1IO",x.fillInStackTrace());
        }catch(NullPointerException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+n.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicPartner()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicPartner-0",n.fillInStackTrace());
        }
    }
}