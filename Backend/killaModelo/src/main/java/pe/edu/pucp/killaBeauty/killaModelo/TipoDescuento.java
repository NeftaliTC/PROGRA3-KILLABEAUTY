package pe.edu.pucp.killaBeauty.killaModelo;

public enum TipoDescuento {
    PORCENTAJE(1, "Porcentaje"),
    MONTOFIJO(2, "Monto Fijo");

    private final int id;
    private final String nombre;

    TipoDescuento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return nombre;
    }
}
