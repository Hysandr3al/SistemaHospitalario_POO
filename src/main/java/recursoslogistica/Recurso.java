package recursoslogistica;

import java.time.LocalDate;

/**
 * Clase abstracta base para todos los recursos del hospital
 * (Ambulancias, Insumos, Equipos, etc.)
 */
public abstract class Recurso {
    protected String codigo;
    protected String nombre;
    protected EstadoRecurso estado;
    protected LocalDate fechaRegistro;

    public Recurso(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.estado = EstadoRecurso.DISPONIBLE;
        this.fechaRegistro = LocalDate.now();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public EstadoRecurso getEstado() {
        return estado;
    }

    public void setEstado(EstadoRecurso estado) {
        this.estado = estado;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    @Override
    public abstract String toString();
}