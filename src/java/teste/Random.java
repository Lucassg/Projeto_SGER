/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

/**
 *
 * @author Lucas Garcia
 */
public class Random {
    
    public static void main(String[] args){
        
       java.util.Random rnd = new java.util.Random();
       
       for(int i = 0; i < 316; i++){
        
            System.out.println(rnd.nextInt(316));
       }
    }
}


