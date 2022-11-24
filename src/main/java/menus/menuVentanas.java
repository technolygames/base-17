package menus;
//clases
import clases.MediaHandler;
import clases.logger;
import clases.Validation;
import clases.DisplayNotification;
import java.awt.EventQueue;
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
        new MediaHandler(menuVentanas.class.getName()).setLookAndFeel(menuVentanas.this);
        
        menu();
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Ventana principal");
        setResizable(false);
        pack();
    }
    
    protected Properties p;
    
    protected String rol=start.role;
    
    protected final void settings(){
        String nombre=start.nameUser;
        jMenuItem2.setText(nombre);
        picLabel.setIcon(new ImageIcon(new ImageIcon(new MediaHandler(menuVentanas.class.getName()).getFormImage()).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
    }
    
    protected final void botones(){
        offButton.addActionListener(a->
            new ventana3().setVisible(true)
        );
        
        productButton.addActionListener(a->
            new ventana1().setVisible(true)
        );
        
        storeButton.addActionListener(a->
            new ventana2().setVisible(true)
        );
        
        form1Button.addActionListener(a->
            new Validation(new formulario1(),rol,formulario1.class.getName()).toRestrictedForm()
        );
        
        form2Button.addActionListener(a->
            new Validation(new formulario2(),rol,formulario2.class.getName()).toRestrictedForm()
        );
        
        form3Button.addActionListener(a->
            new Validation(new formulario3(),rol,formulario3.class.getName()).toRestrictedForm()
        );
        
        ltprvButton.addActionListener(a->
            new Validation(new ltshProviders(),rol,ltshProviders.class.getName()).toRestrictedForm()
        );
        
        ltpsButton.addActionListener(a->
            new Validation(new ltshPartners(),rol,ltshPartners.class.getName()).toRestrictedForm()
        );
        
        ltwkButton.addActionListener(a->
            new Validation(new ltshWorkers(),rol,ltshWorkers.class.getName()).toRestrictedForm()
        );
        
        ltstButton.addActionListener(a->
            new ltshStorage().setVisible(true)
        );
        
        ltshButton.addActionListener(a->
            new ltshProduct().setVisible(true)
        );
        
        ltoffButton.addActionListener(a->
            new ltshOff().setVisible(true)
        );
        
        closeButton.addActionListener(a->{
            switch(JOptionPane.showConfirmDialog(this,"¿Deseas cerrar el programa?","Notice 1",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE)){
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
        aboutButton.addActionListener(a->
            new about(new javax.swing.JFrame(),true).setVisible(true)
        );
        
        jMenuItem2.addActionListener(a->
            new dataWindow1(new javax.swing.JFrame(),true,start.userID).setVisible(true)
        );
        
        jMenuItem3.addActionListener(a->
            logout()
        );
        
        properButton.addActionListener(a->
            new proper1().setVisible(true)
        );
    }
    
    protected void logout(){
        new start().setVisible(true);
        new DisplayNotification().trayNotify("Has cerrado sesión","Hasta luego, "+jMenuItem2.getText(),MessageType.INFO);
        new logger(Level.INFO).staticLogger("Sesión finalizada.\nOcurrió en la clase '"+menuVentanas.class.getName()+"', en el método 'logout()'.\nUsuario que terminó sesión: "+jMenuItem2.getText());
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
        setIconImage(new MediaHandler(menuVentanas.class.getName()).getIconImage());

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ltwkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ltstButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ltshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ltprvButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ltpsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ltoffButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(form1Button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(offButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(form3Button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(form2Button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(form3Button)
                            .addComponent(ltprvButton))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(form2Button)
                            .addComponent(ltpsButton))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(form1Button)
                            .addComponent(ltwkButton))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(storeButton)
                            .addComponent(ltstButton))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(productButton)
                            .addComponent(ltshButton))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(offButton)
                            .addComponent(ltoffButton)
                            .addComponent(closeButton))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new menuVentanas().setVisible(true)
        );
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
    public static javax.swing.JLabel picLabel;
    private javax.swing.JButton productButton;
    private javax.swing.JMenuItem properButton;
    private javax.swing.JButton storeButton;
    // End of variables declaration//GEN-END:variables
}