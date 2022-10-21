package venPrimarias;
//clases
import clases.guiMediaHandler;
import clases.logger;
import paneles.databaseConfig;
import paneles.databaseExport;
import paneles.databaseImport;
import paneles.partDataRestore;
import paneles.provDataRestore;
import paneles.environmentPanel;
import paneles.workerDataRestore;
//java
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

public class adminTools extends javax.swing.JFrame{
    public adminTools(){
        initComponents();
        new guiMediaHandler(adminTools.class.getName()).LookAndFeel(adminTools.this);
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Herramientas de administrador");
        setResizable(false);
        pack();
    }
    
    protected final void settings(){
        JMenuItem[] items={jMenuItem5,jMenuItem6};
        try{
            Properties p=new Properties();
            p.load(new FileInputStream("data/config/env.properties"));
            if(p.getProperty("local_mysql").isEmpty()){
                for(JMenuItem c:items){
                    c.setEnabled(false);
                }
            }else{
                for(JMenuItem c:items){
                    c.setEnabled(true);
                }
            }
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(this,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+adminTools.class.getName()+"', en el método 'settings()'");
            new logger(Level.SEVERE).exceptionLogger(adminTools.class.getName(),"settings-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(this,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+adminTools.class.getName()+"', en el método 'settings()'");
            new logger(Level.SEVERE).exceptionLogger(adminTools.class.getName(),"settings-2IO",x.fillInStackTrace());
        }
    }
    
    protected final void botones(){
        backButton.addActionListener((a)->{
            setVisible(false);
            dispose();
        });
        
        jMenuItem1.addActionListener((a)->{
            openPanel(new databaseConfig());
        });
        
        jMenuItem2.addActionListener((a)->{
            openPanel(new workerDataRestore());
        });
        
        jMenuItem3.addActionListener((a)->{
            openPanel(new partDataRestore());
        });
        
        jMenuItem4.addActionListener((a)->{
            openPanel(new provDataRestore());
        });
        
        jMenuItem5.addActionListener((a)->{
            openPanel(new databaseImport());
        });
        
        jMenuItem6.addActionListener((a)->{
            openPanel(new databaseExport());
        });
        
        jMenuItem7.addActionListener((a)->{
            openPanel(new environmentPanel());
        });
    }
    
    protected void openPanel(Component panel){
        EventQueue.invokeLater(()->{
            this.getContentPane().setLayout(new BorderLayout());
            this.getContentPane().add(panel,BorderLayout.CENTER);
            this.pack();
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
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new guiMediaHandler(adminTools.class.getName()).getIconImage());

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

        jMenu3.setText("Sistema");

        jMenuItem7.setText("Variables");
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

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
        EventQueue.invokeLater(()->{
            new adminTools().setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    // End of variables declaration//GEN-END:variables
}