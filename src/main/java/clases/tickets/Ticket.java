package clases.tickets;
//clases
import clases.logger;
//java
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.print.Doc;
import javax.print.SimpleDoc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintException;
import javax.print.PrintServiceLookup;
//extension larga
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

/**
 * Clase encargada de imprimir y dar forma al Ticket.
 * 
 * @author unknown
 */
public class Ticket{
    static ArrayList<String> cabezaLineas=new ArrayList<String>();
    static ArrayList<String> subcabezaLineas=new ArrayList<String>();
    static ArrayList<String> items=new ArrayList<String>();
    static ArrayList<String> totales=new ArrayList<String>();
    static ArrayList<String> lineasPie=new ArrayList<String>();
    
    /**
     * Inicializa la instancia para imprimir el ticket por medio de la ticketera.
     * 
     * @param impresora a la que se imprimirá el documento.
     * @param gaveta Abrirá la gaveta.
     */
    public Ticket(String impresora,boolean gaveta){
        String methodName="ticket";
        String cadena="";
        try{
            FileWriter imp=new FileWriter(impresora,StandardCharsets.UTF_8);
            char[] caracter=new char[]{0x1B,'R',18};
            char[] cortarPapel=new char[]{0x1B,'m'};
            
            imp.write(caracter);
            for(int cabecera=0;cabecera<cabezaLineas.size();cabecera++){
                cadena+=cabezaLineas.get(cabecera);
                imp.write(cadena);
                setFormato(imp,27);
            }
            for(int subcabecera=0;subcabecera<subcabezaLineas.size();subcabecera++){
                cadena+=subcabezaLineas.get(subcabecera);
                imp.write(cadena);
            }
            for(int ITEM=0;ITEM<items.size();ITEM++){
                cadena+=items.get(ITEM);
                imp.write(cadena);
            }
            for(int total=0;total<totales.size();total++){
                cadena+=totales.get(total);
                imp.write(cadena);
            }
            for(int pie=0;pie<lineasPie.size();pie++){
                cadena+=lineasPie.get(pie);
                imp.write(cadena);
            }
            imp.write(cortarPapel);
            
            DocFlavor flavor=DocFlavor.BYTE_ARRAY.AUTOSENSE;
            PrintService service=PrintServiceLookup.lookupDefaultPrintService();
            DocPrintJob pj=service.createPrintJob();
            byte[] bytes=cadena.getBytes();
            Doc doc=new SimpleDoc(bytes,flavor,null);
            
            pj.print(doc,null);
            
            abrirGaveta(imp,gaveta);
            
            remove();
            
            imp.flush();
            imp.close();
        }catch(PrintException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,Ticket.class.getName(),methodName,"PE_T1");
            remove();
        }catch(IOException x){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,x,Ticket.class.getName(),methodName,"2IO_H1");
            remove();
        }catch(IllegalStateException n){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,n,Ticket.class.getName(),methodName,"15");
            remove();
        }
    }
    
    protected void remove(){
        cabezaLineas.clear();
        subcabezaLineas.clear();
        items.clear();
        totales.clear();
        lineasPie.clear();
    }
    
    public static void addCabecera(String linea){
        cabezaLineas.add(linea);
    }
    
    public static void addSubcabecera(String linea){
        subcabezaLineas.add(linea);
    }
    
    public static void addItem(String cantidad,String item,String precio){
        Order2 newItem=new Order2(' ');
        items.add(newItem.generarItem(cantidad,item,precio));
    }
    
    public static void addTotal(String nombre,String precio){
        Order1 newTotal=new Order1(' ');
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
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,Ticket.class.getName(),"setFormato","2IO_T2");
        }
    }
    
    public static String darEspacio(){
        return "\n";
    }
    
    public static void abrirGaveta(FileWriter fw,boolean flag){
        try{
            if(flag==true){
                char abrir[]={(char)27,(char)112,(char)0,(char)10,(char)100};
                fw.write(abrir);
            }
        }catch(IOException e){
            new logger(Level.SEVERE).storeAndViewCaughtException(null,e,Ticket.class.getName(),"abrirGaveta","2IO_T3");
        }
    }
}