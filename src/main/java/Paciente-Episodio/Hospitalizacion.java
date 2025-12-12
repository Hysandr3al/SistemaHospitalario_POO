package hospital.pacienteepisodio;

public class Hospitalizacion extends Episodio {

    private int cama;

    public Hospitalizacion(String descripcion, int cama) {
        super(descripcion);
        this.cama = cama;
    }

    public Hospitalizacion(int id, String descripcion, int cama, java.time.LocalDate fecha) {
        super(id, descripcion, fecha);
        this.cama = cama;
    }

    public int getCama() {
        return cama;
    }

    public void setCama(int cama) {
        this.cama = cama;
    }

    @Override
    public String toString() {
        return "Hospitalización: " + descripcion + " (" + fecha + ") - Cama: " + cama;
    }
}