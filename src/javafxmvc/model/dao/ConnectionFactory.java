/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author marco
 */
public class ConnectionFactory {
    private Connection connection;
    
    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/javafxmvc", "root","root");
            return this.connection;
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("ERRO: "+ex.getMessage());
            return null;
        }
    }
}
