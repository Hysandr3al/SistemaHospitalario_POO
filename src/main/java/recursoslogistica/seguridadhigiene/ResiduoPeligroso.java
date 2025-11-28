package recursoslogistica.seguridadhigiene;

import java.time.LocalDateTime;

public class ResiduoPeligroso {
    private String identificador;
    private TipoResiduo tipo;
    private double peso;
    private String ubicacionOrigen;
    private LocalDateTime fechaGeneracion;
    private EstadoResiduo estado;

    public ResiduoPeligroso(String identificador, TipoResiduo tipo, 
                            double peso, String ubicacionOrigen) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.peso = peso;
        this.ubicacionOrigen = ubicacionOrigen;
        this.fechaGeneracion = LocalDateTime.now();
        this.estado = EstadoResiduo.GENERADO;
    }

    public void recolectar() {
        this.estado = EstadoResiduo.RECOLECTADO;
    }

    public void transportar() {
        this.estado = EstadoResiduo.EN_TRANSPORTE;
    }

    public void tratar() {
        this.estado = EstadoResiduo.TRATADO;
    }

    public void disponer() {
        this.estado = EstadoResiduo.DISPUESTO;
    }

    public TipoResiduo getTipo() {
        return tipo;
    }

    public double getPeso() {
        return peso;
    }

    public EstadoResiduo getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Residuo #" + identificador + " - " + tipo + " - " + peso + " kg"
                + " - Origen: " + ubicacionOrigen + " - Estado: " + estado;
    }
}