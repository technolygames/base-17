package main;
//clases
import clases.dirs;
import venPrimarias.start;
//java
import java.awt.EventQueue;

public class main{
    public static void main(String[] args){
        String[] dirs={
            "data/config",
            "data/databackup",
            "data/databackup/Empleados",
            "data/databackup/Proveedores",
            "data/databackup/Socios",
            "data/generic/Jasper",
            "data/generic/tickets",
            "data/libs",
            "data/logs/exceptions",
            "data/logs/static",
            "data/media",
            "data/media/forms",
            "data/media/forms/copy/",
            "data/media/icon",
            "data/media/icon/copy/",
            "data/media/secondary"
        };
        
        for(String dir:dirs){
            new dirs().makeDir(dir);
        }
        
        EventQueue.invokeLater(()->{
            new start().setVisible(true);
        });
    }
}