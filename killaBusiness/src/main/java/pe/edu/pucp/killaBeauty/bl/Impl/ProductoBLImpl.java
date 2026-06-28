package pe.edu.pucp.killaBeauty.bl.Impl;

import pe.edu.pucp.killaBeauty.bl.ProductoBL;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Marca;
import pe.edu.pucp.killaBeauty.killaModelo.Producto;
import pe.edu.pucp.killaBeauty.killaModelo.Subcategoria;
import pe.edu.pucp.killaDAO.Impl.MarcaDAOImpl;
import pe.edu.pucp.killaDAO.Impl.ProductoDAOImpl;
import pe.edu.pucp.killaDAO.Impl.SubCategoriaDAOImpl;
import pe.edu.pucp.killaDAO.MarcaDAO;
import pe.edu.pucp.killaDAO.ProductoDAO;
import pe.edu.pucp.killaDAO.SubCategoriaDAO;

import java.sql.SQLException;
import java.util.List;

public class ProductoBLImpl implements ProductoBL {
    private ProductoDAO productoDAO = new ProductoDAOImpl();
    private MarcaDAO marcaDAO = new MarcaDAOImpl();
    private SubCategoriaDAO subcategoriaDAO = new SubCategoriaDAOImpl();

    @Override
    public Producto create(Producto p) throws BusinessLogicException {
        validarProducto(p, false);
        try {
            return productoDAO.save(p);
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    @Override
    public Producto update(Producto p) throws BusinessLogicException {
        validarProducto(p, true);
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

    // validaciones de producto
    private void validarProducto(Producto p, boolean requiereId) throws BusinessLogicException {
        if (p == null) {
            throw new BusinessLogicException("El producto no puede ser nulo");
        }

        if (requiereId) {
            if (p.getId() <= 0) {
                throw new BusinessLogicException("Se requiere un ID valido para actualizar el producto");
            }
            validarProductoExistente(p.getId());
        }

        if (p.getNombre() == null || p.getNombre().trim().isEmpty()) {
            throw new BusinessLogicException("El nombre del producto no puede estar vacio");
        }
        p.setNombre(p.getNombre().trim());

        if (p.getPrecioBase() <= 0) {
            throw new BusinessLogicException("El precio base debe ser mayor a 0");
        }

        if (p.getStock() == null) {
            throw new BusinessLogicException("El stock del producto es obligatorio");
        }
        if (p.getStock() < 0) {
            throw new BusinessLogicException("El stock no puede ser negativo");
        }

        validarMarca(p);
        validarSubcategoria(p);
        validarEstados(p);
    }

    private void validarProductoExistente(int idProducto) throws BusinessLogicException {
        try {
            if (productoDAO.load(idProducto) == null) {
                throw new BusinessLogicException("No existe un producto con el ID indicado");
            }
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    private void validarMarca(Producto p) throws BusinessLogicException {
        if (p.getMarca() == null || p.getMarca().getId() <= 0) {
            throw new BusinessLogicException("El producto debe tener una marca valida");
        }

        try {
            Marca marca = marcaDAO.load(p.getMarca().getId());
            if (marca == null) {
                throw new BusinessLogicException("La marca seleccionada no existe");
            }
            if (!Boolean.TRUE.equals(marca.getActivo())) {
                throw new BusinessLogicException("La marca seleccionada no esta activa");
            }
            p.setMarca(marca);
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    private void validarSubcategoria(Producto p) throws BusinessLogicException {
        if (p.getSubcategoria() == null || p.getSubcategoria().getId() <= 0) {
            throw new BusinessLogicException("El producto debe tener una subcategoria valida");
        }

        try {
            Subcategoria subcategoria = subcategoriaDAO.load(p.getSubcategoria().getId());
            if (subcategoria == null) {
                throw new BusinessLogicException("La subcategoria seleccionada no existe");
            }
            if (!Boolean.TRUE.equals(subcategoria.getActivo())) {
                throw new BusinessLogicException("la subcategoria seleccionada no esta activa");
            }
            if (subcategoria.getCategoria() == null || !Boolean.TRUE.equals(subcategoria.getCategoria().getActivo())) {
                throw new BusinessLogicException("La categoria de la subcategoria seleccionada no esta activa");
            }
            p.setSubcategoria(subcategoria);
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    private void validarEstados(Producto p) throws BusinessLogicException {
        if (p.getDisponible() == null) {
            throw new BusinessLogicException("Debe indicar si el producto esta disponible");
        }
        if (p.getPromocion() == null) {
            throw new BusinessLogicException("debe indicar si el producto esta en promocion");
        }
        if (p.getActivo() == null) {
            throw new BusinessLogicException("Debe indicar si el producto esta activo");
        }
    }
}
