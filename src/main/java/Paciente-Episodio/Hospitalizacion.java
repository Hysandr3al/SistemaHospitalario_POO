package hospital.pacienteepisodio;

public class Hospitalizacion extends Episodio {
    private int cama;

    public Hospitalizacion(String descripcion, int cama) {
        super(descripcion);
        this.cama = cama;
    }

    @Override
    public String toString() {
        return "Hospitalizacion: " + descripcion + " (" + fecha + ") - Cama: " + cama;
    }
}
