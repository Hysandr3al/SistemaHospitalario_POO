package Farmacia;

import java.util.List;

import Receta.DetalleMedicamento;
import Receta.RecetaMedica;

public class RecetaMedicaExterna extends RecetaMedica{
    private String nombreCliente;

    public RecetaMedicaExterna(List<DetalleMedicamento> detalleMedicamentos, String nombreCliente) {
        super(detalleMedicamentos);
        this.nombreCliente = nombreCliente;
    }

    public String getNombreCliente(){
        return nombreCliente;
    }
}

