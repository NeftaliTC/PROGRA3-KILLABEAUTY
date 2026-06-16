package pe.edu.pucp.killaDAO.Impl;



import java.sql.*;

import pe.edu.pucp.dbManager.DBManager;
import pe.edu.pucp.dbManager.TransactionContext;
import pe.edu.pucp.killaBeauty.killaModelo.Usuario;
import pe.edu.pucp.killaDAO.UsuarioDAO;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public Usuario load(Integer id) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE id_usuario = ?";
        try (PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id_usuario"));
                    u.setCorreoElectronico(rs.getString("correo_electronico"));
                    u.setActivo(rs.getBoolean("activo"));
                    return u;
                }
            }
        }
        return null;
    }

    @Override
    public Usuario save(Usuario u) throws SQLException {
        String sql = "INSERT INTO Usuario (nombre, correo_electronico, contraseña, activo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = TransactionContext.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreoElectronico());
            ps.setString(3, u.getContrasena());
            ps.setBoolean(4, u.getActivo());
            ps.executeUpdate();
            try(ResultSet keys = ps.getGeneratedKeys()) {
                if(keys.next()) u.setId(keys.getInt(1));
            }
        }
        return u;
    }

    @Override
    public Usuario update(Usuario u) throws SQLException {
        String sql = "UPDATE Usuario SET nombre = ?, correo_electronico = ?, contraseña = ?, activo = ? WHERE id_usuario = ?";
        try (PreparedStatement ps = TransactionContext.getConnection().prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreoElectronico());
            ps.setString(3, u.getContrasena());
            ps.setBoolean(4, u.getActivo());
            ps.setInt(5, u.getId());
            ps.executeUpdate();
        }
        return u;
    }

    @Override
    public void remove(Usuario u) throws SQLException {
        u.setActivo(false);
        String sql = "UPDATE Usuario SET activo = ? WHERE id_usuario = ?";
        try (PreparedStatement ps = TransactionContext.getConnection().prepareStatement(sql)) {
            ps.setBoolean(1, u.getActivo());
            ps.setInt(2, u.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Usuario> listByTipoUsuario(int idTipoUsuario) throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuario WHERE id_tipoUsuario = ?";
        try (PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, idTipoUsuario);
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id_usuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setCorreoElectronico(rs.getString("correo_electronico"));
                    u.setActivo(rs.getBoolean("activo"));
                    lista.add(u);
                }
            }
        }
        return lista;
    }

    @Override
    public Usuario loadByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE correo_electronico = ?";
        try (PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id_usuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setCorreoElectronico(rs.getString("correo_electronico"));
                    u.setActivo(rs.getBoolean("activo"));
                    return u;
                }
            }
        }
        return null;
    }
}