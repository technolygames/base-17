package venPrimarias;

import clases.datos;
import clases.logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.awt.Image;
import java.awt.Toolkit;
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
import venTerciarias.dataWindow1;

public final class ltshWorkers extends javax.swing.JFrame{
    public ltshWorkers(){
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error CNFE: "+e.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'ltshWorkers()'",Level.WARNING);
            new logger().exceptionLogger(ltshWorkers.class.getName(),Level.WARNING,"ltshWorkers-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IE: "+x.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'ltshWorkers()'",Level.WARNING);
            new logger().exceptionLogger(ltshWorkers.class.getName(),Level.WARNING,"ltshWorkers-IE",x.fillInStackTrace());
        }catch(IllegalAccessException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IAE: "+ñ.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'ltshWorkers()'",Level.WARNING);
            new logger().exceptionLogger(ltshWorkers.class.getName(),Level.WARNING,"ltshWorkers-IAE",ñ.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error ULAFE: "+y.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'ltshWorkers()'",Level.WARNING);
            new logger().exceptionLogger(ltshWorkers.class.getName(),Level.WARNING,"ltshWorkers-ULAFE",y.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'ltshWorkers()'",Level.WARNING);
            new logger().exceptionLogger(ltshWorkers.class.getName(),Level.WARNING,"ltshWorkers-0",k.fillInStackTrace());
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+s.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'ltshWorkers()'",Level.WARNING);
            new logger().exceptionLogger(ltshWorkers.class.getName(),Level.WARNING,"ltshWorkers-1IO",s.fillInStackTrace());
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+d.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'ltshWorkers()'",Level.WARNING);
            new logger().exceptionLogger(ltshWorkers.class.getName(),Level.WARNING,"ltshWorkers-2IO",d.fillInStackTrace());
        }
        
        datosMostrar();
        boton();
        
        setSize(1200,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Empleados");
        
        wdataButton.setVisible(false);
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
            new logger().logStaticSaver("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(ltshWorkers.class.getName(),Level.WARNING,"getIconImage-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(ltshWorkers.class.getName(),Level.WARNING,"getIconImage-2IO",x.fillInStackTrace());
        }
        return retValue;
    }
    
    protected final void boton(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        searchButton.addActionListener((ae)->{
            datosBuscar();
        });
        
        wdataButton.addActionListener((ae)->{
            new dataWindow1(new javax.swing.JFrame(),true).setVisible(true);
        });
        
        refreshButton.addActionListener((e)->{
            datosMostrar();
        });
    }
    
