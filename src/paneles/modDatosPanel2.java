package paneles;
//clases
import clases.datos;
import clases.laf;
import clases.logger;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

public class modDatosPanel2 extends javax.swing.JPanel{
    public modDatosPanel2(){
        initComponents();
        new laf().LookAndFeel(modDatosPanel2.this,modDatosPanel2.class.getName(),"modDatosPanel2");
        
        botones();
        settings();
    }
    
    protected PreparedStatement ps;
    protected ResultSet rs;
    
    protected void settings(){
        jLabel1.setToolTipText("Nombre(s)");
        jLabel2.setToolTipText("Apellido paterno");
        jLabel3.setToolTipText("Apellido materno");
        jLabel4.setToolTipText("Tipo de socio");
        jLabel5.setToolTipText("Correo");
        jLabel6.setToolTipText("RFC");
    }
    
    protected void botones(){
        closeButton.addActionListener((a)->{
            setVisible(false);
        });
        
        jCheckBox1.addActionListener((a)->{
            if(jCheckBox1.isSelected()==true){
                //selected
                jCheckBox2.setSelected(false);
                jCheckBox3.setSelected(false);
                jCheckBox4.setSelected(false);
                jCheckBox5.setSelected(false);
                jCheckBox6.setSelected(false);
                //enabled
                jCheckBox2.setEnabled(false);
                jCheckBox3.setEnabled(false);
                jCheckBox4.setEnabled(false);
                jCheckBox5.setEnabled(false);
                jCheckBox6.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                jTextField2.setEnabled(false);
                jTextField3.setEnabled(false);
                jTextField4.setEnabled(false);
                jTextField5.setEnabled(false);
                //función
                if(jCheckBox1.isSelected()==true){
                    jButton1.addActionListener((p)->{
                        if(!jTextField1.getText().equals("")&&jCheckBox1.isSelected()==true){
                            while(!jTextField1.getText().equals("")&&jCheckBox1.isSelected()==true){
                                new datos().actualizarDatosSocio("set nombre_part='"+jTextField1.getText()+"' where codigo_part='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba el(los) nombres(s) a cambiar","Error 18",JOptionPane.WARNING_MESSAGE);
                            new logger().staticLogger("Error 18: no se escribió el(los) nombre(s) a cambiar.\nOcurrió en '"+modDatosPanel2.class.getName()+"', en el método 'botones(jCheckBox1)'",Level.WARNING);
                        }
                    });
                }
            }else if(jCheckBox1.isSelected()==false){
                //enabled
                jCheckBox2.setEnabled(true);
                jCheckBox3.setEnabled(true);
                jCheckBox4.setEnabled(true);
                jCheckBox5.setEnabled(true);
                jCheckBox6.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                jTextField2.setEnabled(true);
                jTextField3.setEnabled(true);
                jTextField4.setEnabled(true);
                jTextField5.setEnabled(true);
            }
        });
        
        jCheckBox2.addActionListener((a)->{
            if(jCheckBox2.isSelected()==true){
                //selected
                jCheckBox1.setSelected(false);
                jCheckBox3.setSelected(false);
                jCheckBox4.setSelected(false);
                jCheckBox5.setSelected(false);
                jCheckBox6.setSelected(false);
                //enabled
                jCheckBox1.setEnabled(false);
                jCheckBox3.setEnabled(false);
                jCheckBox4.setEnabled(false);
                jCheckBox5.setEnabled(false);
                jCheckBox6.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                jTextField1.setEnabled(false);
                jTextField3.setEnabled(false);
                jTextField4.setEnabled(false);
                jTextField5.setEnabled(false);
                //función
                if(jCheckBox2.isSelected()==true){
                    jButton1.addActionListener((p)->{
                        if(!jTextField2.getText().equals("")&&jCheckBox2.isSelected()==true){
                            while(!jTextField2.getText().equals("")&&jCheckBox2.isSelected()==true){
                                new datos().actualizarDatosSocio("set apellidop_part='"+jTextField2.getText()+"' where codigo_part='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba el apellido paterno a cambiar","Error 18",JOptionPane.WARNING_MESSAGE);
                            new logger().staticLogger("Error 18: no se escribió el apellido paterno a cambiar.\nOcurrió en '"+modDatosPanel2.class.getName()+"', en el método 'botones(jCheckBox2)'",Level.WARNING);
                        }
                    });
                }
            }else if(jCheckBox2.isSelected()==false){
                //enabled
                jCheckBox1.setEnabled(true);
                jCheckBox3.setEnabled(true);
                jCheckBox4.setEnabled(true);
                jCheckBox5.setEnabled(true);
                jCheckBox6.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                jTextField1.setEnabled(true);
                jTextField3.setEnabled(true);
                jTextField4.setEnabled(true);
                jTextField5.setEnabled(true);
            }
        });
        
        jCheckBox3.addActionListener((a)->{
            if(jCheckBox3.isSelected()==true){
                //selected
                jCheckBox1.setSelected(false);
                jCheckBox2.setSelected(false);
                jCheckBox4.setSelected(false);
                jCheckBox5.setSelected(false);
                jCheckBox6.setSelected(false);
                //enabled
                jCheckBox1.setEnabled(false);
                jCheckBox2.setEnabled(false);
                jCheckBox4.setEnabled(false);
                jCheckBox5.setEnabled(false);
                jCheckBox6.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                jTextField1.setEnabled(false);
                jTextField2.setEnabled(false);
                jTextField4.setEnabled(false);
                jTextField5.setEnabled(false);
                //función
                if(jCheckBox3.isSelected()==true){
                    jButton1.addActionListener((p)->{
                        if(!jTextField3.getText().equals("")&&jCheckBox3.isSelected()==true){
                            while(!jTextField3.getText().equals("")&&jCheckBox3.isSelected()==true){
                                new datos().actualizarDatosSocio("set apellidom_part='"+jTextField3.getText()+"' where codigo_part='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba el apellido materno a cambiar","Error 18",JOptionPane.WARNING_MESSAGE);
                            new logger().staticLogger("Error 18: no se escribió el apellido materno a cambiar.\nOcurrió en '"+modDatosPanel2.class.getName()+"', en el método 'botones(jCheckBox3)'",Level.WARNING);
                        }
                    });
                }
            }else if(jCheckBox3.isSelected()==false){
                //enabled
                jCheckBox1.setEnabled(true);
                jCheckBox2.setEnabled(true);
                jCheckBox4.setEnabled(true);
                jCheckBox5.setEnabled(true);
                jCheckBox6.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                jTextField1.setEnabled(true);
                jTextField2.setEnabled(true);
                jTextField4.setEnabled(true);
                jTextField5.setEnabled(true);
            }
        });
        
        jCheckBox4.addActionListener((a)->{
            if(jCheckBox4.isSelected()==true){
                //selected
                jCheckBox1.setSelected(false);
                jCheckBox2.setSelected(false);
                jCheckBox3.setSelected(false);
                jCheckBox5.setSelected(false);
                jCheckBox6.setSelected(false);
                //enabled
                jCheckBox1.setEnabled(false);
                jCheckBox2.setEnabled(false);
                jCheckBox3.setEnabled(false);
                jCheckBox5.setEnabled(false);
                jCheckBox6.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                jTextField1.setEnabled(false);
                jTextField2.setEnabled(false);
                jTextField3.setEnabled(false);
                jTextField4.setEnabled(false);
                jTextField5.setEnabled(false);
                //función
                if(jCheckBox4.isSelected()==true){
                    jButton1.addActionListener((p)->{
                        if(!jComboBox1.getModel().getSelectedItem().equals(jLabel4.getText())&&jCheckBox4.isSelected()==true){
                            while(!jComboBox1.getModel().getSelectedItem().equals(jLabel4.getText())&&jCheckBox4.isSelected()==true){
                                new datos().actualizarDatosSocio("set tipo_socio='"+jComboBox1.getSelectedItem().toString()+"' where codigo_part='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: seleccione el nuevo tipo de socio","Error 18",JOptionPane.WARNING_MESSAGE);
                            new logger().staticLogger("Error 18: no se seleccionó el nuevo tipo de socio.\nOcurrió en '"+modDatosPanel2.class.getName()+"', en el método 'botones(jCheckBox4)'",Level.WARNING);
                        }
                    });
                }
            }else if(jCheckBox4.isSelected()==false){
                //enabled
                jCheckBox1.setEnabled(true);
                jCheckBox2.setEnabled(true);
                jCheckBox3.setEnabled(true);
                jCheckBox5.setEnabled(true);
                jCheckBox6.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                jTextField1.setEnabled(true);
                jTextField2.setEnabled(true);
                jTextField3.setEnabled(true);
                jTextField4.setEnabled(true);
                jTextField5.setEnabled(true);
            }
        });
        
        jCheckBox5.addActionListener((a)->{
            if(jCheckBox5.isSelected()==true){
                //selected
                jCheckBox1.setSelected(false);
                jCheckBox2.setSelected(false);
                jCheckBox3.setSelected(false);
                jCheckBox4.setSelected(false);
                jCheckBox6.setSelected(false);
                //enabled
                jCheckBox1.setEnabled(false);
                jCheckBox2.setEnabled(false);
                jCheckBox3.setEnabled(false);
                jCheckBox4.setEnabled(false);
                jCheckBox6.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                jTextField1.setEnabled(false);
                jTextField2.setEnabled(false);
                jTextField3.setEnabled(false);
                jTextField5.setEnabled(false);
                //función
                if(jCheckBox5.isSelected()==true){
                    jButton1.addActionListener((p)->{
                        if(!jTextField4.getText().equals("")&&jCheckBox5.isSelected()==true){
                            while(!jTextField4.getText().equals("")&&jCheckBox5.isSelected()==true){
                                new datos().actualizarDatosSocio("set correo='"+jTextField4.getText()+"' where codigo_part='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba el nuevo correo","Error 18",JOptionPane.WARNING_MESSAGE);
                            new logger().staticLogger("Error 18: no se escribió el nuevo correo del socio.\nOcurrió en '"+modDatosPanel2.class.getName()+"', en el método 'botones(jCheckBox4)'",Level.WARNING);
                        }
                    });
                }
            }else if(jCheckBox5.isSelected()==false){
                //enabled
                jCheckBox1.setEnabled(true);
                jCheckBox2.setEnabled(true);
                jCheckBox3.setEnabled(true);
                jCheckBox4.setEnabled(true);
                jCheckBox6.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                jTextField1.setEnabled(true);
                jTextField2.setEnabled(true);
                jTextField3.setEnabled(true);
                jTextField5.setEnabled(true);
            }
        });
        
        jCheckBox6.addActionListener((a)->{
            if(jCheckBox6.isSelected()==true){
                //selected
                jCheckBox1.setSelected(false);
                jCheckBox2.setSelected(false);
                jCheckBox3.setSelected(false);
                jCheckBox4.setSelected(false);
                jCheckBox5.setSelected(false);
                //enabled
                jCheckBox1.setEnabled(false);
                jCheckBox2.setEnabled(false);
                jCheckBox3.setEnabled(false);
                jCheckBox4.setEnabled(false);
                jCheckBox5.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                jTextField1.setEnabled(false);
                jTextField2.setEnabled(false);
                jTextField3.setEnabled(false);
                jTextField4.setEnabled(false);
                //función
                if(jCheckBox6.isSelected()==true){
                    jButton1.addActionListener((p)->{
                        if(!jTextField5.getText().equals("")&&jCheckBox6.isSelected()==true){
                            while(!jTextField5.getText().equals("")&&jCheckBox6.isSelected()==true){
                                new datos().actualizarDatosSocio("set rfc='"+jTextField5.getText()+"' where codigo_part='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba el nuevo rfc","Error 18",JOptionPane.WARNING_MESSAGE);
                            new logger().staticLogger("Error 18: no se escribió el nuevo rfc del socio.\nOcurrió en '"+modDatosPanel2.class.getName()+"', en el método 'botones(jCheckBox4)'",Level.WARNING);
                        }
                    });
                }
            }else if(jCheckBox6.isSelected()==false){
                //enabled
                jCheckBox1.setEnabled(true);
                jCheckBox2.setEnabled(true);
                jCheckBox3.setEnabled(true);
                jCheckBox4.setEnabled(true);
                jCheckBox5.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                jTextField1.setEnabled(true);
                jTextField2.setEnabled(true);
                jTextField3.setEnabled(true);
                jTextField4.setEnabled(true);
            }
        });
        
        searchButton.addActionListener((a)->{
            consulta();
        });
    }
    
    protected void consulta(){
        try{
            ps=new datos().getConnection().prepareStatement("select nombre_part,apellidop_part,apellidom_part,tipo_socio,correo,rfc from socios where codigo_part='"+txtSearch.getText()+"';");
            rs=ps.executeQuery();
            if(rs.next()){
                jLabel1.setText(rs.getString("nombre_part"));
                jLabel2.setText(rs.getString("apellidop_part"));
                jLabel3.setText(rs.getString("apellidom_part"));
                jLabel4.setText(rs.getString("tipo_socio"));
                jLabel5.setText(rs.getString("correo"));
                jLabel6.setText(rs.getString("rfc"));
            }else{
                JOptionPane.showMessageDialog(null,"Error: no existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 14: no existen o no se ingresaron los datos a buscar y cambiar.\nOcurrió en '"+modDatosPanel2.class.getName()+"', en el método 'consulta()'",Level.WARNING);
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en '"+modDatosPanel2.class.getName()+"', en el método 'consulta()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel2.class.getName(),Level.WARNING,"consulta-14",e.fillInStackTrace());
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
        jButton1 = new javax.swing.JButton();
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

        jLabel2.setText("Apellido paterno");

        jLabel3.setText("Apellido materno");

        jLabel4.setText("Tipo de socio");

        jButton1.setText("Actualizar datos");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Correo");

        jLabel6.setText("RFC");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(jTextField4)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(jButton1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton jButton1;
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
    // End of variables declaration//GEN-END:variables
}