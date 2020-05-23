/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.carton;

import java.awt.Point;

/**
 *
 * @author andyloz
 */
public abstract class Carton {
    private int[][] matriz;

    public Carton(int filas, int columnas) {
        matriz = new int[filas][columnas];
    }
    
    public abstract void generarCarton();
    
    public Point tacharNumero(int numero) {
        int tachado = -1;
        
        int filas = matriz.length;
        int columnas = matriz[0].length;
        
        for (int fil = 0; fil < filas; fil++) {
            for (int col = 0; col < columnas; col++) {
                // Si la casilla esta rellena coincide con el número dado
                if (matriz[fil][col] == numero) {
                    
                    matriz[fil][col] = tachado;
                    return new Point(fil, col);
                }
            }
        }
        return null;
    }
    
    public boolean esLinea(int numFila) {
        if (numFila < 0 || numFila > matriz.length) {
            throw new IndexOutOfBoundsException(numFila);
        }
        
        int columnas = matriz[0].length;
        
        for (int col = 0; col < columnas; col++) {
            // Si la casilla esta rellena y no está tachada...
            if (matriz[numFila][col] > 0) {
                    return false;
            }
        }
        return true;
    }
    
    public boolean esBingo() {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        
        for (int fil = 0; fil < filas; fil++) {
            for (int col = 0; col < columnas; col++) {
                // Si la casilla esta rellena y no está tachada...
                if (matriz[fil][col] > 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
