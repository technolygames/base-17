package venTerciarias;
//clases
import clases.Datos;
import clases.Dirs;
import clases.Events;
import clases.MediaHandler;
import clases.logger;
import clases.Thread2;
import java.awt.Desktop;
import venPrimarias.start;
//java
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
//extension larga
import java.util.logging.Level;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.nio.charset.StandardCharsets;
import java.lang.reflect.InaccessibleObjectException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.InternetAddress;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.util.JRFontNotFoundException;

public class dataWindow2 extends javax.swing.JDialog{
    public dataWindow2(java.awt.Frame parent,boolean modal){
        super(parent, modal);
        initComponents();
        new MediaHandler(dataWindow2.class.getName()).setLookAndFeel(dataWindow2.this);
        
        botones();
        datosMostrar();
        popup();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Datos del socio");
        setResizable(false);
        pack();
    }
    
    protected int codigo;
    
    public dataWindow2(java.awt.Frame parent,boolean modal,int code){
        super(parent, modal);
        initComponents();
        new MediaHandler(dataWindow2.class.getName()).setLookAndFeel(dataWindow2.this);
        
        this.codigo=code;
        
        botones();
        datosMostrar();
        popup();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Datos del socio");
        setResizable(false);
        pack();
    }
    
    protected String dir;
    protected String image;
    protected String methodName;
    
    protected String email;
    protected String name;
    protected String pass;
    protected String port;
    protected String server;
    
    protected Properties p;
    
    protected JPopupMenu popup;
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    protected final void settings(){
        methodName="settings";
        
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        
        try{
            p=new Properties();
            p.load(new FileReader("data/config/smtp.properties"));
            
            email=p.getProperty("correo");
            name=p.getProperty("nombre");
            pass=p.getProperty("password");
            port=p.getProperty("puerto");
            server=p.getProperty("server");
        }catch(IOException e){
            new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(this,e,methodName,"2IO");
        }
    }
    
