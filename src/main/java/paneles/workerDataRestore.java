package paneles;
//clases
import clases.backuphandler.LectorJson1;
//java
import java.io.File;
import javax.swing.JFileChooser;
//extension larga
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class workerDataRestore extends javax.swing.JPanel{
    public workerDataRestore(){
        initComponents();
        
        botones();
        settings();
    }
    
    protected JFileChooser filechooser;
    
    protected final void settings(){
        loadDataButton.setEnabled(false);
    }
    
    protected final void botones(){
        closeButton.addActionListener((a)->{
            setVisible(false);
        });
        
        searchButton.addActionListener((a)->{
            filechooser=new JFileChooser("data/databackup/Empleados");
            
            filechooser.setMultiSelectionEnabled(false);
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.setFileFilter(new FileNameExtensionFilter("Archivos JSON","json"));
            
            if(JFileChooser.APPROVE_OPTION==filechooser.showOpenDialog(null)){
                File f=filechooser.getSelectedFile();
                jTextField1.setText(f.toString());
                
                unlockLoadButton();
            }
        });
        
        jTextField1.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent a){
                unlockLoadButton();
            }
        });
        
        loadDataButton.addActionListener((a)->{
            new LectorJson1().readDataWorkerJson(jTextField1.getText());
        });
    }
    
    protected void unlockLoadButton(){
        if(!jTextField1.getText().isEmpty()){
            loadDataButton.setEnabled(true);
        }else{
            loadDataButton.setEnabled(false);
        }
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