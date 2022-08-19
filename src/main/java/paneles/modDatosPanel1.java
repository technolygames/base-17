package paneles;
//clases
import clases.datos;
import clases.logger;
//java
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
//extension larga
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.logging.Level;

public class modDatosPanel1 extends javax.swing.JPanel{
    public modDatosPanel1(){
        initComponents();
        
        settings();
        botones();
    }
    
    protected void settings(){
        etiContra.setToolTipText("Contraseña");
        etiNombre.setToolTipText("Nombre(s)");
        etiAP.setToolTipText("Apellido paterno");
        etiAM.setToolTipText("Apellido materno");
        etiCURP.setToolTipText("Clave única de registro de población");
        etiDom.setToolTipText("Domicilio");
        etiPuesto.setToolTipText("Puesto");
        etiExp.setToolTipText("Experiencia");
        etiGE.setToolTipText("Grado de estudios");
        etiContacto.setToolTipText("Contacto");
        etiFN.setToolTipText("Fecha de nacimiento");
        etiEdad.setToolTipText("Edad");
        etiEstado.setToolTipText("Estado");
        
        dcFN.setDateFormatString("yyyy-MM-dd");
    }
    
    protected final void botones(){
        var datos=new datos();
        
        closeButton.addActionListener((a)->{
            setVisible(false);
        });
        
        cbContra.addActionListener((a)->{
            JCheckBox[] checkboxes={cbNombre,cbAP,cbAM,cbCURP,cbDomicilio,cbPuesto,cbExp,cbGE,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbContra.isSelected()==true){
                
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                txtContra.setEnabled(true);
                //función
                if(cbContra.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!txtContra.getPassword().equals("")&&cbContra.isSelected()==true&&txtContra.isEnabled()==true){
                            while(!txtContra.getPassword().equals("")&&cbContra.isSelected()==true&&txtContra.isEnabled()==true){
                                datos.actualizarDatos("empleados set password='"+txtContra.getPassword().toString()+"' where codigo_emp='"+Integer.parseInt(txtSearch.getText())+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbContra.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                txtSearch.setEnabled(true);
                txtContra.setEnabled(false);
                txtContra.setText("");
            }
        });
        
        cbNombre.addActionListener((a)->{
            JCheckBox[] checkboxes={cbContra,cbAP,cbAM,cbCURP,cbDomicilio,cbPuesto,cbExp,cbGE,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbNombre.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                txtNombre.setEnabled(true);
                //función
                if(cbNombre.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!txtNombre.getText().equals("")&&cbNombre.isSelected()==true&&txtNombre.isEnabled()==true){
                            while(!txtNombre.getText().equals("")&&cbNombre.isSelected()==true&&txtNombre.isEnabled()==true){
                                datos.actualizarDatos("empleados set nombre_emp='"+txtNombre.getText()+"' where codigo_emp='"+Integer.parseInt(txtSearch.getText())+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbNombre.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                txtSearch.setEnabled(true);
                txtNombre.setEnabled(false);
                txtNombre.setText("");
            }
        });
        
        cbAP.addActionListener((a)->{
            JCheckBox[] checkboxes={cbContra,cbNombre,cbAM,cbCURP,cbDomicilio,cbPuesto,cbExp,cbGE,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbAP.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                txtAP.setEnabled(true);
                //función
                if(cbAP.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!txtAP.getText().equals("")&&cbAP.isSelected()==true&&txtAP.isEnabled()==true){
                            while(!txtAP.getText().equals("")&&cbAP.isSelected()==true&&txtAP.isEnabled()==true){
                                datos.actualizarDatos("empleados set apellidop_amp='"+txtAP.getText()+"' where codigo_emp='"+Integer.parseInt(txtSearch.getText())+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbAP.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                txtSearch.setEnabled(true);
                txtAP.setEnabled(false);
                txtAP.setText("");
            }
        });
        
        cbAM.addActionListener((a)->{
            JCheckBox[] checkboxes={cbContra,cbNombre,cbAP,cbCURP,cbDomicilio,cbPuesto,cbExp,cbGE,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbAM.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                txtAM.setEnabled(true);
                //función
                if(cbAM.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!txtAM.getText().equals("")&&cbAM.isSelected()==true&&txtAM.isEnabled()==true){
                            while(!txtAM.getText().equals("")&&cbAM.isSelected()==true&&txtAM.isEnabled()==true){
                                datos.actualizarDatos("empleados set apellidom_emp='"+txtAM.getText()+"' where codigo_emp='"+Integer.parseInt(txtSearch.getText())+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbAM.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                txtSearch.setEnabled(true);
                txtAM.setEnabled(false);
                txtAM.setText("");
            }
        });
        
        cbCURP.addActionListener((a)->{
            JCheckBox[] checkboxes={cbContra,cbNombre,cbAP,cbAM,cbDomicilio,cbPuesto,cbExp,cbGE,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbCURP.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                txtCURP.setEnabled(true);
                //función
                if(cbCURP.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!txtCURP.getText().equals("")&&cbCURP.isSelected()==true&&txtCURP.isEnabled()==true){
                            while(!txtCURP.getText().equals("")&&cbCURP.isSelected()==true&&txtCURP.isEnabled()==true){
                                datos.actualizarDatos("empleados set curp='"+txtCURP.getText()+"' where codigo_emp='"+Integer.parseInt(txtSearch.getText())+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbCURP.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                txtSearch.setEnabled(true);
                txtCURP.setEnabled(false);
                txtCURP.setText("");
            }
        });
        
        cbDomicilio.addActionListener((a)->{
            JCheckBox[] checkboxes={cbContra,cbNombre,cbAP,cbAM,cbCURP,cbPuesto,cbExp,cbGE,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbDomicilio.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                txtDom.setEnabled(true);
                //función
                if(cbDomicilio.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!txtDom.getText().equals("")&&cbDomicilio.isSelected()==true&&txtDom.isEnabled()==true){
                            while(!txtDom.getText().equals("")&&cbDomicilio.isSelected()==true&&txtDom.isEnabled()==true){
                                datos.actualizarDatos("empleados set domicilio='"+txtDom.getText()+"' where codigo_emp='"+Integer.parseInt(txtSearch.getText())+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbDomicilio.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                txtSearch.setEditable(true);
                txtDom.setEnabled(false);
                txtDom.setText("");
            }
        });
        
        cbPuesto.addActionListener((a)->{
            JCheckBox[] checkboxes={cbContra,cbNombre,cbAP,cbAM,cbCURP,cbDomicilio,cbExp,cbGE,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbPuesto.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //combobox
                jComboBox1.setEnabled(true);
                //textfields
                txtSearch.setEnabled(false);
                //función
                if(cbPuesto.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!jComboBox1.getModel().getSelectedItem().equals(etiPuesto.getText())&&cbPuesto.isSelected()==true&&jComboBox1.isEnabled()==true){
                            while(!jComboBox1.getModel().getSelectedItem().equals(etiPuesto.getText())&&cbPuesto.isSelected()==true&&jComboBox1.isEnabled()==true){
                                datos.actualizarDatos("empleados set puesto='"+jComboBox1.getSelectedItem().toString()+"' where codigo_emp='"+Integer.parseInt(txtSearch.getText())+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbPuesto.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //combobox
                jComboBox1.setEnabled(false);
                jComboBox1.getModel().setSelectedItem("Empleado");
                //textfields
                txtSearch.setEnabled(true);
            }
        });
        
        cbExp.addActionListener((a)->{
            JCheckBox[] checkboxes={cbContra,cbNombre,cbAP,cbAM,cbCURP,cbDomicilio,cbPuesto,cbGE,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbExp.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                txtExp.setEnabled(true);
                //función
                if(cbExp.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!txtExp.getText().equals("")&&cbExp.isSelected()==true&&txtExp.isEnabled()==true){
                            while(!txtExp.getText().equals("")&&cbExp.isSelected()==true&&txtExp.isEnabled()==true){
                                datos.actualizarDatos("empleados set experiencia='"+Integer.parseInt(txtExp.getText())+"' where codigo_emp='"+Integer.parseInt(txtSearch.getText())+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbExp.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                txtSearch.setEnabled(true);
                txtExp.setEnabled(false);
                txtExp.setText("");
            }
        });
        
        cbGE.addActionListener((a)->{
            JCheckBox[] checkboxes={cbContra,cbNombre,cbAP,cbAM,cbCURP,cbDomicilio,cbPuesto,cbExp,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbGE.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                txtGE.setEnabled(true);
                //función
                if(cbGE.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!txtGE.getText().equals("")&&cbGE.isSelected()==true&&txtGE.isEnabled()==true){
                            while(!txtGE.getText().equals("")&&cbGE.isSelected()==true&&txtGE.isEnabled()==true){
                                datos.actualizarDatos("empleados set grado_estudios='"+txtGE.getText()+"' where codigo_emp='"+Integer.parseInt(txtSearch.getText())+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbGE.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                txtSearch.setEnabled(true);
                txtGE.setEnabled(false);
                txtGE.setText("");
            }
        });
        
        cbContacto.addActionListener((a)->{
            JCheckBox[] checkboxes={cbContra,cbNombre,cbAP,cbAM,cbCURP,cbDomicilio,cbPuesto,cbExp,cbGE,cbFN,cbEdad,cbEstado};
            if(cbContacto.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                txtContacto.setEnabled(true);
                //función
                if(cbContacto.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!txtContacto.getText().equals("")&&cbContacto.isSelected()==true&&txtContacto.isEnabled()==true){
                            while(!txtContacto.getText().equals("")&&cbContacto.isSelected()==true&&txtContacto.isEnabled()==true){
                                datos.actualizarDatos("empleados set contacto='"+Integer.parseInt(txtContacto.getText())+"' where codigo_emp='"+Integer.parseInt(txtSearch.getText())+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbContacto.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                txtSearch.setEnabled(true);
                txtContacto.setEnabled(false);
                txtContacto.setText("");
            }
        });
        
        cbFN.addActionListener((a)->{
            JCheckBox[] checkboxes={cbContra,cbNombre,cbAP,cbAM,cbCURP,cbDomicilio,cbPuesto,cbExp,cbGE,cbContacto,cbEdad,cbEstado};
            if(cbFN.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                dcFN.setEnabled(true);
                //función
                if(cbFN.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!dcFN.getDate().equals("")&&cbFN.isSelected()==true&&dcFN.isEnabled()==true){
                            while(!dcFN.getDate().equals("")&&cbFN.isSelected()==true&&dcFN.isEnabled()==true){
                                datos.actualizarDatos("empleados set fecha_nacimiento='"+new Date(dcFN.getDate().getTime())+"' where codigo_emp='"+Integer.parseInt(txtSearch.getText())+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbFN.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                txtSearch.setEnabled(true);
                dcFN.setEnabled(false);
                dcFN.setDate(null);
            }
        });
        
        cbEdad.addActionListener((a)->{
            JCheckBox[] checkboxes={cbContra,cbNombre,cbAP,cbAM,cbCURP,cbDomicilio,cbPuesto,cbExp,cbGE,cbContacto,cbFN,cbEstado};
            if(cbEdad.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                txtSearch.setEnabled(false);
                txtEdad.setEnabled(true);
                //función
                if(cbEdad.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!txtEdad.getText().equals("")&&cbEdad.isSelected()==true&&txtEdad.isEnabled()==true){
                            while(!txtEdad.getText().equals("")&&cbEdad.isSelected()==true&&txtEdad.isEnabled()==true){
                                datos.actualizarDatos("empleados set edad='"+Integer.parseInt(txtEdad.getText())+"' where codigo_emp='"+Integer.parseInt(txtSearch.getText())+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbEdad.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                txtSearch.setEnabled(true);
                txtEdad.setEnabled(false);
                txtEdad.setText("");
            }
        });
        
        cbEstado.addActionListener((a)->{
            JCheckBox[] checkboxes={cbContra,cbNombre,cbAP,cbAM,cbCURP,cbDomicilio,cbPuesto,cbExp,cbGE,cbContacto,cbFN,cbEdad};
            if(cbEstado.isSelected()==true){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //combo
                jComboBox2.setEnabled(true);
                //textfields
                txtSearch.setEnabled(false);
                //función
                if(cbEstado.isSelected()==true){
                    updateButton.addActionListener((b)->{
                        if(!jComboBox2.getModel().getSelectedItem().equals(etiEstado.getText())&&cbEstado.isSelected()==true&&jComboBox2.isEnabled()==true){
                            while(!jComboBox2.getModel().getSelectedItem().equals(etiEstado.getText())&&cbEstado.isSelected()==true&&jComboBox2.isEnabled()==true){
                                datos.actualizarDatos("empleados set estado='"+jComboBox2.getSelectedItem().toString()+"' where codigo_emp='"+Integer.parseInt(txtSearch.getText())+"';");
                                consulta();
                                break;
                            }
                        }
                    });
                }
            }else if(cbEstado.isSelected()==false){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //combo
                jComboBox2.setEnabled(false);
                jComboBox2.getModel().setSelectedItem("Activo");
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
            PreparedStatement ps=new datos().getConnection().prepareStatement("select * from empleados where codigo_emp='"+Integer.parseInt(txtSearch.getText())+"';");
            ResultSet rs=ps.executeQuery();
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
                etiFN.setText(rs.getDate("fecha_nacimiento").toString());
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
        updateButton = new javax.swing.JButton();
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

        updateButton.setText("Actualizar datos");

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
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiContra, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiAP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiAM, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiCURP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiDom, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiExp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiGE, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiFN, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbDomicilio, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbPuesto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbExp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbGE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbContacto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbFN, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbAP, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbAM, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbCURP, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(cbContra)
                            .addComponent(cbNombre)
                            .addComponent(cbEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDom, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCURP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtExp, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGE, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcFN, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
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
                    .addComponent(etiNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiAP)
                    .addComponent(txtAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiAM)
                    .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiCURP)
                    .addComponent(txtCURP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCURP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiDom)
                    .addComponent(txtDom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDomicilio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiPuesto)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPuesto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiExp)
                    .addComponent(txtExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbExp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiGE)
                    .addComponent(txtGE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbGE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiContacto)
                    .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbContacto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiFN)
                    .addComponent(dcFN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiEdad)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEdad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiEstado)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton)
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
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}