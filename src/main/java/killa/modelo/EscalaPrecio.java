package killa.modelo;

public class EscalaPrecio {
    private int cantidadMinima;
    private double precioUnitario;
    
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

    
}
