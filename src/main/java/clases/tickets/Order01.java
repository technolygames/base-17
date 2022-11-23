package clases.tickets;

public class Order01{
    char[] temp=new char[]{' '};
    
    public Order01(char delimit){
        temp=new char[]{delimit};
    }
    
    public String getTotalNombre(String totalItem){
        String[] delimitado=totalItem.split(""+temp);
        return delimitado[0];
    }
    
    public String getTotalCantidad(String totalItem){
        String[] delimitado=totalItem.split(""+temp);
        return delimitado[1];
    }
    
    public String generarTotal(String nombre,String precio){
        return nombre+temp[0]+precio;
    }
}