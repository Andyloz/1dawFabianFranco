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
    
    private CartonEuropeo carton;
    private BomboEuropeo bombo;
    
    public BingoEuropeo(String id, LocalDate fecha, String idJugador) {
        super(id, fecha, idJugador);
        
        this.carton = new CartonEuropeo();
        this.bombo = new BomboEuropeo();
    }    

    public CartonEuropeo getCarton() {
        return carton;
    }

    public BomboEuropeo getBombo() {
        return bombo;
    }

    @Override
    public String toString() {
        return "BingoEuropeo{" + super.toString() + ", carton=" + carton + ", bombo=" + bombo + '}';
    }
}
