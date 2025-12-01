package hospital.pacienteepisodio;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuEpisodio {

    private static Scanner sc = new Scanner(System.in);

    // Episodios almacenados temporalmente (sin BD todavía)
    private static ArrayList<Episodio> episodios = new ArrayList<>();

    // DAOs independientes
    private static CitaAmbulatoriaDAO citaDAO = new CitaAmbulatoriaDAO();
    private static HospitalizacionDAO hospDAO = new HospitalizacionDAO();
    private static CirugiaDAO cirDAO = new CirugiaDAO();
    private static UrgenciaDAO urgDAO = new UrgenciaDAO();

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n=== CRUD DE EPISODIOS (SIN EpisodioListDAO) ===");
            System.out.println("1. Registrar episodio");
            System.out.println("2. Listar episodios");
            System.out.println("3. Editar descripción de un episodio");
            System.out.println("4. Eliminar episodio");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> registrarEpisodio();
                case 2 -> listarEpisodios();
                case 3 -> editarEpisodio();
                case 4 -> eliminarEpisodio();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    // ---------------------------------------------------------
    // REGISTRAR
    // ---------------------------------------------------------
    private static void registrarEpisodio() {

        System.out.println("\n--- Elegir tipo de episodio ---");
        System.out.println("1. Cita Ambulatoria");
        System.out.println("2. Hospitalización");
        System.out.println("3. Cirugía");
        System.out.println("4. Urgencia");
        System.out.print("Tipo: ");

        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.print("Descripción: ");
        String desc = sc.nextLine();

        Episodio epi = null;

        switch (tipo) {
            case 1 -> {
                epi = new CitaAmbulatoria(desc);
                citaDAO.registrar((CitaAmbulatoria) epi);
            }
            case 2 -> {
                System.out.print("Número de cama: ");
                int cama = sc.nextInt();
                sc.nextLine();
                epi = new Hospitalizacion(desc, cama);
                hospDAO.registrar((Hospitalizacion) epi);
            }
            case 3 -> {
                System.out.print("Tipo de cirugía: ");
                String tipoC = sc.nextLine();
                epi = new Cirugia(desc, tipoC);
                cirDAO.registrar((Cirugia) epi);
            }
            case 4 -> {
                System.out.print("Triage: ");
                String tri = sc.nextLine();
                epi = new Urgencia(desc, tri);
                urgDAO.registrar((Urgencia) epi);
            }
            default -> {
                System.out.println("Tipo no válido.");
                return;
            }
        }

        episodios.add(epi);

        System.out.println("Episodio registrado.");
    }

    // ---------------------------------------------------------
    // LISTAR
    // ---------------------------------------------------------
    private static void listarEpisodios() {
        System.out.println("\n--- LISTA DE EPISODIOS ---");

        if (episodios.isEmpty()) {
            System.out.println("No hay episodios registrados.");
            return;
        }

        int i = 1;
        for (Episodio e : episodios) {
            System.out.println(i + ". " + e);
            i++;
        }
    }

    // ---------------------------------------------------------
    // EDITAR
    // ---------------------------------------------------------
    private static void editarEpisodio() {
        listarEpisodios();

        if (episodios.isEmpty()) return;

        System.out.print("\nSeleccione número: ");
        int pos = sc.nextInt();
        sc.nextLine();

        if (pos < 1 || pos > episodios.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Episodio e = episodios.get(pos - 1);

        System.out.print("Nueva descripción: ");
        String nueva = sc.nextLine();

        e.descripcion = nueva;

        System.out.println("Episodio actualizado.");
    }

    // ---------------------------------------------------------
    // ELIMINAR
    // ---------------------------------------------------------
    private static void eliminarEpisodio() {
        listarEpisodios();

        if (episodios.isEmpty()) return;

        System.out.print("\nNúmero a eliminar: ");
        int pos = sc.nextInt();
        sc.nextLine();

        if (pos < 1 || pos > episodios.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        episodios.remove(pos - 1);

        System.out.println("Episodio eliminado.");
    }
}