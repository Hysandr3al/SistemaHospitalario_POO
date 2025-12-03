package dao;

import Conexion.ConexionDB;
import hospital.pacienteepisodio.Cirugia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CirugiaDAO implements InterfazCRUD<Cirugia> {

    private Cirugia mapear(ResultSet rs) throws SQLException {
        return new Cirugia(
                rs.getString("descripcion"),
                rs.getString("tipo")
        );
    }

    @Override
    public boolean registrar(Cirugia c) {
        String sql = "INSERT INTO Cirugia(descripcion, fecha, tipo) VALUES(?, ?, ?)";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getDescripcion());
            ps.setDate(2, Date.valueOf(c.getFecha()));
            ps.setString(3, c.getTipo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error registrando cirugía: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(Cirugia c) {
        String sql = "UPDATE Cirugia SET descripcion=?, fecha=?, tipo=? WHERE idEpisodio=?";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getDescripcion());
            ps.setDate(2, Date.valueOf(c.getFecha()));
            ps.setString(3, c.getTipo());
            ps.setInt(4, c.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e){
            System.out.println("Error modificando cirugía: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Cirugia WHERE idEpisodio=?";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e){
            System.out.println("Error eliminando cirugía: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Cirugia buscarPorId(int id) {
        String sql = "SELECT * FROM Cirugia WHERE idEpisodio=?";
        Cirugia c = null;

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                c = mapear(rs);
            }

        } catch (SQLException e){
            System.out.println("Error buscando cirugía: " + e.getMessage());
        }

        return c;
    }

    @Override
    public List<Cirugia> listar() {
        List<Cirugia> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cirugia";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                lista.add(mapear(rs));
            }

        } catch (SQLException e){
            System.out.println("Error listando cirugías: " + e.getMessage());
        }

        return lista;
    }
}