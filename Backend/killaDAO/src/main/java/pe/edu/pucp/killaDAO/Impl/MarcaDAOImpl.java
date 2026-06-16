package pe.edu.pucp.killaDAO.Impl;


import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.Pais;
import pe.edu.pucp.killaDAO.MarcaDAO;
import pe.edu.pucp.killaBeauty.killaModelo.Marca;
import pe.edu.pucp.dbManager.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAOImpl implements MarcaDAO {

    @Override
    public List<Marca> listAll() throws SQLException {
        List<Marca> lista = new ArrayList<>();
        String sql = "SELECT id_marca, descripcion, pais, activo FROM Marca";

        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Marca m = new Marca();
                m.setId(rs.getInt("id_marca"));
                m.setDescripcion(rs.getString("descripcion"));
                m.setPais(Pais.valueOf(rs.getString("pais"))); // Enum
                m.setActivo(rs.getBoolean("activo"));
                lista.add(m);
            }
        }
        return lista;
    }

    @Override
    public Marca load(Integer id) throws SQLException {
        String sql = "SELECT id_marca, descripcion, pais, activo FROM Marca WHERE id_marca = ?";
        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Marca m = new Marca();
                    m.setId(rs.getInt("id_marca"));
                    m.setDescripcion(rs.getString("descripcion"));
                    m.setPais(Pais.valueOf(rs.getString("pais")));
                    m.setActivo(rs.getBoolean("activo"));
                    return m;
                }
            }
        }
        return null;
    }

    @Override
    public Marca save(Marca m) throws SQLException {
        String sql = "INSERT INTO Marca (descripcion, pais, activo) VALUES (?, ?, ?)";
        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, m.getDescripcion());
            ps.setString(2, m.getPais().name());
            ps.setBoolean(3, m.getActivo());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) m.setId(keys.getInt(1));
            }
        }
        return m;
    }

    @Override
    public Marca update(Marca m) throws SQLException {
        String sql = "UPDATE Marca SET descripcion = ?, pais = ?, activo = ? WHERE id_marca = ?";
        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, m.getDescripcion());
            ps.setString(2, m.getPais().name());
            ps.setBoolean(3, m.getActivo());
            ps.setInt(4, m.getId());
            ps.executeUpdate();
        }
        return m;
    }

    @Override
    public void remove(Marca m) throws SQLException {
        m.setActivo(false);
        String sql = "UPDATE Marca SET activo = ? WHERE id_marca = ?";
        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setBoolean(1, m.getActivo());
            ps.setInt(2, m.getId());
            ps.executeUpdate();
        }
    }
}
