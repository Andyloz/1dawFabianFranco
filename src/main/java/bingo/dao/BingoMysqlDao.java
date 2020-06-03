/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.dao;

import bingo.Bingo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public List<Bingo> getAllBingos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Integer> getAllIds() throws SQLException {
        String sql = "select id from partida";
        List<Integer> ids = new ArrayList<>();
        
        ResultSet set = con.createStatement().executeQuery(sql);
        while (set.next()) {            
            ids.add(set.getInt("id"));
        }
        
        return ids;
    }

    @Override
    public Bingo getById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveBingo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteBingo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
