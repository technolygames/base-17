package paneles;
//clases
import clases.logger;
import clases.Thread1;
import clases.Thread3;
import clases.mvc.Controlador;
import venTerciarias.databaseWindow;
//java
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
//extension larga
import java.util.logging.Level;
import javax.swing.filechooser.FileNameExtensionFilter;

public class databaseImport extends javax.swing.JPanel{
    public databaseImport(){
        initComponents();
        
        botones();
        configIn();
    }
    
    protected Controlador modelo;
    
    public databaseImport(Controlador modelo){
        initComponents();
        
        this.modelo=modelo;
        
        botones();
        configIn();
    }
    
    protected Properties p;
    protected InputStream is;
    
    protected String database;
    protected String host;
    protected String user;
    protected String pass;
    
    protected String methodName;
    
    protected final void configIn(){
        jTextField3.setText(databaseWindow.nombredb);
        
        methodName="settings";
        try{
            p=new Properties();
            p.load(new FileInputStream("data/config/databaseInfo.properties"));
            
            database=p.getProperty("database");
            host=p.getProperty("ip");
            user=p.getProperty("user");
            pass=p.getProperty("pass");
            
            jTextField1.setText(user);
            jPasswordField1.setText(pass);
            
            p.clear();
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"1IO");
        }catch(IOException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,x,methodName,"2IO");
        }
    }
    
    protected final void botones(){
        closeButton.addActionListener(a->
            setVisible(false)
        );
        
        createButton.addActionListener(a->
            new databaseWindow(new javax.swing.JFrame(),true).setVisible(true)
        );
        
        fileButton.addActionListener(a->{
            methodName="botones.file";
            try{
                p=new Properties();
                p.load(new FileInputStream("data/config/filechooserd.properties"));
                JFileChooser chooser=new JFileChooser(p.getProperty("lastdirectory_database_import"));
                
                chooser.setMultiSelectionEnabled(false);
                chooser.setFileFilter(new FileNameExtensionFilter("Archivo SQL","sql"));
                chooser.setAcceptAllFileFilterUsed(false);
                
                if(JFileChooser.APPROVE_OPTION==chooser.showOpenDialog(null)){
                    File f=chooser.getSelectedFile();
                    
                    jTextField2.setText(f.getPath());
                    
                    p.setProperty("lastdirectory_database_import",f.getParent());
                    p.store(new FileOutputStream("data/config/filechooserd.properties"),"JFileChooserDirection");
                }
            }catch(IOException e){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"2IO");
            }
        });
        
        importButton.addActionListener(a->{
            configOut();
            importDatabase();
        });
    }
    
    protected void importDatabase(){
        methodName="importDatabase";
        new Thread(()->{
            String user1=jTextField1.getText();
            String pass1=String.valueOf(jPasswordField1.getPassword());
            String db=jTextField3.getText();
            String dbDir=jTextField2.getText();
            
            try{
                p=new Properties();
                p.load(new FileInputStream("data/config/env.properties"));
                
                Process pr=Runtime.getRuntime().exec("cmd /c "+p.getProperty("local_mysql")+"mysql.exe --user="+user1+" -p "+db+"<"+dbDir+" --password="+pass1+" --host="+host);
                new Thread(new Thread3(pr.getErrorStream())).start();
                
                is=new FileInputStream(dbDir);
                
                new Thread(new Thread1(is,pr.getOutputStream())).start();
                
                JOptionPane.showMessageDialog(this,"Se ha importado correctamente la base de datos","Rel 2E",JOptionPane.INFORMATION_MESSAGE);
                logger.staticLogger(Level.INFO,"Rel 2E: se importó correctamente la base de datos.\nOcurrió en el método 'importDatabase()'.\nUsuario que hizo la acción: "+String.valueOf(modelo.getUserID()),this.getClass().getName());
                
                is.close();
            }catch(IOException e){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"2IO");
            }catch(NullPointerException x){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,x,methodName,"0");
            }
        }).start();
    }
    
    protected void configOut(){
        methodName="configOut";
        if(!jTextField3.getText().equals(database)){
            switch(JOptionPane.showConfirmDialog(this,"¿Deseas usar la base de datos importada como principal?","Notice 1",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE)){
                case 0:{
                    try{
                        p=new Properties();
                        p.load(new FileInputStream("data/config/databaseInfo.properties"));
                        p.setProperty("database",jTextField3.getText());
                        p.store(new FileOutputStream("data/config/databaseInfo.properties"),"DatabaseConfig");
                    }catch(FileNotFoundException e){
                        new logger(Level.SEVERE, this.getClass().getName()).catchException(this,e,methodName,"1IO");
                    }catch(IOException x){
                        new logger(Level.SEVERE, this.getClass().getName()).catchException(this,x,methodName,"2IO");
                    }
                    break;
                }
                default:{
                    break;
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        importButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        fileButton = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        createButton = new javax.swing.JButton();

        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        jLabel3.setText("Base de datos a usar:");

        importButton.setText("Importar");

        closeButton.setText("Cerrar panel");

        jLabel4.setText("Base de datos a importar:");

        fileButton.setText("Buscar");

        createButton.setText("Crear base de datos");

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
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(jTextField3)
                                    .addComponent(jPasswordField1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(createButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(closeButton))
                                    .addComponent(jTextField2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fileButton))))
                    .addComponent(importButton))
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
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(importButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton createButton;
    private javax.swing.JButton fileButton;
    private javax.swing.JButton importButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    public static javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}