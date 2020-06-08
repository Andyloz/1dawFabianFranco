/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.bombo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author andyloz
 */
public abstract class Bombo {
    private List<Integer> listaBolas;
    private List<Integer> bolasSacadas;

    public Bombo() {
        listaBolas = new ArrayList<>();
        bolasSacadas = new ArrayList<>();
        llenarBombo();
    }

    public Bombo(List<Integer> listaBolas) {
        this.listaBolas = listaBolas;
        bolasSacadas = new ArrayList<>();
    }
    
    public int sacarBola() {
        // Devolver el último número de la lista si hay bolas disponibles
        if (listaBolas.size() > 0) {
            int bola = listaBolas.remove(listaBolas.size() - 1);
            // Añadimos la bola al historial de bolas sacadas y la devolvemos
            bolasSacadas.add(bola);
            return bola;
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

    protected List<Integer> getListaBolas() {
        return listaBolas;
    }

    public List<Integer> getUltBolasSacadas(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("num ("+ num +") debe ser mayor que 0");
        }
        
        List<Integer> bolas = new ArrayList<>();
        
        if (num > bolasSacadas.size()) {
            num = bolasSacadas.size();
        }
        
        for (int i = 1; i <= num; i++) {
            bolas.add(bolasSacadas.get(bolasSacadas.size()-i));
        }
        
        return bolas;
    }

    @Override
    public String toString() {
        String str = listaBolas.toString().replace(" ", "");
        return str.substring(1, str.length() - 1);
    }
}
