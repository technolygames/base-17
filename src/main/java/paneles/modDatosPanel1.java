package paneles;
//clases
import clases.Datos;
import clases.logger;
import clases.PlaceHolder;
//java
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class modDatosPanel1 extends javax.swing.JPanel{
    protected int user;
    protected boolean estado;
    protected String methodName;
    
    protected JCheckBox[] checkboxes;
    
    public modDatosPanel1(){
        initComponents();
        
        estado=true;
        
        botones();
        enabledComponents(true,estado);
        settings();
    }
    
    public modDatosPanel1(int code){
        initComponents();
        
        this.user=code;
        txtSearch.setText(String.valueOf(user));
        estado=false;
        
        botones();
        consulta();
        enabledComponents(false,estado);
        settings();
    }
    
    public modDatosPanel1(int code,boolean flag){
        initComponents();
        
        if(!flag){
            closeButton.setEnabled(false);
        }
        
        this.user=code;
        txtSearch.setText(String.valueOf(user));
        estado=false;
        
        botones();
        consulta();
        enabledComponents(false,estado);
        settings();
    }
    
    protected final void settings(){
        placeHolders();
        
        dcFN.setDateFormatString("yyyy-MM-dd");
    }
    
    protected final void botones(){
        var datos=new Datos();
        String tabla="empleados";
        String campo="codigo_emp";
        
        closeButton.addActionListener(a->
            setVisible(false)
        );
        
        cbContra.addActionListener(a->{
            methodName="botones.cbContra";
            checkboxes=new JCheckBox[]{cbNombre,cbAP,cbAM,cbCURP,cbDomicilio,cbPuesto,cbExp,cbGE,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbContra.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                txtContra.setEnabled(true);
                //función
                updateButton.addActionListener(b->{
                    try{
                        String password=String.valueOf(txtContra.getPassword());
                        user=Integer.parseInt(txtSearch.getText());
                        while(!password.isEmpty()&&cbContra.isSelected()&&txtContra.isEnabled()){
                            datos.actualizarDatosString(tabla,"password",campo,password,user);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE).storeAndViewCaughtException(this,e,modDatosPanel1.class.getName(),methodName,"12");
                    }
                });
            }else{
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                enabledComponents(true,estado);
                txtContra.setEnabled(false);
                txtContra.setText("");
            }
        });
        
        cbNombre.addActionListener(a->{
            methodName="botones.cbNombre";
            checkboxes=new JCheckBox[]{cbContra,cbAP,cbAM,cbCURP,cbDomicilio,cbPuesto,cbExp,cbGE,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbNombre.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                txtNombre.setEnabled(true);
                //función
                updateButton.addActionListener(b->{
                    try{
                        String name=txtNombre.getText();
                        user=Integer.parseInt(txtSearch.getText());
                        while(!name.isEmpty()&&cbNombre.isSelected()&&txtNombre.isEnabled()){
                            datos.actualizarDatosString(tabla,"nombre_emp",campo,name,user);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE).storeAndViewCaughtException(this,e,modDatosPanel1.class.getName(),methodName,"12");
                    }
                });
            }else{
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                enabledComponents(true,estado);
                txtNombre.setEnabled(false);
                txtNombre.setText("");
                placeHolders();
            }
        });
        
        cbAP.addActionListener(a->{
            methodName="botones.cbAP";
            checkboxes=new JCheckBox[]{cbContra,cbNombre,cbAM,cbCURP,cbDomicilio,cbPuesto,cbExp,cbGE,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbAP.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                txtAP.setEnabled(true);
                //función
                updateButton.addActionListener(b->{
                    try{
                        String lastname=txtAP.getText();
                        user=Integer.parseInt(txtSearch.getText());
                        while(!lastname.isEmpty()&&cbAP.isSelected()&&txtAP.isEnabled()){
                            datos.actualizarDatosString(tabla,"apellidop_emp",campo,lastname,user);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE).storeAndViewCaughtException(this,e,modDatosPanel1.class.getName(),methodName,"12");
                    }
                });
            }else{
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                enabledComponents(true,estado);
                txtAP.setEnabled(false);
                txtAP.setText("");
                placeHolders();
            }
        });
        
        cbAM.addActionListener(a->{
            methodName="botones.cbAM";
            checkboxes=new JCheckBox[]{cbContra,cbNombre,cbAP,cbCURP,cbDomicilio,cbPuesto,cbExp,cbGE,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbAM.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                txtAM.setEnabled(true);
                //función
                updateButton.addActionListener(b->{
                    try{
                        String surname=txtAM.getText();
                        user=Integer.parseInt(txtSearch.getText());
                        while(!surname.isEmpty()&&cbAM.isSelected()&&txtAM.isEnabled()){
                            datos.actualizarDatosString(tabla,"apellidom_emp",campo,surname,user);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE).storeAndViewCaughtException(this,e,modDatosPanel1.class.getName(),methodName,"12");
                    }
                });
            }else{
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                enabledComponents(true,estado);
                txtAM.setEnabled(false);
                txtAM.setText("");
                placeHolders();
            }
        });
        
        cbCURP.addActionListener(a->{
            methodName="botones.cbCURP";
            checkboxes=new JCheckBox[]{cbContra,cbNombre,cbAP,cbAM,cbDomicilio,cbPuesto,cbExp,cbGE,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbCURP.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                txtCURP.setEnabled(true);
                //función
                updateButton.addActionListener(b->{
                    try{
                        String id=txtCURP.getText();
                        user=Integer.parseInt(txtSearch.getText());
                        while(!id.isEmpty()&&cbCURP.isSelected()&&txtCURP.isEnabled()){
                            datos.actualizarDatosString(tabla,"curp",campo,id,user);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE).storeAndViewCaughtException(this,e,modDatosPanel1.class.getName(),methodName,"12");
                    }
                });
            }else{
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                enabledComponents(true,estado);
                txtCURP.setEnabled(false);
                txtCURP.setText("");
                placeHolders();
            }
        });
        
        cbDomicilio.addActionListener(a->{
            methodName="botones.cbDomicilio";
            checkboxes=new JCheckBox[]{cbContra,cbNombre,cbAP,cbAM,cbCURP,cbPuesto,cbExp,cbGE,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbDomicilio.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                txtDom.setEnabled(true);
                //función
                updateButton.addActionListener(b->{
                    try{
                        String add=txtDom.getText();
                        user=Integer.parseInt(txtSearch.getText());
                        while(!add.isEmpty()&&cbDomicilio.isSelected()&&txtDom.isEnabled()){
                            datos.actualizarDatosString(tabla,"domicilio",campo,add,user);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE).storeAndViewCaughtException(this,e,modDatosPanel1.class.getName(),methodName,"12");
                    }
                });
            }else{
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                enabledComponents(true,estado);
                txtDom.setEnabled(false);
                txtDom.setText("");
                placeHolders();
            }
        });
        
        cbPuesto.addActionListener(a->{
            methodName="botones.cbPuesto";
            checkboxes=new JCheckBox[]{cbContra,cbNombre,cbAP,cbAM,cbCURP,cbDomicilio,cbExp,cbGE,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbPuesto.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //combobox
                jComboBox1.setEnabled(true);
                //textfields
                enabledComponents(false,estado);
                //función
                updateButton.addActionListener(b->{
                    try{
                        String combo=jComboBox1.getModel().getSelectedItem().toString();
                        user=Integer.parseInt(txtSearch.getText());
                        while(!combo.equals(etiPuesto.getText())&&cbPuesto.isSelected()&&jComboBox1.isEnabled()){
                            datos.actualizarDatosString(tabla,"puesto",campo,combo,user);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE).storeAndViewCaughtException(this,e,modDatosPanel1.class.getName(),methodName,"12");
                    }
                });
            }else{
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //combobox
                jComboBox1.setEnabled(false);
                jComboBox1.getModel().setSelectedItem("Empleado");
                //textfields
                enabledComponents(true,estado);
            }
        });
        
        cbExp.addActionListener(a->{
            methodName="botones.cbExp";
            checkboxes=new JCheckBox[]{cbContra,cbNombre,cbAP,cbAM,cbCURP,cbDomicilio,cbPuesto,cbGE,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbExp.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                txtExp.setEnabled(true);
                //función
                updateButton.addActionListener(b->{
                    try{
                        int exp=Integer.parseInt(txtExp.getText());
                        user=Integer.parseInt(txtSearch.getText());
                        while(exp!=0&&cbExp.isSelected()&&txtExp.isEnabled()){
                            datos.actualizarDatosInteger(tabla,"experiencia",campo,exp,user,true);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE).storeAndViewCaughtException(this,e,modDatosPanel1.class.getName(),methodName,"12");
                    }
                });
            }else{
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                enabledComponents(true,estado);
                txtExp.setEnabled(false);
                txtExp.setText("");
                placeHolders();
            }
        });
        
        cbGE.addActionListener(a->{
            methodName="botones.cbGE";
            checkboxes=new JCheckBox[]{cbContra,cbNombre,cbAP,cbAM,cbCURP,cbDomicilio,cbPuesto,cbExp,cbContacto,cbFN,cbEdad,cbEstado};
            if(cbGE.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                txtGE.setEnabled(true);
                //función
                updateButton.addActionListener(b->{
                    try{
                        String school=txtGE.getText();
                        user=Integer.parseInt(txtSearch.getText());
                        while(!school.isEmpty()&&cbGE.isSelected()&&txtGE.isEnabled()){
                            datos.actualizarDatosString(tabla,"grado_estudios",campo,school,user);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE).storeAndViewCaughtException(this,e,modDatosPanel1.class.getName(),methodName,"12");
                    }
                });
            }else{
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                enabledComponents(true,estado);
                txtGE.setEnabled(false);
                txtGE.setText("");
                placeHolders();
            }
        });
        
        cbContacto.addActionListener(a->{
            methodName="botones.cbContacto";
            checkboxes=new JCheckBox[]{cbContra,cbNombre,cbAP,cbAM,cbCURP,cbDomicilio,cbPuesto,cbExp,cbGE,cbFN,cbEdad,cbEstado};
            if(cbContacto.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                txtContacto.setEnabled(true);
                //función
                updateButton.addActionListener(b->{
                    try{
                        int number=Integer.parseInt(txtContacto.getText());
                        user=Integer.parseInt(txtSearch.getText());
                        while(number!=0&&cbContacto.isSelected()&&txtContacto.isEnabled()){
                            datos.actualizarDatosInteger(tabla,"contacto",campo,number,user,true);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE).storeAndViewCaughtException(this,e,modDatosPanel1.class.getName(),methodName,"12");
                    }
                });
            }else{
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                enabledComponents(true,estado);
                txtContacto.setEnabled(false);
                txtContacto.setText("");
                placeHolders();
            }
        });
        
        cbFN.addActionListener(a->{
            methodName="botones.cbFN";
            checkboxes=new JCheckBox[]{cbContra,cbNombre,cbAP,cbAM,cbCURP,cbDomicilio,cbPuesto,cbExp,cbGE,cbContacto,cbEdad,cbEstado};
            if(cbFN.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                dcFN.setEnabled(true);
                //función
                updateButton.addActionListener(b->{
                    try{
                        long date=dcFN.getDate().getTime();
                        user=Integer.parseInt(txtSearch.getText());
                        while(date!=0&&cbFN.isSelected()&&dcFN.isEnabled()){
                            datos.actualizarDatosDate(tabla,"fecha_nacimiento",campo,new Date(date),user);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE).storeAndViewCaughtException(this,e,modDatosPanel1.class.getName(),methodName,"12");
                    }
                });
                
            }else{
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                enabledComponents(true,estado);
                dcFN.setEnabled(false);
                dcFN.setDate(null);
            }
        });
        
        cbEdad.addActionListener(a->{
            methodName="botones.cbEdad";
            checkboxes=new JCheckBox[]{cbContra,cbNombre,cbAP,cbAM,cbCURP,cbDomicilio,cbPuesto,cbExp,cbGE,cbContacto,cbFN,cbEstado};
            if(cbEdad.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //textfields
                enabledComponents(false,estado);
                txtEdad.setEnabled(true);
                //función
                updateButton.addActionListener(b->{
                    try{
                        int age=Integer.parseInt(txtEdad.getText());
                        user=Integer.parseInt(txtSearch.getText());
                        while(age!=0&&cbEdad.isSelected()&&txtEdad.isEnabled()){
                            datos.actualizarDatosInteger(tabla,"edad",campo,age,user,true);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE).storeAndViewCaughtException(this,e,modDatosPanel1.class.getName(),methodName,"12");
                    }
                });
            }else{
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //textfields
                enabledComponents(true,estado);
                txtEdad.setEnabled(false);
                txtEdad.setText("");
                placeHolders();
            }
        });
        
        cbEstado.addActionListener(a->{
            methodName="botones.cbEstado";
            checkboxes=new JCheckBox[]{cbContra,cbNombre,cbAP,cbAM,cbCURP,cbDomicilio,cbPuesto,cbExp,cbGE,cbContacto,cbFN,cbEdad};
            if(cbEstado.isSelected()){
                for(JCheckBox c:checkboxes){
                    c.setEnabled(false);
                    c.setSelected(false);
                }
                //combo
                jComboBox2.setEnabled(true);
                //textfields
                enabledComponents(false,estado);
                //función
                updateButton.addActionListener(b->{
                    try{
                        String combo=jComboBox2.getModel().getSelectedItem().toString();
                        user=Integer.parseInt(txtSearch.getText());
                        while(!combo.equals(etiEstado.getText())&&cbEstado.isSelected()&&jComboBox2.isEnabled()){
                            datos.actualizarDatosString(tabla,"estado",campo,combo,user);
                            consulta();
                            break;
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE).storeAndViewCaughtException(this,e,modDatosPanel1.class.getName(),methodName,"12");
                    }
                });
            }else{
                for(JCheckBox c:checkboxes){
                    c.setEnabled(true);
                }
                //combo
                jComboBox2.setEnabled(false);
                jComboBox2.getModel().setSelectedItem("Activo");
                //textfields
                enabledComponents(true,estado);
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
                PreparedStatement ps=new Datos().getConnection().prepareStatement("select * from empleados where codigo_emp=?;");
                ps.setInt(1,Integer.parseInt(txtSearch.getText()));
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    etiContra.setText(rs.getString("password"));
                    etiNombre.setText(rs.getString("nombre_emp"));
                    etiAP.setText(rs.getString("apellidop_emp"));
                    etiAM.setText(rs.getString("apellidom_emp"));
                    etiCURP.setText(rs.getString("curp"));
                    etiDom.setText(rs.getString("domicilio"));
                    etiPuesto.setText(rs.getString("puesto"));
                    
                    int exp=rs.getInt("experiencia");
                    if(exp==0){
                        etiExp.setText("Sin experiencia");
                    }else if(exp==1){
                        etiExp.setText(String.valueOf(exp).concat(" año"));
                    }else if(exp>=2){
                        etiExp.setText(String.valueOf(exp).concat(" años"));
                    }
                    
                    etiGE.setText(rs.getString("grado_estudios"));
                    etiContacto.setText(String.valueOf(rs.getInt("contacto")));
                    etiFN.setText(rs.getDate("fecha_nacimiento").toString());
                    etiEdad.setText(String.valueOf(rs.getInt("edad")));
                    etiEstado.setText(rs.getString("estado"));
                }else{
                    new logger(Level.WARNING).storeAndViewError14(this,modDatosPanel1.class.getName(),methodName);
                }
                
                ps.close();
                rs.close();
            }else{
                new logger(Level.WARNING).storeAndViewError18(this,modDatosPanel1.class.getName(),methodName);
            }
        }catch(SQLException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,e,modDatosPanel1.class.getName(),methodName,"14");
        }
    }
    
    protected void enabledComponents(boolean flag1, boolean flag2){
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
        new PlaceHolder(txtNombre,"Nombre(s)").inicialize();
        new PlaceHolder(txtAP,"Apellido paterno").inicialize();
        new PlaceHolder(txtAM,"Apellido materno").inicialize();
        new PlaceHolder(txtCURP,"CURP").inicialize();
        new PlaceHolder(txtDom,"Domicilio").inicialize();
        new PlaceHolder(txtExp,"Experiencia").inicialize();
        new PlaceHolder(txtGE,"Grado de estudios").inicialize();
        new PlaceHolder(txtContacto,"Contacto").inicialize();
        new PlaceHolder(txtEdad,"Edad").inicialize();
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING).storeAndViewNumberInputWarning(this,modDatosPanel1.class.getName(),"txtNombreKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyPressed
    
    private void txtAPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAPKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING).storeAndViewNumberInputWarning(this,modDatosPanel1.class.getName(),"txtAPKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtAPKeyPressed
    
    private void txtAMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAMKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING).storeAndViewNumberInputWarning(this,modDatosPanel1.class.getName(),"txtAMKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtAMKeyPressed
    
    private void txtExpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExpKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new logger(Level.WARNING).storeAndViewLetterInputWarning(this,modDatosPanel1.class.getName(),"txtExpKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtExpKeyPressed
    
    private void txtGEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGEKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING).storeAndViewNumberInputWarning(this,modDatosPanel1.class.getName(),"txtGEKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtGEKeyPressed
    
    private void txtContactoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactoKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new logger(Level.WARNING).storeAndViewLetterInputWarning(this,modDatosPanel1.class.getName(),"txtContactoKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtContactoKeyPressed
    
    private void txtEdadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new logger(Level.WARNING).storeAndViewNumberInputWarning(this,modDatosPanel1.class.getName(),"txtEdadKeyPressed");
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