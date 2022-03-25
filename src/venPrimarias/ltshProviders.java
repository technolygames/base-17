package venPrimarias;
//clases
import clases.datos;
import clases.Icono;
import clases.logger;
//librerías
import net.proteanit.sql.DbUtils;
//java
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
//extension larga
import java.util.logging.Level;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public class ltshProviders extends javax.swing.JFrame{
    public ltshProviders(){
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error CNFE: "+e.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'ltshProviders()'",Level.WARNING);
            new logger().exceptionLogger(ltshProviders.class.getName(),Level.WARNING,"ltshProviders-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error iE: "+x.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'ltshProviders()'",Level.WARNING);
            new logger().exceptionLogger(ltshProviders.class.getName(),Level.WARNING,"ltshProviders-iE",x.fillInStackTrace());
        }catch(IllegalAccessException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IAE: "+n.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'ltshProviders()'",Level.WARNING);
            new logger().exceptionLogger(ltshProviders.class.getName(),Level.WARNING,"ltshProviders-IAE",n.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException j){
            JOptionPane.showMessageDialog(null,"Error:\n"+j.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 28: "+j.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'ltshProviders()'",Level.WARNING);
            new logger().exceptionLogger(ltshProviders.class.getName(),Level.WARNING,"ltshProviders-28",j.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'ltshProviders()'",Level.WARNING);
            new logger().exceptionLogger(ltshProviders.class.getName(),Level.WARNING,"ltshProviders-0",k.fillInStackTrace());
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+s.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'ltshProviders()'",Level.WARNING);
            new logger().exceptionLogger(ltshProviders.class.getName(),Level.WARNING,"ltshProviders-1IO",s.fillInStackTrace());
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+d.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'ltshProviders()'",Level.WARNING);
            new logger().exceptionLogger(ltshProviders.class.getName(),Level.WARNING,"ltshProviders-2IO",d.fillInStackTrace());
        }
        
        botones();
        datosMostrar();
        
        setSize(950,500);
        setLocationRelativeTo(null);
        setTitle("Productos vendidos");
        setResizable(false);
    }
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    protected DefaultTableModel dtm;
    protected RowSorter<TableModel> sorter;
    
    protected final void botones(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        refreshButton.addActionListener((ae)->{
            datosMostrar();
        });
        
        searchButton.addActionListener((ae)->{
            datosBuscar();
        });
    }
    
    protected final void datosMostrar(){
        dtm=new DefaultTableModel();
        sorter=new TableRowSorter<>(dtm);
        try{
            ps=new datos().getConnection().prepareStatement("select codigo_prov,nombre_prov,apellidop_prov,apellidom_prov,empresa,contacto,fecha_ingreso,fecha_uentrega from proveedor;");
            rs=ps.executeQuery();
            dtm.setColumnIdentifiers(new Object[]{"Código del proveedor","Nombre","Apellido paterno","Apellido materno","Empresa","Contacto","Fecha de registro","Fecha de última entrega"});
            while(rs.next()){
                dtm.addRow(new Object[]{rs.getInt("codigo_prov"),rs.getString("nombre_prov"),rs.getString("apellidop_prov"),rs.getString("apellidom_prov"),rs.getString("empresa"),rs.getInt("contacto"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_uentrega")});
            }
            jTable1.setRowSorter(sorter);
            jTable1.getRowSorter().toggleSortOrder(0);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setModel(dtm);
            jTable1.getModel();
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 16",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 16: "+e.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'datosMostrar()'",Level.WARNING);
            new logger().exceptionLogger(ltshProviders.class.getName(),Level.WARNING,"datosMostrar-16",e.fillInStackTrace());
        }
    }
    
    protected final void datosBuscar(){
        dtm=new DefaultTableModel();
        sorter=new TableRowSorter<>(dtm);
        try{
            String id=jTextField1.getText();
            int i=jComboBox1.getSelectedIndex();
            if(i==0){
                ps=new datos().getConnection().prepareStatement("select codigo_prov,nombre_prov,apellidop_prov,apellidom_prov,empresa,contacto from proveedor where codigo_prov='"+id+"';");
                rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Código del proveedor","Nombre","Apellido paterno","Apellido materno","Empresa","Contacto","Fecha de registro","Fecha de última entrega"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getInt("codigo_prov"),rs.getString("nombre_prov"),rs.getString("apellidop_prov"),rs.getString("apellidom_prov"),rs.getString("empresa"),rs.getInt("contacto"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_uentrega")});
                }
                jTable1.setRowSorter(sorter);
                jTable1.getRowSorter().toggleSortOrder(0);
                jTable1.getTableHeader().setReorderingAllowed(false);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                jTable1.setModel(dtm);
                
                ps.close();
                rs.close();
            }
            if(i==1){
                ps=new datos().getConnection().prepareStatement("select codigo_prov,nombre_prov,apellidop_prov,apellidom_prov,empresa,contacto from proveedor where nombre_prov='"+id+"';");
                rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Código del proveedor","Nombre","Apellido paterno","Apellido materno","Empresa","Contacto","Fecha de registro","Fecha de última entrega"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getInt("codigo_prov"),rs.getString("nombre_prov"),rs.getString("apellidop_prov"),rs.getString("apellidom_prov"),rs.getString("empresa"),rs.getInt("contacto"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_uentrega")});
                }
                jTable1.setRowSorter(sorter);
                jTable1.getRowSorter().toggleSortOrder(0);
                jTable1.getTableHeader().setReorderingAllowed(false);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                jTable1.setModel(dtm);
                
                ps.close();
                rs.close();
            }
            if(i==2){
                ps=new datos().getConnection().prepareStatement("select codigo_prov,nombre_prov,apellidop_prov,apellidom_prov,empresa,contacto from proveedor where apellidop_prov='"+id+"';");
                rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Código del proveedor","Nombre","Apellido paterno","Apellido materno","Empresa","Contacto","Fecha de registro","Fecha de última entrega"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getInt("codigo_prov"),rs.getString("nombre_prov"),rs.getString("apellidop_prov"),rs.getString("apellidom_prov"),rs.getString("empresa"),rs.getInt("contacto"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_uentrega")});
                }
                jTable1.setRowSorter(sorter);
                jTable1.getRowSorter().toggleSortOrder(0);
                jTable1.getTableHeader().setReorderingAllowed(false);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                jTable1.setModel(dtm);
                
                ps.close();
                rs.close();
            }
            if(i==3){
                ps=new datos().getConnection().prepareStatement("select codigo_prov,nombre_prov,apellidop_prov,apellidom_prov,empresa,contacto from proveedor where apellidom_prov='"+id+"';");
                rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Código del proveedor","Nombre","Apellido paterno","Apellido materno","Empresa","Contacto","Fecha de registro","Fecha de última entrega"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getInt("codigo_prov"),rs.getString("nombre_prov"),rs.getString("apellidop_prov"),rs.getString("apellidom_prov"),rs.getString("empresa"),rs.getInt("contacto"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_uentrega")});
                }
                jTable1.setRowSorter(sorter);
                jTable1.getRowSorter().toggleSortOrder(0);
                jTable1.getTableHeader().setReorderingAllowed(false);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                jTable1.setModel(dtm);
                
                ps.close();
                rs.close();
            }
            if(i==4){
                ps=new datos().getConnection().prepareStatement("select codigo_prov,nombre_prov,apellidop_prov,apellidom_prov,empresa,contacto from proveedor where empresa='"+id+"';");
                rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Código del proveedor","Nombre","Apellido paterno","Apellido materno","Empresa","Contacto","Fecha de registro","Fecha de última entrega"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getInt("codigo_prov"),rs.getString("nombre_prov"),rs.getString("apellidop_prov"),rs.getString("apellidom_prov"),rs.getString("empresa"),rs.getInt("contacto"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_uentrega")});
                }
                jTable1.setRowSorter(sorter);
                jTable1.getRowSorter().toggleSortOrder(0);
                jTable1.getTableHeader().setReorderingAllowed(false);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                jTable1.setModel(dtm);
                
                ps.close();
                rs.close();
            }
            if(i==5){
                ps=new datos().getConnection().prepareStatement("select codigo_prov,nombre_prov,apellidop_prov,apellidom_prov,empresa,contacto from proveedor where contacto='"+id+"';");
                rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Código del proveedor","Nombre","Apellido paterno","Apellido materno","Empresa","Contacto","Fecha de registro","Fecha de última entrega"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getInt("codigo_prov"),rs.getString("nombre_prov"),rs.getString("apellidop_prov"),rs.getString("apellidom_prov"),rs.getString("empresa"),rs.getInt("contacto"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_uentrega")});
                }
                jTable1.setRowSorter(sorter);
                jTable1.getRowSorter().toggleSortOrder(0);
                jTable1.getTableHeader().setReorderingAllowed(false);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                jTable1.setModel(dtm);
                
                ps.close();
                rs.close();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 16",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 16: "+e.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshProviders.class.getName(),Level.WARNING,"datosBuscar-16",e.fillInStackTrace());
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshProviders.class.getName(),Level.WARNING,"datosBuscar-0",x.fillInStackTrace());
        }catch(ArrayIndexOutOfBoundsException p){
            JOptionPane.showMessageDialog(null,"Error:\n"+p.getMessage(),"Error AIOOBE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error AIOOBE: "+p.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshProviders.class.getName(),Level.WARNING,"datosBuscar-AIOOBE",p.fillInStackTrace());
        }catch(IndexOutOfBoundsException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 32",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 32: "+n.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshProviders.class.getName(),Level.WARNING,"datosBuscar-32",n.fillInStackTrace());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Icono().getIconImage());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Proveedores");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nombre", "Apellido paterno", "Apellido materno", "Empresa", "Contacto" }));

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

        backButton.setText("Regresar");

        searchButton.setText("Buscar");

        refreshButton.setText("Recargar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(refreshButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]){
        new ltshProviders().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}