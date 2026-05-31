package pe.edu.pucp.killaBeauty.bl.impl;

import pe.edu.pucp.dbManager.TransactionContext;
import pe.edu.pucp.killaBeauty.bl.DireccionBL;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Direccion;
import pe.edu.pucp.killaDAO.DireccionDAO;
import pe.edu.pucp.killaDAO.Impl.DireccionDAOImpl;

import java.util.List;

public class DireccionBLImpl implements DireccionBL {

    private DireccionDAO direccionDAO = new DireccionDAOImpl();

    @Override
    public Direccion create(Direccion d) throws BusinessLogicException {
        try {
            // Al crear, por defecto no es predeterminada
            d.setEsPredeterminada(false);
            return direccionDAO.save(d);
        } catch (Exception ex) {
            throw new BusinessLogicException("Error al crear la dirección: " + ex.getMessage());
        }
    }

    @Override
    public Direccion update(Direccion d) throws BusinessLogicException {
        try {
            TransactionContext.getConnection();
            // Si el usuario marca esta como predeterminada, limpiamos las anteriores
            if (d.getEsPredeterminada()) {
                direccionDAO.resetearPredeterminadas(d.getUsuario().getId());
            }
            direccionDAO.update(d);
            TransactionContext.commit();
            return d;
        } catch (Exception ex) {
            TransactionContext.rollback();
            throw new BusinessLogicException("Error al actualizar la dirección: " + ex.getMessage());
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public void remove(Direccion d) throws BusinessLogicException {
        try {
            TransactionContext.getConnection();
            direccionDAO.remove(d);
            TransactionContext.commit();
        } catch (Exception ex) {
            TransactionContext.rollback();
            throw new BusinessLogicException("Error al eliminar la dirección: " + ex.getMessage());
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public Direccion load(Integer id) throws BusinessLogicException {
        try {
            return direccionDAO.load(id);
        } catch (Exception e) {
            throw new BusinessLogicException("Error al cargar la dirección: " + e.getMessage());
        }
    }

    @Override
    public List<Direccion> listAll() throws BusinessLogicException {
        try {
            return direccionDAO.listAll();
        } catch (Exception e) {
            throw new BusinessLogicException("Error al listar todas las direcciones: " + e.getMessage());
        }
    }

    @Override
    public List<Direccion> listarPorUsuario(Integer idUsuario) throws BusinessLogicException {
        try {
            return direccionDAO.listarPorUsuario(idUsuario);
        } catch (Exception e) {
            throw new BusinessLogicException("Error al listar las direcciones del usuario: " + e.getMessage());
        }
    }
}
