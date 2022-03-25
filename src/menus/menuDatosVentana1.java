package menus;
//clases
import clases.Icono;
import clases.logger;
import paneles.delDatosPanel1;
import paneles.modDatosPanel1;
//java
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
//extension larga
import java.util.logging.Level;

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
            new logger().logStaticSaver("Error CNFE: "+e.getMessage()+".\nOcurrió en la clase '"+menuDatosVentana1.class.getName()+"', en el método 'menuDatosVentana1()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana1.class.getName(),Level.WARNING,"menuDatosVentana1-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IE: "+x.getMessage()+".\nOcurrió en la clase '"+menuDatosVentana1.class.getName()+"', en el método 'menuDatosVentana1()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana1.class.getName(),Level.WARNING,"menuDatosVentana1-IE",x.fillInStackTrace());
        }catch(IllegalAccessException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IAE: "+n.getMessage()+".\nOcurrió en la clase '"+menuDatosVentana1.class.getName()+"', en el método 'menuDatosVentana1()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana1.class.getName(),Level.WARNING,"menuDatosVentana1-IAE",n.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 28: "+y.getMessage()+".\nOcurrió en la clase '"+menuDatosVentana1.class.getName()+"', en el método 'menuDatosVentana1()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana1.class.getName(),Level.WARNING,"menuDatosVentana1-28",y.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+menuDatosVentana1.class.getName()+"', en el método 'menuDatosVentana1()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana1.class.getName(),Level.WARNING,"menuDatosVentana1-0",k.fillInStackTrace());
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+s.getMessage()+".\nOcurrió en la clase '"+menuDatosVentana1.class.getName()+"', en el método 'menuDatosVentana1()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana1.class.getName(),Level.WARNING,"menuDatosVentana1-1IO",s.fillInStackTrace());
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+d.getMessage()+".\nOcurrió en la clase '"+menuDatosVentana1.class.getName()+"', en el método 'menuDatosVentana1()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana1.class.getName(),Level.WARNING,"menuDatosVentana1-2IO",d.fillInStackTrace());
        }
        
        botones();
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
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
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miDelData = new javax.swing.JMenuItem();
        miModData = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Icono().getIconImage());

        backButton.setText("Regresar");

        jMenu1.setText("Ventana");

        miDelData.setText("Eliminar datos");
        jMenu1.add(miDelData);

        miModData.setText("Cambiar datos");
        jMenu1.add(miModData);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(399, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(319, Short.MAX_VALUE)
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
    // End of variables declaration//GEN-END:variables
}