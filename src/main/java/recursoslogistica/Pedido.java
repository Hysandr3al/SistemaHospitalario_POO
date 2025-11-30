package recursoslogistica;

import java.time.LocalDateTime;

public class Pedido {
    private String numeroPedido;
    private String departamentoSolicitante;
    private Insumo insumo;
    private int cantidadSolicitada;
    private boolean aprobado;
    private LocalDateTime fecha;

    public Pedido(String numeroPedido, String departamento, Insumo insumo, int cantidad) {
        this.numeroPedido = numeroPedido;
        this.departamentoSolicitante = departamento;
        this.insumo = insumo;
        this.cantidadSolicitada = cantidad;
        this.fecha = LocalDateTime.now();
        this.aprobado = false;
    }

    public boolean procesar() {
        if (insumo.retirar(cantidadSolicitada)) {
            this.aprobado = true;
            System.out.println("✅ Pedido aprobado: " + this);
            return true;
        } else {
            this.aprobado = false;
            System.out.println("❌ Pedido rechazado - Stock insuficiente: " + this);
            return false;
        }
    }

    public boolean isAprobado() {
        return aprobado;
    }

    @Override
    public String toString() {
        return "Pedido #" + numeroPedido + " - " + departamentoSolicitante + " - " + insumo.getNombre() + " x" + cantidadSolicitada;
    }
}