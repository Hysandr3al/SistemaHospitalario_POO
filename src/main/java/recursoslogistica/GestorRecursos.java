package recursoslogistica;

import dao.AmbulanciaDAO;
import dao.InsumoDAO;
import java.util.ArrayList;
import java.util.List;

public class GestorRecursos {
    private AmbulanciaDAO ambulanciaDAO;
    private InsumoDAO insumoDAO;
    private ArrayList<Pedido> pedidos;
    private int contadorPedidos;

    public GestorRecursos() {
        this.ambulanciaDAO = new AmbulanciaDAO();
        this.insumoDAO = new InsumoDAO();
        this.pedidos = new ArrayList<>();
        this.contadorPedidos = 1;
    }

    public void agregarAmbulancia(Ambulancia ambulancia) {
        // Intentamos registrar. Si falla (ej. placa repetida), el DAO devuelve false.
        if (ambulanciaDAO.registrar(ambulancia)) {
            System.out.println("-> Ambulancia " + ambulancia.getPlaca() + " guardada en BD.");
        } else {
            System.out.println("-> La ambulancia " + ambulancia.getPlaca() + " ya existe o hubo error.");
        }
    }

    public void agregarInsumo(Insumo insumo) {
        if (insumoDAO.registrar(insumo)) {
            System.out.println("-> Insumo " + insumo.getNombre() + " guardado en BD.");
        } else {
            System.out.println("-> El insumo " + insumo.getCodigo() + " ya existe o hubo error.");
        }
    }

    public Ambulancia solicitarAmbulancia() {
        List<Ambulancia> flota = ambulanciaDAO.listar();
        for (Ambulancia amb : flota) {
            if (amb.getEstado() == EstadoRecurso.DISPONIBLE) {
                return amb;
            }
        }
        return null;
    }
    
    // Metodo critico para guardar cambios de estado
    public void guardarCambiosAmbulancia(Ambulancia amb) {
        if (ambulanciaDAO.modificar(amb)) {
            System.out.println("   (Estado actualizado en Base de Datos)");
        }
    }

    public Pedido crearPedido(String departamento, String codigoInsumo, int cantidad) {
        Insumo insumo = insumoDAO.buscarPorCodigo(codigoInsumo); 
        
        if (insumo == null) {
            System.out.println(" Insumo no encontrado: " + codigoInsumo);
            return null;
        }

        String numeroPedido = "PED" + String.format("%03d", contadorPedidos++);
        Pedido pedido = new Pedido(numeroPedido, departamento, insumo, cantidad);
        
        // Si hay stock, procesar() resta la cantidad en el objeto Java
        if (pedido.procesar()) {
            // Y aqui guardamos esa resta en la Base de Datos
            insumoDAO.modificar(insumo); 
        }
        
        pedidos.add(pedido);
        return pedido;
    }

    public void generarReporte() {
        System.out.println("\n REPORTE (DATOS EN TIEMPO REAL DESDE SQL SERVER)");
        System.out.println("===============================================");
        
        System.out.println("AMBULANCIAS:");
        List<Ambulancia> flota = ambulanciaDAO.listar();
        for (Ambulancia a : flota) System.out.println(" - " + a);

        System.out.println("\nINSUMOS:");
        List<Insumo> inv = insumoDAO.listar();
        for (Insumo i : inv) System.out.println(" - " + i);
        
        System.out.println("===============================================");
    }
}