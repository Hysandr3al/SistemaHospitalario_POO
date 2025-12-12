package Farmacia;

public interface Vendible {
    boolean vender(int cantidad);
    double calcularPrecio();
}