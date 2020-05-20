/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.time.LocalDate;

/**
 *
 * @author andyloz
 */
public abstract class Bingo {
    private String id;
    private LocalDate fecha;
    private String idJugador;

    public Bingo(String id, LocalDate fecha, String idJugador) {
        this.id = id;
        this.fecha = fecha;
        this.idJugador = idJugador;
    }

    public String getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(String idJugador) {
        this.idJugador = idJugador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Bingo{" + "id=" + id + ", fecha=" + fecha + ", idJugador=" + idJugador + '}';
    }
}
