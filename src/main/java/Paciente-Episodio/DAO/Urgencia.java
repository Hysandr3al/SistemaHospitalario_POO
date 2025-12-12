package dao;

import Conexion.ConexionDB;
import hospital.pacienteepisodio.Urgencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UrgenciaDAO implements InterfazCRUD<Urgencia> {

    private Urgencia mapear(ResultSet rs) throws SQLException {
        return new Urgencia(
                rs.getString("descripcion"),
                rs.getString("triage")
        );
    }

    @Override
    public boolean registrar(Urgencia u) {
        String sql = "INSERT INTO Urgencia(descripcion, fecha, triage) VALUES(?, ?, ?)";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getDescripcion());
            ps.setDate(2, Date.valueOf(u.getFecha()));
            ps.setString(3, u.getTriage());

            return ps.executeUpdate() > 0;

        } catch (SQLException e){
            System.out.println("Error registrando urgencia: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(Urgencia u) {
        String sql = "UPDATE Urgencia SET descripcion=?, fecha=?, triage=? WHERE idEpisodio=?";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getDescripcion());
            ps.setDate(2, Date.valueOf(u.getFecha()));
            ps.setString(3, u.getTriage());
            ps.setInt(4, u.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e){
            System.out.println("Error modificando urgencia: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Urgencia WHERE idEpisodio=?";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e){
            System.out.println("Error eliminando urgencia: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Urgencia buscarPorId(int id) {
        String sql = "SELECT * FROM Urgencia WHERE idEpisodio=?";
        Urgencia u = null;

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                u = mapear(rs);
            }

        } catch (SQLException e){
            System.out.println("Error buscando urgencia: " + e.getMessage());
        }

        return u;
    }

    @Override
    public List<Urgencia> listar() {
        List<Urgencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM Urgencia";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                lista.add(mapear(rs));
            }

        } catch (SQLException e){
            System.out.println("Error listando urgencias: " + e.getMessage());
        }

        return lista;
    }
}