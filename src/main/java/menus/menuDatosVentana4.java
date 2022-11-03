package menus;
//clases
import clases.GuiMediaHandler;
import paneles.delDatosPanel4;
import paneles.modDatosPanel4;
//java
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.BorderLayout;

public class menuDatosVentana4 extends javax.swing.JFrame{
    public menuDatosVentana4(){
        initComponents();
        new GuiMediaHandler(menuDatosVentana4.class.getName()).LookAndFeel(menuDatosVentana4.this);
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    protected int codigo;
    
    public menuDatosVentana4(int code){
        initComponents();
        new GuiMediaHandler(menuDatosVentana4.class.getName()).LookAndFeel(menuDatosVentana4.this);
        
        botones();
        settings();
        
        this.codigo=code;
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    public menuDatosVentana4(Component panel,int code){
        initComponents();
        new GuiMediaHandler(menuDatosVentana4.class.getName()).LookAndFeel(menuDatosVentana4.this);
        
        botones();
        settings();
        
        this.codigo=code;
        
        openPanel(panel);
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    public menuDatosVentana4(Component panel,boolean flag){
        initComponents();
        new GuiMediaHandler(menuDatosVentana4.class.getName()).LookAndFeel(menuDatosVentana4.this);
        
        botones();
        settings();
        
        if(!flag){
            jMenu1.setVisible(false);
        }
        
        openPanel(panel);
        
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
                openPanel(new delDatosPanel4(codigo));
            }else{
                openPanel(new delDatosPanel4());
            }
        });
        
        miModData.addActionListener(a->{
            if(codigo!=0){
                openPanel(new modDatosPanel4(codigo));
            }else{
                openPanel(new modDatosPanel4());
            }
        });
    }
    
    protected void openPanel(Component panel){
        EventQueue.invokeLater(()->{
            menuDatosVentana4.this.getContentPane().setLayout(new BorderLayout());
            menuDatosVentana4.this.getContentPane().add(panel,BorderLayout.CENTER);
            menuDatosVentana4.this.pack();
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
        setIconImage(new GuiMediaHandler(menuDatosVentana4.class.getName()).getIconImage());

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