package pe.edu.pucp.killaBeauty.killaModelo;

public class EscalaPrecio {
    private int cantidadMinima;
    private double precioUnitario;
    private int id;
    private Boolean activo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EscalaPrecio(){};

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
