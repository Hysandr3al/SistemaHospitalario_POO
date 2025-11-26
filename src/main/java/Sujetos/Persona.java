package Sujetos;

public abstract class Persona {
    protected String nombres;
    protected String apellidos;
    protected String telefono;
    protected String dni;
    protected String sexo;
    protected int edad;



    public Persona(String nombres, String apellidos, String telefono, String dni, String sexo, int edad) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
        this.sexo = sexo;
        this.edad = edad;
    }


    public abstract void mostrarInfo();
}