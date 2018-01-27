/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingbiometricsrecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author eleazar
 */
public class Database {
    Connection connection = null;
    
    public void connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bugc", "root", "");
        }catch(ClassNotFoundException cnf){
            cnf.printStackTrace();
        }catch(SQLException sql){
            sql.printStackTrace();
        }
    }
}
