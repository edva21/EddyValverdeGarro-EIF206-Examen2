/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import javafx.collections.ObservableList;

/**
 *
 * @author edva5
 */ 
public interface CRUD<UserClass> {
    boolean create(UserClass u);
    UserClass read(Object o);
    ObservableList<UserClass> readAll();
    boolean update(UserClass u);
    boolean delete(Object o);    
}
