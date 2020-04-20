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
