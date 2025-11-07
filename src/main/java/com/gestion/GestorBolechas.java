package com.gestion;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.conexion.DBConnection;
import com.conexion.MySQLConnection;
import com.conexion.MariaDBConnection;
import com.mysql.cj.MysqlConnection;

public class GestorBolechas {
    DBConnection conexion;
    public GestorBolechas() {
        //this.conexion = new MySQLConnection();
        this.conexion = new MariaDBConnection();
    }
    public void crearBaseDatos(String nombre, boolean borrar){
        try (Connection conn = conexion.getConnection();
            Statement statement = conn.createStatement()){
            if (borrar==true) {
                statement.executeUpdate("DROP DATABASE IF EXISTS " + nombre);
                System.out.println("Base de datos borrada");
            }
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + nombre);
            System.out.println("Base de datos creada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
