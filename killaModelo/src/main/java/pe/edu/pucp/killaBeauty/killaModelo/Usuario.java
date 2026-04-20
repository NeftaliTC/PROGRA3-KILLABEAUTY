package pe.edu.pucp.killaBeauty.killaModelo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Usuario {

    private int id;
    private String nombre;
    private String correoElectronico;
    private LocalDateTime fechaDeInscripcion;
    private String contrasena; //-> estado
    private String telefono;
    private boolean activo; // activo - inactivo
    private List<Direccion> direcciones;
    private List<Pedido> pedidos;
    private CarritoDeCompras carritoActivo;
    private TipoUsuario tipoUsuario;

    //Constructores
    public Usuario() {
        this.pedidos = new ArrayList<>();
        this.direcciones = new ArrayList<>();
    }

    //Getters y setters
    public int getIdUsuario() {
        return id;
    }

    public void setIdUsuario(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public LocalDateTime getFechaDeInscripcion() {
        return fechaDeInscripcion;
    }

    public void setFechaDeInscripcion(LocalDateTime fechaDeInscripcion) {
        this.fechaDeInscripcion = fechaDeInscripcion;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public CarritoDeCompras getCarritoActivo() {
        return carritoActivo;
    }

    public void setCarritoActivo(CarritoDeCompras carritoActivo) {
        this.carritoActivo = carritoActivo;
    }

    public TipoUsuario getTipoUsuario(){return tipoUsuario;}

    public void setTipoUsuario(TipoUsuario tipoUsuario){this.tipoUsuario = tipoUsuario;}
}
