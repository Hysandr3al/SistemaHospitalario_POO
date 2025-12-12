package dao;

import Conexion.ConexionDB;
import recursoslogistica.CategoriaInsumo;
import recursoslogistica.EstadoRecurso;
import recursoslogistica.Insumo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsumoDAO implements InterfazCRUD<Insumo> {

    @Override
    public boolean registrar(Insumo i) {
        String sql = "INSERT INTO Insumo (codigo, nombre, categoria, cantidad, stockMinimo, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, i.getCodigo());
            ps.setString(2, i.getNombre());
            ps.setString(3, i.getCategoria().toString());
            ps.setInt(4, i.getCantidad());
            ps.setInt(5, i.getStockMinimo());
            ps.setString(6, i.getEstado().toString());

            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                i.setId(rs.getInt(1));
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error registrando insumo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Insumo> listar() {
        List<Insumo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Insumo";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String catStr = rs.getString("categoria");
                int cant = rs.getInt("cantidad");
                int stockMin = rs.getInt("stockMinimo");
                String estStr = rs.getString("estado");

                
                CategoriaInsumo cat = CategoriaInsumo.valueOf(catStr);
                
                Insumo i = new Insumo(codigo, nombre, cat, cant, stockMin);
                i.setId(rs.getInt("idInsumo"));
                i.setEstado(EstadoRecurso.valueOf(estStr));
                
                lista.add(i);
            }
        } catch (SQLException e) {
            System.out.println("Error listando insumos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean modificar(Insumo i) {
        
        String sql = "UPDATE Insumo SET cantidad=?, estado=? WHERE codigo=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, i.getCantidad());
            ps.setString(2, i.getEstado().toString());
            ps.setString(3, i.getCodigo());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error actualizando stock insumo: " + e.getMessage());
            return false;
        }
    }

    
    
    public Insumo buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM Insumo WHERE codigo = ?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Insumo i = new Insumo(
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    CategoriaInsumo.valueOf(rs.getString("categoria")),
                    rs.getInt("cantidad"),
                    rs.getInt("stockMinimo")
                );
                i.setId(rs.getInt("idInsumo"));
                i.setEstado(EstadoRecurso.valueOf(rs.getString("estado")));
                return i;
            }
        } catch (SQLException e) {
            System.out.println("Error buscando insumo: " + e.getMessage());
        }
        return null;
    }
    
    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Insumo WHERE idInsumo = ?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al eliminar insumo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Insumo buscarPorId(int id) {
        String sql = "SELECT * FROM Insumo WHERE idInsumo = ?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    
                    CategoriaInsumo cat = CategoriaInsumo.valueOf(rs.getString("categoria"));
                    EstadoRecurso est = EstadoRecurso.valueOf(rs.getString("estado"));

                    Insumo i = new Insumo(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        cat,
                        rs.getInt("cantidad"),
                        rs.getInt("stockMinimo")
                    );
                    i.setId(rs.getInt("idInsumo"));
                    i.setEstado(est);
                    
                    return i;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar insumo por ID: " + e.getMessage());
        }
        return null;
    }
    
}