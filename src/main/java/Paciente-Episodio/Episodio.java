package hospital.pacienteepisodio;

import java.time.LocalDate;

public abstract class Episodio {
    protected String descripcion;
    protected LocalDate fecha;

    public Episodio(String descripcion) {
        this.descripcion = descripcion;
        this.fecha = LocalDate.now();
    }

    @Override
    public abstract String toString();
}
