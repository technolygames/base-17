package clases.backuphandler;
//clases
import clases.Dirs;
import clases.Events;
import clases.logger;
import clases.MediaHandler;
import clases.mvc.MvcForm1;
import clases.mvc.MvcForm2;
import clases.mvc.MvcForm3;
//librerías
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
//java
import java.sql.Date;
import java.awt.Frame;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//con extensión larga
import java.util.logging.Level;
import java.nio.charset.StandardCharsets;

/**
 * Clase encargada de leer los archivos creados para copia de seguridad.
 * 
 * @author erick
 */
public class LectorJson{
    protected Frame frame=MediaHandler.getFrames();
    
    protected String methodName;
    
    /**
     * Se encarga de leer un archivo JSON, con la estructura de la tabla de empleados. 
     * Devuelve los datos organizados en una clase llamada MvcForm1.
     * 
     * @param dir Nombre del archivo a leer.
     * 
     * @return los datos rescatados del archivo leído.
     */
    public MvcForm1 readDataWorkerJson(String dir){
        methodName="readDataWorkerJson";
        try{
            JsonElement je=JsonParser.parseReader(new JsonReader(new FileReader(dir,StandardCharsets.UTF_8)));
            MvcForm1 modelo=new MvcForm1();
            Gson gson=new GsonBuilder().create();
            
            var data=gson.toJsonTree(je).getAsJsonObject();
            var data2=gson.toJsonTree(je).getAsJsonObject().getAsJsonObject("datos");
            
            modelo.setPassword(data.get("password").getAsString());
            modelo.setCodigo(data.get("codigo_emp").getAsInt());
            modelo.setNombre(data.get("nombre_emp").getAsString());
            modelo.setApellidoPaterno(data.get("apellidop_emp").getAsString());
            modelo.setApellidoMaterno(data.get("apellidom_emp").getAsString());
            modelo.setCurp(data.get("curp").getAsString());
            modelo.setDomicilio(data.get("domicilio").getAsString());
            modelo.setPuesto(data.get("puesto").getAsString());
            modelo.setExperiencia(data.get("experiencia").getAsInt());
            modelo.setGradoEstudios(data.get("grado_estudios").getAsString());
            modelo.setContacto(data.get("contacto").getAsInt());
            modelo.setFechaNacimiento(data.get("fecha_nacimiento").getAsString());
            modelo.setEdad(Events.calcAge(Date.valueOf(modelo.getFechaNacimiento()).getTime(),data.get("edad").getAsInt()));
            modelo.setEstado(data.get("estado").getAsString());
            modelo.setDatosExtra(data.get("datos_extra").getAsString());
            String dirImage=Dirs.findPic(dir,data.get("imagen").getAsString());
            modelo.setImagen(new FileInputStream(dirImage));
            modelo.setDirImagen(dirImage);
            modelo.setNumeroVentas(data2.get("no_ventas").getAsInt());
            
            return modelo;
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,e,methodName,"1IO");
            return null;
        }catch(IOException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,x,methodName,"2IO");
            return null;
        }catch(IllegalStateException n){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,n,methodName,"15");
            return null;
        }
    }
    
    /**
     * Se encarga de leer un archivo JSON, con la estructura de la tabla de socios. 
     * Devuelve los datos organizados en una clase llamada MvcForm2.
     * 
     * @param dir Nombre del archivo a leer.
     * 
     * @return los datos rescatados del archivo leído.
     */
    public MvcForm2 readDataPartnerJson(String dir){
        methodName="readDataPartnerJson";
        try{
            JsonElement je=JsonParser.parseReader(new JsonReader(new FileReader(dir,StandardCharsets.UTF_8)));
            MvcForm2 modelo=new MvcForm2();
            Gson gson=new GsonBuilder().create();
            
            var data=gson.toJsonTree(je).getAsJsonObject();
            
            modelo.setCodigo(data.get("codigo_part").getAsInt());
            modelo.setNombre(data.get("nombre_part").getAsString());
            modelo.setApellidoPaterno(data.get("apellidop_part").getAsString());
            modelo.setApellidoMaterno(data.get("apellidom_part").getAsString());
            modelo.setTipo(data.get("tipo_socio").getAsString());
            modelo.setCorreo(data.get("correo").getAsString());
            modelo.setRfc(data.get("rfc").getAsString());
            modelo.setDatos(data.get("datos_extra").getAsString());
            String dirImage=Dirs.findPic(dir,data.get("imagen").getAsString());
            modelo.setImagen(new FileInputStream(dirImage));
            modelo.setDirmagen(dirImage);
            
            return modelo;
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,e,methodName,"1IO");
            return null;
        }catch(IOException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,x,methodName,"2IO");
            return null;
        }catch(IllegalStateException n){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,n,methodName,"15");
            return null;
        }
    }
    
    /**
     * Se encarga de leer un archivo JSON, con la estructura de la tabla de proveedor. 
     * Devuelve los datos organizados en una clase llamada MvcForm3.
     * 
     * @param dir Nombre del archivo a leer.
     * 
     * @return los datos rescatados del archivo leído.
     */
    public MvcForm3 readDataProviderJson(String dir){
        methodName="readDataProviderJson";
        try{
            JsonElement je=JsonParser.parseReader(new JsonReader(new FileReader(dir,StandardCharsets.UTF_8)));
            Gson gson=new GsonBuilder().create();
            MvcForm3 modelo=new MvcForm3();
            
            var data=gson.toJsonTree(je).getAsJsonObject();
            
            modelo.setCodigo(data.get("codigo_prov").getAsInt());
            modelo.setNombre(data.get("nombre_prov").getAsString());
            modelo.setApellidoPaterno(data.get("apellidop_prov").getAsString());
            modelo.setApellidoMaterno(data.get("apellidom_prov").getAsString());
            modelo.setEmpresa(data.get("empresa").toString());
            modelo.setContacto(data.get("contato").getAsInt());
            String dirImage=Dirs.findPic(dir,data.get("iamgen").getAsString());
            modelo.setImagen(new FileInputStream(dirImage));
            modelo.setDirImagen(dirImage);
            
            return modelo;
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,e,methodName,"1IO");
            return null;
        }catch(IOException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,x,methodName,"2IO");
            return null;
        }catch(IllegalStateException n){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(frame,n,methodName,"15");
            return null;
        }
    }
}