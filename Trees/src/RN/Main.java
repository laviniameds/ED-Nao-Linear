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
        rubroNegra.insert(33,33);
        rubroNegra.insert(14,14);
        rubroNegra.insert(47,47);
        rubroNegra.insert(10,10);
        rubroNegra.insert(20,20);
        rubroNegra.insert(38,38);
        rubroNegra.insert(51,51);
        rubroNegra.insert(5,5);
        rubroNegra.insert(18,18);
        rubroNegra.insert(36,36);
        rubroNegra.insert(39,39);
        rubroNegra.insert(53,53 );       
        rubroNegra.insert(17,17 );
        rubroNegra.insert(16,16 );
        rubroNegra.insert(15,15 );       
        rubroNegra.insert(37,37 );
        
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
