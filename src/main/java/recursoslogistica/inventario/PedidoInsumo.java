package recursoslogistica.inventario;

import java.time.LocalDateTime;

public class PedidoInsumo {
    private String numeroPedido;
    private String moduloSolicitante;
    private Insumo insumo;
    private int cantidadSolicitada;
    private EstadoPedido estado;
    private LocalDateTime fechaPedido;
    private LocalDateTime fechaEntrega;

    public PedidoInsumo(String numeroPedido, String moduloSolicitante, 
                        Insumo insumo, int cantidadSolicitada) {
        this.numeroPedido = numeroPedido;
        this.moduloSolicitante = moduloSolicitante;
        this.insumo = insumo;
        this.cantidadSolicitada = cantidadSolicitada;
        this.estado = EstadoPedido.PENDIENTE;
        this.fechaPedido = LocalDateTime.now();
    }

    public boolean procesarPedido() {
        if (insumo.retirar(cantidadSolicitada)) {
            this.estado = EstadoPedido.APROBADO;
            this.fechaEntrega = LocalDateTime.now();
            return true;
        } else {
            this.estado = EstadoPedido.RECHAZADO;
            return false;
        }
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Pedido #" + numeroPedido + " - " + moduloSolicitante 
                + " - " + insumo.getNombre() + " x" + cantidadSolicitada 
                + " - Estado: " + estado;
    }
}