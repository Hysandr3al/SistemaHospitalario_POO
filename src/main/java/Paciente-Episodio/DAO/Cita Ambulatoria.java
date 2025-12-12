package dao;

import Conexion.ConexionDB;
import hospital.pacienteepisodio.CitaAmbulatoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaAmbulatoriaDAO implements InterfazCRUD<CitaAmbulatoria> {

    private CitaAmbulatoria mapear(ResultSet rs) throws SQLException {
        return new CitaAmbulatoria(
                rs.getString("descripcion")
        );
    }

    @Override
    public boolean registrar(CitaAmbulatoria c) {
        String sql = "INSERT INTO CitaAmbulatoria(descripcion, fecha) VALUES(?, ?)";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getDescripcion());
            ps.setDate(2, Date.valueOf(c.getFecha()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error registrando Cita Ambulatoria: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(CitaAmbulatoria c) {
        String sql = "UPDATE CitaAmbulatoria SET descripcion=?, fecha=? WHERE idEpisodio=?";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getDescripcion());
            ps.setDate(2, Date.valueOf(c.getFecha()));
            ps.setInt(3, c.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error modificando Cita Ambulatoria: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM CitaAmbulatoria WHERE idEpisodio=?";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e){
            System.out.println("Error eliminando Cita Ambulatoria: " + e.getMessage());
            return false;
        }
    }

    @Override
    public CitaAmbulatoria buscarPorId(int id) {
        String sql = "SELECT * FROM CitaAmbulatoria WHERE idEpisodio=?";
        CitaAmbulatoria c = null;

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                c = mapear(rs);
            }

        } catch (SQLException e){
            System.out.println("Error buscando Cita Ambulatoria: " + e.getMessage());
        }

        return c;
    }

    @Override
    public List<CitaAmbulatoria> listar() {
        String sql = "SELECT * FROM CitaAmbulatoria";
        List<CitaAmbulatoria> lista = new ArrayList<>();

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                lista.add(mapear(rs));
            }

        } catch (SQLException e){
            System.out.println("Error listando Cita Ambulatoria: " + e.getMessage());
        }

        return lista;
    }
}