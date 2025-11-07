package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.gestion.ConfigLoader;

public class MariaDBConnection implements DBConnection {
    /*
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "usuario";
    private static final String PASSWORD = "usuario123";
    */
    
    @Override
    public Connection getConnection() {
        //Con archivo application.properties
        String URL = ConfigLoader.get("mariadb.url");
        String USER = ConfigLoader.get("mariadb.user");
        String PASSWORD = ConfigLoader.get("mariadb.password");
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a MariaDB: " + e.getMessage());
            return null;
        }
    }
}

