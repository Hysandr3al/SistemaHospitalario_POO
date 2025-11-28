package recursoslogistica;

import recursoslogistica.ambulancias.*;
import recursoslogistica.inventario.*;
import recursoslogistica.mantenimiento.*;
import recursoslogistica.seguridadhigiene.*;
import java.util.ArrayList;

/**
 * GESTOR CENTRAL del Módulo C
 * Esta clase es la INTERFAZ que expones a los otros módulos
 */
public class GestorRecursos {
    
    private ArrayList<Insumo> inventario;
    private ArrayList<Ambulancia> flota;
    private ArrayList<EquipoBiomedico> equipos;
    private ArrayList<PedidoInsumo> pedidos;
    private ArrayList<Corrida911> corridas;
    private ArrayList<OrdenTrabajo> ordenesMantenimiento;
    private ArrayList<ResiduoPeligroso> residuos;
    private ArrayList<ProtocoloBioseguridad> protocolos;
    
    private int contadorPedidos = 1;
    private int contadorCorridas = 1;
    private int contadorOrdenes = 1;
    private int contadorResiduos = 1;
    
    public GestorRecursos() {
        this.inventario = new ArrayList<>();
        this.flota = new ArrayList<>();
        this.equipos = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.corridas = new ArrayList<>();
        this.ordenesMantenimiento = new ArrayList<>();
        this.residuos = new ArrayList<>();
        this.protocolos = new ArrayList<>();
    }
    
    public boolean solicitarInsumo(String codigoInsumo, int cantidad, String moduloSolicitante) {
        Insumo insumo = buscarInsumo(codigoInsumo);
        
        if (insumo == null) {
            System.out.println("❌ Insumo no encontrado: " + codigoInsumo);
            return false;
        }
        
        String numeroPedido = "PED" + String.format("%03d", contadorPedidos++);
        PedidoInsumo pedido = new PedidoInsumo(numeroPedido, moduloSolicitante, insumo, cantidad);
        pedidos.add(pedido);
        
        boolean exitoso = pedido.procesarPedido();
        
        if (exitoso) {
            System.out.println("✅ Pedido aprobado: " + pedido);
        } else {
            System.out.println("❌ Pedido rechazado - Stock insuficiente: " + pedido);
        }
        
        return exitoso;
    }
    
    public boolean verificarDisponibilidad(String codigoInsumo, int cantidadRequerida) {
        Insumo insumo = buscarInsumo(codigoInsumo);
        return insumo != null && insumo.getCantidad() >= cantidadRequerida;
    }
    
    public int consultarStock(String codigoInsumo) {
        Insumo insumo = buscarInsumo(codigoInsumo);
        return insumo != null ? insumo.getCantidad() : 0;
    }
    
    public void agregarInsumo(Insumo insumo) {
        inventario.add(insumo);
    }
    
    public Ambulancia solicitarAmbulancia(TipoAmbulancia tipo) {
        for (Ambulancia amb : flota) {
            if (amb.getTipo() == tipo && amb.getEstado() == EstadoRecurso.DISPONIBLE) {
                amb.setEstado(EstadoRecurso.EN_USO);
                return amb;
            }
        }
        return null;
    }
    
    public Corrida911 registrarCorrida911(String direccion, String motivo) {
        String numeroCorrida = "C" + String.format("%03d", contadorCorridas++);
        Corrida911 corrida = new Corrida911(numeroCorrida, direccion, motivo);
        corridas.add(corrida);
        return corrida;
    }
    
    public void liberarAmbulancia(Ambulancia ambulancia) {
        ambulancia.setEstado(EstadoRecurso.DISPONIBLE);
        System.out.println("✅ Ambulancia liberada: " + ambulancia.getPlaca());
    }
    
    public void agregarAmbulancia(Ambulancia ambulancia) {
        flota.add(ambulancia);
    }
    
    public boolean equipoDisponible(String codigoEquipo) {
        EquipoBiomedico equipo = buscarEquipo(codigoEquipo);
        return equipo != null && equipo.getEstado() == EstadoRecurso.DISPONIBLE;
    }
    
