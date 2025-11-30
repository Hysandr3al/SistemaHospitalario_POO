package dao;

import Conexion.ConexionDB;
import Sujetos.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO implements InterfazCRUD<Doctor>{

    private Doctor mapearDoctor(ResultSet rs) throws SQLException {
        Doctor d = new Doctor();
        d.setIdDoctor(rs.getInt("idDoctor"));
        d.setNombres(rs.getString("nombres"));
        d.setApellidos(rs.getString("apellidos"));
        d.setDni(rs.getString("dni"));
        d.setTelefono(rs.getString("telefono"));
        d.setEspecialidad(rs.getString("especialidad"));
        d.setSexo(rs.getString("sexo"));
        d.setEdad(rs.getInt("edad"));
        d.setHorario(rs.getString("horario"));
        
        return d;
    }

    @Override
    public boolean registrar(Doctor d){
        String sql = "INSERT INTO Paciente(idDoctor, especialidad, horario, nombres, apellidos, dni, telefono, sexo, edad)" + 
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, d.getIdDoctor());
            ps.setString(2, d.getEspecialidad());
            ps.setString(3, d.getHorario());
            ps.setString(4, d.getNombres());
            ps.setString(5, d.getApellidos());
            ps.setString(6, d.getDni());
            ps.setString(7, d.getTelefono());
            ps.setString(8, d.getSexo());
            ps.setInt(9, d.getEdad());

            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println("Error registrando doctor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(Doctor d){
        String sql = "UPDATE Paciente SET especialidad=?, horario=?, nombres=?, apellidos=?, dni=?, telefono=?, sexo=?, edad=?" + "WHERE idDoctor=?";
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, d.getEspecialidad());
            ps.setString(2, d.getHorario());
            ps.setString(3, d.getNombres());
            ps.setString(4, d.getApellidos());
            ps.setString(5, d.getDni());
            ps.setString(6, d.getTelefono());
            ps.setString(7, d.getSexo());
            ps.setInt(8, d.getEdad());
            ps.setInt(9, d.getIdDoctor());

            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println("Error modificando doctor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id){
        String sql = "DELETE FROM Doctor WHERE idDoctor=?";
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println("Error eliminando doctor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Doctor buscarPorId(int id){
        String sql = "SELECT * FROM Paciente WHERE idDoctor=?";
        Doctor d = null;
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                d = mapearDoctor(rs);
            }
        } catch (SQLException e){
            System.out.println("Error buscando doctor: " + e.getMessage());
        } 
        return d;
    }

    @Override
    public List<Doctor> listar(){
        List<Doctor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Doctor";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    lista.add(mapearDoctor(rs));
                }
        } catch (SQLException e){
            System.out.println("Error al listar doctores: " + e.getMessage());
        }

        return lista;
    }
}