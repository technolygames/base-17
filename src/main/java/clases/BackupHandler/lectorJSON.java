package clases.BackupHandler;
//clases
import clases.datos;
import clases.logger;
import clases.mvc.mvcForm1;
import clases.mvc.mvcForm2;
//librerías
import com.google.gson.stream.JsonReader;
//java
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JOptionPane;
//con extensión larga
import java.util.logging.Level;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de leer los archivos creados para copia de seguridad.
 * 
 * @author erick
 */
public class lectorJSON{
    protected JsonReader jsonr;
    
    protected int numeroVentas;
    
    /**
     * Se encarga de leer un archivo JSON, con la estructura de la tabla de empleados, para volver a almacenar los datos en la base de datos.
     * 
     * @param dir Nombre del archivo a leer.
     */
    public void readDataWorkerJson(String dir){
        try{
            jsonr=new JsonReader(new FileReader(dir,StandardCharsets.UTF_8));
            List<mvcForm1> lista=new ArrayList<>();
            mvcForm1 modelo=new mvcForm1();
            
            jsonr.beginObject();
            while(jsonr.hasNext()){
                switch(jsonr.nextName()){
                    case "password" -> modelo.setPassword(jsonr.nextString());
                    case "codigo_emp" -> modelo.setCodigo(jsonr.nextInt());
                    case "nombre_emp" -> modelo.setNombre(jsonr.nextString());
                    case "apellidop_emp" -> modelo.setApellidoMaterno(jsonr.nextString());
                    case "apellidom_emp" -> modelo.setApellidoMaterno(jsonr.nextString());
                    case "curp" -> modelo.setCurp(jsonr.nextString());
                    case "domicilio" -> modelo.setDomicilio(jsonr.nextString());
                    case "puesto" -> modelo.setPuesto(jsonr.nextString());
                    case "experiencia" -> modelo.setExperiencia(jsonr.nextInt());
                    case "grado_estudios" -> modelo.setGradoEstudios(jsonr.nextString());
                    case "contacto" -> modelo.setContacto(jsonr.nextInt());
                    case "fecha_nacimiento" -> modelo.setFechaNacimiento(jsonr.nextString());
                    case "edad" -> modelo.setEdad(jsonr.nextInt());
                    case "estado" -> modelo.setEstado(jsonr.nextString());
                    case "datos_extra" -> modelo.setDatosExtra(jsonr.nextString());
                    case "imagen" -> modelo.setImagen(new FileInputStream(jsonr.nextString()));
                    case "datos" -> leerDatosSecundarios(jsonr);
                    default -> jsonr.skipValue();
                }
            }
            
            lista.add(modelo);
            
            new datos().insertarDatosEmpleado(lista);
            new datos().insertarDatosConteo(lista.get(0).getCodigo(),lista.get(0).getNombre(),lista.get(0).getApellidoPaterno(),lista.get(0).getApellidoMaterno(),numeroVentas);
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
    
    /**
     * Se encarga de leer un archivo JSON, con la estructura de la tabla de socios, para volver a almacenar los datos en la base de datos.
     * 
     * @param dir Nombre del archivo a leer.
     */
    public void readDataPartnerJson(String dir){
        try{
            jsonr=new JsonReader(new FileReader(dir,StandardCharsets.UTF_8));
            List<mvcForm2> lista=new ArrayList<>();
            mvcForm2 modelo=new mvcForm2();
            
            jsonr.beginObject();
            while(jsonr.hasNext()){
                switch(jsonr.nextName()){
                    case "codigo_part" -> modelo.setCodigo(jsonr.nextInt());
                    case "nombre_part" -> modelo.setNombre(jsonr.nextString());
                    case "apellidop_part" -> modelo.setApellidoPaterno(jsonr.nextString());
                    case "apellidom_part" -> modelo.setApellidoMaterno(jsonr.nextString());
                    case "tipo_socio" -> modelo.setTipo(jsonr.nextString());
                    case "correo" -> modelo.setCorreo(jsonr.nextString());
                    case "rfc" -> modelo.setRfc(jsonr.nextString());
                    case "datos_extra" -> modelo.setDatos(jsonr.nextString());
                    case "imagen" -> modelo.setImagen(new FileInputStream(jsonr.nextString()));
                    default -> jsonr.skipValue();
                }
            }
            lista.add(modelo);
            new datos().insertarDatosSocio(lista);
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
    protected int contacto;
    protected String foto;
    /**
     * Se encarga de leer un archivo JSON, con la estructura de la tabla de proveedor, para volver a almacenar los datos en la base de datos.
     * 
     * @param dir Nombre del archivo a leer.
     */
    public void readDataProviderJson(String dir){
        try{
            jsonr=new JsonReader(new FileReader(dir,StandardCharsets.UTF_8));
            jsonr.beginObject();
            while(jsonr.hasNext()){
                switch(jsonr.nextName()){
                    case "codigo_prov" -> codigoProveedor=jsonr.nextInt();
                    case "nombre_prov" -> nombreProveedor=jsonr.nextString();
                    case "apellidop_prov" -> apellidoPaternoProveedor=jsonr.nextString();
                    case "apellidom_prov" -> apellidoMaternoProveedor=jsonr.nextString();
                    case "empresa" -> empresa=jsonr.nextString();
                    case "contacto" -> contacto=jsonr.nextInt();
                    case "imagen" -> foto=jsonr.nextString();
                    default -> jsonr.skipValue();
                }
            }
            new datos().insertarDatosProveedor(codigoProveedor,nombreProveedor,apellidoPaternoProveedor,apellidoMaternoProveedor,empresa,contacto,new FileInputStream(foto));
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