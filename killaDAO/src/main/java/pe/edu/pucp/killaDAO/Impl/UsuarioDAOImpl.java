package pe.edu.pucp.killaDAO.Impl;



import java.sql.*;

import pe.edu.pucp.dbManager.dbManager;
import pe.edu.pucp.killaBeauty.killaModelo.Usuario;
import pe.edu.pucp.killaDAO.UsuarioDAO;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public List<Usuario> listAll() throws SQLException {
        List<Usuario> list=new ArrayList<>();
        String sql = "SELECT id_usuario, nombre, apellido_materno, apellido_paterno, " +
                "correo_electronico, fecha_de_inscripcion, contrasena, telefono, activo, id_tipoUsuario " +
                "FROM Usuario " +
                "WHERE activo = TRUE";
        try(Connection connection=dbManager.getInstance().getConnection();
            Statement stm=connection.createStatement(); ResultSet rs=stm.executeQuery(sql)) {
            while(rs.next()){
                Usuario usuario=new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellidoMaterno(rs.getString(3));
                usuario.setApellidoPaterno(rs.getString(4));
                usuario.setCorreoElectronico(rs.getString(5));
                usuario.setFechaDeInscripcion(rs.getDate(6));
                usuario.setContrasena(rs.getString(7));
                usuario.setTelefono(rs.getString(8));
                usuario.setEstado(rs.getBoolean(9));
                list.add(usuario);


            }
            return list;
        }

    }

    @Override
    public Usuario load(Integer id) throws SQLException {

        String sql = "SELECT id_usuario, nombre, apellido_materno, apellido_paterno, " +
                "correo_electronico, fecha_de_inscripcion, contrasena, telefono, activo, id_tipoUsuario " +
                "FROM Usuario " +
                "WHERE id_usuario = ?";
        try(Connection connection=dbManager.getInstance().getConnection();
            PreparedStatement pstmt=connection.prepareStatement(sql)) {
            pstmt.setInt(1,id);
            try(ResultSet rs=pstmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt(1));
                    usuario.setNombre(rs.getString(2));
                    usuario.setApellidoMaterno(rs.getString(3));
                    usuario.setApellidoPaterno(rs.getString(4));
                    usuario.setCorreoElectronico(rs.getString(5));
                    usuario.setFechaDeInscripcion(rs.getDate(6));
                    usuario.setContrasena(rs.getString(7));
                    usuario.setTelefono(rs.getString(8));
                    usuario.setEstado(rs.getBoolean(9));
                    usuario.setId_tipoUsuario(rs.getInt(10));
                    return usuario;

                }
            }
        }
        return null;
    }

    @Override
    public Usuario save(Usuario usuario) throws SQLException {
        usuario.setEstado(true);
        String sql="INSERT INTO Usuario " +
                "(nombre, apellido_materno, apellido_paterno, correo_electronico, contrasena, telefono, activo, id_tipoUsuario) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection=dbManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellidoMaterno());
            pstmt.setString(3, usuario.getApellidoPaterno());
            pstmt.setString(4, usuario.getCorreoElectronico());
            pstmt.setString(5, usuario.getContrasena());
            pstmt.setString(6, usuario.getTelefono());
            pstmt.setBoolean(7, usuario.getEstado());
            pstmt.setInt(8, usuario.getId_tipoUsuario());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newId = generatedKeys.getInt(1);
                        usuario.setId(newId);
                    }
                }
            }
        }

        return usuario;

    }

    @Override
    public Usuario update(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET nombre = ?, apellido_materno = ?, apellido_paterno = ?, " +
                "correo_electronico = ?, contrasena = ?, telefono = ?, activo = ?, id_tipoUsuario= ?" +
                "WHERE id_usuario = ?";
        try (Connection connection = dbManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellidoMaterno());
            pstmt.setString(3, usuario.getApellidoPaterno());
            pstmt.setString(4, usuario.getCorreoElectronico());
            pstmt.setString(5, usuario.getContrasena());
            pstmt.setString(6, usuario.getTelefono());
            pstmt.setBoolean(7, usuario.getEstado());
            pstmt.setInt(8, usuario.getId_tipoUsuario());
            pstmt.executeUpdate();
            return usuario;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        }

    @Override
    public void remove(Usuario usuario) throws SQLException {
        usuario.setEstado(false);
        String sql = "UPDATE Usuario SET activo = ? WHERE id_usuario = ?";

        try (Connection connection = dbManager.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setBoolean(1, usuario.getEstado());
            pstmt.setInt(2, usuario.getId());
            pstmt.executeUpdate();
        }
    }
}
