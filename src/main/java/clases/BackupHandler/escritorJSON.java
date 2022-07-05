package clases.BackupHandler;
//clases
import clases.datos;
import clases.logger;
//librerías
import com.google.gson.stream.JsonWriter;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import javax.swing.JOptionPane;
//con extensión larga
import java.util.logging.Level;
import java.nio.charset.StandardCharsets;

/**
 * Esta clase se encarga de crear archivos JSON para guardar datos.
 * 
 * @author erick
 */
public class escritorJSON{
    protected JsonWriter jsonw;
    protected PreparedStatement ps;
    protected ResultSet rs;
    
    /**
     * Se encarga de crear archivos JSON como copia de seguridad de la tabla empleados.
     * 
     * @param codigoEmpleado Código de identificación del empleado.
     */
    public void writeDataWorkerJson(int codigoEmpleado){
        try{
            ps=new datos().getConnection().prepareStatement("select empleados.*,conteo.no_ventas from empleados,conteo where empleados.codigo_emp='"+codigoEmpleado+"' and conteo.codigo_emp='"+codigoEmpleado+"';");
            rs=ps.executeQuery();
            while(rs.next()){
                new File(System.getProperty("user.dir")+"/data/databackup/Empleados/"+rs.getString("nombre_emp")+"-"+rs.getInt("codigo_emp")).mkdir();
                jsonw=new JsonWriter(new OutputStreamWriter(new FileOutputStream(System.getProperty("user.dir")+"/data/databackup/Empleados/"+rs.getString("nombre_emp")+"-"+rs.getInt("codigo_emp")+"/"+rs.getString("nombre_emp")+"-"+rs.getInt("codigo_emp")+".json"),StandardCharsets.UTF_8));
                new escritorFoto().storePicWorker(rs.getInt("codigo_emp"),rs.getString("nombre_emp"));
                
                jsonw.beginObject();
                jsonw.setIndent("   ");
                jsonw.name("password").value(rs.getString("password"));
                jsonw.name("codigo_emp").value(rs.getInt("codigo_emp"));
                jsonw.name("nombre_emp").value(rs.getString("nombre_emp"));
                jsonw.name("apellidop_emp").value(rs.getString("apellidop_emp"));
                jsonw.name("apellidom_emp").value(rs.getString("apellidom_emp"));
                jsonw.name("curp").value(rs.getString("curp"));
                jsonw.name("domicilio").value(rs.getString("domicilio"));
                jsonw.name("puesto").value(rs.getString("puesto"));
                jsonw.name("experiencia").value(rs.getInt("experiencia"));
                jsonw.name("grado_estudios").value(rs.getString("grado_estudios"));
                jsonw.name("contacto").value(rs.getInt("contacto"));
                jsonw.name("fecha_nacimiento").value(rs.getString("fecha_nacimiento"));
                jsonw.name("edad").value(rs.getInt("edad"));
                jsonw.name("estado").value(rs.getString("estado"));
                jsonw.name("datos_extra").value(rs.getString("datos_extra"));
                jsonw.name("imagen").value(escritorFoto.dir1);
                jsonw.name("datos").beginObject();
                jsonw.name("no_ventas").value(rs.getInt("no_ventas"));
                jsonw.endObject();
                jsonw.endObject();
                break;
            }
            
            ps.close();
            rs.close();
            jsonw.flush();
            jsonw.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+e.getMessage()+".\nOcurrió en la clase '"+escritorJSON.class.getName()+"', en el método 'writeDataWorkerJson()'");
            new logger(Level.SEVERE).exceptionLogger(escritorJSON.class.getName(),"writeDataWorkerJson-2IO",e.fillInStackTrace());
        }catch(IllegalStateException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error ISE",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error ISE: "+x.getMessage()+".\nOcurrió en la clase '"+escritorJSON.class.getName()+"', en el método 'writeDataWorkerJson()'");
            new logger(Level.SEVERE).exceptionLogger(escritorJSON.class.getName(),"writeDataWorkerJson-ISE",x.fillInStackTrace());
        }catch(SQLException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+n.getMessage()+".\nOcurrió en la clase '"+escritorJSON.class.getName()+"', en el método 'writeDataWorkerJson()'");
            new logger(Level.SEVERE).exceptionLogger(escritorJSON.class.getName(),"writeDataWorkerJson-14",n.fillInStackTrace());
        }catch(NullPointerException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+s.getMessage()+".\nOcurrió en la clase '"+escritorJSON.class.getName()+"', en el método 'writeDataWorkerJson()'");
            new logger(Level.SEVERE).exceptionLogger(escritorJSON.class.getName(),"writeDataWorkerJson-0",s.fillInStackTrace());
        }
    }
    
    /**
     * Se encarga de crear archivos JSON como copia de seguridad de la tabla socios.
     * 
     * @param codigoSocio Código de identificación del socio.
     */
    public void writeDataPartnerJson(int codigoSocio){
        try{
            ps=new datos().getConnection().prepareStatement("select*from socios where codigo_part='"+codigoSocio+"'");
            rs=ps.executeQuery();
            while(rs.next()){
                new File(System.getProperty("user.dir")+"/data/databackup/Socios/"+rs.getString("nombre_part")+"-"+rs.getInt("codigo_part")).mkdir();
                jsonw=new JsonWriter(new OutputStreamWriter(new FileOutputStream(System.getProperty("user.dir")+"/data/databackup/Socios/"+rs.getString("nombre_part")+"-"+rs.getInt("codigo_part")+"/"+rs.getString("nombre_part")+"-"+rs.getInt("codigo_part")+".json"),StandardCharsets.UTF_8));
                new escritorFoto().storePicPartner(rs.getInt("codigo_part"),rs.getString("nombre_part"));
                jsonw.beginObject();
                jsonw.setIndent("   ");
                jsonw.name("codigo_part").value(rs.getInt("codigo_part"));
                jsonw.name("nombre_part").value(rs.getString("nombre_part"));
                jsonw.name("apellidop_part").value(rs.getString("apellidop_part"));
                jsonw.name("apellidom_part").value(rs.getString("apellidom_part"));
                jsonw.name("tipo_socio").value(rs.getString("tipo_socio"));
                jsonw.name("correo").value(rs.getString("correo"));
                jsonw.name("rfc").value(rs.getString("rfc"));
                jsonw.name("datos_extra").value(rs.getString("datos_extra"));
                jsonw.name("imagen").value(escritorFoto.dir2);
                jsonw.endObject();
                break;
            }
            
            ps.close();
            rs.close();
            jsonw.flush();
            jsonw.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+e.getMessage()+".\nOcurrió en la clase '"+escritorJSON.class.getName()+"', en el método 'writeDataPartnerJson()'");
            new logger(Level.SEVERE).exceptionLogger(escritorJSON.class.getName(),"writeDataPartnerJson-2IO",e.fillInStackTrace());
        }catch(IllegalStateException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error ISE",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error ISE: "+x.getMessage()+".\nOcurrió en la clase '"+escritorJSON.class.getName()+"', en el método 'writeDataPartnerJson()'");
            new logger(Level.SEVERE).exceptionLogger(escritorJSON.class.getName(),"writeDataPartnerJson-ISE",x.fillInStackTrace());
        }catch(SQLException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+n.getMessage()+".\nOcurrió en la clase '"+escritorJSON.class.getName()+"', en el método 'writeDataPartnerJson()'");
            new logger(Level.SEVERE).exceptionLogger(escritorJSON.class.getName(),"writeDataPartnerJson-14",n.fillInStackTrace());
        }
    }
    
    /**
     * Se encarga de crear archivos JSON como copia de seguridad de la tabla proveedor.
     * 
     * @param codigoProveedor Código de identificación del proveedor.
     */
    public void writeDataProviderJson(int codigoProveedor){
        try{
            ps=new datos().getConnection().prepareStatement("select*from proveedor where codigo_prov='"+codigoProveedor+"';");
            rs=ps.executeQuery();
            while(rs.next()){
                new File(System.getProperty("user.dir")+"/data/databackup/Proveedores/"+rs.getString("nombre_prov")+"-"+rs.getInt("codigo_prov")).mkdir();
                jsonw=new JsonWriter(new OutputStreamWriter(new FileOutputStream(System.getProperty("user.dir")+"/data/databackup/Proveedores/"+rs.getString("nombre_prov")+"-"+rs.getInt("codigo_prov")+"/"+rs.getString("nombre_prov")+"-"+rs.getInt("codigo_prov")+".json"),StandardCharsets.UTF_8));
                new escritorFoto().storePicProvider(rs.getInt("codigo_prov"),rs.getString("nombre_prov"));
                jsonw.beginObject();
                jsonw.setIndent("   ");
                jsonw.name("codigo_prov").value(rs.getInt("codigo_prov"));
                jsonw.name("nombre_prov").value(rs.getString("nombre_prov"));
                jsonw.name("apellidop_prov").value(rs.getString("apellidop_prov"));
                jsonw.name("apellidom_prov").value(rs.getString("apellidom_prov"));
                jsonw.name("empresa").value(rs.getString("empresa"));
                jsonw.name("contacto").value(rs.getInt("contacto"));
                jsonw.name("imagen").value(escritorFoto.dir3);
                jsonw.endObject();
            }
            
            ps.close();
            rs.close();
            jsonw.flush();
            jsonw.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+e.getMessage()+".\nOcurrió en la clase '"+escritorJSON.class.getName()+"', en el método 'writeDataProviderJson()'");
            new logger(Level.SEVERE).exceptionLogger(escritorJSON.class.getName(),"writeDataProviderJson-2IO",e.fillInStackTrace());
        }catch(IllegalStateException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error ISE",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error ISE: "+x.getMessage()+".\nOcurrió en la clase '"+escritorJSON.class.getName()+"', en el método 'writeDataProviderJson()'");
            new logger(Level.SEVERE).exceptionLogger(escritorJSON.class.getName(),"writeDataProviderJson-ISE",x.fillInStackTrace());
        }catch(SQLException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+n.getMessage()+".\nOcurrió en la clase '"+escritorJSON.class.getName()+"', en el método 'writeDataProviderJson()'");
            new logger(Level.SEVERE).exceptionLogger(escritorJSON.class.getName(),"writeDataProviderJson-14",n.fillInStackTrace());
        }
    }
}