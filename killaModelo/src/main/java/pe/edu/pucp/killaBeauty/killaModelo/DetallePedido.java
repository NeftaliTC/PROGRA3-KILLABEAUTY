package pe.edu.pucp.killaBeauty.killaModelo;

public class DetallePedido {
    private int id;
    private int cantidad;
    private double precioAplicado;
    private double subtotal;
    private Producto producto;
    private Descuento descuentoAplicado;
    private Pedido pedido;
    // Constructor
    public DetallePedido() {}

    // Getters y setters
    public int getIdDetallePedido() {
        return id;
    }

    public void setIdDetallePedido(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioAplicado() {
        return precioAplicado;
    }

    public void setPrecioAplicado(double precioAplicado) {
        this.precioAplicado = precioAplicado;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Descuento getDescuentoAplicado() {
        return descuentoAplicado;
    }

    public void setDescuentoAplicado(Descuento descuentoAplicado) {
        this.descuentoAplicado = descuentoAplicado;
    }

    public Pedido getPedido(){return pedido;}

    public void setPedido(Pedido pedido){this.pedido = pedido;}
}
