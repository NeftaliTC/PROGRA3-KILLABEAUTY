package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.Permiso;
import pe.edu.pucp.killaBeauty.killaModelo.RolPermiso;
import pe.edu.pucp.killaBeauty.killaModelo.TipoUsuario;
import pe.edu.pucp.killaDAO.RolPermisoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolPermisoDAOImpl implements RolPermisoDAO {

    @Override
    public List<RolPermiso> listAll() throws SQLException {
        List<RolPermiso> lista = new ArrayList<>();
        String sql = """
            SELECT rp.id_tipoUsuario, p.id_permiso, p.nombre, p.descripcion
            FROM Rol_Permiso rp
            INNER JOIN Permiso p ON p.id_permiso = rp.id_permiso
        """;

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TipoUsuario tipo = TipoUsuario.fromId(rs.getInt("id_tipoUsuario"));

                Permiso permiso = new Permiso();
                permiso.setId(rs.getInt("id_permiso"));
                permiso.setNombre(rs.getString("nombre"));
                permiso.setDescripcion(rs.getString("descripcion"));

                RolPermiso rp = new RolPermiso();
                rp.setTipoUsuario(tipo);
                rp.setPermiso(permiso);

                lista.add(rp);
            }
        }
        return lista;
    }

    @Override
    public RolPermiso load(Integer idPermiso) throws SQLException {
        // Como BaseDAO pide solo 1 ID, aquí cargamos por id_permiso
        String sql = """
            SELECT rp.id_tipoUsuario, p.id_permiso, p.nombre, p.descripcion
            FROM Rol_Permiso rp
            INNER JOIN Permiso p ON p.id_permiso = rp.id_permiso
            WHERE rp.id_permiso = ?
            LIMIT 1
        """;

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idPermiso);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TipoUsuario tipo = TipoUsuario.fromId(rs.getInt("id_tipoUsuario"));

                    Permiso permiso = new Permiso();
                    permiso.setId(rs.getInt("id_permiso"));
                    permiso.setNombre(rs.getString("nombre"));
                    permiso.setDescripcion(rs.getString("descripcion"));

                    return new RolPermiso(tipo, permiso);
                }
            }
        }
        return null;
    }

    @Override
    public RolPermiso save(RolPermiso rolPermiso) throws SQLException {
        String sql = "INSERT INTO Rol_Permiso (id_tipoUsuario, id_permiso) VALUES (?, ?)";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, rolPermiso.getTipoUsuario().getId());
            ps.setInt(2, rolPermiso.getPermiso().getId());
            ps.executeUpdate();
        }
        return rolPermiso;
    }

    @Override
    public RolPermiso update(RolPermiso rolPermiso) throws SQLException {
        // No hay "id" único. Normalmente en tabla muchos a muchos no se hace update,
        // se elimina y se vuelve a insertar.
        throw new UnsupportedOperationException("Tabla puente con PK compuesta: use remove + save");
    }

    @Override
    public void remove(RolPermiso rolPermiso) throws SQLException {
        String sql = "DELETE FROM Rol_Permiso WHERE id_tipoUsuario = ? AND id_permiso = ?";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, rolPermiso.getTipoUsuario().getId());
            ps.setInt(2, rolPermiso.getPermiso().getId());
            ps.executeUpdate();
        }
    }
}
