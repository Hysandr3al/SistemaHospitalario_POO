package Receta;
import Farmacia.Medicamento;

public class DetalleMedicamento {
    private int cantidad;
    private Medicamento medicamento;

    public DetalleMedicamento(Medicamento medicamento, int cantidad){
        this.medicamento = medicamento;
        this.cantidad = cantidad;
    }

    public Medicamento getMedicamento(){
        return medicamento;
    }

    public int getCantidad(){
        return cantidad;
    }


    public double calcularSubTotal(){
        return medicamento.getPrecio() * cantidad;
    }

}