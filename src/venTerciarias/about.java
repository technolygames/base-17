package venTerciarias;
//clases
import clases.Icono;
import clases.laf;
import clases.logger;
//java
import java.awt.Cursor;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

public final class about extends javax.swing.JDialog{
    public about(java.awt.Frame parent, boolean modal){
        super(parent, modal);
        initComponents();
        new laf().LookAndFeel(about.this,about.class.getName(),"about");
        
        etiquetas();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Acerca del programa");
        setResizable(false);
    }
    
    protected Properties p;
    
    protected void settings(){
        websiteLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    protected void etiquetas(){
        p=new Properties();
        try{
            p.load(new FileInputStream(System.getProperty("user.dir")+"/src/data/config/acerca.properties"));
            
            versionLabel.setText(p.getProperty("version"));
            estableLabel.setText(p.getProperty("estable"));
            inicioLabel.setText(p.getProperty("inicio"));
            finLabel.setText(p.getProperty("fin"));
            devLabel.setText(p.getProperty("dev"));
            pubLabel.setText(p.getProperty("pub"));
            fpubLabel.setText(p.getProperty("fpub"));
            jdkLabel.setText(p.getProperty("jdk"));
            jreLabel.setText(p.getProperty("jre"));
            devonLabel.setText(p.getProperty("devon"));
            propLabel.setText(p.getProperty("prop"));
            websiteLabel.setText(p.getProperty("website"));
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+about.class.getName()+"', en el método 'etiquetas()'",Level.WARNING);
            new logger().exceptionLogger(about.class.getName(),Level.WARNING,"etiqueta-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+about.class.getName()+"', en el método 'etiquetas()'",Level.WARNING);
            new logger().exceptionLogger(about.class.getName(),Level.WARNING,"etiquetas-2IO",x.fillInStackTrace());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        versionLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        devLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pubLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fpubLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jdkLabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jreLabel = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        devonLabel = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        estableLabel = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        finLabel = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        propLabel = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        inicioLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        websiteLabel = new javax.swing.JLabel();

        jLabel7.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Icono().getIconImage());

        jLabel1.setText("Versión:");

        versionLabel.setText("jLabel1");

        jLabel3.setText("Desarrolladora:");

        devLabel.setText("jLabel5");

        jLabel5.setText("Publicadora:");

        pubLabel.setText("jLabel6");

        jLabel9.setText("Fecha de publicación:");

        fpubLabel.setText("jLabel7");

        jLabel11.setText("Versión de JDK:");

        jdkLabel.setText("jLabel8");

        jLabel13.setText("Versión de JRE:");

        jreLabel.setText("jLabel9");

        jLabel15.setText("Desarrollado en:");

        devonLabel.setText("jLabel10");

        jLabel17.setText("Última versión estable:");

        estableLabel.setText("jLabel2");

        jLabel19.setText("Fin del desarrollo:");

        finLabel.setText("jLabel4");

        jLabel21.setText("Propietario da la versión:");

        propLabel.setText("jLabel11");

        jLabel24.setText("Gracias por adquirirlo");

        jLabel25.setText("Inicio del desarrollo:");

        inicioLabel.setText("jLabel3");

        jLabel8.setText("Página del desarrollador:");

        websiteLabel.setText("jLabel12");
        websiteLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                websiteLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addGap(306, 306, 306))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(versionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(estableLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inicioLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(finLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(devLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pubLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fpubLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdkLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(devonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(propLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(websiteLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(versionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(estableLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(inicioLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(finLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(devLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(pubLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(fpubLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jdkLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jreLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(devonLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(propLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(websiteLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        new about(new javax.swing.JFrame(),true).setVisible(true);
    }
    
    private void websiteLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_websiteLabelMouseClicked
        p=new Properties();
        try{
            p.load(new FileInputStream(System.getProperty("user.dir")+"/src/data/config/acerca.properties"));
            Desktop.getDesktop().browse(new URI(p.getProperty("website")));
        }catch(URISyntaxException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 31",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 31: "+e.getMessage()+".\nOcurrió en la clase '"+about.class.getName()+"', en el método 'jLabel27MouseClicked()'",Level.WARNING);
            new logger().exceptionLogger(about.class.getName(),Level.WARNING,"MouseClicked-31",e.fillInStackTrace());
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+about.class.getName()+"', en el método 'jLabel27MouseClicked()'",Level.WARNING);
            new logger().exceptionLogger(about.class.getName(),Level.WARNING,"MouseClicked-0",x.fillInStackTrace());
        }catch(FileNotFoundException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+ñ.getMessage()+".\nOcurrió en la clase '"+about.class.getName()+"', en el método 'jLabel27MouseClicked()'",Level.WARNING);
            new logger().exceptionLogger(about.class.getName(),Level.WARNING,"MouseClicked-1IO",ñ.fillInStackTrace());
        }catch(IOException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+k.getMessage()+".\nOcurrió en la clase '"+about.class.getName()+"', en el método 'jLabel27MouseClicked()'",Level.WARNING);
            new logger().exceptionLogger(about.class.getName(),Level.WARNING,"MouseClicked-2IO",k.fillInStackTrace());
        }
    }//GEN-LAST:event_websiteLabelMouseClicked
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel devLabel;
    private javax.swing.JLabel devonLabel;
    private javax.swing.JLabel estableLabel;
    private javax.swing.JLabel finLabel;
    private javax.swing.JLabel fpubLabel;
    private javax.swing.JLabel inicioLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jdkLabel;
    private javax.swing.JLabel jreLabel;
    private javax.swing.JLabel propLabel;
    private javax.swing.JLabel pubLabel;
    private javax.swing.JLabel versionLabel;
    private javax.swing.JLabel websiteLabel;
    // End of variables declaration//GEN-END:variables
}