package venSecundarias;

import clases.logger;
import clases.resourceDownload;
import clases.win10Notification;
import menuVentanas.menuVentanas;

import java.awt.Taskbar;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

public final class loadWindow extends javax.swing.JFrame{
    public loadWindow(){
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
        }catch(IllegalAccessException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
        }catch(UnsupportedLookAndFeelException m){
            JOptionPane.showMessageDialog(null,"Error:\n"+m.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
        }
        
        load();
        
        setLocationRelativeTo(null);
    }
    
    protected Timer t;
    
    protected File f;
    protected Image retValue;
    protected Properties p;
    protected InputStream is;
    protected FileOutputStream fos;
    
    protected URL u;
    protected URLConnection uc;
    
    protected int ent;
    
    protected byte[] bites;
    
    @Override
    public Image getIconImage(){
        p=new Properties();
        try{
            p.load(new FileInputStream("src/data/config/config.properties"));
            retValue=Toolkit.getDefaultToolkit().getImage(p.getProperty("icono"));
            
            retValue.flush();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+e.getMessage(),Level.WARNING);
            new logger().exceptionLogger("start(1IO-get)",Level.SEVERE,"getIconImage()",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+x.getMessage(),Level.WARNING);
            new logger().exceptionLogger("start(2IO-get)",Level.SEVERE,"getIconImage()",x.fillInStackTrace());
        }
        return retValue;
    }
    
    protected final void load(){
        Taskbar tb=Taskbar.getTaskbar();
        ActionListener al=(ActionEvent ae)->{
            if(jProgressBar1.getValue()<115&&Taskbar.isTaskbarSupported()){
                jProgressBar1.setValue(jProgressBar1.getValue()+5);
                tb.setWindowProgressState(loadWindow.this,Taskbar.State.NORMAL);
                tb.setWindowProgressValue(loadWindow.this,jProgressBar1.getValue());
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                switch(jProgressBar1.getValue()){
                    case 0:
                        jLabel2.setText(null);
                        jLabel2.setText("bridj-0.6.2.jar");
                        new resourceDownload().downloadLibs("bridj-0.6.2.jar","http://download857.mediafire.com/g481j40i6ykg/lzf97xqjc8i2tou/bridj-0.6.2.jar");
                        break;
                    case 5:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-beanutils-1.8.2.jar");
                        new resourceDownload().downloadLibs("commons-beanutils-1.8.2.jar","http://download948.mediafire.com/8wfgp1dibp1g/1bjokubhviqd7oy/commons-beanutils-1.8.2.jar");
                        break;
                    case 10:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-collections-20040616.jar");
                        new resourceDownload().downloadLibs("commons-collections-20040616.jar","http://download1321.mediafire.com/g2n95cokmvvg/iubx1pu950sqkuh/commons-collections-20040616.jar");
                        break;
                    case 15:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-dbutils-1.7.jar");
                        new resourceDownload().downloadLibs("commons-dbutils-1.7.jar","https://download1640.mediafire.com/51t9hl9efblg/u596d8tvooae3x3/commons-dbutils-1.7.jar");
                        break;
                    case 20:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-digester-2.1.jar");
                        new resourceDownload().downloadLibs("commons-digester-2.1.jar","https://download944.mediafire.com/azl77y51rrvg/8s1tfq5qbqgrte2/commons-digester-2.1.jar");
                        break;
                    case 25:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-logging-1.2.1.1.jar");
                        new resourceDownload().downloadLibs("commons-logging-1.2.1.1.jar","https://download946.mediafire.com/1kyfaheeydmg/t5q1fpls44cy9dx/commons-logging-1.2.1.1.jar");
                        break;
                    case 30:
                        jLabel2.setText(null);
                        jLabel2.setText("dms-19.3.0.0.jar");
                        new resourceDownload().downloadLibs("dms-19.3.0.0.jar","https://download1511.mediafire.com/h6gc1wkgooeg/hr0vah81v4umv6e/dms-19.3.0.0.jar");
                        break;
                    case 35:
                        jLabel2.setText(null);
                        jLabel2.setText("groovy-all-2.4.5.jar");
                        new resourceDownload().downloadLibs("groovy-all-2.4.5.jar","https://download1510.mediafire.com/y7cxz5afzdkg/rbpscmclmf08nbm/groovy-all-2.4.5.jar");
                        break;
                    case 40:
                        jLabel2.setText(null);
                        jLabel2.setText("iText-2.1.7.jar");
                        new resourceDownload().downloadLibs("iText-2.1.7.jar","https://download1593.mediafire.com/au9c8tsbyd1g/pe6hhwfpk0c0d2g/iText-2.1.7.jar");
                        break;
                    case 45:
                        jLabel2.setText(null);
                        jLabel2.setText("itext-pdfa-5.5.4.jar");
                        new resourceDownload().downloadLibs("itext-pdfa-5.5.4.jar","https://download1588.mediafire.com/da3dxv87t6kg/qlvfop758tinnqo/itext-pdfa-5.5.4.jar");
                        break;
                    case 50:
                        jLabel2.setText(null);
                        jLabel2.setText("itextpdf-5.5.4.jar");
                        new resourceDownload().downloadLibs("itextpdf-5.5.4.jar","https://download1583.mediafire.com/v9zo9rbdc6yg/65kk9h26u5pvm8g/itextpdf-5.5.4.jar");
                        break;
                    case 55:
                        jLabel2.setText(null);
                        jLabel2.setText("jasperreports-4.7.1.jar");
                        new resourceDownload().downloadLibs("jasperreports-4.7.1.jar","https://download940.mediafire.com/6uka89u4l6kg/jg4oi1ukqmlgrcw/jasperreports-4.7.1.jar");
                        break;
                    case 60:
                        jLabel2.setText(null);
                        jLabel2.setText("jasperreports-6.0.0.jar");
                        new resourceDownload().downloadLibs("jasperreports-6.0.0.jar","https://download1509.mediafire.com/708u9pgxswug/yue06ldio0qz21q/jasperreports-6.0.0.jar");
                        break;
                    case 65:
                        jLabel2.setText(null);
                        jLabel2.setText("jasperreports-javaflow-4.7.1.jar");
                        new resourceDownload().downloadLibs("jasperreports-javaflow-4.7.1.jar","https://download851.mediafire.com/twxlygkuhzag/n98i3o1cgzz9a1o/jasperreports-javaflow-4.7.1.jar");
                        break;
                    case 70:
                        jLabel2.setText(null);
                        jLabel2.setText("JPanelWebCam.jar");
                        new resourceDownload().downloadLibs("JPanelWebCam.jar","https://download1334.mediafire.com/dkyh1n1rtmgg/0o7jbfrehjphqvz/JPanelWebCam.jar");
                        break;
                    case 75:
                        jLabel2.setText(null);
                        jLabel2.setText("mysql-connector-java-8.0.17.jar");
                        new resourceDownload().downloadLibs("mysql-connector-java-8.0.17.jar","https://download1481.mediafire.com/h3u03fcda5ig/vlith3vil9706rs/mysql-connector-java-8.0.17.jar");
                        break;
                    case 80:
                        jLabel2.setText(null);
                        jLabel2.setText("oclc-dbutils-1.0.20080317.jar");
                        new resourceDownload().downloadLibs("oclc-dbutils-1.0.20080317.jar","https://download1585.mediafire.com/ed75mq82mc5g/s9462u1mtqvnv2n/oclc-dbutils-1.0.20080317.jar");
                        break;
                    case 85:
                        jLabel2.setText(null);
                        jLabel2.setText("ojdbc6_g.jar");
                        new resourceDownload().downloadLibs("ojdbc6_g.jar","https://download844.mediafire.com/t6d607ppm88g/ai1wzj7z8azvyl5/ojdbc6_g.jar");
                        break;
                    case 90:
                        jLabel2.setText(null);
                        jLabel2.setText("poi-3.5-FINAL.jar");
                        new resourceDownload().downloadLibs("poi-3.5-FINAL.jar","https://download1505.mediafire.com/9rco39egrdng/cpc8b9obnl4ob0o/poi-3.5-FINAL.jar");
                        break;
                    case 95:
                        jLabel2.setText(null);
                        jLabel2.setText("rs2xml.jar");
                        new resourceDownload().downloadLibs("rs2xml.jar","https://download850.mediafire.com/f9hgcuvbvy4g/dvc94za7iesvz0f/rs2xml.jar");
                        break;
                    case 100:
                        jLabel2.setText(null);
                        jLabel2.setText("slf4j-api-1.7.2.jar");
                        new resourceDownload().downloadLibs("slf4j-api-1.7.2.jar","https://download1583.mediafire.com/rha2eun6yy4g/3ggur09zdev86b1/slf4j-api-1.7.2.jar");
                        break;
                    case 105:
                        jLabel2.setText(null);
                        jLabel2.setText("slf4j-simple-1.6.1.jar");
                        new resourceDownload().downloadLibs("slf4j-simple-1.6.1.jar","https://download1581.mediafire.com/h5pkq5l7ydrg/4hce15bg4tp4ak6/slf4j-simple-1.6.1.jar");
                        break;
                    case 110:
                        jLabel2.setText(null);
                        jLabel2.setText("webcam-capture-0.3.10.jar");
                        new resourceDownload().downloadLibs("webcam-capture-0.3.10.jar","https://download1073.mediafire.com/1geeo2nqwwog/z00fyqio5fr1qrm/webcam-capture-0.3.10.jar");
                        break;
                    case 115:
                        jLabel2.setText(null);
                        jLabel2.setText("PlaceHolder.jar");
                        new resourceDownload().downloadLibs("PlaceHolder.jar","https://download941.mediafire.com/fhzz83vhg0pg/mykmzydwe3am1x4/PlaceHolder.jar");
                        break;
                    default:
                        new win10Notification().trayNotify("Error 39","No se puede descargar las librerías de internet",TrayIcon.MessageType.ERROR);
                        new logger().logStaticSaver("No se puede descargar las librerías de internet",Level.WARNING);
                        tb.setWindowProgressState(loadWindow.this,Taskbar.State.ERROR);
                }
            }else{
                t.stop();
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                new menuVentanas().setVisible(true);
                dispose();
            }
        };
        t=new Timer(115,al);
        t.start();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());
        setUndecorated(true);

        jProgressBar1.setMaximum(115);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Cargando:");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        new loadWindow().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}