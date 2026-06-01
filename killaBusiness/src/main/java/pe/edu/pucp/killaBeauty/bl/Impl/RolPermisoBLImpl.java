package pe.edu.pucp.killaBeauty.bl.Impl;

import pe.edu.pucp.killaBeauty.bl.RolPermisoBL;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.RolPermiso;
import pe.edu.pucp.killaDAO.RolPermisoDAO;
import pe.edu.pucp.killaDAO.Impl.RolPermisoDAOImpl;

import java.util.List;

public class RolPermisoBLImpl implements RolPermisoBL {

    private RolPermisoDAO rolPermisoDAO = new RolPermisoDAOImpl();

    @Override
    public RolPermiso create(RolPermiso rp) throws BusinessLogicException {
        try {
            return rolPermisoDAO.save(rp);
        } catch (Exception ex) {
            throw new BusinessLogicException("Error al asignar el permiso al rol: " + ex.getMessage());
        }
    }

    @Override
    public void remove(RolPermiso rp) throws BusinessLogicException {
        try {
            rolPermisoDAO.remove(rp);
        } catch (Exception ex) {
            throw new BusinessLogicException("Error al retirar el permiso del rol: " + ex.getMessage());
        }
    }

    @Override
    public RolPermiso load(Integer id) throws BusinessLogicException {
        try {
            // Esto simplemente pasará el error de "Operación no soportada" que se configuro en el DAO
            return rolPermisoDAO.load(id);
        } catch (Exception ex) {
            throw new BusinessLogicException("Error al cargar el permiso: " + ex.getMessage());
        }
    }

    @Override
    public List<RolPermiso> listAll() throws BusinessLogicException {
        try {
            return rolPermisoDAO.listAll();
        } catch (Exception ex) {
            throw new BusinessLogicException("Error al listar todos los permisos: " + ex.getMessage());
        }
    }

    @Override
    public List<RolPermiso> listarPorTipoUsuario(Integer idTipoUsuario) throws BusinessLogicException {
        try {
            return rolPermisoDAO.listarPorTipoUsuario(idTipoUsuario);
        } catch (Exception ex) {
            throw new BusinessLogicException("Error al listar los permisos del tipo de usuario: " + ex.getMessage());
        }
    }
}
