package dao;

import Conexion.ConexionDB;
import recursoslogistica.Ambulancia;
import recursoslogistica.EstadoRecurso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AmbulanciaDAO implements InterfazCRUD<Ambulancia> {

    @Override
    public boolean registrar(Ambulancia a) {
        String sql = "INSERT INTO Ambulancia (codigo, placa, tipo, estado, ubicacionActual) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, a.getCodigo());
            ps.setString(2, a.getPlaca());
            
            ps.setString(3, a.getTipoAmbulancia()); 
            ps.setString(4, a.getEstado().toString());
            
            ps.setString(5, a.getUbicacionActual()); 

            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getInt(1)); 
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error registrando ambulancia: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Ambulancia> listar() {
        List<Ambulancia> lista = new ArrayList<>();
        String sql = "SELECT * FROM Ambulancia";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Ambulancia a = new Ambulancia(
                    rs.getString("codigo"),
                    rs.getString("placa"),
                    rs.getString("tipo")
                );
                
                a.setId(rs.getInt("idAmbulancia"));
                a.setEstado(EstadoRecurso.valueOf(rs.getString("estado")));
                a.setUbicacionActual(rs.getString("ubicacionActual")); 
                
                lista.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Error listando ambulancias: " + e.getMessage());
        }
        return lista;
    }
    
    
    @Override 
    public boolean modificar(Ambulancia a) { 
        String sql = "UPDATE Ambulancia SET estado=?, ubicacionActual=? WHERE placa=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, a.getEstado().toString());
            ps.setString(2, a.getUbicacionActual());
            ps.setString(3, a.getPlaca());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error actualizando ambulancia: " + e.getMessage());
            return false;
        }
    }

    
    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Ambulancia WHERE idAmbulancia = ?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al eliminar ambulancia: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Ambulancia buscarPorId(int id) {
        String sql = "SELECT * FROM Ambulancia WHERE idAmbulancia = ?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                 
                    Ambulancia a = new Ambulancia(
                        rs.getString("codigo"),
                        rs.getString("placa"),
                        rs.getString("tipo")
                    );
                    a.setId(rs.getInt("idAmbulancia"));
                    a.setEstado(EstadoRecurso.valueOf(rs.getString("estado")));
                    a.setUbicacionActual(rs.getString("ubicacionActual"));
                    
                    return a;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar ambulancia por ID: " + e.getMessage());
        }
        return null;
    }
}