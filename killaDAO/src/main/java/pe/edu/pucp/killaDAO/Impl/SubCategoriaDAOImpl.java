package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.Subcategoria;
import pe.edu.pucp.killaDAO.SubCategoriaDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubCategoriaDAOImpl implements SubCategoriaDAO {

    @Override
    public List<Subcategoria> listAll() throws SQLException {
        List<Subcategoria> lista = new ArrayList<>();
        String sql = "SELECT id_subcategoria, descripcion FROM Subcategoria";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Subcategoria s = new Subcategoria();
                s.setId(rs.getInt("id_subcategoria"));
                s.setNombre(rs.getString("descripcion"));
                lista.add(s);
            }
        }
        return lista;
    }

    @Override
    public List<Subcategoria> listByCategoriaId(Integer idCategoria) throws SQLException {
        List<Subcategoria> lista = new ArrayList<>();
        String sql = "SELECT id_subcategoria, descripcion FROM Subcategoria WHERE id_categoria = ?";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Subcategoria s = new Subcategoria();
                    s.setId(rs.getInt("id_subcategoria"));
                    s.setNombre(rs.getString("descripcion"));
                    lista.add(s);
                }
            }
        }
        return lista;
    }

    @Override
    public Subcategoria load(Integer id) throws SQLException {
        String sql = "SELECT id_subcategoria, descripcion FROM Subcategoria WHERE id_subcategoria = ?";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Subcategoria s = new Subcategoria();
                    s.setId(rs.getInt("id_subcategoria"));
                    s.setNombre(rs.getString("descripcion"));
                    return s;
                }
            }
        }
        return null;
    }

    // BaseDAO exige este metodo, pero falta idCategoria
    @Override
    public Subcategoria save(Subcategoria t) throws SQLException {
        throw new UnsupportedOperationException("Use save(Subcategoria, Integer idCategoria)");
    }

    @Override
    public Subcategoria save(Subcategoria subcategoria, Integer idCategoria) throws SQLException {
        String sql = "INSERT INTO Subcategoria (descripcion, id_categoria) VALUES (?, ?)";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, subcategoria.getNombre());
            ps.setInt(2, idCategoria);

            int affected = ps.executeUpdate();
            if (affected > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        subcategoria.setId(keys.getInt(1));
                    }
                }
            }
        }
        return subcategoria;
    }

    @Override
    public Subcategoria update(Subcategoria t) throws SQLException {
        throw new UnsupportedOperationException("Use update(Subcategoria, Integer idCategoria)");
    }

    @Override
    public Subcategoria update(Subcategoria subcategoria, Integer idCategoria) throws SQLException {
        String sql = "UPDATE Subcategoria SET descripcion = ?, id_categoria = ? WHERE id_subcategoria = ?";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, subcategoria.getNombre());
            ps.setInt(2, idCategoria);
            ps.setInt(3, subcategoria.getId());

            ps.executeUpdate();
        }
        return subcategoria;
    }

    @Override
    public void remove(Subcategoria subcategoria) throws SQLException {
        String sql = "DELETE FROM Subcategoria WHERE id_subcategoria = ?";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, subcategoria.getId());
            ps.executeUpdate();
        }
    }
}
