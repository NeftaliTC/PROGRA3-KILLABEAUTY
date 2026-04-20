package pe.edu.pucp.killaBeauty.killaModelo;

public class DetalleCarrito {
    private int id;
    private int cantidad;
    private Producto producto;
    private CarritoDeCompras carrito;

    public DetalleCarrito() {}

    public int getIdDetalleCarrito() {
        return id;
    }

    public void setIdDetalleCarrito(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public CarritoDeCompras getCarrito(){return carrito;}

    public void setCarrito(CarritoDeCompras carrito){this.carrito = carrito;}

}
