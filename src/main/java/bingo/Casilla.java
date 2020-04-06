/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

/**
 *
 * @author andyloz
 */
public class Casilla {
    
    private int num;
    private boolean tachado;
    private boolean rellenable;

    public Casilla() {
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

    public boolean isRellenable() {
        return rellenable;
    }

    public void setRellenable(boolean rellenable) {
        this.rellenable = rellenable;
    }
}
