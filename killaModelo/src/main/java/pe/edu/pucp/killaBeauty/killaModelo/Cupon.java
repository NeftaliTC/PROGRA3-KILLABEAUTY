package pe.edu.pucp.killaBeauty.killaModelo;
<<<<<<< HEAD

import java.time.LocalDateTime;

public class Cupon {

    private int id;
    private String codigo;
    private String descripcion;
    private double porcentajeDeDescuento;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
=======
import java.util.Date;

public class Cupon {

    private int idCupon;
    private String codigo;
    private String descripcion;
    private double porcentajeDeDescuento;
    private Date fechaInicio;
    private Date fechaFin;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
    private boolean activo;

    // Constructores
    public Cupon() {}

<<<<<<< HEAD
    public Cupon(int id, String codigo, double porcentajeDeDescuento, LocalDateTime fechaInicio, LocalDateTime fechaFin, boolean activo) {
        this.id = id;
=======
    public Cupon(int idCupon, String codigo, double porcentajeDeDescuento, Date fechaInicio, Date fechaFin, boolean activo) {
        this.idCupon = idCupon;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
        this.codigo = codigo;
        this.porcentajeDeDescuento = porcentajeDeDescuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = activo;
    }

    // Métodos
<<<<<<< HEAD
    public boolean esVigente(LocalDateTime fecha) {
=======
    public boolean esVigente(Date fecha) {
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
        return false;
    }

    public void validarVigencia() {}

    // Getters y setters
    public int getIdCupon() {
<<<<<<< HEAD
        return id;
    }

    public void setIdCupon(int id) {
        this.id = id;
=======
        return idCupon;
    }

    public void setIdCupon(int idCupon) {
        this.idCupon = idCupon;
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
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

<<<<<<< HEAD
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
=======
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
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
        this.fechaFin = fechaFin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
<<<<<<< HEAD

=======
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
}
