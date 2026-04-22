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
        String sql = "SELECT id_permiso, nombre, descripcion FROM Permiso";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Permiso p = new Permiso();
                p.setId(rs.getInt("id_permiso"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                lista.add(p);
            }
        }
        return lista;
    }

    @Override
    public Permiso load(Integer id) throws SQLException {
        String sql = "SELECT id_permiso, nombre, descripcion FROM Permiso WHERE id_permiso = ?";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Permiso p = new Permiso();
                    p.setId(rs.getInt("id_permiso"));
                    p.setNombre(rs.getString("nombre"));
                    p.setDescripcion(rs.getString("descripcion"));
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public Permiso save(Permiso permiso) throws SQLException {
        String sql = "INSERT INTO Permiso (nombre, descripcion) VALUES (?, ?)";

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
        String sql = "UPDATE Permiso SET nombre = ?, descripcion = ? WHERE id_permiso = ?";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, permiso.getNombre());
            ps.setString(2, permiso.getDescripcion());
            ps.setInt(3, permiso.getId());

            ps.executeUpdate();
        }
        return permiso;
    }

    @Override
    public void remove(Permiso permiso) throws SQLException {
        String sql = "DELETE FROM Permiso WHERE id_permiso = ?";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, permiso.getId());
            ps.executeUpdate();
        }
    }
}
