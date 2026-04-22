package pe.edu.pucp.killaBeauty.killaModelo;
import java.util.Date;

public class Cupon {

    private int id;
    private String codigo;
    private String descripcion;
    private double porcentaje;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean activo;

    // Constructores
    public Cupon() {}

    public Cupon(int idCupon, String codigo, double porcentajeDeDescuento, Date fechaInicio, Date fechaFin, boolean activo) {
        this.id = idCupon;
        this.codigo = codigo;
        this.porcentaje = porcentajeDeDescuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = activo;
    }

    // Métodos
    public boolean esVigente(Date fecha) {
        return false;
    }

    public void validarVigencia() {}

    // Getters y setters
    public int getIdCupon() {
        return id;
    }

    public void setIdCupon(int idCupon) {
        this.id = idCupon;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPorcentajeDescuento() {
        return porcentaje;
    }

    public double getPorcentajeDeDescuento() {
        return porcentaje;
    }

    public void setPorcentajeDeDescuento(double porcentajeDeDescuento) {
        this.porcentaje = porcentajeDeDescuento;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
