package pe.edu.pucp.killaBeauty.killaModelo;
import java.time.LocalDate;

public class Resena {
    private int id;
    private String titulo;
    private String comentario;
    private int calificacion;
    private boolean verificado;
    private LocalDate fechaPublicacion;

    private Usuario cliente;
    private Producto producto;

    public Resena() {
        this.fechaPublicacion = LocalDate.now();
        this.cliente = new Usuario();
        this.producto = new Producto();
    }

    public int getIdResena() {
        return id;
    }

    public void setIdResena(int idResena) {
        this.id = idResena;
    }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

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

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void validarCalificacion() {}

}
