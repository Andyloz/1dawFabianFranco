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
            System.out.println("\n\n"
                    + bienvenida + "\n\n"
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
            switch (scannerInt(0, 1)) {
                case 1:
                    System.out.println("\n");
                    bingo = crearPartida();
                    System.out.println("\n");
                    break;
                case 2:
                    System.out.println("\n");
                    bingo = partidasGuardadas();
                    System.out.println("\n");
                    break;
                case 0:
                    return;
            }
            
            if (bingo != null) {
                juego(bingo);
                return;
            }
        }
    }
    
    private static Bingo crearPartida() {
        Bingo bingo = null;
        
        System.out.println("¿Cual es tu nombre?");
        String nombre = scannerString();
        System.out.println("\n");
        
        System.out.println("Seleccione:\n\n"
                + "(1) Bingo Americano\n"
                + "(2) Bingo Europeo\n"
                + "(0) Volver");
        
        switch (scannerInt(0, 2)) {
            case 1:
                bingo = new BingoAmericano(nombre);
                break;
            case 2:
                bingo = new BingoEuropeo(nombre);
                break;
        }
        
        return bingo;
    }
    
    private static Bingo partidasGuardadas() {
        Bingo bingo = null;
        return bingo;
    }
    
    private static void juego(Bingo bingo) {
        System.out.println(bingo);
    }
    
    private static String scannerString() {
        Scanner sc = new Scanner(System.in);
        
        String str;
        String ok = "> ";
        String error = "!! > ";
        String prompt = ok;
        do {
            System.out.print(prompt);
            
            str = sc.nextLine();
            
            if (str.length() == 0) {
                prompt = error;
            }
        } while (str.length() == 0);
        
        return str;
    }
    
    private static int scannerInt(int ini, int fin) {
        Scanner sc = new Scanner(System.in);
        
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
    
    private static void enterParaContinuar() {
        System.out.println("Presione [Enter] para continuar");
        try {
            System.in.read();
        } catch (IOException ex) {}
    }
}
