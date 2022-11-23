package clases.backuphandler;
//clases
import clases.Datos;
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
//con extensión larga
import java.util.logging.Level;
import java.nio.charset.StandardCharsets;

/**
 * Esta clase se encarga de crear archivos JSON para guardar datos.
 * 
 * @author erick
 */
public class EscritorJson{
    protected JsonWriter jsonw;
    protected PreparedStatement ps;
    protected ResultSet rs;
    
    protected String methodName;
    
    /**
     * Se encarga de crear archivos JSON como copia de seguridad de la tabla empleados.
     * 
     * @param codigoEmpleado a crear copia de seguridad.
     */
    public void writeDataWorkerJson(int codigoEmpleado){
        methodName="writeDataWorkerJson";
        try{
            ps=new Datos().getConnection().prepareStatement("select empleados.*,conteo.no_ventas from empleados,conteo where empleados.codigo_emp=? and conteo.codigo_emp=?;");
            ps.setInt(1,codigoEmpleado);
            ps.setInt(2,codigoEmpleado);
            rs=ps.executeQuery();
            while(rs.next()){
                int codigo=rs.getInt("codigo_emp");
                String nombre=rs.getString("nombre_emp");
                
                new File("data/databackup/Empleados/"+nombre+"-"+codigo).mkdir();
                jsonw=new JsonWriter(new OutputStreamWriter(new FileOutputStream("data/databackup/Empleados/"+nombre+"-"+codigo+"/"+nombre+"-"+codigo+".json"),StandardCharsets.UTF_8));
                new EscritorFoto().storePicWorker(codigo,nombre);
                
                jsonw.beginObject();
                jsonw.setIndent("   ");
                jsonw.name("password").value(rs.getString("password"));
                jsonw.name("codigo_emp").value(codigo);
                jsonw.name("nombre_emp").value(nombre);
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
                jsonw.name("imagen").value(EscritorFoto.dir1);
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
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,EscritorJson.class.getName(),methodName,"2IO");
        }catch(IllegalStateException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,EscritorJson.class.getName(),methodName,"15");
        }catch(SQLException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,n,EscritorJson.class.getName(),methodName,"14");
        }catch(NullPointerException s){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,s,EscritorJson.class.getName(),methodName,"0");
        }
    }
    
    /**
     * Se encarga de crear archivos JSON como copia de seguridad de la tabla socios.
     * 
     * @param codigoSocio a crear copia de seguridad.
     */
    public void writeDataPartnerJson(int codigoSocio){
        methodName="writeDataPartnerJson";
        try{
            ps=new Datos().getConnection().prepareStatement("select*from socios where codigo_part=?;");
            ps.setInt(1,codigoSocio);
            rs=ps.executeQuery();
            while(rs.next()){
                int codigo=rs.getInt("codigo_part");
                String nombre=rs.getString("nombre_part");
                
                new File("data/databackup/Socios/"+nombre+"-"+codigo).mkdir();
                jsonw=new JsonWriter(new OutputStreamWriter(new FileOutputStream("data/databackup/Socios/"+nombre+"-"+codigo+"/"+nombre+"-"+codigo+".json"),StandardCharsets.UTF_8));
                new EscritorFoto().storePicPartner(codigo,nombre);
                
                jsonw.beginObject();
                jsonw.setIndent("   ");
                jsonw.name("codigo_part").value(codigo);
                jsonw.name("nombre_part").value(nombre);
                jsonw.name("apellidop_part").value(rs.getString("apellidop_part"));
                jsonw.name("apellidom_part").value(rs.getString("apellidom_part"));
                jsonw.name("tipo_socio").value(rs.getString("tipo_socio"));
                jsonw.name("correo").value(rs.getString("correo"));
                jsonw.name("rfc").value(rs.getString("rfc"));
                jsonw.name("datos_extra").value(rs.getString("datos_extra"));
                jsonw.name("imagen").value(EscritorFoto.dir2);
                jsonw.endObject();
                break;
            }
            
            ps.close();
            rs.close();
            jsonw.flush();
            jsonw.close();
        }catch(IOException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,EscritorJson.class.getName(),methodName,"2IO");
        }catch(IllegalStateException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,EscritorJson.class.getName(),methodName,"15");
        }catch(SQLException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,n,EscritorJson.class.getName(),methodName,"14");
        }
    }
    
    /**
     * Se encarga de crear archivos JSON como copia de seguridad de la tabla proveedor.
     * 
     * @param codigoProveedor a crear copia de seguridad.
     */
    public void writeDataProviderJson(int codigoProveedor){
        methodName="writeDataProviderJson";
        try{
            ps=new Datos().getConnection().prepareStatement("select*from proveedor where codigo_prov=?;");
            ps.setInt(1,codigoProveedor);
            rs=ps.executeQuery();
            while(rs.next()){
                int codigo=rs.getInt("codigo_prov");
                String nombre=rs.getString("nombre_prov");
                
                new File("data/databackup/Proveedores/"+nombre+"-"+codigo).mkdir();
                jsonw=new JsonWriter(new OutputStreamWriter(new FileOutputStream("data/databackup/Proveedores/"+nombre+"-"+codigo+"/"+nombre+"-"+codigo+".json"),StandardCharsets.UTF_8));
                new EscritorFoto().storePicProvider(codigo,nombre);
                
                jsonw.beginObject();
                jsonw.setIndent("   ");
                jsonw.name("codigo_prov").value(codigo);
                jsonw.name("nombre_prov").value(nombre);
                jsonw.name("apellidop_prov").value(rs.getString("apellidop_prov"));
                jsonw.name("apellidom_prov").value(rs.getString("apellidom_prov"));
                jsonw.name("empresa").value(rs.getString("empresa"));
                jsonw.name("contacto").value(rs.getInt("contacto"));
                jsonw.name("imagen").value(EscritorFoto.dir3);
                jsonw.endObject();
            }
            
            ps.close();
            rs.close();
            jsonw.flush();
            jsonw.close();
        }catch(IOException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,EscritorJson.class.getName(),methodName,"2IO");
        }catch(IllegalStateException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,EscritorJson.class.getName(),methodName,"15");
        }catch(SQLException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,n,EscritorJson.class.getName(),methodName,"14");
        }
    }
}