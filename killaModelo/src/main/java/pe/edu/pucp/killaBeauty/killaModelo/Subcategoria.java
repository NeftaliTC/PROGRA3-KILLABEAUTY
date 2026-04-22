package pe.edu.pucp.killaBeauty.killaModelo;

public class Subcategoria {
<<<<<<< HEAD
    private int id;
=======
    private int idSubcategoria;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
    private String nombre;
    // Navegación
    private Categoria categoria; // categoría a la que pertenece subcategoría

    // Constructores
    public Subcategoria() {}

<<<<<<< HEAD
    public Subcategoria(int id, String nombre, Categoria categoria) {
        this.id = id;
=======
    public Subcategoria(int idSubcategoria, String nombre, Categoria categoria) {
        this.idSubcategoria = idSubcategoria;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
        this.nombre = nombre;
        this.categoria = categoria;
    }

<<<<<<< HEAD
    // Métodos
=======
    // Métodos 
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
    public void cambiarCategoriaPadre(Categoria nuevaCategoria) {}

    // Getters y setters
    public int getIdSubcategoria() {
<<<<<<< HEAD
        return id;
    }

    public void setIdSubcategoria(int id) {
        this.id = id;
=======
        return idSubcategoria;
    }

    public void setIdSubcategoria(int idSubcategoria) {
        this.idSubcategoria = idSubcategoria;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
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
