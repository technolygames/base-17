package clases.tickets;

import clases.logger;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import javax.swing.JOptionPane;

import java.util.logging.Level;
import javax.swing.JTable;

/**
 * Clase encargada de imprimir el ticket de compra.
 * Se encarga de imprimir el ticket con los objetos comprados, cantidad y total.
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
     * @param iva Impuesto de valor adquirido según el paí­s.
     * @param pago Método de pago utilizado al comprar.
     * @param cambio Cambio devuelto al comprador.
     */
    public void imprimirTicket(JTable tabla,String empleado,String total0,int iva,String pago,String cambio){
        try{
            Date date=new Date();
            Properties p=new Properties();
            ticket ticket=new ticket();
            
            SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat hora=new SimpleDateFormat("hh:mm:ss aa");
            
            p.load(new FileInputStream(System.getProperty("user.dir")+"/src/data/config/config.properties"));
            
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
            ticket.addTotal("Subtotal: ",total0);
            ticket.addTotal("IVA: ",iva+"%");
            int t0=Integer.parseInt(total0);
            int total6=(int)(t0+(t0*0.16));
            ticket.addTotal("Total: ",String.valueOf(total6));
            ticket.addTotal("Paga con: ",pago);
            ticket.addTotal("Cambio: ",cambio);
            ticket.addPieLinea(ticket.darEspacio());
            ticket.addPieLinea("Gracias por su preferencia.");
            ticket.imprimirDocumento("");
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error NFE_T1",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error NFE_T1: "+e.getMessage()+"\nOcurrió en la clase '"+datosTicket.class.getName()+"', en el método 'imprimirTicket()'",Level.WARNING);
            new logger().exceptionLogger(datosTicket.class.getName(),Level.WARNING,"imprimirTicket-NFE_T1",e.fillInStackTrace());
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