package killa.modelo;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
public class Pedido {
    private int id_pedido;
    private Date fechaDePedido;
    private String estado;
    private double subtotal;
    private double igv;
    private double total;

    // Navegabilidad
    private Cliente cliente;
    private Descuento descuento; // FK id_descuento en tu imagen
    private List<DetallePedido> detalles;

    public Pedido() {
        this.detalles = new ArrayList<>();
    }
}
