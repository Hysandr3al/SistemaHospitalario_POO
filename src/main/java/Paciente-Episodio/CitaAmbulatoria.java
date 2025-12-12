package hospital.pacienteepisodio;

public class CitaAmbulatoria extends Episodio {

    public CitaAmbulatoria(String descripcion) {
        super(descripcion);
    }

    public CitaAmbulatoria(int id, String descripcion, java.time.LocalDate fecha) {
        super(id, descripcion, fecha);
    }

    @Override
    public String toString() {
        return "Cita Ambulatoria: " + descripcion + " (" + fecha + ")";
    }
}