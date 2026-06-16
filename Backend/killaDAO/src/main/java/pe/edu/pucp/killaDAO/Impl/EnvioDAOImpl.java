package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.*;
import pe.edu.pucp.killaDAO.EnvioDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnvioDAOImpl implements EnvioDAO {
    @Override
    public List<Envio> listAll() throws SQLException {
        List<Envio> lista = new ArrayList<>();
        String sql = """
                SELECT id_envio, descripcion, costo_envio, fecha_envio, id_estado_envio, id_pedido, id_courier, numero_seguimiento 
                FROM Envio
                """;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapRow(rs));
        }
        return lista;
    }

    @Override
    public Envio load(Integer id) throws SQLException {
        String sql = """
                SELECT id_envio, descripcion, costo_envio, fecha_envio, id_estado_envio, id_pedido, id_courier, numero_seguimiento 
                FROM Envio WHERE id_envio = ?
                """;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    @Override
    public Envio save(Envio e) throws SQLException {
        String sql = """
                INSERT INTO Envio (descripcion, costo_envio, fecha_envio, id_estado_envio, id_pedido, id_courier, numero_seguimiento)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getDescripcion());
            ps.setDouble(2, e.getCostoEnvio());
            ps.setTimestamp(3, new Timestamp(e.getFechaEnvio().getTime()));
            ps.setInt(4, e.getEstadoEnvio().getId());
            ps.setInt(5, e.getPedido().getId());
            ps.setInt(6, e.getCourier().getId());
            ps.setInt(7, e.getNumeroSeguimiento());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) e.setId(rs.getInt(1));
            }
        }
        return e;
    }

    @Override
    public Envio update(Envio e) throws SQLException {
        String sql = """
                UPDATE Envio SET descripcion = ?, costo_envio = ?, id_estado_envio = ?, numero_seguimiento = ?
                WHERE id_envio = ?
                """;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getDescripcion());
            ps.setDouble(2, e.getCostoEnvio());
            ps.setInt(3, e.getEstadoEnvio().getId());
            ps.setInt(4, e.getNumeroSeguimiento());
            ps.setInt(5, e.getId());
            ps.executeUpdate();
        }
        return e;
    }

    @Override
    public void remove(Envio e) throws SQLException {
        String sql = "UPDATE Envio SET id_estado_envio = ? WHERE id_envio = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, EstadoPedido.CANCELADO.getId());
            ps.setInt(2, e.getId());
            ps.executeUpdate();
        }
    }

    private Envio mapRow(ResultSet rs) throws SQLException {
        Envio e = new Envio();
        e.setId(rs.getInt("id_envio"));
        e.setDescripcion(rs.getString("descripcion"));
        e.setCostoEnvio(rs.getDouble("costo_envio"));
        e.setFechaEnvio(rs.getTimestamp("fecha_envio"));
        e.setNumeroSeguimiento(rs.getInt("numero_seguimiento"));

        int idEstado = rs.getInt("id_estado_envio");
        e.setEstadoEnvio(EstadoEnvio.fromId(idEstado));

        Pedido p = new Pedido();
        p.setId(rs.getInt("id_pedido"));
        e.setPedido(p);

        Courier c = new Courier();
        c.setId(rs.getInt("id_courier"));
        e.setCourier(c);

        return e;
    }
}
