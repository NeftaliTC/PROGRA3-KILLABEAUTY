package pe.edu.pucp.killaBeauty.killaModelo;

<<<<<<< HEAD
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
=======
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
// REVISADA
public class Usuario {
    private int idCliente;
    private String nombre;
    private String correoElectronico;
    private Date fechaDeInscripcion;
    private String contrasena; //-> estado
    private String telefono;
    private String estado; // activo - inactivo
    private List<Direccion> direcciones;
    private List<Pedido> pedidos;
    private Carrito carritoActivo;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8

    //Constructores
    public Usuario() {
        this.pedidos = new ArrayList<>();
        this.direcciones = new ArrayList<>();
    }

<<<<<<< HEAD
    //Getters y setters
    public int getIdUsuario() {
        return id;
    }

    public void setIdUsuario(int id) {
        this.id = id;
=======
    //Metodos
    public void registrarse() {}
    public void iniciarSesion(String correoElectronico, String contrasena) {}
    public void realizarPedido() {}
    public void actualizarDatos() {}
    public void agregarDireccion(Direccion direccionNueva) {}
    public void agregarPedido(Pedido pedidoNuevo) {}
    
    //Getters y setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
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

<<<<<<< HEAD
    public LocalDateTime getFechaDeInscripcion() {
        return fechaDeInscripcion;
    }

    public void setFechaDeInscripcion(LocalDateTime fechaDeInscripcion) {
=======
    public Date getFechaDeInscripcion() {
        return fechaDeInscripcion;
    }

    public void setFechaDeInscripcion(Date fechaDeInscripcion) {
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
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

<<<<<<< HEAD
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
=======
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
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

<<<<<<< HEAD
    public CarritoDeCompras getCarritoActivo() {
        return carritoActivo;
    }

    public void setCarritoActivo(CarritoDeCompras carritoActivo) {
        this.carritoActivo = carritoActivo;
    }

    public TipoUsuario getTipoUsuario(){return tipoUsuario;}

    public void setTipoUsuario(TipoUsuario tipoUsuario){this.tipoUsuario = tipoUsuario;}
=======
    public Carrito getCarritoActivo() {
        return carritoActivo;
    }

    public void setCarritoActivo(Carrito carritoActivo) {
        this.carritoActivo = carritoActivo;
    }
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
}
