package venSecundarias;
//clases
import clases.datos;
import clases.guiMediaHandler;
import clases.logger;
import clases.tickets.datosTicket;
import venPrimarias.start;
import venPrimarias.ventana1;
//java
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import javax.swing.table.DefaultTableModel;

public class paymentWindow extends javax.swing.JDialog{
    public paymentWindow(java.awt.Frame parent,boolean modal){
        super(parent,modal);
        initComponents();
        new guiMediaHandler(paymentWindow.class.getName()).LookAndFeel(paymentWindow.this);
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Realizar pago");
        setResizable(false);
        pack();
    }
    
    protected DefaultTableModel dtm;
    
    protected int resultado;
    public static int result;
    
    protected boolean state=false;
    
    protected void settings(){
        dtm=new DefaultTableModel();
        dtm.setRowCount(0);
        dtm.setColumnIdentifiers(new Object[]{
            "Código del producto",
            "Nombre del producto",
            "Marca",
            "Cantidad",
            "Precio",
            "Total"
        });
        
        for(var i=0;i<ventana1.dtm.getRowCount();i++){
            dtm.addRow(new Object[]{
                ventana1.dtm.getValueAt(i,0),
                ventana1.dtm.getValueAt(i,1),
                ventana1.dtm.getValueAt(i,2),
                ventana1.dtm.getValueAt(i,3), 
                ventana1.dtm.getValueAt(i,4),
                ventana1.dtm.getValueAt(i,5)
            });
        }
        
        jLabel2.setText(String.valueOf(ventana1.codigo_emp));
        try{
            int res=0;
            for(int i=0;i<dtm.getRowCount();i++){
                int n1=Integer.parseInt(dtm.getValueAt(i,5).toString());
                res+=n1;
                
                //resultado=res;
            }
            jLabel4.setText(String.valueOf(res));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 32: "+e.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'settings()'");
            new logger(Level.SEVERE).exceptionLogger(paymentWindow.class.getName(),"settings-32",e.fillInStackTrace());
        }
        
        jTable1.setEnabled(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setModel(dtm);
    }
    
