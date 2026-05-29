package pe.edu.pucp.killaBeauty.killaModelo;

public class DetallePedido {
    private int id;
    private int cantidad;
    private double precioAplicado;
    private Producto producto;
    private double subtotal;
    private Boolean activo;

    public DetallePedido() {}

    public double calcularSubtotal() {
        this.subtotal = this.cantidad * this.precioAplicado;
        return this.subtotal;
    }

    public double calcularTotalConDescuento() {
        // Por ahora no hay descuento por ítem
        return calcularSubtotal();
    }

    public int getIdDetallePedido() {
        return id;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.id = idDetallePedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = Math.max(0, cantidad);
    }

    public double getPrecioAplicado() {
        return precioAplicado;
    }

    public void setPrecioAplicado(double precioAplicado) {
        this.precioAplicado = Math.max(0, precioAplicado);
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}