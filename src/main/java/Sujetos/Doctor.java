package Sujetos;

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

    public RecetaMedica crearRecetaMedica(Paciente paciente){
        return;
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
