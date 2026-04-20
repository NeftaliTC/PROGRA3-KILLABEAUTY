package pe.edu.pucp.killaBeauty.killaModelo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class CarritoDeCompras {
    private int id; // redundante
    private LocalDateTime fechaDeCreacion;
    private EstadoCarrito estado;

    private Usuario usuario;
    private List<DetalleCarrito> items;

    public CarritoDeCompras() {
        this.items = new ArrayList<>();
        this.estado = EstadoCarrito.ACTIVO;
    }

    public void agregarItem(DetalleCarrito item) {
        items.add(item);
    }

    public int getIdCarrito() {
        return id;
    }
    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }
    public EstadoCarrito getEstado() {
        return estado;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setItems(List<DetalleCarrito> items) {
        this.items = items;
    }
    public void setIdCarrito(int idCarrito) {
        this.id = idCarrito;
    }
    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }
    public void setEstado(EstadoCarrito estado) {
        this.estado = estado;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleCarrito> getItems() {
        return items;
    }
}
