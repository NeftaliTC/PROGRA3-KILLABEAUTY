package killa.modelo;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Cliente {
    private int id_cliente;
    private String nombre;
    private String correoElectronico;
    private Date fechaDeInscripcion;
    private String contrasena;
    private String telefono;
    private String estado;
    // Navegabilidad: Un cliente puede ver sus pedidos realizados
    private List<Pedido> pedidos;

    public Cliente() {
        this.pedidos = new ArrayList<>();
    }
}
