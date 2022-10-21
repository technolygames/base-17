package paneles;
//clases
import clases.datos;
import clases.logger;
import clases.placeHolder;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
//extension larga
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.logging.Level;

public class modDatosPanel4 extends javax.swing.JPanel{
    protected int user;
    protected boolean estado;
    
    public modDatosPanel4(){
        initComponents();
        
        estado=true;
        
        botones();
        enabledComponents(true,estado);
        settings();
    }
    
    public modDatosPanel4(int code){
        initComponents();
        
        this.user=code;
        txtSearch.setText(String.valueOf(user));
        estado=false;
        
        botones();
        consulta();
        enabledComponents(false,estado);
        settings();
    }
    
    public modDatosPanel4(int code,boolean flag){
        initComponents();
        
        if(!flag){
            closeButton.setEnabled(false);
        }
        
        this.user=code;
        txtSearch.setText(String.valueOf(user));
        estado=false;
        
        botones();
        consulta();
        enabledComponents(false,estado);
        settings();
    }
    
    protected final void settings(){
        placeHolders();
    }
    
    protected final void botones(){
        var datos=new datos();
        
        closeButton.addActionListener((a)->{
            setVisible(false);
        });
        
        jCheckBox1.addActionListener((a)->{
            JCheckBox[] checkboxes={jCheckBox2,jCheckBox3,jCheckBox4,jCheckBox5};
            if(jCheckBox1.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                jTextField1.setEnabled(true);
                //función
                updateButton.addActionListener((b)->{
                    String tf1=jTextField1.getText();
                    user=Integer.parseInt(txtSearch.getText());
                    while(!tf1.isEmpty()&&jCheckBox1.isSelected()&&jTextField1.isEnabled()){
                        datos.actualizarDatosString("almacen","nombre_prod","codigo_prod",tf1,user);
                        consulta();
                        break;
                    }
                });
            }else if(!jCheckBox1.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                enabledComponents(true,estado);
                jTextField1.setEnabled(false);
                jTextField1.setText("");
                placeHolders();
            }
        });
        
        jCheckBox2.addActionListener((a)->{
            JCheckBox[] checkboxes={jCheckBox1,jCheckBox3,jCheckBox4,jCheckBox5};
            if(jCheckBox2.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                jTextField2.setEnabled(true);
                //función
                updateButton.addActionListener((b)->{
                    String tf2=jTextField2.getText();
                    user=Integer.parseInt(txtSearch.getText());
                    while(!tf2.isEmpty()&&jCheckBox2.isSelected()&&jTextField2.isEnabled()){
                        datos.actualizarDatosString("almacen","marca","codigo_prod",tf2,user);
                        consulta();
                        break;
                    }
                });
            }else if(!jCheckBox2.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                enabledComponents(true,estado);
                jTextField2.setEnabled(false);
                jTextField2.setText("");
                placeHolders();
            }
        });
        
        jCheckBox3.addActionListener((a)->{
            JCheckBox[] checkboxes={jCheckBox1,jCheckBox2,jCheckBox4,jCheckBox5};
            if(jCheckBox3.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                jTextField3.setEnabled(true);
                //función
                updateButton.addActionListener((b)->{
                    int tf3=Integer.parseInt(jTextField3.getText());
                    user=Integer.parseInt(txtSearch.getText());
                    while(tf3!=0&&jCheckBox3.isSelected()&&jTextField3.isEnabled()){
                        datos.actualizarDatosInteger("almacen","cantidad","codigo_prod",tf3,user,true);
                        consulta();
                        break;
                    }
                });
            }else if(!jCheckBox3.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                enabledComponents(true,estado);
                jTextField3.setEnabled(false);
                jTextField3.setText("");
                placeHolders();
            }
        });
        
        jCheckBox4.addActionListener((a)->{
            JCheckBox[] checkboxes={jCheckBox1,jCheckBox2,jCheckBox3,jCheckBox5};
            if(jCheckBox4.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                jTextField4.setEnabled(true);
                //función
                updateButton.addActionListener((b)->{
                    int tf4=Integer.parseInt(jTextField4.getText());
                    user=Integer.parseInt(txtSearch.getText());
                    while(tf4!=0&&jCheckBox4.isSelected()&&jTextField4.isEnabled()){
                        datos.actualizarDatosInteger("almacen","precio_unitario","codigo_prod",tf4,user,true);
                        consulta();
                        break;
                    }
                });
            }else if(!jCheckBox4.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                enabledComponents(true,estado);
                jTextField4.setEnabled(false);
                jTextField4.setText("");
                placeHolders();
            }
        });
        
        jCheckBox5.addActionListener((a)->{
            JCheckBox[] checkboxes={jCheckBox1,jCheckBox2,jCheckBox3,jCheckBox4};
            if(jCheckBox5.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //combo
                jComboBox1.setEnabled(true);
                //textfields
                enabledComponents(false,estado);
                //función
                updateButton.addActionListener((b)->{
                    String combo=jComboBox1.getModel().getSelectedItem().toString();
                    user=Integer.parseInt(txtSearch.getText());
                    while(!combo.equals(jLabel5.getText())&&jCheckBox5.isSelected()&&jComboBox1.isEnabled()){
                        datos.actualizarDatosString("almacen","stock","codigo_prod",combo,user);
                        consulta();
                        break;
                    }
                });
            }else if(!jCheckBox5.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //combo
                jComboBox1.setEnabled(false);
                jComboBox1.getModel().setSelectedItem("En Existencia");
                //textfields
                enabledComponents(true,estado);
            }
        });
        
        searchButton.addActionListener((a)->{
            consulta();
        });
        
        txtSearch.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    consulta();
                }
            }
        });
    }
    
    protected void consulta(){
        try{
            if(!txtSearch.getText().isEmpty()){
                PreparedStatement ps=new datos().getConnection().prepareStatement("select * from almacen where codigo_prod=?;");
                ps.setInt(1,Integer.parseInt(txtSearch.getText()));
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    jLabel1.setText(rs.getString("nombre_prod"));
                    jLabel2.setText(rs.getString("marca"));
                    jLabel3.setText(String.valueOf(rs.getInt("cantidad")));
                    jLabel4.setText(String.valueOf(rs.getInt("precio_unitario")));
                    jLabel5.setText(rs.getString("stock"));
                }else{
                    JOptionPane.showMessageDialog(this,"Error: no existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                    new logger(Level.WARNING).staticLogger("Error 14: no existen o no se ingresaron los datos a buscar y cambiar.\nOcurrió en '"+modDatosPanel4.class.getName()+"', en el método 'consulta()'");
                }
                ps.close();
                rs.close();
            }else{
                JOptionPane.showMessageDialog(this,"Error:\nEscribe la palabra clave que deseas buscar","Error 14",JOptionPane.WARNING_MESSAGE);
                new logger(Level.WARNING).staticLogger("Error 18: no se escribió la palabra clave para hacer la búsqueda.\nOcurrió en la clase '"+modDatosPanel4.class.getName()+"', en el método 'consulta()'");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en '"+modDatosPanel4.class.getName()+"', en el método 'consulta()'");
            new logger(Level.SEVERE).exceptionLogger(modDatosPanel4.class.getName(),"consulta-14",e.fillInStackTrace());
        }
    }
    
    protected void enabledComponents(boolean flag1,boolean flag2){
        if(flag2){
            txtSearch.setEnabled(flag1);
            searchButton.setEnabled(flag1);
        }if(!flag2){
            txtSearch.setEnabled(false);
            searchButton.setEnabled(false);
        }
    }
    
    protected void placeHolders(){
        new placeHolder(jTextField1,"Nombre del producto").inicialize();
        new placeHolder(jTextField2,"Marca").inicialize();
        new placeHolder(jTextField3,"Cantidad").inicialize();
        new placeHolder(jTextField4,"Precio unitario").inicialize();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        updateButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();

        updateButton.setText("Actualizar datos");

        closeButton.setText("Cerrar panel");

        searchButton.setText("Buscar");

        jLabel1.setText("Nombre del producto");

        jLabel2.setText("Marca");

        jLabel3.setText("Cantidad");

        jLabel4.setText("Precio unitario");

        jLabel5.setText("Stock");

        jTextField1.setEnabled(false);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jTextField2.setEnabled(false);
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jTextField3.setEnabled(false);
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jTextField4.setEnabled(false);
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En Existencia", "Agotado" }));
        jComboBox1.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox5)
                            .addComponent(jCheckBox1)
                            .addComponent(jCheckBox2)
                            .addComponent(jCheckBox3)
                            .addComponent(jCheckBox4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox1))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(this,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel4.class.getName()+"', en el método 'jTextField1KeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyPressed
    
    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(this,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel4.class.getName()+"', en el método 'jTextField2KeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyPressed
    
    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(this,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel4.class.getName()+"', en el método 'jTextField3KeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField3KeyPressed
    
    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(this,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel4.class.getName()+"', en el método 'jTextField4KeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyPressed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
