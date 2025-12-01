package hospital.dao;

import hospital.pacienteepisodio.Hospitalizacion;
import java.sql.*;

public class HospitalizacionDAO extends EpisodioDAO {

    public boolean registrar(Hospitalizacion h, int idPaciente) {

        int idEpisodio = registrarEpisodioBase(h, idPaciente, "Hospitalizacion");
        if (idEpisodio == -1) return false;

        String sql = "INSERT INTO Hospitalizacion(idEpisodio, cama) VALUES (?,?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEpisodio);
            ps.setInt(2, h.getCama());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error registrar hospitalización: " + e.getMessage());
            return false;
        }
    }
}
