package Sujetos;

public class Paciente extends Persona {

    private String grupoSanguineo;
    private String alergias;
    private HistoriaClinica historiaClinica;
    private String idPaciente;
    private String direccion;

    public Paciente(String nombres, String apellidos, String telefono, String dni,
                    String sexo, int edad, String grupoSanguineo, String alergias, String idPaciente, String direccion) {

        super(nombres, apellidos, telefono, dni, sexo, edad);
        this.grupoSanguineo = grupoSanguineo;
        this.alergias = alergias;
        this.historiaClinica = new HistoriaClinica(this);
        this.idPaciente = idPaciente;
        this.direccion = direccion;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }
    
    
    //getters y setters
    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }
    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }
    public String getAlergias() {
        return alergias;
    }
    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }
    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }
    public String getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
