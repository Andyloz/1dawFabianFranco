/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.carton;

import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author andyloz
 */
public final class CartonAmericano extends Carton {
    
    public static final int FILAS = 5;
    public static final int COLUMNAS = 5;

    public CartonAmericano() {
        super(FILAS, COLUMNAS);
    }

    @Override
    public void generarCarton() {
        HashSet<Integer>[] numeros = new HashSet[COLUMNAS];
        
        for (int i = 0; i < COLUMNAS; i++) {
            numeros[i] = new HashSet<>();
        }
        
        generarNumeros(numeros);
    }
    
    
    private void generarNumeros(HashSet<Integer>[] numeros) {
        Random random = new Random();
        
        for (int i = 0; i < COLUMNAS; i++) {
            int rangoOrigen = 15 * i;
            int limiteRango = 16;
            
            while (numeros[i].size() != 5) {
                numeros[i].add(random.nextInt(limiteRango) + rangoOrigen);
                /**
                 * Si el número generado ya está presente en el Set, no será
                 * añadido
                **/
            }
        }
    }
}
