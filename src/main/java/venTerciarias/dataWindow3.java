package venTerciarias;
//clases
import clases.Datos;
import clases.Dirs;
import clases.MediaHandler;
import clases.logger;
import clases.Thread2;
import clases.backuphandler.EscritorJson;
import clases.mvc.Controlador;
//java
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
//extension larga
import java.util.logging.Level;

public class dataWindow3 extends javax.swing.JDialog{
    public dataWindow3(java.awt.Frame parent,boolean modal){
        super(parent, modal);
        initComponents();
        new MediaHandler(dataWindow3.class.getName()).setLookAndFeel(dataWindow3.this);
        
        botones();
        datosMostrar();
    }
    
    protected int codigo;
    
    protected Controlador modelo;
    
    public dataWindow3(java.awt.Frame parent, boolean modal, int code, Controlador modelo){
        super(parent, modal);
        initComponents();
        new MediaHandler(dataWindow3.class.getName()).setLookAndFeel(dataWindow3.this);
        
        this.codigo=code;
        this.modelo=modelo;
        
        botones();
        datosMostrar();
    }
    
    protected String methodName;
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    protected final void settings(){
        MediaHandler.load(dataWindow3.this,"Datos del empleado",false);
    }
    
    protected final void datosMostrar(){
        methodName="datosMostrar";
        
        try{
            ps=new Datos(modelo).getConnection().prepareStatement("select * from proveedor where codigo_prov=?;");
            ps.setInt(1,codigo);
            rs=ps.executeQuery();
            if(rs.next()){
                etiCodigo.setText(String.valueOf(rs.getInt("codigo_prov")));
                etiNombre.setText(rs.getString("nombre_prov"));
                etiApellidoP.setText(rs.getString("apellidop_prov"));
                etiApellidoM.setText(rs.getString("apellidom_prov"));
                etiEmpresa.setText(rs.getString("empresa"));
                etiContacto.setText(String.valueOf(rs.getInt("contacto")));
                etiIngreso.setText(rs.getString("fecha_ingreso"));
                etiUEntrega.setText(rs.getString("fecha_uentrega"));
                
                new EscritorJson(modelo).writeDataProviderJson(Integer.parseInt(etiCodigo.getText()));
                
                etiFoto.setIcon(new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(rs.getBytes("foto"))).getImage().getScaledInstance(etiFoto.getWidth(),etiFoto.getHeight(),Image.SCALE_DEFAULT)));
            }else{
                new logger(Level.WARNING,this.getClass().getName()).storeError14(this,methodName);
            }
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"14");
        }catch(NullPointerException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,x,methodName,"0");
        }
    }
    
    protected final void botones(){
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        storeImgButton.addActionListener(a->{
            methodName="botones.storeImg";
            try{
                int codigo1=Integer.parseInt(etiCodigo.getText());
                String nombre=etiNombre.getText();
                
                ps=new Datos(modelo).getConnection().prepareStatement("select foto from proveedor where codigo_prov=?;");
                ps.setInt(1,codigo1);
                rs=ps.executeQuery();
                
                File f=new File("data/media/dataImage/Proveedor",nombre+"-"+codigo1+".jpg");
                
                new Thread2(rs,new FileOutputStream(Dirs.exists(f))).run();
                
                logger.staticLogger(Level.INFO,"Se guardó correctamente la imagen del proveedor.\nOcurrió en la clase '"+dataWindow3.class.getName()+"', en el método 'botones(storeImgButton)'.\nUsuario que hizo la acción: "+String.valueOf(modelo.getUserID()),this.getClass().getName());
                
                ps.close();
            }catch(SQLException e){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"14");
            }catch(FileNotFoundException x){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,x,methodName,"1IO");
            }catch(NullPointerException n){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,n,methodName,"0");
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        storeImgButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        etiFoto = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        etiCodigo = new javax.swing.JLabel();
        etiNombre = new javax.swing.JLabel();
        etiApellidoP = new javax.swing.JLabel();
        etiApellidoM = new javax.swing.JLabel();
        etiEmpresa = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        etiContacto = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        etiIngreso = new javax.swing.JLabel();
        etiUEntrega = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new MediaHandler(dataWindow3.class.getName()).getIconImage());

        storeImgButton.setText("Guardar imagen");

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Datos");

        etiFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        backButton.setText("Regresar");

        jLabel6.setText("Empresa:");

        etiCodigo.setText(" ");
        etiCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiNombre.setText(" ");
        etiNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiApellidoP.setText(" ");
        etiApellidoP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiApellidoM.setText(" ");
        etiApellidoM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiEmpresa.setText(" ");
        etiEmpresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Código del proveedor:");

        jLabel3.setText("Nombre del proveedor:");

        jLabel4.setText("Apellido paterno:");

        jLabel5.setText("Apellido materno:");

        etiContacto.setText(" ");
        etiContacto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Contacto:");

        etiIngreso.setText(" ");
        etiIngreso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiUEntrega.setText(" ");
        etiUEntrega.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setText("Fecha de ingreso:");

        jLabel9.setText("Fecha de última entrega:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(etiApellidoM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(etiApellidoP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(etiCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(etiNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(etiEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiUEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(storeImgButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(5, 5, 5)
                        .addComponent(etiFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etiCodigo)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etiNombre)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(etiApellidoP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(etiApellidoM))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(etiEmpresa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(etiContacto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiIngreso)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiUEntrega)
                            .addComponent(jLabel9))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(storeImgButton)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new dataWindow3(new javax.swing.JFrame(),true).setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel etiApellidoM;
    private javax.swing.JLabel etiApellidoP;
    private javax.swing.JLabel etiCodigo;
    private javax.swing.JLabel etiContacto;
    private javax.swing.JLabel etiEmpresa;
    private javax.swing.JLabel etiFoto;
    private javax.swing.JLabel etiIngreso;
    private javax.swing.JLabel etiNombre;
    private javax.swing.JLabel etiUEntrega;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton storeImgButton;
    // End of variables declaration//GEN-END:variables
}