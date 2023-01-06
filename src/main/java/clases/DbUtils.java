package clases;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
//extension larga
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase hace el manejo de datos para mostrarlos, según el resultado, en la tabla destino.
 * 
 * @author unknown
 */
public class DbUtils{
    protected DbUtils(){}
    public static TableModel resultSetToTableModel(ResultSet rs) throws SQLException{
        ResultSetMetaData metaData=rs.getMetaData();
        int numberOfColumns=metaData.getColumnCount();
        Vector<String> columnNames=new Vector<>();
        // Get the column names
        for(int column=0;column<numberOfColumns;column++){
            columnNames.addElement(metaData.getColumnLabel(column+1));
        }
        
        // Get all rows.
        Vector<Vector<Object>> rows=new Vector<>();
        while(rs.next()){
            Vector<Object> newRow=new Vector<>();
            for(int i=1;i<=numberOfColumns;i++){
                newRow.addElement(rs.getObject(i));
            }
            rows.addElement(newRow);
        }
        
        return new DefaultTableModel(rows,columnNames);
    }
    
    public static List<List<Object>> resultSetToNestedList(ResultSet rs,boolean includeColumnNames) throws SQLException{
        // To contain all rows.
        List<List<Object>> rows=new ArrayList<>();
        ResultSetMetaData metaData=rs.getMetaData();
        int numberOfColumns=metaData.getColumnCount();
        
        // Include column headers as first row if required
        if(includeColumnNames){
            List<Object> columnNames=new ArrayList<>();
            // Get the column names
            for(int column=0;column<numberOfColumns;column++){
                columnNames.add(metaData.getColumnLabel(column+1));
            }
            rows.add(columnNames);
        }
        
        // Get the data
        while(rs.next()){
            List<Object> newRow=new ArrayList<>();
            for(int i=1;i<=numberOfColumns;i++){
                newRow.add(rs.getObject(i));
            }
            
            rows.add(newRow);
        }
        
        return rows;
    }

    public static List<List<Object>> resultSetToNestedList(ResultSet rs) throws SQLException{
        // To contain all rows.
        List<List<Object>> rows=new ArrayList<>();
        ResultSetMetaData metaData=rs.getMetaData();
        int numberOfColumns=metaData.getColumnCount();
        
        // Get the data
        while(rs.next()){
            List<Object> newRow=new ArrayList<>();
            for(int i=1;i<=numberOfColumns;i++){
                newRow.add(rs.getObject(i));
            }
            
            rows.add(newRow);
        }
        
        return rows;
    }
}
