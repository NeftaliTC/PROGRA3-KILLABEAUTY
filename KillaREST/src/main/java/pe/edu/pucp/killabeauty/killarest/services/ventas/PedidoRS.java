package pe.edu.pucp.killabeauty.killarest.services.ventas;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.edu.pucp.killaBeauty.bl.*;
import pe.edu.pucp.killaBeauty.bl.Impl.*;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.*;
import pe.edu.pucp.killabeauty.killarest.dto.ErrorDTO;
import pe.edu.pucp.killabeauty.killarest.dto.PedidoCheckoutDTO;
import pe.edu.pucp.killabeauty.killarest.dto.PedidoDetalleDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Path("/pedido")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoRS {
    private final PedidoBL pedidoBL = new PedidoBLImpl();
    private final EnvioBL envioBL = new EnvioBLImpl();
    private final CourierBL courierBL = new CourierBLImpl();
    private final PagoBL pagoBL = new PagoBLImpl();
    private final ComprobantePagoBL comprobanteBL = new ComprobantePagoBLImpl();
    private static final Pattern DNI_PATTERN = Pattern.compile("^\\d{8}$");
    private static final Pattern RUC_PATTERN = Pattern.compile("^(10|20)\\d{9}$");

    // Construye el DTO completo buscando envio, pago y comprobante del pedido
    private PedidoDetalleDTO construirDTO(Pedido p) throws BusinessLogicException {
        try {
            Envio envio = envioBL.obtenerPorIdPedido(p.getId());

            Pago pago = pagoBL.obtenerPorIdPedido(p.getId());

            ComprobantePago comprobante = null;
            if (pago != null)
                comprobante = comprobanteBL.obtenerPorIdPago(pago.getIdPago());

            return new PedidoDetalleDTO(p, envio, pago, comprobante);
        } catch (Exception ex) {
            throw new BusinessLogicException(ex.getMessage());
        }
    }

    @POST
    @Path("/checkout")
    public Response registrarDesdeCheckout(PedidoCheckoutDTO request) {
        try {
            validarCheckout(request);

            //Crear pedido
            Pedido pedido = pedidoBL.createFromCart(
                    request.getUsuarioId(),
                    request.getDireccionId(),
                    request.getCuponId(),
                    mapDetallesCarrito(request.getItems())
            );

            //Crear pago
            Pago pago = new Pago();
            pago.setPedido(pedido);
            pago.setMontoPagado(pedido.getTotal() + request.getCostoEnvio() - request.getDescuentoCupon());
            pago.setFechaHoraPago(new java.util.Date());
            pago.setEstado(true);
            pago.setMetodoPago(resolverMetodoPago(request.getMetodoPago()));
            pago = pagoBL.create(pago);

            // Actualizar total del pedido con el monto real pagado
            pedidoBL.actualizarTotal(pedido.getId(), pago.getMontoPagado());
            pedido.setTotal(pago.getMontoPagado());

            //Crear Envio si hay direccion seleccionada
            if (request.getDireccionId() != null && request.getDireccionId() > 0) {
                Courier courier = courierBL.buscarAsignado();
                if (courier != null) {
                    Envio envio = new Envio();
                    envio.setPedido(pedido);
                    envio.setCourier(courier);
                    envio.setCostoEnvio(request.getCostoEnvio());
                    envio.setEstadoEnvio(EstadoEnvio.PENDIENTE);
                    envio.setNumeroSeguimiento(generarNumeroSeguimiento(pedido.getId()));
                    envioBL.create(envio);
                }
            }

            //Crear comprobante
            ComprobantePago comprobante = crearComprobante(request, pago);
            if (comprobante != null)
                comprobanteBL.create(comprobante);

            return Response.status(Response.Status.CREATED)
                    .entity(construirDTO(pedido))
                    .build();
        } catch (BusinessLogicException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(ex.getMessage())).build();
        }
    }

    @POST
    public Response registrar(Pedido pedido) {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(pedidoBL.create(pedido))
                    .build();
        } catch (BusinessLogicException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(ex.getMessage())).build();
        }
    }

    @GET
    public Response listar() {
        try {
            List<PedidoDetalleDTO> lista = new ArrayList<>();
            for (Pedido p : pedidoBL.listAll()) lista.add(construirDTO(p));
            return Response.ok(lista).build();
        } catch (BusinessLogicException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorDTO(ex.getMessage())).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") int id) {
        try {
            Pedido p = pedidoBL.load(id);
            return (p != null) ? Response.ok(construirDTO(p)).build()
                    : Response.status(Response.Status.NOT_FOUND).build();
        } catch (BusinessLogicException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorDTO(ex.getMessage())).build();
        }
    }

    @GET
    @Path("/cliente/{idCliente}")
    public Response listarPorCliente(@PathParam("idCliente") int idCliente) {
        try {
            List<PedidoDetalleDTO> lista = new ArrayList<>();
            for (Pedido p : pedidoBL.listByCliente(idCliente)) lista.add(construirDTO(p));
            return Response.ok(lista).build();
        } catch (BusinessLogicException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorDTO(ex.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") int id, Pedido pedido) {
        try {
            pedido.setId(id);
            return Response.ok(pedidoBL.update(pedido)).build();
        } catch (BusinessLogicException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(ex.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}/cancelar")
    public Response cancelar(@PathParam("id") int id) {
        try {
            return Response.ok(construirDTO(pedidoBL.cancel(id))).build();
        } catch (BusinessLogicException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(ex.getMessage())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") int id) {
        try {
            Pedido p = pedidoBL.load(id);
            if (p == null) return Response.status(Response.Status.NOT_FOUND).build();
            pedidoBL.remove(p);
            return Response.noContent().build();
        } catch (BusinessLogicException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(ex.getMessage())).build();
        }
    }

    private List<DetallePedido> mapDetallesCarrito(List<PedidoCheckoutDTO.ItemCarritoDTO> items) {
        List<DetallePedido> detalles = new ArrayList<>();

        for (PedidoCheckoutDTO.ItemCarritoDTO item : items) {
            DetallePedido detalle = new DetallePedido();
            Producto producto = new Producto();
            producto.setId(item.getProductoId());
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioAplicado(item.getPrecioAplicado());
            detalles.add(detalle);
        }
        return detalles;
    }

    private MetodoPago resolverMetodoPago(String metodoPago) {
        if (metodoPago == null) return MetodoPago.TARJETA;
        return switch (metodoPago.toUpperCase()) {
            case "YAPE_PLIN" -> MetodoPago.YAPE_PLIN;
            case "TARJETA" -> MetodoPago.TARJETA;
            default -> MetodoPago.TARJETA;
        };
    }

    private String generarNumeroSeguimiento(int idPedido) {
        return String.format("KIL-%d-%06d",
                java.time.LocalDate.now().getYear(), idPedido);
    }

    private ComprobantePago crearComprobante(PedidoCheckoutDTO request, Pago pago) {
        if (request.getTipoComprobante() == null) return null;

        if (request.getTipoComprobante().equalsIgnoreCase("BOLETA")) {
            Boleta b = new Boleta();
            b.setPago(pago);
            b.setFechaEmision(new java.util.Date());
            b.setSerie("B001");
            b.setNumeroCorrelativo(String.format("%08d", pago.getIdPago()));
            b.setTipoComprobante(TipoComprobante.getById(1));
            b.setDni(request.getDni());
            return b;
        } else if (request.getTipoComprobante().equalsIgnoreCase("FACTURA")) {
            Factura f = new Factura();
            f.setPago(pago);
            f.setFechaEmision(new java.util.Date());
            f.setSerie("F001");
            f.setNumeroCorrelativo(String.format("%08d", pago.getIdPago()));
            f.setTipoComprobante(TipoComprobante.getById(2));
            f.setRuc(request.getRuc());
            f.setRazonSocial(request.getRazonSocial());
            f.setDireccionFiscal(request.getDireccionFiscal());
            return f;
        }
        return null;
    }

    private void validarCheckout(PedidoCheckoutDTO request) throws BusinessLogicException {
        if (request == null) {
            throw new BusinessLogicException("La solicitud de checkout no puede ser nula.");
        }
        if (request.getUsuarioId() == null || request.getUsuarioId() <= 0) {
            throw new BusinessLogicException("El usuario del checkout debe ser valido.");
        }
        if (request.getDireccionId() != null && request.getDireccionId() < 0) {
            throw new BusinessLogicException("La direccion seleccionada debe ser valida.");
        }
        if (request.getCuponId() != null && request.getCuponId() <= 0) {
            throw new BusinessLogicException("El cupon seleccionado debe ser valido.");
        }
        if (request.getCostoEnvio() < 0) {
            throw new BusinessLogicException("El costo de envio no puede ser negativo.");
        }
        if (request.getDescuentoCupon() < 0) {
            throw new BusinessLogicException("El descuento del cupon no puede ser negativo.");
        }
        validarItemsCheckout(request.getItems());
        validarMetodoPago(request);
        validarComprobanteCheckout(request);
    }

    private void validarItemsCheckout(List<PedidoCheckoutDTO.ItemCarritoDTO> items) throws BusinessLogicException {
        if (items == null || items.isEmpty()) {
            throw new BusinessLogicException("El pedido debe tener al menos un producto.");
        }
        for (PedidoCheckoutDTO.ItemCarritoDTO item : items) {
            if (item == null) {
                throw new BusinessLogicException("El pedido contiene un item invalido.");
            }
            if (item.getProductoId() == null || item.getProductoId() <= 0) {
                throw new BusinessLogicException("Cada item debe tener un producto valido.");
            }
            if (item.getCantidad() == null || item.getCantidad() <= 0) {
                throw new BusinessLogicException("La cantidad de cada item debe ser mayor a 0.");
            }
            if (item.getPrecioAplicado() <= 0) {
                throw new BusinessLogicException("El precio aplicado de cada item debe ser mayor a 0.");
            }
        }
    }

    private void validarMetodoPago(PedidoCheckoutDTO request) throws BusinessLogicException {
        if (request.getMetodoPago() == null || request.getMetodoPago().trim().isEmpty()) {
            request.setMetodoPago("TARJETA");
            return;
        }

        String metodoPago = request.getMetodoPago().trim().toUpperCase();
        if (!metodoPago.equals("TARJETA")
                && !metodoPago.equals("YAPE_PLIN")) {
            throw new BusinessLogicException("El metodo de pago seleccionado no es valido.");
        }
        request.setMetodoPago(metodoPago);
    }

    private void validarComprobanteCheckout(PedidoCheckoutDTO request) throws BusinessLogicException {
        if (request.getTipoComprobante() == null || request.getTipoComprobante().trim().isEmpty()) {
            request.setTipoComprobante(null);
            return;
        }

        String tipoComprobante = request.getTipoComprobante().trim().toUpperCase();
        request.setTipoComprobante(tipoComprobante);

        if (tipoComprobante.equals("BOLETA")) {
            if (request.getDni() == null || !DNI_PATTERN.matcher(request.getDni().trim()).matches()) {
                throw new BusinessLogicException("Para boleta debe ingresar un DNI de 8 digitos.");
            }
            request.setDni(request.getDni().trim());
            return;
        }

        if (tipoComprobante.equals("FACTURA")) {
            if (request.getRuc() == null || !RUC_PATTERN.matcher(request.getRuc().trim()).matches()) {
                throw new BusinessLogicException("Para factura debe ingresar un RUC de 11 digitos que empiece con 10 o 20.");
            }
            if (request.getRazonSocial() == null || request.getRazonSocial().trim().isEmpty()) {
                throw new BusinessLogicException("La razon social es obligatoria para factura.");
            }
            if (request.getDireccionFiscal() == null || request.getDireccionFiscal().trim().isEmpty()) {
                throw new BusinessLogicException("La direccion fiscal es obligatoria para factura.");
            }
            request.setRuc(request.getRuc().trim());
            request.setRazonSocial(request.getRazonSocial().trim());
            request.setDireccionFiscal(request.getDireccionFiscal().trim());
            return;
        }

        throw new BusinessLogicException("El tipo de comprobante debe ser BOLETA o FACTURA.");
    }
}
