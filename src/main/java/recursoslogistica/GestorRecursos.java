package recursoslogistica;

import java.util.ArrayList;

public class GestorRecursos {
    private ArrayList<Ambulancia> flota;
    private ArrayList<Insumo> inventario;
    private ArrayList<Pedido> pedidos;
    private int contadorPedidos;

    public GestorRecursos() {
        this.flota = new ArrayList<>();
        this.inventario = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.contadorPedidos = 1;
    }

    public void agregarAmbulancia(Ambulancia ambulancia) {
        flota.add(ambulancia);
    }

    public void agregarInsumo(Insumo insumo) {
        inventario.add(insumo);
    }

    public Ambulancia solicitarAmbulancia() {
        for (Ambulancia amb : flota) {
            if (amb.getEstado() == EstadoRecurso.DISPONIBLE) {
                return amb;
            }
        }
        return null;
    }

    public Pedido crearPedido(String departamento, String codigoInsumo, int cantidad) {
        Insumo insumo = buscarInsumo(codigoInsumo);
        if (insumo == null) {
            System.out.println("❌ Insumo no encontrado: " + codigoInsumo);
            return null;
        }

        String numeroPedido = "PED" + String.format("%03d", contadorPedidos++);
        Pedido pedido = new Pedido(numeroPedido, departamento, insumo, cantidad);
        pedidos.add(pedido);
        return pedido;
    }

    private Insumo buscarInsumo(String codigo) {
        for (Insumo insumo : inventario) {
            if (insumo.getCodigo().equals(codigo)) {
                return insumo;
            }
        }
        return null;
    }

    public void generarReporte() {
        System.out.println("\n📊 REPORTE DEL MÓDULO C - RECURSOS Y LOGÍSTICA");
        System.out.println("=".repeat(60));

        System.out.println("\n🚑 FLOTA DE AMBULANCIAS:");
        for (Ambulancia amb : flota) {
            System.out.println("  " + amb);
        }

        System.out.println("\n📦 INVENTARIO:");
        for (Insumo insumo : inventario) {
            System.out.println("  " + insumo);
        }

        System.out.println("\n📋 PEDIDOS RECIENTES:");
        int max = Math.min(5, pedidos.size());
        for (int i = Math.max(0, pedidos.size() - max); i < pedidos.size(); i++) {
            System.out.println("  " + pedidos.get(i) + " - " + (pedidos.get(i).isAprobado() ? "✅ APROBADO" : "❌ RECHAZADO"));
        }

        System.out.println("=".repeat(60));
    }
}