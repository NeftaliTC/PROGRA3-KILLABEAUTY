public class DetalleCarrito {
    private int id_detalleCarrito;
    private int cantidad;
//navegación
    private Producto producto;
    private Carro carro;

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public String toString() {
        return producto.getNombre() + " x" + cantidad;
    }
}

