package venPrimarias;
//clases
import clases.Datos;
import clases.DbUtils;
import clases.MediaHandler;
import clases.logger;
import menus.menuDatosVentana1;
import paneles.delDatosPanel1;
import paneles.modDatosPanel1;
import venTerciarias.dataWindow1;
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
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public class ltshWorkers extends javax.swing.JFrame{
    public ltshWorkers(){
        initComponents();
        new MediaHandler(ltshWorkers.class.getName()).LookAndFeel(ltshWorkers.this);
        
        botones();
        datosMostrar();
        popup();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Empleados");
        pack();
    }
    
    protected Object[] header;
    protected String methodName;
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    protected DefaultTableModel dtm;
    protected RowSorter<TableModel> sorter;
    protected JPopupMenu popupMenu;
    
    protected final void settings(){
        mostrarBoton(false);
    }
    
    protected final void botones(){
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        jButton2.addActionListener(a->
            new dataWindow1(new javax.swing.JFrame(),true,Integer.parseInt(dtm.getValueAt(0,1).toString())).setVisible(true)
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
            new logger(Level.WARNING).storeAndViewError18(this,ltshWorkers.class.getName(),methodName);
        }
    }
    
    protected final void datosMostrar(){
        methodName="datosMostrar";
        header=new Object[]{"Contraseña","Código","Nombre(s)","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Contacto","Edad","Estado","Fecha de registro","Fecha de sesión"};
        
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
            ps=new Datos().getConnection().prepareStatement("select * from empleados;");
            rs=ps.executeQuery();
            dtm.setColumnIdentifiers(header);
            while(rs.next()){
                dtm.addRow(new Object[]{rs.getString("password"),rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"),rs.getString("puesto"),rs.getString("experiencia"),rs.getString("grado_estudios"),rs.getInt("contacto"),rs.getInt("edad"),rs.getString("estado"),rs.getString("fecha_registro"),rs.getString("fecha_sesion")});
            }
            jTable1.setRowSorter(sorter);
            jTable1.getRowSorter().toggleSortOrder(0);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setModel(dtm);
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,e,ltshWorkers.class.getName(),methodName,"16");
        }catch(NumberFormatException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,x,ltshWorkers.class.getName(),methodName,"NFE");
        }catch(NullPointerException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,n,ltshWorkers.class.getName(),methodName,"0");
        }
    }
    
    protected void datosBuscar(){
        methodName="datosBuscar";
        header=new Object[]{"Contraseña","Código","Nombre(s)","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Contacto","Edad","Estado","Fecha de registro","Fecha de sesión"};
        
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
                case 0:
                    ps=new Datos().getConnection().prepareStatement("select * from empleados where codigo_emp=?;");
                    ps.setInt(1,Integer.parseInt(txtBuscar.getText()));
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getString("password"),rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"),rs.getString("puesto"),rs.getString("experiencia"),rs.getString("grado_estudios"),rs.getInt("contacto"),rs.getInt("edad"),rs.getString("estado"),rs.getString("fecha_registro"),rs.getString("fecha_sesion")});
                    }else{
                        new logger(Level.WARNING).storeAndViewError14(this,ltshWorkers.class.getName(),methodName);
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
                case 1:
                    ps=new Datos().getConnection().prepareStatement("select * from empleados where nombre_emp=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getString("password"),rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"),rs.getString("puesto"),rs.getString("experiencia"),rs.getString("grado_estudios"),rs.getInt("contacto"),rs.getInt("edad"),rs.getString("estado"),rs.getString("fecha_registro"),rs.getString("fecha_sesion")});
                    }else{
                        new logger(Level.WARNING).storeAndViewError14(this,ltshWorkers.class.getName(),methodName);
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
                case 2:
                    ps=new Datos().getConnection().prepareStatement("select * from empleados where apellidop_emp=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getString("password"),rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"),rs.getString("puesto"),rs.getString("experiencia"),rs.getString("grado_estudios"),rs.getInt("contacto"),rs.getInt("edad"),rs.getString("estado"),rs.getString("fecha_registro"),rs.getString("fecha_sesion")});
                    }else{
                        new logger(Level.WARNING).storeAndViewError14(this,ltshWorkers.class.getName(),methodName);
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
                case 3:
                    ps=new Datos().getConnection().prepareStatement("select * from empleados where apellidom_emp=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(header);
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getString("password"),rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"),rs.getString("puesto"),rs.getString("experiencia"),rs.getString("grado_estudios"),rs.getInt("contacto"),rs.getInt("edad"),rs.getString("estado"),rs.getString("fecha_registro"),rs.getString("fecha_sesion")});
                    }else{
                        new logger(Level.WARNING).storeAndViewError14(this,ltshWorkers.class.getName(),methodName);
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
                default:
                    break;
            }
        }catch(SQLException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,e,ltshWorkers.class.getName(),methodName,"14");
        }catch(NullPointerException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,x,ltshWorkers.class.getName(),methodName,"0");
        }catch(ArrayIndexOutOfBoundsException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,n,ltshWorkers.class.getName(),methodName,"AIOOBE");
        }catch(IndexOutOfBoundsException s){
            new logger(Level.SEVERE).storeAndViewCaughtException(this,s,ltshWorkers.class.getName(),methodName,"IOOBE");
        }
    }
    
    protected final void popup(){
        popupMenu=new JPopupMenu();
        
        JMenuItem mi1=new JMenuItem(new AbstractAction("Ver datos"){
            @Override
            public void actionPerformed(ActionEvent e){
                new dataWindow1(new javax.swing.JFrame(),true,Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString())).setVisible(true);
            }
        });
        
        JMenuItem mi2=new JMenuItem(new AbstractAction("Modificar datos"){
            @Override
            public void actionPerformed(ActionEvent a){
                new menuDatosVentana1(new modDatosPanel1(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString()),false),false).setVisible(true);
            }
        });
        
        JMenuItem mi3=new JMenuItem(new AbstractAction("Eliminar datos"){
            @Override
            public void actionPerformed(ActionEvent a){
                new menuDatosVentana1(new delDatosPanel1(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString()),false),false).setVisible(true);
            }
        });
        
        JMenuItem mi4=new JMenuItem(new AbstractAction("Menú"){
            @Override
            public void actionPerformed(ActionEvent a){
                new menuDatosVentana1(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString())).setVisible(true);
            }
        });
        
        popupMenu.add(mi1);
        popupMenu.add(new JSeparator());
        popupMenu.add(mi4);
        popupMenu.add(new JSeparator());
        popupMenu.add(mi2);
        popupMenu.add(mi3);
    }
    
    protected void showPopup(MouseEvent a){
        if(a.isPopupTrigger()){
            popupMenu.show(a.getComponent(),a.getX(),a.getY());
        }
    }
    
    protected void mostrarBoton(boolean flag){
        if(flag){
            jButton2.setVisible(true);
        }
        if(!flag){
            jButton2.setVisible(false);
            textField("");
        }
    }
    
    protected void textField(String text){
        txtBuscar.setText(text);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        backButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new MediaHandler(ltshWorkers.class.getName()).getIconImage());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Empleados");

        searchButton.setText("Buscar");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nombre", "Apellido paterno", "Apellido materno" }));

        backButton.setText("Regresar");

        refreshButton.setText("Recargar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Ver datos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1188, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchButton)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(refreshButton)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new ltshWorkers().setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}