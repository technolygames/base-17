package clases.BackupHandler;
//clases
import clases.datos;
import clases.logger;
//librerías
import com.google.gson.stream.JsonReader;
//java
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
//con extensión larga
import java.util.logging.Level;
import java.nio.charset.StandardCharsets;

/**
 * Clase encargada de leer los archivos creados para copia de seguridad
 * 
 * @author erick
 */
public class lectorJSON{
    protected JsonReader jsonr;
    
    protected String password;
    protected int codigoEmpleado;
    protected String nombreEmpleado;
    protected String apellidoPaternoEmpleado;
    protected String apellidoMaternoEmpleado;
    protected String domicilio;
    protected String puesto;
    protected int experiencia;
    protected String gradoEstudios;
    protected int contacto1;
    protected int edad;
    protected String estado;
    protected String datosExtra1;
    protected String foto1;
    /**
     * Se encarga de leer un archivo JSON, con la estructura de la tabla de empleados, para volver a almacenar los datos en la base de datos.
     * 
     * @param dir Nombre el archivo a+- leer.
     */
    public void readDataWorkerJson(String dir){
        try{
            jsonr=new JsonReader(new InputStreamReader(new FileInputStream(dir),StandardCharsets.UTF_8));
            jsonr.beginObject();
            while(jsonr.hasNext()){
                String name=jsonr.nextName();
                if(name.equals("password")){
                    password=jsonr.nextString();
                }else if(name.equals("codigo_emp")){
                    codigoEmpleado=jsonr.nextInt();
                }else if(name.equals("nombre_emp")){
                    nombreEmpleado=jsonr.nextString();
                }else if(name.equals("apellidop_emp")){
                    apellidoPaternoEmpleado=jsonr.nextString();
                }else if(name.equals("apellidom_emp")){
                    apellidoMaternoEmpleado=jsonr.nextString();
                }else if(name.equals("domicilio")){
                    domicilio=jsonr.nextString();
                }else if(name.equals("puesto")){
                    puesto=jsonr.nextString();
                }else if(name.equals("experiencia")){
                    experiencia=jsonr.nextInt();
                }else if(name.equals("grado_estudios")){
                    gradoEstudios=jsonr.nextString();
                }else if(name.equals("contacto")){
                    contacto1=jsonr.nextInt();
                }else if(name.equals("edad")){
                    edad=jsonr.nextInt();
                }else if(name.equals("estado")){
                    estado=jsonr.nextString();
                }else if(name.equals("datos_extra")){
                    datosExtra1=jsonr.nextString();
                }else if(name.equals("imagen")){
                    foto1=jsonr.nextString();
                }
            }
            new datos().insertarDatosEmpleado(password,codigoEmpleado,nombreEmpleado,apellidoPaternoEmpleado,apellidoMaternoEmpleado,domicilio,puesto,experiencia,gradoEstudios,contacto1,edad,estado,datosExtra1,new FileInputStream(foto1));
            jsonr.endObject();
            
            jsonr.close();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataWorkerJson()'",Level.WARNING);
            new logger().exceptionLogger(lectorJSON.class.getName(),Level.WARNING,"readDataWorkerJson-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataWorkerJson()'",Level.WARNING);
            new logger().exceptionLogger(lectorJSON.class.getName(),Level.WARNING,"readDataWorkerJson-2IO",x.fillInStackTrace());
        }catch(IllegalStateException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error ISE",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error ISE: "+n.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataWorkerJson()'",Level.WARNING);
            new logger().exceptionLogger(lectorJSON.class.getName(),Level.WARNING,"readDataWorkerJson-ISE",n.fillInStackTrace());
        }
    }
    
    protected int codigoSocio;
    
