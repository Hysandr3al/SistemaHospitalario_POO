package dao;

import Conexion.ConexionDB;
import Farmacia.Medicamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO implements InterfazCRUD<Medicamento>{

    private Medicamento mapearMedicamento(ResultSet rs) throws SQLException {
        Medicamento m = new Medicamento();
        m.setIdMedicamento(rs.getInt("idMedicamento"));
        m.setNombre(rs.getString("nombre"));
        m.setTipo(rs.getString("tipo"));
        m.setPrecio(rs.getDouble("precio"));
        m.setStock(rs.getInt("stock"));
        m.setFechaVencimiento(rs.getString("fecha_vencimiento"));
        
        return m;
    }

    @Override
    public boolean registrar(Medicamento m){
        String sql = "INSERT INTO Medicamento(nombre, tipo, precio, stock, fecha_vencimiento)" + 
                        "VALUES (?, ?, ?, ?, ?)";
        
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getTipo());
            ps.setDouble(3, m.getPrecio());
            ps.setInt(4, m.getStock());
            ps.setString(5, m.getFechaVencimiento());

            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println("Error registrando medicamento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(Medicamento m){
        String sql = "UPDATE Medicamento SET nombre=?, tipo=?, precio=?, stock=?, fecha_vencimiento=?" + "WHERE idMedicamento=?";
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getTipo());
            ps.setDouble(3, m.getPrecio());
            ps.setInt(4, m.getStock());
            ps.setString(5, m.getFechaVencimiento());
            ps.setInt(6, m.getIdMedicamento());

            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println("Error modificando medicamento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id){
        String sql = "DELETE FROM Medicamento WHERE idMedicamento=?";
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println("Error eliminando medicamento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Medicamento buscarPorId(int id){
        String sql = "SELECT * FROM Medicamento WHERE idMedicamento=?";
        Medicamento m = null;
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                m = mapearMedicamento(rs);
            }
        } catch (SQLException e){
            System.out.println("Error buscando medicamento: " + e.getMessage());
        } 
        return m;
    }

    @Override
    public List<Medicamento> listar(){
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Medicamento";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    lista.add(mapearMedicamento(rs));
                }
        } catch (SQLException e){
            System.out.println("Error al listar medicamentos: " + e.getMessage());
        }

        return lista;
    }
}
