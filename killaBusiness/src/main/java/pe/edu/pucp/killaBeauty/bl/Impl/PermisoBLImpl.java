package pe.edu.pucp.killaBeauty.bl.Impl;

import pe.edu.pucp.killaBeauty.bl.PermisoBL;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Permiso;
import pe.edu.pucp.killaDAO.Impl.PermisoDAOImpl;
import pe.edu.pucp.killaDAO.PermisoDAO;

import java.util.List;

public class PermisoBLImpl implements PermisoBL {

    private PermisoDAO permisoDAO = new PermisoDAOImpl();

    @Override
    public Permiso create(Permiso p) throws BusinessLogicException {
        try {
            if (p.getNombre() == null || p.getNombre().isEmpty()) {
                throw new BusinessLogicException("El nombre del permiso no puede estar vacío");
            }
            return permisoDAO.save(p);
        } catch (Exception ex) {
            throw new BusinessLogicException("Error al crear el permiso: " + ex.getMessage());
        }
    }

    @Override
    public Permiso update(Permiso p) throws BusinessLogicException {
        try {
            return permisoDAO.update(p);
        } catch (Exception ex) {
            throw new BusinessLogicException("Error al actualizar el permiso: " + ex.getMessage());
        }
    }

    @Override
    public void remove(Permiso p) throws BusinessLogicException {
        try {
            permisoDAO.remove(p);
        } catch (Exception ex) {
            throw new BusinessLogicException("Error al eliminar el permiso: " + ex.getMessage());
        }
    }

    @Override
    public Permiso load(Integer id) throws BusinessLogicException {
        try {
            return permisoDAO.load(id);
        } catch (Exception ex) {
            throw new BusinessLogicException("Error al cargar el permiso: " + ex.getMessage());
        }
    }

    @Override
    public List<Permiso> listAll() throws BusinessLogicException {
        try {
            return permisoDAO.listAll();
        } catch (Exception ex) {
            throw new BusinessLogicException("Error al listar los permisos: " + ex.getMessage());
        }
    }
}
