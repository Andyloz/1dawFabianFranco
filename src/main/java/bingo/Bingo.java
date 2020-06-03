/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 *
 * @author andyloz
 */
public abstract class Bingo {    
    private String id;
    private LocalDate fecha;
    private String idJugador;

    public Bingo(String idJugador) {
        this.idJugador = truncarNombre(idJugador);
        this.fecha = LocalDate.now();
    }
    
    public Bingo(String id, LocalDate fecha, String idJugador) {
        this.id = id;
        this.fecha = fecha;
        this.idJugador = truncarNombre(idJugador);
    }
    
    private String truncarNombre(String idJugador) {
        if (idJugador.length() > 50) {
            idJugador = idJugador.substring(0, 50);
        }
        
        return idJugador;
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
        String fechaF = fecha.format(DateTimeFormatter.ISO_LOCAL_DATE);
        return id + "," + fechaF + "," + idJugador;
    }
}
