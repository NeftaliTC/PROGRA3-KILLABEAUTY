package pe.edu.pucp.killaBeauty.killaModelo;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
// REVISADA
public class Usuario {
    private int id;
    private String nombre;
    private String correoElectronico;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private Date fechaDeInscripcion;
    private String contrasena; //-> estado
    private String telefono;
    private boolean estado; // 1=activo - 0=inactivo
    private List<Direccion> direcciones;
    private List<Pedido> pedidos;
    private Carrito carritoActivo;
    private int id_tipoUsuario;

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

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
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

    public Carrito getCarritoActivo() {
        return carritoActivo;
    }

    public void setCarritoActivo(Carrito carritoActivo) {
        this.carritoActivo = carritoActivo;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public int getId_tipoUsuario() {
        return id_tipoUsuario;
    }

    public void setId_tipoUsuario(int id_tipoUsuario) {
        this.id_tipoUsuario = id_tipoUsuario;
    }
}
