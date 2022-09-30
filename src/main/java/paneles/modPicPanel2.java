package paneles;
//clases
import clases.datos;
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

public class modPicPanel2 extends javax.swing.JPanel{
    public modPicPanel2(){
        initComponents();
        
        botones();
    }
    
    public modPicPanel2(int code){
        initComponents();
        
        txtSearch.setText(String.valueOf(code));
        
        botones();
        consulta2();
    }
    
    protected Properties p;
    protected JFileChooser jfc;
    
    protected int codigo;
    
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
                direccion="data/media/webcam/"+nombre+"-"+codigo+".jpg";
                ImageIO.write(webcam.getImage(),"JPG",new File(direccion));
                picLabel.setText(null);
                picLabel.setIcon(new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(direccion)).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
                webcam.close();
            }catch(IOException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 2IO: "+e.getMessage()+".\nOcurrió en la clase '"+modPicPanel2.class.getName()+"', en el método 'botones(takePicButton)'");
                new logger(Level.SEVERE).exceptionLogger(modPicPanel2.class.getName(),"botones.takePic-2IO",e.fillInStackTrace());
            }
        });
        
        searchButton.addActionListener((a)->{
            consulta1();
        });
        
        updateButton.addActionListener((a)->{
            try{
                new datos().actualizarFotoPerfil("socios","codigo_part",Integer.parseInt(txtSearch.getText()),new FileInputStream(direccion));
                consulta1();
            }catch(FileNotFoundException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+modPicPanel2.class.getName()+"', en el método 'botones(updateButton)'");
                new logger(Level.SEVERE).exceptionLogger(modPicPanel2.class.getName(),"botones.update-1IO",e.fillInStackTrace());
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
    
    protected void combo1(){
        switch(jComboBox1.getSelectedIndex()){
            case 0:{
                try{
                    p=new Properties();
                    p.load(new FileInputStream("data/config/filechooserd.properties"));
                    jfc=new JFileChooser(p.getProperty("lastdirectory_pic2"));
                    
                    jfc.setFileFilter(new FileNameExtensionFilter("Archivos JPG","jpg"));
                    
                    if(JFileChooser.APPROVE_OPTION==jfc.showOpenDialog(null)){
                        try{
                            File f=jfc.getSelectedFile();
                            direccion=f.getPath();
                            
                            picLabel.setText(null);
                            picLabel.setIcon(new ImageIcon(new ImageIcon(direccion).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
                            
                            p.setProperty("lastdirectory_pic2",f.getParent());
                            p.store(new FileOutputStream("data/config/filechooserd.properties"),"JFileChooserDirection");
                        }catch(IOException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 24",JOptionPane.ERROR_MESSAGE);
                            new logger(Level.SEVERE).staticLogger("Error 24: "+e.getMessage()+".\nOcurrió en la clase '"+modPicPanel2.class.getName()+"', en el método 'combo1()'");
                            new logger(Level.SEVERE).exceptionLogger(modPicPanel2.class.getName(),"combo1-24",e.fillInStackTrace());
                        }
                    }
                }catch(HeadlessException e){
                    JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 40",JOptionPane.ERROR_MESSAGE);
                    new logger(Level.SEVERE).staticLogger("Error 40: "+e.getMessage()+".\nOcurrió en la clase '"+modPicPanel2.class.getName()+"', en el método 'combo1()'");
                    new logger(Level.SEVERE).exceptionLogger(modPicPanel2.class.getName(),"combo1-40",e.fillInStackTrace());
                }catch(FileNotFoundException x){
                    JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
                    new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+modPicPanel2.class.getName()+"', en el método 'combo1()'");
                    new logger(Level.SEVERE).exceptionLogger(modPicPanel2.class.getName(),"combo1-1IO",x.fillInStackTrace());
                }catch(IOException n){
                    JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
                    new logger(Level.SEVERE).staticLogger("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+modPicPanel2.class.getName()+"', en el método 'combo1()'");
                    new logger(Level.SEVERE).exceptionLogger(modPicPanel2.class.getName(),"combo1-2IO",n.fillInStackTrace());
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
        try{
            if(!txtSearch.getText().isEmpty()){
                PreparedStatement ps=new datos().getConnection().prepareStatement("select * from socios where codigo_part=?;");
                ps.setInt(1,Integer.parseInt(txtSearch.getText()));
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    codigo=rs.getInt("codigo_part");
                    nombre=rs.getString("nombre_part");
                    picLabel.setText(null);
                    picLabel.setIcon(new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(rs.getBytes("foto"))).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
                }else{
                    JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                    new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+modPicPanel2.class.getName()+"', en el método 'consulta1()'");
                }
                ps.close();
                rs.close();
            }else{
                JOptionPane.showMessageDialog(null,"Error:\nEscribe la palabra clave que deseas buscar","Error 14",JOptionPane.WARNING_MESSAGE);
                new logger(Level.WARNING).staticLogger("Error 18: no se escribió la palabra clave para hacer la búsqueda.\nOcurrió en la clase '"+modPicPanel2.class.getName()+"', en el método 'consulta1()'");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+modPicPanel2.class.getName()+"', en el método 'consulta1()'");
            new logger(Level.SEVERE).exceptionLogger(modPicPanel2.class.getName(),"consulta1-14",e.fillInStackTrace());
        }
    }
    
    protected void consulta2(){
        try{
            PreparedStatement ps=new datos().getConnection().prepareStatement("select * from socios where codigo_part=?;");
            ps.setInt(1,Integer.parseInt(txtSearch.getText()));
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                codigo=rs.getInt("codigo_part");
                nombre=rs.getString("nombre_part");
                picLabel.setText(null);
                picLabel.setIcon(new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(rs.getBytes("foto"))).getImage().getScaledInstance(229,229,Image.SCALE_DEFAULT)));
            }else{
                JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+modPicPanel2.class.getName()+"', en el método 'consulta1()'");
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+modPicPanel2.class.getName()+"', en el método 'consulta1()'");
            new logger(Level.SEVERE).exceptionLogger(modPicPanel2.class.getName(),"consulta1-14",e.fillInStackTrace());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        picLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        takePicButton = new javax.swing.JButton();
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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton))
                            .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(takePicButton))))
                .addContainerGap(90, Short.MAX_VALUE))
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
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(takePicButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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