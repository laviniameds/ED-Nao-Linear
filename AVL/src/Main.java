import interfaces.InvalidPositionException;
import binary.ClassBT;

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
        
        binaria.insert(20, "sou o root");
        binaria.insert(25, "sou o filho direito do root");
        binaria.insert(10, "sou o filho esquerdo do root");
        binaria.insert(15, "sou o filho direito do 10");
        binaria.insert(30, "sou o filho direito do 25");
        binaria.insert(8, "sou o filho esquerdo do 10");
        binaria.mostrar();
    }
}
