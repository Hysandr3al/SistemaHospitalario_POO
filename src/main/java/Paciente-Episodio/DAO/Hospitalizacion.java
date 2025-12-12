package dao;

import Conexion.ConexionDB;
import hospital.pacienteepisodio.Hospitalizacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalizacionDAO implements InterfazCRUD<Hospitalizacion> {

    private Hospitalizacion mapear(ResultSet rs) throws SQLException {
        return new Hospitalizacion(
                rs.getString("descripcion"),
                rs.getInt("cama")
        );
    }

    @Override
    public boolean registrar(Hospitalizacion h) {
        String sql = "INSERT INTO Hospitalizacion(descripcion, fecha, cama) VALUES(?, ?, ?)";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, h.getDescripcion());
            ps.setDate(2, Date.valueOf(h.getFecha()));
            ps.setInt(3, h.getCama());

            return ps.executeUpdate() > 0;

        } catch (SQLException e){
            System.out.println("Error registrando hospitalización: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(Hospitalizacion h) {
        String sql = "UPDATE Hospitalizacion SET descripcion=?, fecha=?, cama=? WHERE idEpisodio=?";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, h.getDescripcion());
            ps.setDate(2, Date.valueOf(h.getFecha()));
            ps.setInt(3, h.getCama());
            ps.setInt(4, h.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e){
            System.out.println("Error modificando hospitalización: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Hospitalizacion WHERE idEpisodio=?";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e){
            System.out.println("Error eliminando hospitalización: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Hospitalizacion buscarPorId(int id) {
        String sql = "SELECT * FROM Hospitalizacion WHERE idEpisodio=?";
        Hospitalizacion h = null;

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                h = mapear(rs);
            }

        } catch (SQLException e){
            System.out.println("Error buscando hospitalización: " + e.getMessage());
        }

        return h;
    }

    @Override
    public List<Hospitalizacion> listar() {
        List<Hospitalizacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Hospitalizacion";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                lista.add(mapear(rs));
            }

        } catch (SQLException e){
            System.out.println("Error listando hospitalizaciones: " + e.getMessage());
        }

        return lista;
    }
}