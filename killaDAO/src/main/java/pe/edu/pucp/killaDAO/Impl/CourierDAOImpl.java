package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.Courier;
import pe.edu.pucp.killaDAO.CourierDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourierDAOImpl implements CourierDAO {
    @Override
    public List<Courier> listAll() throws SQLException {
        List<Courier> lista = new ArrayList<>();
        String sql = "SELECT * FROM Courier"; // Trae todos para que el admin pueda gestionarlos
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Courier c = new Courier();
                c.setId(rs.getInt("id_courier"));
                c.setNombre(rs.getString("nombre"));
                c.setRUC(rs.getInt("ruc"));
                c.setTelefono(rs.getInt("telefono"));
                c.setActivo(rs.getBoolean("activo"));
                c.setCorreo(rs.getString("correo"));
                lista.add(c);
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
                    Courier c = new Courier();
                    c.setId(rs.getInt("id_courier"));
                    c.setNombre(rs.getString("nombre"));
                    c.setRUC(rs.getInt("ruc"));
                    c.setTelefono(rs.getInt("telefono"));
                    c.setActivo(rs.getBoolean("activo"));
                    c.setCorreo(rs.getString("correo"));
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public Courier save(Courier courier) throws SQLException {
        String sql = "INSERT INTO Courier (nombre, ruc, telefono, activo, correo) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBManager.getInstance().getConnection()) {
            if (courier.isActivo()) { // Si se marca como activo, desasignar los otros
                con.createStatement().executeUpdate("UPDATE Courier SET activo = 0");
            }
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, courier.getNombre());
                ps.setInt(2, courier.getRUC());
                ps.setInt(3, courier.getTelefono());
                ps.setBoolean(4, courier.isActivo());
                ps.setString(5, courier.getCorreo());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next())
                        courier.setId(rs.getInt(1));
                }
            }
        }
        return courier;
    }

    @Override
    public Courier update(Courier courier) throws SQLException {
        String sql = "UPDATE Courier SET nombre = ?, ruc = ?, telefono = ?, activo = ?, correo = ? WHERE id_courier = ?";
        try (Connection con = DBManager.getInstance().getConnection()) {
            // Lógica de exclusividad: si este courier se activa, los demás se desactivan
            if (courier.isActivo()) {
                con.createStatement().executeUpdate("UPDATE Courier SET activo = 0 WHERE id_courier != " + courier.getId());
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, courier.getNombre());
                ps.setInt(2, courier.getRUC());
                ps.setInt(3, courier.getTelefono());
                ps.setBoolean(4, courier.isActivo());
                ps.setString(5, courier.getCorreo());
                ps.setInt(6, courier.getId());
                ps.executeUpdate();
            }
        }
        return courier;
    }

    @Override
    public void remove(Courier courier) throws SQLException {}
}
