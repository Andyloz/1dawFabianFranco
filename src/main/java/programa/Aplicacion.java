/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa;

import bingo.Bingo;
import bingo.BingoAmericano;
import bingo.BingoEuropeo;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author andyloz
 */
public class Aplicacion {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        System.out.println("$$$$$$$\\  $$\\\n"
                + "$$  __$$\\ \\__|\n"
                + "$$ |  $$ |$$\\ $$$$$$$\\   $$$$$$\\   $$$$$$\\\n"
                + "$$$$$$$\\ |$$ |$$  __$$\\ $$  __$$\\ $$  __$$\\\n"
                + "$$  __$$\\ $$ |$$ |  $$ |$$ /  $$ |$$ /  $$ |\n"
                + "$$ |  $$ |$$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |\n"
                + "$$$$$$$  |$$ |$$ |  $$ |\\$$$$$$$ |\\$$$$$$  |\n"
                + "\\_______/ \\__|\\__|  \\__| \\____$$ | \\______/\n"
                + "                        $$\\   $$ |\n"
                + "                        \\$$$$$$  |\n"
                + "                         \\______/");
        System.out.println("--------------------------------------------\n\n");
        
        
        String nombre = "";
        Bingo bingo = null;
        System.out.println();
        
        switch (scannerPartida()) {
            case 1:
                bingo = new BingoAmericano(nombre);
                break;
            case 2:
                bingo = new BingoEuropeo(nombre);
                break;
        }
        
        System.out.println(nombre);
        System.out.println(bingo.getCarton().toPrettyString());
    }
    
    private static String scannerNombre() {
        System.out.print("¿Cual es tu nombre?\n"
                + "> ");
        return sc.nextLine();
    }
    
    private static int scannerPartida() {
        int opcion = 0;
        System.out.println("¿A que bingo quiere jugar?\n"
                + "  1. Americano\n"
                + "  2. Europeo");
        do {
            System.out.print("> ");
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
            }
        } while (opcion != 1 && opcion != 2);
        System.out.println("\n");
        
        return opcion;
    }
}
