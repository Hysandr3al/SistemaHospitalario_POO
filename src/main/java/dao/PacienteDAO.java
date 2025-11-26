package dao;

import Conexion.ConexionDB;
import Sujetos.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO implements InterfazCRUD{
    @Override
    public boolean registrar(Paciente p){
        String sql = "INSERT INTO Paciente(gruposanguineo, alergias, idPaciente, nombre, apellido, dni, telefono, direccion, sexo, edad)" + 
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, p.getGrupoSanguineo());
            ps.setString(2, p.getAlergias());
            ps.setInt(3, p.getIdPaciente());
            ps.setString(4, p.getNombres());
            ps.setString(5, p.getApellidos());
            ps.setString(6, p.getDni());
            ps.setString(7, p.getTelefono());
            ps.setString(8, p.getDireccion());
            ps.setString(9, p.getSexo());
            ps.setInt(10, p.getEdad());

            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println("Error registrando paciente: " + e.getMessage());
            return false;
        }
    }
}
