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
    
    private final static class ConstructorCarton {
        
        private Casilla[][] gridCasillas;

        private boolean[][] gridCombs;
        private ArrayList<Integer> filsDisp;
        private ArrayList<Integer> colsDisp;
        
        private int[] numCasPorCol;

        public ConstructorCarton() {
            this.gridCasillas = new Casilla[3][9];

            // Esta variable marca las casillas a las que se les va a asignar un
            // número
            this.gridCombs = new boolean[3][9];

            // Variable de filas disponibles (véase this.comprobarFilsDisp(int))
            this.filsDisp = new ArrayList<>(3);
            this.filsDisp.add(0);
            this.filsDisp.add(1);
            this.filsDisp.add(2);

            // Variable de columnas disponibles (véase this.comprobarColsDisp(int))
            this.colsDisp = new ArrayList<>(9);
            this.colsDisp.add(0); this.colsDisp.add(1); this.colsDisp.add(2);
            this.colsDisp.add(3); this.colsDisp.add(4); this.colsDisp.add(5);
            this.colsDisp.add(6); this.colsDisp.add(7); this.colsDisp.add(8);
            
            // Esta variable almacenará el número de casillas asignadas por columna.
            this.numCasPorCol = new int[9];
        }

        private void primerasCasillas() {
            Random rnd = new Random();
            // Recorremos las columnas...
            for (int cols = 0; cols < 9; cols++) {
                // Fila candidata generada
                int fil = this.filsDisp.get(rnd.nextInt(this.filsDisp.size()));

                // A partir de la 3ra iteración comprobará que la casilla sea asignable
                if (cols > 1) {
                    if (!this.casillaAsignable(fil, cols)) {
                        // Si no es asignable se generará una fila diferente
                        cols--;
                        continue;
                    }
                }
                this.asignarCasilla(fil, cols);

                // A partir de la 5ta iteración, es necesario comprobar qué filas
                // están llenas
                if (cols > 3) {
                    this.comprobarFilsDisp(fil);
                }
            }
        }

        // Marca un true en la posición de la casilla en el grid de Combinación del
        // cartón para su posterior rellenado
        private void asignarCasilla(int fil, int col) {
            this.gridCombs[fil][col] = true;
        }
        
        private boolean casillaAsignable(int fil, int col) {
            // Comprobar anteriores...
            boolean anterior = false;
            // ...si hay espacio atrás
            if (col > 0) {
                // Comprobar anterior
                anterior = this.gridCombs[fil][col - 1];
            }
            // Si las dos anteriores están asignadas devolver false...
            if (col > 1 && anterior && this.gridCombs[fil][col - 2]) {
                return false;
            }

            // Comprobar posteriores
            boolean posterior = false;
            // ...si hay espacio después
            if (col < 8) {
                // Comprobar posterior
                posterior = this.gridCombs[fil][col + 1];
            }
            // Si las dos posteriores están asignadas, devolver false
            if (col < 7 && posterior && this.gridCombs[fil][col + 2]) {
                return false;
            }

            // ...si no, devolver true
            return true;
        }
        
        // Comprueba que no se vayan a asignar más casillas de las que se pueden
        // asignar en una fila (5)
        private void comprobarFilsDisp(int fil) {
            int contCasillas = 0;
            // Recorremos las casillas para esa fila
            for (int cols = 0; cols < 9; cols++) {
                // Si la casilla ha sido asginada
                if (gridCombs[fil][cols]) {
                    // .. la cuenta
                    contCasillas++;
                }
            }
            if (contCasillas > 4) {
                // Si la fila llega a tener 5 casillas asignadas, la retira de las 
                // filas disponibles para rellenar
                this.filsDisp.remove((Integer) fil);
            }
        }

        // Comprueba que no se vayan a asginar más casillas de las que se pueden
        // asginar en una columna (2)
        private void comprobarColsDisp(int col) {
            int contCasillas = 0;
            // Recorremos las casillas para esa columna
            for (int fils = 0; fils < 3; fils++) {
                // Si la casilla ha sido asignada
                if (gridCombs[fils][col]) {
                    // .. la cuenta
                    contCasillas++;
                }
            }
            if (contCasillas > 1) {
                // Si la columna llega a tener 2 casillas asignadas, la retira de las 
                // filas disponibles para rellenar
                this.colsDisp.remove((Integer) col);
            }
            // Aprovechamos el método para contar las casillas que hay en esa columna
            this.numCasPorCol[col] = contCasillas;
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