    protected final void botones(){
        calcButton.addActionListener((a)->{
            result=Integer.parseInt(jLabel4.getText());
            new calcWindow(new javax.swing.JFrame(),true).setVisible(true);
        });
        
        cancelButton.addActionListener((a)->{
            while(state==false){
                int i=JOptionPane.showConfirmDialog(null,"¿Deseas cancelar la compra?","Notice 1",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                switch(i){
                    case 0:{
                        setVisible(false);
                        dispose();
                        break;
                    }
                }
                break;
            }
            if(state==true){
                setVisible(false);
                dispose();
            }
        });
        
        jComboBox1.addActionListener((a)->{
            switch(jComboBox1.getSelectedIndex()){
                case 0:{
                    calcButton.setVisible(true);
                    jLabel5.setVisible(true);
                    jLabel6.setVisible(true);
                    jLabel8.setVisible(true);
                    break;
                }
                case 1:{
                    calcButton.setVisible(false);
                    jLabel5.setVisible(false);
                    jLabel6.setVisible(false);
                    jLabel8.setVisible(false);
                    break;
                }
            }
        });
        
        mkPaidButton.addActionListener((a)->{
            try{
                switch(jComboBox1.getSelectedIndex()){
                    case 0:{
                        /*
                        Efectivo
                        Cash
                        */
                        for(int i=0;i<dtm.getRowCount();i++){
                            int codigo_prod=Integer.parseInt(dtm.getValueAt(i,0).toString());
                            int codigo_emp=Integer.parseInt(jLabel2.getText());
                            String nombre=dtm.getValueAt(i,1).toString();
                            String marca=dtm.getValueAt(i,2).toString();
                            int cantidad=Integer.parseInt(dtm.getValueAt(i,3).toString());
                            int precio=Integer.parseInt(dtm.getValueAt(i,4).toString());
                            int total=Integer.parseInt(dtm.getValueAt(i,5).toString());
                            
                            new datos().insertarDatosProducto(codigo_prod,codigo_emp,nombre,marca,cantidad,precio,total);
                            new datos().actualizarDatosAlmacen("set cantidad=cantidad-'"+cantidad+"' where codigo_prod='"+codigo_prod+"';");
                        }
                        
                        state=true;
                        cancelButton.setText("Regresar");
                        new datosTicket().imprimirTicket(jTable1,jLabel2.getText(),Integer.parseInt(jLabel4.getText()),jComboBox1.getSelectedItem().toString(),Integer.parseInt(jLabel6.getText()),true);
                        new datos().actualizarDatosConteo("set no_ventas=no_ventas+1 where codigo_emp='"+Integer.parseInt(jLabel2.getText())+"' and fecha_sesion='"+new SimpleDateFormat("yyyy/MM/dd").format(new Date())+"';");
                        
                        JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
                        new logger(Level.INFO).staticLogger("Rel 1: se guardaron correctamente los datos a ka base de datos.\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'botones(mkPaidButton)'.\nUsuario que hizo los cambios: "+String.valueOf(start.userID));
                        break;
                    }
                    case 1:{
                        /*
                        Tarjeta
                        Card
                        */
                        for(int i=0;i<dtm.getRowCount();i++){
                            int codigo_prod=Integer.parseInt(dtm.getValueAt(i,0).toString());
                            int codigo_emp=Integer.parseInt(jLabel2.getText());
                            String nombre_prod=dtm.getValueAt(i,1).toString();
                            String marca_prod=dtm.getValueAt(i,2).toString();
                            int cantidad=Integer.parseInt(dtm.getValueAt(i,3).toString());
                            int precio=Integer.parseInt(dtm.getValueAt(i,4).toString());
                            int total=Integer.parseInt(dtm.getValueAt(i,5).toString());
                            
                            new datos().insertarDatosProducto(codigo_prod,codigo_emp,nombre_prod,marca_prod,cantidad,precio,total);
                            new datos().actualizarDatosAlmacen("set cantidad=cantidad-'"+cantidad+"' where codigo_prod='"+codigo_prod+"';");
                        }
                        /*
                        Aquí deberá ir el código para que se pague con tarjeta
                        Here will being the code for payment with card
                        */
                        state=true;
                        cancelButton.setText("Regresar");
                        new datosTicket().imprimirTicket(jTable1,jLabel2.getText(),Integer.parseInt(jLabel4.getText()),jComboBox1.getSelectedItem().toString(),false);
                        new datos().actualizarDatosConteo("set no_ventas=no_ventas+1 where codigo_emp='"+Integer.parseInt(jLabel2.getText())+"' and fecha_sesion='"+new SimpleDateFormat("yyyy/MM/dd").format(new Date())+"';");
                        
                        JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
                        new logger(Level.INFO).staticLogger("Rel 1: se guardaron correctamente los datos a ka base de datos.\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'botones(mkPaidButton)'.\nUsuario que hizo los cambios: "+String.valueOf(start.userID));
                        break;
                    }
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 32: "+e.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'botones(mkPaidButton)'");
                new logger(Level.SEVERE).exceptionLogger(paymentWindow.class.getName(),"botones.mkPaid-32",e.fillInStackTrace());
            }catch(NullPointerException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'botones(mkPaidButton)'");
                new logger(Level.SEVERE).exceptionLogger(paymentWindow.class.getName(),"botones.mkPaid-0",x.fillInStackTrace());
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelButton = new javax.swing.JButton();
        mkPaidButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        calcButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new guiMediaHandler(paymentWindow.class.getName()).getIconImage());

        cancelButton.setText("Cancelar");

        mkPaidButton.setText("Realizar pago");

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
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Empleado:");

        jLabel2.setText("jLabel2");

        jLabel3.setText("Total:");

        jLabel4.setText("jLabel4");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta" }));

        jLabel5.setText("Cambio:");

        jLabel6.setText(" ");

        jLabel7.setText("$");

        jLabel8.setText("$");

        calcButton.setText("Calcular");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mkPaidButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(calcButton)
                        .addGap(127, 127, 127)
                        .addComponent(cancelButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(mkPaidButton)
                    .addComponent(calcButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]){
        new paymentWindow(new javax.swing.JFrame(),true).setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calcButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton mkPaidButton;
    // End of variables declaration//GEN-END:variables
}