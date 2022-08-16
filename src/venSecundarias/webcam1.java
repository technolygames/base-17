package venSecundarias;

import clases.logger;
import static venPrimarias.formulario1.picLabel;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import java.util.logging.Level;
import java.awt.image.RenderedImage;

public final class webcam1 extends javax.swing.JDialog{
    public webcam1(java.awt.Frame parent,boolean modal){
        super(parent,modal);
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error CNFE: "+e.getMessage()+".\nOcurrió en la clase '"+webcam1.class.getName()+"', en el método 'webcam1()'",Level.WARNING);
            new logger().exceptionLogger(webcam1.class.getName(),Level.WARNING,"webcam1-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IE: "+x.getMessage()+".\nOcurrió en la clase '"+webcam1.class.getName()+"', en el método 'webcam1()'",Level.WARNING);
            new logger().exceptionLogger(webcam1.class.getName(),Level.WARNING,"webcam1-IE",x.fillInStackTrace());
        }catch(IllegalAccessException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IAE: "+n.getMessage()+".\nOcurrió en la clase '"+webcam1.class.getName()+"', en el método 'webcam1()'",Level.WARNING);
            new logger().exceptionLogger(webcam1.class.getName(),Level.WARNING,"webcam1-IAE",n.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 28: "+y.getMessage()+".\nOcurrió en la clase '"+webcam1.class.getName()+"', en el método 'webcam1()'",Level.WARNING);
            new logger().exceptionLogger(webcam1.class.getName(),Level.WARNING,"webcam1-28",y.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+webcam1.class.getName()+"', en el método 'webcam1()'",Level.WARNING);
            new logger().exceptionLogger(webcam1.class.getName(),Level.WARNING,"webcam1-0",k.fillInStackTrace());
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+s.getMessage()+".\nOcurrió en la clase '"+webcam1.class.getName()+"', en el método 'webcam1()'",Level.WARNING);
            new logger().exceptionLogger(webcam1.class.getName(),Level.WARNING,"webcam1-1IO",s.fillInStackTrace());
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+d.getMessage()+".\nOcurrió en la clase '"+webcam1.class.getName()+"', en el método 'webcam1()'",Level.WARNING);
            new logger().exceptionLogger(webcam1.class.getName(),Level.WARNING,"webcam1-2IO",d.fillInStackTrace());
        }
        
        botones();
        
        setLocationRelativeTo(null);
        setTitle("Webcam");
        
        jWebcam1.setACTIVARCAMARA(true);
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
            new logger().logStaticSaver("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+webcam1.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(webcam1.class.getName(),Level.WARNING,"getIconImage-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+webcam1.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(webcam1.class.getName(),Level.WARNING,"getIconImage-2IO",x.fillInStackTrace());
        }
        return retValue;
    }
    
    protected final void botones(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        picButton.addActionListener((ae)->{
            try{
                ImageIcon ii=new ImageIcon(jWebcam1.getImage());
                Icon i=new ImageIcon(ii.getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT));
                ImageIO.write((RenderedImage)jWebcam1.getImage(),"jpg",new File("src/data/media/webcam/"+Math.random()+".jpg"));
                picLabel.setIcon(i);
            }catch(NullPointerException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 41",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 41: "+e.getMessage()+".\nOcurrió en la clase '"+webcam1.class.getName()+"', en el método 'botones(picButton)'",Level.WARNING);
                new logger().exceptionLogger(webcam1.class.getName(),Level.WARNING,"botones.pic-41",e.fillInStackTrace());
            } catch (IOException x) {
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+webcam1.class.getName()+"', en el método 'botones(picButton)'",Level.WARNING);
                new logger().exceptionLogger(webcam1.class.getName(),Level.WARNING,"botones.pic-0",x.fillInStackTrace());
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        jWebcam1 = new JPanelWebCam.JPanelWebCam();
        picButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());

        backButton.setText("Regresar");

        jWebcam1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Presiona 2 veces a la pantalla de la cámara", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.BELOW_TOP));

        javax.swing.GroupLayout jWebcam1Layout = new javax.swing.GroupLayout(jWebcam1);
        jWebcam1.setLayout(jWebcam1Layout);
        jWebcam1Layout.setHorizontalGroup(
            jWebcam1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jWebcam1Layout.setVerticalGroup(
            jWebcam1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 223, Short.MAX_VALUE)
        );

        picButton.setText("Tomar foto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(picButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addComponent(jWebcam1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jWebcam1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backButton)
                    .addComponent(picButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        new webcam1(new javax.swing.JFrame(),true).setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    public static JPanelWebCam.JPanelWebCam jWebcam1;
    private javax.swing.JButton picButton;
    // End of variables declaration//GEN-END:variables
}