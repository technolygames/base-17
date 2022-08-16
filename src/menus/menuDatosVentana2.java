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
import paneles.delDatosPanel2;
import paneles.modDatosPanel2;

public class menuDatosVentana2 extends javax.swing.JFrame{
    public menuDatosVentana2(){
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error CNFE: "+e.getMessage()+" en 'menuDatosVentana2()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana2.class.getName(),Level.WARNING,"menuDatosVentana2-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IE: "+x.getMessage()+" en 'menuDatosVentana2()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana2.class.getName(),Level.WARNING,"menuDatosVentana2-IE",x.fillInStackTrace());
        }catch(IllegalAccessException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IAE: "+ñ.getMessage()+" en 'menuDatosVentana2()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana2.class.getName(),Level.WARNING,"menuDatosVentana2-IAE",ñ.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 28: "+k.getMessage()+" en 'menuDatosVentana2()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana2.class.getName(),Level.WARNING,"menuDatosVentana2-28",k.fillInStackTrace());
        }catch(FileNotFoundException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+y.getMessage()+" en 'menuDatosVentana2()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana2.class.getName(),Level.WARNING,"menuDatosVentana2-1IO",y.fillInStackTrace());
        }catch(IOException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+s.getMessage()+" en 'menuDatosVentana2()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana2.class.getName(),Level.WARNING,"menuDatosVentana2-2IO",s.fillInStackTrace());
        }
        
        botones();
        
        setResizable(false);
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
            retValue.flush();;
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+e.getMessage()+" en 'geticonImage()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana2.class.getName(),Level.WARNING,"getIconImage-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+x.getMessage()+" en 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(menuDatosVentana2.class.getName(),Level.WARNING,"getIconImage-2IO",x.fillInStackTrace());
        }
        return retValue;
    }
    
    protected final void botones(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        miDelData.addActionListener((a)->{
           delDatosPanel2 ddp2=new delDatosPanel2(); 
           ddp2.setVisible(true);
           menuDatosVentana2.this.setLayout(new BorderLayout());
           menuDatosVentana2.this.getContentPane().add(ddp2,BorderLayout.CENTER);
        });
        
        miModData.addActionListener((a)->{
            modDatosPanel2 mdp2=new modDatosPanel2();
            mdp2.setVisible(true);
            menuDatosVentana2.this.setLayout(new BorderLayout());
            menuDatosVentana2.this.getContentPane().add(mdp2,BorderLayout.CENTER);
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
        setIconImage(getIconImage());

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(349, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(269, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]){
        new menuDatosVentana2().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem miDelData;
    private javax.swing.JMenuItem miModData;
    // End of variables declaration//GEN-END:variables
}