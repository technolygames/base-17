package venSecundarias;

import clases.logger;
import clases.thread;
import java.awt.Image;
import java.awt.Toolkit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

public class exportWindow extends javax.swing.JDialog{
    public exportWindow(java.awt.Frame parent,boolean modal){
        super(parent,modal);
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error CNFE: "+e.getMessage()+".\nOcurrió en la clase '"+exportWindow.class.getName()+"', en el método 'exportWindow()'",Level.WARNING);
            new logger().exceptionLogger(exportWindow.class.getName(),Level.WARNING,"exportWindow-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IE: "+x.getMessage()+".\nOcurrió en la clase '"+exportWindow.class.getName()+"', en el método 'exportWindow()'",Level.WARNING);
            new logger().exceptionLogger(exportWindow.class.getName(),Level.WARNING,"exportWindow-IE",x.fillInStackTrace());
        }catch(IllegalAccessException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IAE: "+n.getMessage()+".\nOcurrió en la clase '"+exportWindow.class.getName()+"', en el método 'exportWindow()'",Level.WARNING);
            new logger().exceptionLogger(exportWindow.class.getName(),Level.WARNING,"exportWindow-IAE",n.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 28: "+y.getMessage()+".\nOcurrió en la clase '"+exportWindow.class.getName()+"', en el método 'exportWindow()'",Level.WARNING);
            new logger().exceptionLogger(exportWindow.class.getName(),Level.WARNING,"exportWindow-28",y.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+exportWindow.class.getName()+"', en el método 'exportWindow()'",Level.WARNING);
            new logger().exceptionLogger(exportWindow.class.getName(),Level.WARNING,"exportWindow-0",k.fillInStackTrace());
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+s.getMessage()+".\nOcurrió en la clase '"+exportWindow.class.getName()+"', en el método 'exportWindow()'",Level.WARNING);
            new logger().exceptionLogger(exportWindow.class.getName(),Level.WARNING,"exportWindow-1IO",s.fillInStackTrace());
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+d.getMessage()+".\nOcurrió en la clase '"+exportWindow.class.getName()+"', en el método 'exportWindow()'",Level.WARNING);
            new logger().exceptionLogger(exportWindow.class.getName(),Level.WARNING,"exportWindow-2IO",d.fillInStackTrace());
        }
        
        botones();
        
        setLocationRelativeTo(null);
        setTitle("Exportar");
    }
    
    protected File f;
    protected InputStream is;
    protected OutputStream os;
    protected JFileChooser j;
    
    protected Image retValue;
    protected Properties p;
    
    public Image getIconImage(){
        p=new Properties();
        try{
            p.load(new FileInputStream("src/data/config/config.properties"));
            retValue=Toolkit.getDefaultToolkit().getImage(p.getProperty("icono"));
            retValue.flush();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+exportWindow.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(exportWindow.class.getName(),Level.WARNING,"getIconImage-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+exportWindow.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(exportWindow.class.getName(),Level.WARNING,"getIconImage-2IO",x.fillInStackTrace());
        }
        return retValue;
    }
    
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
        String nombrebdExportada=based+".sql";
        
        try{
            Process pr=Runtime.getRuntime().exec("c:\\xampp\\mysql\\bin\\mysqldump.exe -u "+nombreUsuario+" -p "+passUsuario+" "+based);
            
            is=pr.getInputStream();
            os=new FileOutputStream(new File("src/data/database/MySQL/"+nombrebdExportada));
            
            new thread(is,os).run();
            
            JOptionPane.showMessageDialog(null,"Se ha exportado la base de datos correctamente","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            
            os.close();
            os.flush();
            is.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 8",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 8: "+e.getMessage()+".\nOcurrió en la clase '"+exportWindow.class.getName()+"', en el método 'copia()'",Level.WARNING);
            new logger().exceptionLogger(exportWindow.class.getName(),Level.WARNING,"copia-8",e.fillInStackTrace());
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+exportWindow.class.getName()+"', en el método 'copia()'",Level.WARNING);
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
        setIconImage(getIconImage());

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