package clases.BackupHandler;
//clases
import clases.logger;
//librerías
import com.google.gson.stream.JsonWriter;
//java
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
    
    /**
     * Se encarga de crear archivos JSON como copia de seguridad para la tabla empleados.
     * 
     * @param password Contraseña usada por el empleado.
     * @param codigoEmpleado Código de identificación del empleado.
     * @param nombreEmpleado Nombre(s) del empleado.
     * @param apellidoPaternoEmpleado Apellido paterno del empleado.
     * @param apellidoMaternoEmpleado Apellido materno del empleado.
     * @param domicilio Domicilio actual de empleado.
     * @param puesto Puesto asignado al empleado.
     * @param experiencia Experiencia laboral previa del empleado.
     * @param gradoEstudios Grado máximo de estudios del empleado.
     * @param contacto Número telefónico del empleado.
     * @param edad Edad actual del empleado.
     * @param estado Estado laboral del empleado en el negocio.
     * @param datosExtra Datos extras.
     */
    public void writeDataWorkerJson(String password,int codigoEmpleado,String nombreEmpleado,String apellidoPaternoEmpleado,String apellidoMaternoEmpleado,String domicilio,String puesto,String experiencia,String gradoEstudios,int contacto,int edad,String estado,String datosExtra){
        try{
            jsonw=new JsonWriter(new OutputStreamWriter(new FileOutputStream("src/data/dataBackup/"+nombreEmpleado+"-"+(int)(Math.random()*10000)+".json"),StandardCharsets.UTF_8));
            jsonw.beginObject();
            jsonw.setIndent("   ");
            jsonw.name("password").value(password);
            jsonw.name("codigo_emp").value(codigoEmpleado);
            jsonw.name("nombre_emp").value(nombreEmpleado);
            jsonw.name("apellidop_emp").value(apellidoPaternoEmpleado);
            jsonw.name("apellidom_emp").value(apellidoMaternoEmpleado);
            jsonw.name("domicilio").value(domicilio);
            jsonw.name("puesto").value(puesto);
            jsonw.name("experiencia").value(experiencia);
            jsonw.name("grado_estudios").value(gradoEstudios);
            jsonw.name("contacto").value(contacto);
            jsonw.name("edad").value(edad);
            jsonw.name("estado").value(estado);
            jsonw.name("datos_extra").value(datosExtra);
            jsonw.endObject();
            
            jsonw.flush();
            jsonw.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+e.getMessage()+".\nOcurrió en la clase '"+escritorJSON.class.getName()+"', en el método 'writeDataWorkerJson()'",Level.WARNING);
            new logger().exceptionLogger(escritorJSON.class.getName(),Level.WARNING,"writeDataWorkerJson-2IO",e.fillInStackTrace());
        }catch(IllegalStateException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error ISA",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error ISA: "+x.getMessage()+".\nOcurrió en la clase '"+escritorJSON.class.getName()+"', en el método 'writeDataWorkerJson()'",Level.WARNING);
            new logger().exceptionLogger(escritorJSON.class.getName(),Level.WARNING,"writeDataWorkerJson-ISA",x.fillInStackTrace());
        }
    }
    
    /**
     * Se encarga de crear archivos JSON como copia de seguridad para la tabla socios.
     * 
     * @param codigoSocio Código de identificación del socio.
     * @param nombreSocio Nombre(s) del socio.
     * @param apellidoPaternoSocio Apellido paterno del socio.
     * @param apellidoMaternoSocio Apellido materno del socio.
     * @param tipoSocio Tipo de afiliación del socio.
     * @param datosExtra Datos extras.
     */
    public void writeDataPartnerJson(int codigoSocio,String nombreSocio,String apellidoPaternoSocio,String apellidoMaternoSocio,String tipoSocio,String datosExtra){
        try{
            jsonw=new JsonWriter(new OutputStreamWriter(new FileOutputStream("src/data/dataBackup/"+nombreSocio+"-"+(int)(Math.random()*10000)+".json"),StandardCharsets.UTF_8));
            jsonw.beginObject();
            jsonw.setIndent("   ");
            jsonw.name("codigo_part").value(codigoSocio);
            jsonw.name("nombre_part").value(nombreSocio);
            jsonw.name("apellidop_part").value(apellidoPaternoSocio);
            jsonw.name("apellidom_part").value(apellidoMaternoSocio);
            jsonw.name("tipo_socio").value(tipoSocio);
            jsonw.name("datos_extra").value(datosExtra);
            jsonw.endObject();
            
            jsonw.flush();
            jsonw.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+e.getMessage()+".\nOcurrió en la clase '"+escritorJSON.class.getName()+"', en el método 'writeDataPartnerJson()'",Level.WARNING);
            new logger().exceptionLogger(escritorJSON.class.getName(),Level.WARNING,"writeDataPartnerJson-2IO",e.fillInStackTrace());
        }catch(IllegalStateException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error ISA",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error ISA: "+x.getMessage()+".\nOcurrió en la clase '"+escritorJSON.class.getName()+"', en el método 'writeDataPartnerJson()'",Level.WARNING);
            new logger().exceptionLogger(escritorJSON.class.getName(),Level.WARNING,"writeDataPartnerJson-ISA",x.fillInStackTrace());
        }
    }
    
    /**
     * Se encarga de crear archivos JSON como copia de seguridad para la tabla proveedor.
     * 
     * 
     * @param codigoProveedor Código de identificación del proveedor.
     * @param nombreProveedor Nombre(s) del proveedor.
     * @param apellidoPaternoProveedor Apellido paterno del proveedor.
     * @param apellidoMaternoProveedor Apellido materno del proveedor.
     * @param empresa Empresa a la que pertenece el proveedor.
     * @param contacto Número telefónico del proveedor.
     */
    public void writeDataProviderJson(int codigoProveedor,String nombreProveedor,String apellidoPaternoProveedor,String apellidoMaternoProveedor,String empresa,int contacto){
        try{
            jsonw=new JsonWriter(new OutputStreamWriter(new FileOutputStream("src/data/dataBackup/"+nombreProveedor+"-"+(int)(Math.random()*10000)+".json"),StandardCharsets.UTF_8));
            jsonw.beginObject();
            jsonw.setIndent("   ");
            jsonw.name("codigo_prov").value(codigoProveedor);
            jsonw.name("nombre_prov").value(nombreProveedor);
            jsonw.name("apellidop_prov").value(apellidoPaternoProveedor);
            jsonw.name("apellidom_prov").value(apellidoMaternoProveedor);
            jsonw.name("empresa").value(empresa);
            jsonw.name("contacto").value(contacto);
            jsonw.endObject();
            
            jsonw.flush();
            jsonw.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+e.getMessage()+".\nOcurrió en la clase '"+escritorJSON.class.getName()+"', en el método 'writeDataProviderJson()'",Level.WARNING);
            new logger().exceptionLogger(escritorJSON.class.getName(),Level.WARNING,"writeDataProviderJson-2IO",e.fillInStackTrace());
        }catch(IllegalStateException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error ISA",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error ISA: "+x.getMessage()+".\nOcurrió en la clase '"+escritorJSON.class.getName()+"', en el método 'writeDataProviderJson()'",Level.WARNING);
            new logger().exceptionLogger(escritorJSON.class.getName(),Level.WARNING,"writeDataProviderJson-ISA",x.fillInStackTrace());
        }
    }
}