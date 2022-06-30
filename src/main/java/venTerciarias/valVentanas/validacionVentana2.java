package venTerciarias.valVentanas;
//clases
import clases.datos;
import clases.icono;
import clases.laf;
import clases.logger;
import venPrimarias.formulario1;
import venPrimarias.start;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

public class validacionVentana2 extends javax.swing.JDialog{
    public validacionVentana2(java.awt.Frame parent,boolean modal){
        super(parent,modal);
        initComponents();
        new laf(validacionVentana2.class.getName()).LookAndFeel(validacionVentana2.this);
        
        botones();
        
        setLocationRelativeTo(null);
        setTitle("Validación");
        setResizable(false);
        
        jTextField1.setText(start.nameUser);
    }
    
    protected final void botones(){
        backButton.addActionListener((a)->{
            setVisible(true);
            dispose();
        });
        
        valButton.addActionListener((a)->{
            String usuario=jTextField1.getText();
            String contra=String.valueOf(jPasswordField1.getPassword());
            
            String consulta="select * from empleados where password='"+contra+"' and nombre_emp='"+usuario+"';";
            
            try{
                if(!jTextField1.getText().equals("")||!jPasswordField1.getPassword().equals("")){
                    PreparedStatement ps=new datos().getConnection().prepareStatement(consulta);
                    ResultSet rs=ps.executeQuery();
                    if(rs.next()){
                        if(rs.getString("puesto").equals("Dueño")||rs.getString("puesto").equals("Programador")||rs.getString("puesto").equals("Desarrollador")){
                            new formulario1().setVisible(true);
                            new logger().staticLogger("Rel 5: validación correcta a 'formulario1'.\nOcurrió en la clase '"+validacionVentana2.class.getName()+"', en el método 'botones(valButton)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(null,"Acceso restringido","Error 38",JOptionPane.WARNING_MESSAGE);
                            new logger().staticLogger("Error 38: usuario sin privilegios.\nOcurrió en la clase '"+validacionVentana2.class.getName()+"', en el método 'botones(valButton)'",Level.WARNING);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Error: no existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger().staticLogger("Error 14: no existen o no se ingresaron los datos a buscar y cambiar.\nOcurrió en '"+validacionVentana2.class.getName()+"', en el método 'botones(valButton)'",Level.WARNING);
                    }
                    ps.close();
                    rs.close();
                }else{
                    JOptionPane.showMessageDialog(null,"Error:\n Escribe tu usuario y/o contraseña","Error 18",JOptionPane.WARNING_MESSAGE);
                    new logger().staticLogger("Error 18: no se escribió usuario y/o contraseña.\nOcurrió en la clase '"+validacionVentana2.class.getName()+"', en el método 'botones(valButton)'",Level.WARNING);
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 9",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 9: "+e.getMessage()+".\nOcurrió en la clase '"+validacionVentana2.class.getName()+"', en el método 'botones(valButton)'",Level.WARNING);
                new logger().exceptionLogger(validacionVentana2.class.getName(),Level.WARNING,"botones.val-9",e.fillInStackTrace());
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        valButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new icono().getIconImage());

        jLabel1.setText("Usuario:");

        backButton.setText("Regresar");

        valButton.setText("Validar");

        jLabel2.setText("Contraseña:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(valButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jPasswordField1))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(valButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]){ 
        new validacionVentana2(new javax.swing.JFrame(),true).setVisible(true);
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