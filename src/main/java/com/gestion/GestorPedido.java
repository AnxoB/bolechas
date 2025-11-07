package com.gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorPedido {
    Connection conn;
    //Constructor con conexion
    GestorPedido(Connection conn){
        this.conn=conn;
    }

    /**
     * Creamos la tabla Pedido
     * Definimos un string con la consulta
     * Ejecutamos la consulta con ese string con un statement
     */
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

    /**
     * Insertamos un pedido
     * Pasamos los siguientes parametros
     * @param idPedido
     * @param fecha
     * @param dni_cliente
     * Realizamos la insercion con un preparedStatement
     */
    public void insertarPedido(int idPedido, String fecha, String dni_cliente){
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO Pedido VALUES (?, ?)");
            pst.setInt(1, idPedido);
            pst.setString(2, fecha);
            pst.setString(3, dni_cliente);
            System.out.println("Pedido Insertado");
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mostramos el pedido de un cliente
     * Pasamos los siguientes parametros
     * @param dni
     * Realizamos la consulta con un preparedStatement
     */
    public void mostrarPedido(String dni){
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT pe.fecha, pr.descripcion, pr.precio from Pedido pe inner join Producto pr on pe.id_pedido=pr.id_pedido where pe.dni_cliente=?");
            pst.setString(1, dni);
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
