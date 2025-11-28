package Sujetos;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private static final Scanner sc = new Scanner(System.in);
    private static Paciente paciente;

    // Mapa para opciones del menú principal
    private static final Map<Integer, Runnable> acciones = new HashMap<>();

    public static void main(String[] args) {

        cargarAcciones();

        int opcion;

        do {
            mostrarMenu();

            opcion = leerEntero("Seleccione una opción: ");

            acciones.getOrDefault(opcion, () -> System.out.println("Opción no válida"))
                    .run();

        } while (opcion != 0);
    }

    // ================================
    // CONFIGURACIÓN DE ACCIONES
    // ================================
    private static void cargarAcciones() {
        acciones.put(1, Menu::registrarPaciente);
        acciones.put(2, Menu::mostrarPaciente);
        acciones.put(3, Menu::agregarEpisodio);
        acciones.put(4, Menu::mostrarHistoriaClinica);
        acciones.put(0, () -> System.out.println("Saliendo del sistema..."));
    }

    // ================================
    // MOSTRAR MENÚ
    // ================================
    private static void mostrarMenu() {
        System.out.println("\n=== SISTEMA HOSPITALARIO – MÓDULO PACIENTE Y EPISODIO ===");
        System.out.println("1. Registrar paciente");
        System.out.println("2. Ver información del paciente");
        System.out.println("3. Agregar episodio");
        System.out.println("4. Ver historia clínica");
        System.out.println("0. Salir");
    }

    // ================================
    // REGISTRAR PACIENTE
    // ================================
    private static void registrarPaciente() {
        System.out.println("\n--- Registro de Paciente ---");

        String nombres   = leerTexto("Nombres: ");
        String apellidos = leerTexto("Apellidos: ");
        String telefono  = leerTexto("Teléfono: ");
        String dni       = leerTexto("DNI: ");
        String sexo      = leerTexto("Sexo: ");
        int edad         = leerEntero("Edad: ");
        String gs        = leerTexto("Grupo sanguíneo: ");
        String alergias  = leerTexto("Alergias: ");

        paciente = new Paciente(nombres, apellidos, telefono, dni, sexo, edad, gs, alergias);

        System.out.println("Paciente registrado exitosamente.");
    }

    // ================================
    // MOSTRAR PACIENTE
    // ================================
    private static void mostrarPaciente() {
        if (paciente == null) {
            System.out.println("No hay paciente registrado.");
            return;
        }

        paciente.mostrarInfo();
    }

    // ================================
    // AGREGAR EPISODIO
    // ================================
    private static void agregarEpisodio() {

        if (paciente == null) {
            System.out.println("Primero debe registrar un paciente.");
            return;
        }

        System.out.println("\n--- Agregar Episodio ---");
        System.out.println("1. Cita Ambulatoria");
        System.out.println("2. Hospitalización");
        System.out.println("3. Cirugía");
        System.out.println("4. Urgencia");

        int tipo = leerEntero("Seleccione: ");

        String desc = leerTexto("Descripción del episodio: ");

        switch (tipo) {
            case 1 ->
                paciente.getHistoriaClinica().agregarEpisodio(new CitaAmbulatoria(desc));

            case 2 -> {
                int cama = leerEntero("Número de cama: ");
                paciente.getHistoriaClinica().agregarEpisodio(new Hospitalizacion(desc, cama));
            }

            case 3 -> {
                String tipoC = leerTexto("Tipo de cirugía: ");
                paciente.getHistoriaClinica().agregarEpisodio(new Cirugia(desc, tipoC));
            }

            case 4 -> {
                String triage = leerTexto("Triage: ");
                paciente.getHistoriaClinica().agregarEpisodio(new Urgencia(desc, triage));
            }

            default -> {
                System.out.println("Tipo no válido.");
                return;
            }
        }

        System.out.println("Episodio agregado correctamente.");
    }

    // ================================
    // MOSTRAR HISTORIA CLÍNICA
    // ================================
    private static void mostrarHistoriaClinica() {
        if (paciente == null) {
            System.out.println("No hay paciente registrado.");
            return;
        }

        System.out.println("\n" + paciente.getHistoriaClinica());

        paciente.getHistoriaClinica().getEpisodios()
                .forEach(ep -> System.out.println(" - " + ep));
    }

    // ================================
    // MÉTODOS UTILITARIOS
    // ================================
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }
}