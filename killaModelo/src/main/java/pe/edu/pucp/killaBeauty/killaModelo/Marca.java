package pe.edu.pucp.killaBeauty.killaModelo;

public class Marca {
    private int idMarca;
    private String descripcion;
    private String paisDeOrigen;

    //Constructores
    public Marca() {};

    public Marca(int idMarca, String descripcion, String paisDeOrigen) {
        this.idMarca = idMarca;
        this.descripcion = descripcion;
        this.paisDeOrigen = paisDeOrigen;
    }
    
    //Getters y setters
    public int getIdMarca() {
        return idMarca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPaisDeOrigen() {
        return paisDeOrigen;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPaisDeOrigen(String paisDeOrigen) {
        this.paisDeOrigen = paisDeOrigen;
    }
    
}
