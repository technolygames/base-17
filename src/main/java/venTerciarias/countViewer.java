package venTerciarias;

import clases.guiMediaHandler;
import paneles.countPanel;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.EventQueue;

public class countViewer extends javax.swing.JDialog{
    public countViewer(java.awt.Frame parent,boolean modal){
        super(parent,modal);
        initComponents();
        new guiMediaHandler(countViewer.class.getName()).LookAndFeel(countViewer.this);
        
        setLocationRelativeTo(null);
        setTitle("Conteo de ventas");
        setResizable(false);
        pack();
    }
    
    public countViewer(java.awt.Frame parent,boolean modal,Component panel){
        super(parent,modal);
        initComponents();
        new guiMediaHandler(countViewer.class.getName()).LookAndFeel(countViewer.this);
        
        openPanel(panel);
        
        setLocationRelativeTo(null);
        setTitle("Conteo de ventas");
        setResizable(false);
        pack();
    }
    
    protected final void openPanel(Component panel){
        EventQueue.invokeLater(()->{
            countViewer.this.getContentPane().setLayout(new BorderLayout());
            countViewer.this.getContentPane().add(panel,BorderLayout.CENTER);
            countViewer.this.pack();
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]){
        EventQueue.invokeLater(()->{
            new countViewer(new javax.swing.JFrame(),true,new countPanel()).setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}