package pe.edu.pucp.killaBeauty.killaModelo;

public class DetalleCarrito {
    private int id;
    private int cantidad;
    private Producto producto;
    private CarritoDeCompras carrito;
    private int idCarrito;

    public CarritoDeCompras getCarrito() {return carrito;}

    public void setCarrito(CarritoDeCompras carrito) {this.carrito = carrito;}

    public DetalleCarrito() {
        this.producto = new Producto();
    }

    public int getIdDetalleCarrito() {
        return id;
    }

    public void setIdDetalleCarrito(int idDetalleCarrito) {
        this.id = idDetalleCarrito;
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

    public int getIdCarrito() { return idCarrito; }

    public void setIdCarrito(int idCarrito) { this.idCarrito = idCarrito; }

    public double calcularSubtotal() {
        if (producto != null) return cantidad * producto.getPrecioBase();
        return 0.0;
    }


}

