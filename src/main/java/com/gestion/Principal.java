package com.gestion;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.conexion.MySQLConnection;

public class Principal {
    public static void main(String[] args) {
        GestorBolechas gestor = new GestorBolechas();
        Scanner sc = new Scanner(System.in);

        System.out.println("Quieres borrar la base de datos si existe?(s/n)");
        String borrar = sc.nextLine();
        String nombreBD = "bolechasAnxoB";
        if (borrar != null && borrar.equalsIgnoreCase("s")) {
            gestor.crearBaseDatos(nombreBD, true);
        } else {
            gestor.crearBaseDatos(nombreBD, false);
        }
        
        MySQLConnection mySQLConnection = new MySQLConnection();
        try (Connection conn = mySQLConnection.getConnection();
            Statement statement = conn.createStatement()){
            statement.execute("USE" + nombreBD);

            GestorCliente gestorCliente = new GestorCliente(conn);
            GestorProducto gestorProducto = new GestorProducto(conn);
            GestorPedido gestorPedido = new GestorPedido(conn);
            gestorCliente.crearTabla();
            gestorPedido.crearTabla();
            gestorProducto.crearTabla();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        


    }
}
