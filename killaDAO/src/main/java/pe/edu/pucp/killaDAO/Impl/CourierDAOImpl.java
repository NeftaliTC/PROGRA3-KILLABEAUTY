package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.dbManager.TransactionContext;
import pe.edu.pucp.killaBeauty.killaModelo.Courier;
import pe.edu.pucp.killaDAO.CourierDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourierDAOImpl implements CourierDAO {
    @Override
    public List<Courier> listAll() throws SQLException {
        List<Courier> lista = new ArrayList<>();
        String sql = "SELECT id_courier, nombre, ruc, telefono, activo, correo FROM Courier";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearCourier(rs));
            }
        }
        return lista;
    }

    @Override
    public Courier load(Integer id) throws SQLException {
        String sql = "SELECT id_courier, nombre, ruc, telefono, activo, correo FROM Courier WHERE id_courier = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCourier(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Courier save(Courier courier) throws SQLException {
        String sql = "INSERT INTO Courier (nombre, ruc, telefono, activo, correo) VALUES (?, ?, ?, ?, ?)";
        Connection con = TransactionContext.getConnection();

        if (courier.isActivo()) {
            con.createStatement().executeUpdate("UPDATE Courier SET activo = 0");
        }

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, courier.getNombre());
            ps.setString(2, courier.getRuc());
            ps.setString(3, courier.getTelefono());
            ps.setBoolean(4, courier.isActivo());
            ps.setString(5, courier.getCorreo());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    courier.setId(rs.getInt(1));
                }
            }
        }

        return courier;
    }

    @Override
    public Courier update(Courier courier) throws SQLException {
        String sql = "UPDATE Courier SET nombre = ?, ruc = ?, telefono = ?, activo = ?, correo = ? WHERE id_courier = ?";
        Connection con = TransactionContext.getConnection();

        if (courier.isActivo()) {
            con.createStatement().executeUpdate("UPDATE Courier SET activo = 0 WHERE id_courier != " + courier.getId());
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, courier.getNombre());
            ps.setString(2, courier.getRuc());
            ps.setString(3, courier.getTelefono());
            ps.setBoolean(4, courier.isActivo());
            ps.setString(5, courier.getCorreo());
            ps.setInt(6, courier.getId());
            ps.executeUpdate();
        }

        return courier;
    }

    @Override
    public void remove(Courier courier) throws SQLException {
        throw new UnsupportedOperationException("La eliminacion no esta permitida en este sistema.");
    }

    private Courier mapearCourier(ResultSet rs) throws SQLException {
        Courier c = new Courier();
        c.setId(rs.getInt("id_courier"));
        c.setNombre(rs.getString("nombre"));
        c.setRuc(rs.getString("ruc"));
        c.setTelefono(rs.getString("telefono"));
        c.setActivo(rs.getBoolean("activo"));
        c.setCorreo(rs.getString("correo"));
        return c;
    }
}
