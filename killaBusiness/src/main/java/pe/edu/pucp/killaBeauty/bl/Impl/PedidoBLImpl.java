package pe.edu.pucp.killaBeauty.bl.Impl;

import pe.edu.pucp.dbManager.TransactionContext;
import pe.edu.pucp.killaBeauty.bl.PedidoBL;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.*;
import pe.edu.pucp.killaBeauty.killaModelo.Promocionales.Cupon;
import pe.edu.pucp.killaDAO.Impl.PedidoDAOImpl;
import pe.edu.pucp.killaDAO.Impl.ProductoDAOImpl;
import pe.edu.pucp.killaDAO.PedidoDAO;
import pe.edu.pucp.killaDAO.ProductoDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PedidoBLImpl implements PedidoBL {
    private PedidoDAO pedidoDAO = new PedidoDAOImpl();
    private ProductoDAO productoDAO = new ProductoDAOImpl();

    @Override
    public Pedido create(Pedido pedido) throws BusinessLogicException {
        validarPedido(pedido);
        if (pedido.getFechaPedido() == null) pedido.setFechaPedido(new Date());
        if (pedido.getEstadoPedido() == null) pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
        recalcularTotales(pedido);

        try {
            TransactionContext.getConnection();
            Pedido guardado = pedidoDAO.save(pedido);
            TransactionContext.commit();
            return guardado;
        } catch (SQLException e) {
            TransactionContext.rollback();
            throw new BusinessLogicException(e);
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public Pedido createFromCart(Integer idCliente, Integer idDireccion, Integer idCupon, List<DetallePedido> detalles) throws BusinessLogicException {
        if (idCliente == null || idCliente <= 0) throw new BusinessLogicException("Debe indicar un cliente valido.");
        if (idDireccion == null || idDireccion <= 0) throw new BusinessLogicException("Debe indicar una direccion de envio valida.");
        if (detalles == null || detalles.isEmpty()) throw new BusinessLogicException("El carrito no tiene productos para registrar.");

        Pedido pedido = new Pedido();

        Usuario cliente = new Usuario();
        cliente.setId(idCliente);
        pedido.setCliente(cliente);

        Direccion direccion = new Direccion();
        direccion.setId(idDireccion);
        pedido.setDireccionEnvio(direccion);

        if (idCupon != null && idCupon > 0) {
            Cupon cupon = new Cupon();
            cupon.setId(idCupon);
            pedido.setCupon(cupon);
        }

        pedido.setDetalles(prepararDetallesDesdeCarrito(detalles));
        return create(pedido);
    }

    @Override
    public Pedido update(Pedido pedido) throws BusinessLogicException {
        validarPedido(pedido);
        recalcularTotales(pedido);

        try {
            TransactionContext.getConnection();
            Pedido actualizado = pedidoDAO.update(pedido);
            TransactionContext.commit();
            return actualizado;
        } catch (SQLException e) {
            TransactionContext.rollback();
            throw new BusinessLogicException(e);
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public void remove(Pedido pedido) throws BusinessLogicException {
        if (pedido == null || pedido.getId() <= 0) throw new BusinessLogicException("Debe indicar un pedido valido.");
        try {
            TransactionContext.getConnection();
            pedidoDAO.remove(pedido);
            TransactionContext.commit();
        } catch (SQLException e) {
            TransactionContext.rollback();
            throw new BusinessLogicException(e);
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public Pedido load(Integer id) throws BusinessLogicException {
        try {
            return pedidoDAO.load(id);
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    @Override
    public List<Pedido> listAll() throws BusinessLogicException {
        try {
            return pedidoDAO.listAll();
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    private void validarPedido(Pedido pedido) throws BusinessLogicException {
        if (pedido == null)
            throw new BusinessLogicException("El pedido no puede ser nulo.");

        if (pedido.getCliente() == null || pedido.getCliente().getId() <= 0)
            throw new BusinessLogicException("El pedido debe tener un cliente valido.");

        // Temporalmente no validar dirección
        if (pedido.getDireccionEnvio() != null &&
                pedido.getDireccionEnvio().getId() <= 0) {
            throw new BusinessLogicException("El pedido debe tener una direccion de envio valida.");
        }

        if (pedido.getDetalles() == null || pedido.getDetalles().isEmpty())
            throw new BusinessLogicException("El pedido debe tener al menos un detalle.");

        for (DetallePedido detalle : pedido.getDetalles())
            validarDetalle(detalle);
    }

    private void validarDetalle(DetallePedido detalle) throws BusinessLogicException {
        if (detalle == null) throw new BusinessLogicException("El detalle del pedido no puede ser nulo.");
        if (detalle.getProducto() == null || detalle.getProducto().getId() <= 0) throw new BusinessLogicException("Cada detalle debe tener un producto valido.");
        if (detalle.getCantidad() <= 0) throw new BusinessLogicException("La cantidad del detalle debe ser mayor a cero.");
        if (detalle.getPrecioAplicado() < 0) throw new BusinessLogicException("El precio aplicado no puede ser negativo.");
    }

    private List<DetallePedido> prepararDetallesDesdeCarrito(List<DetallePedido> detalles) throws BusinessLogicException {
        List<DetallePedido> detallesPreparados = new ArrayList<>();

        for (DetallePedido item : detalles) {
            if (item == null || item.getProducto() == null || item.getProducto().getId() <= 0) {
                throw new BusinessLogicException("Cada item del carrito debe tener un producto valido.");
            }
            if (item.getCantidad() <= 0) {
                throw new BusinessLogicException("La cantidad de cada item del carrito debe ser mayor a cero.");
            }

            Producto producto = cargarProducto(item.getProducto().getId());
            validarProductoDisponible(producto, item.getCantidad());

            DetallePedido detalle = new DetallePedido();
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioAplicado(producto.getPrecioBase());
            detallesPreparados.add(detalle);
        }

        return detallesPreparados;
    }

    private Producto cargarProducto(int idProducto) throws BusinessLogicException {
        try {
            Producto producto = productoDAO.load(idProducto);
            if (producto == null) throw new BusinessLogicException("No se encontro el producto con id " + idProducto + ".");
            return producto;
        } catch (SQLException e) {
            throw new BusinessLogicException(e);
        }
    }

    private void validarProductoDisponible(Producto producto, int cantidad) throws BusinessLogicException {
        if (Boolean.FALSE.equals(producto.getActivo()) || Boolean.FALSE.equals(producto.getDisponible())) {
            throw new BusinessLogicException("El producto " + producto.getNombre() + " no esta disponible.");
        }
        if (producto.getStock() != null && producto.getStock() < cantidad) {
            throw new BusinessLogicException("No hay stock suficiente para " + producto.getNombre() + ".");
        }
    }

    private void recalcularTotales(Pedido pedido) {
        double subtotal = 0.0;
        for (DetallePedido detalle : pedido.getDetalles()) subtotal += detalle.calcularSubtotal();
        pedido.setSubtotal(subtotal);
        pedido.setIgv(subtotal * 0.18);
        pedido.setTotal(pedido.getSubtotal() + pedido.getIgv());
    }
}