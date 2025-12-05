package recursoslogistica;

public class Insumo extends Recurso {
    private CategoriaInsumo categoria;
    private int cantidad;
    private int stockMinimo;

    public Insumo(String codigo, String nombre, CategoriaInsumo categoria, int cantidad, int stockMinimo) {
        super(codigo, nombre);
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.stockMinimo = stockMinimo;
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

    public void agregar(int cantidadAgregar) {
        this.cantidad += cantidadAgregar;
        actualizarEstado();
    }

    private void actualizarEstado() {
        if (cantidad == 0) {
            this.estado = EstadoRecurso.AGOTADO;
        } else {
            this.estado = EstadoRecurso.DISPONIBLE;
        }
    }

    public boolean necesitaReposicion() {
        return cantidad <= stockMinimo;
    }

    // --- GETTERS NECESARIOS PARA EL DAO (Base de Datos) ---
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; } // Necesario para cargar desde BD

    public CategoriaInsumo getCategoria() { return categoria; }
    
    public int getStockMinimo() { return stockMinimo; } // Este faltaba

    @Override
    public String toString() {
        String alerta = necesitaReposicion() ? " STOCK BAJO" : "";
        return "Insumo: " + nombre + " (" + categoria + ") - Stock: " + cantidad + " unidades" + alerta;
    }
}