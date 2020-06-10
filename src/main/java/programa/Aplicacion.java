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
            
            int opcion = scannerInt(0, 1);
            
            System.out.println("\n");
            switch (opcion) {
                case 1:
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
                    + "(0) Volver\n");
            
            Bingo bingo = null;
            int opcion = scannerInt(0, 2);
            
            System.out.println("\n");
            switch (opcion) {
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
        
        int opcion = scannerInt(0, 2);
        
        System.out.println("\n");
        switch (opcion) {
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
        while (true) {            
            
            if (!comprobarConBd()) {
                System.out.println("No hay conexion con la base de datos\n");
                return null;
            }

            Bingo bingo = null;
            List<Bingo> partidas = dao.getAllPartidas();
            
            if (partidas.size() > 0) {

                    System.out.println("Seleccione una partida:\n");

                    for (int i = 0; i < partidas.size(); i++) {
                        Bingo p = partidas.get(i);
                        System.out.println("("+ (i+1) +") " + p.toPrettyString());
                    }
                    
                    System.out.println("\n(-1) Borrar todas las partidas");
                    System.out.println("(0) Volver");
                    
                    int opcion = scannerInt(-1, partidas.size());
                    
                    System.out.println("\n");
                    switch (opcion) {
                        case 0:
                            return null;
                        case -1:
                            borrarTodo();
                            continue;
                    }
                    
                    opcion--;
                    bingo = gestionarPartida(partidas.get(opcion));
                    
                    if (bingo != null) {
                        return bingo;
                    }

            } else {
                System.out.println("No hay partidas guardadas\n");
                enterParaContinuar();
                System.out.println("\n");
                return null;
            }
        }
    }
    
    private static Bingo gestionarPartida(Bingo bingo) {
        while (true) {            
            System.out.println("Partida:\n"
                    + bingo.toPrettyString() + "\n\n"
                    
                    + "(1) Jugar esta partida\n"
                    + "(2) Cambiar ID jugador\n"
                    + "(3) Eliminar partida\n"
                    + "(0) Volver\n");
            
            int opcion = scannerInt(0, 3);
            
            System.out.println("\n");
            switch (opcion) {
                case 1:
                    return bingo;
                case 2:
                    cambiarIdjugador(bingo);
                    break;
                case 3:
                    if (borrarPartida(bingo)) {
                        return null;
                    }
                    break;
            }
        }
    }
    
    private static boolean borrarPartida(Bingo bingo) {
        System.out.println("¿Seguro que quiere borrar la partida?\n"
                + bingo.toPrettyString() + "\n\n"
                
                + "S/N ");
        
        boolean estaBorrado = false;
        String opcion = scannerString("S", "N");
        System.out.println();
        
        switch (opcion) {
            case "S":
                if (dao.deletePartida(bingo)) {
                    System.out.println("Partida borrada.");
                    estaBorrado = true;
                } else {
                    System.out.println("Ha habido un error al borrar la partida.");
                }
                enterParaContinuar();
                break;
            case "N":
                System.out.println("No se ha borrado la partida.");
                enterParaContinuar();
                break;
        }
        System.out.println("\n");
        
        return estaBorrado;
    }
    
    private static boolean borrarTodo() {
        System.out.println("¿Seguro que quiere borrar todas las partidas?\n"
                + "S/N ");
        
        boolean estaBorrado = false;
        String opcion = scannerString("S", "N");
        System.out.println();
        
        switch (opcion) {
            case "S":
                if (dao.deletePartida()) {
                    System.out.println("Partidas borradas.");
                    estaBorrado = true;
                } else {
                    System.out.println("Ha habido un error al borrar las partidas.");
                }
                enterParaContinuar();
                break;
            case "N":
                System.out.println("No se han borrado las partidas.");
                enterParaContinuar();
                break;
        }
        System.out.println("\n");
        
        return estaBorrado;
    }
    
    private static Bingo cambiarIdjugador(Bingo bingo) {
        System.out.println("Introduzca el id de jugador (o 0 para cancelar):");
        
        String nombre = scannerString();
        
        System.out.println();
        switch (nombre) {
            case "0":
                System.out.println("No se ha cambiado el id de jugador");
                break;
            default:
                bingo.setIdJugador(nombre);
                System.out.println("Se ha cambiado el id de jugador a " + nombre);
                break;
        }
        
        System.out.println("\n");
        return bingo;
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
    
    private static boolean salirOSeguir() {
        System.out.println("Siguiente bola [B] - Salir [S]");
        
        String opcion = scannerString("B", "S");
        System.out.println();
        switch (opcion) {
            case "S":
                return true;
        }
                
        return false;
    }
    
    private static boolean esSeguro(String msg) {
        System.out.println(msg);

        String opcion = scannerSiNo();
        System.out.println("\n");

        switch (opcion) {
            case "N":
                return false;
        }
        return true;
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
    
    private static String scannerSiNo() {
        String[] opciones = {"S", "N"};
        
        Scanner sc = new Scanner(System.in);
        
        String str;
        String ok = "S/N > ";
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
