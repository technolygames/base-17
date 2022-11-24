package venPrimarias;
//clases
import clases.Datos;
import clases.DbUtils;
import clases.MediaHandler;
import clases.logger;
import menus.menuDatosVentana4;
import paneles.delDatosPanel4;
import paneles.modDatosPanel4;
//java
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.RowSorter;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.AbstractAction;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public class ltshStorage extends javax.swing.JFrame{
    public ltshStorage(){
        initComponents();
        new MediaHandler(ltshStorage.class.getName()).setLookAndFeel(ltshStorage.this);
        
        botones();
        datosMostrar();
        popup();
        
        setLocationRelativeTo(null);
        setTitle("Almacén");
        pack();
    }
    
    protected Object[] header;
    protected String methodName;
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    protected JPopupMenu popupMenu;
    protected DefaultTableModel dtm;
    protected RowSorter<TableModel> sorter;
    
    protected final void botones(){
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        refreshButton.addActionListener(a->
            searchAndClear()
        );
        
        searchButton.addActionListener(a->
            searchData()
        );
        
        txtBuscar.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    searchData();
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
        
        jComboBox1.addActionListener(a->{
            int i=jComboBox1.getSelectedIndex();
            if(i>=0&&i<6){
                searchAndClear();
            }
        });
    }
    
    //NO USAR PARA BUSCAR DATOS
    //Este método se encarga de limpiar el cuadro de búsqueda y la tabla, también de esconder el botón de ver datos detallados
    protected void searchAndClear(){
        textField("");
        datosMostrar();
    }
    
    //Este es para buscar datos en concreto
    protected void searchData(){
        methodName="searchData";
        if(!txtBuscar.getText().isEmpty()){
            datosBuscar();
        }else{
            new logger(Level.WARNING).storeAndViewError18(this,ltshStorage.class.getName(),methodName);
        }
    }
    
    protected final void datosMostrar(){
        methodName="datosMostrar";
        header=new Object[]{"Código del producto","Código del lote","Código del proveedor","Nombre del producto","Marca","Cantidad","Precio unitario","Stock","Fecha de ingreso"};
        
        dtm=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                //all cells false
                return false;
            }
        };
        
        for(int i=0;i<dtm.getRowCount();i++){
            for(int j=0;j<dtm.getColumnCount();j++){
                dtm.isCellEditable(i,j);
            }
        }
        
        sorter=new TableRowSorter<>(dtm);
        try{
            ps=new Datos().getConnection().prepareStatement("select * from almacen;");
            rs=ps.executeQuery();
            dtm.setColumnIdentifiers(header);
            while(rs.next()){
                dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_lote"),rs.getInt("codigo_prov"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio_unitario"),rs.getString("stock"),rs.getString("fecha_ingreso")});
            }
            jTable1.setRowSorter(sorter);
            jTable1.getRowSorter().toggleSortOrder(0);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setModel(dtm);
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,e,ltshStorage.class.getName(),methodName,"16");
        }
    }
    
    protected void datosBuscar(){
        methodName="datosBuscar";
        header=new Object[]{"Código del producto","Código del lote","Código del proveedor","Nombre del producto","Marca","Cantidad","Precio unitario","Stock","Fecha de ingreso"};
        
        dtm=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                //all cells false
                return false;
            }
        };
        
        for(int i=0;i<dtm.getRowCount();i++){
            for(int j=0;j<dtm.getColumnCount();j++){
                dtm.isCellEditable(i,j);
            }
        }
        
        sorter=new TableRowSorter<>(dtm);
        try{
            switch(jComboBox1.getSelectedIndex()){
                case 0->{
                    ps=new Datos().getConnection().prepareStatement("select * from almacen where codigo_prod=?;");
                    ps.setInt(1,Integer.parseInt(txtBuscar.getText()));
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_lote"),rs.getInt("codigo_prov"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio_unitario"),rs.getString("stock"),rs.getString("fecha_ingreso")});
                    }else{
                        new logger(Level.WARNING).storeAndViewError14(this,ltshStorage.class.getName(),methodName);
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                }
                case 1->{
                    ps=new Datos().getConnection().prepareStatement("select * from almacen where codigo_lote=?;");
                    ps.setInt(1,Integer.parseInt(txtBuscar.getText()));
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_lote"),rs.getInt("codigo_prov"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio_unitario"),rs.getString("stock"),rs.getString("fecha_ingreso")});
                    }else{
                        new logger(Level.WARNING).storeAndViewError14(this,ltshStorage.class.getName(),methodName);
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                }
                case 2->{
                    ps=new Datos().getConnection().prepareStatement("select * from almacen where codigo_prov=?;");
                    ps.setInt(1,Integer.parseInt(txtBuscar.getText()));
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_lote"),rs.getInt("codigo_prov"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio_unitario"),rs.getString("stock"),rs.getString("fecha_ingreso")});
                    }else{
                        new logger(Level.WARNING).storeAndViewError14(this,ltshStorage.class.getName(),methodName);
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                }
                case 3->{
                    ps=new Datos().getConnection().prepareStatement("select * from almacen where nombre_prod=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_lote"),rs.getInt("codigo_prov"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio_unitario"),rs.getString("stock"),rs.getString("fecha_ingreso")});
                    }else{
                        new logger(Level.WARNING).storeAndViewError14(this,ltshStorage.class.getName(),methodName);
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                }
                case 4->{
                    ps=new Datos().getConnection().prepareStatement("select * from almacen where marca=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    while(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_lote"),rs.getInt("codigo_prov"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio_unitario"),rs.getString("stock"),rs.getString("fecha_ingreso")});
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                }
                case 5->{
                    ps=new Datos().getConnection().prepareStatement("select * from almacen where stock=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    while(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_lote"),rs.getInt("codigo_prov"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio_unitario"),rs.getString("stock"),rs.getString("fecha_ingreso")});
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                }
                default->{
                    break;
                }
            }
        }catch(SQLException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,e,ltshStorage.class.getName(),methodName,"14");
        }catch(NullPointerException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,x,ltshStorage.class.getName(),methodName,"0");
        }catch(ArrayIndexOutOfBoundsException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,n,ltshStorage.class.getName(),methodName,"AIOOBE");
        }catch(IndexOutOfBoundsException s){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,s,ltshStorage.class.getName(),methodName,"IOOBE");
        }
    }
    
    protected final void popup(){
        popupMenu=new JPopupMenu();
        
        JMenuItem mi1=new JMenuItem(new AbstractAction("Modificar datos"){
            @Override
            public void actionPerformed(ActionEvent a){
                new menuDatosVentana4(new modDatosPanel4(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString()),false),false).setVisible(true);
            }
        });
        
        JMenuItem mi2=new JMenuItem(new AbstractAction("Eliminar datos"){
            @Override
            public void actionPerformed(ActionEvent a){
                new menuDatosVentana4(new delDatosPanel4(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString()),false),false).setVisible(true);
            }
        });
        
        popupMenu.add(mi1);
        popupMenu.add(mi2);
    }
    
    protected void showPopup(MouseEvent a){
        if(a.isPopupTrigger()){
            popupMenu.show(a.getComponent(),a.getX(),a.getY());
        }
    }
    
    protected void textField(String text){
        txtBuscar.setText(text);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new MediaHandler(ltshStorage.class.getName()).getIconImage());

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel3.setText("Almacén");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código del producto", "Código del lote", "Código del proveedor", "Nombre del producto", "Marca", "Stock" }));

        searchButton.setText("Buscar");

        backButton.setText("Regresar");

        refreshButton.setText("Recargar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backButton)
                    .addComponent(refreshButton)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new ltshStorage().setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}