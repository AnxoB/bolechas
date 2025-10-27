package com.gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorPedido {
    Connection conn;
    GestorPedido(Connection conn){
        this.conn=conn;
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
        try (Statement statement = conn.createStatement()){
            statement.execute(tabla);
            System.out.println("Tabla Pedido creada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
