package pe.edu.pucp.killaBeauty.killaModelo;

import java.time.LocalDateTime;

public class Cupon {

    private int id;
    private String codigo;
    private String descripcion;
    private double porcentajeDeDescuento;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private boolean activo;

    // Constructores
    public Cupon() {}

    public Cupon(int id, String codigo, double porcentajeDeDescuento, LocalDateTime fechaInicio, LocalDateTime fechaFin, boolean activo) {
        this.id = id;
        this.codigo = codigo;
        this.porcentajeDeDescuento = porcentajeDeDescuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = activo;
    }

    // Métodos
    public boolean esVigente(LocalDateTime fecha) {
        return false;
    }

    public void validarVigencia() {}

    // Getters y setters
    public int getIdCupon() {
        return id;
    }

    public void setIdCupon(int id) {
        this.id = id;
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
        return porcentajeDeDescuento;
    }

    public double getPorcentajeDeDescuento() {
        return porcentajeDeDescuento;
    }

    public void setPorcentajeDeDescuento(double porcentajeDeDescuento) {
        this.porcentajeDeDescuento = porcentajeDeDescuento;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
