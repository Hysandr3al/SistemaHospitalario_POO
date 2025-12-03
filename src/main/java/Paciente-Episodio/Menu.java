package pacienteepisodio;

import dao.PacienteDAO;
import dao.EpisodioDAO;

import Sujetos.Paciente;
import hospital.pacienteepisodio.*;

import java.util.Scanner;

public class Menu {

    private static final Scanner sc = new Scanner(System.in);

    // DAOs
    private static final PacienteDAO pacienteDAO = new PacienteDAO();
    private static final EpisodioDAO episodioDAO = new EpisodioDAO();

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n=== SISTEMA HOSPITALARIO – MÓDULO PACIENTE & EPISODIOS ===");
            System.out.println("1. Registrar Paciente (BD)");
            System.out.println("2. Buscar Paciente por ID");
            System.out.println("3. Registrar Episodio (BD)");
            System.out.println("4. Mostrar Historia Clínica desde BD");
            System.out.println("0. Salir");
            System.out.print("Seleccione opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> registrarPacienteBD();
                case 2 -> buscarPacienteBD();
                case 3 -> registrarEpisodioBD();
                case 4 -> mostrarHistoriaClinicaBD();
                case 0 -> System.out.println("Cerrando sistema...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    // ============================================================
    // 1. Registrar Paciente en BD
    // ============================================================
    private static void registrarPacienteBD() {

        System.out.print("Nombres: ");
        String n = sc.nextLine();

        System.out.print("Apellidos: ");
        String a = sc.nextLine();

        System.out.print("Teléfono: ");
        String t = sc.nextLine();

        System.out.print("DNI: ");
        String dni = sc.nextLine();

        System.out.print("Sexo: ");
        String sexo = sc.nextLine();

        System.out.print("Edad: ");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.print("Grupo sanguíneo: ");
        String gs = sc.nextLine();

        System.out.print("Alergias: ");
        String al = sc.nextLine();

        System.out.print("Dirección: ");
        String dir = sc.nextLine();

        Paciente p = new Paciente(n, a, t, dni, sexo, edad, gs, al, 0, dir);

        if (pacienteDAO.registrar(p)) {
            System.out.println("✔ Paciente registrado en BD.");
        } else {
            System.out.println("✘ Error al registrar paciente.");
        }
    }

    // ============================================================
    // 2. Buscar paciente por ID
    // ============================================================
    private static Paciente buscarPacienteBD() {
        System.out.print("Ingrese ID del Paciente: ");
        int id = sc.nextInt();

        Paciente p = pacienteDAO.buscarPorId(id);

        if (p == null) {
            System.out.println("✘ Paciente no encontrado.");
        } else {
            System.out.println("✔ Paciente encontrado:");
            p.mostrarInfo();
        }

        return p;
    }

    // ============================================================
    // 3. Registrar un episodio en BD
    // ============================================================
    private static void registrarEpisodioBD() {

        Paciente p = buscarPacienteBD();

        if (p == null) return;

        System.out.println("\n--- Registrar Episodio ---");
        System.out.println("1. Cita Ambulatoria");
        System.out.println("2. Hospitalización");
        System.out.println("3. Cirugía");
        System.out.println("4. Urgencia");
        System.out.print("Seleccione tipo: ");

        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.print("Descripción: ");
        String desc = sc.nextLine();

        Episodio ep = null;

        switch (tipo) {
            case 1 -> ep = new CitaAmbulatoria(desc);

            case 2 -> {
                System.out.print("Número de cama: ");
                int cama = sc.nextInt();
                sc.nextLine();
                ep = new Hospitalizacion(desc, cama);
            }

            case 3 -> {
                System.out.print("Tipo de cirugía: ");
                String tc = sc.nextLine();
                ep = new Cirugia(desc, tc);
            }

            case 4 -> {
                System.out.print("Triage: ");
                String triage = sc.nextLine();
                ep = new Urgencia(desc, triage);
            }

            default -> {
                System.out.println("Tipo inválido.");
                return;
            }
        }

        if (episodioDAO.registrar(ep, p.getIdPaciente())) {
            System.out.println("✔ Episodio registrado en BD.");
        } else {
            System.out.println("✘ Error al registrar episodio.");
        }
    }

    // ============================================================
    // 4. Mostrar historia clínica desde BD
    // ============================================================
    private static void mostrarHistoriaClinicaBD() {

        Paciente p = buscarPacienteBD();
        if (p == null) return;

        System.out.println("\n=== HISTORIA CLÍNICA DE: " + p.getNombres() + " ===");

        var lista = episodioDAO.listarPorPaciente(p.getIdPaciente());

        if (lista.isEmpty()) {
            System.out.println("No hay episodios registrados.");
            return;
        }

        lista.forEach(ep -> System.out.println(" - " + ep));
    }
}