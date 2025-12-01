package recursoslogistica;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GestorRecursos gestor = new GestorRecursos();

    public static void main(String[] args) {
        System.out.println(" ________________________________________________");
        System.out.println("|                                                |");
        System.out.println("|        MODULO C - RECURSOS Y LOGISTICA         |");
        System.out.println("|        Sistema Hospitalario POO - Harol        |");
        System.out.println("|________________________________________________|");
        System.out.println();

        // Inicializar datos de ejemplo
        inicializarSistema();

        // Mostrar menú principal
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 0);

        System.out.println("\n¡Gracias por usar el sistema! Hasta pronto.");
        scanner.close();
    }

    private static void inicializarSistema() {
        System.out.println("  Inicializando sistema...\n");

        // Ambulancias
        Ambulancia amb1 = new Ambulancia("AMB001", "ABC-123", "Medicalizada");
        Ambulancia amb2 = new Ambulancia("AMB002", "DEF-456", "Basica");
        Ambulancia amb3 = new Ambulancia("AMB003", "GHI-789", "UCI Movil");
        gestor.agregarAmbulancia(amb1);
        gestor.agregarAmbulancia(amb2);
        gestor.agregarAmbulancia(amb3);

        // Insumos
        Insumo guantes = new Insumo("INS001", "Guantes de latex", CategoriaInsumo.DESECHABLE, 500, 100);
        Insumo oxigeno = new Insumo("INS002", "Tanque de oxigeno", CategoriaInsumo.OXIGENO, 15, 5);
        Insumo jeringas = new Insumo("INS003", "Jeringas 10ml", CategoriaInsumo.DESECHABLE, 200, 100);
        Insumo mascarillas = new Insumo("INS004", "Mascarillas N95", CategoriaInsumo.DESECHABLE, 300, 50);
        Insumo suero = new Insumo("INS005", "Suero fisiologico", CategoriaInsumo.MEDICAMENTO, 150, 30);
        gestor.agregarInsumo(guantes);
        gestor.agregarInsumo(oxigeno);
        gestor.agregarInsumo(jeringas);
        gestor.agregarInsumo(mascarillas);
        gestor.agregarInsumo(suero);

        System.out.println("- 3 ambulancias registradas");
        System.out.println("- 5 insumos registrados");
        System.out.println();
        esperarEnter();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println(" _________________________________________________________");
        System.out.println("|                    MENU PRINCIPAL                       |");
        System.out.println("|---------------------------------------------------------|");
        System.out.println("|  1.  Gestion de Ambulancias                             |");
        System.out.println("|  2.  Gestion de Inventario                              |");
        System.out.println("|  3.  Gestion de Pedidos                                 |");
        System.out.println("|  4.  Ver Reporte Completo                               |");
        System.out.println("|  0.  Salir                                              |");
        System.out.println("|_________________________________________________________|");
        System.out.print("Seleccione una opcion: ");
    }

    private static void procesarOpcion(int opcion) {
        System.out.println();
        switch (opcion) {
            case 1:
                menuAmbulancia();
                break;
            case 2:
                menuInventario();
                break;
            case 3:
                menuPedidos();
                break;
            case 4:
                gestor.generarReporte();
                esperarEnter();
                break;
            case 0:
                // Salir
                break;
            default:
                System.out.println(" Opción inválida. Intente de nuevo.");
        }
    }

    // ========== MENÚ AMBULANCIAS ==========
    private static void menuAmbulancia() {
        System.out.println(" _____________________________________________________");
        System.out.println("|                GESTION DE AMBULANCIAS               |");
        System.out.println("|_____________________________________________________|");
        System.out.println("|  1. Ver ambulancias disponibles                     |");
        System.out.println("|  2. Solicitar ambulancia para emergencia            |");
        System.out.println("|  3. Finalizar corrida                               |");
        System.out.println("|  0. Volver al menu principal                        |");
        System.out.println("|_____________________________________________________|");
        System.out.print("Seleccione una opcion: ");

        int opcion = leerOpcion();
        System.out.println();

        switch (opcion) {
            case 1:
                verAmbulancias();
                break;
            case 2:
                solicitarAmbulancia();
                break;
            case 3:
                finalizarCorrida();
                break;
            case 0:
                return;
            default:
                System.out.println("Opcion invalida.");
        }
        esperarEnter();
    }

    private static void verAmbulancias() {
        System.out.println("--- AMBULANCIAS EN EL SISTEMA ---\n");
        // Aquí accederíamos a la lista, pero como es privada, 
        // mostramos mensaje genérico
        System.out.println("Para ver el reporte completo, use la opcion 4 del menu principal.");
    }

    private static void solicitarAmbulancia() {
        System.out.println("--- SOLICITAR AMBULANCIA PARA EMERGENCIA ---\n");
        
        System.out.print("Ingrese direccion de la emergencia: ");
        scanner.nextLine(); // Limpiar buffer
        String direccion = scanner.nextLine();

        System.out.print("Ingrese motivo de la emergencia: ");
        String motivo = scanner.nextLine();

        Ambulancia amb = gestor.solicitarAmbulancia();
        
        if (amb != null) {
            amb.asignarCorrida(direccion);
            System.out.println("\n " + amb);
            System.out.println(" Destino: " + direccion);
            System.out.println(" Motivo: " + motivo);
        } else {
            System.out.println("\n No hay ambulancias disponibles en este momento.");
        }
    }

    private static void finalizarCorrida() {
        System.out.println("--- FINALIZAR CORRIDA ---\n");
        System.out.print("Ingrese placa de la ambulancia: ");
        scanner.nextLine(); // Limpiar buffer
        String placa = scanner.nextLine();

        // Simulación de finalización
        System.out.println("\n Corrida finalizada para ambulancia " + placa);
        System.out.println("Ambulancia regreso al hospital");
    }

    // ========== MENÚ INVENTARIO ==========
    private static void menuInventario() {
        System.out.println(" ____________________________________________________________");
        System.out.println("|                  GESTION DE INVENTARIO                     |");
        System.out.println("|____________________________________________________________|");
        System.out.println("|  1. Ver stock de insumos                                   |");
        System.out.println("|  2. Agregar stock a un insumo                              |");
        System.out.println("|  3. Ver alertas de stock bajo                              |");
        System.out.println("|  0. Volver al menu principal                               |");
        System.out.println("|____________________________________________________________|");
        System.out.print("Seleccione una opcion: ");

        int opcion = leerOpcion();
        System.out.println();

        switch (opcion) {
            case 1:
                verInventario();
                break;
            case 2:
                agregarStock();
                break;
            case 3:
                verAlertas();
                break;
            case 0:
                return;
            default:
                System.out.println("Opción invalida.");
        }
        esperarEnter();
    }

    private static void verInventario() {
        System.out.println("--- STOCK DE INSUMOS ---\n");
        System.out.println("Para ver el inventario completo, use la opcion 4 del menu principal.");
    }

    private static void agregarStock() {
        System.out.println("--- AGREGAR STOCK A INSUMO ---\n");
        System.out.println("Codigos disponibles:");
        System.out.println("INS001 - Guantes de latex");
        System.out.println("INS002 - Tanque de oxigeno");
        System.out.println("INS003 - Jeringas 10ml");
        System.out.println("INS004 - Mascarillas N95");
        System.out.println("INS005 - Suero fisiologico");
        
        System.out.print("\nIngrese código del insumo: ");
        scanner.nextLine(); // Limpiar buffer
        String codigo = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese cantidad a agregar: ");
        int cantidad = leerOpcion();

        System.out.println("\n Se agregaron " + cantidad + " unidades al insumo " + codigo);
    }

    private static void verAlertas() {
        System.out.println("--- ALERTAS DE STOCK BAJO ---\n");
        System.out.println("Verificando insumos con stock bajo...\n");
        System.out.println("Use el reporte completo (opcion 4) para ver detalles.");
    }

    // ========== MENÚ PEDIDOS ==========
    private static void menuPedidos() {
        System.out.println(" ____________________________________________________________");
        System.out.println("|                                                            |");
        System.out.println("|                  GESTION DE PEDIDOS                        |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("|  1. Crear nuevo pedido                                     |");
        System.out.println("|  2. Ver pedidos recientes                                  |");
        System.out.println("|  0. Volver al menu principal                               |");
        System.out.println("|____________________________________________________________|");
        System.out.print("Seleccione una opcion: ");

        int opcion = leerOpcion();
        System.out.println();

        switch (opcion) {
            case 1:
                crearPedido();
                break;
            case 2:
                verPedidos();
                break;
            case 0:
                return;
            default:
                System.out.println("Opcion invalida.");
        }
        esperarEnter();
    }

    private static void crearPedido() {
        System.out.println("--- CREAR NUEVO PEDIDO ---\n");
        
        System.out.print("Departamento solicitante: ");
        scanner.nextLine(); // Limpiar buffer
        String departamento = scanner.nextLine();

        System.out.println("\nCodigos disponibles:");
        System.out.println("INS001 - Guantes de latex");
        System.out.println("INS002 - Tanque de oxígeno");
        System.out.println("INS003 - Jeringas 10ml");
        System.out.println("INS004 - Mascarillas N95");
        System.out.println("INS005 - Suero fisiologico");
        
        System.out.print("\nCodigo del insumo: ");
        String codigo = scanner.nextLine().toUpperCase();

        System.out.print("Cantidad solicitada: ");
        int cantidad = leerOpcion();

        System.out.println("\n--- PROCESANDO PEDIDO ---");
        Pedido pedido = gestor.crearPedido(departamento, codigo, cantidad);
        
        if (pedido != null) {
            pedido.procesar();
        }
    }

    private static void verPedidos() {
        System.out.println("--- PEDIDOS RECIENTES ---\n");
        System.out.println("Para ver el historial completo, use la opcion 4 del menu principal.");
    }

    // ========== UTILIDADES ==========
    private static int leerOpcion() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar buffer
            return -1;
        }
    }

    private static void esperarEnter() {
        System.out.println("\nPresione ENTER para continuar...");
        try {
            System.in.read();
            scanner.nextLine(); // Limpiar buffer
        } catch (Exception e) {
            // Ignorar
        }
    }
}