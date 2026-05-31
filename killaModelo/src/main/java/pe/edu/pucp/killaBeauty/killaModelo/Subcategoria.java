package pe.edu.pucp.killaBeauty.killaModelo;

public class Subcategoria {
    private int id;
    private String nombre;
    private Boolean activo;
    private Categoria categoria;

    public Subcategoria() {}

    public Subcategoria(int id, String nombre, Boolean activo, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
