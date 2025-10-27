package com.gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.conexion.DBConnection;
import com.conexion.MySQLConnection;

public class GestorCliente {
    String nombreBD;
    private final String URL;
    private final String USER = "root";
    private final String PASSWORD = "root";
    GestorCliente(String nombre){
        this.nombreBD=nombre;
        this.URL = "jdbc:mysql://localhost:3306/" + nombreBD;
    }

    public void crearTabla(){
        String tabla = """
                    CREATE TABLE IF NOT EXISTS Cliente (
                        dni char(9) primary key,
                        nombre varchar(50)
                    )
                """;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = conn.createStatement()){
            statement.execute(tabla);
            System.out.println("Tabla Cliente creada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
