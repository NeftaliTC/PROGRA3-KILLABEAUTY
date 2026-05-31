package pe.edu.pucp.killaBeauty.killaModelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private LocalDate fechaPedido;
    private EstadoPedido estadoPedido;
    private String metodoPago;
    private double subtotal;
    private double igv;
    private double total;

    private Usuario cliente;
    private Direccion direccionEnvio;
    private Cupon cupon;
    private List<DetallePedido> detalles;

    public Pedido() {
        this.detalles = new ArrayList<>();
        this.fechaPedido = LocalDate.now();
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public int getIdPedido() {
        return id;
    }

    public void setIdPedido(int id) {
        this.id = id;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Direccion getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(Direccion direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public Cupon getCupon() {
        return cupon;
    }

    public void setCupon(Cupon cupon) {
        this.cupon = cupon;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = (detalles != null) ? detalles : new ArrayList<>();
    }

    public void agregarDetalle(DetallePedido detalle) {
        if (detalle != null) this.detalles.add(detalle);
    }

    public boolean quitarDetalle(DetallePedido detalle) {
        return detalle != null && this.detalles.remove(detalle);
    }

    public void recalcularTotales() {
        this.subtotal = 0.0;

        for (DetallePedido d : detalles) {
            if (d != null) this.subtotal += d.calcularTotalConDescuento();
        }

        if (cupon != null && cupon.esVigente(LocalDate.now())) {
            this.subtotal = this.subtotal * (1 - cupon.getPorcentajeDescuento() / 100.0);
        }

        this.igv = this.subtotal * 0.18;
        this.total = this.subtotal + this.igv;
    }


}
