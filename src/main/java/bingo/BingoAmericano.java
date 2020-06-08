/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import bingo.bombo.BomboAmericano;
import bingo.carton.CartonAmericano;
import java.time.LocalDate;

/**
 *
 * @author andyloz
 */
public final class BingoAmericano extends Bingo {
    
    public BingoAmericano(String idJugador) {
        super(idJugador, new CartonAmericano(), new BomboAmericano());
    }
    
    public BingoAmericano(String id, LocalDate fecha, String idJugador,
            CartonAmericano carton, BomboAmericano bombo) {
        super(id, fecha, idJugador, carton, bombo);
    }
    
    @Override
    public String toPrettyString() {
        return "Bingo Americano -> " + super.toPrettyString();
    }

    @Override
    public String toString() {
        return "Americano," + super.toString();
    }
}
