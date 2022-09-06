package paneles;
//clases
import clases.datos;
import clases.logger;
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

public class modDatosPanel3 extends javax.swing.JPanel{
    public modDatosPanel3(){
        initComponents();
        
        botones();
        settings();
    }
    
    public modDatosPanel3(int code){
        initComponents();
        
        txtSearch.setText(String.valueOf(code));
        consulta();
        
        botones();
        settings();
    }
    
    protected void settings(){
        jLabel1.setToolTipText("Nombre(s)");
        jLabel2.setToolTipText("Apellido paterno");
        jLabel3.setToolTipText("Apellido materno");
        jLabel4.setToolTipText("Empresa");
        jLabel5.setToolTipText("Contacto");
    }
    
    protected final void botones(){
        var datos=new datos();
        
        closeButton.addActionListener((a)->{
            setVisible(false);
        });
        
        jCheckBox1.addActionListener((a)->{
            JCheckBox[] checkboxes={jCheckBox2,jCheckBox3,jCheckBox4,jCheckBox5};
            if(jCheckBox1.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                jTextField1.setEnabled(true);
                //función
                if(jCheckBox1.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!jTextField1.getText().equals("")&&jCheckBox1.isSelected()==true&&jTextField1.isEnabled()==true){
                            while(!jTextField1.getText().equals("")&&jCheckBox1.isSelected()==true&&jTextField1.isEnabled()==true){
                                datos.actualizarDatosString("proveedor","nombre_prov","codigo_prov",jTextField1.getText(),Integer.parseInt(txtSearch.getText()));
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(jCheckBox1.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                txtSearch.setEnabled(true);
                jTextField1.setEnabled(false);
                jTextField1.setText("");
            }
        });
        
        jCheckBox2.addActionListener((a)->{
            JCheckBox[] checkboxes={jCheckBox1,jCheckBox3,jCheckBox4,jCheckBox5};
            if(jCheckBox2.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                jTextField2.setEnabled(true);
                //función
                if(jCheckBox2.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!jTextField2.getText().equals("")&&jCheckBox2.isSelected()==true&&jTextField2.isEnabled()==true){
                            while(!jTextField2.getText().equals("")&&jCheckBox2.isSelected()==true&&jTextField2.isEnabled()==true){
                                datos.actualizarDatosString("proveedor","apellidop_prov","codigo_prov",jTextField2.getText(),Integer.parseInt(txtSearch.getText()));
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(jCheckBox2.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                txtSearch.setEnabled(true);
                jTextField2.setEnabled(false);
                jTextField2.setText("");
            }
        });
        
        jCheckBox3.addActionListener((a)->{
            JCheckBox[] checkboxes={jCheckBox1,jCheckBox2,jCheckBox4,jCheckBox5};
            if(jCheckBox3.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                jTextField3.setEnabled(true);
                //función
                if(jCheckBox3.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!jTextField3.getText().equals("")&&jCheckBox3.isSelected()==true&&jTextField3.isEnabled()==true){
                            while(!jTextField3.getText().equals("")&&jCheckBox3.isSelected()==true&&jTextField3.isEnabled()==true){
                                datos.actualizarDatosString("proveedor","apellidom_prov","codigo_prov",jTextField3.getText(),Integer.parseInt(txtSearch.getText()));
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(jCheckBox3.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                txtSearch.setEnabled(true);
                jTextField3.setEnabled(false);
                jTextField3.setText("");
            }
        });
        
        jCheckBox4.addActionListener((a)->{
            JCheckBox[] checkboxes={jCheckBox1,jCheckBox2,jCheckBox3,jCheckBox5};
            if(jCheckBox4.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                jTextField4.setEnabled(true);
                //función
                if(jCheckBox4.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!jTextField4.getText().equals("")&&jCheckBox4.isSelected()==true&&jTextField4.isEnabled()==true){
                            while(!jTextField4.getText().equals("")&&jCheckBox4.isSelected()==true&&jTextField4.isEnabled()==true){
                                datos.actualizarDatosString("proveedor","empresa","codigo_prov",jTextField4.getText(),Integer.parseInt(txtSearch.getText()));
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(jCheckBox4.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                txtSearch.setEnabled(true);
                jTextField4.setEnabled(false);
                jTextField4.setText("");
            }
        });
        
        jCheckBox5.addActionListener((a)->{
            JCheckBox[] checkboxes={jCheckBox1,jCheckBox2,jCheckBox3,jCheckBox4};
            if(jCheckBox5.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                jTextField5.setEnabled(true);
                //función
                if(jCheckBox5.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!jTextField5.getText().equals("")&&jCheckBox5.isSelected()==true&&jTextField5.isEnabled()==true){
                            while(!jTextField5.getText().equals("")&&jCheckBox5.isSelected()==true&&jTextField5.isEnabled()==true){
                                datos.actualizarDatosInteger("proveedor","contacto","codigo_prov",Integer.parseInt(jTextField5.getText()),Integer.parseInt(txtSearch.getText()));
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(jCheckBox5.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                txtSearch.setEnabled(true);
                jTextField5.setEnabled(false);
                jTextField5.setText("");
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
            PreparedStatement ps=new datos().getConnection().prepareStatement("select * from proveedor where codigo_prov=?;");
            ps.setInt(1,Integer.parseInt(txtSearch.getText()));
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                jLabel1.setText(rs.getString("nombre_prov"));
                jLabel2.setText(rs.getString("apellidop_prov"));
                jLabel3.setText(rs.getString("apellidom_prov"));
                jLabel4.setText(rs.getString("empresa"));
                jLabel5.setText(rs.getString("contacto"));
            }else{
                JOptionPane.showMessageDialog(null,"Error: no existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                new logger(Level.WARNING).staticLogger("Error 14: no existen o no se ingresaron los datos a buscar y cambiar.\nOcurrió en '"+modDatosPanel3.class.getName()+"', en el método 'consulta()'");
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en '"+modDatosPanel3.class.getName()+"', en el método 'consulta()'");
            new logger(Level.SEVERE).exceptionLogger(modDatosPanel3.class.getName(),"consulta-14",e.fillInStackTrace());
        }
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
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jTextField2.setEnabled(false);
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jTextField3.setEnabled(false);
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jTextField4.setEnabled(false);
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jTextField5.setEnabled(false);
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeButton)
                    .addComponent(updateButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel3.class.getName()+"', en el método 'jTextField1KeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyPressed
    
    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel3.class.getName()+"', en el método 'jTextField2KeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyPressed
    
    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel3.class.getName()+"', en el método 'jTextField3KeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField3KeyPressed
    
    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel3.class.getName()+"', en el método 'jTextField4KeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyPressed
    
    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel3.class.getName()+"', en el método 'jTextField5KeyPressed()'");
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