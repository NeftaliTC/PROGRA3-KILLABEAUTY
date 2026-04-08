package killa.modelo;

public class DetalleCarrito {
    private int idDetalleCarrito;
    private int cantidad;
//navegación
    private Producto producto;
    private Carro carro;
// getters setters
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
}

