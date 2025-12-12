package Farmacia;

import java.util.Scanner;
import java.util.List;
import dao.MedicamentoDAO;

public class MenuFarmacia {

    private static final Scanner sc = new Scanner(System.in);
    private static final MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n=== MODULO DE FARMACIA ===");
            System.out.println("1. Ver inventario de medicamentos (Desde BD)");
            System.out.println("2. Registrar nuevo medicamento");
            System.out.println("3. Actualizar stock/precio");
            System.out.println("4. Eliminar medicamento");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> listarMedicamentos();
                case 2 -> registrarMedicamento();
                case 3 -> actualizarMedicamento();
                case 4 -> eliminarMedicamento();
                case 0 -> System.out.println("Saliendo de Farmacia...");
                default -> System.out.println("[!] Opcion no valida.");
            }
        } while (opcion != 0);
        
        
    }

    
    private static void listarMedicamentos() {
        System.out.println("\n--- INVENTARIO DE FARMACIA ---");
        List<Medicamento> lista = medicamentoDAO.listar();
        
        if (lista.isEmpty()) {
            System.out.println("[!] No hay medicamentos registrados.");
        } else {
            for (Medicamento m : lista) {
                System.out.println("ID: " + m.getIdMedicamento() + 
                                   " | Nombre: " + m.getNombre() + 
                                   " | Tipo: " + m.getTipo() + 
                                   " | Stock: " + m.getStock() + 
                                   " | Precio: $" + m.getPrecio());
            }
        }
        esperarEnter();
    }

    
    private static void registrarMedicamento() {
        System.out.println("\n--- NUEVO MEDICAMENTO ---");
        
        System.out.print("Nombre comercial: ");
        String nombre = sc.nextLine();
        
        System.out.print("Tipo (Analgesico, Antibiotico, etc.): ");
        String tipo = sc.nextLine();
        
        System.out.print("Precio unitario: ");
        double precio = leerDouble();
        
        System.out.print("Stock inicial: ");
        int stock = leerEntero();
        
        System.out.print("Fecha Vencimiento (YYYY-MM-DD): ");
        String fecha = sc.nextLine();
        
        // --- EL PARCHE ANTI-BUG ---
        // Si la fecha se saltó (está vacía por el Enter anterior), la pedimos de nuevo.
        if (fecha.isEmpty()) {
            fecha = sc.nextLine();
        }
        // -------------------------

        Medicamento m = new Medicamento(0, nombre, tipo, precio, stock, fecha, 0.0);

        if (medicamentoDAO.registrar(m)) {
            System.out.println("[OK] Medicamento registrado en SQL Server.");
        } else {
            System.out.println("[ERROR] No se pudo registrar.");
        }
        esperarEnter();
    }

   
    private static void actualizarMedicamento() {
        System.out.println("\n--- ACTUALIZAR MEDICAMENTO ---");
        listarMedicamentos(); // Mostramos lista para ver IDs
        
        System.out.print("Ingrese el ID del medicamento a modificar: ");
        int id = leerEntero();
        
        Medicamento m = medicamentoDAO.buscarPorId(id);
        
        if (m != null) {
            System.out.println("Editando: " + m.getNombre());
            System.out.println("Deje en 0 o vacio si no quiere cambiar el valor.");
            
            System.out.print("Nuevo Precio (Actual: " + m.getPrecio() + "): ");
            double nuevoPrecio = leerDouble();
            if (nuevoPrecio > 0) m.setPrecio(nuevoPrecio);
            
            System.out.print("Nuevo Stock (Actual: " + m.getStock() + "): ");
            int nuevoStock = leerEntero();
            if (nuevoStock > 0) m.setStock(nuevoStock);
            
            
            if (medicamentoDAO.modificar(m)) {
                System.out.println("[OK] Datos actualizados correctamente.");
            } else {
                System.out.println("[ERROR] Fallo al actualizar en BD.");
            }
        } else {
            System.out.println("[!] Medicamento no encontrado.");
        }
        esperarEnter();
    }

    
    private static void eliminarMedicamento() {
        System.out.println("\n--- ELIMINAR MEDICAMENTO ---");
        System.out.print("Ingrese el ID a eliminar: ");
        int id = leerEntero();
        
        if (medicamentoDAO.eliminar(id)) {
            System.out.println("[OK] Medicamento eliminado.");
        } else {
            System.out.println("[ERROR] No se pudo eliminar (Verifique el ID).");
        }
        esperarEnter();
    }

    
    private static int leerEntero() {
        try {
            int i = Integer.parseInt(sc.nextLine());
            return i;
        } catch (Exception e) {
            return -1;
        }
    }
    
    private static double leerDouble() {
        try {
            double d = Double.parseDouble(sc.nextLine());
            return d;
        } catch (Exception e) {
            return -1.0;
        }
    }

    private static void esperarEnter() {
        System.out.println("\nPresione ENTER para continuar...");
        try { System.in.read(); } catch(Exception e){}
    }
}