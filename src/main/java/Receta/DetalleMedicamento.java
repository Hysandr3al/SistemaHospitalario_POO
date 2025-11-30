package Receta;
import Farmacia.Medicamento;

public class DetalleMedicamento {
    private int cantidad;
    private Medicamento medicamento;
    private int idDetalle;
    private char tipoReceta; //H o E
    private int idReceta;

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
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    public int getIdDetalle(){
        return idDetalle;
    }
    public void setIdDetalle(int idDetalle){
        this.idDetalle = idDetalle;
    }

    public char getTipoReceta(){
        return tipoReceta;
    }
    public void setTipoReceta(char tipoReceta){
        this.tipoReceta= tipoReceta;
    }

    public int getIdReceta(){
        return idReceta;
    }
    public void setIdReceta(int idReceta){
        this.idReceta = idReceta;
    }

    public double calcularSubTotal(){
        return medicamento.getPrecio() * cantidad;
    }

}