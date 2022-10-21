package venSecundarias;
//clases
import clases.datos;
import clases.guiMediaHandler;
import clases.logger;
import clases.tickets.datosTicket;
import java.awt.EventQueue;
import venPrimarias.start;
import venPrimarias.ventana1;
//java
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
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
    
    protected String nombre;
    protected String marca;
    
    protected int codigo_prod;
    protected int codigo_emp;
    protected int resultado;
    protected int cantidad;
    protected int precio;
    public static int result;
    protected int total;
    
    protected boolean estado=false;
    
    protected final void settings(){
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
        
        calc1();
        
        jTable1.setEnabled(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setModel(dtm);
        jTextField1.setEnabled(false);
    }
    
    protected final void botones(){
        cbAddCoupon.addActionListener((a)->{
            if(cbAddCoupon.isSelected()){
                jTextField1.setEnabled(true);
                if(!jTextField1.getText().equals("")){
                    calc2();
                }else{
                    //do nothing
                }
            }else if(!cbAddCoupon.isSelected()){
                jTextField1.setEnabled(false);
                calc1();
            }
        });
        
        calcButton.addActionListener((a)->{
            result=Integer.parseInt(jLabel4.getText());
            new calcWindow(new javax.swing.JFrame(),true).setVisible(true);
        });
        
        cancelButton.addActionListener((a)->{
            if(estado){
                int i=JOptionPane.showConfirmDialog(this,"¿Deseas cancelar la compra?","Notice 1",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if(i==0){
                    setVisible(false);
                    dispose();
                }
            }else{
                setVisible(false);
                dispose();
            }
        });
        
        jTextField1.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    calc2();
                }
            }
        });
        
        jComboBox1.addActionListener((a)->{
            JLabel[] etiquetas={jLabel5,jLabel6,jLabel8};
            switch(jComboBox1.getSelectedIndex()){
                case 0:{
                    for(JLabel label:etiquetas){
                        label.setVisible(true);
                    }
                    calcButton.setVisible(true);
                    break;
                }
                case 1:{
                    for(JLabel label:etiquetas){
                        label.setVisible(false);
                    }
                    calcButton.setVisible(false);
                    break;
                }
            }
        });
        
        mkPaidButton.addActionListener((a)->{
            var ticket=new datosTicket();
            try{
                switch(jComboBox1.getSelectedIndex()){
                    case 0:{
                        /*
                        Efectivo
                        Cash
                        */
                        confirmPurchase();
                        windowState();
                        
                        ticket.imprimirTicket(jTable1,jLabel2.getText(),Integer.parseInt(jLabel4.getText()),jComboBox1.getSelectedItem().toString(),Integer.parseInt(jLabel6.getText()),true);
                        break;
                    }
                    case 1:{
                        /*
                        Tarjeta
                        Card
                        */
                        confirmPurchase();
                        windowState();
                        /*
                        Aquí deberá ir el código para que se pague con tarjeta
                        Here will being the code for payment with card
                        */
                        ticket.imprimirTicket(jTable1,jLabel2.getText(),Integer.parseInt(jLabel4.getText()),jComboBox1.getSelectedItem().toString(),false);
                        break;
                    }
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 32: "+e.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'botones(mkPaidButton)'");
                new logger(Level.SEVERE).exceptionLogger(paymentWindow.class.getName(),"botones.mkPaid-32",e.fillInStackTrace());
            }catch(NullPointerException x){
                JOptionPane.showMessageDialog(this,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'botones(mkPaidButton)'");
                new logger(Level.SEVERE).exceptionLogger(paymentWindow.class.getName(),"botones.mkPaid-0",x.fillInStackTrace());
            }
        });
    }
    
    protected void calc1(){
        jLabel2.setText(String.valueOf(ventana1.codigo_emp));
        try{
            int res=0;
            for(int i=0;i<dtm.getRowCount();i++){
                int n1=Integer.parseInt(dtm.getValueAt(i,5).toString());
                res+=n1;
            }
            jLabel4.setText(String.valueOf(res));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 32: "+e.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'calc1()'");
            new logger(Level.SEVERE).exceptionLogger(paymentWindow.class.getName(),"calc1-32",e.fillInStackTrace());
        }
    }
    
    protected void calc2(){
        try{
            ResultSet rs=new datos().buscarDatosPromo(jTextField1.getText());
            if(rs.next()){
                var cal=Integer.parseInt(jLabel4.getText())*rs.getFloat("descuento");
                var cal2=Integer.parseInt(jLabel4.getText())-cal;
                jLabel4.setText(String.valueOf(Math.round(cal2)));
            }else{
                JOptionPane.showMessageDialog(this,"Error:\nEscribe correctamente el código de descuento","Error 14",JOptionPane.WARNING_MESSAGE);
                new logger(Level.WARNING).staticLogger("Error 14: no existe el registro en la base de datos.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(addButton)'");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'calc2()'");
            new logger(Level.SEVERE).exceptionLogger(paymentWindow.class.getName(),"calc2-14",e.fillInStackTrace());
        }
    }
    
    protected void confirmPurchase(){
        if(!jTextField1.getText().isEmpty()){
            readTable();
            new datos().actualizarDatosUsoPromo(jTextField1.getText());
        }else{
            readTable();
        }
    }
    
    protected void readTable(){
        var datos=new datos();
        for(int i=0;i<dtm.getRowCount();i++){
            codigo_prod=Integer.parseInt(dtm.getValueAt(i,0).toString());
            codigo_emp=Integer.parseInt(jLabel2.getText());
            nombre=dtm.getValueAt(i,1).toString();
            marca=dtm.getValueAt(i,2).toString();
            cantidad=Integer.parseInt(dtm.getValueAt(i,3).toString());
            precio=Integer.parseInt(dtm.getValueAt(i,4).toString());
            total=Integer.parseInt(dtm.getValueAt(i,5).toString());
            
            datos.insertarDatosProducto(codigo_prod,codigo_emp,nombre,marca,cantidad,precio,total);
            datos.actualizarDatosAlmacen(cantidad,codigo_prod);
        }
        datos.actualizarDatosConteoVentas(codigo_emp,new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        
        JOptionPane.showMessageDialog(this,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
        new logger(Level.INFO).staticLogger("Rel 1: se guardaron correctamente los datos a ka base de datos.\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'readTable()'.\nUsuario que hizo los cambios: "+String.valueOf(start.userID));
    }
    
    protected void windowState(){
        estado=true;
        cancelButton.setText("Regresar");
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
        jTextField1 = new javax.swing.JTextField();
        cbAddCoupon = new javax.swing.JCheckBox();

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

        cbAddCoupon.setText("Agregar cupón");

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
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(cbAddCoupon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbAddCoupon)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        EventQueue.invokeLater(()->{
            new paymentWindow(new javax.swing.JFrame(),true).setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calcButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox cbAddCoupon;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton mkPaidButton;
    // End of variables declaration//GEN-END:variables
}