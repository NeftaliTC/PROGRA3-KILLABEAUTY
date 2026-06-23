package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.Producto;
import pe.edu.pucp.killaDAO.ImagenProductoDAO;
import pe.edu.pucp.killaDAO.ProductoDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    ImagenProductoDAO imagenDAO = new ImagenProductoDAOImpl();

    @Override
    public List<Producto> listAll() throws SQLException {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT id_producto, nombre, precio_base, stock, disponible, promocion, activo, id_marca, id_subcategoria FROM Producto";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecioBase(rs.getDouble("precio_base"));
                p.setStock(rs.getInt("stock"));
                p.setDisponible(rs.getBoolean("disponible"));
                p.setPromocion(rs.getBoolean("promocion"));
                p.setActivo(rs.getBoolean("activo"));
                p.getMarca().setId(rs.getInt("id_marca"));
                p.getSubcategoria().setId(rs.getInt("id_subcategoria"));
                p.setImagenes(imagenDAO.listByProductoId(p.getId()));

                lista.add(p);
            }
        }
        return lista;
    }

    @Override
    public Producto load(Integer id) throws SQLException {
        String sql = "SELECT id_producto, nombre, precio_base, stock, disponible, promocion, activo, id_marca, id_subcategoria FROM Producto WHERE id_producto = ?";
        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Producto p = new Producto();
                    p.setId(rs.getInt("id_producto"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecioBase(rs.getDouble("precio_base"));
                    p.setStock(rs.getInt("stock"));
                    p.setDisponible(rs.getBoolean("disponible"));
                    p.setPromocion(rs.getBoolean("promocion"));
                    p.setActivo(rs.getBoolean("activo"));
                    p.getMarca().setId(rs.getInt("id_marca"));
                    p.getSubcategoria().setId(rs.getInt("id_subcategoria"));
                    p.setImagenes(imagenDAO.listByProductoId(p.getId()));

                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public Producto save(Producto p) throws SQLException {
        String sql = "INSERT INTO Producto (nombre, precio_base, stock, disponible, promocion, activo, id_marca, id_subcategoria) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecioBase());
            ps.setInt(3, p.getStock());
            ps.setBoolean(4, p.getDisponible());
            ps.setBoolean(5, p.getPromocion());
            ps.setBoolean(6, p.getActivo());
            ps.setInt(7, p.getMarca().getId());
            ps.setInt(8, p.getSubcategoria().getId());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) p.setId(keys.getInt(1));
            }
        }
        return p;
    }

    @Override
    public Producto update(Producto p) throws SQLException {
        String sql = "UPDATE Producto SET nombre = ?, precio_base = ?, stock = ?, disponible = ?, promocion = ?, activo = ?, id_marca = ?, id_subcategoria = ? WHERE id_producto = ?";
        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecioBase());
            ps.setInt(3, p.getStock());
            ps.setBoolean(4, p.getDisponible());
            ps.setBoolean(5, p.getPromocion());
            ps.setBoolean(6, p.getActivo());
            ps.setInt(7, p.getMarca().getId());
            ps.setInt(8, p.getSubcategoria().getId());
            ps.setInt(9, p.getId());
            ps.executeUpdate();
        }
        return p;
    }

    @Override
    public void remove(Producto p) throws SQLException {
        p.setActivo(false);
        String sql = "UPDATE Producto SET activo = ? WHERE id_producto = ?";
        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setBoolean(1, p.getActivo());
            ps.setInt(2, p.getId());
            ps.executeUpdate();
        }
    }
}
