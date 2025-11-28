package recursoslogistica.ambulancias;

import recursoslogistica.Recurso;
import recursoslogistica.EstadoRecurso;
import java.util.ArrayList;

public class Ambulancia extends Recurso {
    private String placa;
    private TipoAmbulancia tipo;
    private ArrayList<Tripulante> tripulacion;
    private CoordenadaGPS ubicacionActual;

    public Ambulancia(String codigo, String placa, TipoAmbulancia tipo) {
        super(codigo, "Ambulancia " + placa);
        this.placa = placa;
        this.tipo = tipo;
        this.tripulacion = new ArrayList<>();
        this.ubicacionActual = new CoordenadaGPS(0.0, 0.0);
    }

    public void agregarTripulante(Tripulante t) {
        tripulacion.add(t);
    }

    public void actualizarUbicacion(double latitud, double longitud) {
        this.ubicacionActual = new CoordenadaGPS(latitud, longitud);
    }

    public String getPlaca() {
        return placa;
    }

    public TipoAmbulancia getTipo() {
        return tipo;
    }

    public ArrayList<Tripulante> getTripulacion() {
        return tripulacion;
    }

    public CoordenadaGPS getUbicacionActual() {
        return ubicacionActual;
    }

    @Override
    public String toString() {
        return "Ambulancia: " + placa + " (" + tipo + ") - Estado: " + estado 
                + " - Tripulación: " + tripulacion.size() + " personas - GPS: " + ubicacionActual;
    }
}