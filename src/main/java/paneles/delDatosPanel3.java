package paneles;
//clases
import clases.Datos;
import clases.logger;
import clases.backuphandler.EscritorJson;
//java
import java.sql.SQLException;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class delDatosPanel3 extends javax.swing.JPanel{
    public delDatosPanel3(){
        initComponents();
        
        botones();
    }
    
    public delDatosPanel3(int code){
        initComponents();
        
        jTextField1.setText(String.valueOf(code));
        
        botones();
    }
    
    public delDatosPanel3(int code,boolean flag){
        initComponents();
        
        if(!flag){
            closeButton.setEnabled(false);
        }
        
        jTextField1.setText(String.valueOf(code));
        
        botones();
    }
    
    protected final void botones(){
        closeButton.addActionListener(a->
            setVisible(false)
        );
       
        deleteButton.addActionListener(a->
            deleteData()
        );
        
        jTextField1.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyChar()==KeyEvent.VK_ENTER){
                    deleteData();
                }
            }
        });
    }
    
    protected void deleteData(){
        String methodName="deleteData";
        var datos=new Datos();
        
        try{
            if(!jTextField1.getText().isEmpty()){
                int codigo=Integer.parseInt(jTextField1.getText());
                switch(JOptionPane.showConfirmDialog(this,"¿Deseas crear una copia de seguridad?","Notice 1",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE)){
                    case 0:{
                        new EscritorJson().writeDataProviderJson(codigo);
                        datos.eliminarDatosProveedor(codigo);
                        break;
                    }
                    case 1:{
                        datos.eliminarDatosProveedor(codigo);
                        break;
                    }
                    default:
                        break;
                }
            }else{
                new logger(Level.WARNING).storeAndViewError18(this,delDatosPanel3.class.getName(),methodName);
            }
        }catch(NumberFormatException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,delDatosPanel3.class.getName(),methodName,"32");
        }catch(SQLException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,delDatosPanel3.class.getName(),methodName,"13");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        closeButton.setText("Cerrar panel");

        deleteButton.setText("Eliminar datos");

        jLabel1.setText("Código del proveedor:");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton)
                        .addGap(0, 213, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 241, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(deleteButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new logger(Level.WARNING).storeAndViewLetterInputWarning(this,delDatosPanel3.class.getName(),"jTextField1KeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyPressed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}