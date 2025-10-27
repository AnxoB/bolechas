package com.gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorPedido {
    String nombreBD;
    private final String URL;
    private final String USER = "root";
    private final String PASSWORD = "root";
    GestorPedido(String nombre){
        this.nombreBD=nombre;
        this.URL = "jdbc:mysql://localhost:3306/" + nombreBD;
    }

    public void crearTabla(){
        String tabla = """
                    CREATE TABLE IF NOT EXISTS Pedido (
                        id_pedido smallint primary key,
                        fecha date,
                        dni_cliente char(9),
                        FOREIGN KEY (dni_cliente) references Cliente(dni) ON UPDATE CASCADE
                    )
                """;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = conn.createStatement()){
            statement.execute(tabla);
            System.out.println("Tabla Pedido creada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
