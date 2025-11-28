package recursoslogistica.ambulancias;

public class CoordenadaGPS {
    private double latitud;
    private double longitud;

    public CoordenadaGPS(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    @Override
    public String toString() {
        return "(" + latitud + ", " + longitud + ")";
    }
}