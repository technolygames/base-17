/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.controlador;

/**
 * Clase utilizada para enviar datos sobre el programa.
 * @author erick
 */
public class controlador{
    protected String puesto;
    private String user;
    
    public String getPuesto(){
        return puesto;
    }
    
    public void setPuesto(String puesto){
        this.puesto=puesto;
    }
    
    public String getUser(){
        return user;
    }
    
    public void setUser(String user){
        this.user=user;
    }
}