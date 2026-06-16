package pe.edu.pucp.killaBeauty.killaModelo;

public enum TipoComprobante {
    BOLETA(1, "Boleta de Venta"),
    FACTURA(2, "Factura Comercial");

    private final int id;
    private final String descripcion;

    private TipoComprobante(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoComprobante getById(int id) {
        for (TipoComprobante tipo : TipoComprobante.values()) {
            if (tipo.getId() == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("ID de comprobante inválido: " + id);
    }
}
