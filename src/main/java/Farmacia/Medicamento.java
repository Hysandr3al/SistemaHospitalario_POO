package Farmacia;

public class Medicamento implements Vendible {
    private int codigo;
    private String nombre;
    private String tipo;
    private double precio;
    private int stock;
    private String fechaVencimiento;
    private double descuento;

    public Medicamento(int codigo, String nombre, String tipo, double precio, int stock, String fechaVencimiento, double descuento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.stock = stock;
        this.fechaVencimiento = fechaVencimiento;
        this.descuento = descuento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Override
    public boolean vender(int cantidad){
        if(cantidad <= stock){
            stock -= cantidad;
            return true;
        }
        return false;
    }

    @Override
    public double calcularPrecio() {
        return precio - (precio * descuento);
    }
}
