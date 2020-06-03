/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.carton;

import bingo.bombo.BomboEuropeo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author andyloz
 */
public final class CartonEuropeo extends Carton {
    public static final int FILAS = 3;
    public static final int COLUMNAS = 9;

    public CartonEuropeo() {
        super(FILAS, COLUMNAS);
    }

    @Override
    public void generarCarton() {
        int CANTIDAD_BOLAS = BomboEuropeo.CANTIDAD_BOLAS;
        
        // Lista de números del 1 al 90 desordenada
        List<Integer> bolas =
                IntStream.rangeClosed(1, CANTIDAD_BOLAS)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(bolas);
        
        
        
        // En este array, cada lista almacena los números para una columna.
        List<Integer>[] numeros = new List[COLUMNAS];
        
        // Inicialización del las listas del array
        for (int i = 0; i < COLUMNAS; i++) {
            numeros[i] = new ArrayList<>();
        }
        
        
        this.primerosNumeros(bolas, numeros);
        this.ultimosNumeros(bolas, numeros);
        
        
        int[][] matriz = null;
        while (matriz == null) {            
            matriz = this.colocarNumeros(numeros);
        }
        
        this.setMatriz(matriz);
    }
    
    private void primerosNumeros(List<Integer> bolas, List<Integer>[] numeros) {
        int colRellenas = 0;
        
        /**
         * Iteramos sobre la lista de bolas hasta que todas las columnas tengan
         * exactamente 1 casilla rellena.
         * Utilizaremos las décimas de los números que vayan saliendo como
         * el índice de la lista donde irá el número.
         */
        for (int i = 0; colRellenas < COLUMNAS; i++) {
            int num = bolas.get(i);
            int decimas = num / 10;
            
            decimas = decimas == 9  // el 90 va en la lista de índice 8
                    ? 8
                    : decimas;
            
            // Comprobamos si la fila a la que apunta el índice está vacía
            if (numeros[decimas].isEmpty()) {
                
                // Añadimos el número a la lista correspondiente
                numeros[decimas].add(num);
                
                // Eliminamos el número de la lista de bolas para que no vuelva
                // a aparecer
                bolas.remove(i);
                
                colRellenas++;
            }
        }
    }
    
    private void ultimosNumeros(List<Integer> bolas, List<Integer>[] numeros) {
        int numsRellenos = 0; // de 6
        
        /**
         * Iteramos sobre la lista de bolas hasta que hayan en las listas
         * exactamente 15 números.
         * Es decir, añadimos 6 más a los que ya estaban
         */
        for (int i = 0; numsRellenos < 6; i++) {
            int num = bolas.get(i);
            int decimas = num / 10;
            
            decimas = decimas == 9 // el 90 va en la lista de índice 8
                    ? 8
                    : decimas;
            /**
             * No pueden haber más de dos números en una misma columna, por lo
             * que tampoco lo habrá en una lista
             */
            if (numeros[decimas].size() != 2) {
                
                // Añadimos el número a la lista correspondiente
                numeros[decimas].add(num);
                
                // Eliminamos el número de la lista de bolas para que no vuelva
                // a aparecer
                bolas.remove(i);
                
                numsRellenos++;
            }
        }
    }

    private int[][] colocarNumeros(List<Integer>[] numeros) {
        int[][] matriz = new int[FILAS][COLUMNAS];
        int[] cantNumsPorFilas = new int[FILAS];
        
        // Se colocarán los números por columnas
        for (int col = 0; col < COLUMNAS; col++) {
            /**
             * Esta variable se encargará de almacenar los índices de las filas
             * disponibles.
             * A lo largo del bucle se irán descartando las filas de la columna
             * que no podrán ser rellenadas
             */
            List<Integer> filasDisp = new ArrayList<>(Arrays.asList(0, 1, 2));
            
            // Si alguna de las filas tienen ya 5 números (el máx. por fila)...
            if (col > 6) {
                for (int fil = 0; fil < FILAS; fil++) {
                    if (cantNumsPorFilas[fil] == 5) {
                        filasDisp.remove((Integer) fil); // ...descartada
                    }
                }
            }
            
            if (col > 1) {                
                for (int fil = 0; fil < FILAS; fil++) {
                    // Si las anteriores dos casillas a la casilla en [fil][col]
                    // están rellenas...
                    if (matriz[fil][col-1] != 0 && matriz[fil][col-2] != 0) {
                        filasDisp.remove((Integer) fil); // ...descartada
                    }
                }
            }
            
            /**
             * Si no hay suficientes filas disponibles para la cantidad de
             * números generados para esa columna, se habrá caído en una 
             * combinación imposible, por lo que no devolerá la matriz
             */
            if (filasDisp.size() >= numeros[col].size()) {
                
                // Si sobran filas, habrá que descartar alguna aleatoriamente
                Random random = new Random();
                
                while (filasDisp.size() != numeros[col].size()) {                    
                    filasDisp.remove(
                            random.nextInt(filasDisp.size()));
                }
                
                /**
                 * Se irán insertando los números generados ordenados en las
                 * filas que indica la lista de filasDisponibles
                 */
                Collections.sort(numeros[col]);
                
                for (int i = 0; i < filasDisp.size(); i++) {
                    int fil = filasDisp.get(i);
                    int numero = numeros[col].get(i);
                    
                    matriz[fil][col] = numero;
                    
                    // Se controlará la cantidad de números insertados por fila
                    cantNumsPorFilas[fil]++;
                }
            } else {
                return null;
            }
        }
        
        return matriz;
    }
}
