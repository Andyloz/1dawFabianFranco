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
    private static final long serialVersionUID = 386190036L;
    
    private CartonAmericano carton;
    private BomboAmericano bombo;
    
    public BingoAmericano(String id, LocalDate fecha, String idJugador) {
        super(id, fecha, idJugador);
        
        this.carton = new CartonAmericano();
        this.bombo = new BomboAmericano();
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
        return "BingoAmericano{" + super.toString() + ", carton=" + carton + ", bombo=" + bombo + '}';
    }
}
