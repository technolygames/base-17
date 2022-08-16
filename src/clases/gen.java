/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author erick
 */
public class gen {
    public static void main(String[] args) {
        resourceDownload rd=new resourceDownload();
        
        if(rd.checkConnection("https://www.youtube.com/", 80)==true){
            System.out.println("sis");
        }else{
            System.out.println("non");
        }
    }
}