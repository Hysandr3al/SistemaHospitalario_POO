package recursoslogistica;

import java.time.LocalDate;

public abstract class Recurso {
    
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

    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public EstadoRecurso getEstado() { return estado; }
    public void setEstado(EstadoRecurso estado) { this.estado = estado; }

    @Override
    public abstract String toString();
}