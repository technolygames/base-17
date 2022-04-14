package clases.tickets;

import clases.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.Doc;
import javax.print.PrintException;
import javax.print.SimpleDoc;
import javax.swing.JOptionPane;

/**
 * Clase encargada de imprimir y dar forma al ticket.
 * 
 * @author unknown
 */
public class ticket{
    static ArrayList<String> cabezaLineas=new ArrayList<String>();
    static ArrayList<String> subcabezaLineas=new ArrayList<String>();
    static ArrayList<String> items=new ArrayList<String>();
    static ArrayList<String> totales=new ArrayList<String>();
    static ArrayList<String> lineasPie=new ArrayList<String>();
    
    public static void addCabecera(String linea){
        cabezaLineas.add(linea);
    }
    
    public static void addSubcabecera(String linea){
        subcabezaLineas.add(linea);
    }
    
    public static void addItem(String cantidad,String item,String precio){
        order2 newItem=new order2(' ');
        items.add(newItem.generarItem(cantidad,item,precio));
    }
    
    public static void addTotal(String nombre,String precio){
        order1 newTotal=new order1(' ');
        totales.add(newTotal.generarTotal(nombre,precio));
    }
    
    public static void addPieLinea(String linea){
        lineasPie.add(linea);
    }
    
    public static String dibujarLinea(int valor){
        String raya="";
        for(int i=0;i<valor;i++){
            raya+="-";
        }
        return raya;
    }
    
    public static void setFormato(FileWriter fw,int formato){
        try{
            char[] ESC_CUT_PAPER=new char[]{0x1B,'!',(char)formato};
            fw.write(ESC_CUT_PAPER);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error IOE_T2",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error IOE_T2: "+e.getMessage()+"\nOcurrió en la clase '"+ticket.class.getName()+"', en el método 'setFormato()'",Level.WARNING);
            new logger().exceptionLogger(ticket.class.getName(),Level.WARNING,"setFormato",e.fillInStackTrace());
        }
    }
    
    public static String darEspacio(){
        return "\n";
    }
    
    /**
     * Imprime el ticket por medio de la ticketera.
     * 
     * @param impresora Ticketera a la que se imprimirá¡ el documento.
     * @param gaveta Abrirá la gaveta.
     */
    public static void imprimirDocumento(String impresora,boolean gaveta){
        String cadena="";
        try{
            FileWriter imp=new FileWriter(impresora,StandardCharsets.UTF_8);
            char[] caracter=new char[]{0x1B,'R',18};
            char[] cortarPapel=new char[]{0x1B,'m'};
            
            imp.write(caracter);
            for(int cabecera=0;cabecera<cabezaLineas.size();cabecera++){
                cadena+=cabezaLineas.get(cabecera);
                setFormato(imp,27);
            }
            for(int subcabecera=0;subcabecera<subcabezaLineas.size();subcabecera++){
                cadena+=subcabezaLineas.get(subcabecera);
            }
            for(int ITEM=0;ITEM<items.size();ITEM++){
                cadena+=items.get(ITEM);
            }
            for(int total=0;total<totales.size();total++){
                cadena+=totales.get(total);
            }
            for(int pie=0;pie<lineasPie.size();pie++){
                cadena+=lineasPie.get(pie);
            }
            imp.write(cadena);
            
            imp.write(cortarPapel);
            
            DocFlavor flavor=DocFlavor.INPUT_STREAM.AUTOSENSE;
            PrintService service=PrintServiceLookup.lookupDefaultPrintService();
            DocPrintJob pj=service.createPrintJob();
            byte[] bytes=cadena.getBytes();
            Doc doc=new SimpleDoc(imp,flavor,null);
            
            pj.print(doc,null);
            
            if(gaveta){
                char abrir[]={(char)27,(char)112,(char)0,(char)10,(char)100};
                imp.write(abrir);
            }
            
            cabezaLineas.clear();
            subcabezaLineas.clear();
            items.clear();
            totales.clear();
            lineasPie.clear();
            
            imp.flush();
            imp.close();
        }catch(PrintException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error PE_T1",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error PE_T1: "+x.getMessage()+"\nOcurrió en la clase '"+ticket.class.getName()+"', en el método 'imprimirDocumento()'",Level.WARNING);
            new logger().exceptionLogger(ticket.class.getName(),Level.WARNING,"imprimirDocumento-PE_T1",x.fillInStackTrace());
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO_H1",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO_H1: "+e.getMessage()+"\nOcurrió en la clase '"+ticket.class.getName()+"', en el método 'imprimirDocumento()'",Level.WARNING);
            new logger().exceptionLogger(ticket.class.getName(),Level.WARNING,"imprimirDocumento-1IO_H1",e.fillInStackTrace());
        }catch(IllegalStateException n){
            
        }finally{
            cabezaLineas.removeAll(cabezaLineas);
            subcabezaLineas.removeAll(subcabezaLineas);
            items.removeAll(items);
            totales.removeAll(totales);
            lineasPie.removeAll(lineasPie);
        }
    }
}