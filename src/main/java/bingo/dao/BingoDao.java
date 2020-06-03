/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.dao;

import bingo.Bingo;
import java.util.List;

/**
 *
 * @author andyloz
 */
public interface BingoDao {
    
    List<Bingo> getAllBingos();
    Bingo getById();
    boolean saveBingo();
    boolean deleteBingo();
    
}
