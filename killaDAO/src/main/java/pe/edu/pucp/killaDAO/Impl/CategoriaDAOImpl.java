package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.Categoria;
import pe.edu.pucp.killaDAO.CategoriaDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {

    @Override
    public List<Categoria> listAll() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id_categoria, descripcion FROM Categoria";

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("descripcion"));
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    @Override
    public Categoria load(Integer id) throws SQLException {
        String sql = "SELECT id_categoria, descripcion FROM Categoria WHERE id_categoria = ?";

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getInt("id_categoria"));
                    categoria.setNombre(rs.getString("descripcion"));
                    return categoria;
                }
            }
        }
        return null;
    }

    @Override
    public Categoria save(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO Categoria (descripcion) VALUES (?)";

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, categoria.getNombre());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet keys = pstmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        categoria.setId(keys.getInt(1));
                    }
                }
            }
        }
        return categoria;
    }

    @Override
    public Categoria update(Categoria categoria) throws SQLException {
        String sql = "UPDATE Categoria SET descripcion = ? WHERE id_categoria = ?";

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, categoria.getNombre());
            pstmt.setInt(2, categoria.getId());

            pstmt.executeUpdate();
        }
        return categoria;
    }

    @Override
    public void remove(Categoria categoria) throws SQLException {
        // En tu tabla no hay campo activo -> eliminación física
        String sql = "DELETE FROM Categoria WHERE id_categoria = ?";

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, categoria.getId());
            pstmt.executeUpdate();
        }
    }
}
