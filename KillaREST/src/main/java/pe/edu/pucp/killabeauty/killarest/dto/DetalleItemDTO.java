package pe.edu.pucp.killabeauty.killarest.dto;

import pe.edu.pucp.killaBeauty.killaModelo.DetallePedido;

public class DetalleItemDTO {
    private int productoId;
    private String nombreProducto;
    private String marca;
    private int cantidad;
    private double precioUnitarioOrig;
    private double precioUnitarioDesc;
    private double subtotal;

    public DetalleItemDTO(DetallePedido detalle) {
        this.productoId = detalle.getProducto() != null ? detalle.getProducto().getId() : 0;
        this.nombreProducto = detalle.getProducto() != null ? detalle.getProducto().getNombre() : "";
        this.marca = detalle.getProducto() != null && detalle.getProducto().getMarca() != null
                ? detalle.getProducto().getMarca().getDescripcion()
                : "";
        this.cantidad = detalle.getCantidad();
        this.precioUnitarioOrig = detalle.getProducto() != null ? detalle.getProducto().getPrecioBase() : 0;
        this.precioUnitarioDesc = detalle.getPrecioAplicado();
        this.subtotal = detalle.calcularSubtotal();
    }

    // Getters y Setters
    public int getProductoId() { return productoId; }
    public void setProductoId(int productoId) { this.productoId = productoId; }
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getPrecioUnitarioOrig() { return precioUnitarioOrig; }
    public void setPrecioUnitarioOrig(double precioUnitarioOrig) { this.precioUnitarioOrig = precioUnitarioOrig; }
    public double getPrecioUnitarioDesc() { return precioUnitarioDesc; }
    public void setPrecioUnitarioDesc(double precioUnitarioDesc) { this.precioUnitarioDesc = precioUnitarioDesc; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}
