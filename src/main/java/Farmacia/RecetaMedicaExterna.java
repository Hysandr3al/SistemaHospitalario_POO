package Farmacia;

import java.util.List;

import Receta.DetalleMedicamento;
import Receta.RecetaMedica;

public class RecetaMedicaExterna extends RecetaMedica{
    private String nombreCliente;
    private int idRecetaExterna;

    public RecetaMedicaExterna(List<DetalleMedicamento> detalleMedicamentos, String nombreCliente) {
        super(detalleMedicamentos);
        this.nombreCliente = nombreCliente;
    }

    public String getNombreCliente(){
        return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getIdRecetaExterna() {
        return idRecetaExterna;
    }
    public void setIdRecetaExterna(int idRecetaExterna) {
        this.idRecetaExterna = idRecetaExterna;
    }
    

}

