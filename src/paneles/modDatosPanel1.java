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
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class modDatosPanel1 extends javax.swing.JPanel{
    public modDatosPanel1(){
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error CNFE: "+e.getMessage()+".\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'modDatosPanel1()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel1.class.getName(),Level.WARNING,"modDatosPanel1-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IE: "+x.getMessage()+".\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'modDatosPanel1()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel1.class.getName(),Level.WARNING,"modDatosPanel1-IE",x.fillInStackTrace());
        }catch(IllegalAccessException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IAE: "+n.getMessage()+".\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'modDatosPanel1()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel1.class.getName(),Level.WARNING,"modDatosPanel1-IAE",n.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 28: "+y.getMessage()+".\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'modDatosPanel1()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel1.class.getName(),Level.WARNING,"modDatosPanel1-28",y.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+modDatosPanel1.class.getName()+"', en el método 'modDatosPanel1()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel1.class.getName(),Level.WARNING,"modDatosPanel1-0",k.fillInStackTrace());
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+s.getMessage()+".\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'modDatosPanel1()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel1.class.getName(),Level.WARNING,"modDatosPanel1-1IO",s.fillInStackTrace());
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+d.getMessage()+".\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'modDatosPanel1()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel1.class.getName(),Level.WARNING,"modDatosPanel1-1IO",d.fillInStackTrace());
        }
        
        settings();
        botones();
    }
    
    protected PreparedStatement ps;
    protected ResultSet rs;
    
    protected void settings(){
        etiContra.setToolTipText("Contraseña");
        etiNombre.setToolTipText("Nombre(s)");
        etiAP.setToolTipText("Apellido paterno");
        etiAM.setToolTipText("Apellido materno");
        etiPuesto.setToolTipText("Puesto");
        etiExp.setToolTipText("Experiencia");
        etiGE.setToolTipText("Grado de estudios");
        etiContacto.setToolTipText("Contacto");
        etiEdad.setToolTipText("Edad");
        etiEstado.setToolTipText("Estado");
    }
    
    protected final void botones(){
        closeButton.addActionListener((a)->{
            setVisible(false);
        });
        
        cbContra.addActionListener((a)->{
            if(cbContra.isSelected()==true){
                //selected
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                jComboBox2.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtNombre.setEditable(false);
                txtAP.setEditable(false);
                txtAM.setEditable(false);
                txtDom.setEditable(false);
                txtExp.setEditable(false);
                txtGE.setEditable(false);
                txtContacto.setEditable(false);
                txtEdad.setEditable(false);
                //función
                if(cbContra.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtContra.getPassword().equals("")&&cbContra.isSelected()==true){
                            while(!txtContra.getPassword().equals("")&&cbContra.isSelected()==true){
                                new datos().actualizarDatosEmpleado("set password='"+String.valueOf(txtContra.getPassword())+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba la contraseña nueva","Error 11",JOptionPane.WARNING_MESSAGE);
                            new logger().logStaticSaver("Error 11: no se escribió la contraseña a cambiar.\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'botones(cbContra)'",Level.WARNING);
                        }
                    });
                }
            }else if(cbContra.isSelected()==false){
                //enabled
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                jComboBox2.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtNombre.setEditable(true);
                txtAP.setEditable(true);
                txtAM.setEditable(true);
                txtDom.setEditable(true);
                txtExp.setEditable(true);
                txtGE.setEditable(true);
                txtContacto.setEditable(true);
                txtEdad.setEditable(true);
            }
        });
        
        cbNombre.addActionListener((a)->{
            if(cbNombre.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                jComboBox2.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtAP.setEditable(false);
                txtAM.setEditable(false);
                txtDom.setEditable(false);
                txtExp.setEditable(false);
                txtGE.setEditable(false);
                txtContacto.setEditable(false);
                txtEdad.setEditable(false);
                //función
                if(cbNombre.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtNombre.getText().equals("")&&cbNombre.isSelected()==true){
                            while(!txtNombre.getText().equals("")&&cbNombre.isSelected()==true){
                                new datos().actualizarDatosEmpleado("set nombre_emp='"+txtNombre.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba el(los) nombre(s) nuevo(s)","Error 11",JOptionPane.WARNING_MESSAGE);
                            new logger().logStaticSaver("Error 11: no se escribió el(loa) nombre(s) a cambiar.\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'botones(cbNombre)'",Level.WARNING);
                        }
                    });
                }
            }else if(cbNombre.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                jComboBox2.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtAP.setEditable(true);
                txtAM.setEditable(true);
                txtDom.setEditable(true);
                txtExp.setEditable(true);
                txtGE.setEditable(true);
                txtContacto.setEditable(true);
                txtEdad.setEditable(true);
            }
        });
        
        cbAP.addActionListener((a)->{
            if(cbAP.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAM.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAM.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                jComboBox2.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtNombre.setEditable(false);
                txtAM.setEditable(false);
                txtDom.setEditable(false);
                txtExp.setEditable(false);
                txtGE.setEditable(false);
                txtContacto.setEditable(false);
                txtEdad.setEditable(false);
                //función
                if(cbAP.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtAP.getText().equals("")&&cbAP.isSelected()==true){
                            while(!txtAP.getText().equals("")&&cbAP.isSelected()==true){
                                new datos().actualizarDatosEmpleado("set apellidop_amp='"+txtAP.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba el nuevo apellido","Error 11",JOptionPane.WARNING_MESSAGE);
                            new logger().logStaticSaver("Error 11: no se escribió el apellido paterno a cambiar.\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'botones(cbAP)'",Level.WARNING);
                        }
                    });
                }
            }else if(cbAP.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAM.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                jComboBox2.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtNombre.setEditable(true);
                txtAM.setEditable(true);
                txtDom.setEditable(true);
                txtExp.setEditable(true);
                txtGE.setEditable(true);
                txtContacto.setEditable(true);
                txtEdad.setEditable(true);
            }
        });
        
        cbAM.addActionListener((a)->{
            if(cbAM.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                jComboBox2.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtNombre.setEditable(false);
                txtAP.setEditable(false);
                txtDom.setEditable(false);
                txtExp.setEditable(false);
                txtGE.setEditable(false);
                txtContacto.setEditable(false);
                txtEdad.setEditable(false);
                //función
                if(cbAM.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtAM.getText().equals("")&&cbAM.isSelected()==true){
                            while(!txtAM.getText().equals("")&&cbAM.isSelected()==true){
                                new datos().actualizarDatosEmpleado("set apellidom_emp='"+txtAM.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba el apellido materno nuevo","Error 11",JOptionPane.WARNING_MESSAGE);
                            new logger().logStaticSaver("Error 11: no se escribió el apellido materno a cambiar.\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'botones(cbAM)'",Level.WARNING);
                        }
                    });
                }
            }else if(cbAM.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                jComboBox2.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtNombre.setEditable(true);
                txtAP.setEditable(true);
                txtDom.setEditable(true);
                txtExp.setEditable(true);
                txtGE.setEditable(true);
                txtContacto.setEditable(true);
                txtEdad.setEditable(true);
            }
        });
        
        cbDomicilio.addActionListener((a)->{
            if(cbDomicilio.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                jComboBox2.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtNombre.setEditable(false);
                txtAP.setEditable(false);
                txtAM.setEditable(false);
                txtExp.setEditable(false);
                txtGE.setEditable(false);
                txtContacto.setEditable(false);
                txtEdad.setEditable(false);
                //función
                if(cbDomicilio.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtDom.getText().equals("")&&cbDomicilio.isSelected()==true){
                            while(!txtDom.getText().equals("")&&cbDomicilio.isSelected()==true){
                                new datos().actualizarDatosEmpleado("set domicilio='"+txtDom.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba el domicilio nuevo","Error 11",JOptionPane.WARNING_MESSAGE);
                            new logger().logStaticSaver("Error 11: no se escribió el domicilio a cambiar.\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'botones(cbDom)'",Level.WARNING);
                        }
                    });
                }
            }else if(cbDomicilio.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                jComboBox2.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtNombre.setEditable(true);
                txtAP.setEditable(true);
                txtAM.setEditable(true);
                txtExp.setEditable(true);
                txtGE.setEditable(true);
                txtContacto.setEditable(true);
                txtEdad.setEditable(true);
            }
        });
        
        cbPuesto.addActionListener((a)->{
            if(cbPuesto.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbDomicilio.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //combobox
                jComboBox2.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtNombre.setEditable(false);
                txtAP.setEditable(false);
                txtAM.setEditable(false);
                txtDom.setEditable(false);
                txtExp.setEditable(false);
                txtGE.setEditable(false);
                txtContacto.setEditable(false);
                txtEdad.setEditable(false);
                //función
                if(cbPuesto.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!jComboBox1.getModel().getSelectedItem().equals(etiPuesto.getText())&&cbPuesto.isSelected()==true){
                            while(!jComboBox1.getModel().getSelectedItem().equals(etiPuesto.getText())&&cbPuesto.isSelected()==true){
                                new datos().actualizarDatosEmpleado("set puesto='"+jComboBox1.getSelectedItem().toString()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: seleccione el nuevo puesto","Error 11",JOptionPane.WARNING_MESSAGE);
                            new logger().logStaticSaver("Error 11: no se seleccionó el nuevo puesto.\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'botones(cbPuesto)'",Level.WARNING);
                        }
                    });
                }
            }else if(cbPuesto.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //combobox
                jComboBox2.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtNombre.setEditable(true);
                txtAP.setEditable(true);
                txtAM.setEditable(true);
                txtDom.setEditable(true);
                txtExp.setEditable(true);
                txtGE.setEditable(true);
                txtContacto.setEditable(true);
                txtEdad.setEditable(true);
            }
        });
        
        cbExp.addActionListener((a)->{
            if(cbExp.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                jComboBox2.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtNombre.setEditable(false);
                txtAP.setEditable(false);
                txtAM.setEditable(false);
                txtDom.setEditable(false);
                txtGE.setEditable(false);
                txtContacto.setEditable(false);
                txtEdad.setEditable(false);
                //función
                if(cbExp.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtExp.getText().equals("")&&cbExp.isSelected()==true){
                            while(!txtExp.getText().equals("")&&cbExp.isSelected()==true){
                                new datos().actualizarDatosEmpleado("set experiencia='"+txtExp.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba la experiencia a cambiar","Error 11",JOptionPane.WARNING_MESSAGE);
                            new logger().logStaticSaver("Error 11: no se escribió los años de experiencia a cambiar.\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'botones(cbExp)'",Level.WARNING);
                        }
                    });
                }
            }else if(cbExp.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                jComboBox2.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtNombre.setEditable(true);
                txtAP.setEditable(true);
                txtAM.setEditable(true);
                txtDom.setEditable(true);
                txtGE.setEditable(true);
                txtContacto.setEditable(true);
                txtEdad.setEditable(true);
            }
        });
        
        cbGE.addActionListener((a)->{
            if(cbGE.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbContacto.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                jComboBox2.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtNombre.setEditable(false);
                txtAP.setEditable(false);
                txtAM.setEditable(false);
                txtDom.setEditable(false);
                txtExp.setEditable(false);
                txtContacto.setEditable(false);
                txtEdad.setEditable(false);
                //función
                if(cbGE.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtGE.getText().equals("")&&cbGE.isSelected()==true){
                            while(!txtGE.getText().equals("")&&cbGE.isSelected()==true){
                                new datos().actualizarDatosEmpleado("set grado_estudios='"+txtGE.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba el grado de estudios a cambiar","Error 11",JOptionPane.WARNING_MESSAGE);
                            new logger().logStaticSaver("Error 11: no se escribió el grado de estudios a cambiar.\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'botones(cbGE)'",Level.WARNING);
                        }
                    });
                }
            }else if(cbGE.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                jComboBox2.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtNombre.setEditable(true);
                txtAP.setEditable(true);
                txtAM.setEditable(true);
                txtDom.setEditable(true);
                txtExp.setEditable(true);
                txtContacto.setEditable(true);
                txtEdad.setEditable(true);
            }
        });
        
        cbContacto.addActionListener((a)->{
            if(cbContacto.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                jComboBox2.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtNombre.setEditable(false);
                txtAP.setEditable(false);
                txtAM.setEditable(false);
                txtDom.setEditable(false);
                txtExp.setEditable(false);
                txtGE.setEditable(false);
                txtEdad.setEditable(false);
                //función
                if(cbContacto.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtContacto.getText().equals("")&&cbContacto.isSelected()==true){
                            while(!txtContacto.getText().equals("")&&cbContacto.isSelected()==true){
                                new datos().actualizarDatosEmpleado("set contacto='"+txtContacto.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba el contacto nuevo","Error 11",JOptionPane.WARNING_MESSAGE);
                            new logger().logStaticSaver("Error 11: no se escribió el número telefónico a cambiar.\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'botones(cbContacto)'",Level.WARNING);
                        }
                    });
                }
            }else if(cbContacto.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                jComboBox2.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtNombre.setEditable(true);
                txtAP.setEditable(true);
                txtAM.setEditable(true);
                txtDom.setEditable(true);
                txtExp.setEditable(true);
                txtGE.setEditable(true);
                txtEdad.setEditable(true);
            }
        });
        
        cbEdad.addActionListener((a)->{
            if(cbEdad.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEstado.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                jComboBox2.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtNombre.setEditable(false);
                txtAP.setEditable(false);
                txtAM.setEditable(false);
                txtDom.setEditable(false);
                txtExp.setEditable(false);
                txtGE.setEditable(false);
                txtContacto.setEditable(false);
                //función
                if(cbEdad.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtEdad.getText().equals("")&&cbEdad.isSelected()==true){
                            while(!txtEdad.getText().equals("")&&cbEdad.isSelected()==true){
                                new datos().actualizarDatosEmpleado("set edad='"+txtEdad.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: escriba la edad a cambiar","Error 11",JOptionPane.WARNING_MESSAGE);
                            new logger().logStaticSaver("Error 11: no se escribió la edad a cambiar.\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'botones(cbEdad)'",Level.WARNING);
                        }
                    });
                }
            }else if(cbEdad.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEstado.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                jComboBox2.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtNombre.setEditable(true);
                txtAP.setEditable(true);
                txtAM.setEditable(true);
                txtDom.setEditable(true);
                txtExp.setEditable(true);
                txtGE.setEditable(true);
                txtContacto.setEditable(true);
            }
        });
        
        cbEstado.addActionListener((a)->{
            if(cbEstado.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbEdad.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEdad.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtNombre.setEditable(false);
                txtAP.setEditable(false);
                txtAM.setEditable(false);
                txtDom.setEditable(false);
                txtExp.setEditable(false);
                txtGE.setEditable(false);
                txtContacto.setEditable(false);
                txtEdad.setEditable(false);
                //función
                if(cbEstado.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!jComboBox2.getModel().getSelectedItem().equals(etiEstado.getText())&&cbEstado.isSelected()==true){
                            while(!jComboBox2.getModel().getSelectedItem().equals(etiEstado.getText())&&cbEstado.isSelected()==true){
                                new datos().actualizarDatosEmpleado("set estado='"+jComboBox2.getSelectedItem().toString()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error: seleccione el nuevo estado del empleado","Error 11",JOptionPane.WARNING_MESSAGE);
                            new logger().logStaticSaver("Error 11: no se seleccionó el nuevo estado del empleado.\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'botones(cbPuesto)'",Level.WARNING);
                        }
                    });
                }
            }else if(cbEstado.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEdad.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtNombre.setEditable(true);
                txtAP.setEditable(true);
                txtAM.setEditable(true);
                txtDom.setEditable(true);
                txtExp.setEditable(true);
                txtGE.setEditable(true);
                txtContacto.setEditable(true);
                txtEdad.setEditable(true);
            }
        });
        
        searchButton.addActionListener((a)->{
            consulta();
        });
    }
    
    protected void consulta(){
        try{
            ps=new datos().getConnection().prepareStatement("select password,nombre_emp,apellidop_emp,apellidom_emp,domicilio,puesto,experiencia,grado_estudios,contacto,edad,estado from empleados where codigo_emp='"+txtSearch.getText()+"';");
            rs=ps.executeQuery();
            if(rs.next()){
                etiContra.setText(rs.getString("password"));
                etiNombre.setText(rs.getString("nombre_emp"));
                etiAP.setText(rs.getString("apellidop_emp"));
                etiAM.setText(rs.getString("apellidom_emp"));
                etiDom.setText(rs.getString("domicilio"));
                etiPuesto.setText(rs.getString("puesto"));
                etiExp.setText(rs.getString("experiencia"));
                etiGE.setText(rs.getString("grado_estudios"));
                etiContacto.setText(String.valueOf(rs.getInt("contacto")));
                etiEdad.setText(String.valueOf(rs.getInt("edad")));
                etiEstado.setText(rs.getString("estado"));
            }else{
                JOptionPane.showMessageDialog(null,"Error: no existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 14: no existen o no se ingresaron los datos a buscar y cambiar.\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'consulta()'",Level.WARNING);
            }
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error Prueba (consulta)",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error Prueba: "+e.getMessage()+".\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'consulta()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel1.class.getName(),Level.WARNING,"consulta-Prueba",e.fillInStackTrace());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        etiContra = new javax.swing.JLabel();
        etiNombre = new javax.swing.JLabel();
        cbContra = new javax.swing.JCheckBox();
        cbNombre = new javax.swing.JCheckBox();
        etiAP = new javax.swing.JLabel();
        cbAP = new javax.swing.JCheckBox();
        etiAM = new javax.swing.JLabel();
        cbAM = new javax.swing.JCheckBox();
        txtContra = new javax.swing.JPasswordField();
        txtNombre = new javax.swing.JTextField();
        etiPuesto = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtAP = new javax.swing.JTextField();
        txtAM = new javax.swing.JTextField();
        cbPuesto = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtExp = new javax.swing.JTextField();
        cbExp = new javax.swing.JCheckBox();
        etiExp = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        etiGE = new javax.swing.JLabel();
        cbGE = new javax.swing.JCheckBox();
        txtGE = new javax.swing.JTextField();
        etiContacto = new javax.swing.JLabel();
        cbContacto = new javax.swing.JCheckBox();
        txtContacto = new javax.swing.JTextField();
        etiEdad = new javax.swing.JLabel();
        cbEdad = new javax.swing.JCheckBox();
        txtEdad = new javax.swing.JTextField();
        etiEstado = new javax.swing.JLabel();
        cbEstado = new javax.swing.JCheckBox();
        jComboBox2 = new javax.swing.JComboBox<>();
        etiDom = new javax.swing.JLabel();
        cbDomicilio = new javax.swing.JCheckBox();
        txtDom = new javax.swing.JTextField();

        closeButton.setText("Cerrar");

        etiContra.setText("Contraseña");

        etiNombre.setText("Nombre(s)");

        etiAP.setText("Apellido paterno");

        etiAM.setText("Apellido materno");

        etiPuesto.setText("Puesto");

        jButton1.setText("Actualizar datos");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Programador", "Desarrollador", "Dueño" }));

        etiExp.setText("Experiencia");

        searchButton.setText("Buscar");

        etiGE.setText("Grado de estudios");

        etiContacto.setText("Contacto");

        etiEdad.setText("Edad");

        etiEstado.setText("Estado");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));

        etiDom.setText("Domicilio");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(etiDom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiEdad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiContacto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiGE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(etiExp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiPuesto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiAM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiAP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiContra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbContra)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbAP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAP))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbAM)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAM))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cbExp, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbGE, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtExp)
                                    .addComponent(txtGE, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbDomicilio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDom)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbContra)
                    .addComponent(etiContra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNombre)
                    .addComponent(etiNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAP)
                    .addComponent(etiAP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAM)
                    .addComponent(etiAM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etiDom)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbDomicilio)))
                    .addComponent(txtDom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPuesto)
                    .addComponent(etiPuesto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbExp)
                    .addComponent(txtExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiExp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbGE)
                    .addComponent(txtGE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiGE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbContacto)
                    .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiContacto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbEdad)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiEdad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiEstado)
                    .addComponent(cbEstado)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeButton)
                    .addComponent(jButton1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbAM;
    private javax.swing.JCheckBox cbAP;
    private javax.swing.JCheckBox cbContacto;
    private javax.swing.JCheckBox cbContra;
    private javax.swing.JCheckBox cbDomicilio;
    private javax.swing.JCheckBox cbEdad;
    private javax.swing.JCheckBox cbEstado;
    private javax.swing.JCheckBox cbExp;
    private javax.swing.JCheckBox cbGE;
    private javax.swing.JCheckBox cbNombre;
    private javax.swing.JCheckBox cbPuesto;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel etiAM;
    private javax.swing.JLabel etiAP;
    private javax.swing.JLabel etiContacto;
    private javax.swing.JLabel etiContra;
    private javax.swing.JLabel etiDom;
    private javax.swing.JLabel etiEdad;
    private javax.swing.JLabel etiEstado;
    private javax.swing.JLabel etiExp;
    private javax.swing.JLabel etiGE;
    private javax.swing.JLabel etiNombre;
    private javax.swing.JLabel etiPuesto;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField txtAM;
    private javax.swing.JTextField txtAP;
    private javax.swing.JTextField txtContacto;
    private javax.swing.JPasswordField txtContra;
    private javax.swing.JTextField txtDom;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtExp;
    private javax.swing.JTextField txtGE;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}