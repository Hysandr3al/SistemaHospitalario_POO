package Receta;

import Sujetos.Paciente;
import Sujetos.Doctor;
import java.util.List;

public class RecetaMedicaHospital extends RecetaMedica {
    private int idReceta;
    private Doctor doctor;
    private Paciente paciente;
    private int idRecetaHospital;
    private List<DetalleMedicamento> detallesMedicamentos;

    public RecetaMedicaHospital(){
    }
    public RecetaMedicaHospital(List<DetalleMedicamento> detalleMedicamentos, int idReceta, Doctor doctor, Paciente paciente) {
        super(detalleMedicamentos);
        this.idReceta = idReceta;
        this.doctor = doctor;
        this.paciente = paciente;
    }
    
    public int getIdReceta() {
        return idReceta;
    }
    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public int getIdRecetaHospital() {
        return idRecetaHospital;
    }
    public void setIdRecetaHospital(int idRecetaHospital) {
        this.idRecetaHospital = idRecetaHospital;
    }
    
    public List<DetalleMedicamento> getDetallesMedicamento() {
        return detallesMedicamentos;
    }

    public void setDetallesMedicamento(List<DetalleMedicamento> detallesMedicamento) {
        this.detallesMedicamentos = detallesMedicamento;
    }

}
