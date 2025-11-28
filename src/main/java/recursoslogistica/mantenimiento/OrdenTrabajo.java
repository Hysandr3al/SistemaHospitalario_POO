package recursoslogistica.mantenimiento;

import java.time.LocalDateTime;

public class OrdenTrabajo {
    private String numeroOrden;
    private EquipoBiomedico equipo;
    private TipoMantenimiento tipo;
    private String descripcion;
    private String tecnicoAsignado;
    private EstadoOrden estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaFinalizacion;

public OrdenTrabajo(String numeroOrden, EquipoBiomedico equipo, 
                    TipoMantenimiento tipo, String descripcion) {
    this.numeroOrden = numeroOrden;
    this.equipo = equipo;
    this.tipo = tipo;
    this.descripcion = descripcion;
    this.estado = EstadoOrden.PENDIENTE;
    this.fechaCreacion = LocalDateTime.now();
}

public void asignarTecnico(String tecnico) {
    this.tecnicoAsignado = tecnico;
    this.estado = EstadoOrden.ASIGNADA;
}

public void iniciarTrabajo() {
    this.estado = EstadoOrden.EN_PROCESO;
    equipo.marcarEnMantenimiento();
}

public void finalizar() {
    this.estado = EstadoOrden.FINALIZADA;
    this.fechaFinalizacion = LocalDateTime.now();
    equipo.registrarMantenimiento();
}

public String getNumeroOrden() {
    return numeroOrden;
}

public EstadoOrden getEstado() {
    return estado;
}

@Override
public String toString() {
    return "Orden #" + numeroOrden + " - " + tipo + " - " + equipo.getNombre() 
            + " - Estado: " + estado 
            + (tecnicoAsignado != null ? " - Técnico: " + tecnicoAsignado : "");
}
}