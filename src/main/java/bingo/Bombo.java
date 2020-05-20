/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andyloz
 */
public abstract class Bombo {
    private List<Integer> listaBolas;

    public Bombo() {
        listaBolas = new ArrayList<>();
    }
    
    public int sacarBola() {
        // Devolver el último número de la lista si hay bolas disponibles
        if (listaBolas.size() > 0) {
            return listaBolas.get(listaBolas.size() - 1);
        }
        // Si no, devolver un número negativo
        return -1;
    }
    
    public abstract void llenarBombo();
    
    public int bolasDentro() {
        return listaBolas.size();
    }
    
    public boolean vacio() {
        return listaBolas.isEmpty();
    }
}
