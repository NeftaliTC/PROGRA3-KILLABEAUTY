package pe.edu.pucp.killaBeauty.killaModelo;
// CORREGIDA
public class RolPermiso {

    private int id;
    private TipoUsuario tipoUsuario;
    private Permiso permiso;

        public RolPermiso() {}

        public RolPermiso(int id, TipoUsuario tipoUsuario, Permiso permiso) {
            this.id = id;
            this.tipoUsuario = tipoUsuario;
            this.permiso = permiso;
        }

        // Getters y Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public TipoUsuario getTipoUsuario() { return tipoUsuario; }
        public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }
        public Permiso getPermiso() { return permiso; }
        public void setPermiso(Permiso permiso) { this.permiso = permiso; }
}
