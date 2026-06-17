package pe.edu.pucp.killabeauty.killarest.dto;

import pe.edu.pucp.killaBeauty.killaModelo.Pedido;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoDetalleDTO {
    private int id;
    private String fecha;
    private String estado;
    private double subtotal;
    private double total;
    private String cliente;
    private List<DetalleItemDTO> productos;

    public PedidoDetalleDTO(Pedido p) {
        this.id = p.getId();
        this.fecha = p.getFechaPedido() != null ? p.getFechaPedido().toString() : "";
        this.estado = p.getEstadoPedido() != null ? p.getEstadoPedido().toString() : "PENDIENTE";
        this.subtotal = p.getSubtotal();
        this.total = p.getTotal();
        this.cliente = (p.getCliente() != null) ? p.getCliente().getNombre() : "Sin cliente";

        if (p.getDetalles() != null) {
            this.productos = p.getDetalles().stream()
                    .map(DetalleItemDTO::new)
                    .collect(Collectors.toList());
        }
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public List<DetalleItemDTO> getProductos() { return productos; }
    public void setProductos(List<DetalleItemDTO> productos) { this.productos = productos; }
}
