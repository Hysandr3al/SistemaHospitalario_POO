package Farmacia;

import java.util.Scanner;

import dao.* ;

public class MenuFarmacia {

    static Scanner sc = new Scanner(System.in);
    static int opcion;

    static MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
    static RecetaMedicaHospitalDAO recetaHospitalDAO = new RecetaMedicaHospitalDAO();
    static RecetaMedicaExternaDAO recetaExternaDAO = new RecetaMedicaExternaDAO();
    static DetalleMedicamentoDAO detalleMedicamentoDAO = new DetalleMedicamentoDAO();


    public void mostrarMenuFarmacia(){
        do{
            System.out.println("\n============= FARMACIA =============");
            System.out.println("1. Gestión de medicamentos");
            System.out.println("2. Ventas / Recetas");
            System.out.println("3. Reportes");
            System.out.println("4. Salir");

            opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> MenuMedicamentos();
                case 2 -> MenuVentas();
                case 3 -> MenuReportes();
                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción invalida");
            }
        } while (opcion !=4);
        sc.nextLine();
    }

    public static void  MenuMedicamentos(){
        do{
            System.out.println("\n===== GESTION DE MEDICAMENTOS =====");
            System.out.println("1. Buscar medicamentos");
            System.out.println("2. Agregar medicamento");
            System.out.println("3. Editar medicamento");
            System.out.println("4. Eliminar medicamento");
            System.out.println("5. Ver lista de medicamentos");
            System.out.println("6. Actualizar stock");
            System.out.println("7. Mostrar medicamentos con bajo stock");
            System.out.println("8. Salir");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese ID o nombre a buscar: ");
                    int buscar = sc.nextInt();
                    medicamentoDAO.buscarPorId(buscar);
                    }
                case 2 -> {
                    Medicamento m = new Medicamento();
                    System.out.print("Nombre: ");
                    m.setNombre(sc.nextLine());
                    System.out.print("Stock: ");
                    m.setStock(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Precio: ");
                    m.setPrecio(sc.nextDouble());
                    sc.nextLine();

                    medicamentoDAO.registrar(m);
                }
                case 3 -> {
                    System.out.print("ID del medicamento a editar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    medicamentoDAO.modificar(id);
                    }
                case 4 -> {
                    System.out.print("ID del medicamento a eliminar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    medicamentoDAO.eliminar(id);
                }
                case 5 -> medicamentoDAO.listar();

                case 6 -> {
                    System.out.print("ID del medicamento: ");
                    int id = sc.nextInt();
                    System.out.print("Nuevo stock: ");
                    int nuevoStock = sc.nextInt();
                    medicamentoDAO.actualizarStock(id, nuevoStock);
                }
                case 7 -> medicamentoDAO.listarBajoStock();
                case 8 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 8);
    }

    public static void MenuVentas(){
        do {
            System.out.println("\n===== VENTAS / RECETAS =====");
            System.out.println("1. Venta con receta del hospital");
            System.out.println("2. Venta con receta externa");
            System.out.println("3. Venta sin receta ");
            System.out.println("4. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> venderRecetaHospital();
                case 2 -> venderRecetaExterna();
                case 3 -> venderSinReceta();
                case 4 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 4 );   
    }
    private static void venderRecetaHospital() {
        System.out.print("ID receta hospital: ");
        int idReceta = sc.nextInt();
        sc.nextLine();

        var receta = recetaHospitalDAO.buscarPorId(idReceta);
        if (receta == null) {
        System.out.println(" La receta no existe.");
        return;
        }

        System.out.println("✔ Receta encontrada:");
        System.out.println("ID Doctor: " + receta.getDoctor().getIdDoctor());
        System.out.println("ID Paciente: " + receta.getPaciente().getIdPaciente());

        var detalles = detalleMedicamentoDAO.listar(idReceta);

        if (detalles.isEmpty()) {
        System.out.println("La receta no tiene medicamentos asignados");
        return;
        }

        System.out.println("\nMedicamentos prescritos:");
        for (var det : detalles) {
            System.out.println("- ID medicamento: " + det.getMedicamento().getIdMedicamento()
                + " | Cantidad: " + det.getCantidad());
        }

        // Confirmación
        System.out.print("\n¿Desea procesar esta venta? (s/n): ");
        String conf = sc.nextLine();

        if (!conf.equalsIgnoreCase("s")) {
        System.out.println("Venta cancelada.");
        return;
        }

        // Procesar descuento de stock
        boolean errorStock = false;

        for (var det : detalles) {
            int idMed = det.getMedicamento().getIdMedicamento();
            int cant = det.getCantidad();

            boolean ok = medicamentoDAO.descontarStock(idMed, cant);

            if (!ok) {
            System.out.println(" No hay suficiente stock del medicamento ID: " + idMed);
            errorStock = true;
            }
        }

        if (errorStock) {
            System.out.println("\n Venta no completada por falta de stock.");
        } else { System.out.println("\n Venta completada con éxito.");}
    }
    private static void venderRecetaExterna() {
        System.out.print("ID receta externa: ");
        int idReceta = sc.nextInt();
        sc.nextLine();

        var receta = recetaExternaDAO.buscarPorID(idReceta);
        if (receta == null) {
        System.out.println(" La receta externa no existe.");
        return;
        }

        System.out.println("\nReceta externa encontrada:");
        System.out.println("Cliente: " + receta.getNombreCliente());
        System.out.println("DNI: " + receta.getDniCliente());
        // Obtener detalles
        var detalles = detalleMedicamentoDAO.listar(idReceta);

        if (detalles.isEmpty()) {
            System.out.println("Esta receta no tiene medicamentos registrados.");
            return;
        }

        System.out.println("\nMedicamentos prescritos:");
        for (var d : detalles) {
        System.out.println("- ID medicamento: " + d.getMedicamento().getIdMedicamento()
                + " | Cantidad: " + d.getCantidad());
        }

        System.out.print("\n¿Desea procesar esta venta? (s/n): ");
        String conf = sc.nextLine();

        if (!conf.equalsIgnoreCase("s")) {
            System.out.println("Venta cancelada.");
            return;
        }

        boolean errorStock = false;
        // Descontar stock por cada medicamento
        for (var det : detalles) {
            int idMed = det.getMedicamento().getIdMedicamento();
            int cantidad = det.getCantidad();
            boolean ok = medicamentoDAO.descontarStock(idMed, cantidad);

            if (!ok) {
            System.out.println("Stock insuficiente para el medicamento ID: " + idMed);
            errorStock = true;
            }
        }

    if (errorStock) {
        System.out.println("\nVenta NO completada por falta de stock.");
    } else {
        System.out.println("\n✔ Venta completada con éxito.");
    }
}
    private static void venderSinReceta() {
    System.out.print("ID medicamento: ");
    int id = sc.nextInt();

    System.out.print("Cantidad: ");
    int cantidad = sc.nextInt();
    sc.nextLine();

    boolean ok = medicamentoDAO.venderDirecto(id, cantidad);

    if (ok) {
        System.out.println("Venta realizada correctamente.");
    } else {
        System.out.println("No se pudo realizar la venta. Stock insuficiente o medicamento inexistente.");
    }
}


    public static void MenuReportes(){
        do {
            System.out.println("1. Ver historial de ventas");
            System.out.println("2. Ver medicamentos más vendidos");
            System.out.println("3. Salir");
        } while (opcion != 3);
        sc.nextLine();
    }
}
