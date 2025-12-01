package dao;

import Conexion.ConexionDB;
import Farmacia.Medicamento;
import Receta.DetalleMedicamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleMedicamentoDAO implements InterfazCRUD<DetalleMedicamento>{

    public DetalleMedicamento mapear(ResultSet rs)throws SQLException{
        DetalleMedicamento d = new DetalleMedicamento();

        d.setIdDetalle(rs.getInt("idDetalle"));
        d.setCantidad(rs.getInt("cantidad"));
        d.setTipoReceta(rs.getString("tipoReceta").charAt(0));
        d.setIdReceta(rs.getInt("idReceta"));

        Medicamento m = new Medicamento();
        m.setIdMedicamento(rs.getInt("idMedicamento"));
        m.setNombre(rs.getString("nombre"));
        m.setPrecio(rs.getDouble("precio"));
        d.setMedicamento(m);
        return d;
    }

    @Override
    public boolean registrar(DetalleMedicamento d){
        char tipo = Character.toUpperCase(d.getTipoReceta());

        if (tipo != 'H' && tipo !='E'){
            System.out.println("Error: tipoReceta debe ser 'H' o 'E'");
            return false; 
        }

        String sql = "INSERT INTO DetalleMedicamento (idReceta, tipoReceta, idMedicamento, cantidad) " +
                     "VALUES (?, ?, ?, ?)";
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, d.getIdReceta());
            ps.setString(2, String.valueOf(tipo));
            ps.setInt(3, d.getMedicamento().getIdMedicamento());
            ps.setInt(4, d.getCantidad());

           return ps.executeUpdate() > 0;
        } catch(SQLException e){
            System.out.println("Error al registrar detalle: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean modificar(DetalleMedicamento d){
        String sql = "UPDATE DetalleMedicamento SET cantidad = ? "
                   + "WHERE idDetalle = ?";
         try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, d.getCantidad());
            ps.setInt(2, d.getIdDetalle());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al modificar detalle: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminar(int id){
        String sql = "DELETE FROM DetalleMedicamento WHERE idDetalle=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar detalle: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<DetalleMedicamento> listar() {
        List<DetalleMedicamento> lista = new ArrayList<>();

        String sql = "SELECT d.*, m.nombre AS nombreMedicamento, m.precio AS precioMedicamento " +
                 "FROM DetalleMedicamento d " +
                 "INNER JOIN Medicamento m ON d.idMedicamento = m.idMedicamento";

        try (Connection con = ConexionDB.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            lista.add(mapear(rs));
        }

        } catch (SQLException e) {
        System.out.println("Error al listar: " + e.getMessage());
    }

    return lista;
}

    @Override
    public DetalleMedicamento buscarPorId(int id){
        String sql = "SELECT d.*, " +
                     "       m.nombre AS nombreMedicamento, " +
                     "       m.precio AS precioMedicamento " +
                     "FROM DetalleMedicamento d " +
                     "INNER JOIN Medicamento m ON d.idMedicamento = m.idMedicamento " +
                     "WHERE d.idDetalle = ?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapear(rs);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar detalle: " + e.getMessage());
        }
        return null;
    }

    public List<DetalleMedicamento> listar(int idReceta){
        List<DetalleMedicamento> lista = new ArrayList<>();

        String sql = "SELECT d.*, " +
                     "       m.nombre AS nombreMedicamento, " +
                     "       m.precio AS precioMedicamento " +
                     "FROM DetalleMedicamento d " +
                     "INNER JOIN Medicamento m ON d.idMedicamento = m.idMedicamento " +
                     "WHERE d.idReceta = ?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idReceta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapear(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar detalles: " + e.getMessage());
        }

        return lista;
    }


}
