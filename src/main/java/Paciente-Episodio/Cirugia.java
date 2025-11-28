package hospital.pacienteepisodio;

public class Cirugia extends Episodio {
    private String tipo;

    public Cirugia(String descripcion, String tipo) {
        super(descripcion);
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Cirugia: " + descripcion + " (" + fecha + ") - Tipo: " + tipo;
    }
}
