package menus;
//clases
import clases.MediaHandler;
import clases.mvc.Controlador;
import paneles.modPicPanel3;
import paneles.delDatosPanel3;
import paneles.modDatosPanel3;
//java
import java.awt.Component;
import java.awt.EventQueue;

public class menuDatosVentana3 extends javax.swing.JFrame{
    public menuDatosVentana3(){
        initComponents();
        new MediaHandler(menuDatosVentana3.class.getName()).setLookAndFeel(menuDatosVentana3.this);
        
        botones();
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    protected int codigo;
    
    protected Controlador modelo;
    
    public menuDatosVentana3(Controlador modelo){
        initComponents();
        new MediaHandler(menuDatosVentana3.class.getName()).setLookAndFeel(menuDatosVentana3.this);
        
        this.modelo=modelo;
        
        botones();
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    public menuDatosVentana3(int code,Controlador modelo){
        initComponents();
        new MediaHandler(menuDatosVentana3.class.getName()).setLookAndFeel(menuDatosVentana3.this);
        
        this.codigo=code;
        this.modelo=modelo;
        
        botones();
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    public menuDatosVentana3(Component panel,int code,Controlador modelo){
        initComponents();
        new MediaHandler(menuDatosVentana3.class.getName()).setLookAndFeel(menuDatosVentana3.this);
        
        this.codigo=code;
        this.modelo=modelo;
        
        botones();
        
        MediaHandler.openPanel(menuDatosVentana3.this,panel);
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    public menuDatosVentana3(Component panel,boolean flag,Controlador modelo){
        initComponents();
        new MediaHandler(menuDatosVentana3.class.getName()).setLookAndFeel(menuDatosVentana3.this);
        
        this.modelo=modelo;
        
        botones();
        
        if(!flag){
            jMenu1.setVisible(false);
        }
        
        MediaHandler.openPanel(menuDatosVentana3.this,panel);
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    protected final void botones(){
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        miDelData.addActionListener(a->{
            if(codigo!=0){
                MediaHandler.openPanel(menuDatosVentana3.this,new delDatosPanel3(codigo,modelo));
            }else{
                MediaHandler.openPanel(menuDatosVentana3.this,new delDatosPanel3(modelo));
            }
        });
        
        miModData.addActionListener(a->{
            if(codigo!=0){
                MediaHandler.openPanel(menuDatosVentana3.this,new modDatosPanel3(codigo,modelo));
            }else{
                MediaHandler.openPanel(menuDatosVentana3.this,new modDatosPanel3(modelo));
            }
        });
        
        miModPic.addActionListener(a->{
            if(codigo!=0){
                MediaHandler.openPanel(menuDatosVentana3.this,new modPicPanel3(codigo,modelo));
            }else{
                MediaHandler.openPanel(menuDatosVentana3.this,new modPicPanel3(modelo));
            }
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
        setIconImage(new MediaHandler(menuDatosVentana3.class.getName()).getIconImage());

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
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new menuDatosVentana3().setVisible(true)
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