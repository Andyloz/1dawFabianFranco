/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.dao;

import bingo.Bingo;
import bingo.BingoAmericano;
import bingo.BingoEuropeo;
import bingo.bombo.Bombo;
import bingo.bombo.BomboAmericano;
import bingo.bombo.BomboEuropeo;
import bingo.carton.Carton;
import bingo.carton.CartonAmericano;
import bingo.carton.CartonEuropeo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author andyloz
 */
public class BingoMysqlDao implements BingoDao {
    public final Connection con;

    public BingoMysqlDao() throws SQLException {
        this.con = BingoMysqlCon.getInstance();
    }

    @Override
    public List<Bingo> getAllPartidas() {
    	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Integer> getAllIds() {
        String sql = "select id from partida";
        List<Integer> ids = new ArrayList<>();
        
        try {
            
            ResultSet rset = con.createStatement().executeQuery(sql);
            while (rset.next()) {            
                ids.add(rset.getInt("id"));
            }
            
        } catch (SQLException e) {
            return null;
        }
        
        return ids;
    }

    @Override
    public Bingo getById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean savePartida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletePartida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Bingo getPartidaFromCurrentRow(ResultSet rset) throws SQLException {
        Bingo bingo = null;
        
        String id = rset.getString("id");
        LocalDate fecha = rset.getDate("fecha").toLocalDate();
        String idJugador = rset.getString("idJugador");
        String tipo = rset.getString("tipo");

        switch (tipo) {
            case "Americano":
                CartonAmericano cartonA = (CartonAmericano) buildCarton(tipo, rset.getString("carton"));
                BomboAmericano bomboA = (BomboAmericano) buildBombo(tipo, rset.getString("bombo"));
                bingo = new BingoAmericano(id, fecha, idJugador, cartonA, bomboA);
                break;
            case "Europeo":
                CartonEuropeo cartonE = (CartonEuropeo) buildCarton(tipo, rset.getString("carton"));
                BomboEuropeo bomboE = (BomboEuropeo) buildBombo(tipo, rset.getString("bombo"));
                bingo = new BingoEuropeo(id, fecha, idJugador, cartonE, bomboE);
                break;
        }
        
        return bingo;
    }
    
    private Carton buildCarton(String tipo, String cartonIn) {
        int filas = 0;
        int columnas = 0;
        
        switch (tipo) {
            case "Americano":
                filas = CartonAmericano.FILAS;
                columnas = CartonAmericano.COLUMNAS;
                break;
            case "Europeo":
                filas = CartonEuropeo.FILAS;
                columnas = CartonEuropeo.COLUMNAS;
                break;
        }
        
        String[] numeros = cartonIn.split(",");
        int[][] matriz = new int[filas][columnas];
        
        for (int fil = 0; fil < filas; fil++) {
            for (int col = 0; col < columnas; col++) {
                int i = fil * columnas + col;
                matriz[fil][col] = Integer.parseInt(numeros[i]);
            }
        }
        
        switch (tipo) {
            case "Americano":
                return new CartonAmericano(matriz);
            case "Europeo":
                return new CartonEuropeo(matriz);
            default:
                return null;
        }
    }
    
    private Bombo buildBombo(String tipo, String bomboIn) {
        List<Integer> listaBolas = Stream.of(bomboIn.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        
        switch (tipo) {
            case "Americano":
                return new BomboAmericano(listaBolas);
            case "Europeo":
                return new BomboEuropeo(listaBolas);
            default:
                return null;
        }
    }
}
