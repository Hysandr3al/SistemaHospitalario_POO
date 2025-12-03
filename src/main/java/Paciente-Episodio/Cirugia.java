package hospital.pacienteepisodio;

public class Cirugia extends Episodio {

    private String tipo;

    public Cirugia(String descripcion, String tipo) {
        super(descripcion);
        this.tipo = tipo;
    }

    public Cirugia(int id, String descripcion, String tipo, java.time.LocalDate fecha) {
        super(id, descripcion, fecha);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Cirugía: " + descripcion + " (" + fecha + ") - Tipo: " + tipo;
    }
}