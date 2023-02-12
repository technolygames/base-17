package paneles;
//clases
import clases.logger;
import java.awt.HeadlessException;
import venPrimarias.start;
//java
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
//extension larga
import java.util.logging.Level;

public class environmentPanel extends javax.swing.JPanel{
    public environmentPanel(){
        initComponents();
        
        botones();
        configIn();
    }
    
    protected Properties p;
    protected File f;
    
    protected String direccion;
    protected String methodName;
    
    protected final void configIn(){
        methodName="configIn";
        p=new Properties();
        try{
            p.load(new FileInputStream("data/config/env.properties"));
            
            direccion=p.getProperty("local_mysql");
            jTextField1.setText(direccion);
            
            p.clear();
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(null,e,methodName,"1IO");
        }catch(IOException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(null,x,methodName,"2IO");
        }
    }
    
    protected final void botones(){
        closeButton.addActionListener(a->{
            if(!jTextField1.getText().equals(direccion)){
                if(JOptionPane.showConfirmDialog(this,"Hay cambios.\n¿Deseas cerrar el panel?","Notice 1",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==0){
                    setVisible(false);
                }
            }else{
                setVisible(false);
            }
        });
        
        storeButton.addActionListener(a->
            configOut()
        );
        
        selDirButton.addActionListener(a->{
            methodName="botones.selDir";
            try{
                p=new Properties();
                p.load(new FileInputStream("data/config/filechooserd.properties"));
                JFileChooser chooser=new JFileChooser(p.getProperty("lastdirectory_envvar"));
                
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setDialogTitle("Seleccionar directorio");
                
                if(JFileChooser.APPROVE_OPTION==chooser.showOpenDialog(null)){
                    f=chooser.getSelectedFile();
                    jTextField1.setText(f.getPath().concat("\\"));
                    
                    p.setProperty("lastdirectory_envvar",f.getPath());
                    p.store(new FileOutputStream("data/config/filechooserd.properties"),"JFileChooserDirection");
                }
            }catch(HeadlessException e){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(null,e,methodName,"40");
            }catch(IOException x){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(null,x,methodName,"2IO");
            }catch(NullPointerException n){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(null,n,methodName,"0");
            }
        });
    }
    
    protected void configOut(){
        methodName="configOut";
        p=new Properties();
        f=new File("data/config/env.properties");
        try{
            if(f.exists()){
                p.setProperty("local_mysql",jTextField1.getText());
                
                p.store(new FileOutputStream("data/config/env.properties"),"EnvironmentVariables");
                
                JOptionPane.showMessageDialog(this,"Se guardaron correctamente","Rel 4",JOptionPane.INFORMATION_MESSAGE);
                logger.staticLogger(Level.INFO,"Rel 4: se han guardado las condiguraciones.\nOcurrió en el método 'configOut()'.\nUsuario que hizo los cambios: "+String.valueOf(start.USERID),this.getClass().getName());
            }else{
                f.createNewFile();
            }
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(null,e,methodName,"1IO");
        }catch(IOException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(null,x,methodName,"2IO");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        storeButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        selDirButton = new javax.swing.JButton();

        storeButton.setText("Guardar datos");

        closeButton.setText("Cerrar panel");

        jLabel1.setText("MySQL local:");

        selDirButton.setText("Buscar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(storeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selDirButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selDirButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(storeButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton selDirButton;
    private javax.swing.JButton storeButton;
    // End of variables declaration//GEN-END:variables
}