package pe.edu.pucp.killaBeauty.killaModelo;

import java.util.List;
import java.util.ArrayList;

public class Producto {
    private int id;
    private String nombre;
    private double precioLista;
    private int stock;
    private boolean disponible;
    private Marca marca;
    private Categoria categoria;
    private List<EscalaPrecio> escalas;
    private List<Resena> resenas;

    public Producto() {
        this.escalas = new ArrayList<>();
        this.resenas = new ArrayList<>();
    }

    public int getIdProducto() {
        return id;
    }

    public void setIdProducto(int id) {
        this.id = id;
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

    public boolean getDisponible(){return disponible;}

    public void setDisponible(boolean disponible){this.disponible = disponible;}

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

}
