package paneles;
//clases
import clases.logger;
import clases.thread1;
import clases.thread3;
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
    
    protected String host;
    
    protected final void settings(){
        try{
            p=new Properties();
            p.load(new FileInputStream("data/config/databaseInfo.properties"));
            
            host=p.getProperty("ip");
            
            jTextField1.setText(p.getProperty("user"));
            jPasswordField1.setText(p.getProperty("pass"));
            jTextField3.setText(p.getProperty("database"));
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(this,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+databaseExport.class.getName()+"', en el método 'settings()'");
            new logger(Level.SEVERE).exceptionLogger(databaseExport.class.getName(),"settings-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(this,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+databaseExport.class.getName()+"', en el método 'settings()'");
            new logger(Level.SEVERE).exceptionLogger(databaseExport.class.getName(),"settings-2IO",x.fillInStackTrace());
        }
    }
    
    protected final void botones(){
        closeButton.addActionListener((a)->{
            setVisible(false);
        });
        
        exportButton.addActionListener((a)->{
            exportDatabase();
        });
    }
    
    protected void exportDatabase(){
        new Thread(()->{
            String user=jTextField1.getText();
            String pass=String.valueOf(jPasswordField1.getPassword());
            String db=jTextField3.getText();
            
            try{
                p=new Properties();
                p.load(new FileInputStream("data/config/env.properties"));
                
                File f=new File("data/database/MySQL/"+db+".sql");
                for(int i=0;f.exists();i++){
                    f=new File("data/database/MySQL/"+db+"-("+i+").sql");
                }
                
                Process pr=Runtime.getRuntime().exec("cmd /c "+p.getProperty("local_mysql")+"mysqldump.exe --user="+user+" -p "+db+">"+f.getPath()+" --password="+pass+" --host="+host);
                new thread3(pr.getErrorStream()).run();
                
                os=new FileOutputStream(f);
                
                new Thread(new thread1(pr.getInputStream(),os)).start();
                
                JOptionPane.showMessageDialog(this,"Se ha exportado correctamente la base de datos","Rel 3E",JOptionPane.INFORMATION_MESSAGE);
                new logger(Level.INFO).staticLogger("Rel 3E: se exportó correctamente la base de datos.\nOcurrió en la clase '"+databaseExport.class.getName()+"', en el método 'exportDatabase()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
                
                os.flush();
                os.close();
            }catch(IOException e){
                JOptionPane.showMessageDialog(this,"Error:\n"+e.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 2IO: "+e.getMessage()+".\nOcurrió en la clase '"+databaseExport.class.getName()+"', en el método 'exportDatabase()'");
                new logger(Level.SEVERE).exceptionLogger(databaseExport.class.getName(),"exportDatabase-2IO",e.fillInStackTrace());
            }catch(NullPointerException x){
                JOptionPane.showMessageDialog(this,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+databaseExport.class.getName()+"', en el método 'exportDatabase()'");
                new logger(Level.SEVERE).exceptionLogger(databaseExport.class.getName(),"exportDatabase-0",x.fillInStackTrace());
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