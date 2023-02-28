package menus;
//clases
import clases.MediaHandler;
import clases.mvc.Controlador;
import paneles.delDatosPanel4;
import paneles.modDatosPanel4;
//java
import java.awt.Component;
import java.awt.EventQueue;

public class menuDatosVentana4 extends javax.swing.JFrame{
    public menuDatosVentana4(){
        initComponents();
        new MediaHandler(menuDatosVentana4.class.getName()).setLookAndFeel(menuDatosVentana4.this);
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    protected int codigo;
    
    protected Controlador modelo;
    
    public menuDatosVentana4(Controlador modelo){
        initComponents();
        new MediaHandler(menuDatosVentana4.class.getName()).setLookAndFeel(menuDatosVentana4.this);
        
        this.modelo=modelo;
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    public menuDatosVentana4(int code,Controlador modelo){
        initComponents();
        new MediaHandler(menuDatosVentana4.class.getName()).setLookAndFeel(menuDatosVentana4.this);
        
        botones();
        settings();
        
        this.codigo=code;
        this.modelo=modelo;
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    public menuDatosVentana4(Component panel,int code,Controlador modelo){
        initComponents();
        new MediaHandler(menuDatosVentana4.class.getName()).setLookAndFeel(menuDatosVentana4.this);
        
        botones();
        settings();
        
        this.codigo=code;
        this.modelo=modelo;
        
        MediaHandler.openPanel(menuDatosVentana4.this,panel);
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    public menuDatosVentana4(Component panel,boolean flag,Controlador modelo){
        initComponents();
        new MediaHandler(menuDatosVentana4.class.getName()).setLookAndFeel(menuDatosVentana4.this);
        
        botones();
        settings();
        
        if(!flag){
            jMenu1.setVisible(false);
        }
        
        this.modelo=modelo;
        
        MediaHandler.openPanel(menuDatosVentana4.this,panel);
        
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
                MediaHandler.openPanel(menuDatosVentana4.this,new delDatosPanel4(codigo,modelo));
            }else{
                MediaHandler.openPanel(menuDatosVentana4.this,new delDatosPanel4(modelo));
            }
        });
        
        miModData.addActionListener(a->{
            if(codigo!=0){
                MediaHandler.openPanel(menuDatosVentana4.this,new modDatosPanel4(codigo,modelo));
            }else{
                MediaHandler.openPanel(menuDatosVentana4.this,new modDatosPanel4(modelo));
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new MediaHandler(menuDatosVentana4.class.getName()).getIconImage());

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
                .addContainerGap(319, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(249, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new menuDatosVentana4().setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem miDelData;
    private javax.swing.JMenuItem miModData;
    // End of variables declaration//GEN-END:variables
}