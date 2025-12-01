package hospital.dao;

import hospital.pacienteepisodio.Urgencia;
import java.sql.*;

public class UrgenciaDAO extends EpisodioDAO {

    public boolean registrar(Urgencia u, int idPaciente) {

        int idEpisodio = registrarEpisodioBase(u, idPaciente, "Urgencia");
        if (idEpisodio == -1) return false;

        String sql = "INSERT INTO Urgencia(idEpisodio, triage) VALUES (?,?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEpisodio);
            ps.setString(2, u.getTriage());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error registrar urgencia: " + e.getMessage());
            return false;
        }
    }
}