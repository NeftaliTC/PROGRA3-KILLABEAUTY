package pe.edu.pucp.killaBeauty.killaModelo;

public class Subcategoria {
    private int id;
    private String nombre;

    public Subcategoria() {}

    public Subcategoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int idSubcategoria) {
        this.id = idSubcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String descripcion) {
        this.nombre = descripcion;
    }
}
