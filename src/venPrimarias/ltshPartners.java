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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;
import javax.swing.RowSorter;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
//extension larga
import java.util.logging.Level;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public class ltshPartners extends javax.swing.JFrame{
    public ltshPartners(){
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error CNFE: "+e.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'ltshPartners()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"ltshPartnert-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IE: "+x.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'ltshPartners()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"ltshPartnert-IE",x.fillInStackTrace());
        }catch(IllegalAccessException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IAE: "+n.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'ltshPartners()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"ltshPartnert-IAE",n.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 28: "+y.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'ltshPartners()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"ltshPartnert-28",y.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'ltshPartners()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"ltshPartners-0",k.fillInStackTrace());
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+s.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'ltshPartners()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"ltshPartnert-1IO",s.fillInStackTrace());
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+d.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'ltshPartners()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"ltshPartnert-2IO",d.fillInStackTrace());
        }
        
        botones();
        datosMostrar();
        
        setSize(1100,600);
        setLocationRelativeTo(null);
        setTitle("Socios");
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
        sorter=new TableRowSorter<TableModel>(dtm);
        try{
            ps=new datos().getConnection().prepareStatement("select codigo_part,nombre_part,apellidop_part,apellidom_part,tipo_socio,datos_extra,fecha_ingreso,fecha_ucompra from socios;");
            rs=ps.executeQuery();
            dtm.setColumnIdentifiers(new Object[]{"Código","Nombre","Apellido paterno","Apellido materno","Tipo de socio","Datos extra","Fecha de registro","Fecha de última compra"});
            while(rs.next()){
                dtm.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getDate(8)});
            }
            jTable1.setRowSorter(sorter);
            jTable1.getRowSorter().toggleSortOrder(0);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setModel(dtm);
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 16",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 16: "+e.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosMostrar()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"datosMostrar-16",e.fillInStackTrace());
        }
    }
    
    protected final void datosBuscar(){
        dtm=new DefaultTableModel();
        sorter=new TableRowSorter<TableModel>(dtm);
        try{
            switch(jComboBox1.getSelectedIndex()){
                case 0:
                    ps=new datos().getConnection().prepareStatement("select codigo_part,nombre_part,apellidop_part,apellidom_part,tipo_socio,datos_extra,fecha_ingreso,fecha_ucompra from socios where codigo_part="+jTextField1.getText()+";");
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código del socio","Nombre del socio","Apellido paterno","Apellido materno","Tipo de socio","Datos extra","Fegra de registro","Fecha de última compra"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getDate(8)});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger().logStaticSaver("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
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
                    ps=new datos().getConnection().prepareStatement("select codigo_part,nombre_part,apellidop_part,apellidom_part,tipo_socio,datos_extra,fecha_ingreso,fecha_ucompra from socios where nombre_part='"+jTextField1.getText()+"';");
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código del socio","Nombre del socio","Apellido paterno","Apellido materno","Tipo de socio","Datos extra","Fegra de registro","Fecha de última compra"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getDate(8)});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger().logStaticSaver("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
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
                    ps=new datos().getConnection().prepareStatement("select codigo_part,nombre_part,apellidop_part,apellidom_part,tipo_socio,datos_extra,fecha_ingreso,fecha_ucompra from socios where apellidop_part='"+jTextField1.getText()+"';");
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código del socio","Nombre del socio","Apellido paterno","Apellido materno","Tipo de socio","Datos extra","Fegra de registro","Fecha de última compra"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getDate(8)});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger().logStaticSaver("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
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
                    ps=new datos().getConnection().prepareStatement("select codigo_part,nombre_part,apellidop_part,apellidom_part,tipo_socio,datos_extra,fecha_ingreso,fecha_ucompra from socios where apellidom_part='"+jTextField1.getText()+"';");
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código del socio","Nombre del socio","Apellido paterno","Apellido materno","Tipo de socio","Datos extra","Fegra de registro","Fecha de última compra"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getDate(8)});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger().logStaticSaver("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"datosBuscar-14",e.fillInStackTrace());
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"datosBuscar-0",x.fillInStackTrace());
        }catch(ArrayIndexOutOfBoundsException p){
            JOptionPane.showMessageDialog(null,"Error:\n"+p.getMessage(),"Error Prueba",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error Prueba: "+p.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"datosBuscar-Prueba",p.fillInStackTrace());
        }catch(IndexOutOfBoundsException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 32",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 32: "+n.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"datosBuscar-32",n.fillInStackTrace());
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
        jTextField1 = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Icono().getIconImage());

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
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Socios");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código del socio", "Nombre", "Apellido paterno", "Apellido materno" }));

        searchButton.setText("Buscar");

        refreshButton.setText("Recargar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(refreshButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]){
        new ltshPartners().setVisible(true);
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