    public void reportarEquipoDañado(String codigoEquipo, String descripcionProblema) {
        EquipoBiomedico equipo = buscarEquipo(codigoEquipo);
        
        if (equipo != null) {
            String numeroOrden = "OT" + String.format("%03d", contadorOrdenes++);
            OrdenTrabajo orden = new OrdenTrabajo(
                numeroOrden, equipo, TipoMantenimiento.CORRECTIVO, descripcionProblema
            );
            ordenesMantenimiento.add(orden);
            equipo.marcarEnMantenimiento();
            
            System.out.println("⚠️ Orden de mantenimiento creada: " + orden);
        }
    }
    
    public void agregarEquipo(EquipoBiomedico equipo) {
        equipos.add(equipo);
    }
    
    public void registrarResiduo(TipoResiduo tipo, double peso, String ubicacionOrigen) {
        String identificador = "RES" + String.format("%03d", contadorResiduos++);
        ResiduoPeligroso residuo = new ResiduoPeligroso(identificador, tipo, peso, ubicacionOrigen);
        residuos.add(residuo);
        
        System.out.println("☣️ Residuo registrado: " + residuo);
    }
    
    public ArrayList<ProtocoloBioseguridad> obtenerProtocolosActivos() {
        ArrayList<ProtocoloBioseguridad> activos = new ArrayList<>();
        for (ProtocoloBioseguridad protocolo : protocolos) {
            if (protocolo.isActivo()) {
                activos.add(protocolo);
            }
        }
        return activos;
    }
    
    public void agregarProtocolo(ProtocoloBioseguridad protocolo) {
        protocolos.add(protocolo);
    }
    
    private Insumo buscarInsumo(String codigo) {
        for (Insumo insumo : inventario) {
            if (insumo.getCodigo().equals(codigo)) {
                return insumo;
            }
        }
        return null;
    }
    
    private EquipoBiomedico buscarEquipo(String codigo) {
        for (EquipoBiomedico equipo : equipos) {
            if (equipo.getCodigo().equals(codigo)) {
                return equipo;
            }
        }
        return null;
    }
    
    public void generarReporte() {
        System.out.println("\n📊 REPORTE MÓDULO C - RECURSOS Y LOGÍSTICA");
        System.out.println("============================================================");
        
        System.out.println("\n📦 INVENTARIO:");
        for (Insumo insumo : inventario) {
            System.out.println("  " + insumo);
        }
        
        System.out.println("\n🚑 FLOTA DE AMBULANCIAS:");
        for (Ambulancia amb : flota) {
            System.out.println("  " + amb);
        }
        
        System.out.println("\n🔧 EQUIPOS BIOMÉDICOS:");
        for (EquipoBiomedico equipo : equipos) {
            System.out.println("  " + equipo);
        }
        
        System.out.println("\n📋 PEDIDOS RECIENTES:");
        int max = Math.min(5, pedidos.size());
        for (int i = pedidos.size() - max; i < pedidos.size(); i++) {
            System.out.println("  " + pedidos.get(i));
        }
        
        System.out.println("\n☣️ RESIDUOS PELIGROSOS:");
        for (ResiduoPeligroso residuo : residuos) {
            System.out.println("  " + residuo);
        }
        
        System.out.println("============================================================");
    }
    
    public ArrayList<Insumo> obtenerAlertasStock() {
        ArrayList<Insumo> alertas = new ArrayList<>();
        for (Insumo insumo : inventario) {
            if (insumo.necesitaReposicion()) {
                alertas.add(insumo);
            }
        }
        return alertas;
    }
    
    public ArrayList<EquipoBiomedico> obtenerEquiposEnMantenimiento() {
        ArrayList<EquipoBiomedico> enMantenimiento = new ArrayList<>();
        for (EquipoBiomedico equipo : equipos) {
            if (equipo.necesitaMantenimiento() || 
                equipo.getEstado() == EstadoRecurso.EN_MANTENIMIENTO) {
                enMantenimiento.add(equipo);
            }
        }
        return enMantenimiento;
    }
}