package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.Direccion;
import pe.edu.pucp.killaDAO.DireccionDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DireccionDAOImpl implements DireccionDAO {

    @Override
    public List<Direccion> listAll() throws SQLException {
        List<Direccion> lista = new ArrayList<>();
        String sql = "SELECT id_direccion, Departamento, Provincia, Distrito, Direccion_exacta, Referencia, id_usuario FROM Direccion";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Direccion d = new Direccion();
                d.setIdDireccion(rs.getInt("id_direccion"));
                d.setDepartamento(rs.getString("Departamento"));
                d.setProvincia(rs.getString("Provincia"));
                d.setDistrito(rs.getString("Distrito"));
                d.setDireccionExacta(rs.getString("Direccion_exacta"));
                d.setReferencia(rs.getString("Referencia"));
                d.setIdUsuario(rs.getInt("id_usuario"));
                lista.add(d);
            }
        }
        return lista;
    }

    @Override
    public Direccion load(Integer id) throws SQLException {
        String sql = "SELECT id_direccion, Departamento, Provincia, Distrito, Direccion_exacta, Referencia, id_usuario FROM Direccion WHERE id_direccion = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Direccion d = new Direccion();
                    d.setIdDireccion(rs.getInt("id_direccion"));
                    d.setDepartamento(rs.getString("Departamento"));
                    d.setProvincia(rs.getString("Provincia"));
                    d.setDistrito(rs.getString("Distrito"));
                    d.setDireccionExacta(rs.getString("Direccion_exacta"));
                    d.setReferencia(rs.getString("Referencia"));
                    d.setIdUsuario(rs.getInt("id_usuario"));
                    return d;
                }
            }
        }
        return null;
    }

    @Override
    public Direccion save(Direccion d) throws SQLException {
        String sql = "INSERT INTO Direccion (Departamento, Provincia, Distrito, Direccion_exacta, Referencia, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, d.getDepartamento());
            ps.setString(2, d.getProvincia());
            ps.setString(3, d.getDistrito());
            ps.setString(4, d.getDireccionExacta());
            ps.setString(5, d.getReferencia());
            ps.setInt(6, d.getIdUsuario());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) d.setIdDireccion(rs.getInt(1));
            }
        }
        return d;
    }

    @Override
    public Direccion update(Direccion d) throws SQLException {
        String sql = "UPDATE Direccion SET Departamento = ?, Provincia = ?, Distrito = ?, Direccion_exacta = ?, Referencia = ? WHERE id_direccion = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getDepartamento());
            ps.setString(2, d.getProvincia());
            ps.setString(3, d.getDistrito());
            ps.setString(4, d.getDireccionExacta());
            ps.setString(5, d.getReferencia());
            ps.setInt(6, d.getIdDireccion());
            ps.executeUpdate();
        }
        return d;
    }

    @Override
    public void remove(Direccion d) throws SQLException {
        String sql = "DELETE FROM Direccion WHERE id_direccion = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, d.getIdDireccion());
            ps.executeUpdate();
        }
    }
}