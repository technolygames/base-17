package venPrimarias;

import clases.datos;
import clases.logger;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.UIManager;
import javax.swing.RowSorter;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import net.proteanit.sql.DbUtils;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public final class ltshData extends javax.swing.JFrame{
    public ltshData(){
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error CNFE: "+e.getMessage()+".\nOcurrió en la clase '"+ltshData.class.getName()+"', en el método 'ltshData()'",Level.WARNING);
            new logger().exceptionLogger(ltshData.class.getName(),Level.WARNING,"ltshData-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IE: "+x.getMessage()+".\nOcurrió en la clase '"+ltshData.class.getName()+"', en el método 'ltshData()'",Level.WARNING);
            new logger().exceptionLogger(ltshData.class.getName(),Level.WARNING,"ltshData-IE",x.fillInStackTrace());
        }catch(IllegalAccessException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IAE: "+n.getMessage()+".\nOcurrió en la clase '"+ltshData.class.getName()+"', en el método 'ltshData()'",Level.WARNING);
            new logger().exceptionLogger(ltshData.class.getName(),Level.WARNING,"ltshData-IAE",n.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 28: "+y.getMessage()+".\nOcurrió en la clase '"+ltshData.class.getName()+"', en el método 'ltshData()'",Level.WARNING);
            new logger().exceptionLogger(ltshData.class.getName(),Level.WARNING,"ltshData-28",y.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+ltshData.class.getName()+"', en el método 'ltshData()'",Level.WARNING);
            new logger().exceptionLogger(ltshData.class.getName(),Level.WARNING,"ltshData-0",k.fillInStackTrace());
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+s.getMessage()+".\nOcurrió en la clase '"+ltshData.class.getName()+"', en el método 'ltshData()'",Level.WARNING);
            new logger().exceptionLogger(ltshData.class.getName(),Level.WARNING,"ltshData-1IO",s.fillInStackTrace());
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+d.getMessage()+".\nOcurrió en la clase '"+ltshData.class.getName()+"', en el método 'ltshData()'",Level.WARNING);
            new logger().exceptionLogger(ltshData.class.getName(),Level.WARNING,"ltshData-2IO",d.fillInStackTrace());
        }
        
        datosMostrar();
        botones();
        
        setSize(1000,600);
        setResizable(false);
        setTitle("Almacén");
        setLocationRelativeTo(null);
    }
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    protected DefaultTableModel dtm;
    protected RowSorter<TableModel> sorter;
    
    protected Image retValue;
    protected Properties p;
    
    @Override
    public Image getIconImage(){
        p=new Properties();
        try{
            p.load(new FileInputStream("src/data/config/config.properties"));
            retValue=Toolkit.getDefaultToolkit().getImage(p.getProperty("icono"));
            retValue.flush();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+ltshData.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(ltshData.class.getName(),Level.WARNING,"getIconImage-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+ltshData.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(ltshData.class.getName(),Level.WARNING,"getIconImage-2IO",x.fillInStackTrace());
        }
        return retValue;
    }
    
    protected final void botones(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        refreshButton.addActionListener((a)->{
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
            ps=new datos().getConnection().prepareStatement("select * from almacen;");
            rs=ps.executeQuery();
            dtm.setColumnIdentifiers(new Object[]{"Código de almacén","Código del producto","Código del proveedor","Nombre del producto","Nombre del proveedor","Marca","Stock","Fecha de ingreso"});
            while(rs.next()){
                dtm.addRow(new Object[]{rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
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
            new logger().logStaticSaver("Error 16: "+e.getMessage()+".\nOcurrió en la clase '"+ltshData.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshData.class.getName(),Level.WARNING,"datosBuscar-16",e.fillInStackTrace());
        }
    }
    
    protected final void datosBuscar(){
        dtm=new DefaultTableModel();
        sorter=new TableRowSorter<>(dtm);
        try{
            String id=txtBuscar.getText();
            int i=jComboBox1.getSelectedIndex();
            if(i==0){
                ps=new datos().getConnection().prepareStatement("select * from almacen where codigo_alm="+id+";");
                rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Código de almacén","Código del producto","Código del proveedor","Nombre del producto","Nombre del proveedor","Marca","Stock","Fecha de ingreso"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
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
                ps=new datos().getConnection().prepareStatement("select * from almacen where codigo_prod="+id+";");
                rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Código de almacén","Código del producto","Código del proveedor","Nombre del producto","Nombre del proveedor","Marca","Stock","Fecha de ingreso"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
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
                ps=new datos().getConnection().prepareStatement("select * from almacen where codigo_prov="+id+";");
                rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Código de almacén","Código del producto","Código del proveedor","Nombre del producto","Nombre del proveedor","Marca","Stock","Fecha de ingreso"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
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
                ps=new datos().getConnection().prepareStatement("select * from almacen where nombre_prod='"+id+"';");
                rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Código de almacén","Código del producto","Código del proveedor","Nombre del producto","Nombre del proveedor","Marca","Stock","Fecha de ingreso"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
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
                ps=new datos().getConnection().prepareStatement("select * from almacen where nombre_prov='"+id+"';");
                rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Código de almacén","Código del producto","Código del proveedor","Nombre del producto","Nombre del proveedor","Marca","Stock","Fecha de ingreso"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
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
                ps=new datos().getConnection().prepareStatement("select * from almacen where marca_prod='"+id+"';");
                rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Código de almacén","Código del producto","Código del proveedor","Nombre del producto","Nombre del proveedor","Marca","Stock","Fecha de ingreso"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
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
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+ltshData.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshData.class.getName(),Level.WARNING,"datosBuscar-14",e.fillInStackTrace());
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+ltshData.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshData.class.getName(),Level.WARNING,"datosBuscar-0",x.fillInStackTrace());
        }catch(ArrayIndexOutOfBoundsException p){
            JOptionPane.showMessageDialog(null,"Error:\n"+p.getMessage(),"Error Prueba",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error Prueba: "+p.getMessage()+".\nOcurrió en la clase '"+ltshData.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshData.class.getName(),Level.WARNING,"datosBuscar-Prueba",p.fillInStackTrace());
        }catch(IndexOutOfBoundsException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 32",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 32: "+n.getMessage()+".\nOcurrió en la clase '"+ltshData.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshData.class.getName(),Level.WARNING,"datosBuscar-32",n.fillInStackTrace());
        }
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
        setIconImage(getIconImage());

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel3.setText("Almacén");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código de almacén", "Código del producto", "Código del proveedor", "Nombre del producto", "Nombre del proveedor", "Marca" }));

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
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(refreshButton, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        new ltshData().setVisible(true);
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