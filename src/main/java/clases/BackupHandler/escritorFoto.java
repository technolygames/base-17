package clases.BackupHandler;
//clases
import clases.datos;
import clases.dirs;
import clases.logger;
import venPrimarias.start;
//java
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
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
            
            FileOutputStream fos=new FileOutputStream(dir1);
            byte[] bytes;
            Blob blob;
            rs=ps.executeQuery();
            while(rs.next()){
                blob=rs.getBlob("foto");
                bytes=blob.getBytes(1,(int)blob.length());
                fos.write(bytes);
                break;
            }
            
            new logger(Level.INFO).staticLogger("Se guardó correctamente la imagen del empleado.\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'botones(storeImgButton)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
            
            ps.close();
            fos.flush();
            fos.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicWorker()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicWorker-14",e.fillInStackTrace());
        }catch(FileNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicWorker()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicWorker-10",x.fillInStackTrace());
        }catch(IOException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicWorker()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicWorker-10",n.fillInStackTrace());
        }catch(NullPointerException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+y.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicWorker()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicWorker-0",y.fillInStackTrace());
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
            
            FileOutputStream fos=new FileOutputStream(dir2);
            byte[] bytes;
            Blob blob;
            rs=ps.executeQuery();
            while(rs.next()){
                blob=rs.getBlob("foto");
                bytes=blob.getBytes(1,(int)blob.length());
                fos.write(bytes);
                break;
            }
            
            new logger(Level.INFO).staticLogger("Se guardó correctamente la imagen del socio.\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'botones(storeImgButton)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
            
            ps.close();
            fos.flush();
            fos.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicPartner()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicPartner-14",e.fillInStackTrace());
        }catch(FileNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicPartner()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicPartner-10",x.fillInStackTrace());
        }catch(IOException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicPartner()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicPartner-10",n.fillInStackTrace());
        }catch(NullPointerException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+y.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicPartner()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicPartner-0",y.fillInStackTrace());
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
            
            FileOutputStream fos=new FileOutputStream(dir3);
            byte[] bytes;
            Blob blob;
            rs=ps.executeQuery();
            while(rs.next()){
                blob=rs.getBlob("foto");
                bytes=blob.getBytes(1,(int)blob.length());
                fos.write(bytes);
                break;
            }
            
            new logger(Level.INFO).staticLogger("Se guardó correctamente la imagen del proveedor.\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'botones(storeImgButton)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
            
            ps.close();
            fos.flush();
            fos.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicProvider()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicProvider-14",e.fillInStackTrace());
        }catch(FileNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicProvider()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicProvider-10",x.fillInStackTrace());
        }catch(IOException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicProvider()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicProvider-10",n.fillInStackTrace());
        }catch(NullPointerException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+y.getMessage()+".\nOcurrió en la clase '"+escritorFoto.class.getName()+"', en el método 'storePicProvider()'");
            new logger(Level.SEVERE).exceptionLogger(escritorFoto.class.getName(),"storePicProvider-0",y.fillInStackTrace());
        }
    }
}