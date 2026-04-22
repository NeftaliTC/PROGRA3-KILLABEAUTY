package pe.edu.pucp.killaBeauty.killaModelo;

public class Direccion {
    private int id;
    private String distrito;
    private String provincia;
    private String departamento;
    private String direccionExacta;
    private String referencia;
    private int idUsuario;
        
    public Direccion(){};

    public String mostrarDireccion() {
        return direccionExacta + " (Ref: " + referencia + "), " +
                distrito + ", " + provincia + ", " + departamento;
    }
    
    public int getIdDireccion() {
        return id;
    }

    public String getDistrito() {
        return distrito;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getProvincia() { return provincia; }

    public String getDireccionExacta() { return direccionExacta; }

    public String getReferencia() {
        return referencia;
    }

    public int getIdUsuario() { return idUsuario; }

    public void setIdDireccion(int idDireccion) {
        this.id = idDireccion;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setProvincia(String provincia) { this.provincia = provincia; }

    public void setDireccionExacta(String direccionExacta) { this.direccionExacta = direccionExacta; }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
}

