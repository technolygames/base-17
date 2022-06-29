package main;
//clases
import clases.makeDirs;
import venPrimarias.start;

public class main{
    public static void main(String[] args){
        new start().setVisible(true);
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/main/resources/data/config");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/main/resources/data/databackup");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/main/resources/data/databackup/Empleados");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/main/resources/data/databackup/Proveedores");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/main/resources/data/databackup/Socios");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/main/resources/data/generic/Jasper");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/main/resources/data/generic/tickets");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/main/resources/data/libs");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/main/resources/data/logs/exceptions");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/main/resources/data/logs/static");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/main/resources/data/media");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/main/resources/data/media/forms");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/main/resources/data/media/icon");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/main/resources/data/media/icon/copy/");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/main/resources/data/media/forms/copy/");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/main/resources/data/media/secondary");
    }
}