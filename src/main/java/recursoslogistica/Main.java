package recursoslogistica;

import recursoslogistica.ambulancias.*;
import recursoslogistica.inventario.*;
import recursoslogistica.mantenimiento.*;
import recursoslogistica.seguridadhigiene.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Sistema Hospitalario: Módulo C - Recursos y Logística ===\n");

        System.out.println("--- GESTIÓN DE AMBULANCIAS ---");
        
        Ambulancia amb1 = new Ambulancia("AMB001", "ABC-123", TipoAmbulancia.MEDICALIZADA);
        amb1.agregarTripulante(new Tripulante("Dr. Juan Pérez", "12345678", RolTripulante.MEDICO));
        amb1.agregarTripulante(new Tripulante("Carlos Gómez", "87654321", RolTripulante.CONDUCTOR));
        amb1.actualizarUbicacion(-12.0464, -77.0428);
        
        Corrida911 corrida1 = new Corrida911("C001", "Av. Arequipa 1234", "Accidente de tránsito");
        corrida1.asignarAmbulancia(amb1);
        corrida1.iniciarCorrida();
        
        System.out.println(amb1);
        System.out.println(corrida1);
        System.out.println();

        System.out.println("--- GESTIÓN DE INVENTARIO ---");
        
        Insumo guantes = new Insumo("INS001", "Guantes de látex", 
                                     CategoriaInsumo.DESECHABLE, 500, 100, "pares");
        Insumo oxigeno = new Insumo("INS002", "Tanque de oxígeno", 
                                     CategoriaInsumo.OXIGENO, 15, 5, "unidades");
        Insumo jeringa = new Insumo("INS003", "Jeringa 10ml", 
                                     CategoriaInsumo.DESECHABLE, 80, 100, "unidades");
        
        System.out.println(guantes);
        System.out.println(oxigeno);
        System.out.println(jeringa);
        System.out.println();
        
        PedidoInsumo pedido1 = new PedidoInsumo("PED001", "Módulo A - Urgencias", guantes, 50);
        PedidoInsumo pedido2 = new PedidoInsumo("PED002", "Módulo A - UCI", oxigeno, 3);
        
        System.out.println("Procesando pedidos...");
        pedido1.procesarPedido();
        pedido2.procesarPedido();
        
        System.out.println(pedido1);
        System.out.println(pedido2);
        System.out.println("\nStock actualizado:");
        System.out.println(guantes);
        System.out.println(oxigeno);
        System.out.println();

        System.out.println("--- GESTIÓN DE MANTENIMIENTO ---");
        
        EquipoBiomedico ventilador = new EquipoBiomedico("EQ001", "Ventilador Mecánico Drager", 
                                                          TipoEquipo.VENTILADOR, "UCI - Sala 3", 90);
        EquipoBiomedico desfibrilador = new EquipoBiomedico("EQ002", "Desfibrilador Philips", 
                                                             TipoEquipo.DESFIBRILADOR, "Urgencias", 180);
        
        System.out.println(ventilador);
        System.out.println(desfibrilador);
        System.out.println();
        
        OrdenTrabajo orden1 = new OrdenTrabajo("OT001", ventilador, 
                                                TipoMantenimiento.PREVENTIVO, 
                                                "Revisión trimestral programada");
        orden1.asignarTecnico("Ing. Roberto Silva");
        orden1.iniciarTrabajo();
        orden1.finalizar();
        
        System.out.println(orden1);
        System.out.println("Estado del equipo después del mantenimiento:");
        System.out.println(ventilador);
        System.out.println();

        System.out.println("--- GESTIÓN DE SEGURIDAD E HIGIENE ---");
        
        ResiduoPeligroso residuo1 = new ResiduoPeligroso("RES001", TipoResiduo.INFECCIOSO, 
                                                          2.5, "Quirófano 2");
        ResiduoPeligroso residuo2 = new ResiduoPeligroso("RES002", TipoResiduo.PUNZOCORTANTE, 
                                                          0.8, "Laboratorio");
        
        residuo1.recolectar();
        residuo1.transportar();
        residuo1.tratar();
        residuo1.disponer();
        
        System.out.println(residuo1);
        System.out.println(residuo2);
        System.out.println();
        
        ProtocoloBioseguridad protocolo1 = new ProtocoloBioseguridad(
            "PROTO001", 
            "Manejo de material contaminado COVID-19",
            "Protocolo para manipulación segura de material potencialmente infectado",
            NivelRiesgo.ALTO
        );
        
        System.out.println(protocolo1);
        
        System.out.println("\n=== Fin de la demostración del Módulo C ===");
    }
}