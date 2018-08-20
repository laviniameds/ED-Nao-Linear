package AVL;

import interfaces.InvalidPositionException;
import AVL.ClassBT;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lavinia
 */
public class Main {
    public static void main(String[] args) throws InvalidPositionException{
        ClassBT binaria = new ClassBT();
        
        binaria.insert(10, 0);
        binaria.insert(20, 0);
        binaria.insert(30, 0);
        binaria.mostrar();
        
       /* binaria.insert(40, 0);
        binaria.insert(50, 0);
        binaria.insert(25, 0);
        binaria.mostrar();
        
        binaria.insert(60, 0);
        binaria.insert(70, 0);
        binaria.insert(80, 0);
        binaria.insert(90, 0);
        binaria.mostrar();*/
    }
}
