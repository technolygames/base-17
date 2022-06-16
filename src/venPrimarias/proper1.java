package venPrimarias;
//clases
import clases.Icono;
import clases.laf;
import clases.logger;
import clases.thread;
import venTerciarias.valVentanas.validacionVentana7;
//java
import java.awt.Image;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
//extension larga
import java.util.logging.Level;
import java.awt.event.ActionEvent;
import java.nio.charset.StandardCharsets;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class proper1 extends javax.swing.JFrame{
    public proper1(){
        initComponents();
        new laf().LookAndFeel(proper1.this,proper1.class.getName(),"proper1");
        
        botones();
        configIn();
        combo();
        settings();
        slider();
        
        setLocationRelativeTo(null);
        setTitle("Configuración");
        setResizable(false);
    }
    
    protected ImageIcon ii[];
    protected File f;
    protected Properties p;
    protected InputStream is;
    protected OutputStream os;
    protected JFileChooser jfc;
    
    protected int i;
    
    protected String icono;
    protected String nombre;
    protected String design;
    protected String imagenes;
    protected String nombreArchivo1;
    protected String nombreArchivo2;
    
    protected void settings(){
        jLabel8.setText("Advertencia: la imagen y el ícono no son lo mismo. Asegúrate que hayas cambiado ambos, en caso de que lo hayas hecho");
    }
    
    protected final void configIn(){
        p=new Properties();
        try{
            p.load(new FileReader(System.getProperty("user.dir")+"/src/data/config/config.properties",StandardCharsets.UTF_8));
            
            imagenes=p.getProperty("imagenes");
            icono=p.getProperty("icono");
            design=p.getProperty("look_and_feel");
            nombre=p.getProperty("nombre");
            
            if(!new File(imagenes).exists()){
                imagenes=p.getProperty("imagen_respaldo");
            }
            
            if(!new File(icono).exists()){
                icono=p.getProperty("icono_respaldo");
            }
            
            imageLoader(imagenes,"Ventanas");
            
            jTextField1.setText(nombre);
            jComboBox1.getModel().setSelectedItem(design);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 32: "+e.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configIn()'",Level.WARNING);
            new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"configIn-32",e.fillInStackTrace());
        }catch(FileNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configIn()'",Level.WARNING);
            new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"configIn-1IO",x.fillInStackTrace());
        }catch(IOException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configIn()'",Level.WARNING);
            new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"configIn-2IO",n.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configIn()'",Level.WARNING);
            new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"configIn-0",k.fillInStackTrace());
        }
    }
    
    protected final void botones(){
        p=new Properties();
        
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        iconButton.addActionListener((ae)->{
            try{
                p=new Properties();
                p.load(new FileInputStream(System.getProperty("user.dir")+"/src/data/config/filechooserd.properties"));
                jfc=new JFileChooser(p.getProperty("lastdirectory_icon"));
                
                File k=jfc.getCurrentDirectory();
                
                jfc.setFileFilter(new FileNameExtensionFilter("Archivo PNG","png"));
                
                int n=jfc.showOpenDialog(null);
                if(JFileChooser.APPROVE_OPTION==n){
                    try{
                        f=jfc.getSelectedFile();
                        icono=f.getPath();
                        nombreArchivo2=f.getName();
                        
                        p.setProperty("lastdirectory_icon",f.getParent());
                        p.store(new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/src/data/config/filechooserd.properties")),"JFileChooserDirection");
                    }catch(IOException e){
                        JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 24",JOptionPane.WARNING_MESSAGE);
                        new logger().staticLogger("Error 24: "+e.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(iconButton)'",Level.WARNING);
                        new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"botones.icon-24",e.fillInStackTrace());
                    }
                }
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 40",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 40: "+e.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(iconButton)'",Level.WARNING);
                new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"botones.icon-40",e.fillInStackTrace());
            }catch(FileNotFoundException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(iconButton)'",Level.WARNING);
                new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"botones.icon-1IO",x.fillInStackTrace());
            }catch(IOException n){
                JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(iconButton)'",Level.WARNING);
                new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"botones.icon-2IO",n.fillInStackTrace());
            }
        });
        
        imgButton.addActionListener((ActionEvent ae)->{
            try{
                p=new Properties();
                p.load(new FileInputStream(System.getProperty("user.dir")+"/src/data/config/filechooserd.properties"));
                jfc=new JFileChooser(p.getProperty("lastdirectory_image"));
                File k=jfc.getCurrentDirectory();
                
                jfc.setFileFilter(new FileNameExtensionFilter("Archivo PNG","png"));
                jfc.setFileFilter(new FileNameExtensionFilter("Archivo JPG","jpg"));
                jfc.setFileFilter(new FileNameExtensionFilter("Archivo JPEG","jpeg"));
                
                int ñ=jfc.showOpenDialog(null);
                if(JFileChooser.APPROVE_OPTION==ñ){
                    try{
                        f=jfc.getSelectedFile();
                        imagenes=f.getPath();
                        nombreArchivo1=f.getName();
                        
                        Image i=ImageIO.read(new FileInputStream(imagenes));
                        ImageIcon im=new ImageIcon(i);
                        Icon l=new ImageIcon(im.getImage().getScaledInstance(jLabel3.getWidth(),jLabel3.getHeight(),Image.SCALE_DEFAULT));
                        jLabel3.setIcon(l);
                        i.flush();
                        
                        p.setProperty("lastdirectory_image",f.getParent());
                        p.store(new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/src/data/config/filechooserd.properties")),"JFil eChooserDirection");
                    }catch(IOException x){
                        JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 24",JOptionPane.WARNING_MESSAGE);
                        new logger().staticLogger("Error 24: "+x.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(imgButton)'",Level.WARNING);
                        new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"botones.img-24",x.fillInStackTrace());
                    }
                }
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 40",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 40: "+e.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(imgButton)'",Level.WARNING);
                new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"botones.img-40",e.fillInStackTrace());
            }catch(FileNotFoundException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(imgButton)'",Level.WARNING);
                new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"botones.img-1IO",x.fillInStackTrace());
            }catch(IOException n){
                JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(imgButton)'",Level.WARNING);
                new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"botones.img-2IO",n.fillInStackTrace());
            }
        });
        
        jComboBox1.addActionListener((ae)->{
            try{
                design=jComboBox1.getSelectedItem().toString();
                UIManager.setLookAndFeel(design);
                SwingUtilities.updateComponentTreeUI(this);
            }catch(ClassNotFoundException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error CNFE: "+e.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(jComboBox2)'",Level.WARNING);
                new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"botones.combo2-CNFE",e.fillInStackTrace());
            }catch(IllegalAccessException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error IAE: "+x.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(jComboBox2)'",Level.WARNING);
                new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"botones.combo2-IAE",x.fillInStackTrace());
            }catch(InstantiationException n){
                JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error IE: "+n.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(jComboBox2)'",Level.WARNING);
                new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"botones.combo2-IE",n.fillInStackTrace());
            }catch(UnsupportedLookAndFeelException k){
                JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 28: "+k.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(jComboBox2)'",Level.WARNING);
                new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"botones.combo2-28",k.fillInStackTrace());
            }
        });
        
        schButton.addActionListener((ae)->{
            configOut();
        });
        
        toolsButton.addActionListener((a)->{
            new validacionVentana7(new javax.swing.JFrame(),true).setVisible(true);
        });
    }
    
    protected final void combo(){
        UIManager.installLookAndFeel("FlatLafDark","com.formdev.flatlaf.FlatDarkLaf");
        UIManager.LookAndFeelInfo[] lafi=UIManager.getInstalledLookAndFeels();
        try{
            for(UIManager.LookAndFeelInfo lafi1:lafi){
                jComboBox1.addItem(lafi1.getClassName());
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 32: "+e.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'combo1()'",Level.WARNING);
            new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"combo1-32",e.fillInStackTrace());
        }catch(IllegalArgumentException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 34",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 34: "+x.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'combo1()'",Level.WARNING);
            new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"combo1-34",x.fillInStackTrace());
        }catch(NullPointerException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 0: "+n.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'combo1()'",Level.WARNING);
            new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"combo1-0",n.fillInStackTrace());
        }
    }
    
    protected final void configOut(){
        p=new Properties();
        f=new File(System.getProperty("user.dir")+"/src/data/config/config.properties");
        String dato1=System.getProperty("user.dir")+"/src/data/media/copy/label/";
        String dato2=System.getProperty("user.dir")+"/src/data/media/copy/icon/";
        try{
            if(f.exists()){
                p.setProperty("imagenes",imagenes);
                
                is=new FileInputStream(imagenes);
                os=new FileOutputStream(dato1+nombreArchivo1);
                
                new thread(is,os).run();
                
                p.setProperty("imagen_respaldo",dato1+nombreArchivo1);
                p.setProperty("look_and_feel",design);
                p.setProperty("icono",icono);
                p.setProperty("icono_respaldo",dato2+nombreArchivo2);
                p.setProperty("nombre",jTextField1.getText());
                
                is=new FileInputStream(icono);
                os=new FileOutputStream(dato2+nombreArchivo2);
                
                new thread(is,os).run();
                
                p.store(new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/src/data/config/config.properties",StandardCharsets.UTF_8)),"config1");
                
                JOptionPane.showMessageDialog(null,"Se guardaron correctamente","Rel 4",JOptionPane.INFORMATION_MESSAGE);
                new logger().staticLogger("Rel 4: se han guardado las condiguraciones.\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configOut()'.\nUsuario que hizo los cambios: "+String.valueOf(start.userID),Level.INFO);
                
                is.close();
                os.close();
                os.flush();
            }else{
                f.createNewFile();
            }
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configOut()'",Level.WARNING);
            new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"configOut-1IO",e.fillInStackTrace());
        }catch(NumberFormatException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 32",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 32: "+x.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configOut()'",Level.WARNING);
            new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"configOut-32",x.fillInStackTrace());
        }catch(NullPointerException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 0: "+n.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configOut()'",Level.WARNING);
            new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"configOut-0",n.fillInStackTrace());
        }catch(IOException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+k.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configOut()'",Level.WARNING);
            new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"configOut-2IO",k.fillInStackTrace());
        }
    }
    
    protected void slider(){
        ii=new ImageIcon[2];
        ii[0]=new ImageIcon();
        ii[1]=new ImageIcon();
        
        rightButton.addActionListener((a)->{
            if(a.getSource()==rightButton){
                if(i!=ii.length-1){
                    i=i+1;
                    imageLoader(imagenes,"Ventanas");
                    rightButton.setEnabled(false);
                    leftButton.setEnabled(true);
                }
            }
        });
        
        leftButton.addActionListener((a)->{
            if(a.getSource()==leftButton){
                if(i!=0){
                    i=i-1;
                    imageLoader(icono,"Icono");
                    rightButton.setEnabled(true);
                    leftButton.setEnabled(false);
                }
            }
        });
    }
    
    protected void imageLoader(String dir,String text){
        try{
            Image i=ImageIO.read(new FileInputStream(dir));
            ImageIcon im=new ImageIcon(i);
            Icon l=new ImageIcon(im.getImage().getScaledInstance(jLabel3.getWidth(),jLabel3.getHeight(),Image.SCALE_DEFAULT));
            jLabel3.setIcon(l);
            jLabel5.setText(null);
            jLabel5.setText(text);
            
            i.flush();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+e.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'imageLoader()'",Level.WARNING);
            new logger().exceptionLogger(proper1.class.getName(),Level.WARNING,"imageLoader-2IO",e.fillInStackTrace());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        imgButton = new javax.swing.JButton();
        schButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        iconButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        toolsButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        leftButton = new javax.swing.JButton();
        rightButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Icono().getIconImage());

        jLabel2.setText("Imagen:");

        imgButton.setText("Seleccionar imagen");

        schButton.setText("Guardar cambios");

        backButton.setText("Regresar");

        jLabel4.setText("Look And Feel:");

        jLabel6.setText("Icono de ventana:");

        iconButton.setText("Seleccionar icono");

        jLabel7.setText("Nombre del programa:");

        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("jLabel8");

        toolsButton.setText("Herramientas");

        jLabel1.setText("Administrador:");

        leftButton.setText("<<");

        rightButton.setText(">>");

        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(schButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(iconButton))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(toolsButton)))
                                .addGap(0, 297, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imgButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(leftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(rightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(iconButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(toolsButton)
                            .addComponent(jLabel1)))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rightButton)
                    .addComponent(leftButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imgButton)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(schButton)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        new proper1().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton backButton;
    protected javax.swing.JButton iconButton;
    protected javax.swing.JButton imgButton;
    protected javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    protected javax.swing.JLabel jLabel2;
    protected javax.swing.JLabel jLabel3;
    protected javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    protected javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton leftButton;
    private javax.swing.JButton rightButton;
    protected javax.swing.JButton schButton;
    private javax.swing.JButton toolsButton;
    // End of variables declaration//GEN-END:variables
}