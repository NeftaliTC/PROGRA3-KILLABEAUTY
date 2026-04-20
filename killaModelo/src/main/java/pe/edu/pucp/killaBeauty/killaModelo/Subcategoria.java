package pe.edu.pucp.killaBeauty.killaModelo;

public class Subcategoria {
    private int id;
    private String nombre;
    // Navegación
    private Categoria categoria; // categoría a la que pertenece subcategoría

    // Constructores
    public Subcategoria() {}

    public Subcategoria(int id, String nombre, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    // Métodos
    public void cambiarCategoriaPadre(Categoria nuevaCategoria) {}

    // Getters y setters
    public int getIdSubcategoria() {
        return id;
    }

    public void setIdSubcategoria(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
