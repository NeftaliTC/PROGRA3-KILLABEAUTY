package killa.modelo;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Carro {

    private int idCarrito;
    private Date fechaDeCreacion;
    private String estado; // con un enum tambien


    private Cliente cliente;
    private List<DetalleCarrito> items;

    public Carro() {
        this.items = new ArrayList<>();
    }
        
    public int getIdCarrito() {
        return idCarrito; 
    }
    public Date getFechaDeCreacion() { 
        return fechaDeCreacion; 
    }
    public EstadoCarrito getEstado() {
        return estado;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }
    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }
    public void setEstado(EstadoCarrito estado) {
        this.estado = estado; 
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente; 
    }
}
