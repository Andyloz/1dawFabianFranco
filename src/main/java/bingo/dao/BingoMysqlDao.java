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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author andyloz
 */
public class BingoMysqlDao implements BingoDao {
    public final Connection con;
    
    private static HashSet<String> ids;
    
    private static void fillIds() {
        if (ids == null) {
            try {
                BingoMysqlDao dao = new BingoMysqlDao();
                ids = new HashSet<>(dao.getAllIds());
                
            } catch (SQLException e) {}
        }
    }
    
    

    public BingoMysqlDao() throws SQLException {
        this.con = BingoMysqlCon.getInstance();
    }

    @Override
    public List<Bingo> getAllPartidas() {
        String sql = "select * from partida";
        List<Bingo> partidas = new ArrayList<>();
        
        try {
            
            ResultSet rset = con.createStatement().executeQuery(sql);
            while (rset.next()) { 
                partidas.add(getPartidaFromCurrentRow(rset));
            }
            
        } catch (SQLException e) {
            return null;
        }
        
        return partidas;
    }
    
    public List<String> getAllIds() {
        String sql = "select id from partida";
        List<String> ids = new ArrayList<>();
        
        try {
            
            ResultSet rset = con.createStatement().executeQuery(sql);
            while (rset.next()) {            
                ids.add(rset.getString("id"));
            }
            
        } catch (SQLException e) {
            return null;
        }
        
        return ids;
    }

    @Override
    public Bingo getById(int id) {
        String sql = "select * from partida where id = ?";
        
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            
            ResultSet rset = st.executeQuery();
            rset.next();
            
            return getPartidaFromCurrentRow(rset);
            
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean savePartida(Bingo bingo) {
        String sql = "insert into partida values (?,?,?,?,?,?)";
        bingo.setId(freeId());
        
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, bingo.getId());
            st.setDate(2, Date.valueOf(bingo.getFecha()));
            st.setString(3, bingo.getIdJugador());
            
            String tipo;
            
            if (bingo instanceof BingoAmericano) {
                tipo = "Americano";
            } else if (bingo instanceof BingoEuropeo) {
                tipo = "Europeo";
            }
            st.setString(4, sql);
            
            st.setString(5, bingo.getBombo().toString());
            st.setString(6, bingo.getCarton().toString());
            
            return st.executeUpdate() == 1;
            
        } catch (SQLException e) {
            return false;
        } catch (SQLIntegrityConstraintViolationException) {
            
        }
    }
    
    private String freeId() {
        fillIds();
        
        String id = LocalDateTime.now().toString();
        
        while (!ids.add(id)) {
            id = LocalDateTime.now().toString();
        }
        
        return id;
    }

    @Override
    public boolean deletePartida() {
        try {
            String sql = "delete from partidas";
            con.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
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
