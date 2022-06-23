package venPrimarias;
//clases
import clases.Icono;
import clases.laf;
import paneles.databaseConfig;
import paneles.databaseExport;
import paneles.databaseImport;
import paneles.partDataRestore;
import paneles.provDataRestore;
import paneles.workerDataRestore;
//java
import java.awt.BorderLayout;

public class adminTools extends javax.swing.JFrame{
    public adminTools(){
        initComponents();
        new laf().LookAndFeel(adminTools.this,adminTools.class.getName(),"adminTools");
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Herramientas de administrador");
        setResizable(false);
    }
    
    protected void settings(){
        jMenuItem5.setEnabled(false);
        jMenuItem6.setEnabled(false);
    }
    
    protected final void botones(){
        backButton.addActionListener((a)->{
            setVisible(false);
            dispose();
        });
        
        jMenuItem1.addActionListener((a)->{
            this.getContentPane().setLayout(new BorderLayout());
            this.getContentPane().add(new databaseConfig(),BorderLayout.CENTER);
        });
        
        jMenuItem2.addActionListener((a)->{
            this.getContentPane().setLayout(new BorderLayout());
            this.getContentPane().add(new workerDataRestore(),BorderLayout.CENTER);
        });
        
        jMenuItem3.addActionListener((a)->{
            this.getContentPane().setLayout(new BorderLayout());
            this.getContentPane().add(new partDataRestore(),BorderLayout.CENTER);
        });
        
        jMenuItem4.addActionListener((a)->{
            this.getContentPane().setLayout(new BorderLayout());
            this.getContentPane().add(new provDataRestore(),BorderLayout.CENTER);
        });
        
        jMenuItem5.addActionListener((a)->{
            this.getContentPane().setLayout(new BorderLayout());
            this.getContentPane().add(new databaseImport(),BorderLayout.CENTER);
        });
        
        jMenuItem6.addActionListener((a)->{
            this.getContentPane().setLayout(new BorderLayout());
            this.getContentPane().add(new databaseExport(),BorderLayout.CENTER);
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Icono().getIconImage());

        backButton.setText("Regresar");

        jMenu1.setText("Base de datos");

        jMenuItem1.setText("Configurar BD");
        jMenu1.add(jMenuItem1);

        jMenuItem5.setText("Importar BD");
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText("Exportar BD");
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Backup");

        jMenuItem2.setText("Empleados");
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Socios");
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Proveedores");
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(339, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(259, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]){
        new adminTools().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    // End of variables declaration//GEN-END:variables
}