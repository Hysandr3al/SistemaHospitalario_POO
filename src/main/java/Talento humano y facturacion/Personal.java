/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitaltalentofinanzas;

import Sujetos.Persona;
import java.util.Date;

public class Empleado extends Persona {
    private String idEmpleado;
    private String puesto;
    private Date fechaIngreso;
    private boolean activo;
    private double salarioBase;

    public Empleado(String idEmpleado, String nombres, String apellidos, 
                   String telefono, String dni, String sexo, int edad,
                   String puesto, Date fechaIngreso, double salarioBase) {
        super(nombres, apellidos, telefono, dni, sexo, edad);
        this.idEmpleado = idEmpleado;
        this.puesto = puesto;
        this.fechaIngreso = fechaIngreso;
        this.activo = true;
        this.salarioBase = salarioBase;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("=== INFORMACIÓN DEL EMPLEADO ===");
        System.out.println("Empleado: " + nombres + " " + apellidos);
        System.out.println("DNI: " + dni + " | ID Empleado: " + idEmpleado);
        System.out.println("Puesto: " + puesto + " | Salario Base: $" + salarioBase);
        System.out.println("Fecha de Ingreso: " + fechaIngreso);
        System.out.println("Estado: " + (activo ? "ACTIVO" : "INACTIVO"));
        System.out.println("Teléfono: " + telefono + " | Sexo: " + sexo + " | Edad: " + edad + " años");
        System.out.println("=================================");
    }

    // Getters
    public String getIdEmpleado() {
        return idEmpleado;
    }

    public String getPuesto() {
        return puesto;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public boolean isActivo() {
        return activo;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    // Setters
    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }
    
    // Métodos adicionales
    public int calcularAntiguedad() {
        if (fechaIngreso == null) return 0;
        Date ahora = new Date();
        long diffInMillis = ahora.getTime() - fechaIngreso.getTime();
        return (int) (diffInMillis / (1000L * 60 * 60 * 24 * 365));
    }
    
    public void desactivarEmpleado() {
        this.activo = false;
    }
    
    public void activarEmpleado() {
        this.activo = true;
    }
}
