/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitaltalentofinanzas;

import java.util.Date;

public class Factura {
    private String idFactura;
    private String pacienteId;
    private String procedimiento;
    private double montoTotal;
    private double copago;
    private String obraSocial;
    private Date fecha;

    public Factura(String idFactura, ActoMedico actoMedico, double copago) {
        this.idFactura = idFactura;
        this.pacienteId = actoMedico.getPacienteId();
        this.procedimiento = actoMedico.getProcedimiento();
        this.montoTotal = actoMedico.getCosto();
        this.obraSocial = actoMedico.getObraSocial();
        this.copago = copago;
        this.fecha = new Date();
    }

    public String getIdFactura() { return idFactura; }
    public String getPacienteId() { return pacienteId; }
    public String getProcedimiento() { return procedimiento; }
    public double getMontoTotal() { return montoTotal; }
    public double getCopago() { return copago; }
    public String getObraSocial() { return obraSocial; }
    public Date getFecha() { return fecha; }
}
