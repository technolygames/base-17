package paneles;
//clases
import clases.datos;
import clases.guiMediaHandler;
import clases.logger;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

public class modDatosPanel1 extends javax.swing.JPanel{
    public modDatosPanel1(){
        initComponents();
        new guiMediaHandler(modDatosPanel1.class.getName()).LookAndFeel(modDatosPanel1.this);
        
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
        etiCURP.setToolTipText("Clave única de registro de población");
        etiPuesto.setToolTipText("Puesto");
        etiExp.setToolTipText("Experiencia");
        etiGE.setToolTipText("Grado de estudios");
        etiContacto.setToolTipText("Contacto");
        etiFN.setToolTipText("Fecha de nacimiento");
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
                cbCURP.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbFN.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbCURP.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbFN.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                txtContra.setEnabled(true);
                //función
                if(cbContra.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtContra.getPassword().equals("")&&cbContra.isSelected()==true&&txtContra.isEnabled()==true){
                            while(!txtContra.getPassword().equals("")&&cbContra.isSelected()==true&&txtContra.isEnabled()==true){
                                new datos().actualizarDatosEmpleado("set password='"+String.valueOf(txtContra.getPassword())+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbContra.isSelected()==false){
                //enabled
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbCURP.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbFN.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                txtContra.setEnabled(false);
                txtContra.setText("");
            }
        });
        
        cbNombre.addActionListener((a)->{
            if(cbNombre.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbCURP.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbFN.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbCURP.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbFN.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                txtNombre.setEnabled(true);
                //función
                if(cbNombre.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtNombre.getText().equals("")&&cbNombre.isSelected()==true&&txtNombre.isEnabled()==true){
                            while(!txtNombre.getText().equals("")&&cbNombre.isSelected()==true&&txtNombre.isEnabled()==true){
                                new datos().actualizarDatosEmpleado("set nombre_emp='"+txtNombre.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbNombre.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbCURP.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbFN.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                txtNombre.setEnabled(false);
                txtNombre.setText("");
            }
        });
        
        cbAP.addActionListener((a)->{
            if(cbAP.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAM.setSelected(false);
                cbCURP.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbFN.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAM.setEnabled(false);
                cbCURP.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbFN.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                txtAP.setEnabled(true);
                //función
                if(cbAP.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtAP.getText().equals("")&&cbAP.isSelected()==true&&txtAP.isEnabled()==true){
                            while(!txtAP.getText().equals("")&&cbAP.isSelected()==true&&txtAP.isEnabled()==true){
                                new datos().actualizarDatosEmpleado("set apellidop_amp='"+txtAP.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbAP.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAM.setEnabled(true);
                cbCURP.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbFN.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                txtAP.setEnabled(false);
                txtAP.setText("");
            }
        });
        
        cbAM.addActionListener((a)->{
            if(cbAM.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbCURP.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbFN.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbCURP.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbFN.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                txtAM.setEnabled(true);
                //función
                if(cbAM.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtAM.getText().equals("")&&cbAM.isSelected()==true&&txtAM.isEnabled()==true){
                            while(!txtAM.getText().equals("")&&cbAM.isSelected()==true&&txtAM.isEnabled()==true){
                                new datos().actualizarDatosEmpleado("set apellidom_emp='"+txtAM.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbAM.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbCURP.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbFN.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                txtAM.setEnabled(false);
                txtAM.setText("");
            }
        });
        
        cbCURP.addActionListener((a)->{
            if(cbCURP.isSelected()==true){
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
                cbFN.setSelected(false);
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
                cbContacto.setEnabled(false);
                cbFN.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                txtCURP.setEnabled(true);
                //función
                if(cbCURP.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtCURP.getText().equals("")&&cbCURP.isSelected()==true&&txtCURP.isEnabled()==true){
                            while(!txtCURP.getText().equals("")&&cbCURP.isSelected()==true&&txtCURP.isEnabled()==true){
                                new datos().actualizarDatosEmpleado("set curp='"+txtCURP.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbCURP.isSelected()==false){
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
                cbFN.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                txtCURP.setEnabled(false);
                txtCURP.setText("");
            }
        });
        
        cbDomicilio.addActionListener((a)->{
            if(cbDomicilio.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbCURP.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbFN.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbCURP.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbFN.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                txtDom.setEnabled(true);
                //función
                if(cbDomicilio.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtDom.getText().equals("")&&cbDomicilio.isSelected()==true&&txtDom.isEnabled()==true){
                            while(!txtDom.getText().equals("")&&cbDomicilio.isSelected()==true&&txtDom.isEnabled()==true){
                                new datos().actualizarDatosEmpleado("set domicilio='"+txtDom.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbDomicilio.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbCURP.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbFN.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtDom.setEnabled(false);
                txtDom.setText("");
            }
        });
        
        cbPuesto.addActionListener((a)->{
            if(cbPuesto.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbCURP.setSelected(false);
                cbDomicilio.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbFN.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbCURP.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbFN.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //combobox
                jComboBox1.setEnabled(true);
                //textfields
                txtSearch.setEnabled(false);
                //función
                if(cbPuesto.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!jComboBox1.getModel().getSelectedItem().equals(etiPuesto.getText())&&cbPuesto.isSelected()==true&&jComboBox1.isEnabled()==true){
                            while(!jComboBox1.getModel().getSelectedItem().equals(etiPuesto.getText())&&cbPuesto.isSelected()==true&&jComboBox1.isEnabled()==true){
                                new datos().actualizarDatosEmpleado("set puesto='"+jComboBox1.getSelectedItem().toString()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbPuesto.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbCURP.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbFN.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //combobox
                jComboBox1.setEnabled(false);
                //textfields
                txtSearch.setEnabled(true);
            }
        });
        
        cbExp.addActionListener((a)->{
            if(cbExp.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbCURP.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbFN.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbCURP.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbFN.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                txtExp.setEnabled(true);
                //función
                if(cbExp.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtExp.getText().equals("")&&cbExp.isSelected()==true&&txtExp.isEnabled()==true){
                            while(!txtExp.getText().equals("")&&cbExp.isSelected()==true&&txtExp.isEnabled()==true){
                                new datos().actualizarDatosEmpleado("set experiencia='"+txtExp.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbExp.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbCURP.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbFN.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                txtExp.setEnabled(false);
                txtExp.setText("");
            }
        });
        
        cbGE.addActionListener((a)->{
            if(cbGE.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbCURP.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbContacto.setSelected(false);
                cbFN.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbCURP.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbContacto.setEnabled(false);
                cbFN.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                txtGE.setEnabled(true);
                //función
                if(cbGE.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtGE.getText().equals("")&&cbGE.isSelected()==true&&txtGE.isEnabled()==true){
                            while(!txtGE.getText().equals("")&&cbGE.isSelected()==true&&txtGE.isEnabled()==true){
                                new datos().actualizarDatosEmpleado("set grado_estudios='"+txtGE.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbGE.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbCURP.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbContacto.setEnabled(true);
                cbFN.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                txtGE.setEnabled(false);
                txtGE.setText("");
            }
        });
        
        cbContacto.addActionListener((a)->{
            if(cbContacto.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbCURP.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbFN.setSelected(false);
                cbEdad.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbCURP.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbFN.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                txtContacto.setEnabled(true);
                //función
                if(cbContacto.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtContacto.getText().equals("")&&cbContacto.isSelected()==true&&txtContacto.isEnabled()==true){
                            while(!txtContacto.getText().equals("")&&cbContacto.isSelected()==true&&txtContacto.isEnabled()==true){
                                new datos().actualizarDatosEmpleado("set contacto='"+txtContacto.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbContacto.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbCURP.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbFN.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                txtContacto.setEnabled(false);
                txtContacto.setText("");
            }
        });
        
        cbFN.addActionListener((a)->{
            if(cbFN.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbCURP.setSelected(false);
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
                cbAM.setEnabled(false);
                cbCURP.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                dcFN.setEnabled(true);
                //función
                if(cbFN.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!dcFN.getDate().equals("")&&cbFN.isSelected()==true&&dcFN.isEnabled()==true){
                            while(!dcFN.getDate().equals("")&&cbFN.isSelected()==true&&dcFN.isEnabled()==true){
                                new datos().actualizarDatosEmpleado("set fecha_nacimiento='"+new java.sql.Date(dcFN.getDate().getTime())+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbFN.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbCURP.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                dcFN.setEnabled(false);
                dcFN.setDate(null);
            }
        });
        
        cbEdad.addActionListener((a)->{
            if(cbEdad.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbCURP.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbFN.setSelected(false);
                cbEstado.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbCURP.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbFN.setEnabled(false);
                cbEstado.setEnabled(false);
                //textfields
                txtSearch.setEnabled(false);
                txtEdad.setEnabled(true);
                //función
                if(cbEdad.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!txtEdad.getText().equals("")&&cbEdad.isSelected()==true&&txtEdad.isEnabled()==true){
                            while(!txtEdad.getText().equals("")&&cbEdad.isSelected()==true&&txtEdad.isEnabled()==true){
                                new datos().actualizarDatosEmpleado("set edad='"+txtEdad.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbEdad.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbCURP.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbFN.setEnabled(true);
                cbEstado.setEnabled(true);
                //textfields
                txtSearch.setEnabled(true);
                txtEdad.setEnabled(false);
                txtEdad.setText("");
            }
        });
        
        cbEstado.addActionListener((a)->{
            if(cbEstado.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
                cbCURP.setSelected(false);
                cbDomicilio.setSelected(false);
                cbPuesto.setSelected(false);
                cbExp.setSelected(false);
                cbGE.setSelected(false);
                cbContacto.setSelected(false);
                cbFN.setSelected(false);
                cbEdad.setSelected(false);
                //enabled
                cbContra.setEnabled(false);
                cbNombre.setEnabled(false);
                cbAP.setEnabled(false);
                cbAM.setEnabled(false);
                cbCURP.setEnabled(false);
                cbDomicilio.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbFN.setEnabled(false);
                cbEdad.setEnabled(false);
                //combo
                jComboBox2.setEnabled(true);
                //textfields
                txtSearch.setEnabled(false);
                //función
                if(cbEstado.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        if(!jComboBox2.getModel().getSelectedItem().equals(etiEstado.getText())&&cbEstado.isSelected()==true&&jComboBox2.isEnabled()==true){
                            while(!jComboBox2.getModel().getSelectedItem().equals(etiEstado.getText())&&cbEstado.isSelected()==true&&jComboBox2.isEnabled()==true){
                                new datos().actualizarDatosEmpleado("set estado='"+jComboBox2.getSelectedItem().toString()+"' where codigo_emp='"+txtSearch.getText()+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbEstado.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbCURP.setEnabled(true);
                cbDomicilio.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbFN.setEnabled(true);
                cbEdad.setEnabled(true);
                //combo
                jComboBox2.setEnabled(false);
                //textfields
                txtSearch.setEnabled(true);
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
                new logger(Level.WARNING).staticLogger("Error 14: no existen o no se ingresaron los datos a buscar y cambiar.\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'consulta()'");
            }
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en '"+modDatosPanel1.class.getName()+"', en el método 'consulta()'");
            new logger(Level.SEVERE).exceptionLogger(modDatosPanel1.class.getName(),"consulta-14",e.fillInStackTrace());
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
        etiCURP = new javax.swing.JLabel();
        cbCURP = new javax.swing.JCheckBox();
        txtCURP = new javax.swing.JTextField();
        etiFN = new javax.swing.JLabel();
        cbFN = new javax.swing.JCheckBox();
        dcFN = new com.toedter.calendar.JDateChooser();

        closeButton.setText("Cerrar panel");

        etiContra.setText("Contraseña");

        etiNombre.setText("Nombre(s)");

        etiAP.setText("Apellido paterno");

        etiAM.setText("Apellido materno");

        txtContra.setEnabled(false);

        txtNombre.setEnabled(false);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        etiPuesto.setText("Puesto");

        jButton1.setText("Actualizar datos");

        txtAP.setEnabled(false);
        txtAP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAPKeyPressed(evt);
            }
        });

        txtAM.setEnabled(false);
        txtAM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAMKeyPressed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Programador", "Desarrollador", "Dueño" }));
        jComboBox1.setEnabled(false);

        txtExp.setEnabled(false);
        txtExp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtExpKeyPressed(evt);
            }
        });

        etiExp.setText("Experiencia");

        searchButton.setText("Buscar");

        etiGE.setText("Grado de estudios");

        txtGE.setEnabled(false);
        txtGE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGEKeyPressed(evt);
            }
        });

        etiContacto.setText("Contacto");

        txtContacto.setEnabled(false);
        txtContacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContactoKeyPressed(evt);
            }
        });

        etiEdad.setText("Edad");

        txtEdad.setEnabled(false);
        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEdadKeyPressed(evt);
            }
        });

        etiEstado.setText("Estado");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        jComboBox2.setEnabled(false);

        etiDom.setText("Domicilio");

        txtDom.setEnabled(false);

        etiCURP.setText("CURP");

        txtCURP.setEnabled(false);

        etiFN.setText("Fecha de nacimiento");

        dcFN.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(etiEdad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(etiDom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiContacto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiGE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiExp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiPuesto, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(etiFN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbFN)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcFN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbExp, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbGE, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtExp)
                                    .addComponent(txtGE, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbDomicilio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDom, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(etiAM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(etiAP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiContra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiCURP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbCURP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCURP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                                    .addComponent(txtAM)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton)))
                .addContainerGap(133, Short.MAX_VALUE))
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etiContra)
                        .addComponent(cbContra)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etiNombre)
                        .addComponent(cbNombre)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etiAP)
                        .addComponent(cbAP)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etiAM)
                        .addComponent(cbAM)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCURP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etiCURP)
                        .addComponent(cbCURP)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etiDom)
                        .addComponent(cbDomicilio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etiPuesto)
                        .addComponent(cbPuesto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etiExp)
                        .addComponent(cbExp)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etiGE)
                        .addComponent(cbGE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etiContacto)
                        .addComponent(cbContacto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcFN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etiFN)
                        .addComponent(cbFN)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etiEdad)
                        .addComponent(cbEdad)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etiEstado)
                        .addComponent(cbEstado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel1.class.getName()+"', en el método 'txtNombreKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyPressed
    
    private void txtAPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAPKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel1.class.getName()+"', en el método 'txtAPKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtAPKeyPressed
    
    private void txtAMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAMKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel1.class.getName()+"', en el método 'txtAMKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtAMKeyPressed
    
    private void txtExpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExpKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel1.class.getName()+"', en el método 'txtExpKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtExpKeyPressed
    
    private void txtGEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGEKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel1.class.getName()+"', en el método 'txtGEKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtGEKeyPressed
    
    private void txtContactoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactoKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel1.class.getName()+"', en el método 'txtContactoKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtContactoKeyPressed
    
    private void txtEdadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+modDatosPanel1.class.getName()+"', en el método 'txtEdadKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtEdadKeyPressed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbAM;
    private javax.swing.JCheckBox cbAP;
    private javax.swing.JCheckBox cbCURP;
    private javax.swing.JCheckBox cbContacto;
    private javax.swing.JCheckBox cbContra;
    private javax.swing.JCheckBox cbDomicilio;
    private javax.swing.JCheckBox cbEdad;
    private javax.swing.JCheckBox cbEstado;
    private javax.swing.JCheckBox cbExp;
    private javax.swing.JCheckBox cbFN;
    private javax.swing.JCheckBox cbGE;
    private javax.swing.JCheckBox cbNombre;
    private javax.swing.JCheckBox cbPuesto;
    private javax.swing.JButton closeButton;
    private com.toedter.calendar.JDateChooser dcFN;
    private javax.swing.JLabel etiAM;
    private javax.swing.JLabel etiAP;
    private javax.swing.JLabel etiCURP;
    private javax.swing.JLabel etiContacto;
    private javax.swing.JLabel etiContra;
    private javax.swing.JLabel etiDom;
    private javax.swing.JLabel etiEdad;
    private javax.swing.JLabel etiEstado;
    private javax.swing.JLabel etiExp;
    private javax.swing.JLabel etiFN;
    private javax.swing.JLabel etiGE;
    private javax.swing.JLabel etiNombre;
    private javax.swing.JLabel etiPuesto;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField txtAM;
    private javax.swing.JTextField txtAP;
    private javax.swing.JTextField txtCURP;
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