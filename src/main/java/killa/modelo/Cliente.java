package killa.modelo;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String correoElectronico;
    private Date fechaDeInscripcion;
    private String contrasena;
    private String telefono;
    private String estado; // activo - inactivo
    private List<Direccion> direcciones;
    private List<Pedido> pedidos;
    private Carro carritoActivo;

    //Constructores
    public Cliente() {
        this.pedidos = new ArrayList<>();
        this.direcciones = new ArrayList<>();
    }

    //Metodos
    public void registrarse() {}
    public void iniciarSesion(String correoElectronico, String contrasena) {}
    public void realizarPedido() {}
    public void actualizarDatos() {}
    
    //Getters y setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public Date getFechaDeInscripcion() {
        return fechaDeInscripcion;
    }

    public void setFechaDeInscripcion(Date fechaDeInscripcion) {
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

    public Carro getCarritoActivo() {
        return carritoActivo;
    }

    public void setCarritoActivo(Carro carritoActivo) {
        this.carritoActivo = carritoActivo;
    }
}
