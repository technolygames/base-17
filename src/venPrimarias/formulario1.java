package venPrimarias;
//clases
import clases.datos;
import clases.logger;
import menus.menuDatosVentana1;
//genéricos
import com.placeholder.PlaceHolder;
import java.awt.HeadlessException;
//java
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Properties;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
//con extensión larga
import java.util.logging.Level;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class formulario1 extends javax.swing.JFrame{
    public formulario1(){
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error CNFE: "+e.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'formulario1()'",Level.WARNING);
            new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"formulario1-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IE: "+x.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'formulario1()'",Level.WARNING);
            new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"formulario1-IE",x.fillInStackTrace());
        }catch(IllegalAccessException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IAE: "+n.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'formulario1()'",Level.WARNING);
            new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"formulario1-IAE",n.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 28: "+y.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'formulario1()'",Level.WARNING);
            new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"formulario1-28",y.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'formulario1()'",Level.WARNING);
            new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"formulario1-0",k.fillInStackTrace());
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+s.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'formulario1()'",Level.WARNING);
            new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"formulario1-1IO",s.fillInStackTrace());
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+d.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'formulario1()'",Level.WARNING);
            new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"formulario1-2IO",d.fillInStackTrace());
        }
        
        botones();
        settings();
        
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Formulario 1");
    }
    
    protected datos cn;
    
    protected File f;
    protected Image retValue;
    protected Properties p;
    protected JFileChooser jfc;
    
    protected String direccion;
    
    @Override
    public Image getIconImage(){
        p=new Properties();
        try{
            p.load(new FileInputStream("src/data/config/config.properties"));
            retValue=Toolkit.getDefaultToolkit().getImage(p.getProperty("icono"));
            retValue.flush();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"getIconImage-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"getIconImage-2IO",x.fillInStackTrace());
        }
        return retValue;
    }
    
    protected void settings(){
        picLabel.setVisible(true);
        insimgButton.setVisible(true);
        
        new PlaceHolder(txtNombre,"Primer y/o segundo nombre").inicializar();
        txtNombre.setToolTipText("Primer y/o segundo nombre");
    }
    
    protected final void botones(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        cleanButton.addActionListener((ae)->{
            txtContraseña.setText("");
            txtCodigo.setText("");
            txtNombre.setText("");
            txtAP.setText("");
            txtAM.setText("");
            txtExp.setText("");
            txtEstudios.setText("");
            txtEdad.setText("");
            picLabel.setIcon(null);
            new PlaceHolder(txtNombre,"Primer y/o segundo nombre").inicializar();
        });
        
        insimgButton.addActionListener((ae)->{
            try{
                p=new Properties();
                p.load(new FileInputStream("src/data/config/filechooserd.properties"));
                jfc=new JFileChooser(p.getProperty("lastdirectory_form1"));
                
                File f=jfc.getCurrentDirectory();
                
                jfc.setFileFilter(new FileNameExtensionFilter("Archivos JPG","jpg"));
                jfc.setFileFilter(new FileNameExtensionFilter("Archivos JPEG","jpeg"));
                jfc.setFileFilter(new FileNameExtensionFilter("Archivos PNG","png"));
                
                jfc.setCurrentDirectory(f);
                
                int n=jfc.showOpenDialog(null);
                if(JFileChooser.APPROVE_OPTION==n){
                    try{
                        f=jfc.getSelectedFile();
                        direccion=f.getPath();
                        
                        ImageIcon ii=new ImageIcon(f.toString());
                        Icon i=new ImageIcon(ii.getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT));
                        picLabel.setText(null);
                        picLabel.setIcon(i);
                        
                        p.setProperty("lastdirectory_form1",f.getParent());
                        p.store(new BufferedWriter(new FileWriter("src/data/config/filechooserd.properties")),"JFileChooserDirection");
                    }catch(IOException e){
                        JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 24",JOptionPane.WARNING_MESSAGE);
                        new logger().logStaticSaver("Error 24: "+e.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(insimgButton)'",Level.WARNING);
                        new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"botones.insimg-24",e.fillInStackTrace());
                    }
                }
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 40",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 40: "+e.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(insimgButton)'",Level.WARNING);
                new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"botones.insimg-40",e.fillInStackTrace());
            }catch(FileNotFoundException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(insimgButton)'",Level.WARNING);
                new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"botones.insimg-1IO",x.fillInStackTrace());
            }catch(IOException n){
                JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(insimgButton)'",Level.WARNING);
                new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"botones.insimg-2IO",n.fillInStackTrace());
            }
        });
        
        jMenuItem1.addActionListener((ae)->{
            new menuDatosVentana1().setVisible(true);
        });
        
        storeButton.addActionListener((ae)->{
            try{
                if(txtContraseña.getPassword().equals("")&&txtCodigo.getText().equals("")&&txtNombre.getText().equals("")&&txtAP.getText().equals("")&&txtAM.getText().equals("")&&txtExp.getText().equals("")&&txtEstudios.getText().equals("")&&txtEdad.getText().equals("")&&jTextArea1.getText().equals("")){
                    String password=String.valueOf(txtContraseña.getPassword());
                    int codigo_emp=Integer.parseInt(txtCodigo.getText());
                    String nombre_emp=txtNombre.getText();
                    String apellidop_emp=txtAP.getText();
                    String apellidom_emp=txtAM.getText();
                    String puesto=jComboBox1.getSelectedItem().toString();
                    String experiencia=txtExp.getText();
                    String grado_estudios=txtEstudios.getText();
                    int edad=Integer.parseInt(txtEdad.getText());
                    String datos_extra=jTextArea1.getText();
                    InputStream is=new FileInputStream(direccion);
                    
                    new datos().insertarDatosEmpleado(password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,puesto,experiencia,grado_estudios,edad,datos_extra,is);
                }else{
                    JOptionPane.showMessageDialog(null,"Prueba","Prueba",JOptionPane.WARNING_MESSAGE);
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 18",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 18: "+e.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(storeButton)'",Level.WARNING);
                new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"botones.store-18",e.fillInStackTrace());
            }catch(ArrayIndexOutOfBoundsException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error AIOOBE",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error AIOOBE: "+x.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(storeButton)'",Level.WARNING);
                new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"botones.store-AIOOBE",x.fillInStackTrace());
            }catch(NullPointerException ñ){
                JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 0: "+ñ.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(storeButton)'",Level.WARNING);
                new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"botones.store-0",ñ.fillInStackTrace());
            }catch(FileNotFoundException k){
                JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 1IO: "+k.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(storeButton)'",Level.WARNING);
                new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"botones.store-1IO",k.fillInStackTrace());
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelWebCam1 = new JPanelWebCam.JPanelWebCam();
        backButton = new javax.swing.JButton();
        storeButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtAP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtAM = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtExp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEstudios = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtEstatus = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        picLabel = new javax.swing.JLabel();
        insimgButton = new javax.swing.JButton();
        cleanButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jPanelWebCam1Layout = new javax.swing.GroupLayout(jPanelWebCam1);
        jPanelWebCam1.setLayout(jPanelWebCam1Layout);
        jPanelWebCam1Layout.setHorizontalGroup(
            jPanelWebCam1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanelWebCam1Layout.setVerticalGroup(
            jPanelWebCam1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());

        backButton.setText("Regresar");

        storeButton.setText("Guardar datos");

        jLabel2.setText("Nombre(s):");

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

        jLabel4.setText("Apellido paterno:");

        jLabel5.setText("Apellido materno:");

        txtAM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAMKeyPressed(evt);
            }
        });

        jLabel6.setText("Puesto:");

        jLabel7.setText("Experiencia:");

        txtExp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtExpKeyPressed(evt);
            }
        });

        jLabel8.setText("Grado de estudios:");

        txtEstudios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEstudiosKeyPressed(evt);
            }
        });

        jLabel1.setText("Código:");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });

        jLabel3.setText("Edad:");

        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEdadKeyPressed(evt);
            }
        });

        picLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picLabel.setText("Foto");
        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        insimgButton.setText("Insertar imagen");

        cleanButton.setText("Limpiar campos");

        jLabel9.setText("Contraseña:");

        jLabel10.setText("Datos extra:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Programador", "Desarrollador", "Dueño" }));

        jButton1.setText("Webcam");

        jLabel11.setText("Contacto:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jMenu1.setText("Datos");

        jMenuItem1.setText("Menú de Datos");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtEdad, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEstudios, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtExp, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, 140, Short.MAX_VALUE)
                                .addComponent(txtAM, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtAP, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtContraseña, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(picLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cleanButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(insimgButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtEstatus)
                                .addGap(129, 129, 129))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(storeButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backButton)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEstudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(storeButton)
                            .addComponent(backButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtEstatus))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(insimgButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cleanButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 1",JOptionPane.WARNING_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyPressed
    
    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 2",JOptionPane.WARNING_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyPressed
    
    private void txtAPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAPKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 2",JOptionPane.WARNING_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_txtAPKeyPressed
    
    private void txtAMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAMKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 2",JOptionPane.WARNING_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_txtAMKeyPressed
        
    private void txtExpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExpKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 2",JOptionPane.WARNING_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_txtExpKeyPressed
    
    private void txtEstudiosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstudiosKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 2",JOptionPane.WARNING_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_txtEstudiosKeyPressed
    
    private void txtEdadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 1",JOptionPane.WARNING_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_txtEdadKeyPressed
    
    public static void main(String[] args){
        new formulario1().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton cleanButton;
    private javax.swing.JButton insimgButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private JPanelWebCam.JPanelWebCam jPanelWebCam1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    public static javax.swing.JLabel picLabel;
    private javax.swing.JButton storeButton;
    private javax.swing.JTextField txtAM;
    private javax.swing.JTextField txtAP;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JLabel txtEstatus;
    private javax.swing.JTextField txtEstudios;
    private javax.swing.JTextField txtExp;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}