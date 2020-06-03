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
    private CartonAmericano carton;
    private BomboAmericano bombo;
    
    public BingoAmericano(String idJugador) {
        super(idJugador);
        
        this.carton = new CartonAmericano();
        this.bombo = new BomboAmericano();
    }
    
    public BingoAmericano(String id, LocalDate fecha, String idJugador,
            CartonAmericano carton, BomboAmericano bombo) {
        super(id, fecha, idJugador);
        
        this.carton = carton;
        this.bombo = bombo;
    }

    public CartonAmericano getCarton() {
        return carton;
    }

    public BomboAmericano getBombo() {
        return bombo;
    }
    
    @Override
    public String toPrettyString() {
        return "Bingo Americano -> " + super.toPrettyString();
    }

    @Override
    public String toString() {
        return super.toString() + "Americano";
    }
}
