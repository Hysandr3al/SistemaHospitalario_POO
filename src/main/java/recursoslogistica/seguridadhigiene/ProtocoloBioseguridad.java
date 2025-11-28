package recursoslogistica.seguridadhigiene;

import java.time.LocalDate;

public class ProtocoloBioseguridad {
    private String codigoProtocolo;
    private String nombre;
    private String descripcion;
    private NivelRiesgo nivelRiesgo;
    private LocalDate fechaEmision;
    private boolean activo;

    public ProtocoloBioseguridad(String codigoProtocolo, String nombre, 
                                 String descripcion, NivelRiesgo nivelRiesgo) {
        this.codigoProtocolo = codigoProtocolo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivelRiesgo = nivelRiesgo;
        this.fechaEmision = LocalDate.now();
        this.activo = true;
    }

    public void desactivar() {
        this.activo = false;
    }

    public void activar() {
        this.activo = true;
    }

    public String getCodigoProtocolo() {
        return codigoProtocolo;
    }

    public NivelRiesgo getNivelRiesgo() {
        return nivelRiesgo;
    }

    public boolean isActivo() {
        return activo;
    }

    @Override
    public String toString() {
        return "Protocolo: " + codigoProtocolo + " - " + nombre 
                + " - Nivel de riesgo: " + nivelRiesgo 
                + " - " + (activo ? "ACTIVO" : "INACTIVO");
    }
}