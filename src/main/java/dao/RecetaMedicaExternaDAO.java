package dao;

import Conexion.ConexionDB;
import Farmacia.RecetaMedicaExterna;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecetaMedicaExternaDAO {

    public RecetaMedicaExterna mapear(ResultSet rs) throws SQLException {
        RecetaMedicaExterna re = new RecetaMedicaExterna(); 

        re.setIdRecetaExterna(rs.getInt("idRecetaMedicaExterna"));
        re.setNombreCliente(rs.getString("nombreCliente"));
        re.setDniCliente(rs.getString("dniCliente"));

        return re;
    }

    public int registrar(RecetaMedicaExterna r) {
        String sql = "INSERT INTO RecetaMedicaExterna (nombreCliente, dniCliente) VALUES (?, ?)";
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            
                ps.setString(1, r.getNombreCliente());
                ps.setString(2, r.getDniCliente());

                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    r.setIdRecetaExterna(idGenerado);
                    return idGenerado;
                }
        } catch(SQLException e){
            System.out.println("Error al registrar receta externa" + e.getMessage());
        }
        return -1;
    }

    public RecetaMedicaExterna buscarPorID(int id){
        String sql = "SELECT * FROM RecetaMedicaExterna WHERE idRecetaExterna = ?";

        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return mapear(rs);
            } 
        } catch (SQLException e){
            System.out.println("Error de busqueda de receta externa: " + e.getMessage());
        }
        return null;
    }

     public List<RecetaMedicaExterna> listar(){
        List<RecetaMedicaExterna> lista = new ArrayList<>();
        String sql = "SELECT * FROM RecetaMedicaExterna";

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
        String sql = "DELETE FROM RecetaMedicaExterna WHERE idRecetaExterna = ?";
        
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar receta: " + e.getMessage());
        }

        return false;
    }
}
