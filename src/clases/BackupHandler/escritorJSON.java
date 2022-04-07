/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.BackupHandler;

import com.google.gson.stream.JsonWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * Esta clase se encarga de crear archivos JSON para guardar datos
 * 
 * @author erick
 */
public class escritorJSON{
    protected JsonWriter jsonw;
    
    protected void storeDataJson(){
        try{
            jsonw=new JsonWriter(new OutputStreamWriter(new FileOutputStream("src/data/dataBackup/Prueba-"+(int)(Math.random()*10000)+".json"),StandardCharsets.UTF_8));
            jsonw.beginObject();
            jsonw.setIndent("   ");
            jsonw.name("contraseña").value("");
            jsonw.endObject();
            
            jsonw.flush();
            jsonw.close();
        }catch(IOException e){
            e.fillInStackTrace();
        }catch(IllegalStateException x){
            x.fillInStackTrace();
        }
    }
}