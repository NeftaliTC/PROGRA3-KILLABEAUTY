package pe.edu.pucp.killabeauty.killarest.services.ventas;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.edu.pucp.killaBeauty.bl.ComprobantePagoBL;
import pe.edu.pucp.killaBeauty.bl.EnvioBL;
import pe.edu.pucp.killaBeauty.bl.Impl.ComprobantePagoBLImpl;
import pe.edu.pucp.killaBeauty.bl.Impl.EnvioBLImpl;
import pe.edu.pucp.killaBeauty.bl.Impl.PagoBLImpl;
import pe.edu.pucp.killaBeauty.bl.Impl.PedidoBLImpl;
import pe.edu.pucp.killaBeauty.bl.PagoBL;
import pe.edu.pucp.killaBeauty.bl.PedidoBL;
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

            Pedido pedido = pedidoBL.createFromCart(
                    request.getUsuarioId(),
                    request.getDireccionId(),
                    request.getCuponId(),
                    mapDetallesCarrito(request.getItems())
            );

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
        if (items == null) {
            return detalles;
        }

        for (PedidoCheckoutDTO.ItemCarritoDTO item : items) {
            DetallePedido detalle = new DetallePedido();
            Producto producto = new Producto();
            producto.setId(item != null && item.getProductoId() != null ? item.getProductoId() : 0);
            detalle.setProducto(producto);
            detalle.setCantidad(item != null && item.getCantidad() != null ? item.getCantidad() : 0);
            detalles.add(detalle);
        }

        return detalles;
    }
}
