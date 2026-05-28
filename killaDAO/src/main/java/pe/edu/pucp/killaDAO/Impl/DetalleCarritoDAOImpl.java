package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.DetalleCarrito;
import pe.edu.pucp.killaDAO.DetalleCarritoDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleCarritoDAOImpl implements DetalleCarritoDAO {

    @Override
    public DetalleCarrito save(DetalleCarrito d) throws SQLException {
        String sql = "INSERT INTO DetalleCarrito (cantidad, id_producto, id_carrito) VALUES (?, ?, ?)";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, d.getCantidad());
            ps.setInt(2, d.getProducto().getIdProducto()); // De la composición
            ps.setInt(3, d.getIdCarrito());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) d.setIdDetalleCarrito(rs.getInt(1));
            }
        }
        return d;
    }

    @Override
    public DetalleCarrito load(Integer id) throws SQLException {
        String sql = "SELECT id_detalleCarrito, cantidad, id_producto, id_carrito FROM DetalleCarrito WHERE id_detalleCarrito = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DetalleCarrito d = new DetalleCarrito();
                    d.setIdDetalleCarrito(rs.getInt("id_detalleCarrito"));
                    d.setCantidad(rs.getInt("cantidad"));
                    d.getProducto().setIdProducto(rs.getInt("id_producto"));
                    d.setIdCarrito(rs.getInt("id_carrito"));
                    return d;
                }
            }
        }
        return null;
    }

    @Override
    public List<DetalleCarrito> listAll() throws SQLException {
        List<DetalleCarrito> lista = new ArrayList<>();
        String sql = "SELECT id_detalleCarrito, cantidad, id_producto, id_carrito FROM DetalleCarrito";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DetalleCarrito d = new DetalleCarrito();
                d.setIdDetalleCarrito(rs.getInt("id_detalleCarrito"));
                d.setCantidad(rs.getInt("cantidad"));
                d.getProducto().setIdProducto(rs.getInt("id_producto"));
                d.setIdCarrito(rs.getInt("id_carrito"));
                lista.add(d);
            }
        }
        return lista;
    }

    @Override
    public DetalleCarrito update(DetalleCarrito d) throws SQLException {
        String sql = "UPDATE DetalleCarrito SET cantidad = ? WHERE id_detalleCarrito = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, d.getCantidad());
            ps.setInt(2, d.getIdDetalleCarrito());
            ps.executeUpdate();
        }
        return d;
    }

    @Override
    public void remove(DetalleCarrito d) throws SQLException {
        String sql = "DELETE FROM DetalleCarrito WHERE id_detalleCarrito = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, d.getIdDetalleCarrito());
            ps.executeUpdate();
        }
    }
}
