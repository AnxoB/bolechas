package com.gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorProducto {
    Connection conn;
    GestorProducto(Connection conn){
        this.conn=conn;
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
        try (Statement statement = conn.createStatement()){
            statement.execute(tabla);
            System.out.println("Tabla Producto creada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
