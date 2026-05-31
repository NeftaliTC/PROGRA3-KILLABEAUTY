package pe.edu.pucp.killaBeauty.killaModelo;

public class Direccion {
    private int id;
    private String alias;
    private String distrito;
    private String provincia;
    private String departamento;
    private String codigoPostal;
    private int telefono;
    private String direccionExacta;
    private String referencia;
    private Usuario usuario;
    private Boolean activo;
        
    public Direccion(){};

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

