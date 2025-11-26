/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitaltalentofinanzas;

import java.util.*;

public class Facturacion {
    private Map<String, Factura> facturas;
    private Map<String, Map<String, Object>> obrasSociales;
    private Map<String, List<Factura>> cuentasMedicas;

    public Facturacion() {
        this.facturas = new HashMap<>();
        this.obrasSociales = new HashMap<>();
        this.cuentasMedicas = new HashMap<>();
        inicializarObrasSociales();
    }

    private void inicializarObrasSociales() {
        agregarObraSocial("OSDE", 0.8, 30);
        agregarObraSocial("SWISS_MEDICAL", 0.75, 45);
        agregarObraSocial("GALENO", 0.7, 60);
        agregarObraSocial("PREPAGA", 0.9, 15);
        agregarObraSocial("PARTICULAR", 0.0, 0);
    }

    private void agregarObraSocial(String nombre, double cobertura, int diasPago) {
        Map<String, Object> obraSocial = new HashMap<>();
        obraSocial.put("cobertura", cobertura);
        obraSocial.put("diasPago", diasPago);
        obrasSociales.put(nombre, obraSocial);
    }

    public void recibirActoMedico(ActoMedico actoMedico) {
        Factura factura = generarFactura(actoMedico);
        facturas.put(factura.getIdFactura(), factura);
        actualizarCuentaMedica(actoMedico.getPacienteId(), factura);
    }

    private Factura generarFactura(ActoMedico actoMedico) {
        String facturaId = "F" + (facturas.size() + 1);
        String obraSocial = actoMedico.getObraSocial();
        double cobertura = obrasSociales.containsKey(obraSocial) ? 
                          (double) obrasSociales.get(obraSocial).get("cobertura") : 0.0;
        double copago = actoMedico.getCosto() * (1 - cobertura);
        
        return new Factura(facturaId, actoMedico, copago);
    }

    private void actualizarCuentaMedica(String pacienteId, Factura factura) {
        if (!cuentasMedicas.containsKey(pacienteId)) {
            cuentasMedicas.put(pacienteId, new ArrayList<>());
        }
        cuentasMedicas.get(pacienteId).add(factura);
    }

    public Map<String, Factura> getFacturas() { return facturas; }
    public Map<String, List<Factura>> getCuentasMedicas() { return cuentasMedicas; }
}
