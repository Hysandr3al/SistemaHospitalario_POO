package Farmacia;

import java.util.Scanner;

public class MenuFarmacia {

    static Scanner sc = new Scanner(System.in);
    static int opcion;


    public void mostrarMenuFarmacia(){
        do{
            System.out.println("============= FARMACIA =============");
            System.out.println("1. Gestión de medicamentos");
            System.out.println("2. Ventas / Recetas");
            System.out.println("3. Reportes");
            System.out.println("4. Salir");

            opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> MenuMedicamentos();
                case 2 -> MenuVentas();
                case 3 -> MenuReportes();
                default -> System.out.println("Opción invalida");
            }
        } while (opcion !=4);
        sc.nextLine();
    }

    public static void  MenuMedicamentos(){
        do{
            System.out.println("===== GESTION DE MEDICAMENTOS =====");
            System.out.println("1. Buscar medicamentos");
            System.out.println("2. Agregar medicamento");
            System.out.println("3. Editar medicamento");
            System.out.println("4. Eliminar medicamento");
            System.out.println("5. Ver lista de medicamentos");
            System.out.println("6. Actualizar stock");
            System.out.println("7. Mostrar medicamentos con bajo stock");
            System.out.println("8. Salir");
            opcion = sc.nextInt();

        } while (opcion != 8);
        sc.nextLine();
    }

    public static void MenuVentas(){
        do {
            System.out.println("1. Venta con receta del hospital");
            System.out.println("2. Venta con receta externa");
            System.out.println("3. Venta sin receta ");
            System.out.println("4. Salir");

        } while (opcion != 4 );
        sc.nextLine();
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
