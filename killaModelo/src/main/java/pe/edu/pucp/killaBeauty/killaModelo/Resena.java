package pe.edu.pucp.killaBeauty.killaModelo;
<<<<<<< HEAD

import java.time.LocalDateTime;

public class Resena {
    private int id;
    private String comentario;
    private int calificacion;
    private boolean verificado;
    private LocalDateTime fechaPublicacion;

    private Usuario usuario;
=======
import java.util.Date;
public class Resena {
    private int idResena;
    private String comentario;
    private int calificacion;
    private boolean verificado;
    private Date fechaPublicacion;

    private Usuario cliente;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
    private Producto producto;

    public Resena() {}

    public int getIdResena() {
<<<<<<< HEAD
        return id;
    }

    public void setIdResena(int id) {
        this.id = id;
=======
        return idResena;
    }

    public void setIdResena(int idResena) {
        this.idResena = idResena;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

<<<<<<< HEAD
    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
=======
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

<<<<<<< HEAD
=======
    public void validarCalificacion() {}
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
}
