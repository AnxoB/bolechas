package com.conexion;

import java.sql.Connection;

//Interfaz que unifica como obtener las conexiones
public interface DBConnection {
    Connection getConnection();
} 
