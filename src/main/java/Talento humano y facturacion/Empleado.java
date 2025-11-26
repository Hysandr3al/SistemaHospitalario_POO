/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitaltalentofinanzas;

import java.util.Date;

public class Empleado {
    private String idEmpleado;
    private String nombre;
    private String apellido;
    private String puesto;
    private Date fechaIngreso;
    private boolean activo;
    private double salarioBase;

    public Empleado(String idEmpleado, String nombre, String apellido, 
                   String puesto, Date fechaIngreso, double salarioBase) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.fechaIngreso = fechaIngreso;
        this.activo = true;
        this.salarioBase = salarioBase;
    }

    public String getIdEmpleado() { return idEmpleado; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getPuesto() { return puesto; }
    public Date getFechaIngreso() { return fechaIngreso; }
    public boolean isActivo() { return activo; }
    public double getSalarioBase() { return salarioBase; }
}
