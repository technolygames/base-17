package venTerciarias;
//clases
import clases.datos;
import clases.guiMediaHandler;
import clases.logger;
import clases.thread2;
import clases.validation;
import paneles.countPanel;
import venPrimarias.start;
import menus.menuDatosVentana1;
//java
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

public class dataWindow1 extends javax.swing.JDialog{
    public dataWindow1(java.awt.Frame parent,boolean modal){
        super(parent,modal);
        initComponents();
        new guiMediaHandler(dataWindow1.class.getName()).LookAndFeel(dataWindow1.this);
        
        botones();
        datosMostrar();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Datos del empleado");
        setResizable(false);
        pack();
    }
    
    protected int codigo;
    
    public dataWindow1(java.awt.Frame parent,boolean modal,int code){
        super(parent,modal);
        initComponents();
        new guiMediaHandler(dataWindow1.class.getName()).LookAndFeel(dataWindow1.this);
        
        this.codigo=code;
        
        botones();
        datosMostrar();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Datos del empleado");
        setResizable(false);
        pack();
    }
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    protected final void settings(){
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea2.setLineWrap(true);
        jTextArea2.setWrapStyleWord(true);
        
        miModData.setVisible(false);
        
        if(new validation(etiPuesto.getText(),dataWindow1.class.getName()).isAccessible()&&codigo!=0){
            miModData.setVisible(true);
        }
    }
    
