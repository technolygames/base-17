package paneles;
//clases
import clases.Datos;
import clases.logger;
//java
import java.sql.SQLException;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class delDatosPanel4 extends javax.swing.JPanel{
    public delDatosPanel4(){
        initComponents();
        
        botones();
    }
    
    public delDatosPanel4(int code){
        initComponents();
        
        jTextField1.setText(String.valueOf(code));
        
        botones();
    }
    
    public delDatosPanel4(int code,boolean flag){
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
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    deleteData();
                }
            }
        });
    }
    
    protected void deleteData(){
        String methodName="deleteData";
        try{
            if(!jTextField1.getText().isEmpty()){
                var texto=Integer.parseInt(jTextField1.getText());
                new Datos().eliminarDatosAlmacen(texto);
            }else{
                new logger(Level.WARNING,this.getClass().getName()).storeAndViewError18(this,methodName);
            }
        }catch(NumberFormatException e){
            new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(null,e,methodName,"32");
        }catch(SQLException x){
            new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(null,x,methodName,"13");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        deleteButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        deleteButton.setText("Eliminar datos");

        closeButton.setText("Cerrar  panel");

        jLabel1.setText("Código del producto:");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
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
                        .addGap(0, 180, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeAndViewLetterInputWarning(this,"jTextField1KeyPressed");
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