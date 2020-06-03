/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.dao;

import bingo.Bingo;
import java.sql.Connection;
import java.sql.SQLException;
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
