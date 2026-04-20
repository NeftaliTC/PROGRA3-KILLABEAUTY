package pe.edu.pucp.killaBeauty.killaModelo;

public class EscalaPrecio {

    private int id;
    private int cantidadMinima;
    private double precioUnitario;
    private Producto producto;

    public EscalaPrecio(){};

    public int getIdEscalaPrecio() {return id;}

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public Producto getProducto(){return producto;}

    public void setEscalaPrecio(int id){this.id = id;}

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setProducto(Producto producto) {this.producto = producto;}
}
