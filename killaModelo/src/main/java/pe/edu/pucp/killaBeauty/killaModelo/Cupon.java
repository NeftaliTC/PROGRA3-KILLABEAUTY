package pe.edu.pucp.killaBeauty.killaModelo;
import java.time.LocalDate;

public class Cupon {

    private int id;
    private String codigo;
    private String descripcion;
    private double porcentaje;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean activo;
    private double montoMaximo;
    private double montoMinimoCompra;

    // Constructores
    public Cupon() {}

    public Cupon(int idCupon, String codigo, double porcentajeDeDescuento, LocalDate fechaInicio, LocalDate fechaFin, boolean activo) {
        this.id = idCupon;
        this.codigo = codigo;
        this.porcentaje = porcentajeDeDescuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = activo;
    }

    // Métodos
    public boolean esVigente(LocalDate fecha) {
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public double getMontoMaximo() { return montoMaximo; }

    public void setMontoMaximo(double montoMaximo) { this.montoMaximo = montoMaximo; }

    public double getMontoMinimoCompra() { return montoMinimoCompra; }

    public void setMontoMinimoCompra(double montoMinimoCompra) { this.montoMinimoCompra = montoMinimoCompra; }
}
