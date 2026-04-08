package killa.modelo;
import java.util.Date;
public class Descuento {
    private int idDescuento;
    private String descripcion;
    private double porcentajeDeDescuento;
    private Date fechaInicio;
    private Date fechaFin;

    //Constructores
    public Descuento() {}

    public Descuento(int idDescuento, String descripcion, double porcentajeDeDescuento) {
        this.idDescuento = idDescuento;
        this.descripcion = descripcion;
        this.porcentajeDeDescuento = porcentajeDeDescuento;
    }

    //Metodos
    public void validarVigencia() {}
    
    //Getters y setters
    public int getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(int idDescuento) {
        this.idDescuento = idDescuento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPorcentajeDeDescuento() {
        return porcentajeDeDescuento;
    }

    public void setPorcentajeDeDescuento(double porcentajeDeDescuento) {
        this.porcentajeDeDescuento = porcentajeDeDescuento;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
