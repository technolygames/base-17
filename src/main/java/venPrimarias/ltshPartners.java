package venPrimarias;
//clases
import clases.datos;
import clases.dbUtils;
import clases.guiMediaHandler;
import clases.logger;
import menus.menuDatosVentana2;
import paneles.delDatosPanel2;
import paneles.modDatosPanel2;
import venTerciarias.dataWindow2;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.RowSorter;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JPopupMenu;
import javax.swing.JOptionPane;
//extension larga
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.util.logging.Level;
import javax.swing.AbstractAction;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public class ltshPartners extends javax.swing.JFrame{
    public ltshPartners(){
        initComponents();
        new guiMediaHandler(ltshPartners.class.getName()).LookAndFeel(ltshPartners.this);
        
        botones();
        datosMostrar();
        popup();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Socios");
        pack();
    }
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    protected DefaultTableModel dtm;
    protected RowSorter<TableModel> sorter;
    protected JPopupMenu popupMenu;
    
    public static int code;
    
    protected void settings(){
        mostrarBoton(false);
    }
    
    protected final void botones(){
        backButton.addActionListener((a)->{
            setVisible(false);
            dispose();
        });
        
        jButton1.addActionListener((a)->{
            code=Integer.parseInt(dtm.getValueAt(0,0).toString());
            new dataWindow2(new javax.swing.JFrame(),true).setVisible(true);
        });
        
        refreshButton.addActionListener((a)->{
            textField("");
            datosMostrar();
            mostrarBoton(false);
        });
        
        searchButton.addActionListener((a)->{
            if(!txtBuscar.getText().equals("")){
                datosBuscar();
                mostrarBoton(true);
            }else{
                JOptionPane.showMessageDialog(null,"Error:\nEscribe la palabra clave que deseas buscar","Error 14",JOptionPane.WARNING_MESSAGE);
                new logger(Level.WARNING).staticLogger("Error 18: no se escribió la palabra clave para hacer la búsqueda.\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'botones(searchButton)'");
            }
        });
        
        txtBuscar.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    if(!txtBuscar.getText().equals("")){
                        datosBuscar();
                        mostrarBoton(true);
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nEscribe la palabra clave que deseas buscar","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger(Level.WARNING).staticLogger("Error 18: no se escribió la palabra clave para hacer la búsqueda.\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'botones(txtBuscar)'");
                    }
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
        
        jComboBox1.addActionListener((a)->{
            int i=jComboBox1.getSelectedIndex();
            if(i>=0&&i<4){
                textField("");
                datosMostrar();
                mostrarBoton(false);
            }
        });
    }
    
    protected final void datosMostrar(){
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
            ps=new datos().getConnection().prepareStatement("select * from socios;");
            rs=ps.executeQuery();
            dtm.setColumnIdentifiers(new Object[]{"Código","Nombre(s)","Apellido paterno","Apellido materno","Tipo de socio","Datos extra","Fecha de registro","Fecha de última compra"});
            while(rs.next()){
                dtm.addRow(new Object[]{rs.getInt("codigo_part"),rs.getString("nombre_part"),rs.getString("apellidop_part"),rs.getString("apellidom_part"),rs.getString("tipo_socio"),rs.getString("datos_extra"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_ucompra")});
            }
            jTable1.setRowSorter(sorter);
            jTable1.getRowSorter().toggleSortOrder(0);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setModel(dtm);
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 16",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 16: "+e.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosMostrar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshPartners.class.getName(),"datosMostrar-16",e.fillInStackTrace());
        }
    }
    
    protected final void datosBuscar(){
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
                    ps=new datos().getConnection().prepareStatement("select * from socios where codigo_part=?;");
                    ps.setInt(1,Integer.parseInt(txtBuscar.getText()));
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código","Nombre(s)","Apellido paterno","Apellido materno","Tipo de socio","Datos extra","Fecha de registro","Fecha de última compra"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_part"),rs.getString("nombre_part"),rs.getString("apellidop_part"),rs.getString("apellidom_part"),rs.getString("tipo_socio"),rs.getString("datos_extra"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_ucompra")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con el código especificado.\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'");
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(dbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
                case 1:
                    ps=new datos().getConnection().prepareStatement("select * from socios where nombre_part=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código","Nombre(s)","Apellido paterno","Apellido materno","Tipo de socio","Datos extra","Fecha de registro","Fecha de última compra"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_part"),rs.getString("nombre_part"),rs.getString("apellidop_part"),rs.getString("apellidom_part"),rs.getString("tipo_socio"),rs.getString("datos_extra"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_ucompra")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con el nombre especificado.\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'");
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(dbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
                case 2:
                    ps=new datos().getConnection().prepareStatement("select * from socios where apellidop_part=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código","Nombre(s)","Apellido paterno","Apellido materno","Tipo de socio","Datos extra","Fecha de registro","Fecha de última compra"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_part"),rs.getString("nombre_part"),rs.getString("apellidop_part"),rs.getString("apellidom_part"),rs.getString("tipo_socio"),rs.getString("datos_extra"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_ucompra")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con el apellido paterno especificado.\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'");
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(dbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
                case 3:
                    ps=new datos().getConnection().prepareStatement("select * from socios where apellidom_part=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código","Nombre(s)","Apellido paterno","Apellido materno","Tipo de socio","Datos extra","Fecha de registro","Fecha de última compra"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_part"),rs.getString("nombre_part"),rs.getString("apellidop_part"),rs.getString("apellidom_part"),rs.getString("tipo_socio"),rs.getString("datos_extra"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_ucompra")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con el apellido materno especificado.\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'");
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(dbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
                default:
                    break;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshPartners.class.getName(),"datosBuscar-14",e.fillInStackTrace());
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshPartners.class.getName(),"datosBuscar-0",x.fillInStackTrace());
        }catch(ArrayIndexOutOfBoundsException p){
            JOptionPane.showMessageDialog(null,"Error:\n"+p.getMessage(),"Error AIOOBE",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error AIOOBE: "+p.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshPartners.class.getName(),"datosBuscar-AIOOBE",p.fillInStackTrace());
        }catch(IndexOutOfBoundsException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IOOBE",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error IOOBE: "+n.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshPartners.class.getName(),"datosBuscar-IOOBE",n.fillInStackTrace());
        }
    }
    
    protected void popup(){
        popupMenu=new JPopupMenu();
        
        JMenuItem mi1=new JMenuItem(new AbstractAction("Ver datos"){
            @Override
            public void actionPerformed(ActionEvent e){
                code=Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString());
                new dataWindow2(new javax.swing.JFrame(),true).setVisible(true);
            }
        });
        
        JMenuItem mi2=new JMenuItem(new AbstractAction("Modificar datos"){
            @Override
            public void actionPerformed(ActionEvent a){
                new menuDatosVentana2(new modDatosPanel2(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString()))).setVisible(true);
            }
        });
        
        JMenuItem mi3=new JMenuItem(new AbstractAction("Eliminar datos"){
            @Override
            public void actionPerformed(ActionEvent a){
                new menuDatosVentana2(new delDatosPanel2(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString()))).setVisible(true);
            }
        });
        
        popupMenu.add(mi1);
        popupMenu.add(new JSeparator());
        popupMenu.add(mi2);
        popupMenu.add(mi3);
    }
    
    protected void showPopup(MouseEvent a){
        if(a.isPopupTrigger()){
            popupMenu.show(a.getComponent(),a.getX(),a.getY());
        }
    }
    
    protected void textField(String text){
        txtBuscar.setText(text);
    }
    
    protected void mostrarBoton(boolean flag){
        if(flag==true){
            jButton1.setVisible(true);
        }if(flag==false){
            jButton1.setVisible(false);
            txtBuscar.setText("");
        }
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
        setIconImage(new guiMediaHandler(ltshPartners.class.getName()).getIconImage());

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
    
    public static void main(String args[]){
        new ltshPartners().setVisible(true);
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