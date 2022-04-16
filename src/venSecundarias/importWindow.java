package venSecundarias;
//clases
import clases.Icono;
import clases.laf;
import clases.logger;
import clases.thread;
import venPrimarias.start;
import venTerciarias.databaseWindow;
//java
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
//extension larga
import java.util.logging.Level;
import javax.swing.filechooser.FileNameExtensionFilter;

public class importWindow extends javax.swing.JDialog{
    public importWindow(java.awt.Frame parent,boolean modal){
        super(parent,modal);
        initComponents();
        new laf().LookAndFeel(importWindow.this,importWindow.class.getName(),"importWindow");
        
        botones();
        
        setLocationRelativeTo(null);
        setTitle("Importar");
        setResizable(false);
    }
    
    protected Properties p;
    protected InputStream is;
    protected OutputStream os;
    
    protected String direccion;
    
    protected void settings(){
        jTextField3.setText(databaseWindow.nombredb);
    }
    
    protected final void botones(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        fileButton.addActionListener((ae)->{
            try{
                p=new Properties();
                p.load(new FileInputStream(System.getProperty("user.dir")+"/src/data/config/filechooserd.properties"));
                JFileChooser chooser=new JFileChooser(p.getProperty("lastdirectory_database_import"));
                
                chooser.setFileFilter(new FileNameExtensionFilter("Archivo SQL","sql"));
                
                int i=chooser.showOpenDialog(null);
                if(JFileChooser.APPROVE_OPTION==i){
                    File f=chooser.getSelectedFile().getAbsoluteFile();
                    direccion=f.getAbsolutePath();
                    
                    p.setProperty("lastdirectory_database_import",f.getParent());
                    p.store(new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/src/data/config/filechooserd.properties")),"JFileChooserDirection");
                }
            }catch(IOException e){
                JOptionPane.showMessageDialog(this,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+importWindow.class.getName()+"', en el método 'botones(fileButton)'",Level.WARNING);
                new logger().exceptionLogger(importWindow.class.getName(),Level.WARNING,"botones.file-1IO",e.fillInStackTrace());
            }
        });
        
        importButton.addActionListener((ae)->{
            try{
                Process pr=Runtime.getRuntime().exec("C:\\xampp\\mysql\\bin\\mysql.exe -u "+jTextField1.getText()+" -p "+jPasswordField1.getPassword().toString()+" "+jTextField3.getText());
                
                os=pr.getOutputStream();
                is=new FileInputStream(direccion);
                
                new thread(is,os).run();
                
                JOptionPane.showMessageDialog(null,"Se ha importado correctamente la base de datos","Rel 2E",JOptionPane.INFORMATION_MESSAGE);
                new logger().staticLogger("Rel 2E: se importó correctamente la base de datos.\nOcurrió en la clase '"+importWindow.class.getName()+"', en el método 'botones(importButton)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
                
                os.close();
                os.flush();
                is.close();
            }catch(IOException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 7E",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 7E: "+e.getMessage()+".\nOcurrió en la clase '"+importWindow.class.getName()+"', en el método 'botones(importButton)'",Level.WARNING);
                new logger().exceptionLogger(importWindow.class.getName(),Level.WARNING,"botones.import-7E",e.fillInStackTrace());
            }catch(NullPointerException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+importWindow.class.getName()+"', en el método 'botones(importButton)'",Level.WARNING);
                new logger().exceptionLogger(importWindow.class.getName(),Level.WARNING,"botones.import-0",x.fillInStackTrace());
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        importButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        fileButton = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Icono().getIconImage());

        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        jLabel3.setText("Base de datos a usar:");

        importButton.setText("Importar");

        backButton.setText("Regresar");

        jLabel4.setText("Base de datos a importar:");

        fileButton.setText("Buscar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(jPasswordField1)
                                    .addComponent(fileButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 79, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(importButton)
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
                    .addComponent(jTextField1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fileButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(importButton)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        new importWindow(new javax.swing.JFrame(),true).setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton fileButton;
    private javax.swing.JButton importButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}