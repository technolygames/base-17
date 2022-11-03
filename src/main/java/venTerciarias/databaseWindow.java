package venTerciarias;
//clases
import clases.Datos;
import clases.GuiMediaHandler;
import clases.logger;
import paneles.databaseImport;
//java
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;

public class databaseWindow extends javax.swing.JDialog{
    public databaseWindow(java.awt.Frame parent,boolean modal){
        super(parent,modal);
        initComponents();
        new GuiMediaHandler(databaseWindow.class.getName()).LookAndFeel(databaseWindow.this);
        
        botones();
        
        setLocationRelativeTo(null);
        setTitle("Crear base de datos");
        setResizable(false);
        pack();
    }
    
    public static String nombredb;
    
    protected final void botones(){
        backButton.addActionListener((a)->{
            setVisible(false);
            dispose();
        });
        
        createButton.addActionListener((a)->{
            try{
                new Datos().crearBD(jTextField1.getText());
                databaseImport.jTextField3.setText(jTextField1.getText());
            }catch(SQLException e){
                new logger(Level.SEVERE).storeAndViewCaughtException(this,e,databaseWindow.class.getName(),"botones.create","5E");
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        createButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new GuiMediaHandler(databaseWindow.class.getName()).getIconImage());

        jLabel1.setText("Nombre de la base de datos:");

        createButton.setText("Crear");

        backButton.setText("Regresar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(createButton)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton)
                    .addComponent(createButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new databaseWindow(new javax.swing.JFrame(),true).setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton createButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}