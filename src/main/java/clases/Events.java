package clases;
//java
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.JPopupMenu;
//extension larga
import java.awt.event.MouseEvent;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

/**
 * Clase encargada de manejar eventos de ventana.
 * 
 * @author erick
 */
public class Events{
    protected Events(){}
    
    /**
     * Método usado para mostrar un menú popup.
     * 
     * @param pm menú popup.
     * @param me evento del ratón.
     */
    public static void showPopup(JPopupMenu pm,MouseEvent me){
        if(me.isPopupTrigger()){
            pm.show(me.getComponent(),me.getX(),me.getY());
        }
    }
    
    /**
     * Método usado para crear una instancia de DefaultTableModel con bloqueo para editar celdas.
     * Este método se usa con frecuencia en algunas ventanas
     * 
     * @return instancia de la clase DefaultTableModel
     */
    public static DefaultTableModel tableModel(){
        DefaultTableModel dtm=new DefaultTableModel(){
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
        
        return dtm;
    }
    
    /**
     * Método usado para limpiar la selección de una celda en una tabla.
     * 
     * @param table en la que se limpiará la selección de la celda.
     * @param me evento del ratón.
     */
    public static void clearTableSelection(JTable table,MouseEvent me){
        int row=table.rowAtPoint(me.getPoint());
        if(row>=0&&row<table.getRowCount()){
            table.setRowSelectionInterval(row,row);
        }else{
            table.clearSelection();
        }
    }
    
    /**
     * Método encargado de establecer los parámetros necesarios para usar las funciones de una tabla, como ordenar los datos de las filas.
     * 
     * @param table a la que se le darán los parámetros.
     * @param rws RowSorter que usará la tabla.
     * @param dtm modelo que usará la tabla.
     */
    public static void table(JTable table,RowSorter<TableModel> rws,DefaultTableModel dtm){
        table.setRowSorter(rws);
        table.getRowSorter().toggleSortOrder(0);
        table.getTableHeader().setReorderingAllowed(false);
        table.setModel(dtm);
    }
    
    /**
     * Método encargado de establecer los parámetros necesarios para usar las funciones de una tabla, como:
     * <ul>
     * <li>ordenar los datos de las filas.</li>
     * <li>al realizar búsqueda de datos específicos, estos los agrega a la tabla destino.</li>
     * </ul>
     * 
     * @param table a la que se le darán los parámetros y los valores de la búsqueda realizada.
     * @param rws RowSorter que usará la tabla.
     * @param tm modelo con el que se mostrarán los datos que se obtuvieron de la búsqueda de registros específicos.
     * @param dtm modelo que usará la tabla.
     */
    public static void table(JTable table,RowSorter<TableModel> rws,TableModel tm,DefaultTableModel dtm){
        table.setRowSorter(rws);
        table.getRowSorter().toggleSortOrder(0);
        table.getTableHeader().setReorderingAllowed(false);
        table.setModel(tm);
        table.setModel(dtm);
    }
}
