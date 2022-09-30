package menus;
//clases
import clases.guiMediaHandler;
import paneles.modPicPanel3;
import paneles.delDatosPanel3;
import paneles.modDatosPanel3;
//java
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.BorderLayout;

public class menuDatosVentana3 extends javax.swing.JFrame{
    public menuDatosVentana3(){
        initComponents();
        new guiMediaHandler(menuDatosVentana3.class.getName()).LookAndFeel(menuDatosVentana3.this);
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    protected int codigo;
    
    public menuDatosVentana3(int code){
        initComponents();
        new guiMediaHandler(menuDatosVentana3.class.getName()).LookAndFeel(menuDatosVentana3.this);
        
        botones();
        settings();
        
        this.codigo=code;
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    public menuDatosVentana3(Component panel,int code){
        initComponents();
        new guiMediaHandler(menuDatosVentana3.class.getName()).LookAndFeel(menuDatosVentana3.this);
        
        botones();
        settings();
        
        this.codigo=code;
        
        openPanel(panel);
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
        pack();
    }
    
    public menuDatosVentana3(Component panel,boolean flag){
        initComponents();
        new guiMediaHandler(menuDatosVentana3.class.getName()).LookAndFeel(menuDatosVentana3.this);
        
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
        backButton.setToolTipText("Regresar al formulario");
    }
    
    protected final void botones(){
        backButton.addActionListener((a)->{
            setVisible(false);
            dispose();
        });
        
        miDelData.addActionListener((a)->{
            if(codigo!=0){
                openPanel(new delDatosPanel3(codigo));
            }else{
                openPanel(new delDatosPanel3());
            }
        });
        
        miModData.addActionListener((a)->{
            if(codigo!=0){
                openPanel(new modDatosPanel3(codigo));
            }else{
                openPanel(new modDatosPanel3());
            }
        });
        
        miModPic.addActionListener((a)->{
            if(codigo!=0){
                openPanel(new modPicPanel3(codigo));
            }else{
                openPanel(new modPicPanel3());
            }
        });
    }
    
    protected void openPanel(Component panel){
        EventQueue.invokeLater(()->{
            menuDatosVentana3.this.getContentPane().setLayout(new BorderLayout());
            menuDatosVentana3.this.getContentPane().add(panel,BorderLayout.CENTER);
            menuDatosVentana3.this.pack();
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
        setIconImage(new guiMediaHandler(menuDatosVentana3.class.getName()).getIconImage());

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
    
    public static void main(String args[]){
        EventQueue.invokeLater(()->{
            new menuDatosVentana3().setVisible(true);
        });
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