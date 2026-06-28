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
            if (request == null) {
                throw new BusinessLogicException("La solicitud de checkout no puede ser nula.");
            }

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
        if (items == null) return detalles;

        for (PedidoCheckoutDTO.ItemCarritoDTO item : items) {
            DetallePedido detalle = new DetallePedido();
            Producto producto = new Producto();
            producto.setId(item != null && item.getProductoId() != null ? item.getProductoId() : 0);
            detalle.setProducto(producto);
            detalle.setCantidad(item != null && item.getCantidad() != null ? item.getCantidad() : 0);
            detalle.setPrecioAplicado(item != null ? item.getPrecioAplicado() : 0);
            detalles.add(detalle);
        }
        return detalles;
    }

    private MetodoPago resolverMetodoPago(String metodoPago) {
        if (metodoPago == null) return MetodoPago.TARJETA;
        return switch (metodoPago.toUpperCase()) {
            case "YAPE_PLIN" -> MetodoPago.YAPE;
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
}
