package clases;
//clases
import clases.mvc.Controlador;
//java
import java.awt.Frame;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

/**
 * Esta clase se encarga de hacer las validaciones para ventanas especiales. 
 * Hace la validación del rol del usuario loggeado.
 * 
 * @author erick
 */
public class Validation{
    protected String rol0="Dueño";
    protected String rol1="Programador";
    protected String rol2="Desarrollador";
    
    protected Controlador modelo;
    
    protected String puesto;
    protected String clase;
    protected Frame ventana;
    
    protected Properties p;
    
    /**
     * Inicializa la instancia para mandar los datos solicitados y la ventana destino para que pueda hacer uso del método.
     * 
     * @param frame que se abrirá al validar correctamente los datos solicitados.
     * @param modelo datos del usuario loggeado en el sistema.
     * @param role del empleado para verificar si tiene o no permisos.
     * @param clase que es la misma que la ventana que se abrirá ak validar correctamente los datos.
     */
    public Validation(Frame frame,Controlador modelo,String role,String clase){
        this.ventana=frame;
        this.modelo=modelo;
        this.puesto=role;
        this.clase=clase;
    }
    
    /**
     * Inicializa la instancia para mandar los datos solicitados y validarlos.
     * 
     * @param modelo datos del usuario loggeado en el sistema.
     * @param role del empleado para verificar si tiene o no permisos.
     * @param clase que es la misma que la ventana que se abrirá ak validar correctamente los datos.
     */
    public Validation(Controlador modelo,String role,String clase){
        this.modelo=modelo;
        this.puesto=role;
        this.clase=clase;
    }
    
    /**
     * Método encargado de validar los datos cuando se crea la instancia de esta clase.<br>
     * Roles permitidos:
     * <ul>
     * <li>Dueño (owner)</li>
     * <li>Programador (programmer)</li>
     * <li>Desarrollador (developer)</li>
     * </ul>
     * Si el usuario tiene el rol de empleado, no tendrá acceso a las características y ventanas que hacen uso de esta clase.
     */
    public void toRestrictedForm(){
        if(isAccessible()){
            EventQueue.invokeLater(()->
                ventana.setVisible(true)
            );
            logger.staticLogger(Level.INFO,"Rel 5: validación correcta a '"+clase+"'.\nOcurrió en el método 'toRestrictedForm()'.\nUsuario que hizo la acción: "+String.valueOf(modelo.getUserID()),this.getClass().getName());
        }else{
            JOptionPane.showMessageDialog(MediaHandler.getFrames(),"Acceso restringido","Error 38",JOptionPane.WARNING_MESSAGE);
            logger.staticLogger(Level.WARNING,"Error 38: usuario sin privilegios.\nOcurrió en el método 'toRestrictedForm()'.\nUsuario sin privilegios: "+String.valueOf(modelo.getUserID()),this.getClass().getName());
        }
    }
    
    /**
     * Verifica si es posible ver si cierta característica es visible para el puesto que fue asignado al usuario logeado.
     * 
     * @return si es posible ver o acceder a la característica 
     */
    public boolean isAccessible(){
        return puesto.equals(rol0)||puesto.equals(rol1)||puesto.equals(rol2);
    }
    
    /**
     * Verifica si el usuario que está loggeado tiene permiso de Dueño para realizar la acción a la que este método se usará.
     * 
     * @return un valor booleano si tiene o no permiso.
     */
    public boolean hasOwnerRole(){
        return puesto.equals(rol0);
    }
    
    /**
     * Verifica si el usuario que está loggeado tiene permiso de desarrollador o programador para realizar la acción a la que este método se usará.
     * 
     * @return un valor booleano si tiene o no permiso.
     */
    public boolean hasDevRole(){
        return puesto.equals(rol1)||puesto.equals(rol2);
    }
    
    /**
     * Verifica si el usuario loggeado tiene permisos para realizar la acción a la que este método se usará. 
     * Por defecto, regresará true porque aún no tiene qué debe validar.
     * 
     * @return un valor booleano si tiene o no permiso.
     */
    protected boolean hasPermission(){
        return true;
    }
    
    protected boolean isOnExclusionList(String name){
        p=new Properties();
        try{
            p.load(new FileReader("",StandardCharsets.UTF_8));
            while(!p.isEmpty()){
                
            }
        }catch(FileNotFoundException e){
            return false;
        }catch(IOException x){
            return false;
        }
        return true;
    }
    
    /**
     * Según sea el parámetro que reciba el método, validará el tipo de pago que se está usando. 
     * El tipo de dato que puede recibir el método para validar los datos es entero (Integer).<br>
     * Posibles valores que recibirá para validar:
     * <ul>
     * <li>0 - Efectivo</li>
     * <li>1 - Tarjeta</li>
     * <li>2+ - lanzará una excepción IndexOutOfBounds</li>
     * </ul>
     * 
     * @param index de los valores a validar.
     * 
     * @return el método de pago que se está usando. Si está fuera del rango establecido, dará IndexOutOfBounds.
     */
    public static String paymentType(int index){
        return switch (index){
            case 0->"Efectivo";
            case 1->"Tarjeta";
            default->throw new IndexOutOfBoundsException();
        };
    }
    
    /**
     * @param index
     * @return 
     */
    public static String stock(int index){
        return switch(index){
            case 0->"En existencia";
            case 1->"Agotado";
            default->null;
        };
    }
}