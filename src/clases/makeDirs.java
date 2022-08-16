package clases;

import java.io.File;

public class makeDirs{
    public void makeDir(String dir){
        File direccion=new File(dir);
        if(!direccion.exists()){
            direccion.mkdir();
        }
    }
}