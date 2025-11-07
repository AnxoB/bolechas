package com.gestion;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.conexion.MariaDBConnection;
import com.conexion.MySQLConnection;

public class Principal {
    public static void main(String[] args) {
        GestorBolechas gestor = new GestorBolechas();
        Scanner sc = new Scanner(System.in);

        System.out.println("Quieres borrar la base de datos si existe?(s/n)");
        String borrar = sc.nextLine();
        String nombreBD = "mariaBolechas";
        if (borrar != null && borrar.equalsIgnoreCase("s")) {
            gestor.crearBaseDatos(nombreBD, true);
        } else {
            gestor.crearBaseDatos(nombreBD, false);
        }
        
        //MySQLConnection mySQLConnection = new MySQLConnection();
        MariaDBConnection mariaDBConnection = new MariaDBConnection();
        try (Connection conn = mariaDBConnection.getConnection();
            Statement statement = conn.createStatement()){
            statement.execute("USE " + nombreBD);

            GestorCliente gestorCliente = new GestorCliente(conn);
            GestorProducto gestorProducto = new GestorProducto(conn);
            GestorPedido gestorPedido = new GestorPedido(conn);

            gestorCliente.crearTabla();
            gestorPedido.crearTabla();
            gestorProducto.crearTabla();

            int opcion = 0;
            while (opcion!=5) {
                System.out.println("1. Insertar Cliente");
                System.out.println("2. Insertar Pedido");
                System.out.println("3. Insertar Producto");
                System.out.println("4. Mostrar Pedido de un Cliente");
                System.out.println("5. Salir");
                System.out.print("Elige una opcion: ");
                opcion=sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        gestorCliente.insertarCliente("12345678A", "Anxo");
                        break;
                    case 2:
                        gestorPedido.insertarPedido(1, "2024-06-10", "12345678A");
                        break;
                    case 3:
                        gestorProducto.insertarProducto(1, "Producto1", "Descripcion1", 100, 1);
                        break;
                    case 4:
                        gestorPedido.mostrarPedido("12345678A");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
