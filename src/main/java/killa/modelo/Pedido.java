package killa.modelo;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
public class Pedido {
    private int id_pedido;
    private Date fechaDePedido;
    private String estado;
    private String metodoDePago;
    private double subtotal;
    private double igv;
    private double total;

    private Cliente cliente;
    private Direccion direccionEnvio;
    private Cupon cupon;
    private List<DetallePedido> detalles;

    public Pedido() {
        this.detalles = new ArrayList<>();
    }
}
