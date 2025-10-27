package com.gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorProducto {
    String nombreBD;
    private final String URL;
    private final String USER = "root";
    private final String PASSWORD = "root";
    GestorProducto(String nombre){
        this.nombreBD=nombre;
        this.URL = "jdbc:mysql://localhost:3306/" + nombreBD;
    }

    public void crearTabla(){
        String tabla = """
                    CREATE TABLE IF NOT EXISTS Producto (
                        id_producto smallint primary key,
                        nombre varchar(50),
                        descripcion varchar(100),
                        precio int,
                        id_pedido smallint,
                        FOREIGN KEY (id_pedido) references Pedido(id_pedido) ON UPDATE CASCADE
                    )
                """;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = conn.createStatement()){
            statement.execute(tabla);
            System.out.println("Tabla Producto creada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
