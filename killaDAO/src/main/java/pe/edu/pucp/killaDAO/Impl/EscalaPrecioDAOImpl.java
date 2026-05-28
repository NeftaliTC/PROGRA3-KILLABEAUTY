package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.EscalaPrecio;
import pe.edu.pucp.killaDAO.EscalaPrecioDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscalaPrecioDAOImpl implements EscalaPrecioDAO {
    @Override
    public List<EscalaPrecio> listAll() throws SQLException {
        List<EscalaPrecio> escalas = new ArrayList<>();
        String sql = "SELECT id_escala, cantidadMinima, precioUnitario, activo FROM EscalaPrecio";
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                EscalaPrecio ep = new EscalaPrecio();
                ep.setId(rs.getInt(1));
                ep.setCantidadMinima(rs.getInt(2));
                ep.setPrecioUnitario(rs.getDouble(3));
                ep.setActivo(rs.getBoolean(4));
                escalas.add(ep);
            }
        }
        return escalas;
    }


    @Override
    public EscalaPrecio load(Integer id) throws SQLException {
        String sql = "SELECT id_escala, cantidadMinima, precioUnitario, activo FROM EscalaPrecio WHERE id_escala = ?";
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    EscalaPrecio ep = new EscalaPrecio();
                    ep.setId(rs.getInt(1));
                    ep.setCantidadMinima(rs.getInt(2));
                    ep.setPrecioUnitario(rs.getDouble(3));
                    ep.setActivo(rs.getBoolean(4));
                    return ep;
                }
            }
        }
        return null;
    }

    @Override
    public EscalaPrecio save(EscalaPrecio ep) throws SQLException {
        String sql = "INSERT INTO EscalaPrecio (cantidadMinima, precioUnitario, activo) VALUES (?, ?, ?)";

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, ep.getCantidadMinima());
            pstmt.setDouble(2, ep.getPrecioUnitario());
            pstmt.setBoolean(3, ep.getActivo());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet keys = pstmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        ep.setId(keys.getInt(1));
                    }
                }
            }
        }
        return ep;
    }

    @Override
    public EscalaPrecio update(EscalaPrecio ep) throws SQLException{
        String sql="UPDATE EscalaPrecio SET cantidadMinima = ?, precioUnitario = ?, activo = ? WHERE id_escala = ?";

        try (Connection connection=DBManager.getInstance().getConnection();
             PreparedStatement pstmt=connection.prepareStatement(sql)){
            pstmt.setInt(1, ep.getCantidadMinima());
            pstmt.setDouble(2, ep.getPrecioUnitario());
            pstmt.setBoolean(3, ep.getActivo());
            pstmt.setInt(4, ep.getId());
            pstmt.executeUpdate();
            return ep;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public void remove(EscalaPrecio ep) throws SQLException {
        ep.setActivo(false);
        String sql = "UPDATE EscalaPrecio SET activo = ? WHERE id_escala = ?";
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setBoolean(1, ep.getActivo());
            pstmt.setInt(2, ep.getId());
            pstmt.executeUpdate();
        }
    }

}


