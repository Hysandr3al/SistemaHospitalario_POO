package recursoslogistica;

import java.util.Scanner;

public class MenuLogistica {
    private static Scanner scanner = new Scanner(System.in);
    private static GestorRecursos gestor = new GestorRecursos();

    public static void main(String[] args) {
        System.out.println(" ________________________________________________");
        System.out.println("|                                                |");
        System.out.println("|        MODULO C - RECURSOS Y LOGISTICA         |");
        System.out.println("|      Sistema Hospitalario POO - SQL Server     |");
        System.out.println("|________________________________________________|");
        System.out.println();

        inicializarSistema();


        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 0);

        System.out.println("\nGracias por usar el sistema! Hasta pronto.");
        scanner.close();
    }

    private static void inicializarSistema() {
        System.out.println("  Verificando conexion y datos en Base de Datos...\n");
        
 
        gestor.agregarAmbulancia(new Ambulancia("AMB001", "ABC-123", "Medicalizada"));
        gestor.agregarAmbulancia(new Ambulancia("AMB002", "DEF-456", "Basica"));
        gestor.agregarAmbulancia(new Ambulancia("AMB003", "GHI-789", "UCI Movil"));

       
        gestor.agregarInsumo(new Insumo("INS001", "Guantes de latex", CategoriaInsumo.DESECHABLE, 500, 100));
        gestor.agregarInsumo(new Insumo("INS002", "Tanque de oxigeno", CategoriaInsumo.OXIGENO, 15, 5));
        gestor.agregarInsumo(new Insumo("INS003", "Jeringas 10ml", CategoriaInsumo.DESECHABLE, 200, 100));
        gestor.agregarInsumo(new Insumo("INS004", "Mascarillas N95", CategoriaInsumo.DESECHABLE, 300, 50));
        gestor.agregarInsumo(new Insumo("INS005", "Suero fisiologico", CategoriaInsumo.MEDICAMENTO, 150, 30));

        System.out.println("\nCarga inicial completada.");
        esperarEnter();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println(" _________________________________________________________");
        System.out.println("|                    MENU PRINCIPAL                       |");
        System.out.println("|---------------------------------------------------------|");
        System.out.println("|  1.  Gestion de Ambulancias                             |");
        System.out.println("|  2.  Gestion de Inventario                              |");
        System.out.println("|  3.  Gestion de Pedidos                                 |");
        System.out.println("|  4.  Ver Reporte Completo (Desde BD)                    |");
        System.out.println("|  0.  Salir                                              |");
        System.out.println("|_________________________________________________________|");
        System.out.print("Seleccione una opcion: ");
    }

    private static void procesarOpcion(int opcion) {
        System.out.println();
        switch (opcion) {
            case 1 -> menuAmbulancia();
            case 2 -> menuInventario();
            case 3 -> menuPedidos();
            case 4 -> {
                gestor.generarReporte();
                esperarEnter();
            }
            case 0 -> {}
            default -> System.out.println(" Opcion invalida. Intente de nuevo.");
        }
    }

   
    private static void menuAmbulancia() {
        System.out.println(" _____________________________________________________");
        System.out.println("|                GESTION DE AMBULANCIAS               |");
        System.out.println("|_____________________________________________________|");
        System.out.println("|  1. Ver ambulancias disponibles (READ)              |");
        System.out.println("|  2. Solicitar ambulancia (UPDATE)                   |");
        System.out.println("|  3. Finalizar corrida (UPDATE)                      |");
        System.out.println("|  4. Eliminar ambulancia (DELETE)                    |");
        System.out.println("|  0. Volver al menu principal                        |");
        System.out.println("|_____________________________________________________|");
        System.out.print("Seleccione una opcion: ");

        int opcion = leerOpcion();
        System.out.println();

        switch (opcion) {
            case 1 -> gestor.generarReporte();
            case 2 -> solicitarAmbulancia();
            case 3 -> finalizarCorrida();
            case 4 -> eliminarAmbulancia(); // NUEVO: Llamada al método de eliminar
            case 0 -> { return; }
            default -> System.out.println("Opcion invalida.");
        }
        esperarEnter();
    }

    private static void solicitarAmbulancia() {
        System.out.println("--- SOLICITAR AMBULANCIA PARA EMERGENCIA ---\n");
        
        System.out.print("Ingrese direccion de la emergencia: ");
        scanner.nextLine(); 
        String direccion = scanner.nextLine();

        System.out.print("Ingrese motivo de la emergencia: ");
        String motivo = scanner.nextLine();

        Ambulancia amb = gestor.solicitarAmbulancia();
        
        if (amb != null) {
            amb.asignarCorrida(direccion);
            gestor.guardarCambiosAmbulancia(amb);
            
            System.out.println("\n " + amb);
            System.out.println(" Destino: " + direccion);
            System.out.println(" Motivo: " + motivo);
        } else {
            System.out.println("\n No hay ambulancias disponibles en este momento.");
        }
    }

