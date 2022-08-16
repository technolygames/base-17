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
import venPrimarias.start;

public class modDatosPanel4 extends javax.swing.JPanel{
    public modDatosPanel4(){
        initComponents();
        
        botones();
        settings();
    }
    
    protected void settings(){
        jLabel1.setToolTipText("Nombre del producto");
        jLabel2.setToolTipText("Marca");
        jLabel3.setToolTipText("Cantidad");
        jLabel4.setToolTipText("Precio unitario");
        jLabel5.setToolTipText("Stock");
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
                                datos.actualizarDatos("almacen set nombre_prod='"+jTextField1.getText()+"' where codigo_prod='"+Integer.parseInt(txtSearch.getText())+"';");
                                
                                JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
                                new logger(Level.INFO).staticLogger("Rel 2: se actualizaron correctamente los datos.\nOcurrió en la clase '"+modDatosPanel4.class.getName()+"', en el método 'botones(jCheckBox1)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
                                
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
                                datos.actualizarDatos("almacen set marca='"+jTextField2.getText()+"' where codigo_prod='"+Integer.parseInt(txtSearch.getText())+"';");
                                
                                JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
                                new logger(Level.INFO).staticLogger("Rel 2: se actualizaron correctamente los datos.\nOcurrió en la clase '"+modDatosPanel4.class.getName()+"', en el método 'botones(jCheckBox2)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
                                
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
                                datos.actualizarDatos("almacen set cantidad='"+Integer.parseInt(jTextField3.getText())+"' where codigo_prod='"+Integer.parseInt(txtSearch.getText())+"';");
                                
                                JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
                                new logger(Level.INFO).staticLogger("Rel 2: se actualizaron correctamente los datos.\nOcurrió en la clase '"+modDatosPanel4.class.getName()+"', en el método 'botones(jCheckBox3)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
                                
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
                                datos.actualizarDatos("almacen set precio_unitario='"+Integer.parseInt(jTextField4.getText())+"' where codigo_prod='"+Integer.parseInt(txtSearch.getText())+"';");
                                
                                JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
                                new logger(Level.INFO).staticLogger("Rel 2: se actualizaron correctamente los datos.\nOcurrió en la clase '"+modDatosPanel4.class.getName()+"', en el método 'botones(jCheckBox4)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
                                
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
                //combo
                jComboBox1.setEnabled(true);
                //textfields
                txtSearch.setEnabled(false);
                //función
                if(jCheckBox5.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!jComboBox1.getModel().getSelectedItem().equals(jLabel5.getText())&&jCheckBox5.isSelected()==true&&jComboBox1.isEnabled()==true){
                            while(!jComboBox1.getModel().getSelectedItem().equals(jLabel5.getText())&&jCheckBox5.isSelected()==true&&jComboBox1.isEnabled()==true){
                                datos.actualizarDatos("almacen set stock='"+jComboBox1.getSelectedItem().toString()+"' where codigo_prod='"+Integer.parseInt(txtSearch.getText())+"';");
                                
                                JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
                                new logger(Level.INFO).staticLogger("Rel 2: se actualizaron correctamente los datos.\nOcurrió en la clase '"+modDatosPanel4.class.getName()+"', en el método 'botones(jCheckBox5)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
                                
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
                //combo
                jComboBox1.setEnabled(false);
                jComboBox1.getModel().setSelectedItem("En Existencia");
                //textfields
                txtSearch.setEnabled(true);
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
            PreparedStatement ps=new datos().getConnection().prepareStatement("select * from almacen where codigo_prod='"+Integer.parseInt(txtSearch.getText())+"';");
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                jLabel1.setText(rs.getString("nombre_prod"));
                jLabel2.setText(rs.getString("marca"));
                jLabel3.setText(String.valueOf(rs.getInt("cantidad")));
                jLabel4.setText(String.valueOf(rs.getInt("precio_unitario")));
                jLabel5.setText(rs.getString("stock"));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en '"+modDatosPanel4.class.getName()+"', en el método 'consulta()'");
            new logger(Level.SEVERE).exceptionLogger(modDatosPanel4.class.getName(),"consulta-14",e.fillInStackTrace());
        }
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

        jTextField2.setEnabled(false);

        jTextField3.setEnabled(false);

        jTextField4.setEnabled(false);

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
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jCheckBox1)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jCheckBox2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jCheckBox3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jCheckBox4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jCheckBox5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


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
