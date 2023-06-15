package venTerciarias;
//clases
import clases.MediaHandler;
import clases.mvc.Controlador;
import paneles.countPanel;
//java
import java.awt.Component;
import java.awt.EventQueue;

public class countViewer extends javax.swing.JDialog{
    public countViewer(java.awt.Frame parent,boolean modal){
        super(parent,modal);
        initComponents();
        new MediaHandler(countViewer.class.getName()).setLookAndFeel(countViewer.this);
        
        settings();
    }
    
    public countViewer(java.awt.Frame parent,boolean modal,Component panel){
        super(parent,modal);
        initComponents();
        new MediaHandler(countViewer.class.getName()).setLookAndFeel(countViewer.this);
        
        MediaHandler.openPanel(countViewer.this,panel);
        
        settings();
    }
    
    protected Controlador modelo;
    
    public countViewer(java.awt.Frame parent,boolean modal,Controlador modelo){
        super(parent,modal);
        initComponents();
        new MediaHandler(countViewer.class.getName()).setLookAndFeel(countViewer.this);
        
        this.modelo=modelo;
        
        settings();
    }
    
    public countViewer(java.awt.Frame parent,boolean modal,Component panel,Controlador modelo){
        super(parent,modal);
        initComponents();
        new MediaHandler(countViewer.class.getName()).setLookAndFeel(countViewer.this);
        
        this.modelo=modelo;
        
        MediaHandler.openPanel(countViewer.this,panel);
        
        settings();
    }
    
    protected final void settings(){
        MediaHandler.load(countViewer.this,"Conteo de ventas",false);
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
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new countViewer(new javax.swing.JFrame(),true,new countPanel(654321)).setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}