    protected final void datosMostrar(){
        try{
            ps=new datos().getConnection().prepareStatement("select * from empleados where codigo_emp=?;");
            ps.setInt(1,codigo);
            rs=ps.executeQuery();
            if(rs.next()){
                etiContra.setText(rs.getString("password"));
                etiCodigo.setText(String.valueOf(rs.getInt("codigo_emp")));
                etiNombre.setText(rs.getString("nombre_emp"));
                etiApellidoP.setText(rs.getString("apellidop_emp"));
                etiApellidoM.setText(rs.getString("apellidom_emp"));
                etiCURP.setText(rs.getString("curp"));
                jTextArea1.setText(rs.getString("domicilio"));
                etiPuesto.setText(rs.getString("puesto"));
                etiExp.setText(rs.getString("experiencia"));
                etiEstudios.setText(rs.getString("grado_estudios"));
                etiContacto.setText(rs.getString("contacto"));
                etiFN.setText(String.valueOf(rs.getDate("fecha_nacimiento")));
                etiEdad.setText(String.valueOf(rs.getInt("edad")));
                etiEstado.setText(rs.getString("estado"));
                jTextArea2.setText(rs.getString("datos_extra"));
                etiIngreso.setText(String.valueOf(rs.getDate("fecha_registro")));
                etiSesion.setText(String.valueOf(rs.getDate("fecha_sesion")));
                
                //new escritorJSON().writeDataWorkerJson(Integer.parseInt(etiCodigo.getText()));
                
                etiFoto.setIcon(new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(rs.getBytes("foto"))).getImage().getScaledInstance(etiFoto.getWidth(),etiFoto.getHeight(),Image.SCALE_DEFAULT)));
            }else{
                JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'datosMostrar()'");
            }
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'datosMostrar()'");
            new logger(Level.SEVERE).exceptionLogger(dataWindow1.class.getName(),"datosMostrar-14",e.fillInStackTrace());
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'datosMostrar()'");
            new logger(Level.SEVERE).exceptionLogger(dataWindow1.class.getName(),"datosMostrar-0",x.fillInStackTrace());
        }
    }
    
    protected final void botones(){
        backButton.addActionListener((a)->{
            setVisible(false);
            dispose();
        });
        
        miStorePic.addActionListener((a)->{
            try{
                int codigo1=Integer.parseInt(etiCodigo.getText());
                String nombre=etiNombre.getText();
                
                ps=new datos().getConnection().prepareStatement("select foto from empleados where codigo_emp=?;");
                ps.setInt(1,codigo1);
                rs=ps.executeQuery();
                
                File f=new File("data/media/dataImage/Empleados/"+nombre+"-"+codigo1+".jpg");
                for(int i=0;f.exists();i++){
                    f=new File("data/media/dataImage/Empleados/"+nombre+"-"+codigo1+"-("+i+").jpg");
                }
                
                new thread2(rs,new FileOutputStream(f)).run();
                
                new logger(Level.INFO).staticLogger("Se guardó correctamente la imagen del empleado.\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'botones(miStorePic)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
                
                ps.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'botones(miStorePic)'");
                new logger(Level.SEVERE).exceptionLogger(dataWindow1.class.getName(),"botones.miStorePic-14",e.fillInStackTrace());
            }catch(FileNotFoundException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'botones(miStorePic)'");
                new logger(Level.SEVERE).exceptionLogger(dataWindow1.class.getName(),"botones.miStorePic-10",x.fillInStackTrace());
            }catch(NullPointerException n){
                JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 0: "+n.getMessage()+".\nOcurrió en la clase '"+dataWindow1.class.getName()+"', en el método 'botones(miStorePic)'");
                new logger(Level.SEVERE).exceptionLogger(dataWindow1.class.getName(),"botones.miStorePic-0",n.fillInStackTrace());
            }
        });
        
        miCountViewer.addActionListener((a)->{
            new countViewer(new javax.swing.JFrame(),true,new countPanel(Integer.parseInt(etiCodigo.getText()))).setVisible(true);
        });
        
        miModData.addActionListener((a)->{
            new menuDatosVentana1(Integer.parseInt(etiCodigo.getText())).setVisible(true);
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        etiFoto = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        etiExp = new javax.swing.JLabel();
        etiCodigo = new javax.swing.JLabel();
        etiEstudios = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        etiEdad = new javax.swing.JLabel();
        etiContra = new javax.swing.JLabel();
        etiIngreso = new javax.swing.JLabel();
        etiSesion = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        etiContacto = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        etiEstado = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        etiNombre = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        etiApellidoP = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        etiApellidoM = new javax.swing.JLabel();
        etiPuesto = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        etiCURP = new javax.swing.JLabel();
        etiFN = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miStorePic = new javax.swing.JMenuItem();
        miCountViewer = new javax.swing.JMenuItem();
        miModData = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new guiMediaHandler(dataWindow1.class.getName()).getIconImage());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Datos");

        etiFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        backButton.setText("Regresar");

        jLabel12.setText("Fecha de ingreso:");

        jLabel13.setText("Última sesión:");

        etiExp.setText(" ");
        etiExp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiCodigo.setText(" ");
        etiCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiEstudios.setText(" ");
        etiEstudios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Contraseña:");

        etiEdad.setText(" ");
        etiEdad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiContra.setText(" ");
        etiContra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiIngreso.setText(" ");
        etiIngreso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiSesion.setText(" ");
        etiSesion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Contacto:");

        etiContacto.setText(" ");
        etiContacto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setText("Estado:");

        jLabel15.setText("Domicilio:");

        etiEstado.setText(" ");
        etiEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel16.setText("Datos extra:");

        jLabel4.setText("Código de empleado:");

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setFocusable(false);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel5.setText("Nombre:");

        jLabel6.setText("Apellido paterno:");

        jLabel7.setText("Apellido materno:");

        jLabel8.setText("Puesto:");

        jLabel9.setText("Experiencia:");

        etiNombre.setText(" ");
        etiNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setText("Grado de estudios:");

        etiApellidoP.setText(" ");
        etiApellidoP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setText("Edad:");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        etiApellidoM.setText(" ");
        etiApellidoM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiPuesto.setText(" ");
        etiPuesto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setText("CURP:");

        etiCURP.setText(" ");
        etiCURP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiFN.setText(" ");
        etiFN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel18.setText("Fecha de nacimiento:");

        jMenu1.setText("Acciones");

        miStorePic.setText("Guardar imagen");
        jMenu1.add(miStorePic);

        miCountViewer.setText("Ver conteo");
        jMenu1.add(miCountViewer);

        miModData.setText("Modificar datos");
        jMenu1.add(miModData);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 433, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel16)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel18)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel17)
                            .addComponent(jLabel15)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiContra, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiCURP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiExp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiEstudios, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiFN, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etiFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(etiContra))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(etiCodigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(etiNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(etiApellidoP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(etiApellidoM))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(etiCURP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etiPuesto)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(etiExp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(etiEstudios))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(etiContacto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(etiFN))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(etiEdad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(etiEstado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etiIngreso)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(etiSesion))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->{
            new dataWindow1(new javax.swing.JFrame(),true).setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel etiApellidoM;
    private javax.swing.JLabel etiApellidoP;
    private javax.swing.JLabel etiCURP;
    private javax.swing.JLabel etiCodigo;
    private javax.swing.JLabel etiContacto;
    private javax.swing.JLabel etiContra;
    private javax.swing.JLabel etiEdad;
    private javax.swing.JLabel etiEstado;
    private javax.swing.JLabel etiEstudios;
    private javax.swing.JLabel etiExp;
    private javax.swing.JLabel etiFN;
    private javax.swing.JLabel etiFoto;
    private javax.swing.JLabel etiIngreso;
    private javax.swing.JLabel etiNombre;
    private javax.swing.JLabel etiPuesto;
    private javax.swing.JLabel etiSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JMenuItem miCountViewer;
    private javax.swing.JMenuItem miModData;
    private javax.swing.JMenuItem miStorePic;
    // End of variables declaration//GEN-END:variables
}