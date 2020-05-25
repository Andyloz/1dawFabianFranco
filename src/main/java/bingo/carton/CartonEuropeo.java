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
}
