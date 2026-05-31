package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.Producto;
import pe.edu.pucp.killaDAO.ProductoDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public Producto save(Producto p) throws SQLException {
        String sql = "INSERT INTO Producto (nombre, precio_base, stock, disponible, promocion, id_marca, id_categoria) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecioBase());
            ps.setInt(3, p.getStock());
            ps.setBoolean(4, p.getDisponible());
            ps.setBoolean(5, p.getPromocion());
            ps.setInt(6, p.getMarca().getId()); // Composición
            ps.setInt(7, p.getSubcategoria().getId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setIdProducto(rs.getInt(1));
            }
        }
        return p;
    }

    @Override
    public Producto load(Integer id) throws SQLException {
        String sql = "SELECT id_producto, nombre, precio_base, stock, disponible, promocion, id_marca, id_categoria FROM Producto WHERE id_producto = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Producto p = new Producto();
                    p.setIdProducto(rs.getInt("id_producto"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecioBase(rs.getDouble("precio_base"));
                    p.setStock(rs.getInt("stock"));
                    p.setDisponible(rs.getBoolean("disponible"));
                    p.setPromocion(rs.getBoolean("promocion"));
                    p.getMarca().setId(rs.getInt("id_marca"));
                    p.getSubcategoria().setId(rs.getInt("id_categoria"));
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public List<Producto> listAll() throws SQLException {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT id_producto, nombre, precio_base, stock, disponible, promocion, id_marca, id_categoria FROM Producto";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecioBase(rs.getDouble("precio_base"));
                p.setStock(rs.getInt("stock"));
                p.setDisponible(rs.getBoolean("disponible"));
                p.setPromocion(rs.getBoolean("promocion"));
                p.getMarca().setId(rs.getInt("id_marca"));
                p.getSubcategoria().setId(rs.getInt("id_categoria"));
                lista.add(p);
            }
        }
        return lista;
    }

    @Override
    public Producto update(Producto p) throws SQLException {
        String sql = "UPDATE Producto SET nombre = ?, precio_base = ?, stock = ?, disponible = ?, promocion = ? WHERE id_producto = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecioBase());
            ps.setInt(3, p.getStock());
            ps.setBoolean(4, p.getDisponible());
            ps.setBoolean(5, p.getPromocion());
            ps.setInt(6, p.getIdProducto());
            ps.executeUpdate();
        }
        return p;
    }

    @Override
    public void remove(Producto p) throws SQLException {
        String sql = "DELETE FROM Producto WHERE id_producto = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getIdProducto());
            ps.executeUpdate();
        }
    }
}
