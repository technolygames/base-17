package venPrimarias;
//clases
import clases.Datos;
import clases.MediaHandler;
import clases.logger;
import clases.mvc.MvcForm2;
import menus.menuDatosVentana2;
//librerías
import com.google.gson.stream.JsonReader;
//java
import java.awt.Image;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
//extension larga
import java.util.logging.Level;
import java.nio.charset.StandardCharsets;
import javax.swing.filechooser.FileNameExtensionFilter;

public class formulario2 extends javax.swing.JFrame{
    public formulario2(){
        initComponents();
        new MediaHandler(formulario2.class.getName()).LookAndFeel(formulario2.this);
         
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Formulario 2");
        setResizable(false);
        pack();
    }
    
    protected Properties p;
    protected JTextField campos;
    protected JFileChooser jfc;
    
    protected String direccion;
    protected String methodName;
    
    protected final void settings(){
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
    }
    
    protected final void botones(){
        for(JTextField tf:new JTextField[]{jTextField1,jTextField2,jTextField3,jTextField4}){
            campos=tf;
        }
        
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        jMenuItem1.addActionListener(a->
            new menuDatosVentana2().setVisible(true)
        );
        
        jMenuItem2.addActionListener(a->
            clearImage()
        );
        
        miClearFields.addActionListener(a->{
            campos.setText("");
            jTextArea1.setText("");
            clearImage();
        });
        
        miInsImage.addActionListener(a->{
            methodName="botones.miInsImage";
            try{
                p=new Properties();
                p.load(new FileInputStream("data/config/filechooserd.properties"));
                jfc=new JFileChooser(p.getProperty("lastdirectory_form2"));
                
                jfc.setMultiSelectionEnabled(false);
                jfc.setAcceptAllFileFilterUsed(false);
                jfc.setFileFilter(new FileNameExtensionFilter("Archivos JPG","jpg"));
                
                if(JFileChooser.APPROVE_OPTION==jfc.showOpenDialog(null)){
                    File f=jfc.getSelectedFile();
                    direccion=f.getPath();
                    
                    showImage(direccion);
                    
                    p.setProperty("lastdirectory_form2",f.getParent());
                    p.store(new FileOutputStream("data/config/filechooserd.properties"),"JFileChooserDirection");
                }
                p.clear();
            }catch(HeadlessException e){
                new logger(Level.SEVERE).storeAndViewCaughtException(this,e,formulario2.class.getName(),methodName,"40");
            }catch(FileNotFoundException x){
                new logger(Level.SEVERE).storeAndViewCaughtException(this,x,formulario2.class.getName(),methodName,"1IO");
            }catch(IOException n){
                new logger(Level.SEVERE).storeAndViewCaughtException(this,n,formulario2.class.getName(),methodName,"2IO");
            }
        });
        
        miLoadJson.addActionListener(a->{
            jfc=new JFileChooser("data/databackup/Socios");
            
            jfc.setMultiSelectionEnabled(false);
            jfc.setAcceptAllFileFilterUsed(false);
            jfc.setFileFilter(new FileNameExtensionFilter("Archivos JSON","json"));
            
            if(JFileChooser.APPROVE_OPTION==jfc.showOpenDialog(null)){
                File f=jfc.getSelectedFile();
                loadFromJson(f.getPath());
            }
        });
        
        storeButton.addActionListener(a->{
            methodName="botones.store";
            try{
                if(!campos.getText().isEmpty()||!jTextArea1.getText().isEmpty()||picLabel.getIcon()!=null){
                    List<MvcForm2> lista=new ArrayList<>();
                    MvcForm2 modelo=new MvcForm2();
                    
                    modelo.setCodigo(Integer.parseInt(jTextField1.getText()));
                    modelo.setNombre(jTextField2.getText());
                    modelo.setApellidoPaterno(jTextField3.getText());
                    modelo.setApellidoMaterno(jTextField4.getText());
                    modelo.setTipo(jComboBox1.getSelectedItem().toString());
                    modelo.setCorreo(jTextField5.getText());
                    modelo.setRfc(jTextField6.getText());
                    modelo.setDatos(jTextArea1.getText());
                    modelo.setImagen(new FileInputStream(direccion));
                    
                    lista.add(modelo);
                    
                    new Datos().insertarDatosSocio(lista);
                }else{
                    JOptionPane.showMessageDialog(this,"Error: escribe los datos faltantes","Error 18",JOptionPane.WARNING_MESSAGE);
                    new logger(Level.WARNING).staticLogger("Error 18: no se escribieron o faltan datos en los campos.\nOcurrió en la clase '"+formulario2.class.getName()+"', en el método 'botones(storeButton)'");
                }
            }catch(FileNotFoundException e){
                new logger(Level.SEVERE).storeAndViewCaughtException(this,e,formulario2.class.getName(),methodName,"1IO");
            }catch(NullPointerException x){
                new logger(Level.SEVERE).storeAndViewCaughtException(this,x,formulario2.class.getName(),methodName,"0");
            }catch(NumberFormatException n){
                new logger(Level.SEVERE).storeAndViewCaughtException(this,n,formulario2.class.getName(),methodName,"32");
            }catch(SQLException s){
                new logger(Level.SEVERE).storeAndViewCaughtException(this,s,formulario2.class.getName(),methodName,"11");
            }
        });
    }
    
