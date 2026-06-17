package pe.edu.pucp.killabeauty.killarest.dto;

import pe.edu.pucp.killaBeauty.killaModelo.DetallePedido;

public class DetalleItemDTO {
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public DetalleItemDTO(DetallePedido detalle) {
        this.nombreProducto = detalle.getProducto().getNombre();
        this.cantidad = detalle.getCantidad();
        this.precioUnitario = detalle.getPrecioAplicado();
        this.subtotal = detalle.calcularSubtotal();
    }

    // Getters y Setters
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}
