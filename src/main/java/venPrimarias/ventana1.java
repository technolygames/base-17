package venPrimarias;
//clases
import clases.Datos;
import clases.Events;
import clases.MediaHandler;
import clases.Validation;
import clases.logger;
import clases.mvc.Controlador;
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
        new MediaHandler(ventana1.class.getName()).setLookAndFeel(ventana1.this);
        
        botones();
        popup();
        popup1();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Ventas");
        pack();
    }
    
    protected Controlador modelo;
    
    public ventana1(Controlador modelo){
        initComponents();
        new MediaHandler(ventana1.class.getName()).setLookAndFeel(ventana1.this);
        
        this.modelo=modelo;
        
        botones();
        popup();
        popup1();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Ventas");
        pack();
    }
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    protected DefaultTableModel dtm;
    protected JPopupMenu popupMenu;
    protected JPopupMenu popupMenu1;
    protected JTextField campos;
    
    protected String methodName;
    
    protected final void settings(){
        txtCodEmp.setText(String.valueOf(modelo.getUserID()));
        picLabel.setIcon(new ImageIcon(new ImageIcon(new MediaHandler(start.class.getName()).getFormImage()).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
        
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
        var datos=new Datos(modelo);
        
        addButton.addActionListener(a->{
            methodName="botones.add";
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
                new logger(Level.WARNING,this.getClass().getName()).storeError18(this,methodName);
            }
        });
        
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        cleanButton.addActionListener(a->{
            dtm.setRowCount(0);
            
            clearFields();
        });
        
        jButton2.addActionListener(a->
            removeRow()
        );
        
        mkPmntButton.addActionListener(a->{
            methodName="botones.mkPmnt";
            try{
                if(jTable1.getRowCount()!=0){
                    new paymentWindow(new javax.swing.JFrame(),true,modelo,dtm).setVisible(true);
                }else{
                    new logger(Level.WARNING,this.getClass().getName()).storeError18(this,methodName);
                }
            }catch(NumberFormatException e){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"32");
            }catch(NullPointerException x){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,x,methodName,"0");
            }
        });
        
        txtCodigo.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent a){
                if(!txtCodigo.getText().equals("")){
                    Events.showPopup(popupMenu1,a);
                }else{
                    logger.staticLogger(Level.CONFIG,"no hace la búsqueda","ventana 1");
                }
            }
        });
        
        txtCodigo.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                methodName="botones.txtCodigo";
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    try{
                        ps=datos.getConnection().prepareStatement("select*from almacen where codigo_prod=?;");
                        ps.setInt(1,Integer.parseInt(txtCodigo.getText()));
                        rs=ps.executeQuery();
                        if(rs.next()){
                            if(!txtCodigo.getText().isEmpty()){
                                if(rs.getInt("cantidad")==0){
                                    String etiqueta="stock";
                                    String[] mensaje=new String[]{"Sin stock","Error Prueba"};
                                    if(rs.getString(etiqueta).equals(Validation.stock(1))){
                                        JOptionPane.showMessageDialog(ventana1.this,mensaje[0],mensaje[1],JOptionPane.WARNING_MESSAGE);
                                        logger.staticLogger(Level.CONFIG,"No guarda en ventana1, pero da el aviso",this.getClass().getName());
                                    }else{
                                        if(rs.getString(etiqueta).equals(Validation.stock(0))){
                                            JOptionPane.showMessageDialog(ventana1.this,mensaje[0],mensaje[1],JOptionPane.WARNING_MESSAGE);
                                            logger.staticLogger(Level.CONFIG,"Guarda en ventana1",this.getClass().getName());
                                            datos.actualizarDatosString("almacen","stock","codigo_prod","Agotado",Integer.parseInt(txtCodigo.getText()),false);
                                        }else if(rs.getString(etiqueta).equals(Validation.stock(1))){
                                            JOptionPane.showMessageDialog(ventana1.this,mensaje[0],mensaje[1],JOptionPane.WARNING_MESSAGE);
                                            logger.staticLogger(Level.CONFIG,"No guarda en ventana1 porque el valor es igual",this.getClass().getName());
                                        }
                                    }
                                }else{
                                    txtProd.setText(rs.getString("nombre_prod"));
                                    txtMarca.setText(rs.getString("marca"));
                                    txtPrecio.setText(String.valueOf(rs.getInt("precio_unitario")));
                                }
                            }else{
                                new logger(Level.WARNING,this.getClass().getName()).storeError18(ventana1.this,methodName);
                            }
                        }else{
                            new logger(Level.WARNING,this.getClass().getName()).storeError14(ventana1.this,methodName);
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE,this.getClass().getName()).catchException(ventana1.this,e,methodName,"14");
                    }
                }
            }
        });
        
        txtCant.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                methodName="botones.txtCant";
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    try{
                        ps=datos.getConnection().prepareStatement("select*from almacen where codigo_prod=?;");
                        ps.setInt(1,Integer.parseInt(txtCodigo.getText()));
                        rs=ps.executeQuery();
                        if(rs.next()){
                            if(!txtCant.getText().isEmpty()){
                                int txtCantidad=Integer.parseInt(txtCant.getText());
                                int cantidad=rs.getInt("cantidad");
                                String nombre=rs.getString("nombre_prod");
                                if(txtCantidad>=cantidad&&txtCantidad>cantidad||txtCantidad==cantidad&&cantidad>=1){
                                    if(txtCantidad>cantidad){
                                        JOptionPane.showMessageDialog(ventana1.this,"No tienes mucho stock a partir de la cantidad ingresada.","Error Prueba",JOptionPane.ERROR_MESSAGE);
                                        logger.staticLogger(Level.SEVERE,"Error Prueba: sin stock de "+nombre+".\nOcurrió en el método 'botones(txtCant)'",this.getClass().getName());
                                    }else{
                                        JOptionPane.showMessageDialog(ventana1.this,"No hay mucho stock de este producto.\nSi realizas la venta, te quedarás sin stock de este producto.","Error Prueba",JOptionPane.WARNING_MESSAGE);
                                        logger.staticLogger(Level.WARNING,"Error Prueba: escasez de "+nombre+" (cantidad en almacén: "+cantidad+").\nOcurrió en el método 'botones(txtCant)'",this.getClass().getName());
                                        calc();
                                    }
                                }else{
                                    calc();
                                }
                            }else{
                                new logger(Level.WARNING,this.getClass().getName()).storeError18(ventana1.this,methodName);
                            }
                        }
                    }catch(SQLException e){
                        new logger(Level.SEVERE,this.getClass().getName()).catchException(ventana1.this,e,methodName,"14");
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
                Events.showPopup(popupMenu,a);
            }
        });
    }
    
    protected final void popup(){
        methodName="popup";
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
                    new logger(Level.SEVERE,this.getClass().getName()).catchException(ventana1.this,e,methodName,"AIOOBE");
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
    
    protected final void popup1(){
        popupMenu1=new JPopupMenu();
        
        JMenuItem mi1=new JMenuItem(new AbstractAction("Consultar precio"){
            @Override
            public void actionPerformed(ActionEvent a){
                try{
                    ps=new Datos(modelo).getConnection().prepareStatement("select * from almacen where codigo_prod=?;");
                    ps.setInt(1,Integer.parseInt(txtCodigo.getText()));
                    rs=ps.executeQuery();
                    if(rs.next()){
                        JOptionPane.showMessageDialog(ventana1.this,"Nombre: "+rs.getString("nombre_prod")+"\nPrecio: $"+rs.getInt("precio_unitario"),"Notice 1",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(ventana1.this,"Escribe correctamente el valor requerido","Error Prueba",JOptionPane.WARNING_MESSAGE);
                    }
                }catch(SQLException e){
                    new logger(Level.SEVERE,ventana1.this.getClass().getName()).catchException(ventana1.this,e,"popup1","14");
                }catch(NumberFormatException x){
                    new logger(Level.SEVERE,ventana1.this.getClass().getName()).catchException(ventana1.this,x,"popup1","32");
                }
            }
        });
        
        popupMenu1.add(mi1);
    }
    
    protected void calc(){
        methodName="calc";
        try{
            int n1=Integer.parseInt(txtCant.getText());
            int n2=Integer.parseInt(txtPrecio.getText());
            var res=n2*n1;

            txtTotal.setText(String.valueOf(res));
        }catch(NumberFormatException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"32");
        }
    }
    
    protected void clearFields(){
        for(JTextField tf:new JTextField[]{txtCodigo,txtProd,txtMarca,txtPrecio,txtCant,txtTotal}){
            tf.setText("");
        }
    }
    
    protected void removeRow(){
        methodName="removeRow";
        try{
            if(jTable1.isRowSelected(jTable1.getRowCount())){
                dtm.removeRow(jTable1.getSelectedRow());
            }else{
                logger.staticLogger(Level.INFO,"no hay nada seleccionado",this.getClass().getName());
            }
        }catch(ArrayIndexOutOfBoundsException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"AIOOBE");
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
        mkPmntButton = new javax.swing.JButton();
        picLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCodEmp = new javax.swing.JTextField();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new clases.MediaHandler(ventana1.class.getName()).getIconImage());

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
        jLabel8.setText("Ventas");

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

        mkPmntButton.setText("Realizar pago");

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
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mkPmntButton)
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
                    .addComponent(mkPmntButton)
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
            new logger(Level.WARNING,this.getClass().getName()).storeLetterInputWarning(this,"txtCodigoKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyPressed
    
    private void txtCodEmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodEmpKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeLetterInputWarning(this,"txtCodEmpKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtCodEmpKeyPressed

    private void txtProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProdKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeNumberInputWarning(this,"txtProdKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtProdKeyPressed
    
    private void txtMarcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeNumberInputWarning(this,"txtMarcaKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtMarcaKeyPressed
    
    private void txtCantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeLetterInputWarning(this,"txtCantKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtCantKeyPressed

    private void txtPrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeLetterInputWarning(this,"txtPrecioKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioKeyPressed
    
    private void txtTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new logger(Level.WARNING,this.getClass().getName()).storeLetterInputWarning(this,"txtTotalKeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_txtTotalKeyPressed
            
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new ventana1().setVisible(true)
        );
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
    private javax.swing.JButton mkPmntButton;
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