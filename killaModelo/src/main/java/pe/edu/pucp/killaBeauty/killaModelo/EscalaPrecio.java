package pe.edu.pucp.killaBeauty.killaModelo;

public class EscalaPrecio {
<<<<<<< HEAD

    private int id;
    private int cantidadMinima;
    private double precioUnitario;
    private Producto producto;

    public EscalaPrecio(){};

    public int getIdEscalaPrecio() {return id;}

=======
    private int cantidadMinima;
    private double precioUnitario;
    private int id;
    private boolean activo;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EscalaPrecio(){};

>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

<<<<<<< HEAD
    public Producto getProducto(){return producto;}

    public void setEscalaPrecio(int id){this.id = id;}

=======
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

<<<<<<< HEAD
    public void setProducto(Producto producto) {this.producto = producto;}
=======
    
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
}
