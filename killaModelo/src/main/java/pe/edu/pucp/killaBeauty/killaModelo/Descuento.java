package pe.edu.pucp.killaBeauty.killaModelo;

import java.time.LocalDateTime;

public class Descuento {
    private int id;
    private String descripcion;
    private double porcentajeDeDescuento;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    //Constructores
    public Descuento() {}

    public Descuento(int id, String descripcion, double porcentajeDeDescuento) {
        this.id = id;
        this.descripcion = descripcion;
        this.porcentajeDeDescuento = porcentajeDeDescuento;
        this.fechaInicio = LocalDateTime.now(); //inicializa con la fecha completa de creacion
        this.fechaFin = LocalDateTime.now();
    }

    //Metodos
    public void validarVigencia() {}

    //Getters y setters
    public int getIdDescuento() {
        return id;
    }

    public void setIdDescuento(int id) {
        this.id = id;
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
}
