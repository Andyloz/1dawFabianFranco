/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author andyloz
 */
public class JuegoBingo {
    public static void enterParaContinuar() {
        try {
            System.in.read();
        } catch (IOException e) {
        }
        
    }
    
    public static void main(String[] args) {
        System.out.println("     ____  _                   \n" +
                           "    / __ )(_)___  ____ _____   \n" +
                           "   / __  / / __ \\/ __ `/ __ \\\n" +
                           "  / /_/ / / / / / /_/ / /_/ /  \n" +
                           " /_____/_/_/ /_/\\__, /\\____/ \n" +
                           "               /____/          \n");

        System.out.println("--Pulse [Enter] para empezar--\n");
        enterParaContinuar();
        
        Carton c = new Carton();
        System.out.println("Este es tu cartón:");
        System.out.println(c.imprimirCarton()+"\n");
        
        System.out.println("--Pulsa [Enter] para empezar a jugar--\n");
        enterParaContinuar();
        
        Bombo b = new Bombo();
        ArrayList<Integer> nums = new ArrayList<>();
        while (!c.comprobarBingo()) {            
            System.out.println("------------------------------------------\n");
            
            nums.add(b.siguienteBola());
            System.out.println("Ha salido el "+nums.get(nums.size()-1)+".");
            if (c.tacharCasilla(nums.get(nums.size()-1))) {
                System.out.println("Se ha tachado una casilla !!");
            } else {
                System.out.println("No se ha tachado ninguna casilla.");
            }
            if (c.comprobarLinea(0)) {
                System.out.println("Se ha tachado la primera línea entera !!");
            }
            if (c.comprobarLinea(1)) {
                System.out.println("Se ha tachado la segunda línea entera !!");
            }
            if (c.comprobarLinea(2)) {
                System.out.println("Se ha tachado la tercera línea entera !!");
            }
            
            System.out.println(c.imprimirCarton());
            System.out.print("Útlimos 5 números cantados: ");
            
            ArrayList<Integer> reverseNums = (ArrayList<Integer>) nums.clone();
            Collections.reverse(reverseNums);
            
            if (nums.size() < 5) {
                System.out.println(reverseNums);
            } else {
                System.out.println(reverseNums.subList(0, 4));
            }
            
            System.out.println("--Pulse [Enter] para la siguiente bola--\n");
            enterParaContinuar();
        }
    }
}
