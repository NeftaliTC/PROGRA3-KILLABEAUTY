package pe.edu.pucp.killabeauty.killarest.dto;

import pe.edu.pucp.killaBeauty.killaModelo.Producto;

public class ProductoCatalogoDTO {
    private int id;
    private String nombre;
    private double precioMinimo;
    private double precioMaximo;
    private String imagen;
    private String categoria;
    private String subcategoria;
    private String marca;
    private int calificacion;
    private boolean esPopular;

    public ProductoCatalogoDTO() {
    }

    public ProductoCatalogoDTO(Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.precioMinimo = producto.getPrecioBase();
        this.precioMaximo = producto.getPrecioBase();
        this.imagen = "Images/Logo.png";
        this.categoria = producto.getSubcategoria() != null
                && producto.getSubcategoria().getCategoria() != null
                && producto.getSubcategoria().getCategoria().getDescripcion() != null
                ? producto.getSubcategoria().getCategoria().getDescripcion()
                : "Categoria " + getIdSubcategoria(producto);
        this.subcategoria = producto.getSubcategoria() != null
                && producto.getSubcategoria().getDescripcion() != null
                ? producto.getSubcategoria().getDescripcion()
                : "Subcategoria " + getIdSubcategoria(producto);
        this.marca = producto.getMarca() != null && producto.getMarca().getDescripcion() != null
                ? producto.getMarca().getDescripcion()
                : "Marca " + getIdMarca(producto);
        this.calificacion = 4;
        this.esPopular = Boolean.TRUE.equals(producto.getPromocion());
    }

    private int getIdMarca(Producto producto) {
        return producto.getMarca() != null ? producto.getMarca().getId() : 0;
    }

    private int getIdSubcategoria(Producto producto) {
        return producto.getSubcategoria() != null ? producto.getSubcategoria().getId() : 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioMinimo() {
        return precioMinimo;
    }

    public void setPrecioMinimo(double precioMinimo) {
        this.precioMinimo = precioMinimo;
    }

    public double getPrecioMaximo() {
        return precioMaximo;
    }

    public void setPrecioMaximo(double precioMaximo) {
        this.precioMaximo = precioMaximo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public boolean getEsPopular() {
        return esPopular;
    }

    public void setEsPopular(boolean esPopular) {
        this.esPopular = esPopular;
    }
}
