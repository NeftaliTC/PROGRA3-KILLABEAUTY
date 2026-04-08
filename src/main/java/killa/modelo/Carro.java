package killa.modelo;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
public class Carro {

    private int id_carrito;
    private Date fechaDeCreacion;
    private String estado; // con un enum tambien


    private Cliente cliente;
    private List<DetalleCarrito> items;

    public Carro() {
        this.items = new ArrayList<>();
    }
}
class DetalleCarrito {
    private int id_detalleCarrito;
    private int cantidad;

    // Navegabilidad
    private Producto producto;
    private Carro carro;
}
