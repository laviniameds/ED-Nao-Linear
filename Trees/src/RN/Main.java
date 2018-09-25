/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import RN.ClassRN;
import interfaces.InvalidPositionException;

/**
 *
 * @author lavinia
 */
public class Main {
    public static void main(String[] args) throws InvalidPositionException{
        ClassRN rubroNegra = new ClassRN();
        
        rubroNegra.insert(10, 10);
        rubroNegra.mostrar();
        
        rubroNegra.insert(20, 20);
        rubroNegra.mostrar();  
        
        rubroNegra.insert(30, 30);
        rubroNegra.mostrar();
        
        rubroNegra.insert(40, 40);
        rubroNegra.mostrar();
        
        rubroNegra.insert(50, 50);
        rubroNegra.mostrar();
        
        rubroNegra.insert(25, 25);
        rubroNegra.mostrar();
        
        rubroNegra.insert(60, 60);
        rubroNegra.mostrar();
        
        rubroNegra.insert(70, 70);
        rubroNegra.mostrar();
        
        rubroNegra.insert(80, 80);
        rubroNegra.mostrar();
        
        rubroNegra.insert(35, 35);
        rubroNegra.mostrar();
        
        //teste remoção
        
        /*rubroNegra.remove(40);
        rubroNegra.mostrar();
        
        rubroNegra.remove(25);
        rubroNegra.mostrar();
        
        rubroNegra.remove(50);
        rubroNegra.mostrar();        
        
        rubroNegra.remove(10);
        rubroNegra.mostrar();        
        
        rubroNegra.remove(35);
        rubroNegra.mostrar();
        
        rubroNegra.remove(30);
        rubroNegra.mostrar();
        
        rubroNegra.remove(20);
        rubroNegra.mostrar();
        
        rubroNegra.remove(70);
        rubroNegra.mostrar();*/
        
    }
}
