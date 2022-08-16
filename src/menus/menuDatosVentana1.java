package menus;

import clases.logger;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import paneles.delDatosPanel1;
import paneles.modDatosPanel1;
import paneles.webcamPanel1;

public class menuDatosVentana1 extends javax.swing.JFrame{
    public menuDatosVentana1(){
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error CNFE: "+e.getMessage()+" en 'menuDatosVentana1()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana1.class.getName(),Level.WARNING,"menuDatosVentana1-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IE: "+x.getMessage()+" en 'menuDatosVentana1()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana1.class.getName(),Level.WARNING,"menuDatosVentana1-IE",x.fillInStackTrace());
        }catch(IllegalAccessException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IAE: "+ñ.getMessage()+" en 'menuDatosVentana1()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana1.class.getName(),Level.WARNING,"menuDatosVentana1-IAE",ñ.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 28: "+k.getMessage()+" en 'menuDatosVentana1()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana1.class.getName(),Level.WARNING,"menuDatosVentana1-28",k.fillInStackTrace());
        }catch(FileNotFoundException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+y.getMessage()+" en 'menuDatosVentana1()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana1.class.getName(),Level.WARNING,"menuDatosVentana1-1IO",y.fillInStackTrace());
        }catch(IOException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+s.getMessage()+" en 'menuDatosVentana1()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana1.class.getName(),Level.WARNING,"menuDatosVentana1-2IO",s.fillInStackTrace());
        }
        
        botones();
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
    }
    
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
            new logger().logStaticSaver("Error 1IO: "+e.getMessage()+" en 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana1.class.getName(),Level.WARNING,"getIconImage-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+x.getMessage()+" en 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana1.class.getName(),Level.WARNING,"getIconImage-2IO",x.fillInStackTrace());
        }
        return retValue;
    }
    
    protected final void botones(){
        backButton.setToolTipText("Regresar al formulario");
        backButton.addActionListener((e)->{
            setVisible(false);
            dispose();
        });
        
        miDelData.addActionListener((e)->{
            delDatosPanel1 ddp1=new delDatosPanel1();
            ddp1.setVisible(true);
            menuDatosVentana1.this.getContentPane().setLayout(new BorderLayout());
            menuDatosVentana1.this.getContentPane().add(ddp1,BorderLayout.CENTER);
        });
        
        miModData.addActionListener((e)->{
            modDatosPanel1 mdp1=new modDatosPanel1();
            mdp1.setVisible(true);
            menuDatosVentana1.this.getContentPane().setLayout(new BorderLayout());
            menuDatosVentana1.this.getContentPane().add(mdp1,BorderLayout.CENTER);
        });
        
        miWebcam.addActionListener((e)->{
            webcamPanel1 wcmp1=new webcamPanel1();
            wcmp1.setVisible(true);
            menuDatosVentana1.this.getContentPane().setLayout(new BorderLayout());
            menuDatosVentana1.this.getContentPane().add(wcmp1,BorderLayout.CENTER);
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miDelData = new javax.swing.JMenuItem();
        miModData = new javax.swing.JMenuItem();
        miWebcam = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());

        backButton.setText("Regresar");

        jMenu1.setText("Ventana");

        miDelData.setText("Eliminar datos");
        jMenu1.add(miDelData);

        miModData.setText("Cambiar datos");
        jMenu1.add(miModData);

        miWebcam.setText("Webcam");
        jMenu1.add(miWebcam);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(349, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(269, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]){
        new menuDatosVentana1().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem miDelData;
    private javax.swing.JMenuItem miModData;
    private javax.swing.JMenuItem miWebcam;
    // End of variables declaration//GEN-END:variables
}