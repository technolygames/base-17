package paneles;
//clases
import clases.laf;
import paneles.subpaneles.partDataRestore;
import paneles.subpaneles.provDataRestore;
import paneles.subpaneles.workerDataRestore;

import java.awt.BorderLayout;

public class backupHandler extends javax.swing.JPanel{
    public backupHandler(){
        initComponents();
        new laf().LookAndFeel(backupHandler.this,backupHandler.class.getName(),"backupHandler");
        
        botones();
    }
    
    protected final void botones(){
        closeButton.addActionListener((a)->{
            setVisible(false);
        });
        
        jComboBox1.addActionListener((a)->{
            switch(jComboBox1.getSelectedIndex()){
                case 0:
                    this.setLayout(new BorderLayout());
                    this.add(new workerDataRestore(),BorderLayout.CENTER);
                    break;
                case 1:
                    this.setLayout(new BorderLayout());
                    this.add(new partDataRestore(),BorderLayout.CENTER);
                    break;
                case 2:
                    this.setLayout(new BorderLayout());
                    this.add(new provDataRestore(),BorderLayout.CENTER);
                    break;
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        closeButton.setText("Cerrar panel");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleados", "Socios", "Proveedores" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(237, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(closeButton)
                        .addContainerGap(69, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JComboBox<String> jComboBox1;
    // End of variables declaration//GEN-END:variables
}