    protected final void datosMostrar(){
        methodName="datosMostrar";
        
        try{
            ps=new Datos().getConnection().prepareStatement("select * from socios where codigo_part=?;");
            ps.setInt(1,codigo);
            rs=ps.executeQuery();
            if(rs.next()){
                etiCodigo.setText(String.valueOf(rs.getInt("codigo_part")));
                etiNombre.setText(rs.getString("nombre_part"));
                etiApellidoP.setText(rs.getString("apellidop_part"));
                etiApellidoM.setText(rs.getString("apellidom_part"));
                etiTipoSocio.setText(rs.getString("tipo_socio"));
                etiCorreo.setText(rs.getString("correo"));
                etiRFC.setText(rs.getString("rfc"));
                jTextArea1.setText(rs.getString("datos_extra"));
                etiIngreso.setText(rs.getString("fecha_ingreso"));
                etiUCompra.setText(rs.getString("fecha_ucompra"));
                
                //new escritorJSON().writeDataPartnerJson(Integer.parseInt(etiCodigo.getText()));
                
                etiFoto.setIcon(new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(rs.getBytes("foto"))).getImage().getScaledInstance(etiFoto.getWidth(),etiFoto.getHeight(),Image.SCALE_DEFAULT)));
            }else{
                new logger(Level.WARNING,this.getClass().getName()).storeAndViewError14(this,methodName);
            }
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(this,e,methodName,"14");
        }catch(NullPointerException x){
            new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(this,x,methodName,"0");
        }
    }
    
    protected final void botones(){
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        miCreateInvoice.addActionListener(a->
            imprimirReporte()
        );
        
        miStorePic.addActionListener(a->{
            methodName="botones.miStorePic";
            try{
                int codigo1=Integer.parseInt(etiCodigo.getText());
                String nombre=etiNombre.getText();
                
                ps=new Datos().getConnection().prepareStatement("select foto from socios where codigo_part=?;");
                ps.setInt(1,codigo1);
                rs=ps.executeQuery();
                
                File f=new File("data/media/dataImage/Socios/"+nombre+"-"+codigo1+".jpg");
                String path=Dirs.exists(f);
                
                new Thread2(rs,new FileOutputStream(path)).run();
                
                logger.staticLogger(Level.INFO,"Se guardó correctamente la imagen del socio.\nOcurrió en la clase '"+dataWindow2.class.getName()+"', en el método 'botones(miStorePic)'.\nUsuario que hizo la acción: "+String.valueOf(start.USERID),this.getClass().getName());
                
                ps.close();
            }catch(SQLException e){
                new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(this,e,methodName,"14");
            }catch(FileNotFoundException x){
                new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(this,x,methodName,"1IO");
            }catch(NullPointerException n){
                new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(this,n,methodName,"0");
            }
        });
        
        etiCorreo.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent a){
                Events.showPopup(popup,a);
            }
        });
    }
    
    protected void imprimirReporte(){
        methodName="imprimirReporte";
        p=new Properties();
        MediaHandler mh=new MediaHandler(dataWindow2.class.getName());
        try{
            p.load(new FileReader("data/config/config.properties",StandardCharsets.UTF_8));
            
            Map<String,Object> params=new HashMap<>();
            
            params.put("imagen",mh.getImagePath("imagenes","imagen_respaldo"));
            params.put("nombre_reporte",mh.getProgramName());
            params.put("codigo_emp",start.USERID);
            params.put("codigo_part",Integer.parseInt(etiCodigo.getText()));
            
            JasperPrint jp=JasperFillManager.fillReport(JasperCompileManager.compileReport("data/database/Jasper/reportes.jrxml"),params,new Datos().getConnection());
            JasperViewer jv=new JasperViewer(jp);
            jv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jv.setVisible(false);
            
            File f=new File("data/generic/Jasper/reporte.pdf");
            String path=Dirs.exists(f);
            
            JasperExportManager.exportReportToPdfFile(jp,path);
            
            dir=f.getPath();
            
            if(JOptionPane.showConfirmDialog(this,"¿Deseas abrir el archivo?","Notice 1",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
                Desktop.getDesktop().open(f);
            }
        }catch(JRException e){
            new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(this,e,methodName,"17");
        }catch(ExceptionInInitializerError x){
            new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(this,x,methodName,"EIIE");
        }catch(NoClassDefFoundError n){
            new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(this,n,methodName,"NCDFE");
        }catch(IOException k){
            new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(this,k,methodName,"2IO");
        }catch(JRFontNotFoundException l){
            new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(this,l,methodName,"JRFNFE");
        }catch(InaccessibleObjectException r){
            new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(this,r,methodName,"IAE");
        }
    }
    
    protected final void popup(){
        popup=new JPopupMenu();
        
        JMenuItem mi1=new JMenuItem(new AbstractAction("Enviar factura"){
            @Override
            public void actionPerformed(ActionEvent e){
                if(dir!=null){
                    sendEmail(etiCorreo.getText(),"Test","Prueba de mandar un archivo");
                    logger.staticLogger(Level.INFO,"existe el archivo y se puede enviar",this.getClass().getName());
                }else{
                    JOptionPane.showMessageDialog(null,"Crea el reporte para poder usar esta función","Error Prueba",JOptionPane.WARNING_MESSAGE);
                    logger.staticLogger(Level.INFO,"no existe",this.getClass().getName());
                }
            }
        });
        
        popup.add(mi1);
    }
    
    protected void sendEmail(String toEmail,String subject,String body){
        p=new Properties();
        try{
            String bool="true";
            
            p.put("mail.smtp.host","smtp."+server+".com");
            p.put("mail.smtp.socketFactory.port",port);
            p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            p.put("mail.smtp.auth",bool);
            p.put("mail.smtp.port",port);
            p.put("mail.smtp.starttls.enable",bool);
            p.put("mail.smtp.ssl.checkserveridentity",true);
            
            Session session=Session.getDefaultInstance(p,new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(email,pass);
                }
            });
            
            MimeMessage msg=new MimeMessage(session);
            msg.addHeader("Content-type","text/HTML; charset-UTF-8");
            msg.addHeader("format","flowed");
            msg.addHeader("Content-Transfer-Encoding","8bit");
            msg.setFrom(new InternetAddress(email,name));
            msg.setReplyTo(InternetAddress.parse(email,false));
            msg.setSubject(subject,"UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toEmail,false));
            
            BodyPart msgbp=new MimeBodyPart();
            msgbp.setText(body);
            
            Multipart mp=new MimeMultipart();
            mp.addBodyPart(msgbp);
            
            msgbp=new MimeBodyPart();
            msgbp.setDataHandler(new DataHandler(new FileDataSource(dir)));
            msgbp.setFileName(new File(dir).getName());
            mp.addBodyPart(msgbp);
            
            msg.setContent(mp);
            
            Transport.send(msg);
        }catch(MessagingException e){
            new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(this,e,methodName,"Prueba1");
        }catch(UnsupportedEncodingException x){
            new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(this,x,methodName,"Prueba2");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        storeImgButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        etiFoto = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        etiCodigo = new javax.swing.JLabel();
        etiNombre = new javax.swing.JLabel();
        etiApellidoP = new javax.swing.JLabel();
        etiApellidoM = new javax.swing.JLabel();
        etiTipoSocio = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        etiIngreso = new javax.swing.JLabel();
        etiUCompra = new javax.swing.JLabel();
        etiCorreo = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        etiRFC = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miStorePic = new javax.swing.JMenuItem();
        miCreateInvoice = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new MediaHandler(dataWindow2.class.getName()).getIconImage());

        storeImgButton.setText("Guardar imagen");

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Datos");

        etiFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        backButton.setText("Regresar");

        jLabel2.setText("Código del socio:");

        jLabel3.setText("Nombre del socio:");

        jLabel4.setText("Apellido paterno:");

        jLabel5.setText("Apellido materno:");

        jLabel6.setText("Tipo de socio:");

        jLabel7.setText("Datos extra:");

        jLabel8.setText("Fecha de ingreso:");

        jLabel9.setText("Fecha de última compra:");

        etiCodigo.setText(" ");
        etiCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiNombre.setText(" ");
        etiNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiApellidoP.setText(" ");
        etiApellidoP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiApellidoM.setText(" ");
        etiApellidoM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiTipoSocio.setText(" ");
        etiTipoSocio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        etiIngreso.setText(" ");
        etiIngreso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiUCompra.setText(" ");
        etiUCompra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiCorreo.setText(" ");
        etiCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setText("Correo electrónico:");

        etiRFC.setText(" ");
        etiRFC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setText("RFC:");

        jMenu1.setText("Acciones");

        miStorePic.setText("Guardar imagen");
        jMenu1.add(miStorePic);

        miCreateInvoice.setText("Crear factura");
        jMenu1.add(miCreateInvoice);

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(etiApellidoM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(etiApellidoP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(etiCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(etiNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(etiTipoSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiUCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiRFC, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(storeImgButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(5, 5, 5)
                        .addComponent(etiFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etiCodigo)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etiNombre)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(etiApellidoP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(etiApellidoM))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etiTipoSocio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(etiCorreo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(etiRFC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(etiIngreso))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(etiUCompra))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(storeImgButton)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new dataWindow2(new javax.swing.JFrame(),true).setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel etiApellidoM;
    private javax.swing.JLabel etiApellidoP;
    private javax.swing.JLabel etiCodigo;
    private javax.swing.JLabel etiCorreo;
    private javax.swing.JLabel etiFoto;
    private javax.swing.JLabel etiIngreso;
    private javax.swing.JLabel etiNombre;
    private javax.swing.JLabel etiRFC;
    private javax.swing.JLabel etiTipoSocio;
    private javax.swing.JLabel etiUCompra;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuItem miCreateInvoice;
    private javax.swing.JMenuItem miStorePic;
    private javax.swing.JButton storeImgButton;
    // End of variables declaration//GEN-END:variables
}