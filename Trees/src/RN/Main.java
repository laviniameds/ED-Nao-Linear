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
        
        rubroNegra.insert(4, 4);
        rubroNegra.insert(26, 26);
        rubroNegra.insert(3, 3);
        rubroNegra.insert(9, 9);
        rubroNegra.insert(15, 15);
        
        rubroNegra.mostrar();
        
        rubroNegra.remove(3);
        rubroNegra.mostrar();
    }
}
