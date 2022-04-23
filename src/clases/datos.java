package clases;
//clases
import venPrimarias.start;
//java
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

/**
 * Clase intermedia entre el gestor de base de datos y el programa.
 * Se encarga de registrar, actualizar y eliminar los datos que el usuario desee.
 * 
 * @author erick
 */
public class datos{
    protected Statement s;
    protected Connection cn;
    protected PreparedStatement ps;
    
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
            p.load(new FileInputStream(System.getProperty("user.dir")+"/src/data/config/databaseInfo.properties"));
            
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
            new logger().staticLogger("Error 10: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'getConnection()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"getConnection-10",e.fillInStackTrace());
        }catch(ClassNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 37",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 37: "+x.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'getConnection()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"getConnection-37",x.fillInStackTrace());
        }catch(FileNotFoundException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+ñ.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'getConnection()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"getConnection-1IO",ñ.fillInStackTrace());
        }catch(IOException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+k.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'getConnection()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"getConnection-2IO",k.fillInStackTrace());
        }
        return cn;
    }
    
    /**
     * Crea la base de datos que se usará para importar la base de datos.
     * Advertencia: no usar en otras clases.
     * 
     * @param nombre Nombre de la base de datos.
     */
    public void crearBD(String nombre){
        try{
            ps=getConnection().prepareStatement("create database "+nombre+";");
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se creó la base de datos, pero falta importar la base","Rel 1E",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 1E: se creó correctamente la base de datos.\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'crearBD()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 5E",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 5E: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'crearBD()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"crearBD-5E",e.fillInStackTrace());
        }
    }
    
    /**
     * Guarda los datos de la ventana de productos en la base de datos.
     * 
     * @param codigoProducto Código de identificación del producto.
     * @param codigoEmpleado Código de identificación del empleado que atendió la venta.
     * @param nombreProducto Nombre del producto.
     * @param marca Marca del producto.
     * @param cantidad Cantidad comprada de los productos.
     * @param precio Precio de cada uno de los productos.
     * @param total Precio total al que se vendieron los prodcutos.
     */
    public void insertarDatosProducto(int codigoProducto,int codigoEmpleado,String nombreProducto,String marca,int cantidad,int precio,int total){
        String ins1_query="insert into productos values('"+codigoProducto+"','"+codigoEmpleado+"','"+nombreProducto+"','"+marca+"','"+cantidad+"','"+precio+"','"+total+"',now())";
        try{
            s=getConnection().createStatement();
            s.addBatch(ins1_query);
            s.executeBatch();
            
            s.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosProducto()'",Level.WARNING);
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
     * @param precioUnitario Precio de cada producto del lote.
     * @param stock Disponibilidad del producto.
     */
    public void insertarDatosAlmacen(int codigoProducto,int codigoLote,int codigoProveedor,String nombreProducto,String marca,int cantidad,int precioUnitario,String stock){
        String ins2_query="insert into almacen values('"+codigoProducto+"','"+codigoLote+"','"+codigoProveedor+"','"+nombreProducto+"','"+marca+"','"+cantidad+"','"+precioUnitario+"','"+stock+"',now())";
        try{
            s=getConnection().createStatement();
            s.addBatch(ins2_query);
            s.executeBatch();
            
            s.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosAlmacen()'",Level.WARNING);
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
    public void insertarDatosEmpleado(String password,int codigoEmpleado,String nombreEmpleado,String apellidoPaternoEmpleado,String apellidoMaternoEmpleado,String domicilio,String puesto,int experiencia,String gradoEstudios,int contacto,int edad,String estado,String datosExtra,InputStream foto){
        try{
            ps=getConnection().prepareStatement("insert into empleados(password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,domicilio,puesto,experiencia,grado_estudios,contacto,edad,estado,datos_extra,foto,fecha_registro,fecha_sesion) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now());");
            ps.setString(1,password);
            ps.setInt(2,codigoEmpleado);
            ps.setString(3,nombreEmpleado);
            ps.setString(4,apellidoPaternoEmpleado);
            ps.setString(5,apellidoMaternoEmpleado);
            ps.setString(6,domicilio);
            ps.setString(7,puesto);
            ps.setInt(8,experiencia);
            ps.setString(9,gradoEstudios);
            ps.setInt(10,contacto);
            ps.setInt(11,edad);
            ps.setString(12,estado);
            ps.setString(13,datosExtra);
            ps.setBlob(14,foto);
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 1: se guardaron correctamente los datos a la base de datos.\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosEmpleado()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosEmpleado()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"insertarDatosEmpleado-11",e.fillInStackTrace());
        }
    }
    
    /**
     * Guarda los datos de la ventana de socios en la base de datos.
     * 
     * @param codigoSocio Código de identificación del socio.
     * @param nombreSocio Nombre(s) del socio.
     * @param apellidoPaternoSocio Apellido paterno del socio.
     * @param apellidoMaternoSocio Apellido materno del socio.
     * @param tipoSocio Tipo de afiliación.
     * @param correo Correo de contacto del empleado.
     * @param rfc RFC para tramitar factura.
     * @param datosExtra Datos extra que se quieran agregar como descripción del socio.
     * @param foto Foto del socio para identificarlo.
     */
    public void insertarDatosSocio(int codigoSocio,String nombreSocio,String apellidoPaternoSocio,String apellidoMaternoSocio,String tipoSocio,String correo,String rfc,String datosExtra,InputStream foto){
        try{
            ps=getConnection().prepareStatement("insert into socios(codigo_part,nombre_part,apellidop_part,apellidom_part,tipo_socio,correo,rfc,datos_extra,foto,fecha_ingreso,fecha_ucompra) values(?,?,?,?,?,?,?,?,?,now(),now());");
            ps.setInt(1,codigoSocio);
            ps.setString(2,nombreSocio);
            ps.setString(3,apellidoPaternoSocio);
            ps.setString(4,apellidoMaternoSocio);
            ps.setString(5,tipoSocio);
            ps.setString(6,correo);
            ps.setString(7,rfc);
            ps.setString(8,datosExtra);
            ps.setBinaryStream(9,foto);
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 1: se guardaron correctamente los datos a la base de datos.\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosSocio()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosSocio()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"insertarDatosSocio-11",e.fillInStackTrace());
        }
    }
    
    /**
     * Guarda los datos de la ventana de proveedor en la base de datos.
     * 
     * @param codigoProveedor Código de identificación del proveedor.
     * @param nombreProveedor Nombre(s) del proveedor.
     * @param apellidoPaternoProveedor Apellido paterno del proveedor.
     * @param apellidoMaternoProveedor Apellido materno del proveedor.
     * @param empresa Empresa procedencia del proveedor.
     * @param contacto Número de contacto del proveedor.
     * @param foto Foto del proveedor para identificarlo.
     */
    public void insertarDatosProveedor(int codigoProveedor,String nombreProveedor,String apellidoPaternoProveedor,String apellidoMaternoProveedor,String empresa,int contacto,InputStream foto){
        try{
            ps=getConnection().prepareStatement("insert into proveedor(codigo_prov,nombre_prov,apellidop_prov,apellidom_prov,empresa,contacto,foto,fecha_ingreso,fecha_uentrega) value(?,?,?,?,?,?,?,now(),now());");
            ps.setInt(1,codigoProveedor);
            ps.setString(2,nombreProveedor);
            ps.setString(3,apellidoPaternoProveedor);
            ps.setString(4,apellidoMaternoProveedor);
            ps.setString(5,empresa);
            ps.setInt(6,contacto);
            ps.setBinaryStream(7,foto);
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 1: se guardaron correctamente los datos a la base de datos.\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosProveedor()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosProveedor()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"insertarDatosProveedor-11",e.fillInStackTrace());
        }
    }
    
    /**
     * Guarda los datos de la ventana de promociones en la base de datos.
     * 
     * @param codigoPromo Código de la promoción.
     * @param nombrePromo Nombre de la promoción.
     * @param datosPromo Datos descriptivos de la promoción para que sea válida.
     * @param descuento Porcentaje de descuento.
     * @param inicio Fecha de inicio de la promoción.
     * @param fin Fecha de finalización de la promoción.
     */
    public void insertarDatosPromo(String codigoPromo,String nombrePromo,String datosPromo,float descuento,java.sql.Date inicio,java.sql.Date fin){
        try{
            ps=getConnection().prepareStatement("insert into promociones(id_prom,nombre_prom,datos_prom,descuento,inicio,fin) values('"+codigoPromo+"','"+nombrePromo+"','"+datosPromo+"','"+descuento+"','"+inicio+"','"+fin+"');");
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 1: se guardaron correctamente los datos a la base de datos.\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosPromo()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosPromo()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"insertarDatosPromo-11",e.fillInStackTrace());
        }
    }
    
    /**
     * Guarda los datos para conteo de asistencia del empleado.
     * 
     * @param codigoEmpleado 
     * @param nombreEmpleado 
     * @param apellidoPaternoEmpleado 
     * @param apellidoMaternoEmpleado 
     */
    public void insertarDatosConteo(int codigoEmpleado,String nombreEmpleado,String apellidoPaternoEmpleado,String apellidoMaternoEmpleado){
        try{
            ps=getConnection().prepareStatement("insert into conteo(codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,fecha_sesion) values('"+codigoEmpleado+"','"+nombreEmpleado+"','"+apellidoPaternoEmpleado+"','"+apellidoMaternoEmpleado+"',now());");
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 1: se guardaron correctamente los datos a la base de datos.\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosConteo()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosConteo()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"insertarDatosConteo-11",e.fillInStackTrace());
        }
    }
    
    /**
     * Guarda los datos para conteo de asistencia del empleado.
     * 
     * @param codigoEmpleado 
     * @param nombreEmpleado 
     * @param apellidoPaternoEmpleado 
     * @param apellidoMaternoEmpleado 
     * @param numeroVentas 
     * @param numeroAcciones 
     * 
     * Nota: este método es solo para cargar datos de respaldo eliminados previamente.
     */
    public void insertarDatosConteo(int codigoEmpleado,String nombreEmpleado,String apellidoPaternoEmpleado,String apellidoMaternoEmpleado,int numeroVentas,int numeroAcciones){
        try{
            ps=getConnection().prepareStatement("insert into conteo(codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,no_ventas,no_acciones,fecha_sesion) values('"+codigoEmpleado+"','"+nombreEmpleado+"','"+apellidoPaternoEmpleado+"','"+apellidoMaternoEmpleado+"','"+numeroVentas+"','"+numeroAcciones+"',now());");
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 1: se guardaron correctamente los datos a la base de datos.\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosConteo()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosConteo()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"insertarDatosConteo-11",e.fillInStackTrace());
        }
    }
    
    /**
     * Actualiza datos de la tabla de empleados.
     * Esta es específica para empleados. No usar como universal.
     * 
     * Para que se pueda usar esta clase, se debe usar la sintaxis que está en la documentación del programa.
     * 
     * @param consulta datos que serán modificados.
     */
    public void actualizarDatosEmpleado(String consulta){
        try{
            ps=getConnection().prepareStatement("update empleados "+consulta);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 2: se actualizaron correctamente los datos.\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'actualizarDatosEmpleado()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 12",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 12: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'actualizarDatosEmpleado()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.SEVERE,"actualizarDatosEmpleado-12",e.fillInStackTrace());
        }
    }
    
    /**
     * Actualiza datos de la tabla de socios.
     * Esta es específica para socios. No usar como universal.
     * 
     * Para que se pueda usar esta clase, se debe usar la sintaxis que está en la documentación del programa.
     * 
     * @param consulta datos que serán modificados.
     */
    public void actualizarDatosSocio(String consulta){
        try{
            ps=getConnection().prepareStatement("update socios "+consulta);
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 2: se actualizaron correctamente los datos.\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'actualizarDatosSocio()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
        
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 12",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 12: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'actualizarDatosSocio()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"actualizarDatosSocio-12",e.fillInStackTrace());
        }
    }
    
    /**
     * Actualiza datos de la tabla de proveedores.
     * Esta es específica para socios. No usar como universal.
     * 
     * Para que se pueda usar esta clase, se debe usar la sintaxis que está en la documentación del programa.
     * 
     * @param consulta datos que serán modificados.
     */
    public void actualizarDatosProveedor(String consulta){
        try{
            ps=getConnection().prepareStatement("update proveedor "+consulta);
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 2: se actualizaron correctamente los datos.\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'actualizarDatosProveedor()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 12",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 12: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'actualizarDatosProveedor()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"actualizarDatosProveedor-12",e.fillInStackTrace());
        }
    }
    
    /**
     * Actualiza datos de la tabla de conteo.
     * Esta es específica para socios. No usar como universal.
     * 
     * Para que se pueda usar esta clase, se debe usar la sintaxis que está en la documentación del programa.
     * 
     * @param consulta datos que serán modificados.
     */
    public void actualizarDatosConteo(String consulta){
        try{
            ps=getConnection().prepareStatement("update conteo "+consulta);
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 2: se actualizaron correctamente los datos.\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'actualizarDatosConteo()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 12",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 12: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'actualizarDatosConteo()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"actualizarDatosConteo-12",e.fillInStackTrace());
        }
    }
    
    /**
     * Elimina datos específicos de la tabla empleados.
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de despido del negocio.
     * Se pueden reestablecer si previamente se creó la copia de seguridad.
     * 
     * @param codigoEmpleado Código del empleado al que se eliminarán los datos.
     */
    public void eliminarDatosEmpleado(int codigoEmpleado){
        try{
            ps=getConnection().prepareStatement("delete from empleados where codigo_emp='"+codigoEmpleado+"';");
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 3: se eliminaron correctamente los datos de la base de datos.\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'eliminarDatosEmpleado()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 13",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 13: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'eliminarDatosEmpleado()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.SEVERE,"eliminarDatosEmpleado-13",e.fillInStackTrace());
        }
    }
    
    /**
     * Elimina datos específicos de la tabla socios.
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de desafiliación.
     * Se pueden reestablecer si previamente se creó la copia de seguridad.
     * 
     * @param codigoSocio Código del socio al que se eliminarán los datos.
     */
    public void eliminarDatosSocio(int codigoSocio){
        try{
            ps=getConnection().prepareStatement("delete from socios where codigo_part='"+codigoSocio+"';");
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 3: se eliminaron correctamente los datos de la base de datos.\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'eliminarDatosSocio()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 13",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 13: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'eliminarDatosSocio()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"eliminarDatosSocio-13",e.fillInStackTrace());
        }
    }
    
    /**
     * Elimina datos específicos de la tabla proveedor.
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de despido de la empresa de origen.
     * Se pueden reestablecer si previamente se creó la copia de seguridad.
     * 
     * @param codigoProveedor Código del proveedor al que se eliminarán los datos.
     */
    public void eliminarDatosProveedor(int codigoProveedor){
        String del3_query="delete from proveedor where codigo_prov='"+codigoProveedor+"';";
        try{
            ps=getConnection().prepareStatement(del3_query);
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 3: se eliminaron correctamente los datos de la base de datos.\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'eliminarDatosProveedor()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 13",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 13: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'eliminarDatosProveedor()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"eliminarDatosProveedor-13",e.fillInStackTrace());
        }
    }
    
    /**
     * Elimina datos específicos de la tabla conteo.
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de despido de la empresa de origen.
     * Se pueden reestablecer si previamente se creó la copia de seguridad.
     * 
     * @param codigoEmpleado Código del proveedor al que se eliminarán los datos.
     */
    public void eliminarDatosConteo(int codigoEmpleado){
        try{
            ps=getConnection().prepareStatement("delete from conteo where codigo_emp='"+codigoEmpleado+"';");
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 3: se eliminaron correctamente los datos de la base de datos.\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'eliminarDatosConteo()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 13",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 13: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'eliminarDatosConteo()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"eliminarDatosConteo-13",e.fillInStackTrace());
        }
    }
}