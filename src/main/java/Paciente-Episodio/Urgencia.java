package hospital.pacienteepisodio;

public class Urgencia extends Episodio {
    private String triage;

    public Urgencia(String descripcion, String triage) {
        super(descripcion);
        this.triage = triage;
    }

    @Override
    public String toString() {
        return "Urgencia: " + descripcion + " (" + fecha + ") - Triage: " + triage;
    }
}
