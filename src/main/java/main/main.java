package main;
//clases
import clases.datos;
import clases.dirs;
import venPrimarias.start;

public class main{
    protected static String userdir=datos.userdir;
    public static void main(String[] args){
        new start().setVisible(true);
        new dirs().makeDir(userdir+"/data/config");
        new dirs().makeDir(userdir+"/data/databackup");
        new dirs().makeDir(userdir+"/data/databackup/Empleados");
        new dirs().makeDir(userdir+"/data/databackup/Proveedores");
        new dirs().makeDir(userdir+"/data/databackup/Socios");
        new dirs().makeDir(userdir+"/data/generic/Jasper");
        new dirs().makeDir(userdir+"/data/generic/tickets");
        new dirs().makeDir(userdir+"/data/libs");
        new dirs().makeDir(userdir+"/data/logs/exceptions");
        new dirs().makeDir(userdir+"/data/logs/static");
        new dirs().makeDir(userdir+"/data/media");
        new dirs().makeDir(userdir+"/data/media/forms");
        new dirs().makeDir(userdir+"/data/media/forms/copy/");
        new dirs().makeDir(userdir+"/data/media/icon");
        new dirs().makeDir(userdir+"/data/media/icon/copy/");
        new dirs().makeDir(userdir+"/data/media/secondary");
    }
}