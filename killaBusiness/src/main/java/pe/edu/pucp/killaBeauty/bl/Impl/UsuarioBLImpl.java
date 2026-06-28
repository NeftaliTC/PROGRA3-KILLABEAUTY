package pe.edu.pucp.killaBeauty.bl.Impl;

import org.mindrot.jbcrypt.BCrypt;
import pe.edu.pucp.killaBeauty.bl.UsuarioBL;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.TipoUsuario;
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
            throw new BusinessLogicException("El campo correo no puede estar vacío");
        if (usuario.getFechaNacimiento() == null) {
            throw new BusinessLogicException("La fecha de nacimiento es obligatoria.");
        }
        try {
            Usuario usuarioExistente = usuarioDAO.loadByEmail(usuario.getCorreoElectronico());
            if (usuarioExistente != null) {
                throw new BusinessLogicException("El correo electrónico ya se encuentra registrado.");
            }

            String passwordPlano = usuario.getContrasena();
            String passwordHasheado = BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
            usuario.setContrasena(passwordHasheado);
            usuario.setActivo(true);
            usuario.setTipoUsuario(TipoUsuario.CLIENTE);
            usuario.setFechaDeInscripcion(new java.util.Date());
            return usuarioDAO.save(usuario);
            // ultimo acceso se actualizará con la fecha actual recién cuando haga su primer Login.
        } catch(SQLException e) {
            throw new BusinessLogicException("Error interno: "+ e.getMessage());

        }
    }

    @Override
    public Usuario update(Usuario usuario) throws BusinessLogicException {

        // como condicion el usuario ya esta logeado, es decir el usuario ya tiene un id , por eso no se verifica
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            throw new BusinessLogicException("El nombre es obligatorio.");
        }
        if (usuario.getApellidoPaterno() == null || usuario.getApellidoPaterno().trim().isEmpty()) {
            throw new BusinessLogicException("El apellido paterno es obligatorio.");
        }
        if (usuario.getTelefono() != null && !usuario.getTelefono().matches("^[0-9]{9}$")) {

            throw new BusinessLogicException("El teléfono debe contener 9 dígitos numéricos.");
        }
        try {
            Usuario usuarioActual = usuarioDAO.load(usuario.getId());
            if (usuarioActual == null) {
                throw new BusinessLogicException("El usuario con ID " + usuario.getId() + " no existe en el sistema.");
            }

            usuarioActual.setNombre(usuario.getNombre().trim());
            usuarioActual.setApellidoPaterno(usuario.getApellidoPaterno().trim());
            usuarioActual.setApellidoMaterno(usuario.getApellidoMaterno() != null ? usuario.getApellidoMaterno().trim() : null);
            usuarioActual.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioActual.setGenero(usuario.getGenero());
            usuarioActual.setTelefono(usuario.getTelefono());

            usuarioDAO.update(usuarioActual);

            // Limpiamos el hash por seguridad antes de enviarlo de vuelta al frontend
            usuarioActual.setContrasena(null);
            return usuarioActual;
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

    @Override
    public Usuario cambiarContrasena(int id, String contrasenaActual, String nuevaContrasena) throws BusinessLogicException {
        if (contrasenaActual == null || contrasenaActual.trim().isEmpty()) {
            throw new BusinessLogicException("Debe ingresar su contraseña actual.");
        }
        if (nuevaContrasena == null || nuevaContrasena.length() < 6) {
            throw new BusinessLogicException("La nueva contraseña debe tener al menos 6 caracteres.");
        }
        if (contrasenaActual.equals(nuevaContrasena)) {
            throw new BusinessLogicException("La nueva contraseña no puede ser igual a la anterior.");
        }
        try{

            Usuario usuarioActual = usuarioDAO.load(id);
            if (usuarioActual == null) {
                throw new BusinessLogicException("El usuario no existe en el sistema.");
            }

            // Usamos BCrypt para comparar la contraseña ingresada con la de la BD
            boolean contrasenaValida = BCrypt.checkpw(contrasenaActual, usuarioActual.getContrasena());
            if (!contrasenaValida) {
                throw new BusinessLogicException("La contraseña actual ingresada es incorrecta.");
            }

            String passwordHasheado = BCrypt.hashpw(nuevaContrasena, BCrypt.gensalt());
            usuarioActual.setContrasena(passwordHasheado);
            return usuarioDAO.update(usuarioActual);
        }catch(SQLException e) {
            throw new BusinessLogicException("Error interno: "+ e.getMessage());
        }
    }

    @Override
    public Usuario autenticar(Usuario usuario) throws BusinessLogicException {

        if (usuario.getCorreoElectronico() == null || usuario.getContrasena() == null) {
            throw new BusinessLogicException("El correo y la contraseña son campos obligatorios.");
        }
        try {
            Usuario usuarioBD = usuarioDAO.loadByEmail(usuario.getCorreoElectronico());
            if (usuarioBD == null) {
                throw new BusinessLogicException("Correo o contraseña incorrectos.");
            }
            boolean contrasenaValida = BCrypt.checkpw(usuario.getContrasena(), usuarioBD.getContrasena());
            if (!contrasenaValida) {
                throw new BusinessLogicException("Correo o contraseña incorrectos.");
            }
            // Removemos el hash de la contraseña del objeto antes de retornarlo
            usuarioBD.setContrasena(null);
            return usuarioBD;

        } catch (SQLException e) {
            throw new BusinessLogicException("Error interno en el servidor de datos al intentar procesar el ingreso: " + e.getMessage());
        }
    }
}
