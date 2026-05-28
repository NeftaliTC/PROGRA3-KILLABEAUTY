package pe.edu.pucp.killaBeauty.killaModelo;

public class RolPermiso {

    private TipoUsuario tipoUsuario;
    private Permiso permiso;
    private Boolean activo;

    public RolPermiso() {}

    public RolPermiso(TipoUsuario tipoUsuario, Permiso permiso) {
        this.tipoUsuario = tipoUsuario;
        this.permiso = permiso;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
