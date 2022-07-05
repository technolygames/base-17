package main;
//clases
import clases.makeDirs;
import venPrimarias.start;

public class main{
    public static void main(String[] args){
        new start().setVisible(true);
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/config");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/databackup");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/databackup/Empleados");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/databackup/Proveedores");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/databackup/Socios");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/generic/Jasper");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/generic/tickets");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/libs");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/logs/exceptions");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/logs/static");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/media");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/media/forms");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/media/forms/copy/");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/media/icon");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/media/icon/copy/");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/media/secondary");
    }
}