package pe.edu.pucp.killaBeauty.killaModelo;

public enum MetodoPago {
    YAPE(1, "YAPE"),
    PLIN(2, "PLIN"),
    TRANSFERENCIA(3, "TRANSFERENCIA"),
    TARJETA(4, "TARJETA");

    private final int id;
    private final String nombre;

    MetodoPago(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public static MetodoPago fromId(int id) {
        for (MetodoPago metodo : MetodoPago.values()) {
            if (metodo.getId() == id) {
                return metodo;
            }
        }
        throw new IllegalArgumentException("Método de pago inválido con id: " + id);
    }}
