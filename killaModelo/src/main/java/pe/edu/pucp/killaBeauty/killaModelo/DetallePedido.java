package pe.edu.pucp.killaBeauty.killaModelo;

public class DetallePedido {
<<<<<<< HEAD
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
=======
    private int idDetallePedido;
    private int cantidad;
    private double precioAplicado;
    private Producto producto;
    //private Descuento descuentoAplicado;
    private double subtotal; 
    // Constructor
    public DetallePedido() {}

    // Métodos 
    public double calcularSubtotal() {
        return 0.0;
    }

    public double calcularTotalConDescuento() {
        return 0.0;
    }

    // Getters y setters
    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
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

<<<<<<< HEAD
    public Descuento getDescuentoAplicado() {
        return descuentoAplicado;
    }

    public void setDescuentoAplicado(Descuento descuentoAplicado) {
        this.descuentoAplicado = descuentoAplicado;
    }

    public Pedido getPedido(){return pedido;}

    public void setPedido(Pedido pedido){this.pedido = pedido;}
}
=======
    //public Descuento getDescuentoAplicado() { return descuentoAplicado;    }

    //public void setDescuentoAplicado(Descuento descuentoAplicado) {this.descuentoAplicado = descuentoAplicado;}
}
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
