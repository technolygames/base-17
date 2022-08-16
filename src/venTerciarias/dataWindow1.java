package venTerciarias;

import clases.datos;
import clases.logger;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class dataWindow1 extends javax.swing.JDialog{
    public dataWindow1(java.awt.Frame parent,boolean modal){
        super(parent,modal);
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error CNFE: "+e.getMessage()+".\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'dataWindow()'",Level.WARNING);
            new logger().exceptionLogger(dataWindow1.class.getName(),Level.WARNING,"dataWindow-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IE: "+x.getMessage()+".\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'dataWindow()'",Level.WARNING);
            new logger().exceptionLogger(dataWindow1.class.getName(),Level.WARNING,"dataWindow-IE",x.fillInStackTrace());
        }catch(IllegalAccessException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IAE: "+n.getMessage()+".\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'dataWindow()'",Level.WARNING);
            new logger().exceptionLogger(dataWindow1.class.getName(),Level.WARNING,"dataWindow-IAE",n.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 28: "+y.getMessage()+".\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'dataWindow()'",Level.WARNING);
            new logger().exceptionLogger(dataWindow1.class.getName(),Level.WARNING,"dataWindow-28",y.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'dataWindow()'",Level.WARNING);
            new logger().exceptionLogger(dataWindow1.class.getName(),Level.WARNING,"dataWindow-0",k.fillInStackTrace());
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+s.getMessage()+".\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'dataWindow()'",Level.WARNING);
            new logger().exceptionLogger(dataWindow1.class.getName(),Level.WARNING,"dataWindow-1IO",s.fillInStackTrace());
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+d.getMessage()+".\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'dataWindow()'",Level.WARNING);
            new logger().exceptionLogger(dataWindow1.class.getName(),Level.WARNING,"dataWindow-2IO",d.fillInStackTrace());
        }
        
        boton();
        datosMostrar();
        
        setLocationRelativeTo(null);
        setTitle("Datos del empleado");
        
        idLabel.setVisible(true);
    }
    
    protected datos d;
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    protected Image retValue;
    protected Properties p;
    
    public Image getIconImage(){
        p=new Properties();
        try{
            p.load(new FileInputStream("src/data/config/config.properties"));
            retValue=Toolkit.getDefaultToolkit().getImage(p.getProperty("icono"));
            retValue.flush();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(dataWindow1.class.getName(),Level.WARNING,"getIconImage-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(dataWindow1.class.getName(),Level.WARNING,"getIconImage-2IO",x.fillInStackTrace());
        }
        return retValue;
    }
    
    protected final void datosMostrar(){
        d=new datos();
        String id=idLabel.getText();
        try{
            ps=d.getConnection().prepareStatement("select * from empleados where codigo_emp=12347;");
            rs=ps.executeQuery();
            while(rs.next()){
                etiContra.setText(rs.getString("password"));
                etiCodigo.setText(String.valueOf(rs.getInt("codigo_emp")));
                etiNombre.setText(rs.getString("nombre_emp"));
                etiApellidoP.setText(rs.getString("apellidop_emp"));
                etiApellidoM.setText(rs.getString("apellidom_emp"));
                etiPuesto.setText(rs.getString("puesto"));
                etiExp.setText(rs.getString("experiencia"));
                etiEstudios.setText(rs.getString("grado_estudios"));
                etiContacto.setText(rs.getString("contacto"));
                etiEdad.setText(String.valueOf(rs.getInt("edad")));
                etiEstado.setText(rs.getString("estado"));
                etiIngreso.setText(String.valueOf(rs.getDate("fecha_registro")));
                etiSesion.setText(String.valueOf(rs.getDate("fecha_sesion")));
                
                byte[] imagen=rs.getBytes("foto");
                
                Image i=Toolkit.getDefaultToolkit().createImage(imagen);
                ImageIcon im=new ImageIcon(i);
                Icon l=new ImageIcon(im.getImage().getScaledInstance(etiFoto.getWidth(),etiFoto.getHeight(),Image.SCALE_DEFAULT));
                etiFoto.setIcon(l);
                i.flush();
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 10",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 10: "+e.getMessage()+".\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'datosMostrar()'",Level.WARNING);
            new logger().exceptionLogger(dataWindow1.class.getName(),Level.WARNING,"datosMostrar-10",e.fillInStackTrace());
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'datosMostrar()'",Level.WARNING);
            new logger().exceptionLogger(dataWindow1.class.getName(),Level.WARNING,"datosMostrar-0",x.fillInStackTrace());
        }/*catch(IOException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+ñ.getMessage()+".\nOcurrió en la clase '"+dataWindow.class.getName()+"', en el método 'datosMostrar()'",Level.WARNING);
            new logger().exceptionLogger(dataWindow.class.getName(),Level.WARNING,"datosMostrar-2IO",ñ.fillInStackTrace());/*catch(IOException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+ñ.getMessage()+".\nOcurrió en la clase '"+dataWindow.class.getName()+"', en el método 'datosMostrar()'",Level.WARNING);
            new logger().exceptionLogger(dataWindow.class.getName(),Level.WARNING,"datosMostrar-2IO",ñ.fillInStackTrace());
        }*/
    }
    
    protected final void boton(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        storeImgButton.addActionListener((a)->{
            try{
                ps=new datos().getConnection().prepareStatement("select foto from empleados where codigo_emp='"+etiCodigo.getText()+"';");
                File f=new File("src/data/media/dataImage/"+Math.random()+".jpg");
                FileOutputStream fos=new FileOutputStream(f);
                byte[] bytes;
                Blob blob;
                rs=ps.executeQuery();
                while(rs.next()){
                    blob=rs.getBlob("foto");
                    bytes=blob.getBytes(1,(int)blob.length());
                    fos.write(bytes);
                }
                ps.close();
                fos.close();
                fos.flush();
            }catch(SQLException e){
                e.fillInStackTrace();
            }catch(FileNotFoundException x){
                x.fillInStackTrace();
            }catch(IOException n){
                n.fillInStackTrace();
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        etiFoto = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        etiCodigo = new javax.swing.JLabel();
        etiNombre = new javax.swing.JLabel();
        etiApellidoP = new javax.swing.JLabel();
        etiApellidoM = new javax.swing.JLabel();
        etiPuesto = new javax.swing.JLabel();
        etiExp = new javax.swing.JLabel();
        etiEstudios = new javax.swing.JLabel();
        etiEdad = new javax.swing.JLabel();
        etiIngreso = new javax.swing.JLabel();
        etiSesion = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        etiContra = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        storeImgButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        etiContacto = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        etiEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Datos");

        etiFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Código de empleado:");

        jLabel5.setText("Nombre:");

        jLabel6.setText("Apellido paterno:");

        jLabel7.setText("Apellido materno:");

        jLabel8.setText("Puesto:");

        jLabel9.setText("Experiencia:");

        jLabel10.setText("Grado de estudios:");

        jLabel11.setText("Edad:");

        jLabel12.setText("Fecha de ingreso:");

        jLabel13.setText("Última sesión:");

        etiCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiApellidoP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiApellidoM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiPuesto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiExp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiEstudios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiEdad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiIngreso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiSesion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        backButton.setText("Regresar");

        jLabel3.setText("Contraseña:");

        etiContra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        storeImgButton.setText("Guardar imagen");

        jLabel2.setText("Contacto:");

        etiContacto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setText("Estado:");

        etiEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(storeImgButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etiFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13))
                                        .addGap(21, 21, 21)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(etiNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(etiCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(etiApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(etiApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(etiPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(etiExp, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(etiEstudios, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(etiContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(etiEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(etiEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(etiIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(etiSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(72, 72, 72)
                                        .addComponent(etiContra, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 45, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                                                    .addComponent(etiContra, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(layout.createSequentialGroup()
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                            .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                    .addGroup(layout.createSequentialGroup()
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                            .addGroup(layout.createSequentialGroup()
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                    .addGroup(layout.createSequentialGroup()
                                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                            .addComponent(jLabel4)
                                                                                                            .addComponent(etiCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(jLabel5))
                                                                                                    .addComponent(etiNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jLabel6))
                                                                                            .addComponent(etiApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addComponent(jLabel7))
                                                                                    .addComponent(etiApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jLabel8))
                                                                            .addComponent(etiPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                            .addComponent(jLabel9)
                                                                            .addComponent(etiExp, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                    .addComponent(etiFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel10))
                                                            .addComponent(etiEstudios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel2))
                                                    .addComponent(etiContacto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel11))
                                            .addComponent(etiEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14))
                                    .addComponent(etiEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12))
                            .addComponent(etiIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13))
                    .addComponent(etiSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(storeImgButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        new dataWindow1(new javax.swing.JFrame(),true).setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    public static javax.swing.JLabel etiApellidoM;
    public static javax.swing.JLabel etiApellidoP;
    public static javax.swing.JLabel etiCodigo;
    public static javax.swing.JLabel etiContacto;
    public static javax.swing.JLabel etiContra;
    public static javax.swing.JLabel etiEdad;
    public static javax.swing.JLabel etiEstado;
    public static javax.swing.JLabel etiEstudios;
    public static javax.swing.JLabel etiExp;
    public static javax.swing.JLabel etiFoto;
    public static javax.swing.JLabel etiIngreso;
    public static javax.swing.JLabel etiNombre;
    public static javax.swing.JLabel etiPuesto;
    public static javax.swing.JLabel etiSesion;
    public static javax.swing.JLabel idLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton storeImgButton;
    // End of variables declaration//GEN-END:variables
}