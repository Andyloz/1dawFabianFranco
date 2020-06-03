/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 *
 * @author andyloz
 */
public abstract class Bingo implements Serializable {
    private static final long serialVersionUID = 435401169L;
    
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
    
    public String toPrettyString() {
        String fechaF = fecha.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        return "ID: " + id + "\tFecha: " + fechaF + "\tJugador: " + idJugador;
    }

    @Override
    public String toString() {
        String fechaF = fecha.format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
        return "id=" + id + ", fecha=" + fechaF + ", jugador=" + idJugador;
    }
}
