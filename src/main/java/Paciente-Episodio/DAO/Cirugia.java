package hospital.dao;

import hospital.pacienteepisodio.Cirugia;
import java.sql.*;

public class CirugiaDAO extends EpisodioDAO {

    public boolean registrar(Cirugia c, int idPaciente) {

        int idEpisodio = registrarEpisodioBase(c, idPaciente, "Cirugia");
        if (idEpisodio == -1) return false;

        String sql = "INSERT INTO Cirugia(idEpisodio, tipo) VALUES (?,?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEpisodio);
            ps.setString(2, c.getTipo());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error registrar cirugía: " + e.getMessage());
            return false;
        }
    }
}