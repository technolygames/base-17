package venTerciarias.valVentanas;
//clases
import clases.datos;
import clases.Icono;
import clases.laf;
import clases.logger;
import venSecundarias.importWindow;
import venPrimarias.start;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

public class validacionVentana8 extends javax.swing.JDialog{
    public validacionVentana8(java.awt.Frame parent, boolean modal){
        super(parent, modal);
        initComponents();
        new laf().LookAndFeel(validacionVentana8.this,validacionVentana8.class.getName(),"validacionVentana8");
        
        botones();
        
        setLocationRelativeTo(null);
        setTitle("Validación");
        setResizable(false);
    }
    
    protected final void botones(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        valButton.addActionListener((ae)->{
            String usuario=jTextField1.getText();
            String contra=String.valueOf(jPasswordField1.getPassword());
            
            String consulta="select * from empleados where password='"+contra+"' and nombre_emp='"+usuario+"';";
            
            try{
                if(!jTextField1.getText().equals("")||!jPasswordField1.getPassword().equals("")){
                    PreparedStatement ps=new datos().getConnection().prepareStatement(consulta);
                    ResultSet rs=ps.executeQuery();
                    if(rs.next()){
                        if(rs.getString("puesto").equals("Programador")||rs.getString("puesto").equals("Desarrollador")){
                            new importWindow(new javax.swing.JFrame(),true).setVisible(true);
                            new logger().staticLogger("Validación correcta a 'importWindow'.\nOcurrió en la clase '"+validacionVentana8.class.getName()+"', en el método 'botones(valButton)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.FINE);
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(null,"Acceso restringido","Error 37",JOptionPane.WARNING_MESSAGE);
                            new logger().staticLogger("Error 37: usuario sin privilegios.\nOcurrió en '"+validacionVentana8.class.getName()+"', en el método 'botones(valButton)'",Level.WARNING);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Error: no existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger().staticLogger("Error 14: no existen o no se ingresaron los datos a buscar y cambiar.\nOcurrió en '"+validacionVentana8.class.getName()+"', en el método 'botones(valButton)'",Level.WARNING);
                    }
                    ps.close();
                    rs.close();
                }else{
                    JOptionPane.showMessageDialog(null,"Error:\n Escribe tu usuario y/o contraseña","Error 18",JOptionPane.WARNING_MESSAGE);
                    new logger().staticLogger("Error 18: no se escribió usuario y/o contraseña.\nOcurrió en '"+validacionVentana8.class.getName()+"', en el método 'botones(valButton)'",Level.WARNING);
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 9",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 9: "+e.getMessage()+".\nOcurrió en la clase '"+validacionVentana8.class.getName()+"', en el método 'botones(valButton)'",Level.WARNING);
                new logger().exceptionLogger(validacionVentana8.class.getName(),Level.WARNING,"botones.val-9",e.fillInStackTrace());
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        valButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Icono().getIconImage());

        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        valButton.setText("Validar");

        backButton.setText("Regresar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(valButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valButton)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]){
        new validacionVentana8(new javax.swing.JFrame(), true).setVisible(true);
            
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton valButton;
    // End of variables declaration//GEN-END:variables
}