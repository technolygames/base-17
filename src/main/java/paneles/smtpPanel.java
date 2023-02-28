package paneles;

import clases.logger;
import clases.mvc.Controlador;
import venPrimarias.start;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.JOptionPane;

import java.util.logging.Level;

public class smtpPanel extends javax.swing.JPanel{
    public smtpPanel(){
        initComponents();
        
        botones();
        configIn();
    }
    
    protected Controlador modelo;
    
    public smtpPanel(Controlador modelo){
        initComponents();
        
        this.modelo=modelo;
        
        botones();
        configIn();
    }
    
    protected String methodName;
    
    protected String name;
    protected String mail;
    protected String pass;
    protected String port;
    protected String server;
    
    protected Properties p;
    
    protected final void configIn(){
        methodName="configIn";
        p=new Properties();
        try{
            p.load(new FileReader("data/config/smtp.properties"));
            
            name=p.getProperty("nombre");
            mail=p.getProperty("correo");
            pass=p.getProperty("password");
            port=p.getProperty("puerto");
            server=p.getProperty("server");
            
            jTextField1.setText(name);
            jTextField2.setText(mail);
            jPasswordField1.setText(pass);
            jComboBox1.getModel().setSelectedItem(port);
            jComboBox2.getModel().setSelectedItem(server);
        }catch(IOException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"2IO");
        }
    }
    
    protected final void botones(){
        closeButton.addActionListener(a->{
            if(!jTextField1.getText().equals(name)||
                    !jTextField2.getText().equals(mail)||
                    !String.valueOf(jPasswordField1.getPassword()).equals(pass)||
                    !jComboBox1.getModel().getSelectedItem().equals(port)||
                    !jComboBox2.getModel().getSelectedItem().equals(server)){
                setVisible(false);
            }
        });
        
        clearButton.addActionListener(a->{
            jTextField1.setText("");
            jTextField2.setText("");
            jPasswordField1.setText("");
        });
        
        storeButton.addActionListener(a->
            configOut()
        );
    }
    
    protected void configOut(){
        methodName="configOut";
        p=new Properties();
        try{
            p.setProperty("nombre",jTextField1.getText());
            p.setProperty("correo",jTextField2.getText());
            p.setProperty("password",String.valueOf(jPasswordField1.getPassword()));
            p.setProperty("puerto",jComboBox1.getSelectedItem().toString());
            p.setProperty("server",jComboBox2.getSelectedItem().toString());
            
            p.store(new FileWriter("data/config/smtp.properties"),"SmtpUserData");
            
            JOptionPane.showMessageDialog(this,"Se guardaron correctamente","Rel 4",JOptionPane.INFORMATION_MESSAGE);
            logger.staticLogger(Level.INFO,"Rel 4: se han guardado las condiguraciones.\nOcurrió en el método 'configOut()'.\nUsuario que hizo los cambios: "+String.valueOf(modelo.getUserID()),this.getClass().getName());
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"1IO");
        }catch(IOException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,x,methodName,"2IO");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        storeButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        clearButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();

        storeButton.setText("Guardar");

        closeButton.setText("Cerrar panel");

        jLabel1.setText("Nombre de usuario:");

        jLabel2.setText("Correo (de hotmail):");

        jLabel3.setText("Contraseña:");

        clearButton.setText("Limpiar campos");

        jLabel4.setText("Puerto:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "465", "587" }));

        jLabel5.setText("Servidor:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "gmail", "office365", "live" }));

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
                        .addComponent(closeButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1)
                                .addComponent(jTextField2)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))))
                    .addComponent(clearButton))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(storeButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton storeButton;
    // End of variables declaration//GEN-END:variables
}