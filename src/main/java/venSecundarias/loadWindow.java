package venSecundarias;
//clases
import clases.MediaHandler;
import clases.mvc.Controlador;
import menus.menuVentanas;
//java
import java.awt.Cursor;
import java.awt.Taskbar;
import java.awt.EventQueue;
import javax.swing.Timer;

public final class loadWindow extends javax.swing.JFrame{
    public loadWindow(){
        initComponents();
        new MediaHandler(loadWindow.class.getName()).setLookAndFeel(loadWindow.this);
        
        load();
        settings();
    }
    
    protected Controlador mvc;
    
    public loadWindow(Controlador modelo){
        initComponents();
        new MediaHandler(loadWindow.class.getName()).setLookAndFeel(loadWindow.this);
        
        this.mvc=modelo;
        
        load();
        settings();
    }
    
    protected Timer t;
    
    protected final void settings(){
        MediaHandler.load(loadWindow.this,"",false);
    }
    
    protected final void load(){
        Taskbar tb=Taskbar.getTaskbar();
        t=new Timer(100,a->{
            if(jProgressBar1.getValue()<100){
                if(Taskbar.isTaskbarSupported()){
                    int valor=jProgressBar1.getValue();
                    jProgressBar1.setValue(valor+5);
                    jProgressBar1.setMaximum(100);
                    tb.setWindowProgressState(loadWindow.this,Taskbar.State.NORMAL);
                    tb.setWindowProgressValue(loadWindow.this,valor);
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    switch(valor){
                        case 0:
                            jLabel2.setText(null);
                            jLabel2.setText("Cargando clases");
                            break;
                        case 20:
                            jLabel2.setText(null);
                            jLabel2.setText("Cargando ventanas");
                            break;
                        case 40:
                            jLabel2.setText(null);
                            jLabel2.setText("Cargando dependencias");
                            break;
                        case 60:
                            jLabel2.setText(null);
                            jLabel2.setText("Cargando configuraciones");
                            break;
                        case 80:
                            jLabel2.setText(null);
                            jLabel2.setText("Cargando características");
                            break;
                        case 95:
                            jLabel2.setText(null);
                            jLabel2.setText("Inicio");
                            break;
                        case 100:
                            break;
                    }
                }else{
                    int valor=jProgressBar1.getValue();
                    jProgressBar1.setValue(valor+5);
                    jProgressBar1.setMaximum(100);
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    switch(valor){
                        case 0:
                            jLabel2.setText(null);
                            jLabel2.setText("Cargando clases");
                            break;
                        case 20:
                            jLabel2.setText(null);
                            jLabel2.setText("Cargando ventanas");
                            break;
                        case 40:
                            jLabel2.setText(null);
                            jLabel2.setText("Cargando dependencias");
                            break;
                        case 60:
                            jLabel2.setText(null);
                            jLabel2.setText("Cargando configuraciones");
                            break;
                        case 80:
                            jLabel2.setText(null);
                            jLabel2.setText("Cargando características");
                            break;
                        case 95:
                            jLabel2.setText(null);
                            jLabel2.setText("Inicio");
                            break;
                        case 100:
                            break;
                    }
                }
            }else{
                t.stop();
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                new menuVentanas(mvc).setVisible(true);
                dispose();
            }
        });
        t.start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new MediaHandler(loadWindow.class.getName()).getIconImage());
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Cargando:");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new loadWindow().setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}