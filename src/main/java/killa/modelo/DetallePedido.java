package killa.modelo;

public class DetallePedido {
    private int id_detallePedido;
    private int cantidad;
    private double precioAplicado;

    // Navegabilidad
    private Pedido pedido;
    private Producto producto;

    public DetallePedido() {}
}
