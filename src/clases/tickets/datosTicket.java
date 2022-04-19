package clases.tickets;
//clases
import clases.logger;
//java
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import javax.swing.JTable;
import javax.swing.JOptionPane;
//extension larga
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

/**
 * Clase encargada de imprimir el ticket de compra.
 * Se encarga de imprimir el ticket con los objetos comprados, cantidad y total.
 * 
 * @author unknown
 */
public class datosTicket{
    //String total;
    String precio;
    String cantidad;
    String items;
    
    /**
     * Método encargado de imprimir el ticket.
     * 
     * @param tabla Tabla que obtendrá los datos para el ticket.
     * @param empleado Empleado que atendió en la venta.
     * @param total0 Precio total de los productos.
     * @param pago Método de pago utilizado al comprar.
     * @param cambio Cambio devuelto al comprador.
     */
    public void imprimirTicket(JTable tabla,String empleado,int total0,String pago,int cambio){
        try{
            Date date=new Date();
            Properties p=new Properties();
            ticket ticket=new ticket();
            
            SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat hora=new SimpleDateFormat("hh:mm:ss aa");
            
            p.load(new FileReader(System.getProperty("user.dir")+"/src/data/config/config.properties",StandardCharsets.UTF_8));
            
            ticket.addCabecera(p.getProperty("nombre"));
            ticket.addCabecera(ticket.darEspacio());
            ticket.addCabecera("tlf: 222222--r.u.c: 22222222222");
            ticket.addCabecera(ticket.darEspacio());
            ticket.addSubcabecera(ticket.dibujarLinea(40));
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera("Ticket No. "+(Math.random()*10000)+"");
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera("-"+fecha.format(date)+"-"+hora.format(date));
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera("Quien atendió: "+empleado);
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera(ticket.dibujarLinea(40));
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera("producto----cant----p.u");
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera(ticket.dibujarLinea(40));
            ticket.addSubcabecera(ticket.darEspacio());
            for(int i=0;i<tabla.getRowCount();i++){
                //cantidad de decimales
                NumberFormat nf=NumberFormat.getNumberInstance(Locale.US);
                DecimalFormat form=(DecimalFormat)nf;
                form.applyPattern("#,###.00");
                //items
                String item2=tabla.getValueAt(i,1).toString();
                String item3="";
                int item4=0;
                if(item2.length()>17){
                    item2=item2.substring(0,16)+".";
                }else{
                    item4=17-item2.length();
                    for(int j=0;j<item4;j++){
                        item3+=" ";
                    }
                    items+=item3;
                }
                //cantidad
                String cantidad2=tabla.getValueAt(i,3).toString();
                String cantidad3="";
                int cantidad4=0;
                if(cantidad2.length()<17){
                    cantidad4=17-cantidad2.length();
                    for(int j=0;j<cantidad4;j++){
                        cantidad3+=" ";
                    }
                    cantidad+=cantidad3;
                }
                //precio
                String precio2=tabla.getValueAt(i,4).toString();
                double precio3=Double.parseDouble(precio2);
                int precio4=0;
                precio2=form.format(precio3);
                if(precio2.length()<17){
                    precio4=17-precio2.length();
                    String pre="";
                    for(int j=0;j<precio4;j++){
                        pre+=" ";
                    }
                    precio=pre+precio2;
                }
                //total
                /*String total2=tabla.getValueAt(i,5).toString();
                String total3="";
                int total4=0;
                total2=form.format(Double.parseDouble(total2));
                if(total2.length()<8){
                    total4=8-total2.length();
                    for(int j=0;j<total4;j++){
                        total3+=" ";
                    }
                    total=total3+total2;
                }*/
                //agrego los items al detalle
                ticket.addItem(items,cantidad,precio);
            }
            ticket.addTotal("Subtotal: ",String.valueOf(total0));
            ticket.addTotal("IVA: ","0%");
            ticket.addTotal("Total: ",String.valueOf(total0));
            ticket.addTotal("Paga con: ",pago);
            ticket.addTotal("Cambio: ",String.valueOf(cambio));
            ticket.addPieLinea(ticket.darEspacio());
            ticket.addPieLinea("Gracias por su preferencia.");
            
            ticket.imprimirDocumento("src/data/generic/tickets/ticket-("+new SimpleDateFormat("dd-MM-yyyy hh.mm.ss aa").format(new Date())+").txt",true);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 18",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 18: "+e.getMessage()+"\nOcurrió en la clase '"+datosTicket.class.getName()+"', en el método 'imprimirTicket()'",Level.WARNING);
            new logger().exceptionLogger(datosTicket.class.getName(),Level.WARNING,"imprimirTicket-18",e.fillInStackTrace());
        }catch(FileNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+x.getMessage()+"\nOcurrió en la clase '"+datosTicket.class.getName()+"', en el método 'imprimirTicket()'",Level.WARNING);
            new logger().exceptionLogger(datosTicket.class.getName(),Level.WARNING,"imprimirTicket-1IO",x.fillInStackTrace());
        }catch(IOException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+n.getMessage()+"\nOcurrió en la clase '"+datosTicket.class.getName()+"', en el método 'imprimirTicket()'",Level.WARNING);
            new logger().exceptionLogger(datosTicket.class.getName(),Level.WARNING,"imprimirTicket-2IO",n.fillInStackTrace());
        }
    }
}