package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // CAMBIO CLAVE: Usamos 127.0.0.1 en lugar de localhost
    private static final String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Hospital;encrypt=false;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "12345"; 

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa a la BD Hospital.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la BD: " + e.getMessage());
        }
        return con;
    }
}