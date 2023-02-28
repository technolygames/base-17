package paneles;
//clases
import clases.Datos;
import clases.logger;
import clases.backuphandler.EscritorJson;
import clases.mvc.Controlador;
//java
import java.sql.SQLException;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class delDatosPanel1 extends javax.swing.JPanel{
    public delDatosPanel1(){
        initComponents();
        
        botones();
    }
    
    protected Controlador modelo;
    
    public delDatosPanel1(Controlador modelo){
        initComponents();
        
        this.modelo=modelo;
        
        botones();
    }
    
    public delDatosPanel1(int code,Controlador modelo){
        initComponents();
        
        this.modelo=modelo;
        
        jTextField1.setText(String.valueOf(code));
        
        botones();
    }
    
    public delDatosPanel1(int code,boolean flag,Controlador modelo){
        initComponents();
        
        this.modelo=modelo;
        
        if(!flag){
            closeButton.setEnabled(false);
        }
        
        jTextField1.setText(String.valueOf(code));
        
        botones();
    }
    
    protected final void botones(){
        closeButton.addActionListener(e->
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
        var datos=new Datos(modelo);
        
        try{
            if(!jTextField1.getText().isEmpty()){
                int codigo=Integer.parseInt(jTextField1.getText());
                switch(JOptionPane.showConfirmDialog(this,"¿Deseas crear una copia de seguridad?","Notice 1",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE)){
                    case 0:{
                        new EscritorJson(modelo).writeDataWorkerJson(codigo);
                        datos.eliminarDatosProductos(codigo);
                        datos.eliminarDatosConteo(codigo); 
                        datos.eliminarDatosEmpleado(codigo);
                        break;
                    }
                    case 1:{
                        datos.eliminarDatosProductos(codigo);
                        datos.eliminarDatosConteo(codigo);
                        datos.eliminarDatosEmpleado(codigo);
                        break;
                    }
                    default:
                        break;
                }
            }else{
                new logger(Level.WARNING,this.getClass().getName()).storeError18(this,methodName);
            }
        }catch(NumberFormatException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(null,e,methodName,"32");
        }catch(SQLException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(null,x,methodName,"13");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        deleteButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        closeButton = new javax.swing.JButton();

        deleteButton.setText("Eliminar datos");

        jLabel1.setText("Código del empleado:");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        closeButton.setText("Cerrar panel");

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
                        .addGap(0, 273, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 372, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeLetterInputWarning(this,"jTextField1KeyPressed");
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