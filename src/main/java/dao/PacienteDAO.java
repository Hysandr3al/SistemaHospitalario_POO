package dao;

import Conexion.ConexionBD;
import Sujetos.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO implements InterfazCRUD{
    @Override
    public boolean registrar(Paciente p){
        String sql = "INSERT INTO Paciente(nombre, apellido, dni, telefono, direccion, sexo, edad, grupoSanguineo, alergias)" + 
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try(Connection con = ConnexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, p.getNombres);
        }
    }
}
