package pe.edu.pucp.killaBeauty.killaModelo;

public class DetalleCarrito {
<<<<<<< HEAD
    private int id;
    private int cantidad;
    private Producto producto;
    private CarritoDeCompras carrito;
=======
    private int idDetalleCarrito;
    private int cantidad;
    private Producto producto;

>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8

    public DetalleCarrito() {}

    public int getIdDetalleCarrito() {
<<<<<<< HEAD
        return id;
    }

    public void setIdDetalleCarrito(int id) {
        this.id = id;
=======
        return idDetalleCarrito;
    }

    public void setIdDetalleCarrito(int idDetalleCarrito) {
        this.idDetalleCarrito = idDetalleCarrito;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
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

<<<<<<< HEAD
    public CarritoDeCompras getCarrito(){return carrito;}

    public void setCarrito(CarritoDeCompras carrito){this.carrito = carrito;}

}
=======
    public double calcularSubtotal() {
        return 0.0;
    }
}

>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
