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
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author andyloz
 */
public class Aplicacion {

    private static Scanner sc = new Scanner(System.in);
    private static String bienvenida = 
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
    
    private static BingoMysqlDao dao;
    
    private static boolean comprobarConBd() {
        try {
            dao = new BingoMysqlDao();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    
    

    public static void main(String[] args) {
        while (true) {
            System.out.println(bienvenida + "\n\n"
                    + "(1) Jugar\n"
                    + "(0) Salir");
            
            switch (scannerInt(0, 1)) {
                case 1:
                    System.out.println("\n");
                    menuInicial();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }
    
    private static void menuInicial() {
        while (true) {            
            System.out.println("¿Que quiere hacer?\n\n"
                    + "(1) Nueva partida\n"
                    + "(2) Ver partidas guardadas\n"
                    + "(0) Volver");

            Bingo bingo = null;
            switch (scannerInt(0, 2)) {
                default:
                    System.out.println("\n");
                case 1:
                    bingo = crearPartida();
                    break;
                case 2:
                    bingo = partidasGuardadas();
                    break;
                case 0:
                    return;
            }
            
            if (bingo != null) {
                juego(bingo);
            }
        }
    }
    
    private static Bingo crearPartida() {
        
    }
    
    private static Bingo partidasGuardadas() {
        
    }
    
    private static void juego(Bingo bingo) {
        
    }
    
    private static String scannerString() {
        System.out.print("> ");
        return sc.nextLine();
    }
    
    private static int scannerInt(int ini, int fin) {
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
