package paneles;
//clases
import clases.logger;
import venPrimarias.start;
//java
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.JOptionPane;
//con extensión larga
import java.util.logging.Level;

public class databaseConfig extends javax.swing.JPanel{
    public databaseConfig(){
        initComponents();
        
        botones();
        configIn();
    }
    
    protected File f;
    protected Properties p;
    
    protected String methodName;
    
    protected final void configIn(){
        methodName="configIn";
        p=new Properties();
        try{
            p.load(new FileInputStream("data/config/databaseInfo.properties"));
            
            jComboBox1.getModel().setSelectedItem(p.getProperty("driver"));
            jTextField1.setText(p.getProperty("database"));
            jTextField2.setText(p.getProperty("user"));
            jTextField3.setText(p.getProperty("pass"));
            jTextField4.setText(p.getProperty("ip"));
            jTextField5.setText(p.getProperty("port"));
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,e,databaseConfig.class.getName(),methodName,"1IO");
        }catch(IOException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,x,databaseConfig.class.getName(),methodName,"2IO");
        }
    }
    
    protected final void botones(){
        closeButton.addActionListener(a->
            setVisible(false)
        );
        
        storeButton.addActionListener(a->
            configOut()
        );
    }
    
    protected void configOut(){
        methodName="configOut";
        f=new File("data/config/databaseInfo.properties");
        try{
            if(f.exists()){
                p.setProperty("driver",jComboBox1.getSelectedItem().toString());
                p.setProperty("database",jTextField1.getText());
                p.setProperty("user",jTextField2.getText());
                p.setProperty("pass",jTextField3.getText());
                p.setProperty("ip",jTextField4.getText());
                p.setProperty("port", jTextField5.getText());
                
                p.store(new FileOutputStream("data/config/databaseInfo.properties"),"DatabaseConfig");
                
                JOptionPane.showMessageDialog(this,"Se guardaron correctamente","Rel 4",JOptionPane.INFORMATION_MESSAGE);
                new logger(Level.INFO).staticLogger("Rel 4: se han guardado las condiguraciones.\nOcurrió en la clase '"+databaseConfig.class.getName()+"', en el método 'configOut()'.\nUsuario que hizo los cambios: "+String.valueOf(start.userID));
            }else{
                f.createNewFile();
            }
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,e,databaseConfig.class.getName(),methodName,"1IO");
        }catch(NumberFormatException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,x,databaseConfig.class.getName(),methodName,"32");
        }catch(NullPointerException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,n,databaseConfig.class.getName(),methodName,"0");
        }catch(IOException s){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,s,databaseConfig.class.getName(),methodName,"2IO");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        storeButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();

        jLabel1.setText("Driver:");

        jLabel2.setText("IP:");

        jLabel3.setText("Puerto:");

        jLabel4.setText("Base de datos:");

        storeButton.setText("Guardar");

        jLabel5.setText("Usuario:");

        jLabel6.setText("Contraseña:");

        closeButton.setText("Cerrar panel");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "com.mysql.cj.jdbc.Driver" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2)
                            .addComponent(jTextField5)
                            .addComponent(jTextField4)
                            .addComponent(jTextField3)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(storeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton)))
                .addContainerGap(168, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(storeButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JButton storeButton;
    // End of variables declaration//GEN-END:variables
}