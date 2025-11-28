package recursoslogistica.ambulancias;

public class Tripulante {
    private String nombre;
    private String dni;
    private RolTripulante rol;
    private boolean enServicio;

    public Tripulante(String nombre, String dni, RolTripulante rol) {
        this.nombre = nombre;
        this.dni = dni;
        this.rol = rol;
        this.enServicio = true;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public RolTripulante getRol() {
        return rol;
    }

    public boolean isEnServicio() {
        return enServicio;
    }

    public void setEnServicio(boolean enServicio) {
        this.enServicio = enServicio;
    }

    @Override
    public String toString() {
        return nombre + " (" + rol + ")";
    }
}