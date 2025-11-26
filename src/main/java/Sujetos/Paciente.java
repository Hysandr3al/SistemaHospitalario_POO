package Sujetos;

public class Paciente extends Persona {

    private String grupoSanguineo;
    private String alergias;
    private HistoriaClinica historiaClinica;

    public Paciente(String nombres, String apellidos, String telefono, String dni,
                    String sexo, int edad, String grupoSanguineo, String alergias) {

        super(nombres, apellidos, telefono, dni, sexo, edad);
        this.grupoSanguineo = grupoSanguineo;
        this.alergias = alergias;
        this.historiaClinica = new HistoriaClinica(this);
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Paciente: " + nombres + " " + apellidos + " - DNI: " + dni);
    }

    @Override
    public String toString() {
        return "Paciente: " + nombres + " " + apellidos + " (DNI: " + dni + ")";
    }
}
