package menus;
//clases
import clases.MediaHandler;
import paneles.modPicPanel1;
import paneles.delDatosPanel1;
import paneles.modDatosPanel1;
//java
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.BorderLayout;

public class submenuDatosVentana1 extends javax.swing.JDialog{
    public submenuDatosVentana1(java.awt.Frame parent,boolean modal){
        super(parent, modal);
        initComponents();
        new MediaHandler(menuDatosVentana1.class.getName()).LookAndFeel(submenuDatosVentana1.this);
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    protected int codigo;
    
    public submenuDatosVentana1(java.awt.Frame parent,boolean modal,int code){
        super(parent,modal);
        initComponents();
        new MediaHandler(menuDatosVentana1.class.getName()).LookAndFeel(submenuDatosVentana1.this);
        
        botones();
        settings();
        
        this.codigo=code;
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    protected final void settings(){
    }
    
    protected final void botones(){
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        miDelData.addActionListener(a->{
            if(codigo!=0){
                openPanel(new delDatosPanel1(codigo));
            }else{
                openPanel(new delDatosPanel1());
            }
        });
         
        miModData.addActionListener(a->{
            if(codigo!=0){
                openPanel(new modDatosPanel1(codigo));
            }else{
                openPanel(new modDatosPanel1());
            }
        });
        
        miModPic.addActionListener(a->{
            if(codigo!=0){
                openPanel(new modPicPanel1(codigo));
            }else{
                openPanel(new modPicPanel1());
            }
        });
    }
    
    protected void openPanel(Component panel){
        EventQueue.invokeLater(()->{
            submenuDatosVentana1.this.getContentPane().setLayout(new BorderLayout());
            submenuDatosVentana1.this.getContentPane().add(panel,BorderLayout.CENTER);
            submenuDatosVentana1.this.pack();
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
        miModPic = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new MediaHandler(submenuDatosVentana1.class.getName()).getIconImage());

        backButton.setText("Regresar");

        jMenu1.setText("Ventana");

        miDelData.setText("Eliminar datos");
        jMenu1.add(miDelData);

        miModData.setText("Cambiar datos");
        jMenu1.add(miModData);

        miModPic.setText("Cambiar foto");
        jMenu1.add(miModPic);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(409, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(400, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new submenuDatosVentana1(new javax.swing.JFrame(),true).setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem miDelData;
    private javax.swing.JMenuItem miModData;
    private javax.swing.JMenuItem miModPic;
    // End of variables declaration//GEN-END:variables
}