package clases;
//clases
import venPrimarias.start;
//java
import java.awt.Frame;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

/**
 * Esta clase se encarga de hacer las validaciones para ventanas especiales.<br>
 * Hace la validación del rol del usuario loggeado.
 * 
 * @author erick
 */
public class Validation1{
    protected String puesto;
    protected String clase;
    protected Frame ventana;
    
    /**
     * Inicializa la instancia para mandar los datos solicitados y la ventana destino para que pueda hacer uso del método.
     * 
     * @param frame que se abrirá al validar correctamente los datos solicitados.
     * @param role del empleado para verificar si tiene o no permisos.
     * @param clase que es la misma que la ventana que se abrirá ak validar correctamente los datos.
     */
    public Validation1(Frame frame,String role,String clase){
        this.ventana=frame;
        this.puesto=role;
        this.clase=clase;
    }
    
    /**
     * Inicializa la instancia para mandar los datos solicitados y validarlos.
     * 
     * @param role del empleado para verificar si tiene o no permisos.
     * @param clase que es la misma que la ventana que se abrirá ak validar correctamente los datos.
     */
    public Validation1(String role,String clase){
        this.puesto=role;
        this.clase=clase;
    }
    
    /**
     * Método encargado de validar los datos cuando se crea la instancia de esta clase.
     * Roles permitidos:
     * <ul>
     * <li>Dueño (owner)</li>
     * <li>Programador (programmer)</li>
     * <li>Desarrollador (developer)</li>
     * </ul>
     * Si el usuario tiene el rol de empleado, no tendrá acceso a las características y ventanas que hacen uso de esta clase.
     */
    public void toRestrictedForm(){
        if(puesto.equals("Dueño")||puesto.equals("Programador")||puesto.equals("Desarrollador")){
            EventQueue.invokeLater(()->{
                ventana.setVisible(true);
            });
            new logger(Level.INFO).staticLogger("Rel 5: validación correcta a '"+clase+"'.\nOcurrió en la clase '"+Validation1.class.getName()+"', en el método 'toRestrictedForm()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
        }else{
            JOptionPane.showMessageDialog(null,"Acceso restringido","Error 38",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Error 38: usuario sin privilegios.\nOcurrió en la clase '"+Validation1.class.getName()+"', en el método 'toRestrictedForm()'.\nUsuario sin privilegios: "+String.valueOf(start.userID));
        }
    }
    
    /**
     * Verifica si es posible ver si cierta característica es visible para el puesto que fue asignado al usuario logeado.
     * 
     * @return si es posible ver o acceder a la característica 
     */
    public boolean isAccessible(){
        return puesto.equals("Dueño")||puesto.equals("Programador")||puesto.equals("Desarrollador");
    }
}