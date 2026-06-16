package pe.edu.pucp.killaBeauty.bl.Impl;

import pe.edu.pucp.killaBeauty.bl.ProductoBL;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Producto;
import pe.edu.pucp.killaDAO.Impl.ProductoDAOImpl;
import pe.edu.pucp.killaDAO.ProductoDAO;

import java.sql.SQLException;
import java.util.List;

public class ProductoBLImpl implements ProductoBL {
    private ProductoDAO productoDAO = new ProductoDAOImpl();

    @Override
    public Producto create(Producto p) throws BusinessLogicException {
        try {
            if(p.getNombre() == null || p.getNombre().isEmpty())
                throw new BusinessLogicException("El nombre del producto no puede estar vacío");
            if(p.getSubcategoria() == null)
                throw new BusinessLogicException("El producto debe tener una subcategoría");
            if(p.getMarca() == null)
                throw new BusinessLogicException("El producto debe tener una marca");
            return productoDAO.save(p);
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    @Override
    public Producto update(Producto p) throws BusinessLogicException {
        try {
            return productoDAO.update(p);
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    @Override
    public void remove(Producto p) throws BusinessLogicException {
        try {
            productoDAO.remove(p);
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    @Override
    public List<Producto> listAll() throws BusinessLogicException {
        try {
            return productoDAO.listAll();
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    @Override
    public Producto load(int id) throws BusinessLogicException {
        try {
            return productoDAO.load(id);
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
    }
}
