package paneles;
//clases
import clases.Datos;
import clases.logger;
import clases.PlaceHolder;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JCheckBox;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class modDatosPanel2 extends javax.swing.JPanel{
    protected int user;
    protected boolean estado;
    protected String methodName;
    
    protected JCheckBox[] checkboxes;
    
    public modDatosPanel2(){
        initComponents();
        
        estado=true;
        
        botones();
        enabledComponents(true,estado);
        settings();
    }
    
    public modDatosPanel2(int code){
        initComponents();
        
        this.user=code;
        txtSearch.setText(String.valueOf(user));
        estado=false;
        
        botones();
        consulta();
        enabledComponents(false,estado);
        settings();
    }
    
    public modDatosPanel2(int code,boolean flag){
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
        var datos=new Datos();
        String tabla="socios";
        String campo="codigo_part";
        
        closeButton.addActionListener(a->
            setVisible(false)
        );
        
        jCheckBox1.addActionListener(a->{
            methodName="botones.jCheckBox1";
            checkboxes=new JCheckBox[]{jCheckBox2,jCheckBox3,jCheckBox4,jCheckBox5,jCheckBox6};
            if(jCheckBox1.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                jTextField1.setEnabled(true);
                //función
                updateButton.addActionListener(b->{
                    try{
                        String tf1=jTextField1.getText();
                        user=Integer.parseInt(txtSearch.getText());
                        while(!tf1.isEmpty()&&jCheckBox1.isSelected()&&jTextField1.isEnabled()){
                            datos.actualizarDatosString(tabla,"nombre_part",campo,tf1,user);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"12");
                    }
                });
            }else{
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
        
        jCheckBox2.addActionListener(a->{
            methodName="botones.jCheckBox2";
            checkboxes=new JCheckBox[]{jCheckBox1,jCheckBox3,jCheckBox4,jCheckBox5,jCheckBox6};
            if(jCheckBox2.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                jTextField2.setEnabled(true);
                //función
                updateButton.addActionListener(b->{
                    try{
                        String tf2=jTextField2.getText();
                        user=Integer.parseInt(txtSearch.getText());
                        while(!tf2.isEmpty()&&jCheckBox2.isSelected()&&jTextField2.isEnabled()){
                            datos.actualizarDatosString(tabla,"apellidop_part",campo,tf2,user);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"12");
                    }
                });
            }else{
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
        
        jCheckBox3.addActionListener(a->{
            methodName="botones.jCheckBox3";
            checkboxes=new JCheckBox[]{jCheckBox1,jCheckBox2,jCheckBox4,jCheckBox5,jCheckBox6};
            if(jCheckBox3.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                jTextField3.setEnabled(true);
                //función
                updateButton.addActionListener(b->{
                    try{
                        String tf3=jTextField3.getText();
                        user=Integer.parseInt(txtSearch.getText());
                        while(!tf3.isEmpty()&&jCheckBox3.isSelected()&&jTextField3.isEnabled()){
                            datos.actualizarDatosString(tabla,"apellidom_part",campo,tf3,user);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"12");
                    }
                });
            }else{
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
        
        jCheckBox4.addActionListener(a->{
            methodName="botones.jCheckBox4";
            checkboxes=new JCheckBox[]{jCheckBox1,jCheckBox2,jCheckBox3,jCheckBox5,jCheckBox6};
            if(jCheckBox4.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //combo
                jComboBox1.setEnabled(true);
                //textfields
                enabledComponents(false,estado);
                //función
                updateButton.addActionListener(b->{
                    try{
                        String combo=jComboBox1.getModel().getSelectedItem().toString();
                        user=Integer.parseInt(txtSearch.getText());
                        while(!combo.equals(jLabel4.getText())&&jCheckBox4.isSelected()&&jComboBox1.isEnabled()){
                            datos.actualizarDatosString(tabla,"tipo_socio",campo,combo,user);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"12");
                    }
                });
            }else{
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //combo
                jComboBox1.setEnabled(false);
                jComboBox1.getModel().setSelectedItem("Item 1");
                //textfields
                enabledComponents(true,estado);
            }
        });
        
        jCheckBox5.addActionListener(a->{
            methodName="botones.jCheckBox5";
            checkboxes=new JCheckBox[]{jCheckBox1,jCheckBox2,jCheckBox3,jCheckBox4,jCheckBox6};
            if(jCheckBox5.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                jTextField4.setEnabled(true);
                //función
                updateButton.addActionListener(b->{
                    try{
                        String tf4=jTextField4.getText();
                        user=Integer.parseInt(txtSearch.getText());
                        while(!tf4.isEmpty()&&jCheckBox5.isSelected()&&jTextField4.isEnabled()){
                            datos.actualizarDatosString(tabla,"correo",campo,tf4,user);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"12");
                    }
                });
            }else{
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
        
        jCheckBox6.addActionListener(a->{
            methodName="botones.jCheckBox6";
            checkboxes=new JCheckBox[]{jCheckBox1,jCheckBox2,jCheckBox3,jCheckBox4,jCheckBox5};
            if(jCheckBox6.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                jTextField5.setEnabled(true);
                //función
                updateButton.addActionListener(b->{
                    try{
                        String tf5=jTextField5.getText();
                        user=Integer.parseInt(txtSearch.getText());
                        while(!tf5.isEmpty()&&jCheckBox6.isSelected()&&jTextField5.isEnabled()){
                            datos.actualizarDatosString(tabla,"rfc",campo,tf5,user);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"12");
                    }
                });
            }else{
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                enabledComponents(true,estado);
                jTextField5.setEnabled(false);
                jTextField5.setText("");
                placeHolders();
            }
        });
        
        searchButton.addActionListener(a->
            consulta()
        );
        
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
        methodName="consulta";
        try{
            if(!txtSearch.getText().isEmpty()){
                PreparedStatement ps=new Datos().getConnection().prepareStatement("select * from socios where codigo_part=?;");
                ps.setInt(1,Integer.parseInt(txtSearch.getText()));
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    jLabel1.setText(rs.getString("nombre_part"));
                    jLabel2.setText(rs.getString("apellidop_part"));
                    jLabel3.setText(rs.getString("apellidom_part"));
                    jLabel4.setText(rs.getString("tipo_socio"));
                    jLabel5.setText(rs.getString("correo"));
                    jLabel6.setText(rs.getString("rfc"));
                }else{
                    new logger(Level.WARNING,this.getClass().getName()).storeError14(this,methodName);
                }
                ps.close();
                rs.close();
            }else{
                new logger(Level.WARNING,this.getClass().getName()).storeError18(this,methodName);
            }
        }catch(SQLException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"14");
        }
    }
    
    protected void enabledComponents(boolean flag1, boolean flag2){
        if(flag2){
            txtSearch.setEnabled(flag1);
            searchButton.setEnabled(flag1);
        }
        if(!flag2){
            txtSearch.setEnabled(false);
            searchButton.setEnabled(false);
        }
    }
    
    protected void placeHolders(){
        new PlaceHolder(jTextField1,"Nombre(s)").inicialize();
        new PlaceHolder(jTextField2,"Apellido paterno").inicialize();
        new PlaceHolder(jTextField3,"Apellido materno").inicialize();
        new PlaceHolder(jTextField4,"Correo").inicialize();
        new PlaceHolder(jTextField5,"RFC").inicialize();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox4 = new javax.swing.JCheckBox();
        updateButton = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jCheckBox5 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jCheckBox6 = new javax.swing.JCheckBox();

        closeButton.setText("Cerrar panel");

        searchButton.setText("Buscar");

        jLabel1.setText("Nombre(s)");

        jTextField1.setEnabled(false);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel2.setText("Apellido paterno");

        jTextField2.setEnabled(false);
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jLabel3.setText("Apellido materno");

        jTextField3.setEnabled(false);
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jLabel4.setText("Tipo de socio");

        updateButton.setText("Actualizar datos");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setEnabled(false);

        jLabel5.setText("Correo");

        jTextField4.setEnabled(false);

        jLabel6.setText("RFC");

        jTextField5.setEnabled(false);

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jCheckBox1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jCheckBox2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jCheckBox4)
                                                .addComponent(jCheckBox3))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField2)
                                        .addComponent(jTextField3)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton)))
                .addContainerGap(73, Short.MAX_VALUE))
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
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jCheckBox1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jCheckBox2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(jCheckBox3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(jCheckBox4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jCheckBox5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jCheckBox6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeNumberInputWarning(this,"jTextField1KeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyPressed
    
    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeNumberInputWarning(this,"jTextField2KeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyPressed
    
    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeNumberInputWarning(this,"jTextField3KeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField3KeyPressed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}