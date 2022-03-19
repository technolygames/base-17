package clases;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;

import java.util.logging.Level;

/**
 * Clase intermedia entre el gestor de base de datos y el programa.
 * Se encarga de registrar, actualizar y eliminar los datos que el usuario desee.
 */
public class datos{
    protected Connection cn;
    protected PreparedStatement ps;
    protected Statement s;
    
    protected Properties p;
    
    protected String controlador;
    protected String ip;
    protected String puerto;
    protected String bd;
    protected String usuario;
    protected String contraseña;
    
    /**
     * Conexión a la base de datos.
     * 
     * @return Regresa la conexión a la base de datos.
     */
    public Connection getConnection(){
        p=new Properties();
        try{
            p.load(new FileInputStream("src/data/config/databaseInfo.properties"));
            
            controlador=p.getProperty("driver");
            ip=p.getProperty("ip");
            puerto=p.getProperty("port");
            bd=p.getProperty("database");
            usuario=p.getProperty("user");
            contraseña=p.getProperty("pass");
            
            Class.forName(controlador);
            cn=DriverManager.getConnection("jdbc:mysql://"+ip+":"+puerto+"/"+bd+"",usuario,contraseña);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 10",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 10: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'getConnection()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"getConnection-10",e.fillInStackTrace());
        }catch(ClassNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 37",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 37: "+x.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'getConnection()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"getConnection-37",x.fillInStackTrace());
        }catch(FileNotFoundException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+ñ.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'getConnection()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"getConnection-1IO",ñ.fillInStackTrace());
        }catch(IOException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+k.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'getConnection()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"getConnection-2IO",k.fillInStackTrace());
        }
        return cn;
    }
    
