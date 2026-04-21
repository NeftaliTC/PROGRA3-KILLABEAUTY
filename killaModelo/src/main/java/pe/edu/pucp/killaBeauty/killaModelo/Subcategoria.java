package pe.edu.pucp.killaBeauty.killaModelo;

public class Subcategoria {
    private int idSubcategoria;
    private String nombre;
    // Navegación
    private Categoria categoria; // categoría a la que pertenece subcategoría

    // Constructores
    public Subcategoria() {}

    public Subcategoria(int idSubcategoria, String nombre, Categoria categoria) {
        this.idSubcategoria = idSubcategoria;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    // Métodos 
    public void cambiarCategoriaPadre(Categoria nuevaCategoria) {}

    // Getters y setters
    public int getIdSubcategoria() {
        return idSubcategoria;
    }

    public void setIdSubcategoria(int idSubcategoria) {
        this.idSubcategoria = idSubcategoria;
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
