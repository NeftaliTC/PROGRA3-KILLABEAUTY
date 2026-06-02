package pe.edu.pucp.killaBeauty.bl.Impl;

import pe.edu.pucp.killaBeauty.bl.UsuarioBL;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Usuario;
import pe.edu.pucp.killaDAO.Impl.UsuarioDAOImpl;
import pe.edu.pucp.killaDAO.UsuarioDAO;

import java.sql.SQLException;
import java.util.List;

public class UsuarioBLImpl implements UsuarioBL {
    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    @Override
    public Usuario create(Usuario usuario) throws BusinessLogicException {
        if(usuario.getCorreoElectronico() == null || usuario.getCorreoElectronico().isEmpty())
            throw new BusinessLogicException("El correo no puede estar vacío");
        try {
            return usuarioDAO.save(usuario);
        } catch(SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    @Override
    public Usuario update(Usuario usuario) throws BusinessLogicException {
        try {
            return usuarioDAO.update(usuario);
        } catch(SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    @Override
    public void remove(Usuario usuario) throws BusinessLogicException {
        try {
            usuarioDAO.remove(usuario);
        } catch(SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    @Override
    public Usuario load(int id) throws BusinessLogicException {
        try {
            return usuarioDAO.load(id);
        } catch(SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    @Override
    public Usuario loadByEmail(String email) throws BusinessLogicException {
        try {
            return usuarioDAO.loadByEmail(email);
        } catch(SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    @Override
    public List<Usuario> listByTipoUsuario(int idTipoUsuario) throws BusinessLogicException {
        try {
            return usuarioDAO.listByTipoUsuario(idTipoUsuario);
        } catch(SQLException e) {
            throw new BusinessLogicException(e);
        }
    }
}
