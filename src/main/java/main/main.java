package main;
//clases
import clases.dirs;
import venPrimarias.start;

public class main{
    protected static String userdir=dirs.userdir;
    public static void main(String[] args){
        String[] dirs={
            userdir+"/data/config",
            userdir+"/data/databackup",
            userdir+"/data/databackup/Empleados",
            userdir+"/data/databackup/Proveedores",
            userdir+"/data/databackup/Socios",
            userdir+"/data/generic/Jasper",
            userdir+"/data/generic/tickets",
            userdir+"/data/libs",
            userdir+"/data/logs/exceptions",
            userdir+"/data/logs/static",
            userdir+"/data/media",
            userdir+"/data/media/forms",
            userdir+"/data/media/forms/copy/",
            userdir+"/data/media/icon",
            userdir+"/data/media/icon/copy/",
            userdir+"/data/media/secondary"
        };
        
        for(String dir:dirs){
            new dirs().makeDir(dir);
        }
        
        new start().setVisible(true);
    }
}