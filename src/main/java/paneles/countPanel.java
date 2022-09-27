package paneles;

import clases.datos;
import clases.logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.RowSorter;
import javax.swing.JOptionPane;

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
        
        this.codigo=code;
        
        datosMostrar();
    }
    
    protected PreparedStatement ps;
    protected ResultSet rs;
    
    protected DefaultTableModel dtm;
    protected RowSorter<TableModel> sorter;
    
    protected void datosMostrar(){
        try{
            if(codigo!=0){
                ps=new datos().getConnection().prepareStatement("select * from conteo where codigo_emp=?");
                ps.setInt(1,codigo);
                rs=ps.executeQuery();
                loadData(rs);
            }else{
                ps=new datos().getConnection().prepareStatement("select * from conteo;");
                rs=ps.executeQuery();
                loadData(rs);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+countPanel.class.getName()+"', en el método 'datosMostrar()'");
            new logger(Level.SEVERE).exceptionLogger(countPanel.class.getName(),"datosMostrar-14",e.fillInStackTrace());
        }
    }
    
    protected void loadData(ResultSet rs) throws SQLException{
        dtm=new DefaultTableModel();
        sorter=new TableRowSorter<>(dtm);
        dtm.setColumnIdentifiers(new Object[]{"Número de ventas","Fecha de las ventas"});
        while(rs.next()){
            dtm.addRow(new Object[]{rs.getInt("no_ventas"),rs.getDate("fecha_sesion")});
        }
        jTable1.setRowSorter(sorter);
        jTable1.getRowSorter().toggleSortOrder(0);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setModel(dtm);
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