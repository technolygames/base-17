package venPrimarias;

import clases.datos;
import clases.logger;
import clases.tickets.datosTicket;
import venSecundarias.calcWindow;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventObject;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import java.util.logging.Level;
import javax.swing.CellEditor;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperFillManager;
import venTerciarias.dataWindow4;

public final class ventana1 extends javax.swing.JFrame{
    public ventana1(){
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error CNFE: "+e.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'ventana1()'",Level.WARNING);
            new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"ventana1-CNFE",e.fillInStackTrace());
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IE: "+x.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'ventana1()'",Level.WARNING);
            new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"ventana1-IE",x.fillInStackTrace());
        }catch(IllegalAccessException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IAE: "+n.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'ventana1()'",Level.WARNING);
            new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"ventana1-IAE",n.fillInStackTrace());
        }catch(UnsupportedLookAndFeelException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 28: "+y.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'ventana1()'",Level.WARNING);
            new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"ventana1-28",y.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'ventana1()'",Level.WARNING);
            new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"ventana1-0",k.fillInStackTrace());
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+s.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'ventana1()'",Level.WARNING);
            new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"ventana1-1IO",s.fillInStackTrace());
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+d.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'ventana1()'",Level.WARNING);
            new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"ventana1-2IO",d.fillInStackTrace());
        }
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Productos");
        setResizable(false);
    }
    
    protected datos d;
    protected PreparedStatement ps;
    protected ResultSet rs;
    
    protected Image retValue;
    protected Properties p;
    
    protected DefaultTableModel dtm;
    
    protected String nombre_prod;
    protected String marca_prod;
    
    public static int resultado=0;
    protected int codigo_prod;
    protected int cantidad;
    protected int precio;
    protected int total;
    protected int win;
    
    @Override
    public Image getIconImage(){
        p=new Properties();
        try{
            p.load(new FileInputStream("src/data/config/config.properties"));
            retValue=Toolkit.getDefaultToolkit().getImage(p.getProperty("icono"));
            retValue.flush();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"getIconImage-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'getIconImage()'",Level.WARNING);
            new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"getIconImage-2IO",x.fillInStackTrace());
        }
        return retValue;
    }
    
    protected final void settings(){
        p=new Properties();
        dtm=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                //all cells false
                return false;
            }
        };
        try{
            p.load(new FileInputStream("src/data/config/config.properties"));
            Image i=ImageIO.read(new FileInputStream(p.getProperty("imagenes")));
            ImageIcon im=new ImageIcon(i);
            Icon l=new ImageIcon(im.getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT));
            picLabel.setIcon(l);
            
            i.flush();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Erron:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'settings()'",Level.WARNING);
            new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"settings-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'settings()'",Level.WARNING);
            new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"settings-2IO",x.fillInStackTrace());
        }
        
        dtm.setColumnIdentifiers(new Object[]{
            "Código del producto",
            "Nombre del producto",
            "Marca",
            "Cantidad",
            "Precio",
            "Total"
        });
        
        dtm.setRowCount(0);
        jTable1.setModel(dtm);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setEnabled(true);
        for(int i=0;i<dtm.getRowCount();i++){
            for(int j=0;j<dtm.getColumnCount();j++){
                dtm.isCellEditable(i,j);
            }
        }
    }
    
    protected final void botones(){
        dtm=new DefaultTableModel();
        
        addButton.addActionListener((ae)->{
            if(!txtCodigo.getText().equals("")||!txtProd.getText().equals("")||!txtMarca.getText().equals("")||!txtPrecio.getText().equals("")||!txtCant.getText().equals("")||!txtTotal.getText().equals("")){
                dtm.addRow(new Object[]{
                    txtCodigo.getText(),
                    txtProd.getText(),
                    txtMarca.getText(),
                    txtCant.getText(),
                    txtPrecio.getText(),
                    txtTotal.getText()
                });
            }else{
                JOptionPane.showMessageDialog(null,"Error:\nIngrese los datos que se solicitan","Error 18",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 18: no se escribieron o faltan datos en los campos.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(addButton)'",Level.WARNING);
            }
            
            txtCodigo.setText("");
            txtProd.setText("");
            txtMarca.setText("");
            txtPrecio.setText("");
            txtCant.setText("");
            txtTotal.setText("");
        });
        
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        calcButton.addActionListener((ae)->{
            try{
                int n1=0;
                int res=0;
                for(int i=0;i<dtm.getRowCount();i++){
                    n1=Integer.parseInt(dtm.getValueAt(i,5).toString());
                    res+=n1;
                    
                    resultado=res;
                }
                
                calcWindow clw=new calcWindow(new javax.swing.JFrame(),true);
                clw.setVisible(true);
                calcWindow.txtTotal.setText(String.valueOf(resultado));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 18",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 18: "+e.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(calcButton)'",Level.WARNING);
                new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"botones.calc-18",e.fillInStackTrace());
            }
        });
        
        cleanButton.addActionListener((ae)->{
            dtm.setRowCount(0);
            
            txtCodigo.setText("");
            txtProd.setText("");
            txtMarca.setText("");
            txtPrecio.setText("");
            txtCant.setText("");
            txtTotal.setText("");
        });
        
        genrepButton.addActionListener((ae)->{
            try{
                Connection cn=new datos().getConnection();
                JasperReport jr=JasperCompileManager.compileReport("src/data/database/Jasper/reportes.jasper");
                JasperPrint jp=JasperFillManager.fillReport(jr,null,cn);
                JasperViewer jv=new JasperViewer(jp,false);
                jv.viewReport(jp);
                jv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                jv.setVisible(true);
                
                cn.close();
            }catch(JRException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 17",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 17: "+e.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(genrepButton)'",Level.WARNING);
                new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"botones.genrep-17",e.fillInStackTrace());
            }catch(ExceptionInInitializerError x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error EIIE",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error EIIE: "+x.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(genrepButton)'",Level.WARNING);
                new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"botones.genrep-EIIE",x.fillInStackTrace());
            }catch(NoClassDefFoundError ñ){
                JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error NCDFE",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error NCDFE: "+ñ.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(genrepButton)'",Level.WARNING);
                new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"botones.genrep-NCDFE",ñ.fillInStackTrace());
            }catch(SQLException k){
                JOptionPane.showMessageDialog(null,"Error:\n"+d,"Error Prueba",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error Prueba: "+k.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(genrepButton)'",Level.WARNING);
                new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"botones.genrep-Prueba",k.fillInStackTrace());
            }
        });
        
        jButton2.addActionListener((a)->{
            dtm.removeRow(jTable1.getSelectedRow());
        });
        
        mkPaidButton.addActionListener((a)->{
            try{
                for(int i=0;i<dtm.getRowCount();i++){
                    codigo_prod=Integer.parseInt(dtm.getValueAt(i,0).toString());
                    nombre_prod=dtm.getValueAt(i,1).toString();
                    marca_prod=dtm.getValueAt(i,2).toString();
                    cantidad=Integer.parseInt(dtm.getValueAt(i,3).toString());
                    precio=Integer.parseInt(dtm.getValueAt(i,4).toString());
                    total=Integer.parseInt(dtm.getValueAt(i,5).toString());
                    
                    new datos().insertarDatosProducto(codigo_prod,nombre_prod,marca_prod,cantidad,precio,total);
                }
                JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 18",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 18: "+e.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(mkPaidButton)'",Level.WARNING);
                new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"botones.mkPaid-18",e.fillInStackTrace());
            }catch(NullPointerException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
                new logger().logStaticSaver("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'botones(mkPaidButton)'",Level.WARNING);
                new logger().exceptionLogger(ventana1.class.getName(),Level.WARNING,"botones.mkPaid-0",x.fillInStackTrace());
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtProd = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtCant = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        calcButton = new javax.swing.JButton();
        cleanButton = new javax.swing.JButton();
        genrepButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        mkPaidButton = new javax.swing.JButton();
        picLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());

        jLabel2.setText("Código del producto:");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
                txtCodigoKeyPressed2(evt);
            }
        });

        txtProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProdKeyPressed(evt);
            }
        });

        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMarcaKeyPressed(evt);
            }
        });

        jLabel3.setText("Nombre del producto:");

        jLabel4.setText("Marca:");

        jLabel5.setText("Precio:");

        jLabel6.setText("Cantidad:");

        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioKeyPressed(evt);
            }
        });

        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantKeyPressed(evt);
                txtCantKeyPressed2(evt);
            }
        });

        backButton.setText("Regresar");

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel8.setText("Productos");

        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalKeyPressed(evt);
            }
        });

        jLabel9.setText("Total:");

        calcButton.setText("Calcular");

        cleanButton.setText("Limpiar campos");

        genrepButton.setText("Generar factura");

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
        jScrollPane1.setViewportView(jTable1);

        addButton.setText("Añadir campos");

        mkPaidButton.setText("Realizar pago");

        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton2.setText("Eliminar fila");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtCodigo))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtProd))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mkPaidButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(genrepButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(calcButton)
                                .addGap(126, 126, 126)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addButton)
                                .addGap(18, 18, 18)
                                .addComponent(cleanButton)
                                .addGap(32, 32, 32)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(picLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(calcButton)
                    .addComponent(genrepButton)
                    .addComponent(cleanButton)
                    .addComponent(addButton)
                    .addComponent(mkPaidButton)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 1",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Let 1: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'txtCodigoKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyPressed
    
    private void txtProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProdKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 2",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Let 2: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'txtProdKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtProdKeyPressed
    
    private void txtMarcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 2",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Let 2: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'txtMarcaKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtMarcaKeyPressed
    
    private void txtPrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 1",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Let 1: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'txtPrecioKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioKeyPressed
    
    private void txtCantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 1",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Let 1: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'txtCantKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtCantKeyPressed
    
    private void txtTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 1",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Let 1: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana1.class.getName()+"', en el método 'txtTotalKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtTotalKeyPressed

    private void txtCodigoKeyPressed2(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed2
        try{
            ps=new datos().getConnection().prepareStatement("select*from almacen where codigo_prod="+txtCodigo.getText()+";");
            rs=ps.executeQuery();
            if(rs.next()){
                txtProd.setText(rs.getString("nombre_prod"));
                txtMarca.setText(rs.getString("marca"));
                txtPrecio.setText(String.valueOf(rs.getInt("precio_unitario")));
            }
        }catch(SQLException e){
            e.fillInStackTrace();
        }
    }//GEN-LAST:event_txtCodigoKeyPressed2

    private void txtCantKeyPressed2(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyPressed2
        try{
            int n1=Integer.parseInt(txtCant.getText());
            int n2=Integer.parseInt(txtPrecio.getText());
            int res=n2*n1;
            txtTotal.setText(String.valueOf(res));
        }catch(NumberFormatException e){
        }
    }//GEN-LAST:event_txtCantKeyPressed2
    
    public static void main(String[] args){
        new ventana1().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton addButton;
    protected javax.swing.JButton backButton;
    protected javax.swing.JButton calcButton;
    protected javax.swing.JButton cleanButton;
    protected javax.swing.JButton genrepButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JTable jTable1;
    protected javax.swing.JButton mkPaidButton;
    protected javax.swing.JLabel picLabel;
    protected javax.swing.JTextField txtCant;
    protected javax.swing.JTextField txtCodigo;
    protected javax.swing.JTextField txtMarca;
    protected javax.swing.JTextField txtPrecio;
    protected javax.swing.JTextField txtProd;
    protected javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}