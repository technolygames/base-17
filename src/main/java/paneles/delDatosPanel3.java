package paneles;
//clases
import clases.datos;
import clases.logger;
import clases.BackupHandler.escritorJSON;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//java
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

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
    
    protected final void botones(){
        closeButton.addActionListener((a)->{
            setVisible(false);
        });
       
        deleteButton.addActionListener((a)->{
            deleteData();
        });
        
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
        try{
            if(!jTextField1.getText().equals("")){
                int codigo=Integer.parseInt(jTextField1.getText());
                int opcion=JOptionPane.showConfirmDialog(null,"¿Deseas crear una copia de seguridad?","Notice 1",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                switch(opcion){
                    case 0:{
                        new escritorJSON().writeDataProviderJson(codigo);
                        new datos().eliminarDatosProveedor(codigo);
                        break;
                    }
                    case 1:{
                        new datos().eliminarDatosProveedor(codigo);
                        break;
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,"Escribe el número de identificación da eliminar","Error 18",JOptionPane.WARNING_MESSAGE);
                new logger(Level.WARNING).staticLogger("Error 18: no se escribió correctamente el código del empleado a eliminar.\nOcurrió en la clase '"+delDatosPanel3.class.getName()+"', en el método 'deleteData()'");
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 32: "+e.getMessage()+".\nOcurrió en '"+delDatosPanel3.class.getName()+"', en el método 'deleteData()'");
            new logger(Level.SEVERE).exceptionLogger(delDatosPanel3.class.getName(),"deleteData-32",e.fillInStackTrace());
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
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+delDatosPanel3.class.getName()+"', en el método 'jTextField1KeyPressed()'");
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