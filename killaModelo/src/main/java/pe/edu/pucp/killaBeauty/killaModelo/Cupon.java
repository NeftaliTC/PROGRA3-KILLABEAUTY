package pe.edu.pucp.killaBeauty.killaModelo;
import java.time.LocalDate;

public class Cupon {

    private int id;
    private String descripcion;
    private String codigo;
    private double valorDescuento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean activo;
    private double montoMinimoCompra;
    private double montoMaximoDescuento;
    private int maxUsosGenerales;

    private TipoDescuento tipoDescuento;
    private Campana campana;

    public Cupon() {
    }

    public Cupon(int id, String descripcion, String codigo, double valorDescuento, LocalDate fechaInicio, LocalDate fechaFin, Boolean activo, double montoMinimoCompra, double montoMaximoDescuento, int maxUsosGenerales, TipoDescuento tipoDescuento, Campana campana) {
        this.id = id;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.valorDescuento = valorDescuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = activo;
        this.montoMinimoCompra = montoMinimoCompra;
        this.montoMaximoDescuento = montoMaximoDescuento;
        this.maxUsosGenerales = maxUsosGenerales;
        this.tipoDescuento = tipoDescuento;
        this.campana = campana;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(double valorDescuento) {
        this.valorDescuento = valorDescuento;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public double getMontoMinimoCompra() {
        return montoMinimoCompra;
    }

    public void setMontoMinimoCompra(double montoMinimoCompra) {
        this.montoMinimoCompra = montoMinimoCompra;
    }

    public double getMontoMaximoDescuento() {
        return montoMaximoDescuento;
    }

    public void setMontoMaximoDescuento(double montoMaximoDescuento) {
        this.montoMaximoDescuento = montoMaximoDescuento;
    }

    public int getMaxUsosGenerales() {
        return maxUsosGenerales;
    }

    public void setMaxUsosGenerales(int maxUsosGenerales) {
        this.maxUsosGenerales = maxUsosGenerales;
    }

    public TipoDescuento getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(TipoDescuento tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public Campana getCampana() {
        return campana;
    }

    public void setCampana(Campana campana) {
        this.campana = campana;
    }
}
