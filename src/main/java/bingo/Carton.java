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
