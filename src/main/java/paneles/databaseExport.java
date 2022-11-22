package paneles;
//clases
import clases.Dirs;
import clases.logger;
import clases.Thread1;
import clases.Thread3;
import venPrimarias.start;
//java
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

public class databaseExport extends javax.swing.JPanel{
    public databaseExport(){
        initComponents();
        
        botones();
        settings();
    }
    
    protected Properties p;
    protected OutputStream os;
    
    protected String database;
    protected String host;
    protected String pass;
    protected String user;
    
    protected String methodName;
    
    protected final void settings(){
        methodName="settings";
        try{
            p=new Properties();
            p.load(new FileInputStream("data/config/databaseInfo.properties"));
            
            database=p.getProperty("database");
            host=p.getProperty("ip");
            pass=p.getProperty("pass");
            user=p.getProperty("user");
            
            jTextField1.setText(user);
            jPasswordField1.setText(pass);
            jTextField3.setText(database);
            
            p.clear();
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,e,databaseExport.class.getName(),methodName,"1IO");
        }catch(IOException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,x,databaseExport.class.getName(),methodName,"2IO");
        }
    }
    
    protected final void botones(){
        closeButton.addActionListener(a->
            setVisible(false)
        );
        
        exportButton.addActionListener(a->
            exportDatabase()
        );
    }
    
    protected void exportDatabase(){
        methodName="exportDatabase";
        new Thread(()->{
            String user1=jTextField1.getText();
            String pass2=String.valueOf(jPasswordField1.getPassword());
            String db=jTextField3.getText();
            
            try{
                p=new Properties();
                p.load(new FileInputStream("data/config/env.properties"));
                
                String userdir=Dirs.userdir;
                
                File f=new File(userdir+"\\data\\database\\MySQL\\"+db+".sql");
                for(int i=1;f.exists();i++){
                    f=new File(userdir+"\\data\\database\\MySQL\\"+db+"-("+i+").sql");
                }
                
                String path=f.getPath();
                
                Process pr=Runtime.getRuntime().exec("cmd /c "+p.getProperty("local_mysql")+"mysqldump.exe --user="+user1+" -p "+db+" --result-file="+path+" --password="+pass2+" --host="+host+" --hex-blob --dump-date --compress");
                new Thread(new Thread3(pr.getErrorStream())).start();
                
                os=new FileOutputStream(path);
                
                new Thread(new Thread1(pr.getInputStream(),os)).start();
                
                JOptionPane.showMessageDialog(this,"Se ha exportado correctamente la base de datos","Rel 3E",JOptionPane.INFORMATION_MESSAGE);
                new logger(Level.INFO).staticLogger("Rel 3E: se exportó correctamente la base de datos.\nOcurrió en la clase '"+databaseExport.class.getName()+"', en el método 'exportDatabase()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
                
                os.flush();
                os.close();
            }catch(IOException e){
                new logger(Level.SEVERE).storeAndViewCaughtException(this,e,databaseExport.class.getName(),methodName,"2IO");
            }catch(NullPointerException x){
                new logger(Level.SEVERE).storeAndViewCaughtException(this,x,databaseExport.class.getName(),methodName,"0");
            }
        }).start();
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
        closeButton = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();

        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        jLabel3.setText("Base de datos:");

        exportButton.setText("Exportar");

        closeButton.setText("Cerrar panel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(exportButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField3)
                            .addComponent(jPasswordField1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 213, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton exportButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}