/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author andyloz
 */
public class Carton {
    private Casilla[][] gridCasillas;
    // Diferentes combinaciones de casillas rellenables en una columna
    private static final boolean[][] combCols = {
            {false, false, true},   // [-,-,0]
            {false, true, false},   // [-,0,-]
            {false, true, true},    // [-,0,0]
            {true, false, false},   // [0,-,-]
            {true, false, true},    // [0,-,0]
            {true, true, false},    // [0,0,-]
        };

    public Carton() {
        this.gridCasillas = new Casilla[9][3];
        boolean[][] gridCombinaciones;
        ArrayList<Integer>[] gridNums;
        
        gridCombinaciones = this.generarCombinacion();
        gridNums =          this.generarNumeros(gridCombinaciones);
                            this.colocarNumeros(gridCombinaciones, gridNums);
    }
    
    private boolean[][] generarCombinacion() {
        Random random = new Random();
        boolean[][] gridCombinaciones = new boolean[this.gridCasillas.length][];
        
        // Generaremos combinaciones para cada columna escogiéndolas aleatoriamente
        // de la lista de combinaciones posibles
        for (int i = 0; i < gridCombinaciones.length; i++) {
            gridCombinaciones[i] = combCols[random.nextInt(combCols.length)];
        }
        return gridCombinaciones;
    }
    
    private ArrayList<Integer>[] generarNumeros(boolean[][] gridCombinaciones) {
        Random random = new Random();
        // Almacenaremos los números generados en una variable auxiliar
        ArrayList<Integer>[] gridNums = new ArrayList[this.gridCasillas.length];
        
        // Recorremos las columnas ->
        for (int i = 0; i < gridNums.length; i++) {
            // Instanciamos la lista para la columna i
            gridNums[i] = new ArrayList<>();
            
            // Ahora recorremos la fila de posibilidades para esa columna
            for (boolean combinacion : gridCombinaciones[i]) {
                // Y si encontramos un true
                if (combinacion) {
                    // Añadimos un número aleatorio a la lista de números para esa columna
                    switch (i) {
                        // De las columnas 0 a 7 ->
                        case 0: case 1: case 2: case 3: 
                        case 4: case 5: case 6: case 7:
                            gridNums[i].add(Casilla.generarNumero(i));
                            break;
                        // Para la última columna ->
                        case 8:
                            gridNums[i].add(Casilla.generarNumero(80,99));
                            break;
                    }
                }
            }
            
            // Una vez generados los números:
            //     Si se han generado dos números para la columna i
            while (gridNums[i].size() == 2 &&
                    // Comprobar si los dos números son iguales
                    gridNums[i].get(0).equals(gridNums[i].get(1))) {
                gridNums[i].clear();
                gridNums[i].add(Casilla.generarNumero(i));
                gridNums[i].add(Casilla.generarNumero(i));
            // Repetir hasta que los números sean distintos
            }
            
            // Por último ordenar los números en la lista
            Collections.sort(gridNums[i]);
        }
        
        return gridNums;
    }
    
    private void colocarNumeros(
            boolean[][] gridCombinaciones, ArrayList<Integer>[] gridNums) {
        for (int i = 0; i < this.gridCasillas.length; i++) {
            for (int j = 0; j < this.gridCasillas[i].length; j++) {
                if (gridCombinaciones[i][j]) {
                    this.gridCasillas[i][j] = new Casilla(gridNums[i].remove(0));
                }
            }
        }
    }

    @Override
    public String toString() {
        String str = "╔════╦════╦════╦════╦════╦════╦════╦════╦════╗";
        for (int j = 0; j < this.gridCasillas[0].length; j++) {
            str += "\n║";
            for (int i = 0; i < this.gridCasillas.length; i++) {
                str += this.gridCasillas[i][j] == null
                        ? "    ║"
                        :  String.format(" %2s ║", this.gridCasillas[i][j].getNum());
            }
            switch (j) {
                case 0: case 1:
                    str += "\n╠════╬════╬════╬════╬════╬════╬════╬════╬════╣";
                    break;
                case 2:
                    str += "\n╚════╩════╩════╩════╩════╩════╩════╩════╩════╝";
                    break;
            }
        }
        return str;
    }

    public Casilla[][] getCasillas() {
        return gridCasillas;
    }
}
