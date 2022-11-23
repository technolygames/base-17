package venPrimarias;
//clases
import clases.Datos;
import clases.MediaHandler;
import clases.logger;
import clases.DisplayNotification;
import venSecundarias.loadWindow;
//java
import java.awt.Image;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.time.Period;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.TrayIcon.MessageType;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;

public final class start extends javax.swing.JFrame{
    public start(){
        initComponents();
        new MediaHandler(start.class.getName()).LookAndFeel(start.this);
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Inicio");
        setResizable(false);
        pack();
    }
    
    protected String methodName;
    
    
    
    protected JTextField campos;
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    public static int userID;
    
    public static String nameUser;
    public static String role;
    
    protected final void settings(){
        methodName="settings";
        
        try{
            nameLabel.setText(new MediaHandler(start.class.getName()).getProgramName());
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,e,start.class.getName(),methodName,"1IO");
        }catch(IOException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,x,start.class.getName(),methodName,"2IO");
        }
        
        picLabel.setIcon(new ImageIcon(new ImageIcon(new MediaHandler(start.class.getName()).getFormImage()).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
    }
    
    protected final void botones(){
        closeButton.addActionListener(a->{
            System.exit(0);
            dispose();
        });
        
        loginButton.addActionListener(a->
            login()
        );
        
        txtPassword.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    login();
                }
            }
        });
    }
    
    protected void login(){
        methodName="login";
        String user=txtUsuario.getText();
        String pass=String.valueOf(txtPassword.getPassword());
        var datos=new Datos();
        
        for(JTextField tf:new JTextField[]{txtUsuario,txtPassword}){
            campos=tf;
        }
        
        try{
            if(!campos.getText().isEmpty()){
                datos.actualizarDatosLogin(pass,user);
                rs=datos.login(pass,user);
                if(rs.next()){
                    new loadWindow().setVisible(true);
                    dispose();
                    nameUser=rs.getString("nombre_emp");
                    userID=rs.getInt("codigo_emp");
                    role=rs.getString("puesto");
                    
                    /*revisar la edad del empleado*/{
                        String fn=rs.getString("fecha_nacimiento");
                        int edad1=rs.getInt("edad");
                        String edad2=String.valueOf(Period.between(LocalDate.parse(fn,DateTimeFormatter.ofPattern("yyyy-MM-dd")),LocalDate.now()).getYears());
                        
                        if(!edad2.equals(String.valueOf(edad1))){
                            new logger(Level.INFO).staticLogger("no es igual");
                            datos.actualizarDatosInteger("empleados","edad","codigo_emp",Integer.parseInt(edad2),userID,false);
                        }else{
                            new logger(Level.INFO).staticLogger("es igual");
                        }
                    }
                    
                    /*revisar los años de servicio en ese negocio*/{
                        String fi=rs.getString("fecha_registro");
                        int exp=rs.getInt("experiencia");
                        String as=String.valueOf(Period.between(LocalDate.parse(fi,DateTimeFormatter.ofPattern("yyyy-MM-dd")),LocalDate.now()).getYears());
                        if(!as.equals(String.valueOf(exp))){
                            new logger(Level.INFO).staticLogger("no es igual");
                            datos.actualizarDatosInteger("empleados","experiencia","codigo_emp",Integer.parseInt(as),userID,false);
                        }else{
                            new logger(Level.INFO).staticLogger("es igual");
                        }
                    }
                    
                    /*revisar registros en conteo*/{
                        ps=datos.getConnection().prepareStatement("select * from conteo where codigo_emp=? and fecha_sesion=?;");
                        ps.setInt(1,userID);
                        ps.setString(2,LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
                        ResultSet rs2=ps.executeQuery();
                        if(!rs2.next()){
                            new logger(Level.INFO).staticLogger("1; no hay");
                            new Datos().insertarDatosConteo(rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"));
                        }else{
                            new logger(Level.INFO).staticLogger("2; si hay");
                        }
                    }
                    
                    new DisplayNotification().trayNotify("Inicio de sesión","Bienvenido, "+nameUser,MessageType.INFO);
                    new logger(Level.INFO).staticLogger("Inicio de sesión correcto.\nOcurrió en la clase '"+start.class.getName()+"', en el método 'login()'.\nUsuario logeado: "+userID);
                }else{
                    new logger(Level.WARNING).storeAndViewError18(this,start.class.getName(),methodName);
                }
                
                ps.close();
                rs.close();
            }else{
                new logger(Level.WARNING).storeAndViewError14(this,start.class.getName(),methodName);
            }
        }catch(SQLException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,e,start.class.getName(),methodName,"9");
        }catch(NullPointerException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,x,start.class.getName(),methodName,"0");
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
        setIconImage(new MediaHandler(start.class.getName()).getIconImage());

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
        EventQueue.invokeLater(()->
            new start().setVisible(true)
        );
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