    protected final void datosMostrar(){
        dtm=new DefaultTableModel();
        sorter=new TableRowSorter<>(dtm);
        try{
            ps=new datos().getConnection().prepareStatement("select password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,puesto,experiencia,grado_estudios,contacto,edad,estado,fecha_registro,fecha_sesion from empleados;");
            rs=ps.executeQuery();
            dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Contacto","Edad","Estado","Fecha de registro","Fecha de sesión"});
            while(rs.next()){
                dtm.addRow(new Object[]{rs.getString("password"),rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"),rs.getString("puesto"),rs.getString("experiencia"),rs.getString("grado_estudios"),rs.getInt("contacto"),rs.getInt("edad"),rs.getString("estado"),rs.getDate("fecha_registro"),rs.getDate("fecha_sesion")});
            }
            jTable1.setRowSorter(sorter);
            jTable1.getRowSorter().toggleSortOrder(0);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setModel(dtm);
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 16",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 16: "+e.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosMostrar()'",Level.WARNING);
            new logger().exceptionLogger(ltshWorkers.class.getName(),Level.WARNING,"datosMostrar-16",e.fillInStackTrace());
        }catch(NumberFormatException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 15",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 15: "+x.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosMostrar()'",Level.WARNING);
            new logger().exceptionLogger(ltshWorkers.class.getName(),Level.WARNING,"datosMostrar-15",x.fillInStackTrace());
        }catch(NullPointerException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+ñ.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosMostrar()'",Level.WARNING);
            new logger().exceptionLogger(ltshWorkers.class.getName(),Level.WARNING,"datosMostrar-0",ñ.fillInStackTrace());
        }
    }
    
    protected final void datosBuscar(){
        dtm=new DefaultTableModel();
        sorter=new TableRowSorter<>(dtm);
        try{
            String id=txtBuscar.getText();
            int i=jComboBox1.getSelectedIndex();
            if(i==0){
                ps=new datos().getConnection().prepareStatement("select password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,puesto,experiencia,grado_estudios,contacto,edad,estado,fecha_registro,fecha_sesion from empleados where codigo_emp='"+id+"';");
                rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Contacto","Edad","Estado","Fecha de registro","Fecha de sesión"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getString("password"),rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"),rs.getString("puesto"),rs.getString("experiencia"),rs.getString("grado_estudios"),rs.getInt("contacto"),rs.getInt("edad"),rs.getString("estado"),rs.getDate("fecha_registro"),rs.getDate("fecha_sesion")});
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
                ps=new datos().getConnection().prepareStatement("select password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,puesto,experiencia,grado_estudios,contacto,edad,estado,fecha_registro,fecha_sesion from empleados where nombre_emp='"+id+"';");
                rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Contacto","Edad","Estado","Fecha de registro","Fecha de sesión"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getString("password"),rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"),rs.getString("puesto"),rs.getString("experiencia"),rs.getString("grado_estudios"),rs.getInt("contacto"),rs.getInt("edad"),rs.getString("estado"),rs.getDate("fecha_registro"),rs.getDate("fecha_sesion")});
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
                ps=new datos().getConnection().prepareStatement("select password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,puesto,experiencia,grado_estudios,contacto,edad,estado,fecha_registro,fecha_sesion from empleados where apellidop_emp='"+id+"';");
                rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Contacto","Edad","Estado","Fecha de registro","Fecha de sesión"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getString("password"),rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"),rs.getString("puesto"),rs.getString("experiencia"),rs.getString("grado_estudios"),rs.getInt("contacto"),rs.getInt("edad"),rs.getString("estado"),rs.getDate("fecha_registro"),rs.getDate("fecha_sesion")});
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
                ps=new datos().getConnection().prepareStatement("select password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,puesto,experiencia,grado_estudios,contacto,edad,estado,fecha_registro,fecha_sesion where apellidom_emp='"+id+"';");
                rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Contacto","Edad","Estado","Fecha de registro","Fecha de sesión"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getString("password"),rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"),rs.getString("puesto"),rs.getString("experiencia"),rs.getString("grado_estudios"),rs.getInt("contacto"),rs.getInt("edad"),rs.getString("estado"),rs.getDate("fecha_registro"),rs.getDate("fecha_sesion")});
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
            new logger().logStaticSaver("Error 16: "+e.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshWorkers.class.getName(),Level.WARNING,"datosBuscar-16",e.fillInStackTrace());
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshWorkers.class.getName(),Level.WARNING,"datosBuscar-0",x.fillInStackTrace());
        }catch(ArrayIndexOutOfBoundsException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error Prueba",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error Prueba: "+ñ.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshWorkers.class.getName(),Level.WARNING,"datosBuscar-Prueba",ñ.fillInStackTrace());
        }catch(IndexOutOfBoundsException p){
            JOptionPane.showMessageDialog(null,"Error:\n"+p.getMessage(),"Error 32",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 32: "+p.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshWorkers.class.getName(),Level.WARNING,"datosBuscar-32",p.fillInStackTrace());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        wdataButton = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        backButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();

        jButton1.setText("jButton1");

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Empleados");

        searchButton.setText("Buscar");

        wdataButton.setText("Ver datos");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nombre", "Apellido paterno", "Apellido materno" }));

        backButton.setText("Regresar");

        refreshButton.setText("Recargar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(wdataButton)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wdataButton)
                    .addComponent(backButton)
                    .addComponent(refreshButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        new ltshWorkers().setVisible(true);
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
    private javax.swing.JButton wdataButton;
    // End of variables declaration//GEN-END:variables
}