private static void finalizarCorrida() {
        System.out.println("--- FINALIZAR CORRIDA (LIBERAR AMBULANCIA) ---\n");
        
        
        System.out.print("Ingrese placa de la ambulancia a liberar (Ej. ABC-123): ");
        scanner.nextLine(); 
        String placa = scanner.nextLine();
        
        
        boolean resultado = gestor.liberarAmbulancia(placa);
        
        if (resultado) {
            System.out.println("¡Ambulancia " + placa + " ahora está DISPONIBLE!");
        } else {
            System.out.println("Error: Verifique que la placa sea correcta.");
        }
    }

    
    private static void eliminarAmbulancia() {
        System.out.println("--- ELIMINAR AMBULANCIA (DELETE) ---");
        System.out.println("Nota: Revisa el reporte primero para ver el ID numérico.");
        System.out.print("Ingrese el ID de la ambulancia a borrar: ");
        int id = leerOpcion();
        
 
        gestor.eliminarAmbulancia(id);
    }

    
    private static void menuInventario() {
        System.out.println(" ____________________________________________________________");
        System.out.println("|                  GESTION DE INVENTARIO                     |");
        System.out.println("|____________________________________________________________|");
        System.out.println("|  1. Ver stock de insumos (READ)                            |");
        System.out.println("|  2. Agregar stock a un insumo (UPDATE)                     |");
        System.out.println("|  3. Eliminar insumo (DELETE)                               |");
        System.out.println("|  0. Volver al menu principal                               |");
        System.out.println("|____________________________________________________________|");
        System.out.print("Seleccione una opcion: ");

        int opcion = leerOpcion();
        System.out.println();

        switch (opcion) {
            case 1 -> gestor.generarReporte();
            case 2 -> agregarStock();
            case 3 -> eliminarInsumo(); 
            case 0 -> { return; }
            default -> System.out.println("Opcion invalida.");
        }
        esperarEnter();
    }

private static void agregarStock() {
        System.out.println("--- AGREGAR STOCK A INSUMO (UPDATE) ---\n");
        
        
        scanner.nextLine(); // Limpiar el buffer del teclado
        System.out.print("Ingrese el CODIGO del insumo (ej. INS001): ");
        String codigo = scanner.nextLine().toUpperCase();

        System.out.print("Cantidad a ingresar al almacén: ");
        int cantidad = leerOpcion();

       
        if (cantidad > 0) {
            boolean exito = gestor.sumarStock(codigo, cantidad);
            if (exito) {
                System.out.println("¡Entrada de almacén registrada con éxito!");
            }
        } else {
            System.out.println("La cantidad debe ser mayor a 0.");
        }
    }

    
    private static void eliminarInsumo() {
        System.out.println("--- ELIMINAR INSUMO (DELETE) ---");
        System.out.println("Nota: Revisa el reporte primero para ver el ID numérico.");
        System.out.print("Ingrese el ID del insumo a borrar: ");
        int id = leerOpcion();
        
        gestor.eliminarInsumo(id);
    }

    
    private static void menuPedidos() {
        System.out.println(" ____________________________________________________________");
        System.out.println("|                  GESTION DE PEDIDOS                        |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("|  1. Crear nuevo pedido                                     |");
        System.out.println("|  0. Volver al menu principal                               |");
        System.out.println("|____________________________________________________________|");
        System.out.print("Seleccione una opcion: ");

        int opcion = leerOpcion();
        System.out.println();

        switch (opcion) {
            case 1 -> crearPedido();
            case 0 -> { return; }
            default -> System.out.println("Opcion invalida.");
        }
        esperarEnter();
    }

    private static void crearPedido() {
        System.out.println("--- CREAR NUEVO PEDIDO ---\n");
        
        System.out.print("Departamento solicitante: ");
        scanner.nextLine(); 
        String departamento = scanner.nextLine();

        System.out.println("\nEjemplos de codigos: INS001, INS002, INS003...");
        System.out.print("Codigo del insumo: ");
        String codigo = scanner.nextLine().toUpperCase();

        System.out.print("Cantidad solicitada: ");
        int cantidad = leerOpcion();

        System.out.println("\n--- PROCESANDO PEDIDO ---");
        Pedido pedido = gestor.crearPedido(departamento, codigo, cantidad);
        
        if (pedido != null) {
            if(pedido.isAprobado()){
                System.out.println("PEDIDO APROBADO y stock actualizado en BD!");
                System.out.println(pedido);
            } else {
                System.out.println("PEDIDO RECHAZADO (Stock insuficiente).");
            }
        }
    }

    
    private static int leerOpcion() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); 
            return -1;
        }
    }

    private static void esperarEnter() {
        System.out.println("\nPresione ENTER para continuar...");
        try {
            System.in.read();
            scanner.nextLine(); 
        } catch (Exception e) {}
    }
}