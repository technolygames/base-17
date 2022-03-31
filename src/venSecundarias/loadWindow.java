package venSecundarias;
//clases
import clases.logger;
import clases.Icono;
//import clases.resourceDownload;//still in use
import menus.menuVentanas;
//java
import java.awt.Cursor;
import java.awt.Taskbar;
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
//extension larga
import java.util.logging.Level;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            new logger().logStaticSaver("Error CNFE: "+e.getMessage()+".\nOcurrió en la clase '"+loadWindow.class.getName()+"', en el método 'loadWindow()'",Level.WARNING);
            new logger().exceptionLogger(loadWindow.class.getName(),Level.WARNING,"loadWindow-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IE: "+x.getMessage()+".\nOcurrió en la clase '"+loadWindow.class.getName()+"', en el método 'loadWindow()'",Level.WARNING);
            new logger().exceptionLogger(loadWindow.class.getName(),Level.WARNING,"loadWindow-IE",x.fillInStackTrace());
        }catch(IllegalAccessException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IAE: "+n.getMessage()+".\nOcurrió en la clase '"+loadWindow.class.getName()+"', en el método 'loadWindow()'",Level.WARNING);
            new logger().exceptionLogger(loadWindow.class.getName(),Level.WARNING,"loadWindow-IAE",n.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 28: "+y.getMessage()+".\nOcurrió en la clase '"+loadWindow.class.getName()+"', en el método 'loadWindow()'",Level.WARNING);
            new logger().exceptionLogger(loadWindow.class.getName(),Level.WARNING,"loadWindow-28",y.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+loadWindow.class.getName()+"', en el método 'loadWindow()'",Level.WARNING);
            new logger().exceptionLogger(loadWindow.class.getName(),Level.WARNING,"loadWindow-0",k.fillInStackTrace());
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+s.getMessage()+".\nOcurrió en la clase '"+loadWindow.class.getName()+"', en el método 'loadWindow()'",Level.WARNING);
            new logger().exceptionLogger(loadWindow.class.getName(),Level.WARNING,"loadWindow-1IO",s.fillInStackTrace());
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+d.getMessage()+".\nOcurrió en la clase '"+loadWindow.class.getName()+"', en el método 'loadWindow()'",Level.WARNING);
            new logger().exceptionLogger(loadWindow.class.getName(),Level.WARNING,"loadWindow-2IO",d.fillInStackTrace());
        }
        
        load();
        
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    protected Timer t;
    protected Taskbar tb;
    
    protected File f;
    protected Properties p;
    protected InputStream is;
    protected FileOutputStream fos;
    
    protected URL u;
    protected URLConnection uc;
    
    protected int ent;
    
    protected byte[] bites;
    
    protected final void load(){
        tb=Taskbar.getTaskbar();
        p=new Properties();
        
        try{
            p.load(new FileInputStream("src/data/config/libs.properties"));
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+loadWindow.class.getName()+"', en el método 'load()'",Level.WARNING);
            new logger().exceptionLogger(loadWindow.class.getName(),Level.WARNING,"load-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+loadWindow.class.getName()+"', en el método 'load()'",Level.WARNING);
            new logger().exceptionLogger(loadWindow.class.getName(),Level.WARNING,"load-2IO",x.fillInStackTrace());
        }
        
        ActionListener al=(ActionEvent ae)->{
            if(jProgressBar1.getValue()<40&&Taskbar.isTaskbarSupported()){
                jProgressBar1.setValue(jProgressBar1.getValue()+2);
                jProgressBar1.setMaximum(40);
                tb.setWindowProgressState(loadWindow.this,Taskbar.State.NORMAL);
                tb.setWindowProgressValue(loadWindow.this,jProgressBar1.getValue());
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                switch(jProgressBar1.getValue()){
                    case 0:
                        jLabel2.setText(null);
                        jLabel2.setText("bridj-0.6.2.jar");
                        //new resourceDownload().downloadLibs("bridj-0.6.2.jar",p.getProperty("bridj-0.6.2.jar"));
                        break;
                    case 2:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-beanutils-1.8.2.jar");
                        //new resourceDownload().downloadLibs("commons-beanutils-1.8.2.jar",p.getProperty("commons-beanutils-1.8.2.jar"));
                        break;
                    case 4:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-collections-20040616.jar");
                        //new resourceDownload().downloadLibs("commons-collections-20040616.jar",p.getProperty("commons-collections-20040616.jar"));
                        break;
                    case 6:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-dbutils-1.7.jar");
                        //new resourceDownload().downloadLibs("commons-dbutils-1.7.jar",p.getProperty("commons-dbutils-1.7.jar"));
                        break;
                    case 8:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-digester-2.1.jar");
                        //new resourceDownload().downloadLibs("commons-digester-2.1.jar",p.getProperty("commons-digester-2.1.jar"));
                        break;
                    case 10:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-logging-1.2.1.1.jar");
                        //new resourceDownload().downloadLibs("commons-logging-1.2.1.1.jar",p.getProperty("commons-logging-1.2.1.1.jar"));
                        break;
                    case 12:
                        jLabel2.setText(null);
                        jLabel2.setText("dms-19.3.0.0.jar");
                        //new resourceDownload().downloadLibs("dms-19.3.0.0.jar",p.getProperty("dms-19.3.0.0.jar"));
                        break;
                    case 14:
                        jLabel2.setText(null);
                        jLabel2.setText("groovy-all-2.4.5.jar");
                        //new resourceDownload().downloadLibs("groovy-all-2.4.5.jar",p.getProperty("groovy-all-2.4.5.jar"));
                        break;
                    case 16:
                        jLabel2.setText(null);
                        jLabel2.setText("iText-2.1.7.jar");
                        //new resourceDownload().downloadLibs("iText-2.1.7.jar",p.getProperty("iText-2.1.7.jar"));
                        break;
                    case 18:
                        jLabel2.setText(null);
                        jLabel2.setText("itext-pdfa-5.5.4.jar");
                        //new resourceDownload().downloadLibs("itext-pdfa-5.5.4.jar",p.getProperty("itext-pdfa-5.5.4.jar"));
                        break;
                    case 20:
                        jLabel2.setText(null);
                        jLabel2.setText("itextpdf-5.5.4.jar");
                        //new resourceDownload().downloadLibs("itextpdf-5.5.4.jar",p.getProperty("itextpdf-5.5.4.jar"));
                        break;
                    case 22:
                        jLabel2.setText(null);
                        jLabel2.setText("jasperreports-4.7.1.jar");
                        //new resourceDownload().downloadLibs("jasperreports-4.7.1.jar",p.getProperty("jasperreports-4.7.1.jar"));
                        break;
                    case 24:
                        jLabel2.setText(null);
                        jLabel2.setText("jasperreports-6.0.0.jar");
                        //new resourceDownload().downloadLibs("jasperreports-6.0.0.jar",p.getProperty("jasperreports-6.0.0.jar"));
                        break;
                    case 26:
                        jLabel2.setText(null);
                        jLabel2.setText("jasperreports-javaflow-4.7.1.jar");
                        //new resourceDownload().downloadLibs("jasperreports-javaflow-4.7.1.jar",p.getProperty("jasperreports-javaflow-4.7.1.jar"));
                        break;
                    case 28:
                        jLabel2.setText(null);
                        jLabel2.setText("mysql-connector-java-8.0.17.jar");
                        //new resourceDownload().downloadLibs("mysql-connector-java-8.0.17.jar",p.getProperty("mysql-connector-java-8.0.17.jar"));
                        break;
                    case 30:
                        jLabel2.setText(null);
                        jLabel2.setText("oclc-dbutils-1.0.20080317.jar");
                        //new resourceDownload().downloadLibs("oclc-dbutils-1.0.20080317.jar",p.getProperty("oclc-dbutils-1.0.20080317.jar"));
                        break;
                    case 32:
                        jLabel2.setText(null);
                        jLabel2.setText("ojdbc6_g.jar");
                        //new resourceDownload().downloadLibs("ojdbc6_g.jar",p.getProperty("ojdbc6_g.jar"));
                        break;
                    case 34:
                        jLabel2.setText(null);
                        jLabel2.setText("poi-3.5-FINAL.jar");
                        //new resourceDownload().downloadLibs("poi-3.5-FINAL.jar",p.getProperty("poi-3.5-FINAL.jar"));
                        break;
                    case 36:
                        jLabel2.setText(null);
                        jLabel2.setText("rs2xml.jar");
                        //new resourceDownload().downloadLibs("rs2xml.jar",p.getProperty("rs2xml.jar"));
                        break;
                    case 38:
                        jLabel2.setText(null);
                        jLabel2.setText("PlaceHolder.jar");
                        //new resourceDownload().downloadLibs("PlaceHolder.jar",p.getProperty("PlaceHolder.jar"));
                        break;
                    case 40:
                        jLabel2.setText(null);
                        jLabel2.setText("jcalendar-1.4.jar");
                        //new resourceDownload().downloadLibs("PlaceHolder.jar",p.getProperty("jcalendar-1.4.jar"));
                        break;
                    default:
                        new logger().logStaticSaver("No se puede descargar las librerías",Level.WARNING);
                        tb.setWindowProgressState(loadWindow.this,Taskbar.State.ERROR);
                }
            }else{
                t.stop();
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                new menuVentanas().setVisible(true);
                dispose();
            }
        };
        t=new Timer(100,al);
        t.start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Icono().getIconImage());
        setUndecorated(true);

        jProgressBar1.setMaximum(48);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Cargando:");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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