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
        
        binaria.insert(10, 10);
        binaria.mostrar();
        
        binaria.insert(20, 20);
        binaria.mostrar();    
        
        binaria.insert(30, 30);
        binaria.mostrar();
        
        binaria.insert(40, 40);
        binaria.mostrar();
        
        binaria.insert(50, 50);
        binaria.mostrar();
        
        binaria.insert(25, 25);
        binaria.mostrar();
        
        binaria.insert(60, 60);
        binaria.mostrar();
        
        binaria.insert(70, 70);
        binaria.mostrar();
        
        binaria.insert(80, 80);
        binaria.mostrar();
        
        binaria.insert(90, 90);
        binaria.mostrar();
    }
}
