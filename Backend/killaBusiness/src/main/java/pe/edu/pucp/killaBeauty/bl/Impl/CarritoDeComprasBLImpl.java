package pe.edu.pucp.killaBeauty.bl.Impl;

import pe.edu.pucp.dbManager.TransactionContext;
import pe.edu.pucp.killaBeauty.bl.CarritoDeComprasBL;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.CarritoDeCompras;
import pe.edu.pucp.killaBeauty.killaModelo.DetalleCarrito;
import pe.edu.pucp.killaBeauty.killaModelo.Producto;
import pe.edu.pucp.killaDAO.CarritoDeComprasDAO;
import pe.edu.pucp.killaDAO.DetalleCarritoDAO;
import pe.edu.pucp.killaDAO.Impl.CarritoDeComprasDAOImpl;
import pe.edu.pucp.killaDAO.Impl.DetalleCarritoDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class CarritoDeComprasBLImpl implements CarritoDeComprasBL {

    private CarritoDeComprasDAO carritoDAO = new CarritoDeComprasDAOImpl();
    private DetalleCarritoDAO detalleDAO = new DetalleCarritoDAOImpl();

    @Override
    public CarritoDeCompras create(CarritoDeCompras carrito) throws BusinessLogicException {
        try {
            // Validaciones
            if (carrito.getUsuario() == null)
                throw new BusinessLogicException("El carrito debe tener un usuario asignado");
            List<DetalleCarrito> items = carrito.getDetalleCarritoList();
            if (items == null || items.isEmpty())
                throw new BusinessLogicException("El carrito debe contener al menos un detalle");

            // Obtener la conexión activa (autoCommit = false)
            TransactionContext.getConnection();

            // Guardar carrito
            carritoDAO.save(carrito);

            // Guardar detalles del carrito
            for (DetalleCarrito detalle : items) {
                Producto producto = detalle.getProducto();
                if (producto == null)
                    throw new BusinessLogicException("Cada detalle debe tener un producto");
                if (detalle.getCantidad() <= 0)
                    throw new BusinessLogicException("La cantidad debe ser mayor a 0");
                detalle.setCarritoDeCompras(carrito);
                detalleDAO.save(detalle);
            }

            // Commit al finalizar exitosamente
            TransactionContext.commit();
            return carrito;

        } catch (SQLException | BusinessLogicException e) {
            // Rollback en caso de error
            TransactionContext.rollback();
            throw new BusinessLogicException(e);
        } finally {
            // Cerrar la conexión
            TransactionContext.close();
        }
    }

    @Override
    public CarritoDeCompras update(CarritoDeCompras carrito) throws BusinessLogicException {
        try {
            TransactionContext.getConnection();

            carritoDAO.update(carrito);
            for (DetalleCarrito detalle : carrito.getDetalleCarritoList()) {
                detalleDAO.update(detalle);
            }

            TransactionContext.commit();
            return carrito;
        } catch (SQLException e) {
            TransactionContext.rollback();
            throw new BusinessLogicException(e);
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public void remove(CarritoDeCompras carrito) throws BusinessLogicException {
        try {
            TransactionContext.getConnection();

            for (DetalleCarrito detalle : carrito.getDetalleCarritoList()) {
                detalleDAO.remove(detalle);
            }
            carritoDAO.remove(carrito);

            TransactionContext.commit();
        } catch (SQLException e) {
            TransactionContext.rollback();
            throw new BusinessLogicException(e);
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public CarritoDeCompras load(int id) throws BusinessLogicException {
        try {
            CarritoDeCompras c = carritoDAO.load(id);
            if (c != null) {
                c.setDetalleCarritoList(detalleDAO.listByCarritoId(c.getId()));
            }
            return c;
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    @Override
    public List<CarritoDeCompras> listByUsuarioId(int idUsuario) throws BusinessLogicException {
        try {
            List<CarritoDeCompras> carritos = carritoDAO.listByUsuarioId(idUsuario);
            for (CarritoDeCompras c : carritos) {
                c.setDetalleCarritoList(detalleDAO.listByCarritoId(c.getId()));
            }
            return carritos;
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
    }
}
