package dao;

import Conexion.ConexionDB;
import Receta.RecetaMedicaHospital;
import Sujetos.Doctor;
import Sujetos.Paciente;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;



public class RecetaMedicaHospitalDAO {

    public RecetaMedicaHospital mapear(ResultSet rs) throws SQLException {
        RecetaMedicaHospital r = new RecetaMedicaHospital();
        r.setIdRecetaHospital(rs.getInt("idRecetaHospital"));
        
        Doctor d = new Doctor();
        d.setIdDoctor(rs.getInt("idDoctor"));
        r.setDoctor(d);

        Paciente p = new Paciente();
        p.setIdPaciente(rs.getInt("idPaciente"));
        r.setPaciente(p);

        return r;
    }

    public int registrar(RecetaMedicaHospital r){
        String sql = "INSERT INTO RecetaMedicaHospital (idDoctor, idPaciente) VALUES (?, ?)";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            
                ps.setInt(1, r.getDoctor().getIdDoctor());
                ps.setInt(2, r.getPaciente().getIdPaciente());

                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    r.setIdRecetaHospital(idGenerado);;
                    return idGenerado;
                }

        } catch (SQLException e) {
            System.out.println("Error al registrar receta hospitalaria: " + e.getMessage());
        }
        return -1; //Esto significa un error
    }

    public RecetaMedicaHospital buscarPorId(int id){
        String sql = "SELECT * FROM RecetaMedicaHospital WHERE idRecetaHospital = ?";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return mapear(rs);
            } 
        } catch (SQLException e){
            System.out.println("Error de busqueda: " + e.getMessage());
        }
        return null;
    }

    public List<RecetaMedicaHospital> listar(){
        List<RecetaMedicaHospital> lista = new ArrayList<>();
        String sql = "SELECT * FROM RecetaMedicaHospital";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
        }catch(SQLException e){
            System.out.println("Error al listar recetas medicas: " + e.getMessage());
        }
        return lista;
    }

    public boolean eliminar(int id){
        String sql = "DELETE FROM RecetaMedicaHospital WHERE idRecetaHospital = ?";
        
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar receta hospitalaria: " + e.getMessage());
        }

        return false;
    }
}
