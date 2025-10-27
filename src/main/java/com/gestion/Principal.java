package com.gestion;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        GestorBolechas gestor = new GestorBolechas();
        Scanner sc = new Scanner(System.in);

        System.out.println("Quieres borrar la base de datos si existe?(s/n)");
        String borrar = sc.nextLine();
        if (borrar != null && borrar.equalsIgnoreCase("s")) {
            gestor.crearBaseDatos("bolechasAnxoB", true);
        } else {
            gestor.crearBaseDatos("bolechasAnxoB", false);
        }
        

        GestorCliente gestorCliente = new GestorCliente("bolechasAnxoB");
        GestorProducto gestorProducto = new GestorProducto("bolechasAnxoB");
        GestorPedido gestorPedido = new GestorPedido("bolechasAnxoB");
        gestorCliente.crearTabla();
        gestorPedido.crearTabla();
        gestorProducto.crearTabla();


    }
}
