/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author andyloz
 */
public class Bombo {
    
    private ArrayList<Integer> nums;

    public Bombo() {
        nums = new ArrayList<>(90);
    }
    
    public void llenar() {
        // Rellenamos la lista
        for (int i = 1; i <= 90; i++) {
            nums.add(i);
        }
        // Y la mezclamos
        Collections.shuffle(nums);
    }
    
    public int siguienteBola() {
        try {
            // Devuelve el primer número de la lista
            return nums.remove(0);
        } catch (IndexOutOfBoundsException e) {
            // Si la lista queda vacía, devuelve 0
            return 0;
        }
    }
    
    public int numBolas() {
        return nums.size();
    }

    public ArrayList<Integer> getNums() {
        return nums;
    }
    
    public static String imprimirBola(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("num menor que 0");
        }
        if (num > 99) {
            throw new IllegalArgumentException("num de más de 2 dígitos");
        }
        String str = String.format("%02d", num);
        return "╓──╖\r"
             + "║"+str+"║\r"
             + "╙──╜";
    }
}
