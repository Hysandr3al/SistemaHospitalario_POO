package Sujetos;

public class Doctor extends Persona{
    private String especialidad;
    private String horario;
    private double salario;



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

    @Override
    public void mostrarInfo(){
        System.out.println("Doctor: " + nombres + " " + apellidos + " - Especialidad: " + especialidad);
    };
}
