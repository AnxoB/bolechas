package com.gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.conexion.DBConnection;
import com.conexion.MySQLConnection;

public class GestorCliente {
    Connection conn;
    GestorCliente(Connection conn){
        this.conn=conn;
    }

    public void crearTabla(){
        String tabla = """
                    CREATE TABLE IF NOT EXISTS Cliente (
                        dni char(9) primary key,
                        nombre varchar(50)
                    )
                """;
        try (Statement statement = conn.createStatement()){
            statement.execute(tabla);
            System.out.println("Tabla Cliente creada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarCliente(String dni, String nombre){
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO Cliente VALUES (?, ?)");
            pst.setString(1, dni);
            pst.setString(2, nombre);
            pst.executeUpdate();
            System.out.println("Cliente insertado");
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public void mostrarinformacionCliente(String dni){
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM Cliente WHERE dni = ?");
            pst.setString(1, dni);
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
