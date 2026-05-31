package pe.edu.pucp.killaBeauty.killaModelo.Promocionales;
import java.time.LocalDate;

public class Cupon {
    private int id;
    private String codigo;
    private String descripcion;
    private double valorDescuento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean activo;
    private double montoMaximoDescuento;
    private double montoMinimoCompra;
    private TipoDescuento tipoDescuento;
    private int maxUsosGenerales;
    private Campana campana;
    // Constructores
    public Cupon() {}

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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public double getMontoMaximoDescuento() {
        return montoMaximoDescuento;
    }

    public double getMontoMaximo() {
        return this.montoMaximoDescuento;
    }

    public void setMontoMaximo(double montoMaximo) {
        this.montoMaximoDescuento = montoMaximo;
    }

    public void setMontoMaximoDescuento(double montoMaximoDescuento) {
        this.montoMaximoDescuento = montoMaximoDescuento;
    }

    public double getMontoMinimoCompra() {
        return montoMinimoCompra;
    }

    public void setMontoMinimoCompra(double montoMinimoCompra) {
        this.montoMinimoCompra = montoMinimoCompra;
    }

    public int getMaxUsosGenerales() {
        return maxUsosGenerales;
    }

    public void setMaxUsosGenerales(int maxUsosGenerales) {
        this.maxUsosGenerales = maxUsosGenerales;
    }

    public Campana getCampana() {
        return campana;
    }

    public void setCampana(Campana campana) {
        this.campana = campana;
    }

    public TipoDescuento getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(TipoDescuento tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(double valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public int getIdCupon() {
        return this.id;
    }

    public void setIdCupon(int idCupon) {
        this.id = idCupon;
    }
    public boolean esVigente(LocalDate fecha) {
        if (!this.activo || fecha == null) {
            return false;
        }
        return (!fecha.isBefore(this.fechaInicio) && !fecha.isAfter(this.fechaFin));
    }


    public void validarVigencia() {
        LocalDate hoy = LocalDate.now();
        if (!esVigente(hoy)) {
            this.activo = false;
        }
    }
}
