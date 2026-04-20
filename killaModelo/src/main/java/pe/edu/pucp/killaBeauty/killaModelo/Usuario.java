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
    private String estado; // activo - inactivo
    private List<Direccion> direcciones;
    private List<Pedido> pedidos;
    private CarritoDeCompras carritoActivo;

    //Constructores
    public Usuario() {
        this.pedidos = new ArrayList<>();
        this.direcciones = new ArrayList<>();
    }

    //Metodos
    public void registrarse() {}
    public void iniciarSesion(String correoElectronico, String contrasena) {}
    public void realizarPedido() {}
    public void actualizarDatos() {}
    public void agregarDireccion(Direccion direccionNueva) {}
    public void agregarPedido(Pedido pedidoNuevo) {}

    //Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

}
