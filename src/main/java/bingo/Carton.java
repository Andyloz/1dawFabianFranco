/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

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
        Random random = new Random();
        
        // Inicialización de las casillas
        gridCasillas = new Casilla[9][3];
        for (int i = 0; i < gridCasillas.length; i++) {
            for (int j = 0; j < gridCasillas[i].length; j++) {
                gridCasillas[i][j] = new Casilla();
            }
        }
        
        generarRellenables();
    }
    
    private void generarRellenables() {
        Random random = new Random();
        
        for (int i = 0; i < gridCasillas.length; i++) {
            // Primero seleccionamos una combinación aleatoriamene
            boolean[] combinacion = combCols[random.nextInt(combCols.length)];
            
            // Y luego asignamos a las casillas como rellenables según
            // la combinación generada
            for (int j = 0; j < gridCasillas[i].length; j++) {
                gridCasillas[i][j].setRellenable(combinacion[j]);
            }
        }
    }

    public Casilla[][] getCasillas() {
        return gridCasillas;
    }
}
