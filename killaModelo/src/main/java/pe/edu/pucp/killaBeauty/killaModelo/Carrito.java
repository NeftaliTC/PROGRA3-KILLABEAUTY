package pe.edu.pucp.killaBeauty.killaModelo;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

// usen Date -> usar localDate


public class Carrito {

    private int id; // redundante
    private Date fechaDeCreacion;
    private EstadoCarro estado;
    private Usuario cliente;
    private List<DetalleCarrito> items;
    private Boolean activo;

    public Carrito() {
        this.items = new ArrayList<>();
        this.estado = EstadoCarro.ACTIVO;
    }
    
    public void agregarItem(DetalleCarrito item) {
        items.add(item);
    }
        
    public int getId() {
        return id;
    }
    public Date getFechaDeCreacion() { 
        return fechaDeCreacion; 
    }
    public EstadoCarro getEstado() {
        return estado;
    }
    public Usuario getCliente() {
        return cliente;
    }
    public void setItems(List<DetalleCarrito> items) {
        this.items = items;
    }
     public void setId(int id) {
        this.id = id;
    }
    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }
    public void setEstado(EstadoCarro estado) {
        this.estado = estado;
    }
    public void setCliente(Usuario cliente) {
        this.cliente = cliente; 
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<DetalleCarrito> getItems() {
        return items;
    }

}
