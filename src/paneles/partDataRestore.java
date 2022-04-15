package paneles;

import clases.BackupHandler.lectorJSON;
import clases.laf;
import clases.logger;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class partDataRestore extends javax.swing.JPanel{
    public partDataRestore(){
        initComponents();
        new laf().LookAndFeel(partDataRestore.this,partDataRestore.class.getName(),"partDataRestore");
        
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
                p.load(new FileInputStream(System.getProperty("user.dir")+"/src/data/config/filechooserd.properties"));
                filechooser=new JFileChooser(p.getProperty("lastdirectory_partdr"));
                
                File f=filechooser.getCurrentDirectory();
                
                filechooser.setFileFilter(new FileNameExtensionFilter("Archivos JSON","json"));
                
                filechooser.setCurrentDirectory(f);
                
                int n=filechooser.showOpenDialog(null);
                if(JFileChooser.APPROVE_OPTION==n){
                    f=filechooser.getSelectedFile();
                    jTextField1.setText(f.toString());
                }
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 40",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 40: "+e.getMessage()+".\nOcurrió en la clase '"+partDataRestore.class.getName()+"', en el método 'botones(searchButton)'",Level.WARNING);
                new logger().exceptionLogger(partDataRestore.class.getName(),Level.WARNING,"botones.search-40",e.fillInStackTrace());
            }catch(FileNotFoundException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+partDataRestore.class.getName()+"', en el método 'botones(searchButton)'",Level.WARNING);
                new logger().exceptionLogger(partDataRestore.class.getName(),Level.WARNING,"botones.search-1IO",x.fillInStackTrace());
            }catch(IOException n){
                JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+partDataRestore.class.getName()+"', en el método 'botones(searchButton)'",Level.WARNING);
                new logger().exceptionLogger(partDataRestore.class.getName(),Level.WARNING,"botones.search-2IO",n.fillInStackTrace());
            }
        });
        
        loadDataButton.addActionListener((a)->{
            new lectorJSON().readDataProviderJson(jTextField1.getText());
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadDataButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();

        loadDataButton.setText("Cargar datos");

        closeButton.setText("Cerrar panel");

        jLabel1.setText("Dirección:");

        searchButton.setText("Buscar");

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                                .addComponent(closeButton))
                            .addComponent(jTextField1))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
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