package pe.edu.pucp.killaBeauty.killaModelo;

public class Courier {
    private int id;
    private String nombre;
    private int RUC;
    private int telefono;
    private boolean activo;
    private String correo;

    public Courier() {
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

    public int getRUC() {
        return RUC;
    }

    public void setRUC(int RUC) {
        this.RUC = RUC;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
