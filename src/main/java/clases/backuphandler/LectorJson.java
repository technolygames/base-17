package clases.backuphandler;
//clases
import clases.Datos;
import clases.Dirs;
import clases.Events;
import clases.MediaHandler;
import clases.logger;
import clases.mvc.MvcForm1;
import clases.mvc.MvcForm2;
import clases.mvc.MvcForm3;
import clases.mvc.Controlador;
//librerías
import com.google.gson.stream.JsonReader;
//java
import java.awt.Frame;
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
import java.sql.Date;

/**
 * Clase encargada de leer los archivos creados para copia de seguridad.
 * 
 * @author erick
 */
public class LectorJson{
    protected Controlador modelo0;
    
    public LectorJson(Controlador modelo){
        this.modelo0=modelo;
    }
    
    protected Frame frame=MediaHandler.getFrames();
    
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
            List<MvcForm1> lista=new ArrayList<>();
            MvcForm1 modelo=new MvcForm1();
            
            jsonr.beginObject();
            while(jsonr.hasNext()){
                switch(jsonr.nextName()){
                    case "password" -> modelo.setPassword(jsonr.nextString());
                    case "codigo_emp" -> modelo.setCodigo(jsonr.nextInt());
                    case "nombre_emp" -> modelo.setNombre(jsonr.nextString());
                    case "apellidop_emp" -> modelo.setApellidoPaterno(jsonr.nextString());
                    case "apellidom_emp" -> modelo.setApellidoMaterno(jsonr.nextString());
                    case "curp" -> modelo.setCurp(jsonr.nextString());
                    case "domicilio" -> modelo.setDomicilio(jsonr.nextString());
                    case "puesto" -> modelo.setPuesto(jsonr.nextString());
                    case "experiencia" -> modelo.setExperiencia(jsonr.nextInt());
                    case "grado_estudios" -> modelo.setGradoEstudios(jsonr.nextString());
                    case "contacto" -> modelo.setContacto(jsonr.nextInt());
                    case "fecha_nacimiento" -> modelo.setFechaNacimiento(jsonr.nextString());
                    case "edad" -> modelo.setEdad(Events.calcAge(Date.valueOf(modelo.getFechaNacimiento()).getTime(),jsonr.nextInt()));
                    case "estado" -> modelo.setEstado(jsonr.nextString());
                    case "datos_extra" -> modelo.setDatosExtra(jsonr.nextString());
                    case "imagen" -> modelo.setImagen(new FileInputStream(Dirs.findPic(dir,jsonr.nextString())));
                    case "datos" -> {
                        jsonr.beginObject();
                        while(jsonr.hasNext()){
                            switch(jsonr.nextName()){
                                case "no_ventas"->modelo.setNumeroVentas(jsonr.nextInt());
                                default->jsonr.skipValue();
                            }
                        }
                        jsonr.endObject();
                    }
                    default -> jsonr.skipValue();
                }
            }
            lista.add(modelo);
            new Datos(modelo0).insertarDatosEmpleado(lista);
            new Datos(modelo0).insertarDatosConteo(modelo.getCodigo(),modelo.getNombre(),modelo.getApellidoPaterno(),modelo.getApellidoMaterno(),modelo.getNumeroVentas());
            jsonr.endObject();
            
            lista.clear();
            jsonr.close();
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,e,methodName,"1IO");
        }catch(IOException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,x,methodName,"2IO");
        }catch(IllegalStateException n){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,n,methodName,"15");
        }catch(SQLException s){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,s,methodName,"11");
        }
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
            List<MvcForm2> lista=new ArrayList<>();
            MvcForm2 modelo=new MvcForm2();
            
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
                    case "imagen" -> modelo.setImagen(new FileInputStream(Dirs.findPic(dir,jsonr.nextString())));
                    default -> jsonr.skipValue();
                }
            }
            lista.add(modelo);
            new Datos(modelo0).insertarDatosSocio(lista);
            jsonr.endObject();
            
            lista.clear();
            jsonr.close();
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,e,methodName,"1IO");
        }catch(IOException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,x,methodName,"2IO");
        }catch(IllegalStateException n){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,n,methodName,"15");
        }catch(SQLException s){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,s,methodName,"11");
        }
    }
    
    /**
     * Se encarga de leer un archivo JSON, con la estructura de la tabla de proveedor, para volver a almacenar los datos en la base de datos.
     * 
     * @param dir Nombre del archivo a leer.
     */
    public void readDataProviderJson(String dir){
        methodName="readDataProviderJson";
        try{
            jsonr=new JsonReader(new FileReader(dir,StandardCharsets.UTF_8));
            List<MvcForm3> lista=new ArrayList<>();
            MvcForm3 modelo=new MvcForm3();
            jsonr.beginObject();
            while(jsonr.hasNext()){
                switch(jsonr.nextName()){
                    case "codigo_prov" -> modelo.setCodigo(jsonr.nextInt());
                    case "nombre_prov" -> modelo.setNombre(jsonr.nextString());
                    case "apellidop_prov" -> modelo.setApellidoPaterno(jsonr.nextString());
                    case "apellidom_prov" -> modelo.setApellidoMaterno(jsonr.nextString());
                    case "empresa" -> modelo.setEmpresa(jsonr.nextString());
                    case "contacto" -> modelo.setContacto(jsonr.nextInt());
                    case "imagen" -> modelo.setImagen(new FileInputStream(Dirs.findPic(dir,jsonr.nextString())));
                    default -> jsonr.skipValue();
                }
            }
            lista.add(modelo);
            new Datos(modelo0).insertarDatosProveedor(lista);
            jsonr.endObject();
            
            jsonr.close();
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,e,methodName,"1IO");
        }catch(IOException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,x,methodName,"2IO");
        }catch(IllegalStateException n){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,n,methodName,"15");
        }catch(SQLException s){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,s,methodName,"11");
        }
    }
}