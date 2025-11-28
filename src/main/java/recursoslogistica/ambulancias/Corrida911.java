package recursoslogistica.ambulancias;

import java.time.LocalDateTime;

public class Corrida911 {
    private String numeroCorrida;
    private String direccionOrigen;
    private String motivo;
    private Ambulancia ambulanciaAsignada;
    private EstadoCorrida estado;
    private LocalDateTime horaLlamada;
    private LocalDateTime horaSalida;
    private LocalDateTime horaLlegada;

    public Corrida911(String numeroCorrida, String direccionOrigen, String motivo) {
        this.numeroCorrida = numeroCorrida;
        this.direccionOrigen = direccionOrigen;
        this.motivo = motivo;
        this.estado = EstadoCorrida.PENDIENTE;
        this.horaLlamada = LocalDateTime.now();
    }

    public void asignarAmbulancia(Ambulancia ambulancia) {
        this.ambulanciaAsignada = ambulancia;
        this.estado = EstadoCorrida.ASIGNADA;
    }

    public void iniciarCorrida() {
        this.horaSalida = LocalDateTime.now();
        this.estado = EstadoCorrida.EN_CAMINO;
    }

    public void finalizarCorrida() {
        this.horaLlegada = LocalDateTime.now();
        this.estado = EstadoCorrida.FINALIZADA;
    }

    public String getNumeroCorrida() {
        return numeroCorrida;
    }

    public EstadoCorrida getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Corrida #" + numeroCorrida + " - " + motivo 
                + " (" + direccionOrigen + ") - Estado: " + estado 
                + (ambulanciaAsignada != null ? " - Ambulancia: " + ambulanciaAsignada.getPlaca() : "");
    }
}