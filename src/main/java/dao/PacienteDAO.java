package dao;

import Conexion.ConexionDB;
import Sujetos.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO implements InterfazCRUD<Paciente>{

    private Paciente mapearPaciente(ResultSet rs) throws SQLException {
        Paciente p = new Paciente();
        p.setIdPaciente(rs.getInt("idPaciente"));
        p.setNombres(rs.getString("nombres"));
        p.setDni(rs.getString("dni"));
        p.setTelefono(rs.getString("telefono"));
        p.setDireccion(rs.getString("direccion"));
        p.setSexo(rs.getString("sexo"));
        p.setEdad(rs.getInt("edad"));
        p.setGrupoSanguineo(rs.getString("gruposanguineo"));
        p.setAlergias(rs.getString("alergias"));
        
        return p;
    }

    @Override
    public boolean registrar(Paciente p){
        String sql = "INSERT INTO Paciente(gruposanguineo, alergias, idPaciente, nombres, apellidos, dni, telefono, direccion, sexo, edad)" + 
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

    @Override
    public boolean modificar(Paciente p){
        String sql = "UPDATE Paciente SET gruposanguineo=?, alergias=?, nombres=?, apellidos=?, dni=?, telefono=?, direccion=?, sexo=?, edad=?" + "WHERE idPaciente=?";
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, p.getGrupoSanguineo());
            ps.setString(2, p.getAlergias());
            ps.setString(3, p.getNombres());
            ps.setString(4, p.getApellidos());
            ps.setString(5, p.getDni());
            ps.setString(6, p.getTelefono());
            ps.setString(7, p.getDireccion());
            ps.setString(8, p.getSexo());
            ps.setInt(9, p.getEdad());
            ps.setInt(10, p.getIdPaciente());

            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println("Error registrando paciente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id){
        String sql = "DELETE FROM Paciente WHERE idPaciente=?";
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println("Error eliminando paciente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Paciente buscarPorId(int id){
        String sql = "SELECT * FROM Paciente WHERE idPaciente=?";
        Paciente p = null;
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                p = mapearPaciente(rs);
            }
        } catch (SQLException e){
            System.out.println("Error buscando paciente: " + e.getMessage());
        } 
        return p;
    }

    @Override
    public List<Paciente> listar(){
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Paciente";

        try(Connection con = ConnectionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    lista.add(mapearPaciente(rs));
                }
        } catch (SQLException e){
            System.out.println("Error al listar pacientes: " + e.getMessage());
        }

        return lista;
    }
}
