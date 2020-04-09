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
public class Casilla {
    
    private int num;
    private boolean tachado;

    public Casilla(int num) {
        this.num = num;
        this.tachado = false;
    }
    
    public static int generarNumero(int decimas) {
        Random random = new Random();
        return random.nextInt(9)+1 + (decimas*10);
    }
    
    public static int generarNumero(int inicio, int fin) {
        Random random = new Random();
        return random.nextInt(fin-inicio+1) + inicio;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isTachado() {
        return tachado;
    }

    public void setTachado(boolean tachado) {
        this.tachado = tachado;
    }
}
