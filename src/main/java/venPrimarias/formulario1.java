package venPrimarias;
//clases
import clases.PlaceHolder;
import clases.Datos;
import clases.Dirs;
import clases.Events;
import clases.MediaHandler;
import clases.backuphandler.LectorJson;
import clases.logger;
import clases.mvc.MvcForm1;
import clases.mvc.Controlador;
import menus.menuDatosVentana1;
//java
import java.awt.Image;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.time.format.DateTimeParseException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class formulario1 extends javax.swing.JFrame{
    public formulario1(){
        initComponents();
        new MediaHandler(formulario1.class.getName()).setLookAndFeel(formulario1.this);
        
        botones();
        settings();
        
        setSize(510,565);
        setLocationRelativeTo(null);
        setTitle("Formulario 1");
        setResizable(false);
        pack();
    }
    
    protected Controlador modelo0;
    
    public formulario1(Controlador modelo){
        initComponents();
        new MediaHandler(formulario1.class.getName()).setLookAndFeel(formulario1.this);
        
        this.modelo0=modelo;
        
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
    protected JTextField campos;
    
    protected String direccion;
    protected String methodName;
    
    protected final void settings(){
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        placeHolders();
    }
    
    protected final void botones(){
        for(JTextField tf:new JTextField[]{txtPassword,txtCodigo,txtNombre,txtAP,txtAM,txtCURP,txtDom,txtExp,txtEstudios,txtContacto,txtEdad}){
            campos=tf;
        }
        
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        jMenuItem1.addActionListener(a->
            new menuDatosVentana1(modelo0).setVisible(true)
        );
        
        jMenuItem2.addActionListener(a->
            clearImage()
        );
        
        miClearFields.addActionListener(a->{
            for(JTextField tf:new JTextField[]{txtPassword,txtCodigo,txtNombre,txtAP,txtAM,txtCURP,txtDom,txtExp,txtEstudios,txtContacto,txtEdad}){
                tf.setText("");
            }
            jComboBox1.getModel().setSelectedItem("Empleado");
            jDateChooser1.setDate(null);
            jTextArea1.setText("");
            clearImage();
            placeHolders();
        });
        
        miInsImage.addActionListener(a->{
            methodName="botones.miInsImage";
            try{
                p=new Properties();
                p.load(new FileInputStream("data/config/filechooserd.properties"));
                jfc=new JFileChooser(p.getProperty("lastdirectory_form1"));
                
                jfc.setMultiSelectionEnabled(false);
                jfc.setAcceptAllFileFilterUsed(false);
                jfc.setFileFilter(new FileNameExtensionFilter("Archivos JPG","jpg"));
                
                if(JFileChooser.APPROVE_OPTION==jfc.showOpenDialog(null)){
                    File f=jfc.getSelectedFile();
                    showImage(f.getPath());
                    
                    p.setProperty("lastdirectory_form1",f.getParent());
                    p.store(new FileOutputStream("data/config/filechooserd.properties"),"JFileChooserDirection");
                }
                p.clear();
            }catch(HeadlessException e){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"40");
            }catch(FileNotFoundException x){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,x,methodName,"1IO");
            }catch(IOException n){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,n,methodName,"2IO");
            }
        });
        
        miLoadJson.addActionListener(a->{
            jfc=new JFileChooser("data/databackup/Empleados");
            
            jfc.setMultiSelectionEnabled(false);
            jfc.setAcceptAllFileFilterUsed(false);
            jfc.setFileFilter(new FileNameExtensionFilter("Archivos JSON","json"));
            
            if(JFileChooser.APPROVE_OPTION==jfc.showOpenDialog(this)){
                File f=jfc.getSelectedFile();
                loadFromJson(f.getPath());
            }
        });
        
        storeButton.addActionListener(a->{
            methodName="botones.store";
            try{
                if(!campos.getText().isEmpty()||!jTextArea1.getText().isEmpty()||picLabel.getIcon()!=null){
                    MvcForm1 modelo=new MvcForm1();
                    
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
                    
                    new Datos(modelo0).insertarDatosEmpleado(modelo);
                }else{
                    new logger(Level.WARNING,this.getClass().getName()).storeError18(this,methodName);
                }
            }catch(FileNotFoundException e){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"1IO");
            }catch(NullPointerException x){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,x,methodName,"0");
            }catch(NumberFormatException n){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,n,methodName,"32");
            }catch(ArrayIndexOutOfBoundsException k){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,k,methodName,"AIOOBE");
            }catch(SQLException s){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,s,methodName,"11");
            }
        });
        
        txtEdad.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    try{
                        calcAge(Integer.parseInt(txtEdad.getText()));
                    }catch(DateTimeParseException e){
                        new logger(Level.SEVERE,this.getClass().getName()).catchException(formulario1.this,e,"botones.txtEdad","0");
                    }
                }
            }
        });
    }
    
    protected void loadFromJson(String path){
        var data=new LectorJson().readDataWorkerJson(path);
        
        txtPassword.setText(data.getPassword());
        txtCodigo.setText(String.valueOf(data.getCodigo()));
        txtNombre.setText(data.getNombre());
        txtAP.setText(data.getApellidoPaterno());
        txtAM.setText(data.getApellidoMaterno());
        txtCURP.setText(data.getCurp());
        txtDom.setText(data.getDomicilio());
        jComboBox1.getModel().setSelectedItem(data.getPuesto());
        txtExp.setText(String.valueOf(data.getExperiencia()));
        txtEstudios.setText(data.getGradoEstudios());
        txtContacto.setText(String.valueOf(data.getContacto()));
        jDateChooser1.setDate(Date.valueOf(data.getFechaNacimiento()));
        txtEdad.setText(String.valueOf(Events.calcAge(jDateChooser1.getDate().getTime(),data.getEdad())));
        jComboBox2.getModel().setSelectedItem(data.getEstado());
        jTextArea1.setText(data.getDatosExtra());
        showImage(Dirs.findPic(path,data.getDirImagen()));
    }
    
    protected void calcAge(int edad){
        txtEdad.setText(String.valueOf(Events.calcAge(jDateChooser1.getDate().getTime(),edad)));
    }
    
    protected void clearImage(){
        picLabel.setIcon(null);
        picLabel.setText("Foto");
    }
    
    protected void placeHolders(){
        new PlaceHolder(txtNombre,"Primer y/o segundo nombre").inicialize();
        new PlaceHolder(txtExp,"En años").inicialize();
    }
    
    protected void showImage(String path){
        direccion=path;
        picLabel.setText(null);
        picLabel.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
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
        jMenuItem2 = new javax.swing.JMenuItem();
        miLoadJson = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new MediaHandler(formulario1.class.getName()).getIconImage());

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
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        txtAP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAPKeyPressed(evt);
            }
        });

        txtAM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAMKeyPressed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Programador", "Desarrollador", "Dueño" }));

        txtExp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtExpKeyPressed(evt);
            }
        });

        txtEstudios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEstudiosKeyPressed(evt);
            }
        });

        txtContacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContactoKeyPressed(evt);
            }
        });

        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEdadKeyPressed(evt);
            }
        });

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

        jMenuItem2.setText("Limpiar foto");
        jMenu2.add(jMenuItem2);

        miLoadJson.setText("Cargar JSON");
        jMenu2.add(miLoadJson);

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
            new logger(Level.WARNING,this.getClass().getName()).storeLetterInputWarning(this,"txtCodigoKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyPressed
    
    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt){//GEN-FIRST:event_txtNombreKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeNumberInputWarning(this,"txtNombreKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyPressed
    
    private void txtAPKeyPressed(java.awt.event.KeyEvent evt){//GEN-FIRST:event_txtAPKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeNumberInputWarning(this,"txtAPKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtAPKeyPressed
    
    private void txtAMKeyPressed(java.awt.event.KeyEvent evt){//GEN-FIRST:event_txtAMKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeNumberInputWarning(this,"txtAMKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtAMKeyPressed
    
    private void txtExpKeyPressed(java.awt.event.KeyEvent evt){//GEN-FIRST:event_txtExpKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeLetterInputWarning(this,"txtExpKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtExpKeyPressed
    
    private void txtEstudiosKeyPressed(java.awt.event.KeyEvent evt){//GEN-FIRST:event_txtEstudiosKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeNumberInputWarning(this,"txtEstudiosKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtEstudiosKeyPressed
    
    private void txtContactoKeyPressed(java.awt.event.KeyEvent evt){//GEN-FIRST:event_txtContactoKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeLetterInputWarning(this,"txtContactoKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtContactoKeyPressed
    
    private void txtEdadKeyPressed(java.awt.event.KeyEvent evt){//GEN-FIRST:event_txtEdadKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeLetterInputWarning(this,"txtEdadKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtEdadKeyPressed
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new formulario1().setVisible(true)
        );
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
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuItem miClearFields;
    private javax.swing.JMenuItem miInsImage;
    private javax.swing.JMenuItem miLoadJson;
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