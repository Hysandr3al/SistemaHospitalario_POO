package recursoslogistica.inventario;

import recursoslogistica.Recurso;
import recursoslogistica.EstadoRecurso;

public class Insumo extends Recurso {
    private CategoriaInsumo categoria;
    private int cantidad;
    private int stockMinimo;
    private String unidadMedida;

    public Insumo(String codigo, String nombre, CategoriaInsumo categoria, 
                  int cantidad, int stockMinimo, String unidadMedida) {
        super(codigo, nombre);
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.stockMinimo = stockMinimo;
        this.unidadMedida = unidadMedida;
        actualizarEstado();
    }

    public void agregar(int cantidadAgregar) {
        this.cantidad += cantidadAgregar;
        actualizarEstado();
    }

    public boolean retirar(int cantidadRetirar) {
        if (this.cantidad >= cantidadRetirar) {
            this.cantidad -= cantidadRetirar;
            actualizarEstado();
            return true;
        }
        return false;
    }

    private void actualizarEstado() {
        if (cantidad == 0) {
            this.estado = EstadoRecurso.AGOTADO;
        } else if (cantidad <= stockMinimo) {
            this.estado = EstadoRecurso.EN_MANTENIMIENTO;
        } else {
            this.estado = EstadoRecurso.DISPONIBLE;
        }
    }

    public boolean necesitaReposicion() {
        return cantidad <= stockMinimo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public CategoriaInsumo getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        String alerta = necesitaReposicion() ? " ⚠️ STOCK BAJO" : "";
        return "Insumo: " + nombre + " (" + categoria + ") - Stock: " 
                + cantidad + " " + unidadMedida + alerta;
    }
}