package paneles;
//clases
import clases.Datos;
import clases.Dirs;
import clases.logger;
import clases.mvc.Controlador;
//librerías
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
//java
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class modPicPanel1 extends javax.swing.JPanel{
    public modPicPanel1(){
        initComponents();
        
        botones();
    }
    
    protected Controlador modelo;
    
    public modPicPanel1(Controlador modelo){
        initComponents();
        
        this.modelo=modelo;
        
        botones();
    }
    
    public modPicPanel1(int code,Controlador modelo){
        initComponents();
        
        this.modelo=modelo;
        
        txtSearch.setText(String.valueOf(code));
        txtSearch.setEnabled(false);
        searchButton.setEnabled(false);
        
        botones();
        consulta2();
    }
    
    protected Properties p;
    protected JFileChooser jfc;
    
    protected int codigo;
    
    protected String direccion;
    protected String nombre;
    protected String methodName;
    
    protected final void botones(){
        closeButton.addActionListener(a->
            setVisible(false)
        );
        
        jComboBox1.addActionListener(a->
            combo()
        );
        
        takePicButton.addActionListener(a->{
            methodName="botones.takePic";
            try{
                Webcam webcam=Webcam.getDefault();
                webcam.setViewSize(WebcamResolution.VGA.getSize());
                
                webcam.open();
                direccion=Dirs.exists(new File("data/media/webcam",nombre+"-"+codigo+".jpg"));
                ImageIO.write(webcam.getImage(),"JPG",new File(direccion));
                picLabel.setText(null);
                picLabel.setIcon(new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(direccion)).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
                webcam.close();
            }catch(IOException e){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"2IO");
            }
        });
        
        searchButton.addActionListener(a->
            consulta1()
        );
        
        updateButton.addActionListener(a->{
            methodName="botones.update";
            try{
                new Datos(modelo).actualizarFotoPerfil("empleados","codigo_emp",new FileInputStream(direccion),Integer.parseInt(txtSearch.getText()));
                consulta1();
            }catch(FileNotFoundException e){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"1IO");
            }catch(SQLException e){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"12");
            }
        });
        
        txtSearch.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    consulta1();
                }
            }
        });
    }
    
    protected void combo(){
        methodName="combo1";
        switch(jComboBox1.getSelectedIndex()){
            case 0:{
                try{
                    p=new Properties();
                    p.load(new FileInputStream("data/config/filechooserd.properties"));
                    jfc=new JFileChooser(p.getProperty("lastdirectory_pic1"));
                    
                    jfc.setMultiSelectionEnabled(false);
                    jfc.setAcceptAllFileFilterUsed(false);
                    jfc.setFileFilter(new FileNameExtensionFilter("Archivos JPG","jpg"));
                    
                    if(JFileChooser.APPROVE_OPTION==jfc.showOpenDialog(null)){
                        File f=jfc.getSelectedFile();
                        direccion=f.getPath();
                        
                        picLabel.setText(null);
                        picLabel.setIcon(new ImageIcon(new ImageIcon(direccion).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
                        
                        p.setProperty("lastdirectory_pic1",f.getParent());
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
                break;
            }
            case 1:{
                picLabel.setIcon(null);
                picLabel.setText("Foto");
                break;
            }
        }
    }
    
    protected void consulta1(){
        methodName="consulta1";
        try{
            if(!txtSearch.getText().isEmpty()){
                PreparedStatement ps=new Datos(modelo).getConnection().prepareStatement("select * from empleados where codigo_emp=?;");
                ps.setInt(1,Integer.parseInt(txtSearch.getText()));
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    codigo=rs.getInt("codigo_emp");
                    nombre=rs.getString("nombre_emp");
                    picLabel.setText(null);
                    picLabel.setIcon(new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(rs.getBytes("foto"))).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
                }else{
                    new logger(Level.WARNING,this.getClass().getName()).storeError14(this,methodName);
                }
                ps.close();
                rs.close();
            }else{
                new logger(Level.WARNING,this.getClass().getName()).storeError18(this,methodName);
            }
        }catch(SQLException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"14");
        }
    }
    
    protected final void consulta2(){
        methodName="consulta2";
        try{
            PreparedStatement ps=new Datos(modelo).getConnection().prepareStatement("select * from empleados where codigo_emp=?;");
            ps.setInt(1,Integer.parseInt(txtSearch.getText()));
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                codigo=rs.getInt("codigo_emp");
                nombre=rs.getString("nombre_emp");
                picLabel.setText(null);
                picLabel.setIcon(new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(rs.getBytes("foto"))).getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT)));
            }else{
                new logger(Level.WARNING, this.getClass().getName()).storeError14(this,methodName);
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            new logger(Level.SEVERE, this.getClass().getName()).catchException(this,e,methodName,"14");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        picLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        takePicButton = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();

        closeButton.setText("Cerrar panel");

        updateButton.setText("Actualizar foto");

        picLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picLabel.setText("Foto");
        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buscar foto", "Limpiar foto" }));

        takePicButton.setText("Tomar foto");

        searchButton.setText("Buscar");

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(takePicButton))))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(takePicButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(updateButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel picLabel;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton takePicButton;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}