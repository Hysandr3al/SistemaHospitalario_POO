package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    
    private static final String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Hospital;encrypt=false;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "12345"; 

    
    public static Connection getConnection() throws SQLException {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa a la BD Hospital.");
            return con;
        } catch (SQLException e) {
            
            System.err.println("ERROR FATAL DE CONEXION: " + e.getMessage());
            throw e; 
        }
    }
}