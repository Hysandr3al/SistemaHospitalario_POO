package hospital.pacienteepisodio;

public class Urgencia extends Episodio {

    private String triage;

    public Urgencia(String descripcion, String triage) {
        super(descripcion);
        this.triage = triage;
    }

    public Urgencia(int id, String descripcion, String triage, java.time.LocalDate fecha) {
        super(id, descripcion, fecha);
        this.triage = triage;
    }

    public String getTriage() {
        return triage;
    }

    public void setTriage(String triage) {
        this.triage = triage;
    }

    @Override
    public String toString() {
        return "Urgencia: " + descripcion + " (" + fecha + ") - Triage: " + triage;
    }
}