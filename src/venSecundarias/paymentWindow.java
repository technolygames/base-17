package venSecundarias;
//clases
import clases.Icono;
import clases.datos;
import clases.laf;
import clases.logger;
import clases.tickets.datosTicket;
//java
import java.io.FileReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import java.nio.charset.StandardCharsets;
import java.lang.reflect.InaccessibleObjectException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRFontNotFoundException;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class paymentWindow extends javax.swing.JDialog{
    public paymentWindow(){
        initComponents();
        new laf().LookAndFeel(paymentWindow.this,paymentWindow.class.getName(),"paymentWindow");
        
        botones();
        
        setLocationRelativeTo(null);
        setTitle("Pago");
        setResizable(false);
    }
    
    protected Properties p;
    
    protected final void botones(){
        backButton.addActionListener((a)->{
            setVisible(false);
            dispose();
        });
        
        mkPaidButton.addActionListener((a)->{
            int opcion=JOptionPane.showConfirmDialog(null,"¿Deseas imprimir reporte de compra?","Notice 1",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
            switch(opcion){
                case 0:
                    imprimirReporte();
                    new datosTicket().imprimirTicket(null,"",0,"",0);
                case 1:
                    new datosTicket().imprimirTicket(null,"",0,"",0);
            }
        });
    }
    
    protected void imprimirReporte(){
        p=new Properties();
        try{
            p.load(new FileReader(System.getProperty("user.dir")+"/src/data/config/config.properties",StandardCharsets.UTF_8));
            Connection cn=new datos().getConnection();
            Map<String,Object> params=new HashMap<String,Object>(2);
            JasperDesign jd=JRXmlLoader.load(new FileInputStream(System.getProperty("user.dir")+"/src/data/database/Jasper/reportes.jrxml"));
            params.put("codigo_emp","");
            params.put("nombre_reporte",p.getProperty("nombre"));
            JasperReport jr=JasperCompileManager.compileReport(jd);
            JasperPrint jp=JasperFillManager.fillReport(jr,params,cn);
            JasperViewer jv=new JasperViewer(jp);
            jv.viewReport(jp);
            jv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jv.setVisible(true);
            
            cn.close();
        }catch(JRException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 17",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 17: "+e.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'imprimirReporte()'",Level.WARNING);
            new logger().exceptionLogger(paymentWindow.class.getName(),Level.WARNING,"imprimirReporte-17",e.fillInStackTrace());
        }catch(ExceptionInInitializerError x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error EIIE",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error EIIE: "+x.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'imprimirReporte()'",Level.WARNING);
            new logger().exceptionLogger(paymentWindow.class.getName(),Level.WARNING,"imprimirReporte-EIIE",x.fillInStackTrace());
        }catch(NoClassDefFoundError n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error NCDFE",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error NCDFE: "+n.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'imprimirReporte()'",Level.WARNING);
            new logger().exceptionLogger(paymentWindow.class.getName(),Level.WARNING,"imprimirReporte-NCDFE",n.fillInStackTrace());
        }catch(SQLException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 10",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 10: "+k.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'imprimirReporte()'",Level.WARNING);
            new logger().exceptionLogger(paymentWindow.class.getName(),Level.WARNING,"imprimirReporte-10",k.fillInStackTrace());
        }catch(IOException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+s.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'imprimirReporte()'",Level.WARNING);
            new logger().exceptionLogger(paymentWindow.class.getName(),Level.WARNING,"imprimirReporte-2IO",s.fillInStackTrace());
        }catch(JRFontNotFoundException l){
            JOptionPane.showMessageDialog(null,"Error:\n"+l.getMessage(),"Error JRFNFE",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error JRFNFE: "+l.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'imprimirReporte()'",Level.WARNING);
            new logger().exceptionLogger(paymentWindow.class.getName(),Level.WARNING,"imprimirReporte-JRFNFE",l.fillInStackTrace());
        }catch(InaccessibleObjectException r){
            JOptionPane.showMessageDialog(null,"Error:\n"+r.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error IAE: "+r.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'imprimirReporte()'",Level.WARNING);
            new logger().exceptionLogger(paymentWindow.class.getName(),Level.WARNING,"imprimirReporte-IAE",r.fillInStackTrace());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        mkPaidButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Icono().getIconImage());

        backButton.setText("Regresar");

        mkPaidButton.setText("Realizar pago");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mkPaidButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(272, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(mkPaidButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]){
        new paymentWindow().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton mkPaidButton;
    // End of variables declaration//GEN-END:variables
}