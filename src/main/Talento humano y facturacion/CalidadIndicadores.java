/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitaltalentofinanzas;

import java.util.*;

public class CalidadIndicadores {
    private List<ActoMedico> actosMedicos;
    private Map<String, Object> indicadores;

    public CalidadIndicadores() {
        this.actosMedicos = new ArrayList<>();
        this.indicadores = new HashMap<>();
        inicializarIndicadores();
    }

    private void inicializarIndicadores() {
        indicadores.put("tiempoEsperaPromedio", 0.0);
        indicadores.put("tasaInfecciones", 0.0);
        indicadores.put("tasaMortalidad", 0.0);
        indicadores.put("totalPacientes", 0);
        indicadores.put("totalInfecciones", 0);
        indicadores.put("totalFallecimientos", 0);
        indicadores.put("tiemposEspera", new ArrayList<Integer>());
    }

    public void recibirActoMedico(ActoMedico actoMedico) {
        actosMedicos.add(actoMedico);
        calcularIndicadores(actoMedico);
    }

    private void calcularIndicadores(ActoMedico actoMedico) {
        int totalPacientes = (int) indicadores.get("totalPacientes") + 1;
        indicadores.put("totalPacientes", totalPacientes);

        if (actoMedico.isInfeccion()) {
            int totalInfecciones = (int) indicadores.get("totalInfecciones") + 1;
            indicadores.put("totalInfecciones", totalInfecciones);
        }

        if (actoMedico.isFallecimiento()) {
            int totalFallecimientos = (int) indicadores.get("totalFallecimientos") + 1;
            indicadores.put("totalFallecimientos", totalFallecimientos);
        }

        if (actoMedico.getTiempoEspera() > 0) {
            List<Integer> tiemposEspera = (List<Integer>) indicadores.get("tiemposEspera");
            tiemposEspera.add(actoMedico.getTiempoEspera());
        }

        actualizarEstadisticas();
    }

    private void actualizarEstadisticas() {
        List<Integer> tiemposEspera = (List<Integer>) indicadores.get("tiemposEspera");
        int totalPacientes = (int) indicadores.get("totalPacientes");
        int totalInfecciones = (int) indicadores.get("totalInfecciones");
        int totalFallecimientos = (int) indicadores.get("totalFallecimientos");

        if (!tiemposEspera.isEmpty()) {
            double promedio = tiemposEspera.stream().mapToInt(Integer::intValue).average().orElse(0.0);
            indicadores.put("tiempoEsperaPromedio", promedio);
        }

        if (totalPacientes > 0) {
            double tasaInfecciones = (double) totalInfecciones / totalPacientes * 100;
            indicadores.put("tasaInfecciones", tasaInfecciones);
        }

        if (totalPacientes > 0) {
            double tasaMortalidad = (double) totalFallecimientos / totalPacientes * 100;
            indicadores.put("tasaMortalidad", tasaMortalidad);
        }
    }

    public Map<String, Object> getIndicadores() { return indicadores; }
    public List<ActoMedico> getActosMedicos() { return actosMedicos; }
}
