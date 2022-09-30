package venPrimarias;
//clases
import clases.datos;
import clases.guiMediaHandler;
import clases.logger;
import venSecundarias.paymentWindow;
//java
import java.awt.Image;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import javax.swing.table.DefaultTableModel;

public final class ventana1 extends javax.swing.JFrame{
    public ventana1(){
        initComponents();
        new guiMediaHandler(ventana1.class.getName()).LookAndFeel(ventana1.this);
        
        botones();
        popup();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Productos");
        pack();
    }
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    public static DefaultTableModel dtm;
    protected JPopupMenu popupMenu;
    protected JTextField campos;
    
    public static int codigo_emp;
    
    protected final void settings(){
        txtCodEmp.setText(String.valueOf(start.userID));
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
            "Nombre del producto",
            "Marca",
            "Cantidad",
            "Precio",
            "Total"
        });
        
        for(int i=0;i<dtm.getRowCount();i++){
            for(int j=0;j<dtm.getColumnCount();j++){
                dtm.isCellEditable(i,j);
            }
        }
        
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setModel(dtm);
    }
    
    protected final void botones(){
        dtm=new DefaultTableModel();
        
        addButton.addActionListener((a)->{
            for(JTextField tf:new JTextField[]{txtCodigo,txtProd,txtMarca,txtPrecio,txtCant,txtTotal}){
                campos=tf;
            }
            if(!campos.getText().isEmpty()){
                dtm.addRow(new Object[]{
                    txtCodigo.getText(),
                    txtProd.getText(),
                    txtMarca.getText(),
                    txtCant.getText(),
                    txtPrecio.getText(),
                    txtTotal.getText()
                });
                clearFields();
            }else{
                JOptionPane.showMessageDialog(null,"Error:\nIngrese los datos que se solicitan","Error 18",JOptionPane.WARNING_MESSAGE);
                new logger(Level.WARNING).staticLogger("Error 18: no se escribieron o faltan datos en los campos.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(addButton)'");
            }
        });
        
        backButton.addActionListener((a)->{
            setVisible(false);
            dispose();
        });
        
        cleanButton.addActionListener((a)->{
            dtm.setRowCount(0);
            
            clearFields();
        });
        
        jButton2.addActionListener((a)->{
            removeRow();
        });
        
        mkPaidButton.addActionListener((a)->{
            try{
                if(jTable1.getRowCount()!=0){
                    codigo_emp=Integer.parseInt(txtCodEmp.getText());
                    new paymentWindow(new javax.swing.JFrame(),true).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Error:\nNo se ingresaron los datos de los campos a la tabla","Error 18",JOptionPane.WARNING_MESSAGE);
                    new logger(Level.WARNING).staticLogger("Error 18: No se ingresaron los datos de los campos a la tabla.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(mkPaidButton)'");
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 32: "+e.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(mkPaidButton)'");
                new logger(Level.SEVERE).exceptionLogger(ventana1.class.getName(),"botones.mkPaid-32",e.fillInStackTrace());
            }catch(NullPointerException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(mkPaidButton)'");
                new logger(Level.SEVERE).exceptionLogger(ventana1.class.getName(),"botones.mkPaid-0",x.fillInStackTrace());
            }
        });
        
        txtCodigo.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    try{
                        ps=new datos().getConnection().prepareStatement("select*from almacen where codigo_prod=?;");
                        ps.setInt(1,Integer.parseInt(txtCodigo.getText()));
                        rs=ps.executeQuery();
                        if(rs.next()){
                            if(!txtCodigo.getText().isEmpty()){
                                if(rs.getInt("cantidad")==0){
                                    if(rs.getString("stock").equals("Agotado")){
                                        JOptionPane.showMessageDialog(null,"Sin stock","Error Prueba",JOptionPane.WARNING_MESSAGE);
                                        new logger(Level.CONFIG).staticLogger("No guarda en ventana1, pero da el aviso");
                                    }else{
                                        JOptionPane.showMessageDialog(null,"Sin stock","Error Prueba",JOptionPane.WARNING_MESSAGE);
                                        new datos().actualizarDatosString("almacen","stock","codigo_prod","Agotado",Integer.parseInt(txtCodigo.getText()));
                                        new logger(Level.CONFIG).staticLogger("Guarda en ventana1");
                                    }
                                }else{
                                    txtProd.setText(rs.getString("nombre_prod"));
                                    txtMarca.setText(rs.getString("marca"));
                                    txtPrecio.setText(String.valueOf(rs.getInt("precio_unitario")));
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,"Error:\nIngrese los datos que se solicitan","Error 18",JOptionPane.WARNING_MESSAGE);
                                new logger(Level.WARNING).staticLogger("Error 18: no se escribieron o faltan datos en los campos.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(addButton)'");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                            new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(txtCodigo)'");
                        }
                    }catch(SQLException e){
                        JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
                        new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(txtCodigo)'");
                        new logger(Level.SEVERE).exceptionLogger(ventana1.class.getName(),"botones.txtCodigo-14",e.fillInStackTrace());
                    }
                }
            }
        });
        
        txtCant.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    try{
                        ps=new datos().getConnection().prepareStatement("select*from almacen where codigo_prod=?;");
                        ps.setInt(1,Integer.parseInt(txtCodigo.getText()));
                        rs=ps.executeQuery();
                        if(rs.next()){
                            if(!txtCant.getText().isEmpty()){
                                int txtCantidad=Integer.parseInt(txtCant.getText());
                                int cantidad=rs.getInt("cantidad");
                                if(txtCantidad>=cantidad&&txtCantidad>cantidad||txtCantidad==cantidad&&cantidad>=1){
                                    if(txtCantidad>cantidad){
                                        JOptionPane.showMessageDialog(null,"No tienes mucho stock a partir de la cantidad ingresada.","Error Prueba",JOptionPane.ERROR_MESSAGE);
                                        new logger(Level.SEVERE).staticLogger("Error 14: sin stock de "+rs.getString("nombre_prod")+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(txtCant)'");
                                    }else{
                                        calc();
                                        JOptionPane.showMessageDialog(null,"No hay mucho stock de este producto.\nSi realizas la venta, te quedarás sin stock de este producto.","Error Prueba",JOptionPane.WARNING_MESSAGE);
                                        new logger(Level.WARNING).staticLogger("Error 14: escasez de "+rs.getString("nombre_prod")+" (cantidad en almacén: "+rs.getInt("cantidad")+").\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(txtCant)'");
                                    }
                                }else{
                                    calc();
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,"Error:\nIngrese los datos que se solicitan","Error 18",JOptionPane.WARNING_MESSAGE);
                                new logger(Level.WARNING).staticLogger("Error 18: no se escribieron o faltan datos en los campos.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(addButton)'");
                            }
                        }
                    }catch(SQLException e){
                        JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
                        new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(txtCant)'");
                        new logger(Level.SEVERE).exceptionLogger(ventana1.class.getName(),"botones.txtCant-14",e.fillInStackTrace());
                    }
                }
                if(a.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    txtTotal.setText("");
                }
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
    
    protected final void popup(){
        popupMenu=new JPopupMenu();
        
        JMenuItem mi1=new JMenuItem(new AbstractAction("Editar fila"){
            @Override
            public void actionPerformed(ActionEvent a){
                try{
                    int row=jTable1.getSelectedRow();
                    txtCodigo.setText(jTable1.getValueAt(row,0).toString());
                    txtProd.setText(jTable1.getValueAt(row,1).toString());
                    txtMarca.setText(jTable1.getValueAt(row,2).toString());
                    txtCant.setText(jTable1.getValueAt(row,3).toString());
                    txtPrecio.setText(jTable1.getValueAt(row,4).toString());
                    txtTotal.setText(jTable1.getValueAt(row,5).toString());
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
    
    protected void calc(){
        try{
            int n1=Integer.parseInt(txtCant.getText());
            int n2=Integer.parseInt(txtPrecio.getText());
            int res=n2*n1;

            txtTotal.setText(String.valueOf(res));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 32: "+e.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'calc()'");
            new logger(Level.SEVERE).exceptionLogger(ventana1.class.getName(),"calc-32",e.fillInStackTrace());
        }
    }
    
    protected void clearFields(){
        for(JTextField tf:new JTextField[]{txtCodigo,txtProd,txtMarca,txtPrecio,txtCant,txtTotal}){
            tf.setText("");
        }
    }
    
    protected void removeRow(){
        try{
            if(jTable1.isRowSelected(jTable1.getRowCount())){
                dtm.removeRow(jTable1.getSelectedRow());
            }else{
                new logger(Level.INFO).staticLogger("no hay nada seleccionado");
            }
        }catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error AIOOBE",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error AIOOBE: "+e.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'removeRow()'");
            new logger(Level.SEVERE).exceptionLogger(ventana1.class.getName(),"removeRow-AIOOBE",e.fillInStackTrace());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtProd = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtCant = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cleanButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        mkPaidButton = new javax.swing.JButton();
        picLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCodEmp = new javax.swing.JTextField();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new guiMediaHandler(ventana1.class.getName()).getIconImage());

        jLabel2.setText("Código del producto:");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
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

        jLabel3.setText("Nombre del producto:");

        jLabel4.setText("Marca:");

        jLabel5.setText("Precio:");

        jLabel6.setText("Cantidad:");

        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioKeyPressed(evt);
            }
        });

        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantKeyPressed(evt);
            }
        });

        backButton.setText("Regresar");

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel8.setText("Productos");

        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalKeyPressed(evt);
            }
        });

        jLabel9.setText("Total:");

        cleanButton.setText("Limpiar campos");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        addButton.setText("Añadir campos");

        mkPaidButton.setText("Realizar pago");

        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton2.setText("Eliminar fila");

        jLabel1.setText("Código del empleado:");

        txtCodEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodEmpKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCodEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mkPaidButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addButton)
                        .addGap(113, 113, 113)
                        .addComponent(cleanButton)
                        .addGap(32, 32, 32)
                        .addComponent(backButton))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mkPaidButton)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(backButton)
                        .addComponent(cleanButton)
                        .addComponent(addButton)
                        .addComponent(jButton2)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'txtCodigoKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyPressed
    
    private void txtCodEmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodEmpKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'txtCodEmpKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtCodEmpKeyPressed

    private void txtProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProdKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'txtProdKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtProdKeyPressed
    
    private void txtMarcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'txtMarcaKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtMarcaKeyPressed
    
    private void txtCantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'txtCantKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtCantKeyPressed

    private void txtPrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'txtPrecioKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioKeyPressed
    
    private void txtTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'txtTotalKeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_txtTotalKeyPressed
            
    public static void main(String[] args){
        EventQueue.invokeLater(()->{
            new ventana1().setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton cleanButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton mkPaidButton;
    private javax.swing.JLabel picLabel;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCodEmp;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProd;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}