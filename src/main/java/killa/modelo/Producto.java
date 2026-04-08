package killa.modelo;
import java.util.ArrayList;
import java.util.List;

public class Producto {
    private int idProducto;
    private String nombre;
    private double precioLista;
    private String marca;

    // Un producto tiene una categoría y varias escalas de precio
    private Categoria categoria;
    private List<EscalaPrecio> escalas;

    public Producto() {
        this.escalas = new ArrayList<>();
    }

    public void registrarProducto() {}
    public void actualizarStock(int cantidad) {}
}
