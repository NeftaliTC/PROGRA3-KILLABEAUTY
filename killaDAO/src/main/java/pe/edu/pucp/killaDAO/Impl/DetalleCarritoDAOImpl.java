package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.dbManager.TransactionContext;
import pe.edu.pucp.killaBeauty.killaModelo.DetalleCarrito;
import pe.edu.pucp.killaDAO.DetalleCarritoDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleCarritoDAOImpl implements DetalleCarritoDAO {

    @Override
    public DetalleCarrito load(Integer id) throws SQLException {
        String sql = "SELECT id_detalle, cantidad, id_producto, id_carrito FROM DetalleCarrito WHERE id_detalle = ?";
        try (PreparedStatement ps = TransactionContext.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    DetalleCarrito d = new DetalleCarrito();
                    d.setId(rs.getInt("id_detalle"));
                    d.setCantidad(rs.getInt("cantidad"));
                    return d;
                }
            }
        }
        return null;
    }

    @Override
    public DetalleCarrito save(DetalleCarrito d) throws SQLException {
        String sql = "INSERT INTO DetalleCarrito (cantidad, id_producto, id_carrito) VALUES (?, ?, ?)";
        try (PreparedStatement ps = TransactionContext.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, d.getCantidad());
            ps.setInt(2, d.getProducto().getId());
            ps.setInt(3, d.getCarritoDeCompras().getId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if(rs.next()) d.setId(rs.getInt(1));
            }
        }
        return d;
    }

    @Override
    public DetalleCarrito update(DetalleCarrito d) throws SQLException {
        String sql = "UPDATE DetalleCarrito SET cantidad = ? WHERE id_detalle = ?";
        try (PreparedStatement ps = TransactionContext.getConnection().prepareStatement(sql)) {
            ps.setInt(1, d.getCantidad());
            ps.setInt(2, d.getId());
            ps.executeUpdate();
        }
        return d;
    }

    @Override
    public void remove(DetalleCarrito d) throws SQLException {
        // Sin columna activo, eliminación física
        String sql = "DELETE FROM DetalleCarrito WHERE id_detalle = ?";
        try (PreparedStatement ps = TransactionContext.getConnection().prepareStatement(sql)) {
            ps.setInt(1, d.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public List<DetalleCarrito> listByCarritoId(int idCarrito) throws SQLException {
        List<DetalleCarrito> lista = new ArrayList<>();
        String sql = "SELECT id_detalle, cantidad, id_producto FROM DetalleCarrito WHERE id_carrito = ?";
        try (PreparedStatement ps = TransactionContext.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idCarrito);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    DetalleCarrito d = new DetalleCarrito();
                    d.setId(rs.getInt("id_detalle"));
                    d.setCantidad(rs.getInt("cantidad"));
                    lista.add(d);
                }
            }
        }
        return lista;
    }
}
