package killa.modelo;
import java.util.ArrayList;
import java.util.List;

public class Producto {
    private int idProducto;
    private String nombre;
    private double precioLista;
    private int stock;

    // <Un producto tiene una categoría y varias escalas de precio

    private Marca marca;
    private SubCategoria SubCategoria;
    private List<EscalaPrecio> escalas;
    private List<Resena> resenas;

    public Producto() {
        this.escalas = new ArrayList<>();
        this.resenas = new ArrayList<>();
    }


    public void registrarProducto() {}
    public void actualizarStock(int cantidad) {}

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioLista() {
        return precioLista;
    }

    public int getStock() {
        return stock;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecioLista(double precioLista) {
        this.precioLista = precioLista;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }    
}
