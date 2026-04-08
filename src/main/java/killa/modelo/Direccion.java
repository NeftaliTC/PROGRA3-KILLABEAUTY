package killa.modelo;
public class Direccion {
    private int idDireccion;
    private String calle;
    private String numero;
    private String distrito;
    private String departamento;
    private String codigoPostal;
    private String referencia;
        
    public Direccion(){};

    public String mostrarDireccion() {
    return calle + " " + numero + ", " + distrito + ", " + departamento +
           ", CP: " + codigoPostal +
           ", Ref: " + referencia;
    }
    
    public int getIdDireccion() {
        return idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public String getDistrito() {
        return distrito;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
}

