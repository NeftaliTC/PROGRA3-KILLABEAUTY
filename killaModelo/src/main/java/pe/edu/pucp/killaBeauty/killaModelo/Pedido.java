package pe.edu.pucp.killaBeauty.killaModelo;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
public class Pedido {
    private int idPedido;
    private Date fechaPedido;
    private String estado;
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
        this.fechaPedido = new Date();
    }
	
	public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public double getIgv() {
        return igv;
    }

    public double getTotal() {
        return total;
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
        this.subtotal = 0;
        for (DetallePedido d : detalles) {
            if (d != null) this.subtotal += d.calcularTotalConDescuento();
        }

        if (cupon != null && cupon.esVigente(new Date())) {
            this.subtotal = this.subtotal * (1 - cupon.getPorcentajeDescuento() / 100.0);
        }

        this.igv = this.subtotal * 0.18;
        this.total = this.subtotal + this.igv;
    }
}
