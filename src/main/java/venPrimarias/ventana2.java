package venPrimarias;
//clases
import clases.datos;
import clases.guiMediaHandler;
import clases.logger;
import menus.menuDatosVentana4;
//java
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
//extension larga
import java.util.logging.Level;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import javax.swing.table.DefaultTableModel;

public final class ventana2 extends javax.swing.JFrame{
    public ventana2(){
        initComponents();
        new guiMediaHandler(ventana2.class.getName()).LookAndFeel(ventana2.this);
        
        botones();
        popup();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Almacén");
        pack();
    }
    
    protected DefaultTableModel dtm;
    protected JPopupMenu popupMenu;
    
    protected final void settings(){
        picLabel.setIcon(new ImageIcon(new ImageIcon(new guiMediaHandler(start.class.getName()).getFormImage()).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
        
        dtm=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                //all cells false
                return false;
            }
        };
        
        dtm.setColumnIdentifiers(new Object[]{
            "Código del producto",
            "Código del lote",
            "Código del proveedor",
            "Nombre del producto",
            "Marca",
            "Cantidad",
            "Precio unitario",
            "Stock"
        });
        dtm.setRowCount(0);
        
        for(int i=0;i<dtm.getRowCount();i++){
            for(int j=0;j<dtm.getColumnCount();j++){
                dtm.isCellEditable(i,j);
            }
        }
        
        jTable1.setEnabled(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setModel(dtm);
    }
    
    protected final void botones(){
        dtm=new DefaultTableModel();
        addButton.addActionListener((a)->{
            if(!txtCodProd.getText().equals("")||!txtCodLote.getText().equals("")||!txtCodProv.getText().equals("")||!txtProd.getText().equals("")||!txtMarca.getText().equals("")||!txtCant.getText().equals("")||!txtPU.getText().equals("")||!jComboBox1.getSelectedItem().equals("En Existencia")){
                dtm.addRow(new Object[]{
                    txtCodProd.getText(),
                    txtCodLote.getText(),
                    txtCodProv.getText(),
                    txtProd.getText(),
                    txtMarca.getText(),
                    txtCant.getText(),
                    txtPU.getText(),
                    jComboBox1.getSelectedItem()
                });
            }else{
                JOptionPane.showMessageDialog(null,"Error:\nIngrese los datos que se solicitan","Error 18",JOptionPane.WARNING_MESSAGE);
                new logger(Level.WARNING).staticLogger("Error 18: no se escribieron o faltan datos en los campos.\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'botones(addButton)'");
            }
            
            cleanFields();
        });
        
        backButton.addActionListener((a)->{
            setVisible(false);
            dispose();
        });
        
        cleanButton.addActionListener((a)->{
            dtm.setRowCount(0);
            
            cleanFields();
        });
        
        updateDataButton.addActionListener((a)->{
            new menuDatosVentana4().setVisible(true);
        });
        
        svdtButton.addActionListener((a)->{
            try{
                for(int i=0;i<dtm.getRowCount();i++){
                    int codigoProducto=Integer.parseInt(dtm.getValueAt(i,0).toString());
                    int codigoLote=Integer.parseInt(dtm.getValueAt(i,1).toString());
                    int codigoProveedor=Integer.parseInt(dtm.getValueAt(i,2).toString());
                    String nombreProducto=dtm.getValueAt(i,3).toString();
                    String marca=dtm.getValueAt(i,4).toString();
                    int cantidad=Integer.parseInt(dtm.getValueAt(i,5).toString());
                    int preciou=Integer.parseInt(dtm.getValueAt(i,6).toString());
                    String stock=dtm.getValueAt(i,7).toString();
                    
                    new datos().insertarDatosAlmacen(codigoProducto,codigoLote,codigoProveedor,nombreProducto,marca,cantidad,preciou,stock);
                }
                JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
                new logger(Level.INFO).staticLogger("Rel 1: se guardaron correctamente los datos a ka base de datos.\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'botones(svdtButton)'.\nUsuario que hizo los cambios: "+String.valueOf(start.userID));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 32: "+e.getMessage()+".\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'botones(svdtButton)'");
                new logger(Level.SEVERE).exceptionLogger(ventana2.class.getName(),"botones.svdt-32",e.fillInStackTrace());
            }catch(NullPointerException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'botones(svdtButton)'");
                new logger(Level.SEVERE).exceptionLogger(ventana2.class.getName(),"botones.svdt-0",x.fillInStackTrace());
            }
        });
        
        jTable1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent a){
                int row=jTable1.rowAtPoint(a.getPoint());
                if(row>=0&&row<jTable1.getRowCount()){
                    jTable1.setRowSelectionInterval(row,row);
                }else{
                    jTable1.clearSelection();
                }
                showPopup(a);
            }
        });
    }
    
    protected void popup(){
        popupMenu=new JPopupMenu();
        
        JMenuItem mi1=new JMenuItem(new AbstractAction("Editar fila"){
            @Override
            public void actionPerformed(ActionEvent a){
                try{
                    int row=jTable1.getSelectedRow();
                    txtCodProd.setText(jTable1.getValueAt(row,0).toString());
                    txtCodLote.setText(jTable1.getValueAt(row,1).toString());
                    txtCodProv.setText(jTable1.getValueAt(row,2).toString());
                    txtProd.setText(jTable1.getValueAt(row,3).toString());
                    txtMarca.setText(jTable1.getValueAt(row,4).toString());
                    txtCant.setText(jTable1.getValueAt(row,5).toString());
                    txtPU.setText(jTable1.getValueAt(row,6).toString());
                    jComboBox1.getModel().setSelectedItem(jTable1.getValueAt(row,7).toString());
                    dtm.removeRow(row);
                }catch(ArrayIndexOutOfBoundsException e){
                    JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error AIOOBE",JOptionPane.ERROR_MESSAGE);
                    new logger(Level.SEVERE).staticLogger("Error AIOOBE: "+e.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'popup()'");
                    new logger(Level.SEVERE).exceptionLogger(ventana1.class.getName(),"popup-AIOOBE",e.fillInStackTrace());
                }
            }
        });
        
        JMenuItem mi2=new JMenuItem(new AbstractAction("Eliminar fila"){
            @Override
            public void actionPerformed(ActionEvent a){
                removeRow();
            }
        });
        
        popupMenu.add(mi1);
        popupMenu.add(mi2);
    }
    
    protected void showPopup(MouseEvent evt){
        if(evt.isPopupTrigger()){
            popupMenu.show(evt.getComponent(),evt.getX(),evt.getY());
        }
    }
    
    protected void removeRow(){
        try{
            dtm.removeRow(jTable1.getSelectedRow());
        }catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error AIOOBE",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error AIOOBE: "+e.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'removeRow()'");
            new logger(Level.SEVERE).exceptionLogger(ventana1.class.getName(),"removeRow-AIOOBE",e.fillInStackTrace());
        }
    }
    
    protected void cleanFields(){
        txtCodProd.setText("");
        txtCodLote.setText("");
        txtCodProv.setText("");
        txtProd.setText("");
        txtMarca.setText("");
        txtCant.setText("");
        txtPU.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        svdtButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCodProd = new javax.swing.JTextField();
        txtProd = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtCodLote = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodProv = new javax.swing.JTextField();
        cleanButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtPU = new javax.swing.JTextField();
        picLabel = new javax.swing.JLabel();
        updateDataButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new guiMediaHandler(ventana2.class.getName()).getIconImage());

        backButton.setText("Regresar");

        svdtButton.setText("Guardar datos");

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel6.setText("Almacén");

        jLabel7.setText("Código del producto:");

        jLabel8.setText("Nombre del producto:");

        jLabel9.setText("Marca:");

        jLabel10.setText("Stock:");

        txtCodProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodProdKeyPressed(evt);
            }
        });

        txtCodLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodLoteKeyPressed(evt);
            }
        });

        txtCodProv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodProvKeyPressed(evt);
            }
        });

        txtProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProdKeyPressed(evt);
            }
        });

        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMarcaKeyPressed(evt);
            }
        });

        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantKeyPressed(evt);
            }
        });

        txtPU.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPUKeyPressed(evt);
            }
        });

        jLabel2.setText("Código del lote:");

        jLabel4.setText("Código del proveedor:");

        cleanButton.setText("Limpiar campos");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);

        addButton.setText("Añadir campos");

        jLabel1.setText("Cantidad:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En Existencia", "Agotado" }));

        jLabel3.setText("Precio unitario:");

        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        updateDataButton.setText("Editar datos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtCodProd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodLote, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodProv, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPU, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(svdtButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cleanButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addButton)
                        .addGap(113, 113, 113)
                        .addComponent(updateDataButton)
                        .addGap(32, 32, 32)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCodProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCodLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtCodProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtPU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10)))
                                    .addGap(28, 28, 28))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(cleanButton)
                    .addComponent(addButton)
                    .addComponent(svdtButton)
                    .addComponent(updateDataButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtCodProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProdKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'txtCodigoKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtCodProdKeyPressed
    
    private void txtCodLoteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodLoteKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'txtCodProdKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtCodLoteKeyPressed
    
    private void txtCodProvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProvKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'txtCodProvKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtCodProvKeyPressed
    
    private void txtProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProdKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'txtProdKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtProdKeyPressed
    
    private void txtMarcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'txtMarcaKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtMarcaKeyPressed
    
    private void txtCantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'txtCantKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtCantKeyPressed
    
    private void txtPUKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPUKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'txtPUKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtPUKeyPressed
    
    public static void main(String[] args){
        new ventana2().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton cleanButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel picLabel;
    private javax.swing.JButton svdtButton;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCodLote;
    private javax.swing.JTextField txtCodProd;
    private javax.swing.JTextField txtCodProv;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtPU;
    private javax.swing.JTextField txtProd;
    private javax.swing.JButton updateDataButton;
    // End of variables declaration//GEN-END:variables
}