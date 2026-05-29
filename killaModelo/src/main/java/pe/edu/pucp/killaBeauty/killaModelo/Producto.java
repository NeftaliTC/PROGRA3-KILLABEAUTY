package pe.edu.pucp.killaBeauty.killaModelo;
import java.util.ArrayList;
import java.util.List;

public class Producto {
    private int id;
    private String nombre;
    private double precioBase;
    private int stock;
    private boolean disponible;
    private boolean promocion;
    private Marca marca;
    private Categoria categoria;
    private List<EscalaPrecio> escalas;
    private Boolean activo;
    private List<Resena> resenas;


    public Producto() {
        this.marca = new Marca();
        this.categoria = new Categoria();
        this.escalas = new ArrayList<>();
        this.resenas = new ArrayList<>();
    }

    public int getIdProducto() {
        return id;
    }

    public void setIdProducto(int idProducto) {
        this.id = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean getDisponible() { return disponible; }

    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public boolean getPromocion() { return promocion; }

    public void setPromocion(boolean promocion) { this.promocion = promocion; }

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

    public void registrarProducto() {}

    public void actualizarStock(int cantidad) {}

    public double obtenerPrecioParaCantidad(int cantidad) {
        return 0.0;
    }

    public void agregarEscala(EscalaPrecio escala) {}

    public void agregarResena(Resena resena) {}

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
