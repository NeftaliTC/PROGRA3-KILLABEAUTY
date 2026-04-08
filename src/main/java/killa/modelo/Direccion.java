package killa.modelo;
public class Direccion {
    private int id_direccion;
    private String calle;
    private String numero;
    private String distrito;
    private String departamento;
    private String codigoPostal;
    private String referencia;
        
    public Direccion(){};
    
    public int getId_direccion() {
        return id_direccion;
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

    public void setId_direccion(int id_direccion) {
        this.id_direccion = id_direccion;
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
}
