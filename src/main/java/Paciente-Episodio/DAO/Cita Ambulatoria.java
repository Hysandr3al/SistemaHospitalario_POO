package hospital.dao;

import hospital.pacienteepisodio.CitaAmbulatoria;
import java.sql.*;

public class CitaAmbulatoriaDAO extends EpisodioDAO {

    public boolean registrar(CitaAmbulatoria c, int idPaciente) {

        int idEpisodio = registrarEpisodioBase(c, idPaciente, "CitaAmbulatoria");
        if (idEpisodio == -1) return false;

        String sql = "INSERT INTO CitaAmbulatoria(idEpisodio) VALUES (?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEpisodio);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error registrar cita ambulatoria: " + e.getMessage());
            return false;
        }
    }
}