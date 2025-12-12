package hospital.pacienteepisodio;

public class CitaAmbulatoria extends Episodio {

    public CitaAmbulatoria(String descripcion) {
        super(descripcion);
    }

    @Override
    public String toString() {
        return "CitaAmbulatoria: " + descripcion + " (" + fecha + ")";
    }
}
