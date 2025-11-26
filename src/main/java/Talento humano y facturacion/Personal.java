/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TalentoHumano;

import java.util.*;
import Sujetos.Empleado;  
import java.util.Date;

public class Personal {
    private Map<String, Empleado> empleados;
    private Map<String, List<String>> legajos;
    private Map<String, List<String>> licencias;
    private Map<String, List<String>> guardias;

    public Personal() {
        this.empleados = new HashMap<>();
        this.legajos = new HashMap<>();
        this.licencias = new HashMap<>();
        this.guardias = new HashMap<>();
    }
    public void altaEmpleado(String idEmpleado, String nombres, String apellidos, 
                           String telefono, String dni, String sexo, int edad,
                           String puesto, Date fechaIngreso, double salarioBase) {
        Empleado nuevoEmpleado = new Empleado(idEmpleado, nombres, apellidos, 
                                            telefono, dni, sexo, edad,
                                            puesto, fechaIngreso, salarioBase);
        empleados.put(idEmpleado, nuevoEmpleado);
        legajos.put(idEmpleado, new ArrayList<>());
        licencias.put(idEmpleado, new ArrayList<>());
        guardias.put(idEmpleado, new ArrayList<>());
        agregarAlLegajo(idEmpleado, "ALTA: Empleado dado de alta");
    }

    // ✅ Mantener método viejo para compatibilidad (OPCIONAL)
    public void altaEmpleadoBasico(String idEmpleado, String nombre, String apellido, 
                                 String puesto, Date fechaIngreso, double salarioBase) {
        // Usar valores por defecto para los campos de Persona
        altaEmpleado(idEmpleado, nombre, apellido, "", "", "", 0, 
                    puesto, fechaIngreso, salarioBase);
    }

    // Los demás métodos se mantienen igual...
    public void registrarLicencia(String idEmpleado, String tipoLicencia, 
                                Date fechaInicio, Date fechaFin, String motivo) {
        if (!empleados.containsKey(idEmpleado)) return;
        
        String licencia = String.format("%s|%s|%s|%s", tipoLicencia, fechaInicio, fechaFin, motivo);
        licencias.get(idEmpleado).add(licencia);
        agregarAlLegajo(idEmpleado, "LICENCIA: " + tipoLicencia);
    }

    public void asignarGuardia(String idEmpleado, Date fecha, String turno, String sector) {
        if (!empleados.containsKey(idEmpleado)) return;
        
        String guardia = String.format("%s|%s|%s", fecha, turno, sector);
        guardias.get(idEmpleado).add(guardia);
        agregarAlLegajo(idEmpleado, "GUARDIA: " + turno + " - " + sector);
    }

    private void agregarAlLegajo(String idEmpleado, String registro) {
        legajos.get(idEmpleado).add(registro + " - " + new Date());
    }

    public Map<String, Empleado> getEmpleados() { return empleados; }
    public Map<String, List<String>> getLicencias() { return licencias; }
    public Map<String, List<String>> getGuardias() { return guardias; }
    public Map<String, List<String>> getLegajos() { return legajos; }
}
