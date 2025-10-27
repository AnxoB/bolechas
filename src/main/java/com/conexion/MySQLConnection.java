package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.gestion.ConfigLoader;

public class MySQLConnection implements DBConnection {
    /*
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "usuario";
    private static final String PASSWORD = "usuario123";
    */
    
    @Override
    public Connection getConnection() {
        //Con archivo application.properties
        String URL = ConfigLoader.get("mysql.url");
        String USER = ConfigLoader.get("mysql.user");
        String PASSWORD = ConfigLoader.get("mysql.password");
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a Mysql: " + e.getMessage());
            return null;
        }
    }
}
