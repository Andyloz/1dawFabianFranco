/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.dao;

import bingo.Bingo;
import bingo.carton.Carton;
import bingo.carton.CartonAmericano;
import bingo.carton.CartonEuropeo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
}
