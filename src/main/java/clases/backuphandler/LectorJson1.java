package clases.backuphandler;
//clases
import clases.Datos1;
import clases.logger;
import clases.mvc.MvcForm01;
import clases.mvc.MvcForm02;
//librerías
import com.google.gson.stream.JsonReader;
//java
import java.io.FileReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
//con extensión larga
import java.util.logging.Level;
import java.nio.charset.StandardCharsets;

/**
 * Clase encargada de leer los archivos creados para copia de seguridad.
 * 
 * @author erick
 */
public class LectorJson1{
    protected JsonReader jsonr;
    
    protected int numeroVentas;
    
    protected String methodName;
    
    /**
     * Se encarga de leer un archivo JSON, con la estructura de la tabla de empleados, para volver a almacenar los datos en la base de datos.
     * 
     * @param dir Nombre del archivo a leer.
     */
    public void readDataWorkerJson(String dir){
        methodName="readDataWorkerJson";
        try{
            jsonr=new JsonReader(new FileReader(dir,StandardCharsets.UTF_8));
            List<MvcForm01> lista=new ArrayList<>();
            MvcForm01 modelo=new MvcForm01();
            
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
            
            new Datos1().insertarDatosEmpleado(lista);
            new Datos1().insertarDatosConteo(lista.get(0).getCodigo(),lista.get(0).getNombre(),lista.get(0).getApellidoPaterno(),lista.get(0).getApellidoMaterno(),numeroVentas);
            jsonr.endObject();
            
            jsonr.close();
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,LectorJson1.class.getName(),methodName,"1IO");
        }catch(IOException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,LectorJson1.class.getName(),methodName,"2IO");
        }catch(IllegalStateException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,n,LectorJson1.class.getName(),methodName,"15");
        }catch(SQLException s){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,s,LectorJson1.class.getName(),methodName,"11");
        }
    }
    
    /**
     * Lee datos del método para cargar datos de los empleados.<br>
     * Estos datos son los que vienen como objetos secundarios.
     * 
     * @param json El lector del archivo json con esos datos.
     * 
     * Nota: no usar en otros métodos.
     * 
     * @throws IOException
     * @throws IllegalStateException
     */
    protected void leerDatosSecundarios(JsonReader json) throws IOException,IllegalStateException{
        json.beginObject();
        while(json.hasNext()){
            switch(json.nextName()){
                case "no_ventas"->numeroVentas=json.nextInt();
            }
        }
        json.endObject();
    }
    
    /**
     * Se encarga de leer un archivo JSON, con la estructura de la tabla de socios, para volver a almacenar los datos en la base de datos.
     * 
     * @param dir Nombre del archivo a leer.
     */
    public void readDataPartnerJson(String dir){
        methodName="readDataPartnerJson";
        try{
            jsonr=new JsonReader(new FileReader(dir,StandardCharsets.UTF_8));
            List<MvcForm02> lista=new ArrayList<>();
            MvcForm02 modelo=new MvcForm02();
            
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
            new Datos1().insertarDatosSocio(lista);
            jsonr.endObject();
            
            jsonr.close();
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,LectorJson1.class.getName(),methodName,"1IO");
        }catch(IOException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,LectorJson1.class.getName(),methodName,"2IO");
        }catch(IllegalStateException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,n,LectorJson1.class.getName(),methodName,"15");
        }catch(SQLException s){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,s,LectorJson1.class.getName(),methodName,"11");
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
        methodName="readDataProviderJson";
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
            new Datos1().insertarDatosProveedor(codigoProveedor,nombreProveedor,apellidoPaternoProveedor,apellidoMaternoProveedor,empresa,contacto,new FileInputStream(foto));
            jsonr.endObject();
            
            jsonr.close();
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,LectorJson1.class.getName(),methodName,"1IO");
        }catch(IOException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,LectorJson1.class.getName(),methodName,"2IO");
        }catch(IllegalStateException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,n,LectorJson1.class.getName(),methodName,"15");
        }catch(SQLException s){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,s,LectorJson1.class.getName(),methodName,"11");
        }
    }
}