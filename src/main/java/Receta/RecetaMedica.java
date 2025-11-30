package Receta;

import java.util.List;

public abstract class RecetaMedica {
    protected List<DetalleMedicamento> detalleMedicamentos;

    public RecetaMedica(){
    }
    public RecetaMedica(List<DetalleMedicamento> detalleMedicamentos) {
        this.detalleMedicamentos = detalleMedicamentos;
    }

    public List<DetalleMedicamento> getDetalleMedicamentos() {
        return detalleMedicamentos;
    }

    public double CalcularTotal(){
        double total = 0;
        for (DetalleMedicamento d : detalleMedicamentos){
            total += d.calcularSubTotal();
        }
        return total;
    }
}
