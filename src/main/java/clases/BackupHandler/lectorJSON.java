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
 * Clase encargada de leer los archivos creados para copia de seguridad.
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
    protected String curp;
    protected String domicilio;
    protected String puesto;
    protected int experiencia;
    protected String gradoEstudios;
    protected int contacto1;
    protected String fechaNacimiento;
    protected int edad;
    protected String estado;
    protected String datosExtra1;
    protected String foto1;
    protected int anular;
    protected int numeroVentas;
    /**
     * Se encarga de leer un archivo JSON, con la estructura de la tabla de empleados, para volver a almacenar los datos en la base de datos.
     * 
     * @param dir Nombre del archivo a leer.
     */
    public void readDataWorkerJson(String dir){
        try{
            jsonr=new JsonReader(new InputStreamReader(new FileInputStream(dir),StandardCharsets.UTF_8));
            jsonr.beginObject();
            while(jsonr.hasNext()){
                String name=jsonr.nextName();
                switch(name){
                    case "password":
                        password=jsonr.nextString();
                        break;
                    case "codigo_emp":
                        codigoEmpleado=jsonr.nextInt();
                        break;
                    case "nombre_emp":
                        nombreEmpleado=jsonr.nextString();
                        break;
                    case "apellidop_emp":
                        apellidoPaternoEmpleado=jsonr.nextString();
                        break;
                    case "apellidom_emp":
                        apellidoMaternoEmpleado=jsonr.nextString();
                        break;
                    case "curp":
                        curp=jsonr.nextString();
                        break;
                    case "domicilio":
                        domicilio=jsonr.nextString();
                        break;
                    case "puesto":
                        puesto=jsonr.nextString();
                        break;
                    case "experiencia":
                        experiencia=jsonr.nextInt();
                        break;
                    case "grado_estudios":
                        gradoEstudios=jsonr.nextString();
                        break;
                    case "contacto":
                        contacto1=jsonr.nextInt();
                        break;
                    case "fecha_nacimiento":
                        fechaNacimiento=jsonr.nextString();
                        break;
                    case "edad":
                        edad=jsonr.nextInt();
                        break;
                    case "estado":
                        estado=jsonr.nextString();
                        break;
                    case "datos_extra":
                        datosExtra1=jsonr.nextString();
                        break;
                    case "imagen":
                        foto1=jsonr.nextString();
                        break;
                    case "datos":
                        leerDatosSecundarios(jsonr);
                        break;
                    default:
                        jsonr.skipValue();
                        break;
                }
            }
            new datos().insertarDatosEmpleado(password,codigoEmpleado,nombreEmpleado,apellidoPaternoEmpleado,apellidoMaternoEmpleado,curp,domicilio,puesto,experiencia,gradoEstudios,contacto1,fechaNacimiento,edad,estado,datosExtra1,new FileInputStream(foto1));
            new datos().insertarDatosConteo(codigoEmpleado,nombreEmpleado,apellidoPaternoEmpleado,apellidoMaternoEmpleado,numeroVentas);
            jsonr.endObject();
            
            jsonr.close();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataWorkerJson()'");
            new logger(Level.SEVERE).exceptionLogger(lectorJSON.class.getName(),"readDataWorkerJson-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataWorkerJson()'");
            new logger(Level.SEVERE).exceptionLogger(lectorJSON.class.getName(),"readDataWorkerJson-2IO",x.fillInStackTrace());
        }catch(IllegalStateException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 15",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 15: "+n.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataWorkerJson()'");
            new logger(Level.SEVERE).exceptionLogger(lectorJSON.class.getName(),"readDataWorkerJson-15",n.fillInStackTrace());
        }
    }
    
    /**
     * Lee datos del método para cargar datos de los empleados.<br>
     * Estos datos son los que vienen como objetos secundarios.
     * 
     * @param json El lector del archivo json con esos datos.
     * 
     * Nota: no usar en otros métodos.
     */
    protected void leerDatosSecundarios(JsonReader json){
        try{
            json.beginObject();
            while(json.hasNext()){
                String name=json.nextName();
                if(name.equals("no_ventas")){
                    numeroVentas=jsonr.nextInt();
                }
            }
            json.endObject();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+e.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'leerDatosSecundarios()'");
            new logger(Level.SEVERE).exceptionLogger(lectorJSON.class.getName(),"leerDatosSecundarios-2IO",e.fillInStackTrace());
        }catch(IllegalStateException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 15",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 15: "+x.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'leerDatosSecundarios()'");
            new logger(Level.SEVERE).exceptionLogger(lectorJSON.class.getName(),"leerDatosSecundarios-15",x.fillInStackTrace());
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
     * @param dir Nombre del archivo a leer.
     */
    public void readDataPartnerJson(String dir){
        try{
            jsonr=new JsonReader(new InputStreamReader(new FileInputStream(dir),StandardCharsets.UTF_8));
            jsonr.beginObject();
            while(jsonr.hasNext()){
                String name=jsonr.nextName();
                switch(name){
                    case "codigo_part":
                        codigoSocio=jsonr.nextInt();
                        break;
                    case "nombre_part":
                        nombreSocio=jsonr.nextString();
                        break;
                    case "apellidop_part":
                        apellidoPaternoSocio=jsonr.nextString();
                        break;
                    case "apellidom_part":
                        apellidoMaternoSocio=jsonr.nextString();
                        break;
                    case "tipo_socio":
                        tipoSocio=jsonr.nextString();
                        break;
                    case "correo":
                        correo=jsonr.nextString();
                        break;
                    case "rfc":
                        rfc=jsonr.nextString();
                        break;
                    case "datos_extra":
                        datosExtra2=jsonr.nextString();
                        break;
                    case "imagen":
                        foto2=jsonr.nextString();
                        break;
                    default:
                        break;
                }
            }
            new datos().insertarDatosSocio(codigoSocio,nombreSocio,apellidoPaternoSocio,apellidoMaternoSocio,tipoSocio,correo,rfc,datosExtra2,new FileInputStream(foto2));
            jsonr.endObject();
            
            jsonr.close();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataPartnerJson()'");
            new logger(Level.SEVERE).exceptionLogger(lectorJSON.class.getName(),"readDataPartnerJson-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataPartnerJson()'");
            new logger(Level.SEVERE).exceptionLogger(lectorJSON.class.getName(),"readDataPartnerJson-2IO",x.fillInStackTrace());
        }catch(IllegalStateException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 15",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 15: "+n.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataPartnerJson()'");
            new logger(Level.SEVERE).exceptionLogger(lectorJSON.class.getName(),"readDataPartnerJson-15",n.fillInStackTrace());
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
     * @param dir Nombre del archivo a leer.
     */
    public void readDataProviderJson(String dir){
        try{
            jsonr=new JsonReader(new InputStreamReader(new FileInputStream(dir),StandardCharsets.UTF_8));
            jsonr.beginObject();
            while(jsonr.hasNext()){
                String name=jsonr.nextName();
                switch(name){
                    case "codigo_prov":
                        codigoProveedor=jsonr.nextInt();
                        break;
                    case "nombre_prov":
                        nombreProveedor=jsonr.nextString();
                        break;
                    case "apellidop_prov":
                        apellidoPaternoProveedor=jsonr.nextString();
                        break;
                    case "apellidom_prov":
                        apellidoMaternoProveedor=jsonr.nextString();
                        break;
                    case "empresa":
                        empresa=jsonr.nextString();
                        break;
                    case "contacto":
                        contacto2=jsonr.nextInt();
                        break;
                    case "imagen":
                        foto3=jsonr.nextString();
                        break;
                    default:
                        break;
                }
            }
            new datos().insertarDatosProveedor(codigoProveedor,nombreProveedor,apellidoPaternoProveedor,apellidoMaternoProveedor,empresa,contacto2,new FileInputStream(foto3));
            jsonr.endObject();
            
            jsonr.close();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataProviderJson()'");
            new logger(Level.SEVERE).exceptionLogger(lectorJSON.class.getName(),"readDataProviderJson-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataProviderJson()'");
            new logger(Level.SEVERE).exceptionLogger(lectorJSON.class.getName(),"readDataProviderJson-2IO",x.fillInStackTrace());
        }catch(IllegalStateException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 15",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 15: "+n.getMessage()+".\nOcurrió en la clase '"+lectorJSON.class.getName()+"', en el método 'readDataProviderJson()'");
            new logger(Level.SEVERE).exceptionLogger(lectorJSON.class.getName(),"readDataProviderJson-15",n.fillInStackTrace());
        }
    }
}