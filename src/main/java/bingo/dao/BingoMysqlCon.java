/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

/**
 *
 * @author andyloz
 */
public class BingoMysqlCon {
    private static Connection conexion = null;
    
    private static final String SERVIDOR = "jdbc:mysql://localhost/";
    private static final String BD = "bingo";
    private static final String USER = "bingo";
    private static final String PASS = "bingo";
    private static final String PARAMS = "?serverTimezone=" + TimeZone.getDefault().getID();

    private BingoMysqlCon() {
    }
    
    public static Connection getInstance() throws SQLException {
        
        if (conexion == null) {
            conexion = DriverManager.getConnection(SERVIDOR + BD + PARAMS, USER, PASS);
        }
        
        return conexion;
    }
}
