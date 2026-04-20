package pe.edu.pucp.killaBeauty.killaModelo;

import java.util.List;
import java.util.ArrayList;

public class Categoria {
    private int id;
    private String nombre;
    private List<Subcategoria> subcategorias;

    public Categoria() {
        this.subcategorias = new ArrayList<>();
    }

    public int getIdCategoria() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    public List<Subcategoria> getSubcategorias() {
        return subcategorias;
    }

    public void setIdCategoria(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setSubcategorias(List<Subcategoria> subcategorias) {
        this.subcategorias = subcategorias;
    }
}
