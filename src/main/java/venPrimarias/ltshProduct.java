package venPrimarias;
//clases
import clases.Datos;
import clases.DbUtils;
import clases.MediaHandler;
import clases.logger;
//java
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.RowSorter;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public class ltshProduct extends javax.swing.JFrame{
    public ltshProduct(){
        initComponents();
        new MediaHandler(ltshProduct.class.getName()).LookAndFeel(ltshProduct.this);
        
        botones();
        datosMostrar();
        
        setLocationRelativeTo(null);
        setTitle("Ventas");
        pack();
    }
    
    protected Object[] header;
    protected String methodName;
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
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
        
        jComboBox1.addActionListener(a->{
            int i=jComboBox1.getSelectedIndex();
            if(i>=0&&i<4){
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
            new logger(Level.WARNING).storeAndViewError18(this,ltshProduct.class.getName(),methodName);
        }
    }
    
    protected final void datosMostrar(){
        methodName="datosMostrar";
        header=new Object[]{"Código del producto","Código del empleado","Nombre del producto","Marca","Cantidad","Precio","Total","Fecha de compra"};
        
        dtm=new DefaultTableModel();
        sorter=new TableRowSorter<>(dtm);
        try{
            ps=new Datos().getConnection().prepareStatement("select * from productos;");
            rs=ps.executeQuery();
            dtm.setColumnIdentifiers(header);
            while(rs.next()){
                dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_emp"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio"),rs.getInt("total"),rs.getString("fecha_compra")});
            }
            jTable1.setRowSorter(sorter);
            jTable1.getRowSorter().toggleSortOrder(0);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setModel(dtm);
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,e,ltshProduct.class.getName(),methodName,"16");
        }
    }
    
    protected void datosBuscar(){
        methodName="datosBuscar";
        header=new Object[]{"Código del producto","Código del empleado","Nombre del producto","Marca","Cantidad","Precio","Total","Fecha de compra"};
        
        dtm=new DefaultTableModel();
        sorter=new TableRowSorter<>(dtm);
        try{
            switch(jComboBox1.getSelectedIndex()){
                case 0->{
                    ps=new Datos().getConnection().prepareStatement("select * from productos where codigo_prod=?;");
                    ps.setInt(1,Integer.parseInt(txtBuscar.getText()));
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    while(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_emp"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio"),rs.getInt("total"),rs.getString("fecha_compra")});
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
                    ps=new Datos().getConnection().prepareStatement("select * from productos where codigo_emp=?;");
                    ps.setInt(1,Integer.parseInt(txtBuscar.getText()));
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    while(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_emp"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio"),rs.getInt("total"),rs.getString("fecha_compra")});
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
                    ps=new Datos().getConnection().prepareStatement("select * from productos where nombre_prod=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    while(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_emp"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio"),rs.getInt("total"),rs.getString("fecha_compra")});
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
                    ps=new Datos().getConnection().prepareStatement("select * from productos where marca=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    while(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_emp"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio"),rs.getInt("total"),rs.getString("fecha_compra")});
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
            new logger(Level.SEVERE).storeAndViewCaughtException(this,e,ltshProduct.class.getName(),methodName,"14");
        }catch(NullPointerException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,x,ltshProduct.class.getName(),methodName,"0");
        }catch(ArrayIndexOutOfBoundsException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,n,ltshProduct.class.getName(),methodName,"AIOOBE");
        }catch(IndexOutOfBoundsException s){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,s,ltshProduct.class.getName(),methodName,"IOOBE");
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
        jLabel1 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new MediaHandler(ltshProduct.class.getName()).getIconImage());

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
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Ventas");

        backButton.setText("Regresar");

        searchButton.setText("Buscar");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código del producto", "Código del empleado", "Nombre del producto", "Marca" }));

        refreshButton.setText("Recargar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 787, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchButton)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(refreshButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new ltshProduct().setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}