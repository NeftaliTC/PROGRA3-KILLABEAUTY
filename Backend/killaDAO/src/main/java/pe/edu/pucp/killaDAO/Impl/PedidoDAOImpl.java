package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.dbManager.TransactionContext;
import pe.edu.pucp.killaBeauty.killaModelo.Promocionales.Cupon;
import pe.edu.pucp.killaBeauty.killaModelo.Direccion;
import pe.edu.pucp.killaBeauty.killaModelo.Pedido;
import pe.edu.pucp.killaBeauty.killaModelo.EstadoPedido;
import pe.edu.pucp.killaBeauty.killaModelo.Usuario;
import pe.edu.pucp.killaDAO.PedidoDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl implements PedidoDAO {

    @Override
    public List<Pedido> listAll() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();

        String sql = """
                SELECT id_pedido, id_usuario, id_direccion, id_cupon, fecha_pedido,
                       subtotal, igv, total, estado_pedido
                FROM Pedido
                """;

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                pedidos.add(mapRowToPedido(rs));
            }
        }

        return pedidos;
    }

    @Override
    public Pedido load(Integer id) throws SQLException {
        String sql = """
                SELECT id_pedido, id_usuario, id_direccion, id_cupon, fecha_pedido,
                       subtotal, igv, total, estado_pedido
                FROM Pedido
                WHERE id_pedido = ?
                """;

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToPedido(rs);
                }
            }
        }

        return null;
    }

    @Override
    public Pedido save(Pedido pedido) throws SQLException {
        String sql = """
                INSERT INTO Pedido
                (fecha_pedido, subtotal, id_cupon, igv, total, id_usuario, id_direccion, estado_pedido)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            LocalDate fecha = (pedido.getFechaPedido() != null) ? pedido.getFechaPedido() : LocalDate.now();
            ps.setTimestamp(1, Timestamp.valueOf(fecha.atStartOfDay()));

            ps.setDouble(2, pedido.getSubtotal());

            if (pedido.getCupon() != null) {
                ps.setInt(3, pedido.getCupon().getIdCupon());
            } else {
                ps.setNull(3, Types.INTEGER);
            }

            ps.setDouble(4, pedido.getIgv());
            ps.setDouble(5, pedido.getTotal());

            if (pedido.getCliente() == null) {
                throw new SQLException("Pedido.save: cliente es null");
            }
            ps.setInt(6, pedido.getCliente().getId());

            if (pedido.getDireccionEnvio() == null) {
                throw new SQLException("Pedido.save: direccionEnvio es null");
            }
            ps.setInt(7, pedido.getDireccionEnvio().getId());

            ps.setString(8, pedido.getEstadoPedido().name());

            int affected = ps.executeUpdate();
            if (affected > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        pedido.setId(keys.getInt(1));
                    }
                }
            }
        }

        return pedido;
    }

    @Override
    public Pedido update(Pedido pedido) throws SQLException {
        String sql = """
                UPDATE Pedido
                SET fecha_pedido = ?, subtotal = ?, id_cupon = ?, igv = ?, total = ?,
                    id_usuario = ?, id_direccion = ?, estado_pedido = ?
                WHERE id_pedido = ?
                """;

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            LocalDate fecha = (pedido.getFechaPedido() != null) ? pedido.getFechaPedido() : LocalDate.now();
            ps.setTimestamp(1, Timestamp.valueOf(fecha.atStartOfDay()));

            ps.setDouble(2, pedido.getSubtotal());

            if (pedido.getCupon() != null) {
                ps.setInt(3, pedido.getCupon().getIdCupon());
            } else {
                ps.setNull(3, Types.INTEGER);
            }

            ps.setDouble(4, pedido.getIgv());
            ps.setDouble(5, pedido.getTotal());

            if (pedido.getCliente() == null) {
                throw new SQLException("Pedido.update: cliente es null");
            }
            ps.setInt(6, pedido.getCliente().getId());

            if (pedido.getDireccionEnvio() == null) {
                throw new SQLException("Pedido.update: direccionEnvio es null");
            }
            ps.setInt(7, pedido.getDireccionEnvio().getId());

            ps.setString(8, pedido.getEstadoPedido().name());
            ps.setInt(9, pedido.getId());

            ps.executeUpdate();
        }

        return pedido;
    }

    @Override
    public void remove(Pedido pedido) throws SQLException {
        String sql = "DELETE FROM Pedido WHERE id_pedido = ?";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, pedido.getId());
            ps.executeUpdate();
        }
    }

    private Pedido mapRowToPedido(ResultSet rs) throws SQLException {
        Pedido p = new Pedido();

        p.setId(rs.getInt("id_pedido"));

        Timestamp ts = rs.getTimestamp("fecha_pedido");
        if (ts != null) {
            p.setFechaPedido(ts.toLocalDateTime().toLocalDate());
        }

        p.setSubtotal(rs.getDouble("subtotal"));
        p.setIgv(rs.getDouble("igv"));
        p.setTotal(rs.getDouble("total"));
        p.setEstadoPedido(EstadoPedido.valueOf(rs.getString("estado_pedido")));

        Usuario u = new Usuario();
        u.setId(rs.getInt("id_usuario"));
        p.setCliente(u);

        Direccion d = new Direccion();
        d.setId(rs.getInt("id_direccion"));
        p.setDireccionEnvio(d);

        int idCupon = rs.getInt("id_cupon");
        if (!rs.wasNull()) {
            Cupon c = new Cupon();
            c.setIdCupon(idCupon);
            p.setCupon(c);
        } else {
            p.setCupon(null);
        }

        return p;
    }

    @Override
    public void updateEstado(Integer idPedido, Integer idNuevoEstado) throws SQLException {
        String sql = "UPDATE Pedido SET id_estado_pedido = ? WHERE id_pedido = ?";
        Connection con = TransactionContext.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idNuevoEstado);
            ps.setInt(2, idPedido);
            ps.executeUpdate();
        }
    }
}