    protected String nombreSocio;
    protected String apellidoPaternoSocio;
    protected String apellidoMaternoSocio;
    protected String tipoSocio;
    protected String correo;
    protected String rfc;
    protected String datosExtra2;
    protected String foto2;
    /**
     * Se encarga de leer un archivo JSON, con la estructura de la tabla de socios, para volver a almacenar los datos en la base de datos.
     * 
     * @param dir Nombre el archivo a leer.
     */
    public void readDataPartnerJson(String dir){
        try{
            jsonr=new JsonReader(new InputStreamReader(new FileInputStream(dir),StandardCharsets.UTF_8));
            jsonr.beginObject();
            while(jsonr.hasNext()){
                String name=jsonr.nextName();
                if(name.equals("codigo_part")){
                    codigoSocio=jsonr.nextInt();
                }else if(name.equals("nombre_part")){
                    nombreSocio=jsonr.nextString();
                }else if(name.equals("apellidop_part")){
                    apellidoPaternoSocio=jsonr.nextString();
                }else if(name.equals("apellidom_part")){
                    apellidoMaternoSocio=jsonr.nextString();
                }else if(name.equals("tipo_socio")){
                    tipoSocio=jsonr.nextString();
                }else if(name.equals("correo")){
                    correo=jsonr.nextString();
                }else if(name.equals("rfc")){
                    rfc=jsonr.nextString();
                }else if(name.equals("datos_extra")){
                    datosExtra2=jsonr.nextString();
                }else if(name.equals("imagen")){
                    foto2=jsonr.nextString();
                }
            }
            new datos().insertarDatosSocio(codigoSocio,nombreSocio,apellidoPaternoSocio,apellidoMaternoSocio,tipoSocio,correo,rfc,datosExtra2,new FileInputStream(foto2));
            jsonr.endObject();
            
            jsonr.close();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataPartnerJson()'",Level.WARNING);
            new logger().exceptionLogger(lectorJSON.class.getName(),Level.WARNING,"readDataPartnerJson-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataPartnerJson()'",Level.WARNING);
            new logger().exceptionLogger(lectorJSON.class.getName(),Level.WARNING,"readDataPartnerJson-2IO",x.fillInStackTrace());
        }catch(IllegalStateException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error ISE",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error ISE: "+n.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataPartnerJson()'",Level.WARNING);
            new logger().exceptionLogger(lectorJSON.class.getName(),Level.WARNING,"readDataPartnerJson-ISE",n.fillInStackTrace());
        }
    }
    
    protected int codigoProveedor;
    protected String nombreProveedor;
    protected String apellidoPaternoProveedor;
    protected String apellidoMaternoProveedor;
    protected String empresa;
    protected int contacto2;
    protected String foto3;
    /**
     * Se encarga de leer un archivo JSON, con la estructura de la tabla de proveedor, para volver a almacenar los datos en la base de datos.
     * 
     * @param dir Nombre el archivo a leer.
     */
    public void readDataProviderJson(String dir){
        try{
            jsonr=new JsonReader(new InputStreamReader(new FileInputStream(dir),StandardCharsets.UTF_8));
            jsonr.beginObject();
            while(jsonr.hasNext()){
                String name=jsonr.nextName();
                if(name.equals("codigo_prov")){
                    codigoProveedor=jsonr.nextInt();
                }else if(name.equals("nombre_prov")){
                    nombreProveedor=jsonr.nextString();
                }else if(name.equals("apellidop_prov")){
                    apellidoPaternoProveedor=jsonr.nextString();
                }else if(name.equals("apellidom_prov")){
                    apellidoMaternoProveedor=jsonr.nextString();
                }else if(name.equals("empresa")){
                    empresa=jsonr.nextString();
                }else if(name.equals("contacto")){
                    contacto2=jsonr.nextInt();
                }else if(name.equals("imagen")){
                    foto3=jsonr.nextString();
                }
            }
            new datos().insertarDatosProveedor(codigoProveedor,nombreProveedor,apellidoPaternoProveedor,apellidoMaternoProveedor,empresa,contacto2,new FileInputStream(foto3));
            jsonr.endObject();
            
            jsonr.close();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataProviderJson()'",Level.WARNING);
            new logger().exceptionLogger(lectorJSON.class.getName(),Level.WARNING,"readDataProviderJson-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataProviderJson()'",Level.WARNING);
            new logger().exceptionLogger(lectorJSON.class.getName(),Level.WARNING,"readDataProviderJson-2IO",x.fillInStackTrace());
        }catch(IllegalStateException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error ISE",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error ISE: "+n.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataProviderJson()'",Level.WARNING);
            new logger().exceptionLogger(lectorJSON.class.getName(),Level.WARNING,"readDataProviderJson-ISE",n.fillInStackTrace());
        }
    }
}