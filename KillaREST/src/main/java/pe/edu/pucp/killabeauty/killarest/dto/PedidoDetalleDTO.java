package pe.edu.pucp.killabeauty.killarest.dto;

import pe.edu.pucp.killaBeauty.killaModelo.Pedido;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoDetalleDTO {
    private int id;
    private String fecha;
    private String estado;
    private double subtotal;
    private double igv;
    private double total;
    private int clienteId;
    private String cliente;
    private String correo;
    private String contacto;
    private int direccionId;
    private String direccion;
    private String referencia;
    private String distrito;
    private String provincia;
    private String departamento;
    private Integer cuponId;
    private List<DetalleItemDTO> productos;

    public PedidoDetalleDTO(Pedido p) {
        this.id = p.getId();
        this.fecha = p.getFechaPedido() != null
                ? new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(p.getFechaPedido())
                : "";
        this.estado = p.getEstadoPedido() != null ? p.getEstadoPedido().getNombre() : "PENDIENTE";
        this.subtotal = p.getSubtotal();
        this.igv = p.getIgv();
        this.total = p.getTotal();

        if (p.getCliente() != null) {
            this.clienteId = p.getCliente().getId();
            this.cliente = nombreCompleto(p);
            this.correo = p.getCliente().getCorreoElectronico();
            this.contacto = p.getCliente().getTelefono();
        } else {
            this.cliente = "Sin cliente";
        }

        if (p.getDireccionEnvio() != null) {
            this.direccionId = p.getDireccionEnvio().getId();
            this.direccion = p.getDireccionEnvio().getDireccionDetalle();
            this.referencia = p.getDireccionEnvio().getReferencia();
            this.distrito = p.getDireccionEnvio().getDistrito();
            this.provincia = p.getDireccionEnvio().getProvincia();
            this.departamento = p.getDireccionEnvio().getDepartamento();
        }

        if (p.getCupon() != null && p.getCupon().getId() > 0) {
            this.cuponId = p.getCupon().getId();
        }

        if (p.getDetalles() != null) {
            this.productos = p.getDetalles().stream()
                    .map(DetalleItemDTO::new)
                    .collect(Collectors.toList());
        }
    }

    private String nombreCompleto(Pedido p) {
        StringBuilder nombre = new StringBuilder();
        appendIfPresent(nombre, p.getCliente().getNombre());
        appendIfPresent(nombre, p.getCliente().getApellidoPaterno());
        appendIfPresent(nombre, p.getCliente().getApellidoMaterno());
        return nombre.length() > 0 ? nombre.toString() : "Cliente " + p.getCliente().getId();
    }

    private void appendIfPresent(StringBuilder builder, String value) {
        if (value == null || value.isBlank()) return;
        if (builder.length() > 0) builder.append(" ");
        builder.append(value);
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
    public double getIgv() { return igv; }
    public void setIgv(double igv) { this.igv = igv; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }
    public int getDireccionId() { return direccionId; }
    public void setDireccionId(int direccionId) { this.direccionId = direccionId; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }
    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public Integer getCuponId() { return cuponId; }
    public void setCuponId(Integer cuponId) { this.cuponId = cuponId; }
    public List<DetalleItemDTO> getProductos() { return productos; }
    public void setProductos(List<DetalleItemDTO> productos) { this.productos = productos; }
}
