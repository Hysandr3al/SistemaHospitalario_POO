package hospital.pacienteepisodio;

import java.time.LocalDate;

public abstract class Episodio {

    protected int idEpisodio;     // ← NECESARIO PARA EL DAO
    protected String descripcion;
    protected LocalDate fecha;

    public Episodio(String descripcion) {
        this.descripcion = descripcion;
        this.fecha = LocalDate.now();
    }

    public int getIdEpisodio() {
        return idEpisodio;
    }

    public void setIdEpisodio(int id) {   // ← El DAO lo colocará
        this.idEpisodio = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public abstract String toString();
}
