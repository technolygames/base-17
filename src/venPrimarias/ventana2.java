package venPrimarias;
//clases
import clases.datos;
import clases.Icono;
import clases.laf;
import clases.logger;
//java
import java.awt.Image;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import javax.swing.table.DefaultTableModel;

public final class ventana2 extends javax.swing.JFrame{
    public ventana2(){
        initComponents();
        new laf().LookAndFeel(ventana2.this,ventana2.class.getName(),"ventana2");
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Almacén");
        setResizable(false);
    }
    
    protected datos cn;
    
    protected Properties p;
    protected DefaultTableModel dtm;
    
    protected int cod_prod;
    protected int cod_lote;
    protected int cod_prov;
    protected int cantidad;
    protected int preciou;
    protected int win;
    
    protected String nom_prod;
    protected String marca;
    protected String stock;
    
    protected final void settings(){
        p=new Properties();
        dtm=new DefaultTableModel();
        try{
            p.load(new FileInputStream(System.getProperty("user.dir")+"/src/data/config/config.properties"));
            Image i=ImageIO.read(new FileInputStream(p.getProperty("imagenes")));
            ImageIcon ii=new ImageIcon(i);
            Icon icono=new ImageIcon(ii.getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT));
            picLabel.setIcon(icono);
            i.flush();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'settings()'",Level.WARNING);
            new logger().exceptionLogger(ventana2.class.getName(),Level.WARNING,"settings-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'settings()'",Level.WARNING);
            new logger().exceptionLogger(ventana2.class.getName(),Level.WARNING,"settings-2IO",x.fillInStackTrace());
        }
        
        dtm.setColumnIdentifiers(new Object[]{
            "Código del producto",
            "Código del lote",
            "Código del proveedor",
            "Nombre del producto",
            "Marca",
            "Cantidad",
            "Precio unitario",
            "Stock"
        });
        dtm.setRowCount(0);
        
        jTable1.setModel(dtm);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setEnabled(false);
    }
    
    protected final void botones(){
        dtm=new DefaultTableModel();
        
        addButton.addActionListener((ae)->{
            if(!txtCodProd.getText().equals("")||!txtCodLote.getText().equals("")||!txtCodProv.getText().equals("")||!txtProd.getText().equals("")||!txtMarca.getText().equals("")||!txtCant.getText().equals("")||!txtPU.getText().equals("")||!jComboBox1.getSelectedItem().equals("En Existencia")){
                dtm.addRow(new Object[]{
                    txtCodProd.getText(),
                    txtCodLote.getText(),
                    txtCodProv.getText(),
                    txtProd.getText(),
                    txtMarca.getText(),
                    txtCant.getText(),
                    txtPU.getText(),
                    jComboBox1.getSelectedItem()
                });
            }else{
                JOptionPane.showMessageDialog(null,"Error:\nIngrese los datos que se solicitan","Error 18",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 18: no se escribieron o faltan datos en los campos.\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'botones(addButton)'",Level.WARNING);
            }
            
            txtCodProd.setText("");
            txtCodLote.setText("");
            txtCodProv.setText("");
            txtProd.setText("");
            txtMarca.setText("");
            txtCant.setText("");
            txtPU.setText("");
        });
        
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        cleanButton.addActionListener((ae)->{
            dtm.setRowCount(0);
            
            txtCodProd.setText("");
            txtCodLote.setText("");
            txtCodProv.setText("");
            txtProd.setText("");
            txtMarca.setText("");
            txtCant.setText("");
            txtPU.setText("");
        });
        
        svdtButton.addActionListener((ae)->{
            try{
                for(int i=0;i<dtm.getRowCount();i++){
                    cod_prod=Integer.parseInt(dtm.getValueAt(i,0).toString());
                    cod_lote=Integer.parseInt(dtm.getValueAt(i,1).toString());
                    cod_prov=Integer.parseInt(dtm.getValueAt(i,2).toString());
                    nom_prod=dtm.getValueAt(i,3).toString();
                    marca=dtm.getValueAt(i,4).toString();
                    cantidad=Integer.parseInt(dtm.getValueAt(i,5).toString());
                    preciou=Integer.parseInt(dtm.getValueAt(i,6).toString());
                    stock=dtm.getValueAt(i,7).toString();
                    
                    new datos().insertarDatosAlmacen(cod_prod,cod_lote,cod_prov,nom_prod,marca,cantidad,preciou,stock);
                }
                JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
                new logger().staticLogger("Rel 1: se guardaron correctamente los datos a ka base de datos.\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'botones(svdtButton)'.\nUsuario que hizo los cambios: "+String.valueOf(start.userID),Level.INFO);
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 32: "+e.getMessage()+".\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'botones(svdtButton)'",Level.WARNING);
                new logger().exceptionLogger(ventana2.class.getName(),Level.WARNING,"botones.svdt-32",e.fillInStackTrace());
            }catch(NullPointerException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'botones(svdtButton)'",Level.WARNING);
                new logger().exceptionLogger(ventana2.class.getName(),Level.WARNING,"botones.svdt-0",x.fillInStackTrace());
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        svdtButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCodProd = new javax.swing.JTextField();
        txtProd = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtCodLote = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodProv = new javax.swing.JTextField();
        cleanButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        picLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtPU = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Icono().getIconImage());

        backButton.setText("Regresar");

        svdtButton.setText("Guardar datos");

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel6.setText("Almacén");

        jLabel7.setText("Código del producto:");

        jLabel8.setText("Nombre del producto:");

        jLabel9.setText("Marca:");

        jLabel10.setText("Stock:");

        txtCodProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodProdKeyPressed(evt);
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

        txtCodLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodLoteKeyPressed(evt);
            }
        });

        jLabel2.setText("Código del lote:");

        jLabel4.setText("Código del proveedor:");

        txtCodProv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodProvKeyPressed(evt);
            }
        });

        cleanButton.setText("Limpiar");

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
        jScrollPane1.setViewportView(jTable1);

        addButton.setText("Añadir campos");

        jLabel1.setText("Cantidad:");

        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En Existencia", "Agotado" }));

        jLabel3.setText("Precio unitario:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodProd, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txtCodLote, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCodProv, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(txtPU, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(svdtButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cleanButton)
                        .addGap(129, 129, 129)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(svdtButton)
                    .addComponent(backButton)
                    .addComponent(cleanButton)
                    .addComponent(addButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtCodProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProdKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'txtCodigoKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtCodProdKeyPressed
    
    private void txtCodLoteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodLoteKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'txtCodProdKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtCodLoteKeyPressed
    
    private void txtCodProvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProvKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'txtCodProvKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtCodProvKeyPressed
    
    private void txtProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProdKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'txtProdKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtProdKeyPressed
        
    private void txtMarcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+ventana2.class.getName()+"', en el método 'txtMarcaKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtMarcaKeyPressed
        
    public static void main(String[] args){
        new ventana2().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton addButton;
    protected javax.swing.JButton backButton;
    protected javax.swing.JButton cleanButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    protected javax.swing.JLabel picLabel;
    protected javax.swing.JButton svdtButton;
    protected javax.swing.JTextField txtCant;
    protected javax.swing.JTextField txtCodLote;
    protected javax.swing.JTextField txtCodProd;
    protected javax.swing.JTextField txtCodProv;
    protected javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtPU;
    protected javax.swing.JTextField txtProd;
    // End of variables declaration//GEN-END:variables
}