    protected void loadFromJson(String path){
        try{
            JsonReader jsonr=new JsonReader(new FileReader(path,StandardCharsets.UTF_8));
            jsonr.beginObject();
            while(jsonr.hasNext()){
                switch(jsonr.nextName()){
                    case "codigo_part"->jTextField1.setText(String.valueOf(jsonr.nextInt()));
                    case "nombre_part"->jTextField2.setText(jsonr.nextString());
                    case "apellidop_part"->jTextField3.setText(jsonr.nextString());
                    case "apellidom_part"->jTextField4.setText(jsonr.nextString());
                    case "tipo_socio"->jComboBox1.getModel().setSelectedItem(jsonr.nextString());
                    case "correo"->jTextField5.setText(jsonr.nextString());
                    case "rfc"->jTextField6.setText(jsonr.nextString());
                    case "datos_extra"->jTextArea1.setText(jsonr.nextString());
                    case "imagen"->direccion=jsonr.nextString();
                    default->jsonr.skipValue();
                }
            }
            showImage(direccion);
            jsonr.endObject();
            jsonr.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(this,"Error:\n"+e.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+e.getMessage()+".\nOcurrió en la clase '"+formulario2.class.getName()+"', en el método 'loadFromJson()'");
            new logger(Level.SEVERE).exceptionLogger(formulario2.class.getName(),"loadFromJson-2IO",e.fillInStackTrace());
        }
    }
    
    protected void clearImage(){
        picLabel.setIcon(null);
        picLabel.setText("Foto");
    }
    
    protected void showImage(String path){
        picLabel.setText(null);
        picLabel.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        backButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        storeButton = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        picLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        miInsImage = new javax.swing.JMenuItem();
        miClearFields = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        miLoadJson = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new clases.MediaHandler(formulario2.class.getName()).getIconImage());

        backButton.setText("Regresar");

        jLabel1.setText("Código del socio:");

        jLabel2.setText("Nombre(s):");

        jLabel3.setText("Apellido paterno:");

        jLabel4.setText("Tipo de socio:");

        jLabel5.setText("Datos extra:");

        storeButton.setText("Guardar datos");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Apellido materno:");

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        picLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picLabel.setText("Foto");
        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("E-mail:");

        jLabel8.setText("RFC:");

        jMenu1.setText("Datos");

        jMenuItem1.setText("Menú de Datos");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Opciones");

        miInsImage.setText("Insertar imagen");
        jMenu2.add(miInsImage);

        miClearFields.setText("Limpiar campos");
        jMenu2.add(miClearFields);

        jMenuItem2.setText("Limpiar foto");
        jMenu2.add(jMenuItem2);

        miLoadJson.setText("Cargar JSON");
        jMenu2.add(miLoadJson);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(114, 114, 114))
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(storeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(storeButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new logger(Level.WARNING).storeAndViewLetterInputWarning(this,formulario2.class.getName(),"jTextField1KeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyPressed
    
    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING).storeAndViewNumberInputWarning(this,formulario2.class.getName(),"jTextField2KeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyPressed
    
    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING).storeAndViewNumberInputWarning(this,formulario2.class.getName(),"jTextField3KeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField3KeyPressed
    
    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new logger(Level.WARNING).storeAndViewNumberInputWarning(this,formulario2.class.getName(),"jTextField4KeyPressed");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyPressed
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new formulario2().setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JMenuItem miClearFields;
    private javax.swing.JMenuItem miInsImage;
    private javax.swing.JMenuItem miLoadJson;
    private javax.swing.JLabel picLabel;
    private javax.swing.JButton storeButton;
    // End of variables declaration//GEN-END:variables
}