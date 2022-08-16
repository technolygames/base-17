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

public class modDatosPanel2 extends javax.swing.JPanel{
    public modDatosPanel2(){
        initComponents();
        
        botones();
        settings();
    }
    
    protected void settings(){
        jLabel1.setToolTipText("Nombre(s)");
        jLabel2.setToolTipText("Apellido paterno");
        jLabel3.setToolTipText("Apellido materno");
        jLabel4.setToolTipText("Tipo de socio");
        jLabel5.setToolTipText("Correo");
        jLabel6.setToolTipText("RFC");
    }
    
    protected final void botones(){
        var datos=new datos();
        
        closeButton.addActionListener((a)->{
            setVisible(false);
        });
        
        jCheckBox1.addActionListener((a)->{
            JCheckBox[] checkboxes={jCheckBox2,jCheckBox3,jCheckBox4,jCheckBox5,jCheckBox6};
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
                        if(!jTextField1.getText().equals("")&&jCheckBox1.isSelected()==true){
                            while(!jTextField1.getText().equals("")&&jCheckBox1.isSelected()==true){
                                datos.actualizarDatos("socios set nombre_part='"+jTextField1.getText()+"' where codigo_part='"+Integer.parseInt(txtSearch.getText())+"';");
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
            JCheckBox[] checkboxes={jCheckBox1,jCheckBox3,jCheckBox4,jCheckBox5,jCheckBox6};
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
                        if(!jTextField2.getText().equals("")&&jCheckBox2.isSelected()==true){
                            while(!jTextField2.getText().equals("")&&jCheckBox2.isSelected()==true){
                                datos.actualizarDatos("socios set apellidop_part='"+jTextField2.getText()+"' where codigo_part='"+Integer.parseInt(txtSearch.getText())+"';");
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
            JCheckBox[] checkboxes={jCheckBox1,jCheckBox2,jCheckBox4,jCheckBox5,jCheckBox6};
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
                        if(!jTextField3.getText().equals("")&&jCheckBox3.isSelected()==true){
                            while(!jTextField3.getText().equals("")&&jCheckBox3.isSelected()==true){
                                datos.actualizarDatos("socios set apellidom_part='"+jTextField3.getText()+"' where codigo_part='"+Integer.parseInt(txtSearch.getText())+"';");
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
            JCheckBox[] checkboxes={jCheckBox1,jCheckBox2,jCheckBox3,jCheckBox5,jCheckBox6};
            if(jCheckBox4.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //combo
                jComboBox1.setEnabled(true);
                //textfields
                txtSearch.setEnabled(false);
                //función
                if(jCheckBox4.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!jComboBox1.getModel().getSelectedItem().equals(jLabel4.getText())&&jCheckBox4.isSelected()==true){
                            while(!jComboBox1.getModel().getSelectedItem().equals(jLabel4.getText())&&jCheckBox4.isSelected()==true){
                                datos.actualizarDatos("socios set tipo_socio='"+jComboBox1.getSelectedItem().toString()+"' where codigo_part='"+Integer.parseInt(txtSearch.getText())+"';");
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
                //combo
                jComboBox1.setEnabled(false);
                jComboBox1.getModel().setSelectedItem("Item 1");
                //textfields
                txtSearch.setEnabled(true);
            }
        });
        
        jCheckBox5.addActionListener((a)->{
            JCheckBox[] checkboxes={jCheckBox1,jCheckBox2,jCheckBox3,jCheckBox4,jCheckBox6};
            if(jCheckBox5.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                jTextField4.setEnabled(true);
                //función
                if(jCheckBox5.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!jTextField4.getText().equals("")&&jCheckBox5.isSelected()==true){
                            while(!jTextField4.getText().equals("")&&jCheckBox5.isSelected()==true){
                                datos.actualizarDatos("socios set correo='"+jTextField4.getText()+"' where codigo_part='"+Integer.parseInt(txtSearch.getText())+"';");
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
                jTextField4.setEnabled(false);
                jTextField4.setText("");
            }
        });
        
        jCheckBox6.addActionListener((a)->{
            JCheckBox[] checkboxes={jCheckBox1,jCheckBox2,jCheckBox3,jCheckBox4,jCheckBox5};
            if(jCheckBox6.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                jTextField5.setEnabled(true);
                //función
                if(jCheckBox6.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!jTextField5.getText().equals("")&&jCheckBox6.isSelected()==true){
                            while(!jTextField5.getText().equals("")&&jCheckBox6.isSelected()==true){
                                datos.actualizarDatos("socios set rfc='"+jTextField5.getText()+"' where codigo_part='"+Integer.parseInt(txtSearch.getText())+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(jCheckBox6.isSelected()==false){
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
            PreparedStatement ps=new datos().getConnection().prepareStatement("select * from socios where codigo_part='"+Integer.parseInt(txtSearch.getText())+"';");
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                jLabel1.setText(rs.getString("nombre_part"));
                jLabel2.setText(rs.getString("apellidop_part"));
                jLabel3.setText(rs.getString("apellidom_part"));
                jLabel4.setText(rs.getString("tipo_socio"));
                jLabel5.setText(rs.getString("correo"));
                jLabel6.setText(rs.getString("rfc"));
            }else{
                JOptionPane.showMessageDialog(null,"Error: no existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                new logger(Level.WARNING).staticLogger("Error 14: no existen o no se ingresaron los datos a buscar y cambiar.\nOcurrió en '"+modDatosPanel2.class.getName()+"', en el método 'consulta()'");
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en '"+modDatosPanel2.class.getName()+"', en el método 'consulta()'");
            new logger(Level.SEVERE).exceptionLogger(modDatosPanel2.class.getName(),"consulta-14",e.fillInStackTrace());
        }
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel2.class.getName()+"', en el método 'jTextField1KeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyPressed
    
    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel2.class.getName()+"', en el método 'jTextField2KeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyPressed
    
    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel2.class.getName()+"', en el método 'jTextField3KeyPressed()'");
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