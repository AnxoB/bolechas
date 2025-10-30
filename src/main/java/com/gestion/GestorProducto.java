package com.gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
                        cantidad int,
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

    public void insertarProducto(int idProducto, String nombre, String descripcion, int precio, int idPedido){
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO PRODUCTO VALUES (?, ?, ?, ?, ?)");
            pst.setInt(1, idPedido);
            pst.setString(2, nombre);
            pst.setString(3, descripcion);
            pst.setInt(4, precio);
            pst.setInt(5, idPedido);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
