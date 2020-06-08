/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa;

import bingo.Bingo;
import bingo.BingoAmericano;
import bingo.BingoEuropeo;
import bingo.dao.BingoMysqlDao;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author andyloz
 */
public class Aplicacion {

    static Scanner sc = new Scanner(System.in);
    static String bienvenida = 
            "$$$$$$$\\  $$\\\n"
            + "$$  __$$\\ \\__|\n"
            + "$$ |  $$ |$$\\ $$$$$$$\\   $$$$$$\\   $$$$$$\\\n"
            + "$$$$$$$\\ |$$ |$$  __$$\\ $$  __$$\\ $$  __$$\\\n"
            + "$$  __$$\\ $$ |$$ |  $$ |$$ /  $$ |$$ /  $$ |\n"
            + "$$ |  $$ |$$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |\n"
            + "$$$$$$$  |$$ |$$ |  $$ |\\$$$$$$$ |\\$$$$$$  |\n"
            + "\\_______/ \\__|\\__|  \\__| \\____$$ | \\______/\n"
            + "                        $$\\   $$ |\n"
            + "                        \\$$$$$$  |\n"
            + "                         \\______/\n"
            + "--------------------------------------------";
    
    static boolean bdActiva;
    static BingoMysqlDao dao;

    public static void main(String[] args) {
        while (true) {            
            
        }
    }
    
    public static void bingo() {
        
    }
    
    private static String scannerString() {
        System.out.print("> ");
        return sc.nextLine();
    }
    
    private static int scannerIntPartida(int ini, int fin) {
        if (fin <= ini) {
            throw new IllegalArgumentException(
                    "Fin(" + fin +") mayor o igual que inicio(" + ini + ")");
        }
        
        int opcion;
        String ok = "> ";
        String error = "!! > ";
        String prompt = ok;
        do {
            opcion = ini - 1;
            System.out.print(prompt);
            
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
                prompt = error;
            }
            
            if (opcion < ini || opcion > fin) {
                opcion = ini - 1;
                prompt = error;
            } else {
                prompt = ok;
            }
        } while (opcion == ini - 1);
        
        return opcion;
    }
}
