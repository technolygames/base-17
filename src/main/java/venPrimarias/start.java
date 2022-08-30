package venPrimarias;
//clases
import clases.datos;
import clases.dirs;
import clases.guiMediaHandler;
import clases.logger;
import clases.win10Notification;
import venSecundarias.loadWindow;
//java
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.TrayIcon.MessageType;
import java.nio.charset.StandardCharsets;

public final class start extends javax.swing.JFrame{
    public start(){
        initComponents();
        new guiMediaHandler(start.class.getName()).LookAndFeel(start.this);
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Inicio");
        setResizable(false);
        pack();
    }
    
    protected Properties p;
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    public static int userID;
    
    public static String nameUser;
    public static String role;
    
    protected final void settings(){
        p=new Properties();
        try{
            p.load(new FileReader(dirs.userdir+"/data/config/config.properties",StandardCharsets.UTF_8));
            nameLabel.setText(p.getProperty("nombre"));
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+start.class.getName()+"', en el método 'settings()'");
            new logger(Level.SEVERE).exceptionLogger(start.class.getName(),"settings-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+start.class.getName()+"', en el método 'settings()'");
            new logger(Level.SEVERE).exceptionLogger(start.class.getName(),"settings-2IO",x.fillInStackTrace());
        }
        picLabel.setIcon(new ImageIcon(new ImageIcon(new guiMediaHandler(start.class.getName()).getFormImage()).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
    }
    
    protected final void botones(){
        closeButton.addActionListener((a)->{
            System.exit(0);
            dispose();
        });
        
        loginButton.addActionListener((a)->{
            login();
        });
        
        txtPassword.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    login();
                }
            }
        });
    }
    
    protected final void login(){
        String user=txtUsuario.getText();
        String pass=String.valueOf(txtPassword.getPassword());
        
        try{
            if(!txtUsuario.getText().equals("")||!txtPassword.getPassword().equals("")){
                ps=new datos().getConnection().prepareStatement("select * from empleados where password=? and nombre_emp=? or curp=?;");
                ps.setString(1,pass);
                ps.setString(2,user);
                ps.setString(3,user);
                new datos().actualizarDatos("empleados set fecha_sesion=now() where password='"+pass+"' and nombre_emp='"+user+"' or curp='"+user+"';");
                rs=ps.executeQuery();
                if(rs.next()){
                    new loadWindow().setVisible(true);
                    dispose();
                    nameUser=rs.getString("nombre_emp");
                    userID=rs.getInt("codigo_emp");
                    role=rs.getString("puesto");
                    
                    new datos().insertarDatosConteo(rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"));
                    
                    new win10Notification().trayNotify("Inicio de sesión","Bienvenido, "+nameUser,MessageType.INFO);
                    new logger(Level.INFO).staticLogger("Inicio de sesión correcto.\nOcurrió en la clase '"+start.class.getName()+"', en el método 'login()'.\nUsuario logeado: "+userID);
                }else{
                    JOptionPane.showMessageDialog(null,"Error:\nIngrese correctamente el usuario o contraseña","Error 18",JOptionPane.WARNING_MESSAGE);
                    new logger(Level.WARNING).staticLogger("Error 18: no se ingresaron correctamente el usuario y/o contraseña.\nOcurrió en la clase '"+start.class.getName()+"', en el método 'login()'");
                }
                
                ps.close();
                rs.close();
            }else{
                JOptionPane.showMessageDialog(null,"Error: no existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                new logger(Level.WARNING).staticLogger("Error 14: no existen o no se ingresaron los datos a buscar y cambiar.\nOcurrió en '"+start.class.getName()+"', en el método 'login()'");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 9",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 9: "+e.getMessage()+".\nOcurrió en la clase '"+start.class.getName()+"', en el método 'login()'");
            new logger(Level.SEVERE).exceptionLogger(start.class.getName(),"login-9",e.fillInStackTrace());
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+start.class.getName()+"', en el método 'login()'");
            new logger(Level.SEVERE).exceptionLogger(start.class.getName(),"login-0",x.fillInStackTrace());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField1 = new java.awt.TextField();
        txtUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        picLabel = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();

        textField1.setText("textField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new guiMediaHandler(start.class.getName()).getIconImage());

        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        loginButton.setText("Ingresar");
        loginButton.setToolTipText("");

        closeButton.setText("Salir");

        nameLabel.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N

        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(txtUsuario)))
                            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(loginButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(loginButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        new start().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel picLabel;
    private java.awt.TextField textField1;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}