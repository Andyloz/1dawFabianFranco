/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import bingo.bombo.BomboEuropeo;
import bingo.carton.CartonEuropeo;
import java.time.LocalDate;

/**
 *
 * @author andyloz
 */
public final class BingoEuropeo extends Bingo {
    
    public BingoEuropeo(String idJugador) {
        super(idJugador, new CartonEuropeo(), new BomboEuropeo());
    }
    
    public BingoEuropeo(String id, LocalDate fecha, String idJugador,
            CartonEuropeo carton, BomboEuropeo bombo) {
        super(id, fecha, idJugador, carton, bombo);
    }
    
    @Override
    public String toPrettyString() {
        return "Bingo Europeo\t" + super.toPrettyString();
    }

    @Override
    public String toString() {
        return "Europeo," + super.toString();
    }
}
