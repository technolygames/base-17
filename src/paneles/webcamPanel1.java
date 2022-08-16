package paneles;

public class webcamPanel1 extends javax.swing.JPanel{
    public webcamPanel1(){
        initComponents();
        botones();
    }
    
    protected final void botones(){
        closeButton.setToolTipText("Cierra la subventana actual "+webcamPanel1.class.getName());
        jPanelWebCam1.setToolTipText("Presiona click izquierdo 1 vez dentro del recuadro para encender la cámara.\nPresiona 1 vez click derecho dentro del recuadro para apagar la cámara");
        closeButton.addActionListener((e)->{
            setVisible(false);
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelWebCam1 = new JPanelWebCam.JPanelWebCam();
        closeButton = new javax.swing.JButton();

        jPanelWebCam1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanelWebCam1Layout = new javax.swing.GroupLayout(jPanelWebCam1);
        jPanelWebCam1.setLayout(jPanelWebCam1Layout);
        jPanelWebCam1Layout.setHorizontalGroup(
            jPanelWebCam1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
        );
        jPanelWebCam1Layout.setVerticalGroup(
            jPanelWebCam1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 268, Short.MAX_VALUE)
        );

        closeButton.setText("Cerrar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelWebCam1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(closeButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelWebCam1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private JPanelWebCam.JPanelWebCam jPanelWebCam1;
    // End of variables declaration//GEN-END:variables
}