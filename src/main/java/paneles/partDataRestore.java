package paneles;
//clases
import clases.Datos;
import clases.logger;
import clases.backuphandler.LectorJson;
import clases.mvc.Controlador;
//java
import java.io.File;
import java.sql.SQLException;
import javax.swing.JFileChooser;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class partDataRestore extends javax.swing.JPanel{
    public partDataRestore(){
        initComponents();
        
        botones();
        settings();
    }
    
    protected Controlador modelo;
    
    public partDataRestore(Controlador modelo){
        initComponents();
        
        this.modelo=modelo;
        
        botones();
        settings();
    }
    
    protected JFileChooser filechooser;
    
    protected final void settings(){
        loadDataButton.setEnabled(false);
    }
    
    protected final void botones(){
        closeButton.addActionListener(a->
            setVisible(false)
        );
        
        searchButton.addActionListener(a->{
            filechooser=new JFileChooser("data/databackup/Socios");
            
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
        
        loadDataButton.addActionListener(a->{
            try{
                new Datos(modelo).insertarDatosSocio(new LectorJson().readDataPartnerJson(jTextField1.getText()));
            }catch(SQLException e){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,"botones.loadData","11");
            }
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
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