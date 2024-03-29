package paneles;
//clases
import clases.Datos;
import clases.Events;
import clases.logger;
import clases.mvc.Controlador;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.RowSorter;
//extension larga
import java.util.logging.Level;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public class countPanel extends javax.swing.JPanel{
    public countPanel(){
        initComponents();
        
        datosMostrar();
    }
    
    protected int codigo;
    
    public countPanel(int code){
        initComponents();
        
        this.modelo=modelo;
        this.codigo=code;
        
        datosMostrar();
    }
    
    protected Controlador modelo;
    
    public countPanel(Controlador modelo,int code){
        initComponents();
        
        this.modelo=modelo;
        this.codigo=code;
        
        datosMostrar();
    }
    
    protected PreparedStatement ps;
    protected ResultSet rs;
    
    protected DefaultTableModel dtm;
    protected RowSorter<TableModel> sorter;
    
    protected final void datosMostrar(){
        try{
            if(codigo!=0){
                ps=new Datos(modelo).getConnection().prepareStatement("select * from conteo where codigo_emp=?");
                ps.setInt(1,codigo);
                rs=ps.executeQuery();
                loadData(rs);
            }else{
                ps=new Datos(modelo).getConnection().prepareStatement("select * from conteo;");
                rs=ps.executeQuery();
                loadData(rs);
            }
        }catch(SQLException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,"datosMostrar","14");
        }
    }
    
    protected void loadData(ResultSet rs) throws SQLException{
        dtm=Events.tableModel();
        sorter=new TableRowSorter<>(dtm);
        dtm.setColumnIdentifiers(new Object[]{"Número de ventas","Fecha de sesión"});
        while(rs.next()){
            dtm.addRow(new Object[]{rs.getInt("no_ventas"),rs.getString("fecha_sesion")});
        }
        Events.table(jTable1,sorter,dtm);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}