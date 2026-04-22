package pe.edu.pucp.killaBeauty.killaModelo;
<<<<<<< HEAD

public class Direccion {
    private int id;
=======
public class Direccion {
    private int idDireccion;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
    private String calle;
    private String numero;
    private String distrito;
    private String departamento;
    private String codigoPostal;
    private String referencia;
<<<<<<< HEAD

    public Direccion(){};

    public String mostrarDireccion() {
        return calle + " " + numero + ", " + distrito + ", " + departamento +
                ", CP: " + codigoPostal +
                ", Ref: " + referencia;
    }

    public int getIdDireccion() {
        return id;
=======
        
    public Direccion(){};

    public String mostrarDireccion() {
    return calle + " " + numero + ", " + distrito + ", " + departamento +
           ", CP: " + codigoPostal +
           ", Ref: " + referencia;
    }
    
    public int getIdDireccion() {
        return idDireccion;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
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

<<<<<<< HEAD
    public void setIdDireccion(int id) {
        this.id = id;
=======
    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
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
<<<<<<< HEAD
}
=======
    
}

>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
