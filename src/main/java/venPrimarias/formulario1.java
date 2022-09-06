package venPrimarias;
//clases
import clases.placeHolder;
import clases.datos;
import clases.guiMediaHandler;
import clases.logger;
import clases.mvc.mvcForm1;
import menus.menuDatosVentana1;
//java
import java.awt.Image;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.Period;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class formulario1 extends javax.swing.JFrame{
    public formulario1(){
        initComponents();
        new guiMediaHandler(formulario1.class.getName()).LookAndFeel(formulario1.this);
        
        botones();
        settings();
        
        setSize(510,565);
        setLocationRelativeTo(null);
        setTitle("Formulario 1");
        setResizable(false);
        pack();
    }
    
    protected Properties p;
    protected JFileChooser jfc;
    
    protected String direccion;
    
    protected void settings(){
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        placeHolders();
    }
    
    protected final void botones(){
        backButton.addActionListener((a)->{
            setVisible(false);
            dispose();
        });
        
        jMenuItem1.addActionListener((a)->{
            new menuDatosVentana1().setVisible(true);
        });
        
        jMenuItem3.addActionListener((a)->{
            picLabel.setIcon(null);
            picLabel.setText("Foto");
        });
        
        miClearFields.addActionListener((a)->{
            txtPassword.setText("");
            txtCodigo.setText("");
            txtNombre.setText("");
            txtAP.setText("");
            txtAM.setText("");
            txtDom.setText("");
            txtExp.setText("");
            txtEstudios.setText("");
            txtContacto.setText("");
            txtEdad.setText("");
            picLabel.setIcon(null);
            picLabel.setText("Foto");
            jTextArea1.setText("");
            placeHolders();
        });
        
        miInsImage.addActionListener((a)->{
            try{
                p=new Properties();
                p.load(new FileInputStream("data/config/filechooserd.properties"));
                jfc=new JFileChooser(p.getProperty("lastdirectory_form1"));
                
                jfc.setFileFilter(new FileNameExtensionFilter("Archivos JPG","jpg"));
                
                if(JFileChooser.APPROVE_OPTION==jfc.showOpenDialog(null)){
                    try{
                        File f=jfc.getSelectedFile();
                        direccion=f.getPath();
                        
                        picLabel.setText(null);
                        picLabel.setIcon(new ImageIcon(new ImageIcon(direccion).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
                        
                        p.setProperty("lastdirectory_form1",f.getParent());
                        p.store(new FileOutputStream("data/config/filechooserd.properties"),"JFileChooserDirection");
                    }catch(IOException e){
                        JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 24",JOptionPane.ERROR_MESSAGE);
                        new logger(Level.SEVERE).staticLogger("Error 24: "+e.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(miInsImage)'");
                        new logger(Level.SEVERE).exceptionLogger(formulario1.class.getName(),"botones.miInsImage-24",e.fillInStackTrace());
                    }
                }
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 40",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 40: "+e.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(miInsImage)'");
                new logger(Level.SEVERE).exceptionLogger(formulario1.class.getName(),"botones.miInsImage-40",e.fillInStackTrace());
            }catch(FileNotFoundException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(miInsImage)'");
                new logger(Level.SEVERE).exceptionLogger(formulario1.class.getName(),"botones.miInsImage-1IO",x.fillInStackTrace());
            }catch(IOException n){
                JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(miInsImage)'");
                new logger(Level.SEVERE).exceptionLogger(formulario1.class.getName(),"botones.miInsImage-2IO",n.fillInStackTrace());
            }
        });
        
        storeButton.addActionListener((a)->{
            try{
                if(!txtPassword.getPassword().equals("")||!txtCodigo.getText().equals("")||!txtNombre.getText().equals("")||!txtAP.getText().equals("")||!txtAM.getText().equals("")||!txtDom.getText().equals("")||!txtExp.getText().equals("")||!txtEstudios.getText().equals("")||!txtContacto.getText().equals("")||!txtEdad.getText().equals("")||!jTextArea1.getText().equals("")){
                    List<mvcForm1> datos=new ArrayList<>();
                    mvcForm1 modelo=new mvcForm1();
                    
                    modelo.setPassword(String.valueOf(txtPassword.getPassword()));
                    modelo.setCodigo(Integer.parseInt(txtCodigo.getText()));
                    modelo.setNombre(txtNombre.getText());
                    modelo.setApellidoPaterno(txtAP.getText());
                    modelo.setApellidoMaterno(txtAM.getText());
                    modelo.setCurp(txtCURP.getText());
                    modelo.setDomicilio(txtDom.getText());
                    modelo.setPuesto(jComboBox1.getSelectedItem().toString());
                    modelo.setExperiencia(Integer.parseInt(txtExp.getText()));
                    modelo.setGradoEstudios(txtEstudios.getText());
                    modelo.setContacto(Integer.parseInt(txtContacto.getText()));
                    modelo.setFechaNacimiento(new Date(jDateChooser1.getDate().getTime()).toString());
                    modelo.setEdad(Integer.parseInt(txtEdad.getText()));
                    modelo.setEstado(jComboBox2.getSelectedItem().toString());
                    modelo.setDatosExtra(jTextArea1.getText());
                    modelo.setImagen(new FileInputStream(direccion));
                    
                    datos.add(modelo);
                    
                    new datos().insertarDatosEmpleado(datos);
                }else{
                    JOptionPane.showMessageDialog(null,"Error:\nIngrese los datos que se solicitan","Error 18",JOptionPane.WARNING_MESSAGE);
                    new logger(Level.WARNING).staticLogger("Error 18: no se escribieron o faltan datos en los campos.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(storeButton)'");
                }
            }catch(FileNotFoundException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(storeButton)'");
                new logger(Level.SEVERE).exceptionLogger(formulario1.class.getName(),"botones.store-1IO",e.fillInStackTrace());
            }catch(NullPointerException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(storeButton)'");
                new logger(Level.SEVERE).exceptionLogger(formulario1.class.getName(),"botones.store-0",x.fillInStackTrace());
            }catch(NumberFormatException n){
                JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 32",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 32: "+n.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(storeButton)'");
                new logger(Level.SEVERE).exceptionLogger(formulario1.class.getName(),"botones.store-32",n.fillInStackTrace());
            }catch(ArrayIndexOutOfBoundsException k){
                JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error AIOOBE",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error AIOOBE: "+k.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(storeButton)'");
                new logger(Level.SEVERE).exceptionLogger(formulario1.class.getName(),"botones.store-AIOOBE",k.fillInStackTrace());
            }
        });
        
        txtEdad.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    try{
                        txtEdad.setText(String.valueOf(Period.between(LocalDate.parse(new Date(jDateChooser1.getDate().getTime()).toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd")),LocalDate.now()).getYears()));
                    }catch(DateTimeParseException e){
                        JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
                        new logger(Level.SEVERE).staticLogger("Error 0: "+e.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtFNKeyPressed()'");
                        new logger(Level.SEVERE).exceptionLogger(formulario1.class.getName(),"txtFNKeyPressed-0",e.fillInStackTrace());
                    }
                }
            }
        });
    }
    
    protected void placeHolders(){
        new placeHolder(txtNombre,"Primer y/o segundo nombre").inicialize();
        new placeHolder(txtExp,"En años").inicialize();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        txtNombre = new javax.swing.JTextField();
        txtAP = new javax.swing.JTextField();
        txtAM = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtExp = new javax.swing.JTextField();
        txtEstudios = new javax.swing.JTextField();
        txtContacto = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        picLabel = new javax.swing.JLabel();
        storeButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        txtDom = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCURP = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        miInsImage = new javax.swing.JMenuItem();
        miClearFields = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new guiMediaHandler(formulario1.class.getName()).getIconImage());

        jLabel1.setText("Contraseña:");

        jLabel2.setText("Código:");

        jLabel3.setText("Nombre(s):");

        jLabel4.setText("Apellido paterno:");

        jLabel5.setText("Apellido materno:");

        jLabel7.setText("Experiencia (años):");

        jLabel8.setText("Grado de estudios:");

        jLabel9.setText("Contacto:");

        jLabel11.setText("Datos extra:");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        txtAP.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAPKeyPressed(evt);
            }
        });

        txtAM.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAMKeyPressed(evt);
            }
        });

        txtExp.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtExpKeyPressed(evt);
            }
        });

        txtEstudios.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEstudiosKeyPressed(evt);
            }
        });

        txtContacto.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContactoKeyPressed(evt);
            }
        });

        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEdadKeyPressed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Programador", "Desarrollador", "Dueño" }));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        picLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picLabel.setText("Foto");
        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        storeButton.setText("Guardar datos");

        backButton.setText("Regresar");

        jLabel13.setText("Puesto:");

        jLabel14.setText("Edad:");

        jLabel6.setText("Estado:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));

        jLabel12.setText("Domicilio:");

        jLabel10.setText("CURP:");

        jLabel15.setText("F. nacimiento:");

        jMenu1.setText("Datos");

        jMenuItem1.setText("Menú de datos");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Opciones");

        miInsImage.setText("Insertar imagen");
        jMenu2.add(miInsImage);

        miClearFields.setText("Limpiar campos");
        jMenu2.add(miClearFields);

        jMenuItem3.setText("Limpiar foto");
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                        .addComponent(txtAP, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                        .addComponent(txtAM, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                        .addComponent(txtCURP, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                        .addComponent(txtDom, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                        .addComponent(jComboBox1, 0, 140, Short.MAX_VALUE)
                                        .addComponent(txtExp, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                        .addComponent(txtEstudios, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                        .addComponent(txtContacto, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                        .addComponent(txtEdad, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                        .addComponent(jComboBox2, 0, 140, Short.MAX_VALUE)
                                        .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(storeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtCURP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtDom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtEstudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(storeButton)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt){//GEN-FIRST:event_txtCodigoKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtCodigoKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyPressed
    
    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt){//GEN-FIRST:event_txtNombreKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtNombreKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyPressed
    
    private void txtAPKeyPressed(java.awt.event.KeyEvent evt){//GEN-FIRST:event_txtAPKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtAPKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtAPKeyPressed
    
    private void txtAMKeyPressed(java.awt.event.KeyEvent evt){//GEN-FIRST:event_txtAMKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtAMKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtAMKeyPressed
    
    private void txtExpKeyPressed(java.awt.event.KeyEvent evt){//GEN-FIRST:event_txtExpKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtExpKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtExpKeyPressed
    
    private void txtEstudiosKeyPressed(java.awt.event.KeyEvent evt){//GEN-FIRST:event_txtEstudiosKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtEstudiosKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtEstudiosKeyPressed
    
    private void txtContactoKeyPressed(java.awt.event.KeyEvent evt){//GEN-FIRST:event_txtContactoKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtContactoKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtContactoKeyPressed
    
    private void txtEdadKeyPressed(java.awt.event.KeyEvent evt){//GEN-FIRST:event_txtEdadKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtEdadKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtEdadKeyPressed
    
    public static void main(String args[]){
        new formulario1().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuItem miClearFields;
    private javax.swing.JMenuItem miInsImage;
    private javax.swing.JLabel picLabel;
    private javax.swing.JButton storeButton;
    private javax.swing.JTextField txtAM;
    private javax.swing.JTextField txtAP;
    private javax.swing.JTextField txtCURP;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtContacto;
    private javax.swing.JTextField txtDom;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEstudios;
    private javax.swing.JTextField txtExp;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}