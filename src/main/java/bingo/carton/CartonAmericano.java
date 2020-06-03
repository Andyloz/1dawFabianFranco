/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.carton;

import bingo.Patron;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author andyloz
 */
public final class CartonAmericano extends Carton {
    
    public static final int FILAS = 5;
    public static final int COLUMNAS = 5;
    
    private final Patron premio;

    public CartonAmericano() {
        super(FILAS, COLUMNAS);
        this.premio = generarPremio();
    }

    @Override
    public void generarCarton() {
        HashSet<Integer>[] numeros = new HashSet[COLUMNAS];
        
        // Inicializamos los sets de numeros
        for (int col = 0; col < COLUMNAS; col++) {
            numeros[col] = new HashSet<>();
        }
        
        generarNumeros(numeros);
        colocarNumeros(numeros);
    }
    
    
    private void generarNumeros(HashSet<Integer>[] numeros) {
        Random random = new Random();
        
        // Generamos los números por columnas
        for (int col = 0; col < COLUMNAS; col++) {
            int rangoOrigen = 15 * col; // Número mínimo a generar
            int limiteRango = 16; // Cantidad de números a partir del mín.
            
            do {
                numeros[col].add(random.nextInt(limiteRango) + rangoOrigen);
                /**
                 * Si el número generado ya está presente en el set, no será
                 * añadido
                **/
            } while (numeros[col].size() != 5);
        }
    }
    
    private void colocarNumeros(HashSet<Integer>[] numeros) {
        int[][] matrizTranspuesta = new int[FILAS][];
        
        // Pasamos cada set al array y lo asignamos a su correspondiente fila
        for (int i = 0; i < FILAS; i++) {
            matrizTranspuesta[i] = numeros[i].stream()
                    .mapToInt(x -> x)
                    .sorted() // ordenamos los números antes de pasarlos a array
                    .toArray();
        }
        
        // Casilla central vacía
        matrizTranspuesta[2][2] = 0;
        
        // Como los números de las columnas están en filas, tenemos que 
        // transponer el array bidimensional
        int[][] matriz = new int[FILAS][COLUMNAS];
        
        for (int col = 0; col < COLUMNAS; col++) {
            for (int fil = 0; fil < FILAS; fil++) {
                matriz[fil][col] = matrizTranspuesta[col][fil];
            }
        }
        
        this.setMatriz(matriz);
    }

    private Patron generarPremio() {
        Random random = new Random();
        Patron[] patrones = Patron.values();
        
        return patrones[random.nextInt(patrones.length)];
    }
}
