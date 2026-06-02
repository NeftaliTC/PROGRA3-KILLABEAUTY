package pe.edu.pucp.killaBeauty.bl.Impl;

import pe.edu.pucp.dbManager.TransactionContext;
import pe.edu.pucp.killaBeauty.bl.CourierBL;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Courier;
import pe.edu.pucp.killaDAO.CourierDAO;
import pe.edu.pucp.killaDAO.Impl.CourierDAOImpl;

import java.util.List;

public class CourierBLImpl implements CourierBL {

    private CourierDAO courierDAO = new CourierDAOImpl();

    @Override
    public Courier create(Courier c) throws BusinessLogicException {
        try {
            TransactionContext.getConnection();
            Courier creado = courierDAO.save(c);
            TransactionContext.commit();
            return creado;
        } catch (Exception e) {
            TransactionContext.rollback();
            throw new BusinessLogicException("Error al registrar el courier: " + e.getMessage());
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public Courier update(Courier c) throws BusinessLogicException {
        try {
            // Se usa transacción porque el DAO realiza múltiples updates (el cambio de activo)
            TransactionContext.getConnection();
            Courier actualizado = courierDAO.update(c);
            TransactionContext.commit();
            return actualizado;
        } catch (Exception e) {
            TransactionContext.rollback();
            throw new BusinessLogicException("Error al actualizar el courier: " + e.getMessage());
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public Courier load(Integer id) throws BusinessLogicException {
        try {
            return courierDAO.load(id);
        } catch (Exception e) {
            throw new BusinessLogicException("Error al cargar el courier: " + e.getMessage());
        }
    }

    @Override
    public List<Courier> listAll() throws BusinessLogicException {
        try {
            return courierDAO.listAll();
        } catch (Exception e) {
            throw new BusinessLogicException("Error al listar los couriers: " + e.getMessage());
        }
    }
}
