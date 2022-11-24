package venPrimarias;
//clases
import clases.Dirs;
import clases.MediaHandler;
import clases.logger;
import clases.Thread1;
import clases.Validation;
import menus.menuVentanas;
//librerías
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
//java
import java.awt.Frame;
import java.awt.Image;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
//extension larga
import java.util.logging.Level;
import java.nio.charset.StandardCharsets;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class proper1 extends javax.swing.JFrame{
    public proper1(){
        initComponents();
        new MediaHandler(proper1.class.getName()).setLookAndFeel(proper1.this);
        
        botones();
        configIn();
        combo();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Configuración");
        setResizable(false);
        pack();
    }
    
    protected File f1;
    protected Properties p;
    protected InputStream is;
    protected OutputStream os;
    protected JFileChooser jfc;
    protected JTextField campos;
    
    protected int i;
    
    protected String tlf;
    protected String ruc;
    protected String icono;
    protected String nombre;
    protected String design;
    protected String userdir=Dirs.userdir;
    protected String imagenes;
    protected String methodName;
    protected String nombreArchivo1;
    protected String nombreArchivo2;
    
    protected String rol=start.role;
    
    protected final void settings(){
        imageLoader("Ventanas",jTextField2.getText());
        jLabel8.setText("Advertencia: la imagen y el ícono no son lo mismo. Asegúrate que hayas cambiado ambos, en caso de que lo hayas hecho");
        jTextField2.setVisible(false);
        jTextField3.setVisible(false);
        
        JTextField[] tf={jTextField4,jTextField5};
        
        if(new Validation(rol,proper1.class.getName()).isAccessible()){
            for(JTextField textfield:tf){
                textfield.setEnabled(true);
            }
        }else{
            for(JTextField textfield:tf){
                textfield.setEnabled(false);
            }
        }
    }
    
    protected final void configIn(){
        methodName="configIn";
        p=new Properties();
        try{
            p.load(new FileReader("data/config/config.properties",StandardCharsets.UTF_8));
            
            imagenes=p.getProperty("imagenes");
            jTextField2.setText(imagenes);
            if(!new File(imagenes).exists()){
                imagenes=p.getProperty("imagen_respaldo");
                jTextField2.setText(imagenes);
            }
            
            icono=p.getProperty("icono");
            jTextField3.setText(icono);
            if(!new File(icono).exists()){
                icono=p.getProperty("icono_respaldo");
                jTextField3.setText(icono);
            }
            
            design=p.getProperty("look_and_feel");
            jComboBox1.getModel().setSelectedItem(design);
            
            nombre=p.getProperty("nombre");
            jTextField1.setText(nombre);
            
            ruc=p.getProperty("ruc");
            jTextField4.setText(ruc);
            
            tlf=p.getProperty("tlf");
            jTextField5.setText(tlf);
            
            p.clear();
        }catch(NumberFormatException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,e,proper1.class.getName(),methodName,"32");
        }catch(FileNotFoundException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,x,proper1.class.getName(),methodName,"1IO");
        }catch(IOException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,n,proper1.class.getName(),methodName,"2IO");
        }catch(NullPointerException s){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,s,proper1.class.getName(),methodName,"0");
        }
    }
    
    protected final void botones(){
        p=new Properties();
        
        backButton.addActionListener(a->{
            if(!jComboBox1.getSelectedItem().equals(design)||
                    !jTextField2.getText().equals(imagenes)||
                    !jTextField3.getText().equals(icono)||
                    !jTextField1.getText().equals(nombre)||
                    !jTextField4.getText().equals(ruc)||
                    !jTextField5.getText().equals(tlf)){
                int option=JOptionPane.showConfirmDialog(this,"Hay cambios.\n¿Deseas cerrar la ventana?","Notice 1",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(option==0){
                    setVisible(false);
                    dispose();
                    laf(design);
                }
            }else{
                new logger(Level.INFO).staticLogger("No hay cambios");
                setVisible(false);
                dispose();
            }
        });
        
        
        iconButton.addActionListener(a->{
            methodName="botones.icon";
            try{
                p=new Properties();
                p.load(new FileInputStream("data/config/filechooserd.properties"));
                jfc=new JFileChooser(p.getProperty("lastdirectory_icon"));
                
                jfc.setMultiSelectionEnabled(false);
                jfc.setAcceptAllFileFilterUsed(false);
                jfc.setFileFilter(new FileNameExtensionFilter("Archivo PNG","png"));
                
                if(JFileChooser.APPROVE_OPTION==jfc.showOpenDialog(null)){
                    File f3=jfc.getSelectedFile();
                    
                    jTextField3.setText(f3.getPath());
                    imageLoader("Icono",jTextField3.getText());
                    
                    setIconImage(new MediaHandler(proper1.class.getName()).getImage(jTextField3.getText()));
                    
                    p.setProperty("lastdirectory_icon",f3.getParent());
                    p.store(new FileOutputStream("data/config/filechooserd.properties"),"JFileChooserDirection");
                }
                p.clear();
            }catch(HeadlessException e){
                new logger(Level.SEVERE).storeAndViewCaughtException(this,e,proper1.class.getName(),methodName,"40");
            }catch(FileNotFoundException x){
                new logger(Level.SEVERE).storeAndViewCaughtException(this,x,proper1.class.getName(),methodName,"1IO");
            }catch(IOException n){
                new logger(Level.SEVERE).storeAndViewCaughtException(this,n,proper1.class.getName(),methodName,"2IO");
            }
        });
        
        imgButton.addActionListener(a->{
            methodName="botones.img";
            try{
                p=new Properties();
                p.load(new FileInputStream("data/config/filechooserd.properties"));
                jfc=new JFileChooser(p.getProperty("lastdirectory_image"));
                
                jfc.setMultiSelectionEnabled(false);
                jfc.setAcceptAllFileFilterUsed(false);
                for(FileNameExtensionFilter filtro:new FileNameExtensionFilter[]{new FileNameExtensionFilter("Archivo PNG","png"),new FileNameExtensionFilter("Archivo JPG","jpg"),new FileNameExtensionFilter("Archivo JPEG","jpeg")}){
                    jfc.setFileFilter(filtro);
                }
                
                if(JFileChooser.APPROVE_OPTION==jfc.showOpenDialog(null)){
                    File f2=jfc.getSelectedFile();
                    
                    jTextField2.setText(f2.getPath());
                    imageLoader("Ventanas",jTextField2.getText());
                    
                    p.setProperty("lastdirectory_image",f2.getParent());
                    p.store(new FileOutputStream("data/config/filechooserd.properties"),"JFileChooserDirection");
                }
                p.clear();
            }catch(HeadlessException e){
                new logger(Level.SEVERE).storeAndViewCaughtException(this,e,proper1.class.getName(),methodName,"40");
            }catch(FileNotFoundException x){
                new logger(Level.SEVERE).storeAndViewCaughtException(this,x,proper1.class.getName(),methodName,"1IO");
            }catch(IOException n){
                new logger(Level.SEVERE).storeAndViewCaughtException(this,n,proper1.class.getName(),methodName,"2IO");
            }
        });
        
        jComboBox1.addActionListener(a->
            laf(jComboBox1.getSelectedItem().toString())
        );
        
        leftButton.addActionListener(a->{
            if(a.getSource()==leftButton&&i!=0){
                i=i-1;
                imageLoader("Icono",jTextField3.getText());
                rightButton.setEnabled(true);
                leftButton.setEnabled(false);
            }
        });
        
        rightButton.addActionListener(a->{
            if(a.getSource()==rightButton&&i!=-1){
                i=i+1;
                imageLoader("Ventanas",jTextField2.getText());
                rightButton.setEnabled(false);
                leftButton.setEnabled(true);
            }
        });
        
        schButton.addActionListener(a->
            configOut()
        );
        
        toolsButton.addActionListener(a->
            new Validation(new adminTools(),rol,adminTools.class.getName()).toRestrictedForm()
        );
    }
    
    protected void laf(String laf){
        methodName="laf";
        try{
            UIManager.setLookAndFeel(laf);
            for(Frame frame:Frame.getFrames()){
                SwingUtilities.updateComponentTreeUI(frame);
                frame.pack();
            }
        }catch(ClassNotFoundException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,e,proper1.class.getName(),methodName,"CNFE");
        }catch(IllegalAccessException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,x,proper1.class.getName(),methodName,"IAE");
        }catch(InstantiationException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,n,proper1.class.getName(),methodName,"IE");
        }catch(UnsupportedLookAndFeelException s){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,s,proper1.class.getName(),methodName,"28");
        }
    }
    
    static{
        /*
        Aquí van los look and feel para ser instalado
        Here goes look and feel to be install
        */
        UIManager.installLookAndFeel(new FlatDarkLaf().getName(),new FlatDarkLaf().getClass().getName());
        UIManager.installLookAndFeel(new FlatLightLaf().getName(),new FlatLightLaf().getClass().getName());
    }
    
    protected final void combo(){
        methodName="combo";
        try{
            for(UIManager.LookAndFeelInfo lafi:UIManager.getInstalledLookAndFeels()){
                jComboBox1.addItem(lafi.getClassName());
            }
        }catch(NumberFormatException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,e,proper1.class.getName(),methodName,"32");
        }catch(IllegalArgumentException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,x,proper1.class.getName(),methodName,"34");
        }catch(NullPointerException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,n,proper1.class.getName(),methodName,"0");
        }
    }
    
    protected void configOut(){
        methodName="configOut";
        p=new Properties();
        f1=new File("data/config/config.properties");
        String dato1=userdir+"\\data\\media\\forms\\copy\\";
        String dato2=userdir+"\\data\\media\\icon\\copy\\";
        try{
            if(f1.exists()){
                File image=new File(jTextField2.getText());
                String dirImg=image.getPath();
                String nameImg=image.getName();
                p.setProperty("imagenes",dirImg);
                p.setProperty("imagen_respaldo",dato1+nameImg);
                is=new FileInputStream(dirImg);
                os=new FileOutputStream(dato1+nameImg);
                new Thread(new Thread1(is,os)).start();
                
                p.setProperty("look_and_feel",jComboBox1.getSelectedItem().toString());
                
                File icon=new File(jTextField3.getText());
                String dirIcon=icon.getPath();
                String nameIcon=icon.getName();
                p.setProperty("icono",dirIcon);
                p.setProperty("icono_respaldo",dato2+nameIcon);
                is=new FileInputStream(dirIcon);
                os=new FileOutputStream(dato2+nameIcon);
                new Thread(new Thread1(is,os)).start();
                
                menuVentanas.picLabel.setIcon(new ImageIcon(new ImageIcon(new MediaHandler(proper1.class.getName()).getImage(jTextField2.getText())).getImage().getScaledInstance(menuVentanas.picLabel.getWidth(),menuVentanas.picLabel.getHeight(),Image.SCALE_DEFAULT)));
                
                for(Frame frames:Frame.getFrames()){
                    frames.setIconImage(new MediaHandler(proper1.class.getName()).getImage(jTextField3.getText()));
                }
                
                p.setProperty("nombre",jTextField1.getText());
                p.setProperty("ruc",jTextField4.getText());
                p.setProperty("tlf",jTextField5.getText());
                
                p.store(new FileWriter("data/config/config.properties",StandardCharsets.UTF_8),"config1");
                
                JOptionPane.showMessageDialog(this,"Se guardaron correctamente","Rel 4",JOptionPane.INFORMATION_MESSAGE);
                new logger(Level.INFO).staticLogger("Rel 4: se han guardado las condiguraciones.\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configOut()'.\nUsuario que hizo los cambios: "+String.valueOf(start.userID));
                
                is.close();
                os.flush();
                os.close();
            }else{
                f1.createNewFile();
            }
            p.clear();
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,e,proper1.class.getName(),methodName,"1IO");
        }catch(NumberFormatException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,x,proper1.class.getName(),methodName,"32");
        }catch(NullPointerException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,n,proper1.class.getName(),methodName,"0");
        }catch(IOException s){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,s,proper1.class.getName(),methodName,"2IO");
        }
    }
    
    protected void imageLoader(String label,String image){
        jLabel3.setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(jLabel3.getWidth(),jLabel3.getHeight(),Image.SCALE_DEFAULT)));
        jLabel5.setText(null);
        jLabel5.setText(label);
    }
    
    protected void isEmpty(){
        for(JTextField tf:new JTextField[]{jTextField1,jTextField4,jTextField5}){
            campos=tf;
        }
        
        if(!campos.getText().isEmpty()){
            schButton.setEnabled(true);
        }else{
            schButton.setEnabled(false);
            schButton.setToolTipText("No deben haber campos vacíos");
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
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new MediaHandler(proper1.class.getName()).getIconImage());

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

        jLabel9.setText("R.U.C.:");

        jLabel10.setText("T.I.F.:");

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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imgButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(leftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(rightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(toolsButton)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(iconButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rightButton)
                            .addComponent(leftButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(imgButton)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(iconButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(toolsButton)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
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
        EventQueue.invokeLater(()->
            new proper1().setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton iconButton;
    private javax.swing.JButton imgButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JButton leftButton;
    private javax.swing.JButton rightButton;
    private javax.swing.JButton schButton;
    private javax.swing.JButton toolsButton;
    // End of variables declaration//GEN-END:variables
}