    /**
     * Guarda los datos de la ventana de productos en la base de datos.
     * 
     * @param codigoProducto Código de identificación del producto.
     * @param nombreProducto Nombre del producto.
     * @param marca Marca del producto.
     * @param cantidad Cantidad comprada de los productos.
     * @param precio Precio de cada uno de los productos.
     * @param total Precio total al que se vendieron los prodcutos.
     */
    public void insertarDatosProducto(int codigoProducto,String nombreProducto,String marca,int cantidad,int precio,int total){
        String ins1_query="insert into productos(codigo_prod,nombre_prod,marca,cantidad,precio,total,fecha_compra) values('"+codigoProducto+"','"+nombreProducto+"','"+marca+"','"+cantidad+"','"+precio+"','"+total+"',now())";
        try{
            s=getConnection().createStatement();
            s.addBatch(ins1_query);
            s.executeBatch();
            s.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosProducto()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"insertarDatosProducto-11",e.fillInStackTrace());
        }
    }
    
    /**
     * Guarda los datos de la ventana de almacén en la base de datos.
     * 
     * @param codigoProducto Código de identificación del almacén.
     * @param codigoLote Código de identificación del lote de productos que se almacenará.
     * @param codigoProveedor Código de identificación del proveedor de los productos.
     * @param nombreProducto Nombre del producto a almacenar.
     * @param marca Marca del producto que será almacenado.
     * @param cantidad Cantidad que es entraga y almacenada.
     * @param stock Disponibilidad del producto.
     */
    public void insertarDatosAlmacen(int codigoProducto,int codigoLote,int codigoProveedor,String nombreProducto,String marca,int cantidad,String stock){
        String ins2_query="insert into almacen values('"+codigoProducto+"','"+codigoLote+"','"+codigoProveedor+"','"+nombreProducto+"','"+marca+"','"+cantidad+"','"+stock+"',now())";
        try{
            s=getConnection()./*prepareStatement(ins2_query)*/createStatement();
            s.addBatch(ins2_query);
            s.executeBatch();
            s.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosAlmacen()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"insertarDatosAlmacen-11",e.fillInStackTrace());
        }
    }
    
    /**
     * Guarda los datos de la ventana de empleados en la base de datos.
     * 
     * @param password Contraseña asiganada para que el empleado pueda acceder al programa.
     * @param codigoEmpleado Código de identificación del empleado.
     * @param nombreEmpleado Nombre(s) del empleado.
     * @param apellidoPaternoEmpleado Apellido paterno del empleado.
     * @param apellidoMaternoEmpleado Apellido materno del empleado.
     * @param domicilio Domicilio del empleado.
     * @param puesto Puesto del empleado.
     * @param experiencia Experiencia (en años) del empleado; en caso de no tener, escribir "Nulo".
     * @param gradoEstudios Grado actual de estudios del empleado.
     * @param contacto Número telefónico del empleado.
     * @param edad Edad actual del empleado; en caso de cumplir años, este deberá de ser actualizado.
     * @param estado Estado actual en el negocio.
     * @param datosExtra Datos extras que el CV del empleado se quieran agregar.
     * @param foto Foto del empleado.
     */
    public void insertarDatosEmpleado(String password,int codigoEmpleado,String nombreEmpleado,String apellidoPaternoEmpleado,String apellidoMaternoEmpleado,String domicilio,String puesto,String experiencia,String gradoEstudios,int contacto,int edad,String estado,String datosExtra,InputStream foto){
        try{
            ps=getConnection().prepareStatement("insert into empleados(password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,domicilio,puesto,experiencia,grado_estudios,contacto,edad,estado,datos_extra,foto,fecha_registro,fecha_sesion) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now());");
            ps.setString(1,password);
            ps.setInt(2,codigoEmpleado);
            ps.setString(3,nombreEmpleado);
            ps.setString(4,apellidoPaternoEmpleado);
            ps.setString(5,apellidoMaternoEmpleado);
            ps.setString(6,domicilio);
            ps.setString(7,puesto);
            ps.setString(8,experiencia);
            ps.setString(9,gradoEstudios);
            ps.setInt(10,contacto);
            ps.setInt(11,edad);
            ps.setString(12,estado);
            ps.setString(13,datosExtra);
            ps.setBlob(14,foto);
            ps.executeUpdate("update empleados set fecha_registro=now(), fecha_sesion=now() where codigo_emp='"+codigoEmpleado+"';");
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosEmpleado()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"insertarDatosEmpleado-11",e.fillInStackTrace());
        }
    }
    
    /**
     * Guarda los datos de la ventana de socios en la base de datos.
     * 
     * @param codigoSocio Código de identificación del socio.
     * @param nombreSocio Nombre(s) del socio.
     * @param apellidopSocio Apellido paterno del socio.
     * @param apellidomSocio Apellido materno del socio.
     * @param tipoSocio Tipo de afiliación.
     * @param datosExtra Datos extra que se quieran agregar como descripción del socio.
     * @param foto Foto del socio para identificarlo.
     */
    public void insertarDatosSocio(int codigoSocio,String nombreSocio,String apellidopSocio,String apellidomSocio,String tipoSocio,String datosExtra,InputStream foto){
        String ins4_query="insert into socios(codigo_part,nombre_part,apellidop_part,apellidom_part,tipo_socio,datos_extra,foto,fecha_ingreso,fecha_ucompra) values(?,?,?,?,?,?,?,now(),now());";
        try{
            ps=getConnection().prepareStatement(ins4_query);
            ps.setInt(1,codigoSocio);
            ps.setString(2,nombreSocio);
            ps.setString(3,apellidopSocio);
            ps.setString(4,apellidomSocio);
            ps.setString(5,tipoSocio);
            ps.setString(6,datosExtra);
            ps.setBinaryStream(7,foto);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosSocio()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"insertarDatosSocio-11",e.fillInStackTrace());
        }
    }
    
    /**
     * Guarda los datos de la ventana de proveedor en la base de datos.
     * 
     * @param codigoProveedor Código de identificación del proveedor.
     * @param nombreProveedor Nombre(s) del proveedor.
     * @param apellidoPaternoProvedor Apellido paterno del proveedor.
     * @param apellidoMaternoProveedor Apellido materno del proveedor.
     * @param empresa Empresa procedencia del proveedor.
     * @param contacto Número de contacto del proveedor.
     * @param foto Foto del proveedor para identificarlo.
     */
    public void insertarDatosProveedor(int codigoProveedor,String nombreProveedor,String apellidoPaternoProvedor,String apellidoMaternoProveedor,String empresa,int contacto,InputStream foto){
        String ins5_query="insert into(codigo_prov,nombre_prov,apellidop_prov,apellidom_prov,empresa,contacto,foto,fecha_ingreso,fecha_uentrega) proveedor value(?,?,?,?,?,?,?,now(),now());";
        try{
            ps=getConnection().prepareStatement(ins5_query);
            ps.setInt(1,codigoProveedor);
            ps.setString(2,nombreProveedor);
            ps.setString(3,apellidoPaternoProvedor);
            ps.setString(4,apellidoMaternoProveedor);
            ps.setString(5,empresa);
            ps.setInt(6,contacto);
            ps.setBinaryStream(7,foto);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosProveedor()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"insertarDatosProveedor-11",e.fillInStackTrace());
        }
    }
    
    /**
     * Actualiza los datos de la tabla de empleados.
     * Esta es específica para empleados. No usar como universal.
     * 
     * Para que se pueda usar esta clase, se debe usar la sintaxis que estará en la documentación del programa
     * 
     * @param consulta datos que serán modificados
     */
    public void actualizarDatosEmpleado(String consulta){
        String up1_query="update empleados "+consulta;
        try{
            ps=getConnection().prepareStatement(up1_query);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 12",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 12: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'actualizarDatosEmpleado()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.SEVERE,"actualizarDatosEmpleado-12",e.fillInStackTrace());
        }
    }
    
    /**
     * Actualiza los datos de la tabla de socios.
     * Esta es específica para socios. No usar como universal.
     * 
     * Para que se pueda usar esta clase, se debe usar la sintaxis que estará en la documentación del programa
     * 
     * @param consulta datos que serán modificados
     */
    public void actualizarDatosSocio(String consulta){
        String up2_query="update socios "+consulta;
        try{
            ps=getConnection().prepareStatement(up2_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 12",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 12: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'actualizarDatosSocio()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"actualizarDatosSocio-12",e.fillInStackTrace());
        }
    }
    
    public void actualizarDatosProveedor(String consulta){
        String up2_query="update proveedor "+consulta;
        try{
            ps=getConnection().prepareStatement(up2_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 12",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 12: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'actualizarDatosSocio()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"actualizarDatosProveedor-12",e.fillInStackTrace());
        }
    }
    
    /**
     * Elimina los datos especificados en la tabla 'Empleados'.
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de despido del negocio.
     * 
     * @param codigoEmpleado Código del empleado al que se eliminarán los datos.
     */
    public void eliminarDatosEmpleado(int codigoEmpleado){
        String del1_query="delete from empleados where codigo_emp='"+codigoEmpleado+"';";
        try{
            ps=getConnection().prepareStatement(del1_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 13",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 13: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'eliminarDatosEmpleado()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.SEVERE,"eliminarDatosEmpleado-13",e.fillInStackTrace());
        }
    }
    
    /**
     * Elimina los datos especificados en la tabla 'Socios'.
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de desafiliación.
     * 
     * @param codigoSocio Código del socio al que se eliminarán los datos.
     */
    public void eliminarDatosSocio(int codigoSocio){
        String del2_query="delete from socios where codigo_part='"+codigoSocio+"';";
        try{
            ps=getConnection().prepareStatement(del2_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 13",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 13: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'eliminarDatosSocio()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"eliminarDatosSocio-13",e.fillInStackTrace());
        }
    }
    
    /**
     * Elimina los datos especificados en la tabla 'Proveedor.
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de despido de la empresa de origen.
     * 
     * @param codigoProveedor Código del proveedor al que se eliminarán los datos.
     */
    public void eliminarDatosProveedor(int codigoProveedor){
        String del3_query="delete from proveedor where codigo_prov='"+codigoProveedor+"';";
        try{
            ps=getConnection().prepareStatement(del3_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 13",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 13: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'eliminarDatosProveedor()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"eliminarDatosProveedor-13",e.fillInStackTrace());
        }
    }
}