package paneles;
//clases
import clases.datos;
import clases.dirs;
import clases.logger;
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
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class modPicPanel3 extends javax.swing.JPanel{
    public modPicPanel3(){
        initComponents();
        
        botones();
    }
    
    protected Properties p;
    protected JFileChooser jfc;
    
    protected int codigo;
    
    protected String userdir=dirs.userdir;
    protected String direccion;
    protected String nombre;
    
    protected final void botones(){
        closeButton.addActionListener((a)->{
            setVisible(false);
        });
        
        jComboBox1.addActionListener((a)->{
            combo1();
        });
        
        takePicButton.addActionListener((a)->{
            try{
                Webcam webcam=Webcam.getDefault();
                webcam.setViewSize(WebcamResolution.VGA.getSize());
                
                webcam.open();
                direccion=userdir+"/data/media/webcam/"+nombre+"-"+codigo+".jpg";
                ImageIO.write(webcam.getImage(),"JPG",new File(direccion));
                picLabel.setText(null);
                picLabel.setIcon(new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(direccion)).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
                webcam.close();
            }catch(IOException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 2IO: "+e.getMessage()+".\nOcurrió en la clase '"+modPicPanel3.class.getName()+"', en el método 'botones(takePicButton)'");
                new logger(Level.SEVERE).exceptionLogger(modPicPanel3.class.getName(),"botones.takePic-2IO",e.fillInStackTrace());
            }
        });
        
        searchButton.addActionListener((a)->{
            datosBuscar();
        });
        
        updateButton.addActionListener((a)->{
            try{
                new datos().actualizarFotoPerfil("proveedor","codigo_prov",Integer.parseInt(txtSearch.getText()),new FileInputStream(direccion));
                datosBuscar();
            }catch(FileNotFoundException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+modPicPanel3.class.getName()+"', en el método 'botones(updateButton)'");
                new logger(Level.SEVERE).exceptionLogger(modPicPanel3.class.getName(),"botones.update-1IO",e.fillInStackTrace());
            }
        });
        
        txtSearch.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    datosBuscar();
                }
            }
        });
    }
    
    protected void combo1(){
        switch(jComboBox1.getSelectedIndex()){
            case 0:{
                try{
                    p=new Properties();
                    p.load(new FileInputStream(userdir+"/data/config/filechooserd.properties"));
                    jfc=new JFileChooser(p.getProperty("lastdirectory_pic3"));
                    
                    jfc.setFileFilter(new FileNameExtensionFilter("Archivos JPG","jpg"));
                    
                    if(JFileChooser.APPROVE_OPTION==jfc.showOpenDialog(null)){
                        try{
                            File f=jfc.getSelectedFile();
                            direccion=f.getPath();
                            
                            picLabel.setText(null);
                            picLabel.setIcon(new ImageIcon(new ImageIcon(direccion).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
                            
                            p.setProperty("lastdirectory_pic3",f.getParent());
                            p.store(new FileOutputStream(userdir+"/data/config/filechooserd.properties"),"JFileChooserDirection");
                        }catch(IOException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 24",JOptionPane.ERROR_MESSAGE);
                            new logger(Level.SEVERE).staticLogger("Error 24: "+e.getMessage()+".\nOcurrió en la clase '"+modPicPanel3.class.getName()+"', en el método 'combo1()'");
                            new logger(Level.SEVERE).exceptionLogger(modPicPanel3.class.getName(),"combo1-24",e.fillInStackTrace());
                        }
                    }
                }catch(HeadlessException e){
                    JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 40",JOptionPane.ERROR_MESSAGE);
                    new logger(Level.SEVERE).staticLogger("Error 40: "+e.getMessage()+".\nOcurrió en la clase '"+modPicPanel3.class.getName()+"', en el método 'combo1()'");
                    new logger(Level.SEVERE).exceptionLogger(modPicPanel3.class.getName(),"combo1-40",e.fillInStackTrace());
                }catch(FileNotFoundException x){
                    JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
                    new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+modPicPanel3.class.getName()+"', en el método 'combo1()'");
                    new logger(Level.SEVERE).exceptionLogger(modPicPanel3.class.getName(),"combo1-1IO",x.fillInStackTrace());
                }catch(IOException n){
                    JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
                    new logger(Level.SEVERE).staticLogger("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+modPicPanel3.class.getName()+"', en el método 'combo1()'");
                    new logger(Level.SEVERE).exceptionLogger(modPicPanel3.class.getName(),"combo1-2IO",n.fillInStackTrace());
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
    
    protected void datosBuscar(){
        try{
            PreparedStatement ps=new datos().getConnection().prepareStatement("select * from proveedor where codigo_prov=?;");
            ps.setInt(1,Integer.parseInt(txtSearch.getText()));
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                codigo=rs.getInt("codigo_prov");
                nombre=rs.getString("nombre_prov");
                picLabel.setIcon(new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(rs.getBytes("foto"))).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+modPicPanel3.class.getName()+"', en el método 'datosMostrar()'");
            new logger(Level.SEVERE).exceptionLogger(modPicPanel3.class.getName(),"datosMostrar-14",e.fillInStackTrace());
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
                            .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(takePicButton))))
                .addContainerGap(88, Short.MAX_VALUE))
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
                        .addComponent(picLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(takePicButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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