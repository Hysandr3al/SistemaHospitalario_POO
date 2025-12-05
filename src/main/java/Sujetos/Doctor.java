package Sujetos;

import java.util.List;

import Receta.DetalleMedicamento;
import Receta.RecetaMedicaHospital;

public class Doctor extends Persona{
    private String especialidad;
    private String horario;
    private double salario;
    private int idDoctor;

    public Doctor(){  
    }

    public Doctor(String nombres, String apellidos, String telefono, String dni, String sexo, int edad,
                  String especialidad, String horario, double salario) {
        super(nombres, apellidos, telefono, dni, sexo, edad);
        this.especialidad = especialidad;
        this.horario = horario;
        this.salario = salario;
    }

    public RecetaMedicaHospital crearRecetaMedica(Paciente p, List<DetalleMedicamento> d){
        RecetaMedicaHospital receta = new RecetaMedicaHospital();

        receta.setDoctor(this);
        receta.setPaciente(p);
        receta.setDetallesMedicamento(d);
        return receta;
    }

    public String getEspecialidad(){
        return especialidad;
    }
    public void setEspecialidad(String especialidad){
        this.especialidad = especialidad;
    }
    
    public int getIdDoctor(){
        return idDoctor;
    }
    public void setIdDoctor(int idDoctor){
        this.idDoctor = idDoctor;
    }
    
    public String getHorario(){
        return horario;
    }
    public void setHorario(String horario){
        this.horario = horario;
    }

    public double getSalario(){
        return salario;
    }
    public void setSalario(double salario){
        this.salario = salario;
    }

    @Override
    public void mostrarInfo(){
        System.out.println("Doctor: " + nombres + " " + apellidos + " - Especialidad: " + especialidad);
    };
}
