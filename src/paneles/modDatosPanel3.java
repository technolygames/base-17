package paneles;

import clases.datos;
import clases.logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class modDatosPanel3 extends javax.swing.JPanel{
    public modDatosPanel3(){
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error CNFE: "+e.getMessage()+".\nOcurrió en '"+modDatosPanel3.class.getName()+"', en el método 'modDatosPanel3()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel3.class.getName(),Level.WARNING,"modDatosPanel3-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IE: "+x.getMessage()+".\nOcurrió en '"+modDatosPanel3.class.getName()+"', en el método 'modDatosPanel3()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel3.class.getName(),Level.WARNING,"modDatosPanel3-IE",x.fillInStackTrace());
        }catch(IllegalAccessException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IAE: "+n.getMessage()+".\nOcurrió en '"+modDatosPanel3.class.getName()+"', en el método 'modDatosPanel3()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel3.class.getName(),Level.WARNING,"modDatosPanel3-IAE",n.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 28: "+y.getMessage()+".\nOcurrió en '"+modDatosPanel3.class.getName()+"', en el método 'modDatosPanel3()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel3.class.getName(),Level.WARNING,"modDatosPanel3-28",y.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+modDatosPanel3.class.getName()+"', en el método 'modDatosPanel3()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel3.class.getName(),Level.WARNING,"modDatosPanel3-0",k.fillInStackTrace());
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+s.getMessage()+".\nOcurrió en '"+modDatosPanel3.class.getName()+"', en el método 'modDatosPanel3()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel3.class.getName(),Level.WARNING,"modDatosPanel3-1IO",s.fillInStackTrace());
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+d.getMessage()+".\nOcurrió en '"+modDatosPanel3.class.getName()+"', en el método 'modDatosPanel3()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel3.class.getName(),Level.WARNING,"modDatosPanel3-2IO",d.fillInStackTrace());
        }
        
        botones();
        settings();
    }
    
    protected PreparedStatement ps;
    protected ResultSet rs;
    
    protected void settings(){
        jLabel1.setToolTipText("Nombre(s)");
        jLabel2.setToolTipText("Apellido paterno");
        jLabel3.setToolTipText("Apellido materno");
        jLabel4.setToolTipText("Empresa");
        jLabel5.setToolTipText("Contacto");
    }
    
    protected final void botones(){
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
                //enabled
                jCheckBox2.setEnabled(false);
                jCheckBox3.setEnabled(false);
                jCheckBox4.setEnabled(false);
                jCheckBox5.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                jTextField2.setEnabled(false);
                jTextField3.setEnabled(false);
                jTextField4.setEnabled(false);
                jTextField5.setEnabled(false);
                //función
                if(jCheckBox1.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!jTextField1.getText().equals("")){
                            new datos().actualizarDatosProveedor("set nombre_prov='"+jTextField1.getText()+"' where codigo_prov='"+txtSearch.getText()+"';");
                            consulta();
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba el(los) nombres(s) a cambiar","Error 11",JOptionPane.WARNING_MESSAGE);
                            new logger().logStaticSaver("Error 11: no se escribió el(los) nombre(s) a cambiar.\nOcurrió en '"+modDatosPanel3.class.getName()+"', en el método 'botones(jCheckBox1)'",Level.WARNING);
                        }
                    });
                }
            }else if(jCheckBox1.isSelected()==false){
                //enabled
                jCheckBox2.setEnabled(true);
                jCheckBox3.setEnabled(true);
                jCheckBox4.setEnabled(true);
                jCheckBox5.setEnabled(true);
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
                //enabled
                jCheckBox1.setEnabled(false);
                jCheckBox3.setEnabled(false);
                jCheckBox4.setEnabled(false);
                jCheckBox5.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                jTextField1.setEnabled(false);
                jTextField3.setEnabled(false);
                jTextField4.setEnabled(false);
                jTextField5.setEnabled(false);
                //función
                if(jCheckBox2.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!jTextField2.getText().equals("")){
                            new datos().actualizarDatosProveedor("set apellidop_prov='"+jTextField2.getText()+"' where codigo_prov='"+txtSearch.getText()+"';");
                            consulta();
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba el apellido paterno a cambiar","Error 11",JOptionPane.WARNING_MESSAGE);
                            new logger().logStaticSaver("Error 11: no se escribió el apellido paterno a cambiar.\nOcurrió en '"+modDatosPanel3.class.getName()+"', en el método 'botones(jCheckBox2)'",Level.WARNING);
                        }
                    });
                }
            }else if(jCheckBox2.isSelected()==false){
                //enabled
                jCheckBox1.setEnabled(true);
                jCheckBox3.setEnabled(true);
                jCheckBox4.setEnabled(true);
                jCheckBox5.setEnabled(true);
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
                //enabled
                jCheckBox1.setEnabled(false);
                jCheckBox2.setEnabled(false);
                jCheckBox4.setEnabled(false);
                jCheckBox5.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                jTextField1.setEnabled(false);
                jTextField2.setEnabled(false);
                jTextField4.setEnabled(false);
                jTextField5.setEnabled(false);
                //función
                if(jCheckBox3.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!jTextField3.getText().equals("")){
                            new datos().actualizarDatosProveedor("set apellidom_prov='"+jTextField3.getText()+"' where codigo_prov='"+txtSearch.getText()+"';");
                            consulta();
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba el apellido materno a cambiar","Error 11",JOptionPane.WARNING_MESSAGE);
                            new logger().logStaticSaver("Error 11: no se escribió el apellido materno a cambiar.\nOcurrió en '"+modDatosPanel3.class.getName()+"', en el método 'botones(jCheckBox3)'",Level.WARNING);
                        }
                    });
                }
            }else if(jCheckBox3.isSelected()==false){
                //enabled
                jCheckBox1.setEnabled(true);
                jCheckBox2.setEnabled(true);
                jCheckBox4.setEnabled(true);
                jCheckBox5.setEnabled(true);
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
                //enabled
                jCheckBox1.setEnabled(false);
                jCheckBox2.setEnabled(false);
                jCheckBox3.setEnabled(false);
                jCheckBox5.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                jTextField1.setEnabled(false);
                jTextField2.setEnabled(false);
                jTextField3.setEnabled(false);
                jTextField5.setEnabled(false);
                //función
                if(jCheckBox4.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!jTextField4.getText().equals("")){
                            new datos().actualizarDatosProveedor("set empresa='"+jTextField4.getText()+"' where codigo_prov='"+txtSearch.getText()+"';");
                            consulta();
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba la empresa a cambiar","Error 11",JOptionPane.WARNING_MESSAGE);
                            new logger().logStaticSaver("Error 11: no se escribió la empresa a cambiar.\nOcurrió en '"+modDatosPanel3.class.getName()+"', en el método 'botones(jCheckBox4)'",Level.WARNING);
                        }
                    });
                }
            }else if(jCheckBox4.isSelected()==false){
                //enabled
                jCheckBox1.setEnabled(true);
                jCheckBox2.setEnabled(true);
                jCheckBox3.setEnabled(true);
                jCheckBox5.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                jTextField1.setEnabled(true);
                jTextField2.setEnabled(true);
                jTextField3.setEnabled(true);
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
                //enabled
                jCheckBox1.setEnabled(false);
                jCheckBox2.setEnabled(false);
                jCheckBox3.setEnabled(false);
                jCheckBox4.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                jTextField1.setEnabled(false);
                jTextField2.setEnabled(false);
                jTextField3.setEnabled(false);
                jTextField4.setEnabled(false);
                //función
                if(jCheckBox5.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!jTextField5.getText().equals("")){
                            new datos().actualizarDatosProveedor("set contacto='"+jTextField5.getText()+"' where codigo_prov='"+txtSearch.getText()+"';");
                            consulta();
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba el contacto a cambiar","Error 11",JOptionPane.WARNING_MESSAGE);
                            new logger().logStaticSaver("Error 11: no se escribió el número telefónico a cambiar.\nOcurrió en '"+modDatosPanel3.class.getName()+"', en el método 'botones(jCheckBox5)'",Level.WARNING);
                        }
                    });
                }
            }else if(jCheckBox5.isSelected()==false){
                //enabled
                jCheckBox1.setEnabled(true);
                jCheckBox2.setEnabled(true);
                jCheckBox3.setEnabled(true);
                jCheckBox4.setEnabled(true);
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
            ps=new datos().getConnection().prepareStatement("select nombre_prov,apellidop_prov,apellidom_prov,empresa,contacto from proveedor where codigo_prov='"+txtSearch.getText()+"';");
            rs=ps.executeQuery();
            if(rs.next()){
                jLabel1.setText(rs.getString("nombre_prov"));
                jLabel2.setText(rs.getString("apellidop_prov"));
                jLabel3.setText(rs.getString("apellidom_prov"));
                jLabel4.setText(rs.getString("empresa"));
                jLabel5.setText(rs.getString("contacto"));
            }else{
                JOptionPane.showMessageDialog(null,"Error: no existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 14: no existen o no se ingresaron los datos a buscar y cambiar.\nOcurrió en '"+modDatosPanel3.class.getName()+"', en el método 'consulta()'",Level.WARNING);
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error Prueba (consulta)",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error Prueba: "+e.getMessage()+".\nOcurrió en '"+modDatosPanel3.class.getName()+"', en el método 'consulta()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel3.class.getName(),Level.WARNING,"consulta-Prueba",e.fillInStackTrace());
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
        jButton1 = new javax.swing.JButton();

        closeButton.setText("Cerrar");

        searchButton.setText("Buscar");

        jLabel1.setText("Nombre(s)");

        jLabel2.setText("Apellido paterno");

        jLabel3.setText("Apellido materno");

        jLabel4.setText("Empresa");

        jLabel5.setText("Contacto");

        jButton1.setText("Actualizar datos");

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
    // End of variables declaration//GEN-END:variables
}