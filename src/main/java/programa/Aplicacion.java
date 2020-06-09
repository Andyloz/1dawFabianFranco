/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa;

import bingo.Bingo;
import bingo.BingoAmericano;
import bingo.BingoEuropeo;
import bingo.bombo.Bombo;
import bingo.carton.Carton;
import bingo.dao.BingoMysqlDao;
import java.awt.Point;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
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
    
    private static String empecemos =
              " ___        __   ___  __   ___        __   __       /  /  /\n"
            + "|__   |\\/| |__) |__  /  ` |__   |\\/| /  \\ /__`     /  /  / \n"
            + "|___  |  | |    |___ \\__, |___  |  | \\__/ .__/    .  .  .  \n"
            + "                                                           ";
    
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
            switch (scannerInt(0, 2)) {
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
                jugar(bingo);
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
        if (!comprobarConBd()) {
            System.out.println("No hay conexion con la base de datos");
            return null;
        }
        
        Bingo bingo = null;
        return bingo;
    }
    
    private static void borrarTodo() {
        System.out.println("¿Seguro que quiere borrar todas las partidas?\n");
        System.out.print("S/N ");
        
        String opcion = scannerString("S", "N");
        System.out.println();
        
        switch (opcion) {
            case "S":
                if (dao.deletePartida()) {
                    System.out.println("Partidas borradas.");
                } else {
                    System.out.println("Ha habido un error al borrar las partidas.");
                }
                enterParaContinuar();
                System.out.println("\n");
                break;
            case "N":
                System.out.println("No se han borrado las partidas.");
                enterParaContinuar();
                System.out.println("\n");
        }
    }
    
    private static void jugar(Bingo bingo) {
        Carton carton = bingo.getCarton();
        Bombo bombo = bingo.getBombo();
        
        System.out.println(empecemos + "\n");
        
        while (!carton.esBingo()) {
            Point p = carton.tacharNumero(bombo.sacarBola());
            
            System.out.println(carton.toPrettyString());
            
            if (p != null) {
                int casilla = bombo.getUltBolasSacadas(1).get(0);
                String coordenada = "[Fil "+ (p.x + 1) +", Col "+ (p.y + 1) +"]";
                
                System.out.println("Casilla "+ casilla +" tachada !!!" + coordenada);
                
            } else {
                System.out.println("No se ha tachado ninguna casilla");
            }
            
            System.out.println("\nUltimas bolas sacadas: " + bombo.getUltBolasSacadas(5));
            enterParaContinuar();
            System.out.println("\n");
        }
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
    
    private static String scannerString(String... opciones) {
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
            } else {
                for (String opcion : opciones) {
                    if (str.equalsIgnoreCase(opcion)) {
                        return str.toUpperCase();
                    }
                }
                prompt = error;
            }
        } while (true);
    }
    
    private static int scannerInt(int ini, int fin) {
        Scanner sc = new Scanner(System.in);
        
        if (fin <= ini) {
            throw new IllegalArgumentException(
                    "Fin(" + fin +") mayor o igual que inicio(" + ini + ")");
        }
        
        int opcion;
        String prompt = "> ";
        String error = "!! > ";
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
