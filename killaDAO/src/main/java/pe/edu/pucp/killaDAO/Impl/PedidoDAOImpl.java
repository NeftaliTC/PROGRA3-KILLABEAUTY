package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.dbManager.TransactionContext;
import pe.edu.pucp.killaBeauty.killaModelo.Direccion;
import pe.edu.pucp.killaBeauty.killaModelo.EstadoPedido;
import pe.edu.pucp.killaBeauty.killaModelo.Pedido;
import pe.edu.pucp.killaBeauty.killaModelo.Usuario;
import pe.edu.pucp.killaBeauty.killaModelo.DetallePedido;
import pe.edu.pucp.killaBeauty.killaModelo.Promocionales.Cupon;
import pe.edu.pucp.killaDAO.PedidoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl implements PedidoDAO {

    @Override
    public List<Pedido> listAll() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = """
                SELECT id_pedido, id_usuario, id_direccion, id_cupon, fecha_pedido,
                       subtotal, igv, total, id_estado_pedido
                FROM Pedido
                """;

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) pedidos.add(mapRow(rs));
        }
        return pedidos;
    }

    @Override
    public Pedido load(Integer id) throws SQLException {
        String sql = """
                SELECT id_pedido, id_usuario, id_direccion, id_cupon, fecha_pedido,
                       subtotal, igv, total, id_estado_pedido
                FROM Pedido
                WHERE id_pedido = ?
                """;

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Pedido save(Pedido pedido) throws SQLException {
        validarPedido(pedido);
        String sql = """
                INSERT INTO Pedido
                (fecha_pedido, subtotal, igv, total, id_usuario, id_direccion, id_cupon, id_estado_pedido)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        Connection cn = TransactionContext.getConnection();

        try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setPedidoParams(ps, pedido);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) pedido.setId(keys.getInt(1));
            }
        }

        if (pedido.getDetalles() != null) {
            DetallePedidoDAOImpl detalleDAO = new DetallePedidoDAOImpl();
            for (DetallePedido detalle : pedido.getDetalles()) {
                detalleDAO.save(detalle, pedido.getId());
            }
        }
        return pedido;
    }

    @Override
    public Pedido update(Pedido pedido) throws SQLException {
        validarPedido(pedido);
        String sql = """
                UPDATE Pedido
                SET fecha_pedido = ?, subtotal = ?, igv = ?, total = ?, id_usuario = ?,
                    id_direccion = ?, id_cupon = ?, id_estado_pedido = ?
                WHERE id_pedido = ?
                """;
        Connection cn = TransactionContext.getConnection();

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            setPedidoParams(ps, pedido);
            ps.setInt(9, pedido.getId());
            ps.executeUpdate();
        }

        if (pedido.getDetalles() != null) {
            try (PreparedStatement ps = cn.prepareStatement("DELETE FROM DetallePedido WHERE id_pedido = ?")) {
                ps.setInt(1, pedido.getId());
                ps.executeUpdate();
            }
            DetallePedidoDAOImpl detalleDAO = new DetallePedidoDAOImpl();
            for (DetallePedido detalle : pedido.getDetalles()) {
                detalleDAO.save(detalle, pedido.getId());
            }
        }
        return pedido;
    }

    @Override
    public void remove(Pedido pedido) throws SQLException {
        Connection cn = TransactionContext.getConnection();
        try (PreparedStatement ps = cn.prepareStatement("DELETE FROM DetallePedido WHERE id_pedido = ?")) {
            ps.setInt(1, pedido.getId());
            ps.executeUpdate();
        }
        try (PreparedStatement ps = cn.prepareStatement("DELETE FROM Pedido WHERE id_pedido = ?")) {
            ps.setInt(1, pedido.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void updateEstado(Integer idPedido, Integer idNuevoEstado) throws SQLException {
        String sql = "UPDATE Pedido SET id_estado_pedido = ? WHERE id_pedido = ?";
        Connection cn = TransactionContext.getConnection();
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idNuevoEstado);
            ps.setInt(2, idPedido);
            ps.executeUpdate();
        }
    }

    private void setPedidoParams(PreparedStatement ps, Pedido pedido) throws SQLException {
        setTimestamp(ps, 1, pedido.getFechaPedido());
        ps.setDouble(2, pedido.getSubtotal());
        ps.setDouble(3, pedido.getIgv());
        ps.setDouble(4, pedido.getTotal());
        ps.setInt(5, pedido.getCliente().getId());
        if (pedido.getDireccionEnvio() != null) {
            ps.setInt(6, pedido.getDireccionEnvio().getId());
        } else {
            ps.setNull(6, Types.INTEGER);
        }
        if (pedido.getCupon() != null) ps.setInt(7, pedido.getCupon().getId());
        else ps.setNull(7, Types.INTEGER);
        ps.setInt(8, pedido.getEstadoPedido().getId());
    }

    private Pedido mapRow(ResultSet rs) throws SQLException {
        Pedido p = new Pedido();
        p.setId(rs.getInt("id_pedido"));
        p.setFechaPedido(rs.getTimestamp("fecha_pedido"));
        p.setSubtotal(rs.getDouble("subtotal"));
        p.setIgv(rs.getDouble("igv"));
        p.setTotal(rs.getDouble("total"));
        p.setEstadoPedido(EstadoPedido.fromId(rs.getInt("id_estado_pedido")));

        Usuario u = new Usuario();
        u.setId(rs.getInt("id_usuario"));
        p.setCliente(u);

        Direccion d = new Direccion();
        d.setId(rs.getInt("id_direccion"));
        p.setDireccionEnvio(d);

        int idCupon = rs.getInt("id_cupon");
        if (!rs.wasNull()) {
            Cupon c = new Cupon();
            c.setId(idCupon);
            p.setCupon(c);
        }
        return p;
    }

    private void validarPedido(Pedido pedido) throws SQLException {
        if (pedido.getCliente() == null || pedido.getCliente().getId() <= 0) {
            throw new SQLException("Pedido: cliente invalido");
        }
        if (pedido.getDireccionEnvio() != null &&
                pedido.getDireccionEnvio().getId() <= 0) {
            throw new SQLException("Pedido: direccion de envio invalida");
        }
        if (pedido.getEstadoPedido() == null) {
            throw new SQLException("Pedido: estado invalido");
        }
    }

    private void setTimestamp(PreparedStatement ps, int index, java.util.Date value) throws SQLException {
        if (value == null) ps.setTimestamp(index, new Timestamp(System.currentTimeMillis()));
        else ps.setTimestamp(index, new Timestamp(value.getTime()));
    }

}
