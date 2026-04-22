package pe.edu.pucp.killaBeauty.killaModelo;
<<<<<<< HEAD

import java.util.List;
import java.util.ArrayList;

public class Producto {
    private int id;
    private String nombre;
    private double precioLista;
    private int stock;
    private boolean disponible;
=======
import java.util.ArrayList;
import java.util.List;

public class Producto {
    private int idProducto;
    private String nombre;
    private double precioLista;
    private int stock;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
    private Marca marca;
    private Categoria categoria;
    private List<EscalaPrecio> escalas;
    private List<Resena> resenas;

    public Producto() {
        this.escalas = new ArrayList<>();
        this.resenas = new ArrayList<>();
    }

    public int getIdProducto() {
<<<<<<< HEAD
        return id;
    }

    public void setIdProducto(int id) {
        this.id = id;
=======
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioLista() {
        return precioLista;
    }

    public void setPrecioLista(double precioLista) {
        this.precioLista = precioLista;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

<<<<<<< HEAD
    public boolean getDisponible(){return disponible;}

    public void setDisponible(boolean disponible){this.disponible = disponible;}

=======
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<EscalaPrecio> getEscalas() {
        return escalas;
    }

    public void setEscalas(List<EscalaPrecio> escalas) {
        this.escalas = escalas;
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }

<<<<<<< HEAD
=======
    public void registrarProducto() {}

    public void actualizarStock(int cantidad) {}

    public double obtenerPrecioParaCantidad(int cantidad) {
        return 0.0;
    }

    public void agregarEscala(EscalaPrecio escala) {}

    public void agregarResena(Resena resena) {}
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
}
