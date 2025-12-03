package hospital.pacienteepisodio;

import java.time.LocalDate;

public abstract class Episodio {

    protected int idEpisodio;
    protected String descripcion;
    protected LocalDate fecha;

    public Episodio(String descripcion) {
        this.descripcion = descripcion;
        this.fecha = LocalDate.now();
    }

    // Constructor con ID (para el DAO)
    public Episodio(int idEpisodio, String descripcion, LocalDate fecha) {
        this.idEpisodio = idEpisodio;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getIdEpisodio() {
        return idEpisodio;
    }

    public void setIdEpisodio(int idEpisodio) {
        this.idEpisodio = idEpisodio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public abstract String toString();
}