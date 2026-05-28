package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.Resena;
import pe.edu.pucp.killaDAO.ResenaDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResenaDAOImpl implements ResenaDAO {

    @Override
    public Resena save(Resena r) throws SQLException {
        String sql = "INSERT INTO Resena (titulo, comentario, calificacion, verificado, fecha_de_publicacion, id_usuario, id_producto) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, r.getTitulo());
            ps.setString(2, r.getComentario());
            ps.setInt(3, r.getCalificacion());
            ps.setBoolean(4, r.isVerificado());
            ps.setDate(5, Date.valueOf(r.getFechaPublicacion())); // LocalDate a SQL Date
            ps.setInt(6, r.getCliente().getId());
            ps.setInt(7, r.getProducto().getIdProducto());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) r.setIdResena(rs.getInt(1));
            }
        }
        return r;
    }

    @Override
    public Resena load(Integer id) throws SQLException {
        String sql = "SELECT id_resena, titulo, comentario, calificacion, verificado, fecha_de_publicacion, id_usuario, id_producto FROM Resena WHERE id_resena = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Resena r = new Resena();
                    r.setIdResena(rs.getInt("id_resena"));
                    r.setTitulo(rs.getString("titulo"));
                    r.setComentario(rs.getString("comentario"));
                    r.setCalificacion(rs.getInt("calificacion"));
                    r.setVerificado(rs.getBoolean("verificado"));
                    if (rs.getDate("fecha_de_publicacion") != null)
                        r.setFechaPublicacion(rs.getDate("fecha_de_publicacion").toLocalDate());
                    r.getCliente().setId(rs.getInt("id_usuario"));
                    r.getProducto().setIdProducto(rs.getInt("id_producto"));
                    return r;
                }
            }
        }
        return null;
    }

    @Override
    public List<Resena> listAll() throws SQLException {
        List<Resena> lista = new ArrayList<>();
        String sql = "SELECT id_resena, titulo, comentario, calificacion, verificado, fecha_de_publicacion, id_usuario, id_producto FROM Resena";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Resena r = new Resena();
                r.setIdResena(rs.getInt("id_resena"));
                r.setTitulo(rs.getString("titulo"));
                r.setComentario(rs.getString("comentario"));
                r.setCalificacion(rs.getInt("calificacion"));
                r.setVerificado(rs.getBoolean("verificado"));
                if (rs.getDate("fecha_de_publicacion") != null)
                    r.setFechaPublicacion(rs.getDate("fecha_de_publicacion").toLocalDate());
                r.getCliente().setId(rs.getInt("id_usuario"));
                r.getProducto().setIdProducto(rs.getInt("id_producto"));
                lista.add(r);
            }
        }
        return lista;
    }

    @Override
    public Resena update(Resena r) throws SQLException {
        String sql = "UPDATE Resena SET titulo = ?, comentario = ?, calificacion = ?, verificado = ? WHERE id_resena = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, r.getTitulo());
            ps.setString(2, r.getComentario());
            ps.setInt(3, r.getCalificacion());
            ps.setBoolean(4, r.isVerificado());
            ps.setInt(5, r.getIdResena());
            ps.executeUpdate();
        }
        return r;
    }

    @Override
    public void remove(Resena r) throws SQLException {
        String sql = "DELETE FROM Resena WHERE id_resena = ?";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, r.getIdResena());
            ps.executeUpdate();
        }
    }
}
