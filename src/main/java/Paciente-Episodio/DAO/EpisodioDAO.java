package dao;

import Conexion.ConexionDB;
import hospital.pacienteepisodio.Episodio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EpisodioDAO implements InterfazCRUD<Episodio> {

    private Episodio mapear(ResultSet rs) throws SQLException {
        return new Episodio(
                rs.getString("descripcion")
        );
    }

    @Override
    public boolean registrar(Episodio e) {
        String sql = "INSERT INTO Episodio(descripcion, fecha) VALUES(?, ?)";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getDescripcion());
            ps.setDate(2, Date.valueOf(e.getFecha()));

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error registrando episodio: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(Episodio e) {
        String sql = "UPDATE Episodio SET descripcion=?, fecha=? WHERE idEpisodio=?";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getDescripcion());
            ps.setDate(2, Date.valueOf(e.getFecha()));
            ps.setInt(3, e.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error modificando episodio: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Episodio WHERE idEpisodio=?";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error eliminando episodio: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public Episodio buscarPorId(int id) {
        String sql = "SELECT * FROM Episodio WHERE idEpisodio=?";
        Episodio ep = null;

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ep = mapear(rs);
            }

        } catch (SQLException ex) {
            System.out.println("Error buscando episodio: " + ex.getMessage());
        }

        return ep;
    }

    @Override
    public List<Episodio> listar() {
        List<Episodio> lista = new ArrayList<>();
        String sql = "SELECT * FROM Episodio";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }

        } catch (SQLException ex) {
            System.out.println("Error listando episodios: " + ex.getMessage());
        }

        return lista;
    }
}