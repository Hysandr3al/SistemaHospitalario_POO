package Receta;

import Sujetos.Paciente;

import java.util.List;

import Sujetos.Doctor;

public class RecetaMedicaHospital extends RecetaMedica {
    private int idReceta;
    private Doctor doctor;
    private Paciente paciente;
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

}
