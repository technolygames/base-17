package venSecundarias;
//clases
import clases.Icono;
import clases.laf;
import clases.logger;
import clases.thread;
import venPrimarias.start;
//java
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

public class exportWindow extends javax.swing.JDialog{
    public exportWindow(java.awt.Frame parent,boolean modal){
        super(parent,modal);
        initComponents();
        new laf().LookAndFeel(exportWindow.this,exportWindow.class.getName(),"exportWindow");
        
        botones();
        
        setLocationRelativeTo(null);
        setTitle("Exportar");
        setResizable(false);
    }
    
    protected File f;
    protected InputStream is;
    protected OutputStream os;
    
    protected final void botones(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        exportButton.addActionListener((ae)->{
            copia();
        });
    }
    
    protected void copia(){
        String nombreUsuario=jTextField1.getText();
        String passUsuario=jPasswordField1.getPassword().toString();
        String based=jTextField3.getText();
        String nombrebdExportada=based+(int)(Math.random()*1000)+".sql";
        
        try{
            Process pr=Runtime.getRuntime().exec("C:\\xampp\\mysql\\bin\\mysqldump.exe -u "+nombreUsuario+" -p "+passUsuario+" "+based);
            
            is=pr.getInputStream();
            os=new FileOutputStream(new File(System.getProperty("user.dir")+"/src/data/database/MySQL/"+nombrebdExportada));
            
            new thread(is,os).run();
            
            JOptionPane.showMessageDialog(null,"Se ha exportado correctamente la base de datos","Rel 3E",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 3E: se exportó correctamente la base de datos.\nOcurrió en la clase '"+exportWindow.class.getName()+"', en el método 'copia()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
            
            os.close();
            os.flush();
            is.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 8E",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 8E: "+e.getMessage()+".\nOcurrió en la clase '"+exportWindow.class.getName()+"', en el método 'copia()'",Level.WARNING);
            new logger().exceptionLogger(exportWindow.class.getName(),Level.WARNING,"copia-8E",e.fillInStackTrace());
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+exportWindow.class.getName()+"', en el método 'copia()'",Level.WARNING);
            new logger().exceptionLogger(exportWindow.class.getName(),Level.WARNING,"copia-0",x.fillInStackTrace());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        exportButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Icono().getIconImage());

        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        jLabel3.setText("Base de datos:");

        exportButton.setText("Exportar");

        backButton.setText("Regresar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(exportButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                .addComponent(jPasswordField1)))
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
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportButton)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        new exportWindow(new javax.swing.JFrame(),true).setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton exportButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}