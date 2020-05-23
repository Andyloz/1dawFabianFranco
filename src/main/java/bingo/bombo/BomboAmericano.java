/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.bombo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author andyloz
 */
public final class BomboAmericano extends Bombo {
    public static final int CANTIDAD_BOLAS = 75;

    public BomboAmericano() {
        super();
    }

    @Override
    public void llenarBombo() {
        List<Integer> bolas = this.getListaBolas();
        bolas.clear();
        
        bolas.addAll(
                IntStream.rangeClosed(1, CANTIDAD_BOLAS)
                        .boxed()
                        .collect(Collectors.toList()) );
        
        Collections.shuffle(bolas);
    }
}
