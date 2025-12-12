package recursoslogistica;

import java.time.LocalDate;

public abstract class Recurso {
    // 1. AGREGAR ESTE CAMPO ID
    protected int id; 
    
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

    // 2. AGREGAR GETTER Y SETTER PARA EL ID
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // ... (El resto de tus getters y toString déjalos igual) ...
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public EstadoRecurso getEstado() { return estado; }
    public void setEstado(EstadoRecurso estado) { this.estado = estado; }

    @Override
    public abstract String toString();
}