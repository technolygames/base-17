package venPrimarias;
//clases
import clases.Datos;
import clases.DbUtils;
import clases.Events;
import clases.MediaHandler;
import clases.logger;
import clases.mvc.Controlador;
import menus.menuDatosVentana2;
import paneles.delDatosPanel2;
import paneles.modDatosPanel2;
import venTerciarias.dataWindow2;
//java
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.RowSorter;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
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

public class ltshPartners extends javax.swing.JFrame{
    public ltshPartners(){
        initComponents();
        new MediaHandler(ltshPartners.class.getName()).setLookAndFeel(ltshPartners.this);
        
        botones();
        datosMostrar();
        popup();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Socios");
        pack();
    }
    
    protected Controlador modelo;
    
    public ltshPartners(Controlador modelo){
        initComponents();
        new MediaHandler(ltshPartners.class.getName()).setLookAndFeel(ltshPartners.this);
        
        this.modelo=modelo;
        
        botones();
        datosMostrar();
        popup();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Socios");
        pack();
    }
    
    protected static final Object[] header=new Object[]{"Código","Nombre(s)","Apellido paterno","Apellido materno","Tipo de socio","Datos extra","Fecha de registro","Fecha de última compra"};
    
    protected String methodName;
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    protected JPopupMenu popupMenu;
    protected DefaultTableModel dtm;
    protected RowSorter<TableModel> sorter;
    
    protected final void settings(){
        mostrarBoton(false);
    }
    
    protected final void botones(){
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        jButton1.addActionListener(a->
            new dataWindow2(new javax.swing.JFrame(),true,Integer.parseInt(dtm.getValueAt(0,0).toString()), modelo).setVisible(true)
        );
        
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
                Events.clearTableSelection(jTable1,a);
                Events.showPopup(popupMenu,a);
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
        mostrarBoton(false);
    }
    
    //Este es para buscar datos en concreto
    protected void searchData(){
        methodName="searchData";
        if(!txtBuscar.getText().isEmpty()){
            datosBuscar();
            mostrarBoton(true);
        }else{
            new logger(Level.WARNING,this.getClass().getName()).storeError18(this,methodName);
        }
    }
    
    protected final void datosMostrar(){
        methodName="datosMostrar";
        
        dtm=Events.tableModel();
        sorter=new TableRowSorter<>(dtm);
        try{
            ps=new Datos(modelo).getConnection().prepareStatement("select * from socios;");
            rs=ps.executeQuery();
            dtm.setColumnIdentifiers(header);
            while(rs.next()){
                loadData(dtm,rs);
            }
            Events.table(jTable1,sorter,dtm);
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"16");
        }
    }
    
    protected void datosBuscar(){
        methodName="datosBuscar";
        var datos=new Datos(modelo);
        dtm=Events.tableModel();
        sorter=new TableRowSorter<>(dtm);
        try{
            switch(jComboBox1.getSelectedIndex()){
                case 0->{
                    ps=datos.getConnection().prepareStatement("select * from socios where codigo_part=?;");
                    ps.setInt(1,Integer.parseInt(txtBuscar.getText()));
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    if(rs.next()){
                        loadData(dtm,rs);
                    }else{
                        new logger(Level.WARNING,this.getClass().getName()).storeError14(this,methodName);
                    }
                    Events.table(jTable1,sorter,DbUtils.resultSetToTableModel(rs),dtm);
                    
                    ps.close();
                    rs.close();
                }
                case 1->{
                    ps=datos.getConnection().prepareStatement("select * from socios where nombre_part=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    if(rs.next()){
                        loadData(dtm,rs);
                    }else{
                        new logger(Level.WARNING,this.getClass().getName()).storeError14(this,methodName);
                    }
                    Events.table(jTable1,sorter,DbUtils.resultSetToTableModel(rs),dtm);
                    
                    ps.close();
                    rs.close();
                }
                case 2->{
                    ps=datos.getConnection().prepareStatement("select * from socios where apellidop_part=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    if(rs.next()){
                        loadData(dtm,rs);
                    }else{
                        new logger(Level.WARNING,this.getClass().getName()).storeError14(this,methodName);
                    }
                    Events.table(jTable1,sorter,DbUtils.resultSetToTableModel(rs),dtm);
                    
                    ps.close();
                    rs.close();
                }
                case 3->{
                    ps=datos.getConnection().prepareStatement("select * from socios where apellidom_part=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    if(rs.next()){
                        loadData(dtm,rs);
                    }else{
                        new logger(Level.WARNING,this.getClass().getName()).storeError14(this,methodName);
                    }
                    Events.table(jTable1,sorter,DbUtils.resultSetToTableModel(rs),dtm);
                    
                    ps.close();
                    rs.close();
                }
                default->{
                    break;
                }
            }
        }catch(SQLException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"14");
        }catch(NullPointerException x){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,x,methodName,"0");
        }catch(ArrayIndexOutOfBoundsException n){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,n,methodName,"AIOOBE");
        }catch(IndexOutOfBoundsException s){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,s,methodName,"IOOBE");
        }
    }
    
    protected final void popup(){
        popupMenu=new JPopupMenu();
        
        JMenuItem mi1=new JMenuItem(new AbstractAction("Ver datos"){
            @Override
            public void actionPerformed(ActionEvent e){
                new dataWindow2(new javax.swing.JFrame(),true,Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString()), modelo).setVisible(true);
            }
        });
        
        JMenuItem mi2=new JMenuItem(new AbstractAction("Modificar datos"){
            @Override
            public void actionPerformed(ActionEvent a){
                new menuDatosVentana2(new modDatosPanel2(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString()),false,modelo),false,modelo).setVisible(true);
            }
        });
        
        JMenuItem mi3=new JMenuItem(new AbstractAction("Eliminar datos"){
            @Override
            public void actionPerformed(ActionEvent a){
                new menuDatosVentana2(new delDatosPanel2(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString()),false,modelo),false,modelo).setVisible(true);
            }
        });
        
        JMenuItem mi4=new JMenuItem(new AbstractAction("Menú"){
            @Override
            public void actionPerformed(ActionEvent a){
                new menuDatosVentana2(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString()),modelo).setVisible(true);
            }
        });
        
        popupMenu.add(mi1);
        popupMenu.add(new JSeparator());
        popupMenu.add(mi4);
        popupMenu.add(new JSeparator());
        popupMenu.add(mi2);
        popupMenu.add(mi3);
    }
    
    protected void mostrarBoton(boolean flag){
        if(flag){
            jButton1.setVisible(true);
        }
        if(!flag){
            jButton1.setVisible(false);
            textField("");
        }
    }
    
    protected void textField(String text){
        txtBuscar.setText(text);
    }
    
    protected void loadData(DefaultTableModel dtm1,ResultSet rs1) throws SQLException{
        dtm1.addRow(new Object[]{rs1.getInt("codigo_part"),rs1.getString("nombre_part"),rs1.getString("apellidop_part"),rs1.getString("apellidom_part"),rs1.getString("tipo_socio"),rs1.getString("datos_extra"),rs1.getString("fecha_ingreso"),rs1.getString("fecha_ucompra")});
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new MediaHandler(ltshPartners.class.getName()).getIconImage());

        backButton.setText("Regresar");

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Socios");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nombre", "Apellido paterno", "Apellido materno" }));

        searchButton.setText("Buscar");

        refreshButton.setText("Recargar");

        jButton1.setText("Ver datos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(refreshButton)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new ltshPartners().setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}