package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.dbManager.TransactionContext;
import pe.edu.pucp.killaBeauty.killaModelo.CarritoDeCompras;
import pe.edu.pucp.killaBeauty.killaModelo.EstadoCarrito;
import pe.edu.pucp.killaDAO.CarritoDeComprasDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDeComprasDAOImpl implements CarritoDeComprasDAO {

    @Override
    public CarritoDeCompras load(Integer id) throws SQLException {
        String sql = "SELECT id_carrito, fecha_creacion, estado, id_usuario FROM CarritoDeCompras WHERE id_carrito = ?";
        CarritoDeCompras carrito = null;

        try (PreparedStatement ps = TransactionContext.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    carrito = new CarritoDeCompras();
                    carrito.setId(rs.getInt("id_carrito"));
                    carrito.setFechaDeCreacion(rs.getTimestamp("fecha_creacion"));
                    carrito.setEstado(EstadoCarrito.valueOf(rs.getString("estado")));
                }
            }
        }
        return carrito;
    }

    @Override
    public CarritoDeCompras save(CarritoDeCompras carrito) throws SQLException {
        Connection conn = TransactionContext.getConnection();

        // 1. Insertar carrito
        String sql = "INSERT INTO CarritoDeCompras (fecha_creacion, estado, id_usuario) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setTimestamp(1, new Timestamp(carrito.getFechaDeCreacion().getTime()));
            ps.setString(2, carrito.getEstado().name());
            ps.setInt(3, carrito.getUsuario().getId());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) carrito.setId(keys.getInt(1));
            }
        }

        // 2. Insertar detalles
        sql = "INSERT INTO DetalleCarrito (cantidad, id_producto, id_carrito) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for(var detalle : carrito.getDetalleCarritoList()) {
                ps.setInt(1, detalle.getCantidad());
                ps.setInt(2, detalle.getProducto().getId());
                ps.setInt(3, carrito.getId());
                ps.addBatch();
            }
            ps.executeBatch();
        }

        return carrito;
    }

    @Override
    public CarritoDeCompras update(CarritoDeCompras carrito) throws SQLException {
        String sql = "UPDATE CarritoDeCompras SET fecha_creacion = ?, estado = ? WHERE id_carrito = ?";
        try (PreparedStatement ps = TransactionContext.getConnection().prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(carrito.getFechaDeCreacion().getTime()));
            ps.setString(2, carrito.getEstado().name());
            ps.setInt(3, carrito.getId());
            ps.executeUpdate();
        }
        return carrito;
    }

    @Override
    public void remove(CarritoDeCompras carrito) throws SQLException {
        String sql = "UPDATE CarritoDeCompras SET activo = FALSE WHERE id_carrito = ?";
        try (PreparedStatement ps = TransactionContext.getConnection().prepareStatement(sql)) {
            ps.setInt(1, carrito.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public List<CarritoDeCompras> listAll() throws SQLException {
        List<CarritoDeCompras> lista = new ArrayList<>();
        String sql = "SELECT id_carrito, fecha_creacion, estado, id_usuario FROM CarritoDeCompras";
        try (PreparedStatement ps = TransactionContext.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                CarritoDeCompras c = new CarritoDeCompras();
                c.setId(rs.getInt("id_carrito"));
                c.setFechaDeCreacion(rs.getTimestamp("fecha_creacion"));
                c.setEstado(EstadoCarrito.valueOf(rs.getString("estado")));
                lista.add(c);
            }
        }
        return lista;
    }

    @Override
    public List<CarritoDeCompras> listByUsuarioId(int idUsuario) throws SQLException {
        List<CarritoDeCompras> lista = new ArrayList<>();
        String sql = "SELECT id_carrito, fecha_creacion, estado FROM CarritoDeCompras WHERE id_usuario = ?";
        try (PreparedStatement ps = TransactionContext.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    CarritoDeCompras c = new CarritoDeCompras();
                    c.setId(rs.getInt("id_carrito"));
                    c.setFechaDeCreacion(rs.getTimestamp("fecha_creacion"));
                    c.setEstado(EstadoCarrito.valueOf(rs.getString("estado")));
                    lista.add(c);
                }
            }
        }
        return lista;
    }
}
