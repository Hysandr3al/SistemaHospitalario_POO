package recursoslogistica.mantenimiento;

import recursoslogistica.Recurso;
import recursoslogistica.EstadoRecurso;
import java.time.LocalDate;

public class EquipoBiomedico extends Recurso {
    private TipoEquipo tipo;
    private String ubicacion;
    private LocalDate ultimoMantenimiento;
    private int diasEntreMantenimientos;

    public EquipoBiomedico(String codigo, String nombre, TipoEquipo tipo, 
                           String ubicacion, int diasEntreMantenimientos) {
        super(codigo, nombre);
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.diasEntreMantenimientos = diasEntreMantenimientos;
        this.ultimoMantenimiento = LocalDate.now();
    }

    public boolean necesitaMantenimiento() {
        LocalDate proximoMantenimiento = ultimoMantenimiento.plusDays(diasEntreMantenimientos);
        return LocalDate.now().isAfter(proximoMantenimiento) || 
               LocalDate.now().isEqual(proximoMantenimiento);
    }

    public void registrarMantenimiento() {
        this.ultimoMantenimiento = LocalDate.now();
        this.estado = EstadoRecurso.DISPONIBLE;
    }

    public void marcarEnMantenimiento() {
        this.estado = EstadoRecurso.EN_MANTENIMIENTO;
    }

    public TipoEquipo getTipo() {
        return tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public LocalDate getUltimoMantenimiento() {
        return ultimoMantenimiento;
    }

    @Override
    public String toString() {
        String alerta = necesitaMantenimiento() ? " ⚠️ REQUIERE MANTENIMIENTO" : "";
        return "Equipo: " + nombre + " (" + tipo + ") - Ubicación: " + ubicacion 
                + " - Estado: " + estado + " - Último mant.: " + ultimoMantenimiento + alerta;
    }
}