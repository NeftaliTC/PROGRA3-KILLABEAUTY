package pe.edu.pucp.killaDAO.Impl;


import pe.edu.pucp.killaDAO.MarcaDAO;
import pe.edu.pucp.killaBeauty.killaModelo.Marca;
import pe.edu.pucp.dbManager.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAOImpl implements MarcaDAO {

    @Override
    public List<Marca> listAll() throws SQLException {
        List<Marca> marcas = new ArrayList<>();
        String sql = "SELECT id_marca, descripcion, pais_origen FROM Marca";
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {


            while (rs.next()) {
                Marca marca = new Marca();
                marca.setId(rs.getInt("id_marca"));
                marca.setDescripcion(rs.getString("descripcion"));
                marca.setPaisDeOrigen(rs.getString("pais_origen"));
                marcas.add(marca);
            }
        }
        return marcas;
    }

    @Override
    public Marca load(Integer id) throws SQLException {
        String sql = "SELECT id_marca, descripcion, pais_origen FROM Marca WHERE id_marca = ?";
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Marca marca = new Marca();
                    marca.setId(rs.getInt("id_marca"));
                    marca.setDescripcion(rs.getString("descripcion"));
                    marca.setPaisDeOrigen(rs.getString("pais_origen"));
                    return marca;
                }
            }
        }
        return null;
    }

    @Override
    public Marca save(Marca marca) throws SQLException {
        String sql = "INSERT INTO Marca (descripcion, pais_origen) VALUES (?, ?)";
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, marca.getDescripcion());
            pstmt.setString(2, marca.getPaisDeOrigen());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet keys = pstmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        marca.setId(keys.getInt(1));
                    }
                }
            }
        }
        return marca;
    }

    @Override
    public Marca update(Marca marca) throws SQLException {
        String sql = "UPDATE Marca SET descripcion = ?, pais_origen = ? WHERE id_marca = ?";
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, marca.getDescripcion());
            pstmt.setString(2, marca.getPaisDeOrigen());
            pstmt.setInt(3, marca.getId());

            pstmt.executeUpdate();
        }
        return marca;
    }

    @Override
    public void remove(Marca marca) throws SQLException {
        // Como tu tabla Marca no tiene campo "activo", aquí va borrado físico.
        // Si luego agregan "activo", lo cambiamos a borrado lógico.
        String sql = "DELETE FROM Marca WHERE id_marca = ?";
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, marca.getId());
            pstmt.executeUpdate();
        }
    }
}
