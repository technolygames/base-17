package paneles;
//clases
import clases.dirs;
import clases.logger;
import java.awt.Frame;
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
import venPrimarias.adminTools;

public class environmentPanel extends javax.swing.JPanel{
    public environmentPanel(){
        initComponents();
        
        botones();
        configIn();
    }
    
    protected Properties p;
    protected File f;
    
    protected String userdir=dirs.userdir;
    
    protected void configIn(){
        p=new Properties();
        try{
            p.load(new FileInputStream(userdir+"/data/config/env.properties"));
            
            jTextField1.setText(p.getProperty("local_mysql"));
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+environmentPanel.class.getName()+"', en el método 'configIn()'");
            new logger(Level.SEVERE).exceptionLogger(environmentPanel.class.getName(),"configIn-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+environmentPanel.class.getName()+"', en el método 'configIn()'");
            new logger(Level.SEVERE).exceptionLogger(environmentPanel.class.getName(),"configIn-2IO",x.fillInStackTrace());
        }
    }
    
    protected final void botones(){
        closeButton.addActionListener((a)->{
            setVisible(false);
        });
        
        jButton1.addActionListener((a)->{
            configOut();
            for(Frame f:Frame.getFrames()){
                f.validate();
            }
        });
        
        jButton2.addActionListener((a)->{
            try{
                p=new Properties();
                p.load(new FileInputStream(userdir+"/data/config/filechooserd.properties"));
                JFileChooser chooser=new JFileChooser(p.getProperty("lastdirectory_envvar"));
                
                if(p.getProperty("lastdirectory_envvar").equals("")){
                    chooser.setCurrentDirectory(new File("."));
                }
                
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                
                while(JFileChooser.APPROVE_OPTION==chooser.showOpenDialog(null)){
                    f=chooser.getSelectedFile();
                    jTextField1.setText(f.getPath());
                    
                    p.setProperty("lastdirectory_envvar",f.getParent());
                    p.store(new FileOutputStream(userdir+"/data/config/filechooserd.properties"),"JFileChooserDirection");
                    break;
                }
            }catch(IOException e){
                JOptionPane.showMessageDialog(this,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+environmentPanel.class.getName()+"', en el método 'botones(jButton2)'");
                new logger(Level.SEVERE).exceptionLogger(environmentPanel.class.getName(),"botones.button2-1IO",e.fillInStackTrace());
            }catch(NullPointerException x){
                JOptionPane.showMessageDialog(this,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+environmentPanel.class.getName()+"', en el método 'botones(jButton2)'");
                new logger(Level.SEVERE).exceptionLogger(environmentPanel.class.getName(),"botones.button2-0",x.fillInStackTrace());
            }
        });
    }
    
    protected void configOut(){
        p=new Properties();
        f=new File(userdir+"/data/config/env.properties");
        try{
            if(f.exists()){
                p.setProperty("local_mysql",jTextField1.getText());
                
                p.store(new FileOutputStream(userdir+"/data/config/env.properties"),"EnvironmentVariables");
                
                JOptionPane.showMessageDialog(null,"Se guardaron correctamente","Rel 4",JOptionPane.INFORMATION_MESSAGE);
                new logger(Level.INFO).staticLogger("Rel 4: se han guardado las condiguraciones.\nOcurrió en la clase '"+environmentPanel.class.getName()+"', en el método 'configOut()'.\nUsuario que hizo los cambios: "+String.valueOf(start.userID));
            }else{
                f.createNewFile();
            }
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+environmentPanel.class.getName()+"', en el método 'configOut()'");
            new logger(Level.SEVERE).exceptionLogger(environmentPanel.class.getName(),"configOut-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+environmentPanel.class.getName()+"', en el método 'configOut()'");
            new logger(Level.SEVERE).exceptionLogger(environmentPanel.class.getName(),"configOut-2IO",x.fillInStackTrace());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        jButton1.setText("Guardar datos");

        closeButton.setText("Cerrar panel");

        jLabel1.setText("MySQL local:");

        jButton2.setText("Buscar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}