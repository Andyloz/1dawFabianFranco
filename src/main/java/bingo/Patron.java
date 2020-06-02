/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.awt.Point;
import java.util.List;

/**
 *
 * @author andyloz
 */
public enum Patron {
    
    AHORCADO("El tradicional ahorcado",
            new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3),
            new Point(0,4), new Point(1,4), new Point(2,4), new Point(2,0),
            new Point(2,2), new Point(2,4), new Point(3,1), new Point(3,2),
            new Point(3,3), new Point(3,4), new Point(4,0), new Point(4,2)),
    
    COMPLETO("Relleno del carton completo, menos la casilla completa",
            new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3),
            new Point(0,4), new Point(1,0), new Point(1,1), new Point(1,2),
            new Point(1,3), new Point(1,4), new Point(2,0), new Point(2,1),
            new Point(2,3), new Point(2,4), new Point(3,0), new Point(3,1),
            new Point(3,2), new Point(3,3), new Point(3,4), new Point(4,0),
            new Point(4,1), new Point(4,2), new Point(4,3), new Point(4,4)),
    
    EXPLOSION("Booommm",
            new Point(0,0), new Point(0,4), new Point(1,2), new Point(2,1),
            new Point(2,2), new Point(2,3), new Point(3,2), new Point(4,1),
            new Point(4,4)),
    
    X("Una equis en el tablero",
            new Point(0,0), new Point(0,4), new Point(1,1), new Point(1,3),
            new Point(2,2), new Point(3,1), new Point(3,3), new Point(4,1),
            new Point(4,4));
    
    
    private final String descripcion;
    private final List<Point> coordenadas;

    private Patron(String descripcion, Point... coordenadas) {
        this.descripcion = descripcion;
        this.coordenadas = List.of(coordenadas);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Point> getCoordenadas() {
        return coordenadas;
    }
}
