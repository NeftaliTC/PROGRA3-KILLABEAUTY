package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.Permiso;
import pe.edu.pucp.killaDAO.PermisoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PermisoDAOImpl implements PermisoDAO {

    @Override
    public List<Permiso> listAll() throws SQLException {
        List<Permiso> lista = new ArrayList<>();
        String sql = "SELECT id_permiso, nombre, descripcion, activo FROM Permiso";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Permiso p = new Permiso();
                p.setId(rs.getInt("id_permiso"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setActivo(rs.getBoolean("activo"));
                lista.add(p);
            }
        }
        return lista;
    }

    @Override
    public Permiso load(Integer id) throws SQLException {
        String sql = "SELECT id_permiso, nombre, descripcion, activo FROM Permiso WHERE id_permiso = ?";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Permiso p = new Permiso();
                    p.setId(rs.getInt("id_permiso"));
                    p.setNombre(rs.getString("nombre"));
                    p.setDescripcion(rs.getString("descripcion"));
                    p.setActivo(rs.getBoolean("activo"));
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public Permiso save(Permiso permiso) throws SQLException {
        String sql = "INSERT INTO Permiso (nombre, descripcion, activo) VALUES (?, ?, 1)";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, permiso.getNombre());
            ps.setString(2, permiso.getDescripcion());

            int affected = ps.executeUpdate();
            if (affected > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        permiso.setId(keys.getInt(1));
                    }
                }
            }
        }
        return permiso;
    }

    @Override
    public Permiso update(Permiso permiso) throws SQLException {
        String sql = "UPDATE Permiso SET nombre = ?, descripcion = ?, activo = ? WHERE id_permiso = ?";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, permiso.getNombre());
            ps.setString(2, permiso.getDescripcion());
            ps.setBoolean(3, permiso.getActivo());
            ps.setInt(4, permiso.getId());

            ps.executeUpdate();
        }
        return permiso;
    }

    @Override
    public void remove(Permiso permiso) throws SQLException {
        String sql = "UPDATE Permiso SET activo = 0 WHERE id_permiso = ?";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, permiso.getId());
            ps.executeUpdate();
        }
    }
}
