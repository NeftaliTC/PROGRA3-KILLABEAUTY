package killa.modelo;
import java.util.Date;
public class Resena {
    private int id_resena;
    private String comentario;
    private int calificacion;
    private boolean verificado;
    private Date fechaDePublicacion;

    // NAVEGABILIDAD: La reseña debe conocer a quién la escribió y sobre qué producto
    private Cliente cliente;
    private Producto producto;

    public Resena() {
    }
}
