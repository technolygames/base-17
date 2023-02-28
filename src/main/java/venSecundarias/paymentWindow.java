package venSecundarias;
//clases
import clases.Datos;
import clases.Events;
import clases.MediaHandler;
import clases.Validation;
import clases.logger;
import clases.mvc.Controlador;
import clases.tickets.DatosTicket;
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
        new MediaHandler(paymentWindow.class.getName()).setLookAndFeel(paymentWindow.this);
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Realizar pago");
        setResizable(false);
        pack();
    }
    
    protected DefaultTableModel dtm0;
    
    protected Controlador modelo;
    
    public paymentWindow(java.awt.Frame parent,boolean modal,Controlador modelo,DefaultTableModel dtm){
        super(parent,modal);
        initComponents();
        new MediaHandler(paymentWindow.class.getName()).setLookAndFeel(paymentWindow.this);
        
        this.dtm0=dtm;
        this.modelo=modelo;
        
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
    protected String methodName;
    
    protected int codigoProducto;
    protected int codigoEmpleado;
    protected int resultado;
    protected int cantidad;
    protected int precio;
    public static int result;
    protected int total;
    
    protected boolean estado=false;
    
    protected final void settings(){
        dtm=new DefaultTableModel();
        dtm.setRowCount(0);
        
        /**
         * Cabecera de la tabla
         * Table header
         */
        dtm.setColumnIdentifiers(new Object[]{
            "Código del producto",
            "Nombre del producto",
            "Marca",
            "Cantidad",
            "Precio",
            "Total"
        });
        
       /**
        * Obtiene los datos de la tabla en la ventana de ventas
        * Gets data from table sales window
        */
        for(var i=0;i<dtm0.getRowCount();i++){
            dtm.addRow(new Object[]{
                dtm0.getValueAt(i,0),
                dtm0.getValueAt(i,1),
                dtm0.getValueAt(i,2),
                dtm0.getValueAt(i,3), 
                dtm0.getValueAt(i,4),
                dtm0.getValueAt(i,5)
            });
        }
        
        calc1();
        
        jTable1.setEnabled(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setModel(dtm);
        jTextField1.setEnabled(false);
    }
    
    protected final void botones(){
        cbAddCoupon.addActionListener(a->{
            if(cbAddCoupon.isSelected()){
                jTextField1.setEnabled(true);
                if(!jTextField1.getText().equals("")){
                    calc2();
                }else{
                    //do nothing
                }
            }else{
                jTextField1.setEnabled(false);
                calc1();
            }
        });
        
        calcButton.addActionListener(a->{
            result=Integer.parseInt(jLabel4.getText());
            new calcWindow(new javax.swing.JFrame(),true).setVisible(true);
        });
        
        cancelButton.addActionListener(a->{
            if(estado){
                if(JOptionPane.showConfirmDialog(this,"¿Deseas cancelar la compra?","Notice 1",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==0){
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
        
        jComboBox1.addActionListener(a->{
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
        
        mkPaidButton.addActionListener(a->{
            methodName="botones.mkPaid";
            var ticket=new DatosTicket();
            
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
                        ticket.imprimirTicket(jTable1,jLabel2.getText(),Integer.parseInt(jLabel4.getText()),jComboBox1.getSelectedItem().toString(),Integer.parseInt(jLabel6.getText()),false);
                        break;
                    }
                }
            }catch(NumberFormatException e){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"32");
            }catch(NullPointerException x){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,x,methodName,"0");
            }catch(SQLException n){
                new logger(Level.SEVERE,this.getClass().getName()).catchException(this,n,methodName,"12");
            }
        });
    }
    
    protected void calc1(){
        methodName="calc1";
        
        jLabel2.setText(String.valueOf(modelo.getUserID()));
        try{
            int res=0;
            for(int i=0;i<dtm.getRowCount();i++){
                int n1=Integer.parseInt(dtm.getValueAt(i,5).toString());
                res+=n1;
            }
            jLabel10.setText(String.valueOf(res));
        }catch(NumberFormatException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"32");
        }
    }
    
    protected void calc2(){
        methodName="calc2";
        
        try{
            ResultSet rs=new Datos(modelo).buscarDatosPromo(jTextField1.getText());
            if(rs.next()){
                var desc=rs.getFloat("descuento");
                var cal=Integer.parseInt(jLabel4.getText())*desc;
                var cal2=Integer.parseInt(jLabel4.getText())-cal;
                
                jLabel12.setText(String.valueOf(desc));
                jLabel4.setText(String.valueOf(Math.round(cal2)));
            }else{
                new logger(Level.WARNING,this.getClass().getName()).storeError14(this,methodName);
            }
        }catch(SQLException e){
            new logger(Level.SEVERE,this.getClass().getName()).catchException(this,e,methodName,"14");
        }
    }
    
    protected void confirmPurchase() throws SQLException{
        if(!jTextField1.getText().isEmpty()){
            readTable();
            new Datos(modelo).actualizarDatosUsoPromo(jTextField1.getText());
        }else{
            readTable();
        }
    }
    
    protected void readTable() throws SQLException{
        methodName="readTable";
        var datos=new Datos(modelo);
        for(int i=0;i<dtm.getRowCount();i++){
            codigoProducto=Integer.parseInt(dtm.getValueAt(i,0).toString());
            codigoEmpleado=Integer.parseInt(jLabel2.getText());
            nombre=dtm.getValueAt(i,1).toString();
            marca=dtm.getValueAt(i,2).toString();
            cantidad=Integer.parseInt(dtm.getValueAt(i,3).toString());
            precio=Integer.parseInt(dtm.getValueAt(i,4).toString());
            total=Integer.parseInt(dtm.getValueAt(i,5).toString());
            
            datos.insertarDatosProducto(codigoProducto,codigoEmpleado,nombre,marca,cantidad,precio,total);
            datos.actualizarDatosAlmacen(cantidad,codigoProducto);
        }
        datos.actualizarDatosConteoVentas(codigoEmpleado,new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        
        new logger(Level.CONFIG,this.getClass().getName()).storeMessageConfirmation(this, methodName);
    }
    
    protected void checkPartner(){
        if(jComboBox1.getModel().getSelectedItem().toString().equals(Validation.paymentType(1))){
            
        }else{
            
        }
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
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new clases.MediaHandler(paymentWindow.class.getName()).getIconImage());

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

        jLabel4.setText(" ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta" }));

        jLabel5.setText("Cambio:");

        jLabel6.setText(" ");

        jLabel7.setText("$");

        jLabel8.setText("$");

        calcButton.setText("Calcular");

        cbAddCoupon.setText("Agregar cupón");

        jLabel9.setText("Subtotal:");

        jLabel10.setText(" ");

        jLabel11.setText("Descuento:");

        jLabel12.setText(" ");

        jLabel13.setText("Impuesto:");

        jLabel14.setText(" ");

        jLabel15.setText("$");

        jLabel16.setText("$");

        jLabel17.setText("$");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mkPaidButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(calcButton)
                        .addGap(127, 127, 127)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbAddCoupon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbAddCoupon)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel5)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(mkPaidButton)
                    .addComponent(calcButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new paymentWindow(new javax.swing.JFrame(),true).setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calcButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox cbAddCoupon;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton mkPaidButton;
    // End of variables declaration//GEN-END:variables
}