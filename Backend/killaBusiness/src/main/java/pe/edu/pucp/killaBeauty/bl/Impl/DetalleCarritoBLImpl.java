package pe.edu.pucp.killaBeauty.bl.Impl;

import pe.edu.pucp.dbManager.TransactionContext;
import pe.edu.pucp.killaBeauty.bl.DetalleCarritoBL;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.DetalleCarrito;
import pe.edu.pucp.killaDAO.DetalleCarritoDAO;
import pe.edu.pucp.killaDAO.Impl.DetalleCarritoDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class DetalleCarritoBLImpl implements DetalleCarritoBL {

    private DetalleCarritoDAO detalleDAO = new DetalleCarritoDAOImpl();

    @Override
    public DetalleCarrito create(DetalleCarrito detalle) throws BusinessLogicException {
        if (detalle.getProducto() == null)
            throw new BusinessLogicException("El detalle debe tener un producto.");
        if (detalle.getCantidad() <= 0)
            throw new BusinessLogicException("La cantidad debe ser mayor a 0.");
        if (detalle.getCarritoDeCompras() == null)
            throw new BusinessLogicException("El detalle debe pertenecer a un carrito.");

        try {
            TransactionContext.getConnection();
            detalleDAO.save(detalle);
            return detalle;
        } catch (SQLException e) {
            TransactionContext.rollback();
            throw new BusinessLogicException(e);
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public DetalleCarrito update(DetalleCarrito detalle) throws BusinessLogicException {
        try {
            TransactionContext.getConnection();
            detalleDAO.update(detalle);
            return detalle;
        } catch (SQLException e) {
            TransactionContext.rollback();
            throw new BusinessLogicException(e);
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public void remove(DetalleCarrito detalle) throws BusinessLogicException {
        try {
            TransactionContext.getConnection();
            detalleDAO.remove(detalle);
        } catch (SQLException e) {
            TransactionContext.rollback();
            throw new BusinessLogicException(e);
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public DetalleCarrito load(int id) throws BusinessLogicException {
        try {
            return detalleDAO.load(id);
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    @Override
    public List<DetalleCarrito> listByCarritoId(int idCarrito) throws BusinessLogicException {
        try {
            return detalleDAO.listByCarritoId(idCarrito);
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
    }
}
