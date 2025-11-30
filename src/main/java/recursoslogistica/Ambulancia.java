package recursoslogistica;

public class Ambulancia extends Recurso {
    private String placa;
    private String tipoAmbulancia;
    private String ubicacionActual;

    public Ambulancia(String codigo, String placa, String tipo) {
        super(codigo, "Ambulancia " + placa);
        this.placa = placa;
        this.tipoAmbulancia = tipo;
        this.ubicacionActual = "Hospital";
    }

    public void asignarCorrida(String destino) {
        this.estado = EstadoRecurso.EN_USO;
        this.ubicacionActual = destino;
        System.out.println("Ambulancia " + placa + " enviada a: " + destino);
    }

    public void finalizarCorrida() {
        this.estado = EstadoRecurso.DISPONIBLE;
        this.ubicacionActual = "Hospital";
        System.out.println("Ambulancia " + placa + " regresó al hospital");
    }

    public String getPlaca() {
        return placa;
    }

    @Override
    public String toString() {
        return "Ambulancia " + placa + " (" + tipoAmbulancia + ") - Estado: " + estado + " - Ubicación: " + ubicacionActual;
    }
}