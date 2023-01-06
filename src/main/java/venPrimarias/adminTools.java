package venPrimarias;
//clases
import clases.logger;
import clases.MediaHandler;
import clases.Validation;
import paneles.databaseConfig;
import paneles.databaseExport;
import paneles.databaseImport;
import paneles.environmentPanel;
import paneles.partDataRestore;
import paneles.provDataRestore;
import paneles.smtpPanel;
import paneles.updatePanel;
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
//extension larga
import java.util.logging.Level;

public class adminTools extends javax.swing.JFrame{
    public adminTools(){
        initComponents();
        new MediaHandler(adminTools.class.getName()).setLookAndFeel(adminTools.this);
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Herramientas de administrador");
        setResizable(false);
        pack();
    }
    
    protected String methodName;
    
    protected final void settings(){
        methodName="settings";
        JMenuItem[] items={jMenuItem5,jMenuItem6};
        try{
            Properties p=new Properties();
            p.load(new FileInputStream("data/config/env.properties"));
            if(!p.getProperty("local_mysql").isEmpty()){
                for(JMenuItem c:items){
                    c.setEnabled(true);
                }
            }else{
                for(JMenuItem c:items){
                    c.setEnabled(false);
                }
            }
            p.clear();
        }catch(FileNotFoundException e){
            new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(this,e,methodName,"1IO");
        }catch(IOException x){
            new logger(Level.SEVERE,this.getClass().getName()).storeAndViewCaughtException(this,x,methodName,"2IO");
        }
        
        if(new Validation(start.USER_ROLE,adminTools.class.getName()).hasOwnerRole()){
            jMenuItem9.setEnabled(true);
        }else{
            jMenuItem9.setEnabled(false);
        }
        
        JMenuItem[] mi0=new JMenuItem[]{jMenuItem5,jMenuItem6,jMenuItem7};
        
        if(new Validation(start.USER_ROLE,adminTools.class.getName()).hasDevRole()){
            for(JMenuItem mi1:mi0){
                mi1.setEnabled(true);
            }
        }else{
            for(JMenuItem mi1:mi0){
                mi1.setEnabled(false);
            }
        }
    }
    
    protected final void botones(){
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        jMenuItem1.addActionListener(a->
            openPanel(new databaseConfig())
        );
        
        jMenuItem2.addActionListener(a->
            openPanel(new workerDataRestore())
        );
        
        jMenuItem3.addActionListener(a->
            openPanel(new partDataRestore())
        );
        
        jMenuItem4.addActionListener(a->
            openPanel(new provDataRestore())
        );
        
        jMenuItem5.addActionListener(a->
            openPanel(new databaseImport())
        );
        
        jMenuItem6.addActionListener(a->
            openPanel(new databaseExport())
        );
        
        jMenuItem7.addActionListener(a->
            openPanel(new environmentPanel())
        );
        
        jMenuItem8.addActionListener(a->
            openPanel(new updatePanel())
        );
        
        jMenuItem9.addActionListener(a->
            openPanel(new smtpPanel())
        );
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
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new clases.MediaHandler(adminTools.class.getName()).getIconImage());

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

        jMenuItem8.setText("Actualizar");
        jMenu3.add(jMenuItem8);

        jMenuItem9.setText("Correos");
        jMenu3.add(jMenuItem9);

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
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new adminTools().setVisible(true)
        );
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
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}