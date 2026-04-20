package pe.edu.pucp.killaBeauty.killaModelo;

public class Marca {
    private int id;
    private String descripcion;
    private String paisDeOrigen;

    //Constructores
    public Marca() {};

    public Marca(int id, String descripcion, String paisDeOrigen) {
        this.id = id;
        this.descripcion = descripcion;
        this.paisDeOrigen = paisDeOrigen;
    }

    //Getters y setters
    public int getIdMarca() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPaisDeOrigen() {
        return paisDeOrigen;
    }

    public void setIdMarca(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPaisDeOrigen(String paisDeOrigen) {
        this.paisDeOrigen = paisDeOrigen;
    }
}
