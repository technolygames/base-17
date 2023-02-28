package paneles;
//clases
import clases.Datos;
import clases.logger;
import clases.PlaceHolder;
import clases.mvc.Controlador;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JCheckBox;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class modDatosPanel3 extends javax.swing.JPanel{
    protected int user;
    protected boolean estado;
    protected String methodName;
    
    protected JCheckBox[] checkboxes;
    
    protected Controlador modelo;
    
    public modDatosPanel3(){
        initComponents();
        
        botones();
        settings();
    }
    
    public modDatosPanel3(Controlador modelo){
        initComponents();
        
        this.modelo=modelo;
        
        botones();
        settings();
    }
    
    public modDatosPanel3(int code,Controlador modelo){
        initComponents();
        
        this.user=code;
        this.modelo=modelo;
        txtSearch.setText(String.valueOf(code));
        consulta();
        
        botones();
        settings();
    }
    
    public modDatosPanel3(int code,boolean flag,Controlador modelo){
        initComponents();
        
        if(!flag){
            closeButton.setEnabled(false);
        }
        
        this.user=code;
        this.modelo=modelo;
        txtSearch.setText(String.valueOf(user));
        consulta();
        
        botones();
        settings();
    }
    
    protected final void settings(){
        placeHolders();
    }
    
    protected final void botones(){
        var datos=new Datos(modelo);
        String tabla="proveedor";
        String campo="codigo_prov";
        
        closeButton.addActionListener(a->
            setVisible(false)
        );
        
        jCheckBox1.addActionListener(a->{
            methodName="botones.jCheckBox1";
            checkboxes=new JCheckBox[]{jCheckBox2,jCheckBox3,jCheckBox4,jCheckBox5};
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
                        while(!tf1.equals("")&&jCheckBox1.isSelected()&&jTextField1.isEnabled()){
                            datos.actualizarDatosString(tabla,"nombre_prov",campo,tf1,user, true);
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
            checkboxes=new JCheckBox[]{jCheckBox1,jCheckBox3,jCheckBox4,jCheckBox5};
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
                            datos.actualizarDatosString(tabla,"apellidop_prov",campo,tf2,user, true);
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
            checkboxes=new JCheckBox[]{jCheckBox1,jCheckBox2,jCheckBox4,jCheckBox5};
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
                            datos.actualizarDatosString(tabla,"apellidom_prov",campo,tf3,user, true);
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
            checkboxes=new JCheckBox[]{jCheckBox1,jCheckBox2,jCheckBox3,jCheckBox5};
            if(jCheckBox4.isSelected()){
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
                        while(!tf4.isEmpty()&&jCheckBox4.isSelected()&&jTextField4.isEnabled()){
                            datos.actualizarDatosString(tabla,"empresa",campo,tf4,user, true);
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
        
        jCheckBox5.addActionListener(a->{
            methodName="botones.jCheckBox5";
            checkboxes=new JCheckBox[]{jCheckBox1,jCheckBox2,jCheckBox3,jCheckBox4};
            if(jCheckBox5.isSelected()){
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
                        int tf5=Integer.parseInt(jTextField5.getText());
                        user=Integer.parseInt(txtSearch.getText());
                        while(tf5!=0&&jCheckBox5.isSelected()&&jTextField5.isEnabled()){
                            datos.actualizarDatosInteger(tabla,"contacto",campo,tf5,user,true);
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
                PreparedStatement ps=new Datos(modelo).getConnection().prepareStatement("select * from proveedor where codigo_prov=?;");
                ps.setInt(1,Integer.parseInt(txtSearch.getText()));
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    jLabel1.setText(rs.getString("nombre_prov"));
                    jLabel2.setText(rs.getString("apellidop_prov"));
                    jLabel3.setText(rs.getString("apellidom_prov"));
                    jLabel4.setText(rs.getString("empresa"));
                    jLabel5.setText(rs.getString("contacto"));
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
    
    protected void enabledComponents(boolean flag1,boolean flag2){
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
        new PlaceHolder(jTextField4,"Empresa").inicialize();
        new PlaceHolder(jTextField5,"Contacto").inicialize();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jTextField5 = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();

        closeButton.setText("Cerrar panel");

        searchButton.setText("Buscar");

        jLabel1.setText("Nombre(s)");

        jLabel2.setText("Apellido paterno");

        jLabel3.setText("Apellido materno");

        jLabel4.setText("Empresa");

        jLabel5.setText("Contacto");

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

        jTextField5.setEnabled(false);
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
        });

        updateButton.setText("Actualizar datos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox5)
                            .addComponent(jCheckBox3)
                            .addComponent(jCheckBox4)
                            .addComponent(jCheckBox2)
                            .addComponent(jCheckBox1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jCheckBox1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jCheckBox2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jCheckBox3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jCheckBox4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jCheckBox5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeButton)
                    .addComponent(updateButton))
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
    
    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeNumberInputWarning(this,"jTextField4KeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyPressed
    
    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeLetterInputWarning(this,"jTextField5KeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField5KeyPressed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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