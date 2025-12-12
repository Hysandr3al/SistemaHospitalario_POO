package dao;
 
import java.util.List;


public interface InterfazCRUD <T> {
    boolean registrar(T obj);
    boolean modificar(T obj);
    boolean eliminar(int id);
    T buscarPorId(int id);
    List<T> listar();
}

