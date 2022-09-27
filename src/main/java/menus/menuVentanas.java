package menus;
//clases
import clases.guiMediaHandler;
import clases.logger;
import clases.validation;
import clases.win10Notification;
import venPrimarias.formulario1;
import venPrimarias.formulario2;
import venPrimarias.formulario3;
import venPrimarias.ltshOff;
import venPrimarias.ltshPartners;
import venPrimarias.ltshProduct;
import venPrimarias.ltshProviders;
import venPrimarias.ltshStorage;
import venPrimarias.ltshWorkers;
import venPrimarias.proper1;
import venPrimarias.start;
import venPrimarias.ventana1;
import venPrimarias.ventana2;
import venPrimarias.ventana3;
import venTerciarias.about;
import venTerciarias.dataWindow1;
//java
import java.awt.Image;
import java.util.Properties;
import javax.swing.ImageIcon;
//extension larga
import java.util.logging.Level;
import java.awt.TrayIcon.MessageType;
import javax.swing.JOptionPane;

public final class menuVentanas extends javax.swing.JFrame{
    public menuVentanas(){
        initComponents();
        new guiMediaHandler(menuVentanas.class.getName()).LookAndFeel(menuVentanas.this);
        
        menu();
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Ventana principal");
        setResizable(false);
        pack();
    }
    
    protected Properties p;
    
    protected void settings(){
        String nombre=start.nameUser;
        jMenuItem2.setText(nombre);
        picLabel.setIcon(new ImageIcon(new ImageIcon(new guiMediaHandler(start.class.getName()).getFormImage()).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
    }
    
    protected final void botones(){
        offButton.addActionListener((a)->{
            new ventana3().setVisible(true);
        });
        
        productButton.addActionListener((a)->{
            new ventana1().setVisible(true);
        });
        
        storeButton.addActionListener((a)->{
            new ventana2().setVisible(true);
        });
        
        form1Button.addActionListener((a)->{
            new validation(new formulario1(),start.role,formulario1.class.getName()).toRestrictedForm();
        });
        
        form2Button.addActionListener((a)->{
            new validation(new formulario2(),start.role,formulario2.class.getName()).toRestrictedForm();
        });
        
        form3Button.addActionListener((a)->{
            new validation(new formulario3(),start.role,formulario3.class.getName()).toRestrictedForm();
        });
        
        ltprvButton.addActionListener((a)->{
            new validation(new ltshProviders(),start.role,ltshProviders.class.getName()).toRestrictedForm();
        });
        
        ltpsButton.addActionListener((a)->{
            new validation(new ltshPartners(),start.role,ltshPartners.class.getName()).toRestrictedForm();
        });
        
        ltwkButton.addActionListener((a)->{
            new validation(new ltshWorkers(),start.role,ltshWorkers.class.getName()).toRestrictedForm();
        });
        
        ltstButton.addActionListener((a)->{
            new ltshStorage().setVisible(true);
        });
        
        ltshButton.addActionListener((a)->{
            new ltshProduct().setVisible(true);
        });
        
        ltoffButton.addActionListener((a)->{
            new ltshOff().setVisible(true);
        });
        
        closeButton.addActionListener((a)->{
            int i=JOptionPane.showConfirmDialog(null,"¿Deseas cerrar el programa?","Notice 1",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
            switch(i){
                case 0:
                    new logger(Level.OFF).staticLogger("Programa cerrado");
                    System.exit(0);
                    dispose();
                    break;
                case 1:
                    logout();
                    break;
                default:
                    break;
            }
        });
    }
    
    protected final void menu(){
        aboutButton.addActionListener((a)->{
            new about(new javax.swing.JFrame(),true).setVisible(true);
        });
        
        jMenuItem2.addActionListener((a)->{
            new dataWindow1(new javax.swing.JFrame(),true,start.userID).setVisible(true);
        });
        
        jMenuItem3.addActionListener((a)->{
            logout();
        });
        
        properButton.addActionListener((a)->{
            new proper1().setVisible(true);
        });
    }
    
    protected void logout(){
        new start().setVisible(true);
        new win10Notification().trayNotify("Has cerrado sesión","Hasta luego, "+jMenuItem2.getText(),MessageType.INFO);
        new logger(Level.SEVERE).staticLogger("Sesión finalizada.\nOcurrió en la clase '"+menuVentanas.class.getName()+"', en el método 'logout()'.\nUsuario que terminó sesión: "+jMenuItem2.getText());
        dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        ltwkButton = new javax.swing.JButton();
        ltstButton = new javax.swing.JButton();
        ltshButton = new javax.swing.JButton();
        form1Button = new javax.swing.JButton();
        storeButton = new javax.swing.JButton();
        productButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        form2Button = new javax.swing.JButton();
        ltpsButton = new javax.swing.JButton();
        picLabel = new javax.swing.JLabel();
        form3Button = new javax.swing.JButton();
        ltprvButton = new javax.swing.JButton();
        ltoffButton = new javax.swing.JButton();
        offButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        properButton = new javax.swing.JMenuItem();
        aboutButton = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new guiMediaHandler(menuVentanas.class.getName()).getIconImage());

        ltwkButton.setText("Lista de empleados");

        ltstButton.setText("Itinerario");

        ltshButton.setText("Productos vendidos");

        form1Button.setText("Formulario 1");

        storeButton.setText("Almacén");

        productButton.setText("Productos");

        closeButton.setText("Salir");

        form2Button.setText("Formulario 2");

        ltpsButton.setText("Socios");

        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        form3Button.setText("Formulario 3");

        ltprvButton.setText("Lista de proveedores");

        ltoffButton.setText("Lista de descuentos");

        offButton.setText("Descuentos");

        jMenu3.setText("Opciones");

        properButton.setText("Ajustes");
        jMenu3.add(properButton);

        aboutButton.setText("Acerca");
        jMenu3.add(aboutButton);

        jMenuBar1.add(jMenu3);

        jMenu1.setText("Usuario");

        jMenuItem2.setText("jMenuItem2");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Cerrar sesión");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ltstButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ltwkButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ltpsButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ltprvButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ltshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ltoffButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(storeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(productButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(offButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(form1Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(form2Button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(form3Button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ltprvButton)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ltpsButton)
                                    .addComponent(form2Button))
                                .addGap(18, 18, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ltwkButton)
                            .addComponent(form1Button)))
                    .addComponent(form3Button))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ltstButton)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ltshButton)
                            .addComponent(productButton)))
                    .addComponent(storeButton))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ltoffButton)
                    .addComponent(closeButton)
                    .addComponent(offButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        new menuVentanas().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton form1Button;
    private javax.swing.JButton form2Button;
    private javax.swing.JButton form3Button;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    public static javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JButton ltoffButton;
    private javax.swing.JButton ltprvButton;
    private javax.swing.JButton ltpsButton;
    private javax.swing.JButton ltshButton;
    private javax.swing.JButton ltstButton;
    private javax.swing.JButton ltwkButton;
    private javax.swing.JButton offButton;
    private javax.swing.JLabel picLabel;
    private javax.swing.JButton productButton;
    private javax.swing.JMenuItem properButton;
    private javax.swing.JButton storeButton;
    // End of variables declaration//GEN-END:variables
}