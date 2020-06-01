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
    // 0 -> No rellena   x > 0 -> Rellena   x < 0 -> Tachada 
    private int[][] matriz;

    public Carton(int filas, int columnas) {
        matriz = new int[filas][columnas];
        generarCarton();
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

    protected void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }
    
    public String imprimirCarton() {
        String head = "╔════";
        String mid =  "╠════";
        String tail = "╚════";
        
        for (int col = 0; col < matriz[0].length - 1; col++) {
            head += "╦════";
            mid +=  "╬════";
            tail += "╩════";
        }
        
        head += "╗";
        mid +=  "╣";
        tail += "╝";
        
        String vacio =   "║    ";
        String tachado = "║====";
        String relleno = "║ %02d ";
        
        String str = head + "\n";
        
        for (int fil = 0; fil < matriz.length; fil++) {
            
            for (int col = 0; col < matriz[0].length; col++) {
                int num = matriz[fil][col];
                
                if (num == 0) {
                    str += vacio;
                } else if (num < 0) {
                    str += tachado;
                } else if (num > 0) {
                    str += String.format(relleno, num);
                }
            }
            
            str += "║\n";
            
            if (fil != matriz.length - 1) {
                str += mid + "\n";
            }
        }
        
        return str + tail;
    }
    
    @Override
    public String toString() {
        String str = "";
        
        for (int fil = 0; fil < matriz.length; fil++) {
            for (int col = 0; col < matriz[fil].length; col++) {
                str += matriz[fil][col];
                
                if (fil != matriz.length -1 || col != matriz[fil].length - 1) {
                    str += ",";
                }
            }
        }
        
        return str;
    }
}
