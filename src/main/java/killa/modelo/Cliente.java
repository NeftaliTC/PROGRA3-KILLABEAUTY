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
    private String estado; // activo - inactivo
    private Direccion direccion;
    private List<Pedido> pedidos;
    private Carro carritoActivo;

    public Cliente() {
        this.pedidos = new ArrayList<>();
    }
}
class Direccion {
    private int id_direccion;
    private String distrito;
    private String telefono;
    private String direccion;
    private String referencias;
}
