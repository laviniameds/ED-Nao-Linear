/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import RN.ClassRN;
import interfaces.InvalidPositionException;
import java.util.Scanner;

/**
 *
 * @author lavinia
 */
public class Main {
    public static void main(String[] args) throws InvalidPositionException{
        ClassRN rubroNegra = new ClassRN();
        
        Scanner sc = new Scanner(System.in);
        String menu = "\n\n0 - Sair\n1 - Inserir\n2 - Remover\n3 - Mostrar";
        String menu2 = "digite o numero:";
        Integer op = -1;
        
        while(op != 0){
            System.out.println(menu);
            op = sc.nextInt();
            
            Integer n;
            
            switch(op){
                case 1:
                    System.out.println(menu2);
                    n = sc.nextInt();
                    rubroNegra.insert(n, n);
                    break;
                case 2:
                    System.out.println(menu2);
                    n = sc.nextInt();
                    rubroNegra.remove(n);
                    break;
                case 3:
                    rubroNegra.mostrar();
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }
    }
}
