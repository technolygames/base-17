package clases;
//clases
import clases.mvc.MvcForm01;
import clases.mvc.MvcForm02;
import venPrimarias.start;
//java
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

/**
 * Clase intermedia entre el gestor de base de datos y el programa.<br>
 * Se encarga de registrar, actualizar y eliminar los datos que el usuario desee.
 * 
 * @author erick
 */
public class Datos1{
    protected PreparedStatement ps;
    
    protected Properties p;
    
    protected String db;
    protected String driver;
    protected String ip;
    protected String pass;
    protected String port;
    protected String user;
    
    /**
     * Método para conectar a la base de datos.
     * 
     * @return conexión a la base de datos.
     */
    public Connection getConnection(){
        String methodName="getConnection";
        p=new Properties();
        try{
            p.load(new FileInputStream("data/config/databaseInfo.properties"));
            
            db=p.getProperty("database");
            driver=p.getProperty("driver");
            ip=p.getProperty("ip");
            pass=p.getProperty("pass");
            port=p.getProperty("port");
            user=p.getProperty("user");
            
            Class.forName(driver);
            return DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+db+"?serverTimezone=UTC",user,pass);
        }catch(SQLException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,Datos1.class.getName(),methodName,"10");
            return null;
        }catch(ClassNotFoundException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,Datos1.class.getName(),methodName,"37");
            return null;
        }catch(FileNotFoundException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,n,Datos1.class.getName(),methodName,"1IO");
            return null;
        }catch(IOException k){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,k,Datos1.class.getName(),methodName,"2IO");
            return null;
        }
    }
    
    /**
     * Crea la base de datos que se usará para importar el archivo en el que está la base de datos.
     * Advertencia: no usar en otras clases.
     * 
     * @param nombre Nombre de la base de datos.
     * @throws SQLException si el gestor gestor detecta un problema, lanza este error.
     */
    public void crearBD(String nombre) throws SQLException{
        ps=getConnection().prepareStatement("create database "+nombre+";");
        ps.execute();
        
        JOptionPane.showMessageDialog(null,"Se creó la base de datos, pero falta importar la base","Rel 1E",JOptionPane.INFORMATION_MESSAGE);
        new logger(Level.INFO).staticLogger("Rel 1E: se creó correctamente la base de datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'crearBD()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        
        ps.close();
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
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void insertarDatosProducto(int codigoProducto,int codigoEmpleado,String nombreProducto,String marca,int cantidad,int precio,int total) throws SQLException{
        ps=getConnection().prepareStatement("insert into productos values(?,?,?,?,?,?,?,now())");
        
        ps.setInt(1,codigoProducto);
        ps.setInt(2,codigoEmpleado);
        ps.setString(3,nombreProducto);
        ps.setString(4,marca);
        ps.setInt(5,cantidad);
        ps.setInt(6,precio);
        ps.setInt(7,total);
        ps.addBatch();
        
        ps.executeBatch();
        
        ps.close();
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
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void insertarDatosAlmacen(int codigoProducto,int codigoLote,int codigoProveedor,String nombreProducto,String marca,int cantidad,int precioUnitario,String stock) throws SQLException{
        ps=getConnection().prepareStatement("insert into almacen values(?,?,?,?,?,?,?,?,now())");
        
        ps.setInt(1,codigoProducto);
        ps.setInt(2,codigoLote);
        ps.setInt(3,codigoProveedor);
        ps.setString(4,nombreProducto);
        ps.setString(5,marca);
        ps.setInt(6,cantidad);
        ps.setInt(7,precioUnitario);
        ps.setString(8,stock);
        ps.addBatch();
        
        ps.executeBatch();
        
        ps.close();
    }
    
    /**
     * Guarda los datos de la ventana de empleados en la base de datos.
     * 
     * @param datos que serán almacenados del empleado en la base de datos.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void insertarDatosEmpleado(List<MvcForm01> datos) throws SQLException{
        ps=getConnection().prepareStatement("insert into empleados values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now());");
        ps.setString(1,datos.get(0).getPassword());
        ps.setInt(2,datos.get(0).getCodigo());
        ps.setString(3,datos.get(0).getNombre());
        ps.setString(4,datos.get(0).getApellidoPaterno());
        ps.setString(5,datos.get(0).getApellidoMaterno());
        ps.setString(6,datos.get(0).getCurp());
        ps.setString(7,datos.get(0).getDomicilio());
        ps.setString(8,datos.get(0).getPuesto());
        ps.setInt(9,datos.get(0).getExperiencia());
        ps.setString(10,datos.get(0).getGradoEstudios());
        ps.setInt(11,datos.get(0).getContacto());
        ps.setString(12,datos.get(0).getFechaNacimiento());
        ps.setInt(13,datos.get(0).getEdad());
        ps.setString(14,datos.get(0).getEstado());
        ps.setString(15,datos.get(0).getDatosExtra());
        ps.setBlob(16,datos.get(0).getImagen());
        ps.execute();
        
        JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
        new logger(Level.INFO).staticLogger("Rel 1: se guardaron correctamente los datos a la base de datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'insertarDatosEmpleado()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        
        datos.clear();
        ps.close();
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
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void insertarDatosProveedor(int codigoProveedor,String nombreProveedor,String apellidoPaternoProveedor,String apellidoMaternoProveedor,String empresa,int contacto,InputStream foto) throws SQLException{
        ps=getConnection().prepareStatement("insert into proveedor value(?,?,?,?,?,?,?,now(),now());");
        ps.setInt(1,codigoProveedor);
        ps.setString(2,nombreProveedor);
        ps.setString(3,apellidoPaternoProveedor);
        ps.setString(4,apellidoMaternoProveedor);
        ps.setString(5,empresa);
        ps.setInt(6,contacto);
        ps.setBinaryStream(7,foto);
        ps.execute();
        
        JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
        new logger(Level.INFO).staticLogger("Rel 1: se guardaron correctamente los datos a la base de datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'insertarDatosProveedor()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        
        ps.close();
    }
    
    /**
     * Guarda los datos de la ventana de socios en la base de datos.
     * 
     * @param datos que serán almacenados del socio en la base de datos.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void insertarDatosSocio(List<MvcForm02> datos) throws SQLException{
        ps=getConnection().prepareStatement("insert into socios values(?,?,?,?,?,?,?,?,?,now(),now());");
        ps.setInt(1,datos.get(0).getCodigo());
        ps.setString(2,datos.get(0).getNombre());
        ps.setString(3,datos.get(0).getApellidoPaterno());
        ps.setString(4,datos.get(0).getApellidoMaterno());
        ps.setString(5,datos.get(0).getTipo());
        ps.setString(6,datos.get(0).getCorreo());
        ps.setString(7,datos.get(0).getRfc());
        ps.setString(8,datos.get(0).getDatos());
        ps.setBinaryStream(9,datos.get(0).getImagen());
        ps.execute();
        
        JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
        new logger(Level.INFO).staticLogger("Rel 1: se guardaron correctamente los datos a la base de datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'insertarDatosSocio()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        
        datos.clear();
        ps.close();
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
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void insertarDatosPromo(String codigoPromo,String nombrePromo,String datosPromo,float descuento,java.sql.Date inicio,java.sql.Date fin) throws SQLException{
        ps=getConnection().prepareStatement("insert into promociones(id_prom,nombre_prom,datos_prom,descuento,inicio,fin) values(?,?,?,?,?,?);");
        ps.setString(1,codigoPromo);
        ps.setString(2,nombrePromo);
        ps.setString(3,datosPromo);
        ps.setFloat(4,descuento);
        ps.setDate(5,inicio);
        ps.setDate(6,fin);
        ps.execute();
        
        JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
        new logger(Level.INFO).staticLogger("Rel 1: se guardaron correctamente los datos a la base de datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'insertarDatosPromo()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        
        ps.close();
    }
    
    /**
     * Guarda los datos para conteo de asistencia del empleado.
     * 
     * @param codigoEmpleado Código de identificación del empleado.
     * @param nombreEmpleado Nombre(s) del empleado.
     * @param apellidoPaternoEmpleado Apellido paterno del empleado.
     * @param apellidoMaternoEmpleado Apellido materno del empleado.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void insertarDatosConteo(int codigoEmpleado,String nombreEmpleado,String apellidoPaternoEmpleado,String apellidoMaternoEmpleado) throws SQLException{
        ps=getConnection().prepareStatement("insert into conteo(codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,no_ventas,fecha_sesion) values(?,?,?,?,0,now());");
        ps.setInt(1,codigoEmpleado);
        ps.setString(2,nombreEmpleado);
        ps.setString(3,apellidoPaternoEmpleado);
        ps.setString(4,apellidoMaternoEmpleado);
        ps.execute();
        
        new logger(Level.INFO).staticLogger("Rel 1: se guardaron correctamente los datos a la base de datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'insertarDatosConteo1()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        
        ps.close();
    }
    
    /**
     * Guarda los datos para conteo de asistencia del empleado.
     * 
     * @param codigoEmpleado Código de identificación del empleado.
     * @param nombreEmpleado Nombre(s) del empleado.
     * @param apellidoPaternoEmpleado Apellido paterno del empleado.
     * @param apellidoMaternoEmpleado Apellido materno del empleado.
     * @param numeroVentas Cantidad de ventas realizadas por el empleado.
     * 
     * Nota: este método es solo para cargar datos de respaldo eliminados previamente.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void insertarDatosConteo(int codigoEmpleado,String nombreEmpleado,String apellidoPaternoEmpleado,String apellidoMaternoEmpleado,int numeroVentas) throws SQLException{
        ps=getConnection().prepareStatement("insert into conteo(codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,no_ventas,fecha_sesion) values(?,?,?,?,?,now());");
        ps.setInt(1,codigoEmpleado);
        ps.setString(2,nombreEmpleado);
        ps.setString(3,apellidoPaternoEmpleado);
        ps.setString(4,apellidoMaternoEmpleado);
        ps.setInt(5,numeroVentas);
        ps.execute();
        
        new logger(Level.INFO).staticLogger("Rel 1: se guardaron correctamente los datos a la base de datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'insertarDatosConteo2()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        
        ps.close();
    }
    
    /**
     * Verifica si los datos para loggear son correctos.
     * 
     * @param password del usuario que iniciará sesión.
     * @param user1 usuario que iniciará sesión.
     * 
     * @return el resultado de la consulta.
     * 
     * @throws SQLException en caso de que el gestor detecte algún problema en el ingreso del registro.
     */
    public ResultSet login(String password,String user1) throws SQLException{
        ps=getConnection().prepareStatement("select * from empleados where password=? and nombre_emp=? or curp=?;");
        ps.setString(1,password);
        ps.setString(2,user1);
        ps.setString(3,user1);
        
        return ps.executeQuery();
    }
    
    /**
     * Busca los datos de la promoción en la base de datos.
     * 
     * @param idPromo a buscar. Puede ser String o Integer.
     * 
     * @return los datos requeridos.
     * 
     * @throws SQLException en caso de que el gestor detecte algún problema en la búsqueda del registro.
     */
    public ResultSet buscarDatosPromo(String idPromo) throws SQLException{
        ps=getConnection().prepareStatement("select * from promociones where id_prom=?;");
        ps.setString(1,idPromo);
        
        return ps.executeQuery();
    }
    
    /**
     * Actualiza datos al iniciar sesión en el programa. 
     * Este método no es universal y se debe usar en casos muy específicos.
     * 
     * @param password del usuario loggeado.
     * @param user1 usuario loggeado.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void actualizarDatosLogin(String password,String user1) throws SQLException{
        ps=getConnection().prepareStatement("update empleados set fecha_sesion=now() where password=? and nombre_emp=? or curp=?;");
        ps.setString(1,password);
        ps.setString(2,user1);
        ps.setString(3,user1);
        ps.executeUpdate();
        
        ps.close();
    }
    
    /**
     * Actualiza la cantidad del producto que hay en stock (en almacén).
     * 
     * @param cantidad del producto que se vendió.
     * @param codigo de identificación del producto vendido.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void actualizarDatosAlmacen(int cantidad,int codigo) throws SQLException{
        ps=getConnection().prepareCall("update almacen set cantidad=cantidad-? where codigo_prod=?;");
        ps.setInt(1,cantidad);
        ps.setInt(2,codigo);
        ps.executeUpdate();
        
        ps.close();
    }
    
    /**
     * Actualiza el historial de ventas del empleado de ese día.
     * No actualiza los registros de otros días.
     * 
     * @param codigo de identificación del empleado.
     * @param fecha de inicio de sesión del empleado.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void actualizarDatosConteoVentas(int codigo,String fecha) throws SQLException{
        ps=getConnection().prepareStatement("update conteo set no_ventas=no_ventas+1 where codigo_emp=? and fecha_sesion=?;");
        ps.setInt(1,codigo);
        ps.setString(2,fecha);
        ps.executeUpdate();
        
        ps.close();
    }
    
    /**
     * Actualiza la cantidad de usos del código promocional.
     * 
     * @param codigo promocional a usar.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void actualizarDatosUsoPromo(String codigo) throws SQLException{
        ps=getConnection().prepareStatement("update promociones set no_usos=no_usos+1 where id_prom=?;");
        ps.setString(1,codigo);
        ps.executeUpdate();
        
        ps.close();
    }
    
    /**
     * Actualiza registros en la base de datos.<br>
     * Este método es específico para cadena de texto.
     * 
     * @param tabla a cambiar registros.
     * @param campo1 del registro a cambiar.
     * @param campo2 de identificación.
     * @param datos a cambiar (nuevos datos).
     * @param codigo de identificación del registro.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void actualizarDatosString(String tabla,String campo1,String campo2,String datos,int codigo) throws SQLException{
        ps=getConnection().prepareStatement("update "+tabla+" set "+campo1+"=? where "+campo2+"=?;");
        ps.setString(1,datos);
        ps.setInt(2,codigo);
        ps.executeUpdate();
        
        JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
        new logger(Level.INFO).staticLogger("Rel 2: se actualizaron correctamente los datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'actualizarDatosString()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        
        ps.close();
    }
    
    /**
     * Actualiza registros en la base de datos.<br>
     * Este método es específico para enteros (números).
     * 
     * @param tabla a cambiar registros.
     * @param campo1 del registro a cambiar.
     * @param campo2 de identificación.
     * @param datos a cambiar (nuevos datos).
     * @param codigo de identificación del registro.
     * @param flag para mostrar la notificación de confirmación
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void actualizarDatosInteger(String tabla,String campo1,String campo2,int datos,int codigo,boolean flag) throws SQLException{
        ps=getConnection().prepareStatement("update "+tabla+" set "+campo1+"=? where "+campo2+"=?;");
        ps.setInt(1,datos);
        ps.setInt(2,codigo);
        ps.executeUpdate();
        
        if(flag){
            JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
            new logger(Level.INFO).staticLogger("Rel 2: se actualizaron correctamente los datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'actualizarDatosInteger()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        }else{
            new logger(Level.INFO).staticLogger("Rel 2: se actualizaron correctamente los datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'actualizarDatosInteger()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        }
        
        ps.close();
    }
    
    /**
     * Actualiza registros en la base de datos.<br>
     * Este método es específico para fechas.
     * 
     * @param tabla a cambiar registros.
     * @param campo1 del registro a cambiar.
     * @param campo2 de identificación.
     * @param fecha a cambiar (nuevos datos).
     * @param codigo de identificación del registro.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void actualizarDatosDate(String tabla,String campo1,String campo2,Date fecha,int codigo) throws SQLException{
        ps=getConnection().prepareStatement("update "+tabla+" set "+campo1+"=? where "+campo2+"=?;");
        ps.setDate(1,fecha);
        ps.setInt(2,codigo);
        ps.executeUpdate();
        
        JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
        new logger(Level.INFO).staticLogger("Rel 2: se actualizaron correctamente los datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'actualizarDatosDate()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        
        ps.close();
    }
    
    /**
     * Actualiza la imagen de un registro.<br>
     * Este método es universal.<br>
     * Se debe escribir en la consulta en qué tabla se modificará la imagen y el identificador del registro a cambiar.<br>
     * Las tablas que tienen campos aptos para guardar una imagen son:
     * <ul>
     * <li>Empleados</li>
     * <li>Proveedor</li>
     * <li>Socios</li>
     * </ul>
     * Las demás existentes no son aptas. Favor de no intentar hacerlo.
     * 
     * @param tabla a la que pertenece el campo de imagen a cambiar.
     * @param campo de identificación del registro a cambiar la imagen.
     * @param usuario al que se le cambiará la imagen. Este es el registro, no el campo.
     * @param imagen a cambiar o subir.<br>
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void actualizarFotoPerfil(String tabla,String campo,InputStream imagen,int usuario) throws SQLException{
        ps=getConnection().prepareStatement("update "+tabla+" set foto=? where "+campo+"=?;");
        ps.setBinaryStream(1,imagen);
        ps.setInt(2,usuario);
        ps.executeUpdate();
        
        JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
        new logger(Level.INFO).staticLogger("Rel 2: se guardaron correctamente los datos a la base de datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'actualizarFotoPerfil()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        
        ps.close();
    }
    
    /**
     * Elimina datos específicos de la tabla productos.<br>
     * Prácticamente son todos los productos que ha vendido el empleado al que se le eliminarán los datos de la base de datos.
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de despido del negocio.
     * Se pueden reestablecer si previamente se creó la copia de seguridad (excepto los productos que ha vendido).
     * En su caso, solo será añadido un número con la cantidad de productos que vendió antes de que fueran eliminados los datos.
     * 
     * @param codigoEmpleado a eliminar los productos que ha vendido.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void eliminarDatosProductos(int codigoEmpleado) throws SQLException{
        ps=getConnection().prepareStatement("delete from productos where codigo_emp=?;");
        ps.setInt(1,codigoEmpleado);
        ps.executeUpdate();
        
        new logger(Level.INFO).staticLogger("Rel 3: se eliminaron correctamente los registros de la base de datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'eliminarDatosProductos()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        
        ps.close();
    }
    
    /**
     * Elimina datos específicos de la tabla almacén.<br>
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de fuerza mayor (producto de venta prohibida).
     * En este caso, no aplica la copia de seguridad con JSON.
     * 
     * @param codigoProducto a eliminar.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void eliminarDatosAlmacen(int codigoProducto) throws SQLException{
        ps=getConnection().prepareStatement("delete from almacen where codigo_prod=?;");
        ps.setInt(1,codigoProducto);
        ps.executeUpdate();
        
        JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
        new logger(Level.INFO).staticLogger("Rel 3: se eliminaron correctamente los registros de la base de datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'eliminarDatosAlmacen()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        
        ps.close();
    }
    
    /**
     * Elimina datos específicos de la tabla empleados.<br>
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de despido del negocio.
     * Se pueden reestablecer si previamente se creó la copia de seguridad.
     * 
     * @param codigoEmpleado a eliminar.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void eliminarDatosEmpleado(int codigoEmpleado) throws SQLException{
        ps=getConnection().prepareStatement("delete from empleados where codigo_emp=?;");
        ps.setInt(1,codigoEmpleado);
        ps.executeUpdate();
        
        JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
        new logger(Level.INFO).staticLogger("Rel 3: se eliminaron correctamente los registros de la base de datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'eliminarDatosEmpleado()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        
        ps.close();
    }
    
    /**
     * Elimina datos específicos de la tabla socios.<br>
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de desafiliación.
     * Se pueden reestablecer si previamente se creó la copia de seguridad.
     * 
     * @param codigoSocio a eliminar.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void eliminarDatosSocio(int codigoSocio) throws SQLException{
        ps=getConnection().prepareStatement("delete from socios where codigo_part=?;");
        ps.setInt(1,codigoSocio);
        ps.executeUpdate();
        
        JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
        new logger(Level.INFO).staticLogger("Rel 3: se eliminaron correctamente los registros de la base de datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'eliminarDatosSocio()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        
        ps.close();
    }
    
    /**
     * Elimina datos específicos de la tabla proveedor.<br>
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de despido de la empresa de origen.
     * Se pueden reestablecer si previamente se creó la copia de seguridad.
     * 
     * @param codigoProveedor a eliminar.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void eliminarDatosProveedor(int codigoProveedor) throws SQLException{
        ps=getConnection().prepareStatement("delete from proveedor where codigo_prov=?;");
        ps.setInt(1,codigoProveedor);
        ps.executeUpdate();
        
        JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
        new logger(Level.INFO).staticLogger("Rel 3: se eliminaron correctamente los registros de la base de datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'eliminarDatosProveedor()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        
        ps.close();
    }
    
    /**
     * Elimina datos específicos de la tabla conteo.<br>
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de despido del negocio.
     * Se pueden reestablecer si previamente se creó la copia de seguridad.
     * 
     * @param codigoEmpleado a eliminar.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void eliminarDatosConteo(int codigoEmpleado) throws SQLException{
        ps=getConnection().prepareStatement("delete from conteo where codigo_emp=?;");
        ps.setInt(1,codigoEmpleado);
        ps.executeUpdate();
        
        new logger(Level.INFO).staticLogger("Rel 3: se eliminaron correctamente los registros de la base de datos.\nOcurrió en la clase '"+Datos1.class.getName()+"', en el método 'eliminarDatosConteo()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        
        ps.close();
    }
}