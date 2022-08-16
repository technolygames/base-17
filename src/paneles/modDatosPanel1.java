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
            new logger().logStaticSaver("Error CNFE: "+e.getMessage()+".\nOcurrió en '"+modDatosPanel1.class.getName()+" modDatosPanel1()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel1.class.getName(),Level.WARNING,"modDatosPanel1-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IE: "+x.getMessage()+".\nOcurrió en '"+modDatosPanel1.class.getName()+" modDatosPanel1()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel1.class.getName(),Level.WARNING,"modDatosPanel1-IE",x.fillInStackTrace());
        }catch(IllegalAccessException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IAE: "+ñ.getMessage()+".\nOcurrió en '"+modDatosPanel1.class.getName()+" modDatosPanel1()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel1.class.getName(),Level.WARNING,"modDatosPanel1-IAE",ñ.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 28: "+k.getMessage()+".\nOcurrió en '"+modDatosPanel1.class.getName()+" modDatosPanel1()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel1.class.getName(),Level.WARNING,"modDatosPanel1-28",k.fillInStackTrace());
        }catch(FileNotFoundException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+y.getMessage()+".\nOcurrió en '"+modDatosPanel1.class.getName()+" modDatosPanel1()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel1.class.getName(),Level.WARNING,"modDatosPanel1-1IO",y.fillInStackTrace());
        }catch(IOException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+s.getMessage()+".\nOcurrió en '"+modDatosPanel1.class.getName()+" modDatosPanel1()'",Level.WARNING);
            new logger().exceptionLogger(modDatosPanel1.class.getName(),Level.WARNING,"modDatosPanel1-1IO",s.fillInStackTrace());
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
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtNombre.setEditable(false);
                txtAP.setEditable(false);
                txtAM.setEditable(false);
                txtExp.setEditable(false);
                txtGE.setEditable(false);
                txtContacto.setEditable(false);
                txtEdad.setEditable(false);
                txtEstado.setEditable(false);
                //función
                if(cbContra.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        new datos().actualizarDatosEmpleado("set password='"+String.valueOf(txtContra.getPassword())+"' where codigo_emp='"+txtSearch.getText()+"';");
                        consulta();
                    });
                }
            }else if(cbContra.isSelected()==false){
                //enabled
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
                //textfields
                txtSearch.setEditable(true);
                txtNombre.setEditable(true);
                txtAP.setEditable(true);
                txtAM.setEditable(true);
                txtExp.setEditable(true);
                txtGE.setEditable(true);
                txtContacto.setEditable(true);
                txtEdad.setEditable(true);
                txtEstado.setEditable(true);
            }
        });
        
        cbNombre.addActionListener((a)->{
            if(cbNombre.isSelected()==true){
                //selected
                cbContra.setSelected(false);
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
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtAP.setEditable(false);
                txtAM.setEditable(false);
                txtExp.setEditable(false);
                txtGE.setEditable(false);
                txtContacto.setEditable(false);
                txtEdad.setEditable(false);
                txtEstado.setEditable(false);
                //función
                if(cbContra.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        new datos().actualizarDatosEmpleado("set nombre_emp='"+txtNombre.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                        consulta();
                    });
                }
            }else if(cbNombre.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
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
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtAP.setEditable(true);
                txtAM.setEditable(true);
                txtExp.setEditable(true);
                txtGE.setEditable(true);
                txtContacto.setEditable(true);
                txtEdad.setEditable(true);
                txtEstado.setEditable(true);
            }
        });
        
        cbAP.addActionListener((a)->{
            if(cbAP.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
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
                cbAM.setEnabled(false);
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtNombre.setEditable(false);
                txtAM.setEditable(false);
                txtExp.setEditable(false);
                txtGE.setEditable(false);
                txtContacto.setEditable(false);
                txtEdad.setEditable(false);
                txtEstado.setEditable(false);
                //función
                if(cbAP.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        new datos().actualizarDatosEmpleado("set apellidop_amp='"+txtAP.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                        consulta();
                    });
                }
            }else if(cbAP.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAM.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtNombre.setEditable(true);
                txtAM.setEditable(true);
                txtExp.setEditable(true);
                txtGE.setEditable(true);
                txtContacto.setEditable(true);
                txtEdad.setEditable(true);
                txtEstado.setEditable(true);
            }
        });
        
        cbAM.addActionListener((a)->{
            if(cbAM.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
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
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtNombre.setEditable(false);
                txtAP.setEditable(false);
                txtExp.setEditable(false);
                txtGE.setEditable(false);
                txtContacto.setEditable(false);
                txtEdad.setEditable(false);
                txtEstado.setEditable(false);
                //función
                if(cbAM.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        new datos().actualizarDatosEmpleado("set apellidom_emp='"+txtAM.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                        consulta();
                    });
                }
            }else if(cbAM.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtNombre.setEditable(true);
                txtAP.setEditable(true);
                txtExp.setEditable(true);
                txtGE.setEditable(true);
                txtContacto.setEditable(true);
                txtEdad.setEditable(true);
                txtEstado.setEditable(true);
            }
        });
        
        cbPuesto.addActionListener((a)->{
            if(cbPuesto.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
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
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
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
                txtEstado.setEditable(false);
                //función
                if(cbPuesto.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        new datos().actualizarDatosEmpleado("set puesto='"+jComboBox1.getSelectedItem().toString()+"' where codigo_emp='"+txtSearch.getText()+"';");
                        consulta();
                    });
                }
            }else if(cbPuesto.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
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
                txtEstado.setEditable(true);
            }
        });
        
        cbExp.addActionListener((a)->{
            if(cbExp.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
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
                cbPuesto.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtNombre.setEditable(false);
                txtAP.setEditable(false);
                txtAM.setEditable(false);
                txtGE.setEditable(false);
                txtContacto.setEditable(false);
                txtEdad.setEditable(false);
                txtEstado.setEditable(false);
                //función
                if(cbExp.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        new datos().actualizarDatosEmpleado("set experiencia='"+txtExp.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                        consulta();
                    });
                }
            }else if(cbExp.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtNombre.setEditable(true);
                txtAP.setEditable(true);
                txtAM.setEditable(true);
                txtGE.setEditable(true);
                txtContacto.setEditable(true);
                txtEdad.setEditable(true);
                txtEstado.setEditable(true);
            }
        });
        
        cbGE.addActionListener((a)->{
            if(cbGE.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
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
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtNombre.setEditable(false);
                txtAP.setEditable(false);
                txtAM.setEditable(false);
                txtExp.setEditable(false);
                txtContacto.setEditable(false);
                txtEdad.setEditable(false);
                txtEstado.setEditable(false);
                //función
                if(cbGE.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        new datos().actualizarDatosEmpleado("set grado_estudios='"+txtGE.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                        consulta();
                    });
                }
            }else if(cbGE.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtNombre.setEditable(true);
                txtAP.setEditable(true);
                txtAM.setEditable(true);
                txtExp.setEditable(true);
                txtContacto.setEditable(true);
                txtEdad.setEditable(true);
                txtEstado.setEditable(true);
            }
        });
        
        cbContacto.addActionListener((a)->{
            if(cbContacto.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
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
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbEdad.setEnabled(false);
                cbEstado.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtNombre.setEditable(false);
                txtAP.setEditable(false);
                txtAM.setEditable(false);
                txtExp.setEditable(false);
                txtGE.setEditable(false);
                txtEdad.setEditable(false);
                txtEstado.setEditable(false);
                //función
                if(cbContacto.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        new datos().actualizarDatosEmpleado("set contacto='"+txtContacto.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                        consulta();
                    });
                }
            }else if(cbContacto.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbEdad.setEnabled(true);
                cbEstado.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtNombre.setEditable(true);
                txtAP.setEditable(true);
                txtAM.setEditable(true);
                txtExp.setEditable(true);
                txtGE.setEditable(true);
                txtEdad.setEditable(true);
                txtEstado.setEditable(true);
            }
        });
        
        cbEdad.addActionListener((a)->{
            if(cbEdad.isSelected()==true){
                //selected
                cbContra.setSelected(false);
                cbNombre.setSelected(false);
                cbAP.setSelected(false);
                cbAM.setSelected(false);
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
                cbPuesto.setEnabled(false);
                cbExp.setEnabled(false);
                cbGE.setEnabled(false);
                cbContacto.setEnabled(false);
                cbEstado.setEnabled(false);
                //combo
                jComboBox1.setEnabled(false);
                //textfields
                txtSearch.setEditable(false);
                txtContra.setEditable(false);
                txtNombre.setEditable(false);
                txtAP.setEditable(false);
                txtAM.setEditable(false);
                txtExp.setEditable(false);
                txtGE.setEditable(false);
                txtContacto.setEditable(false);
                txtEstado.setEditable(false);
                //función
                if(cbEdad.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        new datos().actualizarDatosEmpleado("set edad='"+txtEdad.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                        consulta();
                    });
                }
            }else if(cbEdad.isSelected()==false){
                //enabled
                cbContra.setEnabled(true);
                cbNombre.setEnabled(true);
                cbAP.setEnabled(true);
                cbAM.setEnabled(true);
                cbPuesto.setEnabled(true);
                cbExp.setEnabled(true);
                cbGE.setEnabled(true);
                cbContacto.setEnabled(true);
                cbEstado.setEnabled(true);
                //combo
                jComboBox1.setEnabled(true);
                //textfields
                txtSearch.setEditable(true);
                txtContra.setEditable(true);
                txtNombre.setEditable(true);
                txtAP.setEditable(true);
                txtAM.setEditable(true);
                txtExp.setEditable(true);
                txtGE.setEditable(true);
                txtContacto.setEditable(true);
                txtEstado.setEditable(true);
            }
        });
        
        cbEstado.addActionListener((a)->{
            if(cbEstado.isSelected()==true){
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
                //combo
                jComboBox1.setEnabled(false);
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
                if(cbEstado.isSelected()==true){
                    jButton1.addActionListener((b)->{
                        new datos().actualizarDatosEmpleado("set estado='"+txtEstado.getText()+"' where codigo_emp='"+txtSearch.getText()+"';");
                        consulta();
                    });
                }
            }else if(cbEstado.isSelected()==false){
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
                //combo
                jComboBox1.setEnabled(true);
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
        
        searchButton.addActionListener((a)->{
            consulta();
        });
    }
    
    protected void consulta(){
        try{
            ps=new datos().getConnection().prepareStatement("select password,nombre_emp,apellidop_emp,apellidom_emp,puesto,experiencia,grado_estudios,contacto,edad,estado from empleados where codigo_emp='"+txtSearch.getText()+"';");
            rs=ps.executeQuery();
            if(rs.next()){
                etiContra.setText(rs.getString("password"));
                etiNombre.setText(rs.getString("nombre_emp"));
                etiAP.setText(rs.getString("apellidop_emp"));
                etiAM.setText(rs.getString("apellidom_emp"));
                etiPuesto.setText(rs.getString("puesto"));
                etiExp.setText(rs.getString("experiencia"));
                etiGE.setText(rs.getString("grado_estudios"));
                etiContacto.setText(String.valueOf(rs.getInt("contacto")));
                etiEdad.setText(String.valueOf(rs.getInt("edad")));
                etiEstado.setText(rs.getString("estado"));
            }else{
                JOptionPane.showMessageDialog(null,"No existen los datos","Error",JOptionPane.WARNING_MESSAGE);
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error Prueba (consulta)",JOptionPane.WARNING_MESSAGE);
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
        txtEstado = new javax.swing.JTextField();

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
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
                                .addComponent(txtEstado))
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbExp, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbGE, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton)))
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiContra)
                        .addGap(12, 12, 12)
                        .addComponent(etiNombre)
                        .addGap(12, 12, 12)
                        .addComponent(etiAP)
                        .addGap(12, 12, 12)
                        .addComponent(etiAM)
                        .addGap(12, 12, 12)
                        .addComponent(etiPuesto)
                        .addGap(9, 9, 9)
                        .addComponent(etiExp)
                        .addGap(12, 12, 12)
                        .addComponent(etiGE)
                        .addGap(12, 12, 12)
                        .addComponent(etiContacto)
                        .addGap(12, 12, 12)
                        .addComponent(etiEdad)
                        .addGap(12, 12, 12)
                        .addComponent(etiEstado))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbContra))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbNombre, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbAP, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbAM))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbPuesto, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbExp)
                            .addComponent(txtExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbGE)
                            .addComponent(txtGE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbContacto)
                            .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbEdad)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbEstado)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbAM;
    private javax.swing.JCheckBox cbAP;
    private javax.swing.JCheckBox cbContacto;
    private javax.swing.JCheckBox cbContra;
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
    private javax.swing.JLabel etiEdad;
    private javax.swing.JLabel etiEstado;
    private javax.swing.JLabel etiExp;
    private javax.swing.JLabel etiGE;
    private javax.swing.JLabel etiNombre;
    private javax.swing.JLabel etiPuesto;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField txtAM;
    private javax.swing.JTextField txtAP;
    private javax.swing.JTextField txtContacto;
    private javax.swing.JPasswordField txtContra;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtExp;
    private javax.swing.JTextField txtGE;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}