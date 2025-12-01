package hospital.dao;

import hospital.pacienteepisodio.Episodio;
import java.sql.*;

public class EpisodioDAO {

    public int registrarEpisodioBase(Episodio e, int idPaciente, String tipo) {
        String sql = "INSERT INTO Episodio(idPaciente, descripcion, fecha, tipo) VALUES (?,?,GETDATE(),?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, idPaciente);
            ps.setString(2, e.getDescripcion());
            ps.setString(3, tipo);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // idEpisodio generado
            }

        } catch (Exception ex) {
            System.out.println("Error registrar episodio base: " + ex.getMessage());
        }

        return -1;
    }
}