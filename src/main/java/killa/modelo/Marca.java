package killa.modelo;

public class Marca {
    private int idMarca;
    private String descripcion;
    private String paisDeOrigen;
    
    public Marca() {};

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
