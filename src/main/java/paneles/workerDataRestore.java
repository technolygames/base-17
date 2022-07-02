package paneles;
//clases
import clases.logger;
import clases.BackupHandler.lectorJSON;
import clases.guiMediaHandler;
//java
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
//extension larga
import java.util.logging.Level;
import javax.swing.filechooser.FileNameExtensionFilter;

public class workerDataRestore extends javax.swing.JPanel{
    public workerDataRestore(){
        initComponents();
        new guiMediaHandler(workerDataRestore.class.getName()).LookAndFeel(workerDataRestore.this);
         
        botones();
    }
    
    protected JFileChooser filechooser;
    protected Properties p;
    
    protected final void botones(){
        closeButton.addActionListener((a)->{
            setVisible(false);
        });
        
        searchButton.addActionListener((a)->{
            try{
                p=new Properties();
                p.load(new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/data/config/filechooserd.properties"));
                filechooser=new JFileChooser(p.getProperty("lastdirectory_workdr"));
                
                File f=filechooser.getCurrentDirectory();
                
                filechooser.setFileFilter(new FileNameExtensionFilter("Archivos JSON","json"));
                
                filechooser.setCurrentDirectory(f);
                
                int n=filechooser.showOpenDialog(null);
                if(JFileChooser.APPROVE_OPTION==n){
                    f=filechooser.getSelectedFile();
                    jTextField1.setText(f.toString());
                }
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 40",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 40: "+e.getMessage()+".\nOcurrió en la clase '"+workerDataRestore.class.getName()+"', en el método 'botones(searchButton)'");
                new logger(Level.SEVERE).exceptionLogger(workerDataRestore.class.getName(),"botones.search-40",e.fillInStackTrace());
            }catch(FileNotFoundException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+workerDataRestore.class.getName()+"', en el método 'botones(searchButton)'");
                new logger(Level.SEVERE).exceptionLogger(workerDataRestore.class.getName(),"botones.search-1IO",x.fillInStackTrace());
            }catch(IOException n){
                JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+workerDataRestore.class.getName()+"', en el método 'botones(searchButton)'");
                new logger(Level.SEVERE).exceptionLogger(workerDataRestore.class.getName(),"botones.search-2IO",n.fillInStackTrace());
            }
        });
        
        loadDataButton.addActionListener((a)->{
            new lectorJSON().readDataWorkerJson(jTextField1.getText());
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        loadDataButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        jLabel1.setText("Dirección:");

        loadDataButton.setText("Cargar datos");

        searchButton.setText("Buscar");

        closeButton.setText("Cerrar panel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(loadDataButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(closeButton))
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loadDataButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton loadDataButton;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}