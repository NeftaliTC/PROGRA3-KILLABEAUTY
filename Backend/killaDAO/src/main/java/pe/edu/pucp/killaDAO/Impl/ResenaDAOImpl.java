package pe.edu.pucp.killaDAO.Impl;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.killaBeauty.killaModelo.Resena;
import pe.edu.pucp.killaDAO.ResenaDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResenaDAOImpl implements ResenaDAO {

    @Override
    public Resena load(Integer id) throws SQLException {
        String sql = "SELECT * FROM Resena WHERE id_reseña = ?";
        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Resena r = new Resena();
                    r.setIdResena(rs.getInt("id_reseña"));
                    r.setTitulo(rs.getString("titulo"));
                    r.setComentario(rs.getString("comentario"));
                    r.setCalificacion(rs.getInt("calificación"));
                    r.setVerificado(rs.getBoolean("verificado"));
                    r.setFechaPublicacion(rs.getDate("fecha_de_publicacion").toLocalDate());
                    r.getCliente().setId(rs.getInt("id_usuario"));
                    r.getProducto().setId(rs.getInt("id_producto"));
                    r.setActivo(rs.getBoolean("activo"));
                    return r;
                }
            }
        }
        return null;
    }

    @Override
    public Resena save(Resena r) throws SQLException {
        String sql = "INSERT INTO Resena (titulo, comentario, calificación, verificado, fecha_de_publicacion, id_usuario, id_producto, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, r.getTitulo());
            ps.setString(2, r.getComentario());
            ps.setInt(3, r.getCalificacion());
            ps.setBoolean(4, r.isVerificado());
            ps.setDate(5, Date.valueOf(r.getFechaPublicacion()));
            ps.setInt(6, r.getCliente().getId());
            ps.setInt(7, r.getProducto().getId());
            ps.setBoolean(8, r.getActivo() != null ? r.getActivo() : true);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) r.setIdResena(keys.getInt(1));
            }
        }
        return r;
    }

    @Override
    public Resena update(Resena r) throws SQLException {
        String sql = "UPDATE Resena SET titulo = ?, comentario = ?, calificación = ?, verificado = ?, fecha_de_publicacion = ?, id_usuario = ?, id_producto = ?, activo = ? WHERE id_reseña = ?";
        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, r.getTitulo());
            ps.setString(2, r.getComentario());
            ps.setInt(3, r.getCalificacion());
            ps.setBoolean(4, r.isVerificado());
            ps.setDate(5, Date.valueOf(r.getFechaPublicacion()));
            ps.setInt(6, r.getCliente().getId());
            ps.setInt(7, r.getProducto().getId());
            ps.setBoolean(8, r.getActivo() != null ? r.getActivo() : true);
            ps.setInt(9, r.getIdResena());
            ps.executeUpdate();
        }
        return r;
    }

    @Override
    public void remove(Resena r) throws SQLException {
        // Eliminación lógica
        r.setActivo(false);
        String sql = "UPDATE Resena SET activo = ? WHERE id_reseña = ?";
        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setBoolean(1, r.getActivo());
            ps.setInt(2, r.getIdResena());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Resena> listByProductoId(int idProducto) throws SQLException {
        List<Resena> lista = new ArrayList<>();
        String sql = "SELECT * FROM Resena WHERE id_producto = ?";
        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Resena r = new Resena();
                    r.setIdResena(rs.getInt("id_reseña"));
                    r.setTitulo(rs.getString("titulo"));
                    r.setComentario(rs.getString("comentario"));
                    r.setCalificacion(rs.getInt("calificación"));
                    r.setVerificado(rs.getBoolean("verificado"));
                    r.setFechaPublicacion(rs.getDate("fecha_de_publicacion").toLocalDate());
                    r.getCliente().setId(rs.getInt("id_usuario"));
                    r.getProducto().setId(rs.getInt("id_producto"));
                    r.setActivo(rs.getBoolean("activo"));
                    lista.add(r);
                }
            }
        }
        return lista;
    }

    @Override
    public List<Resena> listByUsuarioId(int idUsuario) throws SQLException {
        List<Resena> lista = new ArrayList<>();
        String sql = "SELECT * FROM Resena WHERE id_usuario = ?";
        try (Connection cn = DBManager.getInstance().getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Resena r = new Resena();
                    r.setIdResena(rs.getInt("id_reseña"));
                    r.setTitulo(rs.getString("titulo"));
                    r.setComentario(rs.getString("comentario"));
                    r.setCalificacion(rs.getInt("calificación"));
                    r.setVerificado(rs.getBoolean("verificado"));
                    r.setFechaPublicacion(rs.getDate("fecha_de_publicacion").toLocalDate());
                    r.getCliente().setId(rs.getInt("id_usuario"));
                    r.getProducto().setId(rs.getInt("id_producto"));
                    r.setActivo(rs.getBoolean("activo"));
                    lista.add(r);
                }
            }
        }
        return lista;
    }
}